package com.foodstore.app.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.converter.DozerHelper;
import com.foodstore.app.dto.IngredienteDTO;
import com.foodstore.app.dto.PedidoDTO;
import com.foodstore.app.dto.SanduichePedidoDTO;
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.enums.IngredienteEnum;
import com.foodstore.app.enums.PromocaoAtiva;
import com.foodstore.app.service.IIngredienteService;
import com.foodstore.app.service.IPedidoService;
import com.foodstore.app.service.test.utils.ServiceTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class PedidoServiceTest {

    private static final BigDecimal DEZ = new BigDecimal("10");
    private static final BigDecimal CEM = new BigDecimal("100");

    @Autowired
    private IPedidoService service;

    @Autowired
    private IIngredienteService ingredienteService;

    private Map<Integer, IngredienteEntity> dominioIngredientes;

    /**
     * Prepara um mapa com o dominio dos ingredientes para ser utilizado no escopo de testes.
     */
    @Before
    public void obtemDominioIngredientes() {

        final List<IngredienteDTO> ingredientesDTO = this.ingredienteService.buscarTodosIngredientes();

        final List<IngredienteEntity> ingredientesDomain = DozerHelper.map(ingredientesDTO, IngredienteEntity.class);

        final Map<Integer, IngredienteEntity> dominioIngredientes = new HashMap<>();

        for (final IngredienteEntity ingredienteEntity : ingredientesDomain) {
            dominioIngredientes.put(ingredienteEntity.getCodigoIngrediente(), ingredienteEntity);
        }

        this.dominioIngredientes = dominioIngredientes;

    }

    /**
     * Testa a consulta de um pedido pelo codigo do pedido.
     */
    @Test
    public void testBuscarPedidoPorCodigo() {

        final PedidoDTO pedido = this.service.consultarPedidoPorCodigo(9999L);

        assertNotNull(pedido);
    }

    /**
     * Testa a inclusão de um pedido com os valores previamente calculados.
     */
    @Test
    public void testIncluirPedido() {

        PedidoDTO pedidoDTO = ServiceTestUtils.criaPedidoDTO(this.dominioIngredientes);

        final List<SanduichePedidoDTO> sanduichesCalculados = new ArrayList<>();
        for (final SanduichePedidoDTO sanduicheSemCalculo : pedidoDTO.getSanduiches()) {
            sanduichesCalculados.add(this.service.calculaValorSanduichePedido(sanduicheSemCalculo));
        }

        pedidoDTO.setSanduiches(sanduichesCalculados);

        pedidoDTO = this.service.calculaValoresPedido(pedidoDTO);

        try {

            final PedidoDTO pedidoCadastrado = this.service.incluirPedido(pedidoDTO);

            final PedidoDTO pedidoEncontrado = this.service
                            .consultarPedidoPorCodigo(pedidoCadastrado.getCodigoPedido());
            assertNotNull(pedidoEncontrado);
            final List<SanduichePedidoDTO> listaSanduichesPedido = pedidoEncontrado.getSanduiches();
            assertNotNull(listaSanduichesPedido);
            for (final SanduichePedidoDTO sanduichePedidoDTO : listaSanduichesPedido) {
                assertNotNull(sanduichePedidoDTO.getIngredientes());
            }

        } catch (final Exception e) {
            fail();
        }

    }

    /**
     * Testa a inclusão de um pedido sem o valor dos itens previamente calculados
     */
    @Test
    public void testIncluirPedidoSemPrecos() {

        PedidoDTO pedidoDTO = ServiceTestUtils.criaPedidoDTO(this.dominioIngredientes);

        pedidoDTO.setValorPedidoComDesconto(null);
        pedidoDTO.setValorPedidoSemDesconto(null);
        pedidoDTO.setValorPedidoDesconto(null);

        for (SanduichePedidoDTO sanduichePedidoDto : pedidoDTO.getSanduiches()) {
            sanduichePedidoDto.setValorDescontoPromoCarne(null);
            sanduichePedidoDto.setValorDescontoPromoLight(null);
            sanduichePedidoDto.setValorDescontoPromoQueijo(null);
            sanduichePedidoDto.setPromoCarne(null);
            sanduichePedidoDto.setPromoLight(null);
            sanduichePedidoDto.setPromoQueijo(null);

            sanduichePedidoDto.setValorDescontoTotal(null);
            sanduichePedidoDto.setValorSanduicheComDesconto(null);
            sanduichePedidoDto.setValorSanduicheSemDesconto(null);
        }

        try {

            final PedidoDTO pedidoCadastrado = this.service.incluirPedido(pedidoDTO);

            final PedidoDTO pedidoEncontrado = this.service
                            .consultarPedidoPorCodigo(pedidoCadastrado.getCodigoPedido());

            assertNotNull(pedidoEncontrado);
            assertTrue(!BigDecimal.ZERO.equals(pedidoEncontrado.getValorPedidoComDesconto()));
            assertTrue(!BigDecimal.ZERO.equals(pedidoEncontrado.getValorPedidoSemDesconto()));

            final List<SanduichePedidoDTO> listaSanduichesPedido = pedidoEncontrado.getSanduiches();
            assertNotNull(listaSanduichesPedido);
            for (final SanduichePedidoDTO sanduichePedidoDTO : listaSanduichesPedido) {
                assertNotNull(sanduichePedidoDTO.getIngredientes());
                assertTrue(!BigDecimal.ZERO.equals(sanduichePedidoDTO.getValorSanduicheComDesconto()));
                assertTrue(!BigDecimal.ZERO.equals(sanduichePedidoDTO.getValorSanduicheSemDesconto()));
            }

        } catch (final Exception e) {
            fail();
        }

    }

    /**
     * Testa o cálculo do sanduiche apenas para a promo carne. <br>
     * <br>
     * As variáveis: <br>
     * <b>qtdHamburguerTotal</b> : Quantidade TOTAL de Hambúrgueres <br>
     * <b>qtdHamburguerDesconto</b> : Quantidade de Hambúrgueres que será DESCONTADA do valor total.
     */
    @Test
    public void testCalculoApenasPromoCarne() {

        final IngredienteEntity hamburguer = this.dominioIngredientes.get(IngredienteEnum.HAMBURGUER.getCodigo());
        final Integer qtdHamburguerTotal = 6;
        final Integer qtdHamburguerDesconto = 2;

        final BigDecimal valorDesconto = hamburguer.getValorIngrediente()
                        .multiply(new BigDecimal(qtdHamburguerDesconto));

        final SanduichePedidoDTO sanduicheDTO = ServiceTestUtils.criaSanduichePedidoDTO(1, qtdHamburguerTotal, 1, 1, 0,
                        0, this.dominioIngredientes);

        final SanduichePedidoDTO sanduicheCalculado = this.service.calculaValorSanduichePedido(sanduicheDTO);

        assertTrue(valorDesconto.equals(sanduicheCalculado.getValorDescontoPromoCarne()));
        assertTrue(valorDesconto.equals(sanduicheCalculado.getValorDescontoTotal()));
        assertTrue(
                        sanduicheCalculado.getValorSanduicheSemDesconto().subtract(valorDesconto)
                                        .equals(sanduicheCalculado.getValorSanduicheComDesconto()));

        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoQueijo()));
        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoLight()));

        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoCarne()));
        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoQueijo()));
        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoLight()));
    }

    /**
     * Testa o cálculo do sanduiche com as promoções promoCarne e promoQueijo ativas. <br>
     * As variáveis: <br>
     * <b>qtdHamburguerTotal</b> : Quantidade TOTAL de Hambúrgueres <br>
     * <b>qtdHamburguerDesconto</b> : Quantidade de Hambúrgueres que será DESCONTADA do valor total. <br>
     * <b>qtdQueijoTotal</b> : Quantidade TOTAL de Queijos <br>
     * <b>qtdQueijoDesconto</b> : Quantidade de Queijos que será DESCONTADA do valor total.
     */
    @Test
    public void testCalculoPromoCarneEPromoQueijo() {

        final IngredienteEntity queijo = this.dominioIngredientes.get(IngredienteEnum.QUEIJO.getCodigo());
        final IngredienteEntity carne = this.dominioIngredientes.get(IngredienteEnum.HAMBURGUER.getCodigo());
        final Integer qtdQueijoTotal = 6;
        final Integer qtdQueijoDesconto = 2;
        final Integer qtdHamburguerTotal = 6;
        final Integer qtdHamburguerDesconto = 2;

        final BigDecimal vlrDescQueijo = queijo.getValorIngrediente().multiply(new BigDecimal(qtdQueijoDesconto));
        final BigDecimal vlrDescCarne = carne.getValorIngrediente().multiply(new BigDecimal(qtdHamburguerDesconto));

        final SanduichePedidoDTO sanduicheDTO = ServiceTestUtils.criaSanduichePedidoDTO(1, qtdHamburguerTotal,
                        qtdQueijoTotal, 1, 0, 0,
                        this.dominioIngredientes);

        final SanduichePedidoDTO sanduicheCalculado = this.service.calculaValorSanduichePedido(sanduicheDTO);

        assertTrue(vlrDescQueijo.equals(sanduicheCalculado.getValorDescontoPromoQueijo()));
        assertTrue(vlrDescCarne.equals(sanduicheCalculado.getValorDescontoPromoCarne()));
        assertTrue(vlrDescQueijo.add(vlrDescCarne).equals(sanduicheCalculado.getValorDescontoTotal()));

        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoLight()));

        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoCarne()));
        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoQueijo()));
        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoLight()));
    }

    /**
     * Testa o cálculo do sanduiche apenas para a promoQueijo. <br>
     * As variáveis: <br>
     * <b>qtdQueijoTotal</b> : Quantidade TOTAL de Queijos <br>
     * <b>qtdQueijoDesconto</b> : Quantidade de Queijos que será DESCONTADA do valor total.
     */
    @Test
    public void testCalculoApenasPromoQueijo() {

        final IngredienteEntity queijo = this.dominioIngredientes.get(IngredienteEnum.QUEIJO.getCodigo());
        final Integer qtdQueijoTotal = 6;
        final Integer qtdQueijoDesconto = 2;

        final BigDecimal valorDesconto = queijo.getValorIngrediente().multiply(new BigDecimal(qtdQueijoDesconto));

        final SanduichePedidoDTO sanduicheDTO = ServiceTestUtils.criaSanduichePedidoDTO(1, 1, qtdQueijoTotal, 1, 0, 0,
                        this.dominioIngredientes);

        final SanduichePedidoDTO sanduicheCalculado = this.service.calculaValorSanduichePedido(sanduicheDTO);

        assertTrue(valorDesconto.equals(sanduicheCalculado.getValorDescontoPromoQueijo()));
        assertTrue(valorDesconto.equals(sanduicheCalculado.getValorDescontoTotal()));

        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoCarne()));
        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoLight()));

        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoCarne()));
        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoQueijo()));
        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoLight()));
    }

    /**
     * Testa o cálculo do sanduiche apenas para a promo light. <br>
     * <br>
     * As variáveis: <br>
     * <b>qtdHamburguerTotal</b> : Quantidade TOTAL de Hambúrgueres <br>
     * <b>qtdHamburguerDesconto</b> : Quantidade de Hambúrgueres que será DESCONTADA do valor total.
     */
    @Test
    public void testCalculoApenasPromoLight() {

        final SanduichePedidoDTO sanduicheDTO = ServiceTestUtils.criaSanduichePedidoDTO(1, 1, 1, 0, 0, 1,
                        this.dominioIngredientes);

        final SanduichePedidoDTO sanduicheCalculado = this.service.calculaValorSanduichePedido(sanduicheDTO);
        final BigDecimal valorDesconto = sanduicheCalculado.getValorSanduicheSemDesconto().multiply(DEZ).divide(CEM);

        assertTrue(valorDesconto.equals(sanduicheCalculado.getValorDescontoPromoLight()));
        assertTrue(valorDesconto.equals(sanduicheCalculado.getValorDescontoTotal()));
        assertTrue(
                        sanduicheCalculado.getValorSanduicheSemDesconto().subtract(valorDesconto)
                                        .equals(sanduicheCalculado.getValorSanduicheComDesconto()));

        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoCarne()));
        assertTrue(BigDecimal.ZERO.equals(sanduicheCalculado.getValorDescontoPromoQueijo()));

        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoCarne()));
        assertTrue(PromocaoAtiva.NAO.getCodigo().equals(sanduicheCalculado.getPromoQueijo()));
        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoLight()));
    }

    /**
     * Testa o cálculo do sanduiche com as promoções promoCarne e promoQueijo ativas. <br>
     * As variáveis: <br>
     * <b>qtdHamburguerTotal</b> : Quantidade TOTAL de Hambúrgueres <br>
     * <b>qtdHamburguerDesconto</b> : Quantidade de Hambúrgueres que será DESCONTADA do valor total. <br>
     * <b>qtdQueijoTotal</b> : Quantidade TOTAL de Queijos <br>
     * <b>qtdQueijoDesconto</b> : Quantidade de Queijos que será DESCONTADA do valor total.
     */
    @Test
    public void testCalculoTodasAsPromocoes() {

        final IngredienteEntity queijo = this.dominioIngredientes.get(IngredienteEnum.QUEIJO.getCodigo());
        final IngredienteEntity carne = this.dominioIngredientes.get(IngredienteEnum.HAMBURGUER.getCodigo());
        final Integer qtdQueijoTotal = 6;
        final Integer qtdQueijoDesconto = 2;
        final Integer qtdHamburguerTotal = 6;
        final Integer qtdHamburguerDesconto = 2;

        final BigDecimal vlrDescQueijo = queijo.getValorIngrediente().multiply(new BigDecimal(qtdQueijoDesconto));
        final BigDecimal vlrDescCarne = carne.getValorIngrediente().multiply(new BigDecimal(qtdHamburguerDesconto));
        final BigDecimal totalVlrCarneQueijo = vlrDescCarne.add(vlrDescQueijo);

        final SanduichePedidoDTO sanduicheDTO = ServiceTestUtils.criaSanduichePedidoDTO(1, qtdHamburguerTotal,
                        qtdQueijoTotal, 0, 0, 1,
                        this.dominioIngredientes);

        final SanduichePedidoDTO sanduicheCalculado = this.service.calculaValorSanduichePedido(sanduicheDTO);
        final BigDecimal vlrDescLight = sanduicheCalculado.getValorSanduicheSemDesconto()
                        .subtract(totalVlrCarneQueijo).multiply(DEZ).divide(CEM);

        assertTrue(vlrDescQueijo.equals(sanduicheCalculado.getValorDescontoPromoQueijo()));
        assertTrue(vlrDescCarne.equals(sanduicheCalculado.getValorDescontoPromoCarne()));
        assertTrue(vlrDescQueijo.add(vlrDescCarne).add(vlrDescLight)
                        .equals(sanduicheCalculado.getValorDescontoTotal()));

        assertTrue(vlrDescLight.equals(sanduicheCalculado.getValorDescontoPromoLight()));

        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoCarne()));
        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoQueijo()));
        assertTrue(PromocaoAtiva.SIM.getCodigo().equals(sanduicheCalculado.getPromoLight()));
    }

}
