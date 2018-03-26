package com.foodstore.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe DTO de Ingrediente
 *
 * @author dhsanchesp
 */
public class IngredienteSanduichePedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoIngrediente;

	private Integer quantidadeIngrediente;

	private String nomeIngrediente;

	private BigDecimal valorIngrediente;

	public Integer getCodigoIngrediente() {
		return this.codigoIngrediente;
	}

	public void setCodigoIngrediente(final Integer codigoIngrediente) {
		this.codigoIngrediente = codigoIngrediente;
	}

	public String getNomeIngrediente() {
		return this.nomeIngrediente;
	}

	public void setNomeIngrediente(final String nomeIngrediente) {
		this.nomeIngrediente = nomeIngrediente;
	}

	public BigDecimal getValorIngrediente() {
		return this.valorIngrediente;
	}

	public void setValorIngrediente(final BigDecimal valorIngrediente) {
		this.valorIngrediente = valorIngrediente;
	}

	/**
	 * Atribui um valor default de 1 para a quantidade de ingrediente, caso ele
	 * esteja nulo.
	 *
	 * @return a quantidade do ingrediente
	 */
	public Integer getQuantidadeIngrediente() {

		if (this.quantidadeIngrediente == null) {
			return this.quantidadeIngrediente = 1;
		}
		return this.quantidadeIngrediente;
	}

	public void setQuantidadeIngrediente(final Integer quantidadeIngrediente) {
		this.quantidadeIngrediente = quantidadeIngrediente;
	}

}
