package com.foodstore.app.dao;

import java.util.List;

import com.foodstore.app.entity.IngredienteSanduichePedidoEntity;
import com.foodstore.app.entity.PedidoEntity;
import com.foodstore.app.entity.SanduichePedidoEntity;

/**
 * Classe de interface para os dados do tipo PedidoEntity.
 *
 * @author dhsanchesp
 */
public interface IPedidoDAO {

	/**
	 * Consulta um pedido por codigo do pedido.
	 *
	 * @param codigoPedido o codigo do pedido
	 * @return um pedido
	 */
	PedidoEntity consultarPedidoPorCodigo(Long codigoPedido);

	/**
	 * Consultar todos os pedidos.
	 *
	 * @return os pedidos cadastrados
	 */
	List<PedidoEntity> consultarTodosPedidos();

	/**
	 * Incluir um novo pedido.
	 *
	 * @param pedido o novo pedido
	 */
	void incluirPedido(PedidoEntity pedido);

	/**
	 * Incluir um novo sanduiche pedido.
	 *
	 * @param sanduichePedido the sanduiche pedido
	 */
	void incluirItemPedido(SanduichePedidoEntity sanduichePedido);

	/**
	 * Incluir um novo ingrediente sanduiche pedido.
	 *
	 * @param ingredienteSanduichePedido the ingrediente sanduiche pedido
	 */
	void incluirIngredienteItemPedido(IngredienteSanduichePedidoEntity ingredienteSanduichePedido);

}
