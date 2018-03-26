package com.foodstore.app.repository.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.dao.ISanduicheDAO;
import com.foodstore.app.entity.SanduicheEntity;
/**
 * Teste da camada DAO
 *
 * @author dhsanchesp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class SanduicheRepositoryTest {

    @Autowired
    ISanduicheDAO sanduicheDAO;

    /**
     * Teste da consulta de todos os sanduiches.
     */
    @Test
    public void testBuscarTodosSanduiches() {

        final List<SanduicheEntity> lista = this.sanduicheDAO.buscarTodosSanduiches();

        assertNotNull(lista);
        assertTrue(4 == lista.size());

    }

    /**
     * Teste da consulta de sanduiche por id.
     */
    @Test
    public void testBuscarSanduichePorId() {

        String nome = "X-BACON";
        final SanduicheEntity sanduiche = this.sanduicheDAO.buscarSanduichePorCodigo(1);

        assertNotNull(sanduiche);
        assertTrue(nome.equals(sanduiche.getNomeSanduiche()));

    }

    /**
     * Teste da consulta de sanduiche por um id invalido.
     */
    @Test(expected = NoResultException.class)
    public void testBuscarSanduichePorIdInvalido() {

        final SanduicheEntity sanduiche = this.sanduicheDAO.buscarSanduichePorCodigo(9);

    }

}
