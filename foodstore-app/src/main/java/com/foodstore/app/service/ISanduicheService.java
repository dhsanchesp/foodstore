package com.foodstore.app.service;

import java.util.List;

import com.foodstore.app.dto.SanduicheDTO;

/**
 * A classe de interface dos serviços de Sanduiche.
 *
 * @author dhsanchesp
 */
public interface ISanduicheService {

	/**
	 * Obtém uma lista de todos os sanduiches cadastrados no bando de dados.
	 *
	 * @return lista de sanduiches cadastrados
	 */
	List<SanduicheDTO> buscarSanduiches();

	/**
	 * Buscar sanduiche por codigo.
	 *
	 * @param codigoSanduiche o codigo do sanduiche
	 * @return o sanduiche
	 */
	SanduicheDTO buscarSanduichePorCodigo(Integer codigoSanduiche);

}
