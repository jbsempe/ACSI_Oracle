/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicolasthy
 */
@Entity
@Table(name = "STATISTIQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statistique.findAll", query = "SELECT s FROM Statistique s"),
    @NamedQuery(name = "Statistique.findByStId", query = "SELECT s FROM Statistique s WHERE s.stId = :stId"),
    @NamedQuery(name = "Statistique.findByStNbcree", query = "SELECT s FROM Statistique s WHERE s.stNbcree = :stNbcree"),
    @NamedQuery(name = "Statistique.findByStNbsuppr", query = "SELECT s FROM Statistique s WHERE s.stNbsuppr = :stNbsuppr"),
    @NamedQuery(name = "Statistique.findByStNbmodif", query = "SELECT s FROM Statistique s WHERE s.stNbmodif = :stNbmodif")})
public class Statistique implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ST_ID")
    private BigDecimal stId;
    @Column(name = "ST_NBCREE")
    private BigInteger stNbcree;
    @Column(name = "ST_NBSUPPR")
    private BigInteger stNbsuppr;
    @Column(name = "ST_NBMODIF")
    private BigInteger stNbmodif;
    @JoinColumn(name = "AR_ID", referencedColumnName = "AR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Article arId;

    public Statistique() {
    }

    public Statistique(BigDecimal stId) {
        this.stId = stId;
    }

    public BigDecimal getStId() {
        return stId;
    }

    public void setStId(BigDecimal stId) {
        this.stId = stId;
    }

    public BigInteger getStNbcree() {
        return stNbcree;
    }

    public void setStNbcree(BigInteger stNbcree) {
        this.stNbcree = stNbcree;
    }

    public BigInteger getStNbsuppr() {
        return stNbsuppr;
    }

    public void setStNbsuppr(BigInteger stNbsuppr) {
        this.stNbsuppr = stNbsuppr;
    }

    public BigInteger getStNbmodif() {
        return stNbmodif;
    }

    public void setStNbmodif(BigInteger stNbmodif) {
        this.stNbmodif = stNbmodif;
    }

    public Article getArId() {
        return arId;
    }

    public void setArId(Article arId) {
        this.arId = arId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stId != null ? stId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statistique)) {
            return false;
        }
        Statistique other = (Statistique) object;
        if ((this.stId == null && other.stId != null) || (this.stId != null && !this.stId.equals(other.stId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Statistique[ stId=" + stId + " ]";
    }
    
}
