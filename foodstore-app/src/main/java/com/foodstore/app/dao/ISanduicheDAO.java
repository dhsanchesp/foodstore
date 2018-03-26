package com.foodstore.app.dao;

import java.util.List;

import com.foodstore.app.entity.SanduicheEntity;

/**
 * Classe de interface para os dados do tipo SanduicheEntity.
 *
 * @author dhsanchesp
 */
public interface ISanduicheDAO {

	/**
	 * Consulta que retorna todos os sanduíches cadastrados na base de dados.
	 *
	 * @return lista de sanduíches
	 */
	List<SanduicheEntity> buscarTodosSanduiches();

	/**
	 * Buscar sanduiche por codigo.
	 *
	 * @param codigoSanduiche o codigo do sanduiche
	 * @return o sanduiche
	 */
	SanduicheEntity buscarSanduichePorCodigo(Integer codigoSanduiche);

}
