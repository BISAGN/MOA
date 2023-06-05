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
@Table(name = "co_otpdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoOtpdetail.findAll", query = "SELECT c FROM CoOtpdetail c"),
    @NamedQuery(name = "CoOtpdetail.findByOid", query = "SELECT c FROM CoOtpdetail c WHERE c.oid = :oid"),
    @NamedQuery(name = "CoOtpdetail.findByOtptype", query = "SELECT c FROM CoOtpdetail c WHERE c.otptype = :otptype"),
    @NamedQuery(name = "CoOtpdetail.findByOtpvalue", query = "SELECT c FROM CoOtpdetail c WHERE c.otpvalue = :otpvalue"),
    @NamedQuery(name = "CoOtpdetail.findByEmailormobile", query = "SELECT c FROM CoOtpdetail c WHERE c.emailormobile = :emailormobile"),
    @NamedQuery(name = "CoOtpdetail.findBySenddatetime", query = "SELECT c FROM CoOtpdetail c WHERE c.senddatetime = :senddatetime"),
    @NamedQuery(name = "CoOtpdetail.findByStatus", query = "SELECT c FROM CoOtpdetail c WHERE c.status = :status")})
public class CoOtpdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Long oid;
    @Basic(optional = false)
    @Column(name = "otptype")
    private String otptype;
    @Basic(optional = false)
    @Column(name = "otpvalue")
    private String otpvalue;
    @Basic(optional = false)
    @Column(name = "emailormobile")
    private String emailormobile;
    @Basic(optional = false)
    @Column(name = "senddatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date senddatetime;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public CoOtpdetail() {
    }

    public CoOtpdetail(Long oid) {
        this.oid = oid;
    }

    public CoOtpdetail(Long oid, String otptype, String otpvalue, String emailormobile, Date senddatetime, String status) {
        this.oid = oid;
        this.otptype = otptype;
        this.otpvalue = otpvalue;
        this.emailormobile = emailormobile;
        this.senddatetime = senddatetime;
        this.status = status;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOtptype() {
        return otptype;
    }

    public void setOtptype(String otptype) {
        this.otptype = otptype;
    }

    public String getOtpvalue() {
        return otpvalue;
    }

    public void setOtpvalue(String otpvalue) {
        this.otpvalue = otpvalue;
    }

    public String getEmailormobile() {
        return emailormobile;
    }

    public void setEmailormobile(String emailormobile) {
        this.emailormobile = emailormobile;
    }

    public Date getSenddatetime() {
        return senddatetime;
    }

    public void setSenddatetime(Date senddatetime) {
        this.senddatetime = senddatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoOtpdetail)) {
            return false;
        }
        CoOtpdetail other = (CoOtpdetail) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoOtpdetail[ oid=" + oid + " ]";
    }
    
}
