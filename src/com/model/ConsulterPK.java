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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nicolasthy
 */
@Embeddable
public class ConsulterPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "UT_ID")
    private long utId;
    @Basic(optional = false)
    @Column(name = "AR_ID")
    private long arId;
    @Basic(optional = false)
    @Column(name = "CA_DATE")
    @Temporal(TemporalType.DATE)
    private Date caDate;

    public ConsulterPK() {
    }

    public ConsulterPK(long utId, long arId, Date caDate) {
        this.utId = utId;
        this.arId = arId;
        this.caDate = caDate;
    }

    public long getUtId() {
        return utId;
    }

    public void setUtId(long utId) {
        this.utId = utId;
    }

    public long getArId() {
        return arId;
    }

    public void setArId(long arId) {
        this.arId = arId;
    }

    public Date getCaDate() {
        return caDate;
    }

    public void setCaDate(Date caDate) {
        this.caDate = caDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) utId;
        hash += (int) arId;
        hash += (caDate != null ? caDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsulterPK)) {
            return false;
        }
        ConsulterPK other = (ConsulterPK) object;
        if (this.utId != other.utId) {
            return false;
        }
        if (this.arId != other.arId) {
            return false;
        }
        if ((this.caDate == null && other.caDate != null) || (this.caDate != null && !this.caDate.equals(other.caDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.ConsulterPK[ utId=" + utId + ", arId=" + arId + ", caDate=" + caDate + " ]";
    }
    
}
