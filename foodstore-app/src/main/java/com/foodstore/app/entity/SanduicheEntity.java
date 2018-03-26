package com.foodstore.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "TB_DM_SANDUICHE")
public class SanduicheEntity implements Serializable {

    private static final long serialVersionUID = -4721049148413830162L;

    @Id
    @Column(name = "CD_SANDUICHE")
    private Integer codigoSanduiche;

    @Column(name = "NM_SANDUICHE")
    private String nomeSanduiche;

    @Column(name = "DS_SANDUICHE")
    private String descricaoSanduiche;

    @Column(name = "VR_SANDUICHE")
    private BigDecimal valorSanduiche;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "TB_SANDUICHE_INGREDIENTE", joinColumns = {
        @JoinColumn(name = "CD_SANDUICHE")}, inverseJoinColumns = {
            @JoinColumn(name = "CD_INGREDIENTE")})
    @OrderBy("codigoIngrediente")
    private List<IngredienteEntity> ingredientes = new ArrayList<>();

    public SanduicheEntity() {

    }

    public SanduicheEntity(final Integer codigoSanduiche) {
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

    public List<IngredienteEntity> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(final List<IngredienteEntity> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
