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
 * @author bisag-n
 */
@Entity
@Table(name = "co_feescategorytype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoFeescategorytype.findAll", query = "SELECT c FROM CoFeescategorytype c"),
    @NamedQuery(name = "CoFeescategorytype.findByFtid", query = "SELECT c FROM CoFeescategorytype c WHERE c.ftid = :ftid"),
    @NamedQuery(name = "CoFeescategorytype.findByFeestype", query = "SELECT c FROM CoFeescategorytype c WHERE c.feestype = :feestype"),
    @NamedQuery(name = "CoFeescategorytype.findByStatus", query = "SELECT c FROM CoFeescategorytype c WHERE c.status = :status"),
    @NamedQuery(name = "CoFeescategorytype.findByCreateby", query = "SELECT c FROM CoFeescategorytype c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoFeescategorytype.findByCreatedate", query = "SELECT c FROM CoFeescategorytype c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CoFeescategorytype.findByModifyby", query = "SELECT c FROM CoFeescategorytype c WHERE c.modifyby = :modifyby"),
    @NamedQuery(name = "CoFeescategorytype.findByModifydate", query = "SELECT c FROM CoFeescategorytype c WHERE c.modifydate = :modifydate")})
public class CoFeescategorytype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ftid")
    private Integer ftid;
    @Basic(optional = false)
    @Column(name = "feestype")
    private String feestype;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
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

    public CoFeescategorytype() {
    }

    public CoFeescategorytype(Integer ftid) {
        this.ftid = ftid;
    }

    public CoFeescategorytype(Integer ftid, String feestype, String status, int createby, Date createdate) {
        this.ftid = ftid;
        this.feestype = feestype;
        this.status = status;
        this.createby = createby;
        this.createdate = createdate;
    }

    public Integer getFtid() {
        return ftid;
    }

    public void setFtid(Integer ftid) {
        this.ftid = ftid;
    }

    public String getFeestype() {
        return feestype;
    }

    public void setFeestype(String feestype) {
        this.feestype = feestype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        hash += (ftid != null ? ftid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoFeescategorytype)) {
            return false;
        }
        CoFeescategorytype other = (CoFeescategorytype) object;
        if ((this.ftid == null && other.ftid != null) || (this.ftid != null && !this.ftid.equals(other.ftid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoFeescategorytype[ ftid=" + ftid + " ]";
    }
    
}
