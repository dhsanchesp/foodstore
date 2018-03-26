package com.foodstore.app.service.test;

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

import com.foodstore.app.dto.SanduicheDTO;
import com.foodstore.app.enums.SanduicheEnum;
import com.foodstore.app.service.ISanduicheService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class SanduicheServiceTest {

    @Autowired
    private ISanduicheService service;

    /**
     * Testa a consulta de todos os sanduiches cadstrados
     */
    @Test
    public void testBuscarTodosSanduiches() {

        final List<SanduicheDTO> lista = this.service.buscarSanduiches();

        assertNotNull(lista);
        assertTrue(4 == lista.size());
    }

    /**
     * Testa a consulta de sanduiches, consultando pelo sanduiche X-BACON
     */
    @Test
    public void testBuscarXBacon() {

        final SanduicheDTO sanduiche = this.service.buscarSanduichePorCodigo(SanduicheEnum.X_BACON.getCodigo());

        assertNotNull(sanduiche);
        assertTrue(SanduicheEnum.X_BACON.getCodigo().equals(sanduiche.getCodigoSanduiche()));
        assertTrue(SanduicheEnum.X_BACON.getNomeSanduiche().equals(sanduiche.getNomeSanduiche()));
        assertTrue(SanduicheEnum.X_BACON.getDescricao().equals(sanduiche.getDescricaoSanduiche()));
    }

    /**
     * Testa a consulta de sanduiches, consultando pelo sanduiche X-BACON
     */
    @Test
    public void testBuscarXEgg() {

        final SanduicheDTO sanduiche = this.service.buscarSanduichePorCodigo(SanduicheEnum.X_EGG.getCodigo());

        assertNotNull(sanduiche);
        assertTrue(SanduicheEnum.X_EGG.getCodigo().equals(sanduiche.getCodigoSanduiche()));
        assertTrue(SanduicheEnum.X_EGG.getNomeSanduiche().equals(sanduiche.getNomeSanduiche()));
        assertTrue(SanduicheEnum.X_EGG.getDescricao().equals(sanduiche.getDescricaoSanduiche()));
    }

    /**
     * Testa a consulta de um sanduiche não cadastrado
     */
    @Test(expected = NoResultException.class)
    public void testSanduicheNaoEncontrado() {

        final SanduicheDTO sanduiche = this.service.buscarSanduichePorCodigo(8);

    }
}
