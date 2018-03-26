package com.foodstore.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.dao.IPedidoDAO;
import com.foodstore.app.entity.IngredienteSanduichePedidoEntity;
import com.foodstore.app.entity.PedidoEntity;
import com.foodstore.app.entity.SanduichePedidoEntity;

@Transactional
@Repository
public class PedidoDAOImpl implements IPedidoDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(PedidoDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PedidoEntity consultarPedidoPorCodigo(final Long codigoPedido) {

		try {
			final String hql = "SELECT pedido FROM PedidoEntity as pedido WHERE pedido.codigoPedido = :codigoPedido";

			return (PedidoEntity) this.entityManager.createQuery(hql).setParameter("codigoPedido", codigoPedido).getSingleResult();

		} catch (final EmptyResultDataAccessException e) {
			LOGGER.error("Pedido não encontrado.", e);
			throw new NoResultException("Pedido " + codigoPedido + " não encontrado.");
		}

	}

	@Override
	public void incluirPedido(final PedidoEntity pedido) {

		this.entityManager.persist(pedido);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoEntity> consultarTodosPedidos() {

		final String hql = "SELECT pedido FROM PedidoEntity as pedido";

		return this.entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void incluirItemPedido(final SanduichePedidoEntity sanduichePedido) {

		this.entityManager.persist(sanduichePedido);
	}

	@Override
	public void incluirIngredienteItemPedido(final IngredienteSanduichePedidoEntity ingredienteSanduichePedido) {

		this.entityManager.persist(ingredienteSanduichePedido);
	}

}
