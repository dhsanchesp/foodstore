package com.foodstore.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SanduichePedidoDTO implements Serializable {

	private static final long serialVersionUID = -4721049148413830162L;

	private Long codigoPedido;

	private Long codigoItemPedido;

	private Integer codigoSanduiche;

	private String nomeSanduiche;

	private String descricaoSanduiche;

	private BigDecimal valorSanduicheSemDesconto;

	private BigDecimal valorSanduicheComDesconto;

	private BigDecimal valorDescontoTotal;

	private BigDecimal valorDescontoPromoCarne;

	private BigDecimal valorDescontoPromoQueijo;

	private BigDecimal valorDescontoPromoLight;

	private Integer promoCarne;

	private Integer promoQueijo;

	private Integer promoLight;

	private List<IngredienteSanduichePedidoDTO> ingredientes = new ArrayList<>();

	public SanduichePedidoDTO() {

	}

	public Long getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(final Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public Long getCodigoItemPedido() {
		return this.codigoItemPedido;
	}

	public void setCodigoItemPedido(final Long codigoItemPedido) {
		this.codigoItemPedido = codigoItemPedido;
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

	public List<IngredienteSanduichePedidoDTO> getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(final List<IngredienteSanduichePedidoDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
