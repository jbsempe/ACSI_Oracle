/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.config.BatchWriting;

/**
 *
 * @author nicolasthy
 */
@Entity
@Table(name = "UTILISATEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByUtId", query = "SELECT u FROM Utilisateur u WHERE u.utId = :utId"),
    @NamedQuery(name = "Utilisateur.findByUtNom", query = "SELECT u FROM Utilisateur u WHERE u.utNom = :utNom"),
    @NamedQuery(name = "Utilisateur.findByUtPrenom", query = "SELECT u FROM Utilisateur u WHERE u.utPrenom = :utPrenom"),
    @NamedQuery(name = "Utilisateur.findByUtCp", query = "SELECT u FROM Utilisateur u WHERE u.utCp = :utCp"),
    @NamedQuery(name = "Utilisateur.findByUtPass", query = "SELECT u FROM Utilisateur u WHERE u.utPass = :utPass"),
    @NamedQuery(name = "Utilisateur.findByUtHash", query = "SELECT u FROM Utilisateur u WHERE u.utHash = :utHash"),
    //@NamedQuery(name = "Utilisateur.getLastId", query = "SELECT UT_ID FROM Utilisateur WHERE ROWNUM <=1 ORDER BY UT_ID DESC"),
    @NamedQuery(name = "Utilisateur.findByUtIsadmin", query = "SELECT u FROM Utilisateur u WHERE u.utIsadmin = :utIsadmin"),
    @NamedQuery(name = "Utilisateur.findByDate", query = "SELECT u FROM Utilisateur u WHERE u.utDateinscri = :utDateinscri")})
public class Utilisateur implements Serializable {
    @Column(name = "UT_DATEINSCRI")
    @Temporal(TemporalType.DATE)
    private Date utDateinscri;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "UT_ID")
    private Short utId;
    @Column(name = "UT_NOM")
    private String utNom;
    @Column(name = "UT_PRENOM")
    private String utPrenom;
    @Column(name = "UT_CP")
    private String utCp;
    @Column(name = "UT_PASS")
    private String utPass;
    @Column(name = "UT_HASH")
    private String utHash;
    @Column(name = "UT_ISADMIN")
    private Short utIsadmin;

    public Utilisateur() {
    }

    public Utilisateur(Short utId) {
        this.utId = utId;
    }

    public Short getUtId() {
        return utId;
    }

    public void setUtId(Short utId) {
        this.utId = utId;
    }

    public String getUtNom() {
        return utNom;
    }

    public void setUtNom(String utNom) {
        this.utNom = utNom;
    }

    public String getUtPrenom() {
        return utPrenom;
    }

    public void setUtPrenom(String utPrenom) {
        this.utPrenom = utPrenom;
    }
    
    public String getUtUsername(){
        String username = utPrenom.toLowerCase() + "." + utNom.toLowerCase();
        return username;
    }

    public String getUtCp() {
        return utCp;
    }

    public void setUtCp(String utCp) {
        this.utCp = utCp;
    }

    public String getUtPass() {
        return utPass;
    }

    public void setUtPass(String utPass) {
        this.utPass = utPass;
    }

    public String getUtHash() {
        return utHash;
    }

    public void setUtHash(String utHash) {
        this.utHash = utHash;
    }

    public Short getUtIsadmin() {
        return utIsadmin;
    }

    public void setUtIsadmin(Short utIsadmin) {
        this.utIsadmin = utIsadmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utId != null ? utId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.utId == null && other.utId != null) || (this.utId != null && !this.utId.equals(other.utId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Utilisateur[ utId=" + utId + " ]";
    }

    public Date getUtDateinscri() {
        return utDateinscri;
    }

    public void setUtDateinscri(Date utDateinscri) {
        this.utDateinscri = utDateinscri;
    }
    
    public static String ucfirst(String chaine){
        return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
    }
    
}
