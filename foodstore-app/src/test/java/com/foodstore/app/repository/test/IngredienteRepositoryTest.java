package com.foodstore.app.repository.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.dao.IIngredienteDAO;
import com.foodstore.app.entity.IngredienteEntity;

/**
 * Teste da camada DAO
 *
 * @author dhsanchesp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class IngredienteRepositoryTest {

    @Autowired
    IIngredienteDAO ingredienteDAO;

    /**
     * Teste da consulta de todos os ingredientes
     */
    @Test
    public void testBuscarTodosIngredientes() {

        final List<IngredienteEntity> lista = this.ingredienteDAO.buscarTodosIngredientes();

        assertNotNull(lista);
        assertTrue(5 == lista.size());

    }

    /**
     * Teste da consulta de um ingrediente por codigo
     */
    @Test
    public void testBuscarIngredientePorId() {

        String nomeIngrediente = "HAMBURGUER DE CARNE";

        final IngredienteEntity ingrediente = this.ingredienteDAO.buscarIngredientePorCodigo(1);

        assertNotNull(ingrediente);
        assertTrue(nomeIngrediente.equals(ingrediente.getNomeIngrediente()));

    }

    /**
     * Teste da consulta de um ingrediente com um id inexistente
     */
    @Test(expected = NoResultException.class)
    public void testBuscarIngredientePorIdInvalido() {

        final IngredienteEntity ingrediente = this.ingredienteDAO.buscarIngredientePorCodigo(6);

    }

    /**
     * Teste da consulta de um ingrediente com um id inexistente
     */
    @Test
    public void testAtualizaIngrediente() {

        String novoNome = "HAMBURGUER DE SOJA";
        BigDecimal novoValor = new BigDecimal("4.25");

        IngredienteEntity ingrediente = new IngredienteEntity();
        ingrediente.setCodigoIngrediente(1);
        ingrediente.setValorIngrediente(novoValor);
        ingrediente.setNomeIngrediente(novoNome);

        final IngredienteEntity ingredienteAtualizado = this.ingredienteDAO.atualizarIngrediente(ingrediente);

        assertNotNull(ingredienteAtualizado);
        assertTrue(novoNome.equals(ingredienteAtualizado.getNomeIngrediente()));
        assertTrue(novoValor.equals(ingredienteAtualizado.getValorIngrediente()));

    }

}
