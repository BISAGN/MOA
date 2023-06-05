/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AyushEdu.Models.Counselling_Institute;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rdp
 */
@Entity
@Table(name = "co_commissiontype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoCommissiontype.findAll", query = "SELECT c FROM CoCommissiontype c"),
    @NamedQuery(name = "CoCommissiontype.findByComid", query = "SELECT c FROM CoCommissiontype c WHERE c.comid = :comid"),
    @NamedQuery(name = "CoCommissiontype.findByComname", query = "SELECT c FROM CoCommissiontype c WHERE c.comname = :comname"),
    @NamedQuery(name = "CoCommissiontype.findByStatus", query = "SELECT c FROM CoCommissiontype c WHERE c.status = :status"),
    @NamedQuery(name = "CoCommissiontype.findByCreateby", query = "SELECT c FROM CoCommissiontype c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoCommissiontype.findByCreatedate", query = "SELECT c FROM CoCommissiontype c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CoCommissiontype.findByModifyby", query = "SELECT c FROM CoCommissiontype c WHERE c.modifyby = :modifyby"),
    @NamedQuery(name = "CoCommissiontype.findByModifydate", query = "SELECT c FROM CoCommissiontype c WHERE c.modifydate = :modifydate")})
public class CoCommissiontype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comid")
    private Integer comid;
    @Basic(optional = false)
    @Column(name = "comname")
    private String comname;
    @Basic(optional = false)
    @Column(name = "status")
    private Character status;
    @Basic(optional = false)
    @Column(name = "createby")
    private int createby;
    @Basic(optional = false)
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "modifyby")
    private Integer modifyby;
    @Column(name = "modifydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifydate;

    public CoCommissiontype() {
    }

    public CoCommissiontype(Integer comid) {
        this.comid = comid;
    }

    public CoCommissiontype(Integer comid, String comname, Character status, int createby, Date createdate) {
        this.comid = comid;
        this.comname = comname;
        this.status = status;
        this.createby = createby;
        this.createdate = createdate;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public int getCreateby() {
        return createby;
    }

    public void setCreateby(int createby) {
        this.createby = createby;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getModifyby() {
        return modifyby;
    }

    public void setModifyby(Integer modifyby) {
        this.modifyby = modifyby;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comid != null ? comid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoCommissiontype)) {
            return false;
        }
        CoCommissiontype other = (CoCommissiontype) object;
        if ((this.comid == null && other.comid != null) || (this.comid != null && !this.comid.equals(other.comid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoCommissiontype[ comid=" + comid + " ]";
    }
    
}
