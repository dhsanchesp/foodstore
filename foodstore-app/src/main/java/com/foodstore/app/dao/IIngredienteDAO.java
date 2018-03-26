package com.foodstore.app.dao;

import java.util.List;

import com.foodstore.app.entity.IngredienteEntity;

/**
 * Classe de interface para os dados do tipo IngredienteEntity.
 *
 * @author dhsanchesp
 */
public interface IIngredienteDAO {

	/**
	 * Consulta que retorna a lista de todos os ingredientes cadastrados na base
	 * de dados.
	 *
	 * @return lista de ingredientes
	 */
	List<IngredienteEntity> buscarTodosIngredientes();

	/**
	 * Busca um ingrediente por codigo do ingrediente.
	 *
	 * @param codigoIngrediente o codigo do ingrediente
	 * @return um ingrediente
	 */
	IngredienteEntity buscarIngredientePorCodigo(Integer codigoIngrediente);

	/**
	 * Atualiza as informações de um ingrediente.
	 *
	 * @param ingredienteEntity o ingrediente entity
	 * @return o ingrediente atualizado
	 */
	IngredienteEntity atualizarIngrediente(IngredienteEntity ingredienteEntity);

}
