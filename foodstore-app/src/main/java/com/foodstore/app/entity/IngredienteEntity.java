package com.foodstore.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade de domínio de Ingrediente
 *
 * @author dhsanchesp
 */
@Entity
@Table(name = "TB_DM_INGREDIENTE")
public class IngredienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_INGREDIENTE")
    private Integer codigoIngrediente;

    @Column(name = "NM_INGREDIENTE")
    private String nomeIngrediente;

    @Column(name = "VR_INGREDIENTE")
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

}
