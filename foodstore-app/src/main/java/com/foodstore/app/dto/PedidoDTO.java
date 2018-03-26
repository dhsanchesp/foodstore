package com.foodstore.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigoPedido;

	private String nomeCliente;

	private String cpf;

	private String endereco;

	private String telefone;

	private BigDecimal valorPedidoSemDesconto;

	private BigDecimal valorPedidoComDesconto;

	private BigDecimal valorPedidoDesconto;

	private List<SanduichePedidoDTO> sanduiches;

	public Long getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(final Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(final String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(final String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getValorPedidoSemDesconto() {
		return this.valorPedidoSemDesconto;
	}

	public void setValorPedidoSemDesconto(final BigDecimal valorPedidoSemDesconto) {
		this.valorPedidoSemDesconto = valorPedidoSemDesconto;
	}

	public BigDecimal getValorPedidoComDesconto() {
		return this.valorPedidoComDesconto;
	}

	public void setValorPedidoComDesconto(final BigDecimal valorPedidoComDesconto) {
		this.valorPedidoComDesconto = valorPedidoComDesconto;
	}

	public BigDecimal getValorPedidoDesconto() {
		return this.valorPedidoDesconto;
	}

	public void setValorPedidoDesconto(final BigDecimal valorPedidoDesconto) {
		this.valorPedidoDesconto = valorPedidoDesconto;
	}

	public List<SanduichePedidoDTO> getSanduiches() {
		return this.sanduiches;
	}

	public void setSanduiches(final List<SanduichePedidoDTO> sanduiches) {
		this.sanduiches = sanduiches;
	}

}
