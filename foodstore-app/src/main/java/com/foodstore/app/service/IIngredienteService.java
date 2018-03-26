package com.foodstore.app.service;

import java.util.List;

import com.foodstore.app.dto.IngredienteDTO;

/**
 * A classe de interface dos serviços de Ingrediente.
 *
 * @author dhsanchesp
 */
public interface IIngredienteService {

	/**
	 * Obtém uma lista de todos os ingredientes cadastrados no sistema.
	 *
	 * @return a lista de ingredientes
	 */
	List<IngredienteDTO> buscarTodosIngredientes();

	/**
	 * Busca um ingrediente por codigo do ingrediente.
	 *
	 * @param codigoIngrediente o codigo do ingrediente
	 * @return um ingrediente
	 */
	IngredienteDTO buscarIngredientePorCodigo(Integer codigoIngrediente);

	/**
	 * Atualiza as informações de um ingrediente.
	 *
	 * @param ingredienteDTO the ingrediente DTO
	 * @return o ingrediente atualizado
	 */
	IngredienteDTO atualizarIngrediente(IngredienteDTO ingredienteDTO);
}
