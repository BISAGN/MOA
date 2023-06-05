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
 * @author rdp
 */
@Entity
@Table(name = "co_registrationfeestructure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoRegistrationfeestructure.findAll", query = "SELECT c FROM CoRegistrationfeestructure c"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByRfid", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.rfid = :rfid"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByFees", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.fees = :fees"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByType", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.type = :type"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByStatus", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.status = :status"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByCreateBy", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.createBy = :createBy"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByCreateDate", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.createDate = :createDate"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByModifyBy", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.modifyBy = :modifyBy"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByModifyDate", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.modifyDate = :modifyDate"),
    @NamedQuery(name = "CoRegistrationfeestructure.findBySecurityMoney", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.securityMoney = :securityMoney"),
    @NamedQuery(name = "CoRegistrationfeestructure.findByYear", query = "SELECT c FROM CoRegistrationfeestructure c WHERE c.year = :year")})
public class CoRegistrationfeestructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rfid")
    private Integer rfid;
    @Basic(optional = false)
    @Column(name = "fees")
    private double fees;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "create_by")
    private int createBy;
    @Basic(optional = false)
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "modify_by")
    private Integer modifyBy;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Basic(optional = false)
    @Column(name = "security_money")
    private double securityMoney;
    @Basic(optional = false)
    @Column(name = "year")
    private String year;
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TbLmsCategoryMstr catId;

    public CoRegistrationfeestructure() {
    }

    public CoRegistrationfeestructure(Integer rfid) {
        this.rfid = rfid;
    }

    public CoRegistrationfeestructure(Integer rfid, double fees, String type, String status, int createBy, Date createDate, double securityMoney, String year) {
        this.rfid = rfid;
        this.fees = fees;
        this.type = type;
        this.status = status;
        this.createBy = createBy;
        this.createDate = createDate;
        this.securityMoney = securityMoney;
        this.year = year;
    }

    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public double getSecurityMoney() {
        return securityMoney;
    }

    public void setSecurityMoney(double securityMoney) {
        this.securityMoney = securityMoney;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public TbLmsCategoryMstr getCatId() {
        return catId;
    }

    public void setCatId(TbLmsCategoryMstr catId) {
        this.catId = catId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfid != null ? rfid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoRegistrationfeestructure)) {
            return false;
        }
        CoRegistrationfeestructure other = (CoRegistrationfeestructure) object;
        if ((this.rfid == null && other.rfid != null) || (this.rfid != null && !this.rfid.equals(other.rfid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoRegistrationfeestructure[ rfid=" + rfid + " ]";
    }
    
}
