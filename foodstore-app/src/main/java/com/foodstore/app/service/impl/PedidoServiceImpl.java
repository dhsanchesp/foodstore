package com.foodstore.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodstore.app.converter.DozerHelper;
import com.foodstore.app.dao.IPedidoDAO;
import com.foodstore.app.dto.IngredienteDTO;
import com.foodstore.app.dto.IngredienteSanduichePedidoDTO;
import com.foodstore.app.dto.PedidoDTO;
import com.foodstore.app.dto.SanduichePedidoDTO;
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.entity.PedidoEntity;
import com.foodstore.app.enums.IngredienteEnum;
import com.foodstore.app.enums.PromocaoAtiva;
import com.foodstore.app.service.IIngredienteService;
import com.foodstore.app.service.IPedidoService;
import com.foodstore.app.service.conversor.Conversor;

@Service
public class PedidoServiceImpl implements IPedidoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoServiceImpl.class);

    private static final int QTD_INGREDIENTE_PROMOCAO = 3;
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final BigDecimal DEZ_POR_CENTO = new BigDecimal(10);

    @Autowired
    private IPedidoDAO pedidoDAO;

    @Autowired
    private IIngredienteService ingredienteService;

    @Override
    @Transactional
    public PedidoDTO incluirPedido(final PedidoDTO pedidoDTO) {

        PedidoDTO pedidoParaCadastrar = pedidoDTO;

        if (pedidoParaCadastrar.getValorPedidoSemDesconto() == null
                        || pedidoParaCadastrar.getValorPedidoComDesconto() == null) {

            for (SanduichePedidoDTO sanduichePedidoDTO : pedidoParaCadastrar.getSanduiches()) {
                sanduichePedidoDTO = this.calculaValorSanduichePedido(sanduichePedidoDTO);
            }

            pedidoParaCadastrar = this.calculaValoresPedido(pedidoDTO);
        }

        final PedidoEntity pedidoEntity = Conversor.convertePedidoDTOEmPedidoEntity(pedidoParaCadastrar);

        this.pedidoDAO.incluirPedido(pedidoEntity);

        return Conversor.convertePedidoEntityEmDTO(pedidoEntity);
    }

    @Override
    public PedidoDTO consultarPedidoPorCodigo(final Long codigoPedido) {

        final PedidoEntity pedidoCadastrado = this.pedidoDAO.consultarPedidoPorCodigo(codigoPedido);

        final PedidoDTO pedidoDTOCadastrado = Conversor.convertePedidoEntityEmDTO(pedidoCadastrado);

        return pedidoDTOCadastrado;
    }

    @Override
    public List<PedidoDTO> consultarTodosPedidos() {
        final List<PedidoEntity> listaPedidosCadastrados = this.pedidoDAO.consultarTodosPedidos();

        return Conversor.converteListaPedidoEntityEmDTO(listaPedidosCadastrados);
    }

    /**
     * Calcula os valores totais do pedido.
     *
     * @param pedidoDTO o pedido DTO
     * @param listaSanduiches a lista de sanduiches
     * @return o pedido DTO com os valores calculados
     */
    @Override
    public PedidoDTO calculaValoresPedido(final PedidoDTO pedidoDTO) {

        BigDecimal valorPedidoComDesconto = BigDecimal.ZERO;
        BigDecimal valorPedidoDesconto = BigDecimal.ZERO;
        BigDecimal valorPedidoSemDesconto = BigDecimal.ZERO;

        final PedidoDTO pedidoCalculado = pedidoDTO;

        for (final SanduichePedidoDTO sanduiche : pedidoDTO.getSanduiches()) {

            valorPedidoComDesconto = valorPedidoComDesconto.add(sanduiche.getValorSanduicheComDesconto());
            valorPedidoDesconto = valorPedidoDesconto.add(sanduiche.getValorDescontoTotal());
            valorPedidoSemDesconto = valorPedidoSemDesconto.add(sanduiche.getValorSanduicheSemDesconto());
        }

        pedidoCalculado.setValorPedidoComDesconto(valorPedidoComDesconto);
        pedidoCalculado.setValorPedidoDesconto(valorPedidoDesconto);
        pedidoCalculado.setValorPedidoSemDesconto(valorPedidoSemDesconto);

        return pedidoCalculado;

    }

    @Override
    public SanduichePedidoDTO calculaValorSanduichePedido(final SanduichePedidoDTO sanduichePedidoDTO) {

        final SanduichePedidoDTO sanduicheCalculado = sanduichePedidoDTO;

        final Map<Integer, IngredienteEntity> mapaIngredientes = this.montaDominioIngredientes();

        Boolean promoCarne = Boolean.FALSE;
        Boolean promoQueijo = Boolean.FALSE;

        Boolean alface = Boolean.FALSE;
        Boolean bacon = Boolean.FALSE;

        this.inicializandoComValoresDefault(sanduicheCalculado);

        BigDecimal valorTotalSanduiche = BigDecimal.ZERO;
        for (final IngredienteSanduichePedidoDTO ingredienteDTO : sanduichePedidoDTO.getIngredientes()) {

            final IngredienteEntity ingredienteEntity = mapaIngredientes.get(ingredienteDTO.getCodigoIngrediente());

            valorTotalSanduiche = valorTotalSanduiche.add(
                            multiplicaValorPorQuantidade(ingredienteDTO.getQuantidadeIngrediente(),
                                            ingredienteEntity.getValorIngrediente()));

            if (!promoCarne) {
                promoCarne = this.isIngredienteEQuantidadePromocional(ingredienteDTO, IngredienteEnum.HAMBURGUER);
            }

            if (!promoQueijo) {
                promoQueijo = this.isIngredienteEQuantidadePromocional(ingredienteDTO, IngredienteEnum.QUEIJO);
            }

            if (!alface) {
                alface = this.isIngredientePromocao(ingredienteDTO, IngredienteEnum.ALFACE);
            }
            if (!bacon) {
                bacon = this.isIngredientePromocao(ingredienteDTO, IngredienteEnum.BACON);
            }

        }

        sanduicheCalculado.setValorSanduicheSemDesconto(valorTotalSanduiche);

        this.calculaEPreencheValorPromocionalCarneEQueijo(sanduichePedidoDTO, sanduicheCalculado, mapaIngredientes,
                        promoCarne, promoQueijo);

        this.calculaEPreencheValorPromocionalLight(sanduicheCalculado, alface, bacon, promoCarne, promoQueijo);

        sanduicheCalculado.setValorDescontoTotal(this.somaValorDosDescontos(sanduicheCalculado, promoCarne, promoQueijo,
                        this.isPromoLight(alface, bacon)));

        sanduicheCalculado.setValorSanduicheComDesconto(this.calculaValorSanduicheComDesconto(sanduicheCalculado));

        return sanduicheCalculado;
    }

    /**
     * Obtém o valor percentual.
     *
     * @param base a base
     * @param pct o percentual
     * @return o valor calculado
     */
    private static BigDecimal percentage(final BigDecimal base, final BigDecimal pct) {
        return base.multiply(pct).divide(ONE_HUNDRED).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Monta um mapa com o Dominio de ingredientes da base de dados.
     *
     * @return um mapa com os ingredientes cadastrados.
     */
    private Map<Integer, IngredienteEntity> montaDominioIngredientes() {

        final List<IngredienteDTO> ingredientesDTO = this.ingredienteService.buscarTodosIngredientes();

        final List<IngredienteEntity> ingredientesBase = DozerHelper.map(ingredientesDTO, IngredienteEntity.class);

        final Map<Integer, IngredienteEntity> mapaIngredientes = new HashMap<>();
        for (final IngredienteEntity ingredienteEntity : ingredientesBase) {
            mapaIngredientes.put(ingredienteEntity.getCodigoIngrediente(), ingredienteEntity);
        }
        return mapaIngredientes;
    }

    /**
     * Efetua a multiplicação de um valor BigDecimal por uma quantidade de ingrediente
     *
     * @param qtd a quantidade de ingredientes
     * @param valor o valor do ingrediente
     * @return o valor calculado
     */
    private static BigDecimal multiplicaValorPorQuantidade(final Integer qtd, final BigDecimal valor) {
        BigDecimal resultado;

        resultado = valor.multiply(new BigDecimal(qtd));

        return resultado.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Verifica se tanto o ingrediente e a quantidade desse ingrediente se encaixam na promoção
     *
     * @param ingredienteDTO o ingrediente DTO
     * @param ingredienteEnum o Enum de Ingredientes
     * @return true, se os requisitos forem atendidos
     */
    private boolean isIngredienteEQuantidadePromocional(final IngredienteSanduichePedidoDTO ingredienteDTO,
                    final IngredienteEnum ingredienteEnum) {

        return this.isIngredientePromocao(ingredienteDTO, ingredienteEnum)
                        && this.isMaiorOuIgualQtdIngredientePromo(ingredienteDTO);
    }

    /**
     * Calcula E preenche o valor promocional da promo light, e ativa a flag de promoção ativa. <br>
     * Se não houver nenhum desconto aplicado no sanduíche, o calculo é feito em cima do valor total do sanduiche sem
     * desconto. <br>
     * Caso haja alguma promoção ativa, o calculo é feito no valor total do sanduiche com desconto.
     *
     * @param sanduicheCalculado o sanduiche calculado
     * @param alface o alface
     * @param bacon o bacon
     */
    private void calculaEPreencheValorPromocionalLight(final SanduichePedidoDTO sanduicheCalculado,
                    final Boolean alface,
                    final Boolean bacon,
                    final Boolean promoCarne,
                    final Boolean promoQueijo) {

        if (this.isPromoLight(alface, bacon)) {
            sanduicheCalculado.setPromoLight(PromocaoAtiva.SIM.getCodigo());

            BigDecimal valorDescontoLight;
            BigDecimal valorOutrosDescontos;

            if (promoCarne || promoQueijo) {
                valorOutrosDescontos = sanduicheCalculado.getValorDescontoPromoCarne()
                                .add(sanduicheCalculado.getValorDescontoPromoQueijo());
                valorDescontoLight = percentage(
                                sanduicheCalculado.getValorSanduicheSemDesconto().subtract(valorOutrosDescontos),
                                DEZ_POR_CENTO);
            } else {
                valorDescontoLight = percentage(
                                sanduicheCalculado.getValorSanduicheSemDesconto(),
                                DEZ_POR_CENTO);
            }

            sanduicheCalculado.setValorDescontoPromoLight(valorDescontoLight);
        }
    }

    /**
     * Calcula e preenche valor de desconto da promo carne e para a promo queijo, e ativa a flag de promoção ativa.
     *
     * @param sanduichePedidoDTO o sanduiche pedido DTO
     * @param sanduicheCalculado o sanduiche calculado
     * @param mapaIngredientes o mapa ingredientes
     * @param promoCarne se ativa ou inativa
     * @param promoQueijo se ativa ou inativa
     */
    private void calculaEPreencheValorPromocionalCarneEQueijo(final SanduichePedidoDTO sanduichePedidoDTO,
                    final SanduichePedidoDTO sanduicheCalculado,
                    final Map<Integer, IngredienteEntity> mapaIngredientes,
                    final Boolean promoCarne,
                    final Boolean promoQueijo) {

        if (promoCarne) {
            sanduicheCalculado.setPromoCarne(PromocaoAtiva.SIM.getCodigo());
            sanduicheCalculado.setValorDescontoPromoCarne(this.calculaValorPromocional(IngredienteEnum.HAMBURGUER,
                            promoCarne, sanduichePedidoDTO.getIngredientes(), mapaIngredientes));

        }

        if (promoQueijo) {
            sanduicheCalculado.setPromoQueijo(PromocaoAtiva.SIM.getCodigo());
            sanduicheCalculado.setValorDescontoPromoQueijo(this.calculaValorPromocional(IngredienteEnum.QUEIJO,
                            promoQueijo, sanduichePedidoDTO.getIngredientes(), mapaIngredientes));

        }
    }

    /**
     * Calcula o valor promocional para a promoCarne e/ou promoQueijo.
     *
     * @param IngredienteEnum o ingrediente enum
     * @param promoAtiva se a promo estiver ativa
     * @param listaIngredientesDTO a lista de ingredientes DTO
     * @param dominioIngredientes o dominio de ingredientes
     * @return o valor da promocao
     */
    private BigDecimal calculaValorPromocional(final IngredienteEnum IngredienteEnum,
                    final boolean promoAtiva,
                    final List<IngredienteSanduichePedidoDTO> listaIngredientesDTO,
                    final Map<Integer, IngredienteEntity> dominioIngredientes) {

        for (final IngredienteSanduichePedidoDTO ingredienteDTO : listaIngredientesDTO) {

            if (this.isIngredientePromocao(ingredienteDTO, IngredienteEnum)) {

                final IngredienteEntity ingredienteEntity = dominioIngredientes
                                .get(ingredienteDTO.getCodigoIngrediente());

                final Integer quantidadeIngredientesDesconto = ingredienteDTO.getQuantidadeIngrediente()
                                - calculaQuantidadeACobrar(ingredienteDTO.getQuantidadeIngrediente());

                return multiplicaValorPorQuantidade(
                                quantidadeIngredientesDesconto,
                                ingredienteEntity.getValorIngrediente());
            }
        }

        return BigDecimal.ZERO;
    }

    /**
     * Verifica se o sanduiche se enquadra na PromoLight, onde há Alface e NÃO há Bacon.
     *
     * @param alface o alface
     * @param bacon o bacon
     * @return true, se for enquadrado na promoLight
     */
    private boolean isPromoLight(final Boolean alface, final Boolean bacon) {

        return alface && !bacon;
    }

    /**
     * Verifica se o ingrediente do sanduiche é um dos ingredientes da promoCarne, promoQueijo ou promoLight.
     *
     * @param ingredienteDTO o ingrediente DTO
     * @param ingredienteEnum o ingrediente enum
     * @return true, se for o ingrediente da promoção
     */
    private boolean isIngredientePromocao(final IngredienteSanduichePedidoDTO ingredienteDTO,
                    final IngredienteEnum ingredienteEnum) {

        return ingredienteEnum.getCodigo().equals(ingredienteDTO.getCodigoIngrediente());
    }

    /**
     * Verifica se a quantidade de ingredientes é igual ou maior a quantidade de ingredientes para se aplicar a promoção
     * de descontos. <br>
     * A quantidade de ingredientes para a promoção está configurada na constante <b>QTD_INGREDIENTE_PROMOCAO</b>
     *
     * @param ingredienteDTO o ingrediente DTO
     * @return true, se encaixar no parametro da promoção
     */
    private boolean isMaiorOuIgualQtdIngredientePromo(final IngredienteSanduichePedidoDTO ingredienteDTO) {
        return ingredienteDTO.getQuantidadeIngrediente().compareTo(QTD_INGREDIENTE_PROMOCAO) == 0
                        || ingredienteDTO.getQuantidadeIngrediente().compareTo(QTD_INGREDIENTE_PROMOCAO) == 1;
    }

    /**
     * Calcula valor do sanduiche com desconto. <br>
     * Subtrai o valor total de descontos do valor total sem desconto
     *
     * @param sanduicheCalculado o sanduiche
     * @return o valor total com desconto
     */
    private BigDecimal calculaValorSanduicheComDesconto(final SanduichePedidoDTO sanduicheCalculado) {

        return sanduicheCalculado.getValorSanduicheSemDesconto()
                        .subtract(sanduicheCalculado.getValorDescontoTotal());
    }

    /**
     * Soma o valor de todos os descontos aplicados no sanduiche descontos.
     *
     * @param sanduicheCalculado o sanduiche
     * @param promoCarne a promo carne
     * @param promoQueijo a promo queijo
     * @param promoLight a promo light
     * @return o valor total de descontos
     */
    private BigDecimal somaValorDosDescontos(final SanduichePedidoDTO sanduicheCalculado,
                    final Boolean promoCarne,
                    final Boolean promoQueijo,
                    final Boolean promoLight) {

        BigDecimal somaValorDosDescontos = BigDecimal.ZERO;

        if (promoCarne) {
            somaValorDosDescontos = somaValorDosDescontos.add(sanduicheCalculado.getValorDescontoPromoCarne());
        }

        if (promoQueijo) {
            somaValorDosDescontos = somaValorDosDescontos.add(sanduicheCalculado.getValorDescontoPromoQueijo());
        }

        if (promoLight) {
            somaValorDosDescontos = somaValorDosDescontos.add(sanduicheCalculado.getValorDescontoPromoLight());
        }

        return somaValorDosDescontos;

    }

    /**
     * Inicializando todos os atributos de valor monetário como <b>BigDecimal.ZERO</b>, <br>
     * e os campos de promoção ativa para <b>PromocaoAtiva.NAO </b>.
     *
     * @param sanduicheCalculado o sanduiche
     */
    private void inicializandoComValoresDefault(final SanduichePedidoDTO sanduicheCalculado) {
        sanduicheCalculado.setValorDescontoPromoCarne(BigDecimal.ZERO);
        sanduicheCalculado.setValorDescontoPromoLight(BigDecimal.ZERO);
        sanduicheCalculado.setValorDescontoPromoQueijo(BigDecimal.ZERO);
        sanduicheCalculado.setValorDescontoTotal(BigDecimal.ZERO);
        sanduicheCalculado.setValorSanduicheComDesconto(BigDecimal.ZERO);
        sanduicheCalculado.setValorSanduicheSemDesconto(BigDecimal.ZERO);

        sanduicheCalculado.setPromoCarne(PromocaoAtiva.NAO.getCodigo());
        sanduicheCalculado.setPromoQueijo(PromocaoAtiva.NAO.getCodigo());
        sanduicheCalculado.setPromoLight(PromocaoAtiva.NAO.getCodigo());
    }

    /**
     * Calcula quantidade de ingredientes a ser cobrada no caso de uma promoção ser aplicada no sanduiche.
     *
     * @param qtdIngrediente a quantidade de ingrediente
     * @return a quantidade a ser cobrada
     */
    private static int calculaQuantidadeACobrar(final Integer qtdIngrediente) {

        final int novaQuantidade = qtdIngrediente - qtdIngrediente / 3;

        return novaQuantidade;

    }

}
