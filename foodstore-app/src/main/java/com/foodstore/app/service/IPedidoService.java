package com.foodstore.app.service;

import java.util.List;

import com.foodstore.app.dto.PedidoDTO;
import com.foodstore.app.dto.SanduichePedidoDTO;

/**
 * A classe de interface do service de Pedidos.
 *
 * @author dhsanchesp
 */
public interface IPedidoService {

	/**
	 * Inclui um novo pedido.
	 *
	 * @param pedidoDTO o pedido DTO a ser incluído
	 * @return o pedido cadastrado
	 */
	PedidoDTO incluirPedido(PedidoDTO pedidoDTO);

	/**
	 * Consulta pedido por codigo do pedido.
	 *
	 * @param codigoPedido o codigo do pedido
	 * @return um pedido, se encontrado
	 */
	PedidoDTO consultarPedidoPorCodigo(Long codigoPedido);

	/**
	 * Busca todos os pedidos cadastrados.
	 *
	 * @return os pedidos
	 */
	List<PedidoDTO> consultarTodosPedidos();

	/**
	 * Calcula os valores do pedido, de acordo com os itens pedidos.
	 *
	 * @param pedidoDTO o pedido DTO
	 * @return o pedido DTO com os valores calculados
	 */
	PedidoDTO calculaValoresPedido(PedidoDTO pedidoDTO);

	/**
	 * Calcula o valor do sanduiche pedido, aplicando as regras de cálculo para
	 * as promoções vigentes.
	 *
	 * @param sanduichePedidoDTO o sanduiche pedido DTO
	 * @return o sanduiche pedido DTO com o valor calculado
	 */
	SanduichePedidoDTO calculaValorSanduichePedido(SanduichePedidoDTO sanduichePedidoDTO);
}
