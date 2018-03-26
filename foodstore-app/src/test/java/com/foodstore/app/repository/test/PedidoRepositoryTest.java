package com.foodstore.app.repository.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.dao.IPedidoDAO;
import com.foodstore.app.entity.PedidoEntity;

/**
 * Teste da camada DAO de Pedido
 *
 * @author dhsanchesp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class PedidoRepositoryTest {

    @Autowired
    IPedidoDAO pedidoDAO;

    /**
     * Teste de consulta de todos os pedidos cadastrados
     */
    @Test
    public void testBuscarTodosPedidos() {

        final List<PedidoEntity> lista = this.pedidoDAO.consultarTodosPedidos();

        assertNotNull(lista);
        assertTrue(1 == lista.size());

    }

    /**
     * Teste da consulta de pedido por ID
     */
    @Test
    public void testBuscarPedidoPorId() {

        final PedidoEntity pedido = this.pedidoDAO.consultarPedidoPorCodigo(9999L);

        assertNotNull(pedido);

    }

}
