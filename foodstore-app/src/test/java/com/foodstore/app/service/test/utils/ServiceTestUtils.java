package com.foodstore.app.service.test.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodstore.app.dto.IngredienteSanduichePedidoDTO;
import com.foodstore.app.dto.PedidoDTO;
import com.foodstore.app.dto.SanduichePedidoDTO;
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.entity.IngredienteSanduichePedidoEntity;
import com.foodstore.app.entity.SanduichePedidoEntity;
import com.foodstore.app.enums.IngredienteEnum;
import com.foodstore.app.enums.PromocaoAtiva;

public class ServiceTestUtils {

	private static final Integer ZERO = 0;

	/**
	 * Cria ingrediente sanduiche pedido DTO.
	 *
	 * @param ingredienteEntity the ingrediente entity
	 * @param quantidade the quantidade
	 * @return the ingrediente sanduiche pedido DTO
	 */
	public static IngredienteSanduichePedidoDTO criaIngredienteSanduichePedidoDTO(final IngredienteEntity ingredienteEntity,
		final Integer quantidade) {

		final IngredienteSanduichePedidoDTO ingredienteSanduichePedidoDTO = new IngredienteSanduichePedidoDTO();

		if (ZERO.equals(quantidade)) {
			return null;
		}
		ingredienteSanduichePedidoDTO.setCodigoIngrediente(ingredienteEntity.getCodigoIngrediente());
		ingredienteSanduichePedidoDTO.setNomeIngrediente(ingredienteEntity.getNomeIngrediente());
		ingredienteSanduichePedidoDTO.setQuantidadeIngrediente(quantidade);
		ingredienteSanduichePedidoDTO.setValorIngrediente(ingredienteEntity.getValorIngrediente());

		return ingredienteSanduichePedidoDTO;
	}

	/**
	 * Cria lista ingrediente sanduiche pedido DTO.
	 *
	 * @param qtdHamburguer a quantidade hamburguer
	 * @param qtdQueijo a quantidade queijo
	 * @param qtdBacon a quantidade bacon
	 * @param qtdOvo a quantidade ovo
	 * @param qtdAlface a quantidade alface
	 * @param dominioIngredientes the dominio ingredientes
	 * @return the list
	 */
	public static List<IngredienteSanduichePedidoDTO> criaListaIngredienteSanduichePedidoDTO(
		final Integer qtdHamburguer,
		final Integer qtdQueijo,
		final Integer qtdBacon,
		final Integer qtdOvo,
		final Integer qtdAlface,
		final Map<Integer, IngredienteEntity> dominioIngredientes) {

		final List<IngredienteSanduichePedidoDTO> lista = new ArrayList<>();

		if (!ZERO.equals(qtdHamburguer)) {
			lista.add(criaIngredienteSanduichePedidoDTO(
				obtemIngrediente(IngredienteEnum.HAMBURGUER, dominioIngredientes), qtdHamburguer));
		}
		if (!ZERO.equals(qtdQueijo)) {
			lista.add(criaIngredienteSanduichePedidoDTO(
				obtemIngrediente(IngredienteEnum.QUEIJO, dominioIngredientes), qtdQueijo));
		}
		if (!ZERO.equals(qtdBacon)) {
			lista.add(criaIngredienteSanduichePedidoDTO(
				obtemIngrediente(IngredienteEnum.BACON, dominioIngredientes), qtdBacon));
		}
		if (!ZERO.equals(qtdOvo)) {
			lista.add(criaIngredienteSanduichePedidoDTO(
				obtemIngrediente(IngredienteEnum.OVO, dominioIngredientes), qtdOvo));
		}
		if (!ZERO.equals(qtdAlface)) {
			lista.add(criaIngredienteSanduichePedidoDTO(
				obtemIngrediente(IngredienteEnum.ALFACE, dominioIngredientes), qtdAlface));
		}

		return lista;
	}

	/**
	 * Obtem um ingrediente do mapa de dominio de ingredientes.
	 *
	 * @param ingrediente o ingrediente Enum
	 * @param dominioIngredientes o dominio ingredientes
	 * @return o ingrediente entity
	 */
	public static IngredienteEntity obtemIngrediente(final IngredienteEnum ingrediente, final Map<Integer, IngredienteEntity> dominioIngredientes) {

		return dominioIngredientes.get(ingrediente.getCodigo());

	}

	/**
	 * Cria um sanduiche pedido DTO, com o codigo do sanduiche e a quantidade
	 * dos ingredientes.
	 *
	 * @param codigoSanduiche o codigo do sanduiche
	 * @param qtdHamburguer a quantidade hamburguer
	 * @param qtdQueijo a quantidade queijo
	 * @param qtdBacon a quantidade bacon
	 * @param qtdOvo a quantidade ovo
	 * @param qtdAlface a quantidade alface
	 * @param dominioIngredientes
	 * @return o sanduiche pedido DTO
	 */
	public static SanduichePedidoDTO criaSanduichePedidoDTO(final Integer codigoSanduiche,
		final Integer qtdHamburguer,
		final Integer qtdQueijo,
		final Integer qtdBacon,
		final Integer qtdOvo,
		final Integer qtdAlface,
		final Map<Integer, IngredienteEntity> dominioIngredientes) {

		final SanduichePedidoDTO sanduichePedidoDTO = new SanduichePedidoDTO();
		final List<IngredienteSanduichePedidoDTO> itensIngredientes = criaListaIngredienteSanduichePedidoDTO(
			qtdHamburguer,
			qtdQueijo,
			qtdBacon,
			qtdOvo,
			qtdAlface,
			dominioIngredientes);

		sanduichePedidoDTO.setIngredientes(itensIngredientes);

		sanduichePedidoDTO.setCodigoSanduiche(codigoSanduiche);
		sanduichePedidoDTO.setNomeSanduiche("X-BACON");

		BigDecimal valorSanduiche = BigDecimal.ZERO;
		for (final IngredienteSanduichePedidoDTO ingrediente : itensIngredientes) {
			valorSanduiche = valorSanduiche.add(ingrediente.getValorIngrediente()
				.multiply(new BigDecimal(ingrediente.getQuantidadeIngrediente())));
		}

		sanduichePedidoDTO.setValorSanduicheSemDesconto(valorSanduiche);

		return sanduichePedidoDTO;

	}

	/**
	 * Cria um pedido para testes.
	 *
	 * @return um pedido entity
	 */
	public static PedidoDTO criaPedidoDTO(final Map<Integer, IngredienteEntity> dominioIngredientes) {

		final PedidoDTO pedido = new PedidoDTO();

		pedido.setCpf("35087542867");
		pedido.setNomeCliente("Daniel Sanches");
		pedido.setEndereco("Rua 11 de Abril, 123");
		pedido.setTelefone("19999995555");

		final List<SanduichePedidoDTO> listaSanduiches = new ArrayList<>();
		listaSanduiches.add(criaSanduichePedidoDTO(1, 1, 1, 1, 0, 0, dominioIngredientes));
		listaSanduiches.add(criaSanduichePedidoDTO(2, 1, 1, 0, 0, 0, dominioIngredientes));

		pedido.setSanduiches(listaSanduiches);

		return pedido;
	}

	/**
	 * Calcula os valores totais do pedido.
	 *
	 * @param pedidoDTO o pedido DTO
	 * @param listaSanduiches a lista de sanduiches
	 * @return o pedido DTO com os valores calculados
	 */
	public static PedidoDTO calculaValoresPedido(final PedidoDTO pedidoDTO, final List<SanduichePedidoDTO> listaSanduiches) {

		BigDecimal valorPedidoComDesconto = BigDecimal.ZERO;
		BigDecimal valorPedidoDesconto = BigDecimal.ZERO;
		BigDecimal valorPedidoSemDesconto = BigDecimal.ZERO;

		final PedidoDTO pedidoCalculado = pedidoDTO;

		for (final SanduichePedidoDTO sanduiche : listaSanduiches) {

			valorPedidoComDesconto = valorPedidoComDesconto.add(sanduiche.getValorSanduicheComDesconto());
			valorPedidoDesconto = valorPedidoDesconto.add(sanduiche.getValorDescontoTotal());
			valorPedidoSemDesconto = valorPedidoSemDesconto.add(sanduiche.getValorSanduicheSemDesconto());
		}

		pedidoCalculado.setValorPedidoComDesconto(valorPedidoComDesconto);
		pedidoCalculado.setValorPedidoDesconto(valorPedidoDesconto);
		pedidoCalculado.setValorPedidoSemDesconto(valorPedidoSemDesconto);

		return pedidoCalculado;

	}

	public static SanduichePedidoEntity populaItemPedido() {
		final SanduichePedidoEntity itemPedido = new SanduichePedidoEntity();

		itemPedido.setCodigoSanduiche(1);
		itemPedido.setPromoCarne(PromocaoAtiva.SIM.getCodigo());
		itemPedido.setPromoLight(PromocaoAtiva.NAO.getCodigo());
		itemPedido.setPromoQueijo(PromocaoAtiva.NAO.getCodigo());
		itemPedido.setValorSanduicheComDesconto(new BigDecimal("12.50"));
		itemPedido.setValorDescontoTotal(new BigDecimal("3.00"));
		itemPedido.setValorSanduicheSemDesconto(new BigDecimal("15.50"));
		return itemPedido;
	}

	public static IngredienteSanduichePedidoEntity populaItemPedidoIngrediente(final IngredienteEnum ingredienteEnum,
		final Integer qtdIngrediente,
		final Map<Integer, IngredienteEntity> dominioIngredientes) {

		final IngredienteSanduichePedidoEntity itemIngrediente = new IngredienteSanduichePedidoEntity();

		final IngredienteEntity ingrediente = obtemIngrediente(ingredienteEnum, dominioIngredientes);

		itemIngrediente.setNomeIngrediente(ingrediente.getNomeIngrediente());
		itemIngrediente.setCodigoIngrediente(ingrediente.getCodigoIngrediente());
		itemIngrediente.setQuantidadeIngrediente(qtdIngrediente);
		itemIngrediente.setValorIngrediente(ingrediente.getValorIngrediente());

		return itemIngrediente;
	}

}
