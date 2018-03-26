package com.foodstore.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SanduicheDTO implements Serializable {

	private static final long serialVersionUID = -4721049148413830162L;

	private Integer codigoSanduiche;

	private String nomeSanduiche;

	private String descricaoSanduiche;

	private BigDecimal valorSanduiche;

	private List<IngredienteDTO> ingredientes = new ArrayList<>();

	public SanduicheDTO() {

	}

	public SanduicheDTO(final Integer codigoSanduiche) {
		this.codigoSanduiche = codigoSanduiche;
	}

	public Integer getCodigoSanduiche() {
		return this.codigoSanduiche;
	}

	public void setCodigoSanduiche(final Integer codigoSanduiche) {
		this.codigoSanduiche = codigoSanduiche;
	}

	public String getNomeSanduiche() {
		return this.nomeSanduiche;
	}

	public void setNomeSanduiche(final String nomeSanduiche) {
		this.nomeSanduiche = nomeSanduiche;
	}

	public String getDescricaoSanduiche() {
		return this.descricaoSanduiche;
	}

	public void setDescricaoSanduiche(final String descricaoSanduiche) {
		this.descricaoSanduiche = descricaoSanduiche;
	}

	public BigDecimal getValorSanduiche() {
		return this.valorSanduiche;
	}

	public void setValorSanduiche(final BigDecimal valorSanduiche) {
		this.valorSanduiche = valorSanduiche;
	}

	public List<IngredienteDTO> getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(final List<IngredienteDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
