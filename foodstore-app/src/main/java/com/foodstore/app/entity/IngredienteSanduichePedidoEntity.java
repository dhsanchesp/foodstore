package com.foodstore.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Classe DTO de Ingrediente
 *
 * @author dhsanchesp
 */
@Entity
@Table(name = "TB_ITEM_PEDIDO_INGREDIENTE")
public class IngredienteSanduichePedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SeqItemPedidoIngredienteGenerator", sequenceName = "seq_tb_item_pedido_ingrediente", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqItemPedidoIngredienteGenerator")
	@Column(name = "CD_ITEM_PEDIDO_INGREDIENTE", nullable = false)
	private Long codigoItemPedidoIngrediente;

	@Column(name = "CD_INGREDIENTE")
	private Integer codigoIngrediente;

	@Column(name = "QTD_INGREDIENTE")
	private Integer quantidadeIngrediente;

	@Column(name = "NM_INGREDIENTE")
	private String nomeIngrediente;

	@Column(name = "VR_INGREDIENTE")
	private BigDecimal valorIngrediente;

	@ManyToOne
	@JoinColumn(name = "CD_ITEM_PEDIDO", referencedColumnName = "CD_ITEM_PEDIDO")
	@JsonBackReference
	private SanduichePedidoEntity sanduiche;

	public IngredienteSanduichePedidoEntity() {

	}

	public Long getCodigoItemPedidoIngrediente() {
		return this.codigoItemPedidoIngrediente;
	}

	public void setCodigoItemPedidoIngrediente(final Long codigoItemPedidoIngrediente) {
		this.codigoItemPedidoIngrediente = codigoItemPedidoIngrediente;
	}

	public Integer getCodigoIngrediente() {
		return this.codigoIngrediente;
	}

	public void setCodigoIngrediente(final Integer codigoIngrediente) {
		this.codigoIngrediente = codigoIngrediente;
	}

	public Integer getQuantidadeIngrediente() {
		return this.quantidadeIngrediente;
	}

	public void setQuantidadeIngrediente(final Integer quantidadeIngrediente) {
		this.quantidadeIngrediente = quantidadeIngrediente;
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

	public SanduichePedidoEntity getSanduiche() {
		return this.sanduiche;
	}

	public void setSanduiche(final SanduichePedidoEntity sanduiche) {
		this.sanduiche = sanduiche;
	}

}
