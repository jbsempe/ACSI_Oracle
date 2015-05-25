/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicolasthy
 */
@Entity
@Table(name = "CONSULTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulter.findAll", query = "SELECT c FROM Consulter c"),
    @NamedQuery(name = "Consulter.findByDatedebutvisite", query = "SELECT c FROM Consulter c WHERE c.datedebutvisite = :datedebutvisite"),
    @NamedQuery(name = "Consulter.findByDatefinvisite", query = "SELECT c FROM Consulter c WHERE c.datefinvisite = :datefinvisite"),
    @NamedQuery(name = "Consulter.findByArId", query = "SELECT c FROM Consulter c WHERE c.arId = :arId")})
public class Consulter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DATEDEBUTVISITE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedebutvisite;
    @Column(name = "DATEFINVISITE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datefinvisite;
    @JoinColumn(name = "AR_ID", referencedColumnName = "AR_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Article arId;
    @JoinColumn(name = "UT_ID", referencedColumnName = "UT_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utId;

    public Consulter() {
    }

    public Consulter(Date datedebutvisite) {
        this.datedebutvisite = datedebutvisite;
    }

    public Date getDatedebutvisite() {
        return datedebutvisite;
    }

    public void setDatedebutvisite(Date datedebutvisite) {
        this.datedebutvisite = datedebutvisite;
    }

    public Date getDatefinvisite() {
        return datefinvisite;
    }

    public void setDatefinvisite(Date datefinvisite) {
        this.datefinvisite = datefinvisite;
    }

    public Article getArId() {
        return arId;
    }

    public void setArId(Article arId) {
        this.arId = arId;
    }

    public Utilisateur getUtId() {
        return utId;
    }

    public void setUtId(Utilisateur utId) {
        this.utId = utId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datedebutvisite != null ? datedebutvisite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulter)) {
            return false;
        }
        Consulter other = (Consulter) object;
        if ((this.datedebutvisite == null && other.datedebutvisite != null) || (this.datedebutvisite != null && !this.datedebutvisite.equals(other.datedebutvisite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Consulter[ datedebutvisite=" + datedebutvisite + " ]";
    }
    
}
