package com.foodstore.app.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.enums.IngredienteEnum;
import com.foodstore.app.service.IIngredienteService;

/**
 * Teste da camada Service de Ingrediente
 *
 * @author dhsanchesp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class IngredienteServiceTest {

    @Autowired
    private IIngredienteService service;

    private Map<Integer, IngredienteEntity> dominioIngredientes;

    @Before
    public void obtemDominioIngredientes() {

        final List<IngredienteDTO> ingredientesDTO = this.service.buscarTodosIngredientes();

        final List<IngredienteEntity> ingredientesDomain = DozerHelper.map(ingredientesDTO, IngredienteEntity.class);

        final Map<Integer, IngredienteEntity> dominioIngredientes = new HashMap<>();

        for (final IngredienteEntity ingredienteEntity : ingredientesDomain) {
            dominioIngredientes.put(ingredienteEntity.getCodigoIngrediente(), ingredienteEntity);
        }

        this.dominioIngredientes = dominioIngredientes;

    }

    @Test
    public void testBuscarTodosIngredientes() {

        final List<IngredienteDTO> lista = this.service.buscarTodosIngredientes();

        assertNotNull(lista);
        assertTrue(5 == lista.size());
    }

    @Test
    public void testBuscarHamburguer() {

        final IngredienteDTO ingrediente = this.service
                        .buscarIngredientePorCodigo(IngredienteEnum.HAMBURGUER.getCodigo());

        assertNotNull(ingrediente);
        assertTrue(IngredienteEnum.HAMBURGUER.getCodigo().equals(ingrediente.getCodigoIngrediente()));
        assertTrue(IngredienteEnum.HAMBURGUER.getNomeIngrediente().equals(ingrediente.getNomeIngrediente()));
    }

    @Test
    public void testBuscarOvo() {

        final IngredienteDTO ingrediente = this.service.buscarIngredientePorCodigo(IngredienteEnum.OVO.getCodigo());

        assertNotNull(ingrediente);
        assertTrue(IngredienteEnum.OVO.getCodigo().equals(ingrediente.getCodigoIngrediente()));
        assertTrue(IngredienteEnum.OVO.getNomeIngrediente().equals(ingrediente.getNomeIngrediente()));
    }

    @Test
    public void testAlterarPrecoENomeHamburguer() {

        BigDecimal novoValor = new BigDecimal("4.25");
        String novoNome = "Hambúrguer de Soja";

        final IngredienteDTO ingrediente = this.converteIngredienteEntityEmDTO(
                        this.dominioIngredientes.get(IngredienteEnum.HAMBURGUER.getCodigo()));

        ingrediente.setNomeIngrediente(novoNome);
        ingrediente.setValorIngrediente(novoValor);

        IngredienteDTO ingredienteAtualizado = this.service.atualizarIngrediente(ingrediente);

        assertNotNull(ingredienteAtualizado);
        assertTrue(ingredienteAtualizado.getNomeIngrediente().equals(novoNome));
        assertTrue(ingredienteAtualizado.getValorIngrediente().equals(novoValor));
    }

    /**
     * Converte um ingrediente entity em um ingrediente DTO.
     *
     * @param ingredienteEntity o ingrediente entity
     * @return o ingrediente DTO
     */
    private IngredienteDTO converteIngredienteEntityEmDTO(final IngredienteEntity ingredienteEntity) {

        IngredienteDTO ingredienteDTO = new IngredienteDTO();

        ingredienteDTO.setCodigoIngrediente(ingredienteEntity.getCodigoIngrediente());
        ingredienteDTO.setNomeIngrediente(ingredienteEntity.getNomeIngrediente());
        ingredienteDTO.setValorIngrediente(ingredienteDTO.getValorIngrediente());

        return ingredienteDTO;
    }
}
