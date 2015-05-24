/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "CONSULTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulter.findAll", query = "SELECT c FROM Consulter c"),
    @NamedQuery(name = "Consulter.findByUtId", query = "SELECT c FROM Consulter c WHERE c.consulterPK.utId = :utId"),
    @NamedQuery(name = "Consulter.findByArId", query = "SELECT c FROM Consulter c WHERE c.consulterPK.arId = :arId"),
    @NamedQuery(name = "Consulter.findByCaDate", query = "SELECT c FROM Consulter c WHERE c.consulterPK.caDate = :caDate")})
public class Consulter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsulterPK consulterPK;
    @JoinColumn(name = "AR_ID", referencedColumnName = "AR_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Article article;
    @JoinColumn(name = "UT_ID", referencedColumnName = "UT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public Consulter() {
    }

    public Consulter(ConsulterPK consulterPK) {
        this.consulterPK = consulterPK;
    }

    public Consulter(long utId, long arId, Date caDate) {
        this.consulterPK = new ConsulterPK(utId, arId, caDate);
    }

    public ConsulterPK getConsulterPK() {
        return consulterPK;
    }

    public void setConsulterPK(ConsulterPK consulterPK) {
        this.consulterPK = consulterPK;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consulterPK != null ? consulterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulter)) {
            return false;
        }
        Consulter other = (Consulter) object;
        if ((this.consulterPK == null && other.consulterPK != null) || (this.consulterPK != null && !this.consulterPK.equals(other.consulterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Consulter[ consulterPK=" + consulterPK + " ]";
    }
    
}
