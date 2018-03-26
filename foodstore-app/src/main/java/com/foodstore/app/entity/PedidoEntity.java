package com.foodstore.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TB_PEDIDO")
public class PedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SeqPedidoGenerator", sequenceName = "seq_tb_pedido", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqPedidoGenerator")
	@Column(name = "CD_PEDIDO", nullable = false)
	private Long codigoPedido;

	@Column(name = "NM_CLIENTE")
	private String nomeCliente;

	@Column(name = "NU_CPF")
	private String cpf;

	@Column(name = "END_CLIENTE")
	private String endereco;

	@Column(name = "NU_TELEFONE")
	private String telefone;

	@Column(name = "VR_TOTAL_PEDIDO")
	private BigDecimal valorPedidoSemDesconto;

	@Column(name = "VR_COM_DESCONTO")
	private BigDecimal valorPedidoComDesconto;

	@Column(name = "VR_TOTAL_DESCONTO")
	private BigDecimal valorPedidoDesconto;

	@OneToMany(targetEntity = SanduichePedidoEntity.class, fetch = FetchType.EAGER, mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SanduichePedidoEntity> sanduiches;

	public PedidoEntity() {

	}

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

	public List<SanduichePedidoEntity> getSanduiches() {
		return this.sanduiches;
	}

	public void setSanduiches(final List<SanduichePedidoEntity> sanduiches) {
		this.sanduiches = sanduiches;
	}

}
