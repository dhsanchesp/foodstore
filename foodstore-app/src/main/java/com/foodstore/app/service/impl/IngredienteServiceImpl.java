package com.foodstore.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.converter.DozerHelper;
import com.foodstore.app.dao.IIngredienteDAO;
import com.foodstore.app.dto.IngredienteDTO;
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.service.IIngredienteService;
import com.foodstore.app.service.conversor.Conversor;

@Service
public class IngredienteServiceImpl implements IIngredienteService {

    @Autowired
    private IIngredienteDAO ingredienteDAO;

    @Override
    @Transactional
    @Cacheable("ingredientes-dominio")
    public List<IngredienteDTO> buscarTodosIngredientes() {

        final List<IngredienteEntity> ingredientes = this.ingredienteDAO.buscarTodosIngredientes();

        return DozerHelper.map(ingredientes, IngredienteDTO.class);
    }

    @Override
    public IngredienteDTO buscarIngredientePorCodigo(final Integer codigoIngrediente) {

        final IngredienteEntity ingrediente = this.ingredienteDAO.buscarIngredientePorCodigo(codigoIngrediente);

        return DozerHelper.map(ingrediente, IngredienteDTO.class);
    }

    @Override
    @Transactional
    public IngredienteDTO atualizarIngrediente(final IngredienteDTO ingredienteDTO) {

        final IngredienteEntity ingredienteEntity = this.ingredienteDAO
                        .atualizarIngrediente(Conversor.converteIngredienteDTOEmEntity(ingredienteDTO));

        final IngredienteDTO ingredienteAtualizado = Conversor.converteIngredienteEntityEmDTO(ingredienteEntity);

        return ingredienteAtualizado;
    }

}
