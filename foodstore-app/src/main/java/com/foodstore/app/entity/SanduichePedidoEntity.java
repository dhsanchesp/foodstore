package com.foodstore.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class SanduichePedidoEntity implements Serializable {

	private static final long serialVersionUID = -4721049148413830162L;

	@Id
	@SequenceGenerator(name = "SeqItemPedidoGenerator", sequenceName = "seq_tb_item_pedido", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqItemPedidoGenerator")
	@Column(name = "CD_ITEM_PEDIDO")
	private Long codigoItemPedido;

	@ManyToOne
	@JoinColumn(name = "CD_PEDIDO", referencedColumnName = "CD_PEDIDO")
	@JsonBackReference
	private PedidoEntity pedidoEntity;

	@Column(name = "CD_SANDUICHE")
	private Integer codigoSanduiche;

	@Column(name = "NM_SANDUICHE")
	private String nomeSanduiche;

	@Column(name = "DS_SANDUICHE")
	private String descricaoSanduiche;

	@Column(name = "VR_SEM_DESCONTO")
	private BigDecimal valorSanduicheSemDesconto;

	@Column(name = "VR_COM_DESCONTO")
	private BigDecimal valorSanduicheComDesconto;

	@Column(name = "VR_DESCONTO_TOTAL")
	private BigDecimal valorDescontoTotal;

	@Column(name = "VR_DESCONTO_PROMO_CARNE")
	private BigDecimal valorDescontoPromoCarne;

	@Column(name = "VR_DESCONTO_PROMO_QUEIJO")
	private BigDecimal valorDescontoPromoQueijo;

	@Column(name = "VR_DESCONTO_PROMO_LIGHT")
	private BigDecimal valorDescontoPromoLight;

	@Column(name = "PROMO_CARNE")
	private Integer promoCarne;

	@Column(name = "PROMO_QUEIJO")
	private Integer promoQueijo;

	@Column(name = "PROMO_LIGHT")
	private Integer promoLight;

	@OneToMany(targetEntity = IngredienteSanduichePedidoEntity.class,
		mappedBy = "sanduiche", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<IngredienteSanduichePedidoEntity> ingredientes = new ArrayList<>();

	public SanduichePedidoEntity() {

	}

	public Long getCodigoItemPedido() {
		return this.codigoItemPedido;
	}

	public void setCodigoItemPedido(final Long codigoItemPedido) {
		this.codigoItemPedido = codigoItemPedido;
	}

	public PedidoEntity getPedidoEntity() {
		return this.pedidoEntity;
	}

	public void setPedidoEntity(final PedidoEntity pedidoEntity) {
		this.pedidoEntity = pedidoEntity;
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

	public BigDecimal getValorSanduicheSemDesconto() {
		return this.valorSanduicheSemDesconto;
	}

	public void setValorSanduicheSemDesconto(final BigDecimal valorSanduicheSemDesconto) {
		this.valorSanduicheSemDesconto = valorSanduicheSemDesconto;
	}

	public BigDecimal getValorSanduicheComDesconto() {
		return this.valorSanduicheComDesconto;
	}

	public void setValorSanduicheComDesconto(final BigDecimal valorSanduicheComDesconto) {
		this.valorSanduicheComDesconto = valorSanduicheComDesconto;
	}

	public BigDecimal getValorDescontoTotal() {
		return this.valorDescontoTotal;
	}

	public void setValorDescontoTotal(final BigDecimal valorDescontoTotal) {
		this.valorDescontoTotal = valorDescontoTotal;
	}

	public BigDecimal getValorDescontoPromoCarne() {
		return this.valorDescontoPromoCarne;
	}

	public void setValorDescontoPromoCarne(final BigDecimal valorDescontoPromoCarne) {
		this.valorDescontoPromoCarne = valorDescontoPromoCarne;
	}

	public BigDecimal getValorDescontoPromoQueijo() {
		return this.valorDescontoPromoQueijo;
	}

	public void setValorDescontoPromoQueijo(final BigDecimal valorDescontoPromoQueijo) {
		this.valorDescontoPromoQueijo = valorDescontoPromoQueijo;
	}

	public BigDecimal getValorDescontoPromoLight() {
		return this.valorDescontoPromoLight;
	}

	public void setValorDescontoPromoLight(final BigDecimal valorDescontoPromoLight) {
		this.valorDescontoPromoLight = valorDescontoPromoLight;
	}

	public Integer getPromoCarne() {
		return this.promoCarne;
	}

	public void setPromoCarne(final Integer promoCarne) {
		this.promoCarne = promoCarne;
	}

	public Integer getPromoQueijo() {
		return this.promoQueijo;
	}

	public void setPromoQueijo(final Integer promoQueijo) {
		this.promoQueijo = promoQueijo;
	}

	public Integer getPromoLight() {
		return this.promoLight;
	}

	public void setPromoLight(final Integer promoLight) {
		this.promoLight = promoLight;
	}

	public List<IngredienteSanduichePedidoEntity> getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(final List<IngredienteSanduichePedidoEntity> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
