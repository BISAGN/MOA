/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AyushEdu.Models.Counselling_Institute;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rdp
 */
@Entity
@Table(name = "edu_lms_district_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsDistrictMstr.findAll", query = "SELECT e FROM EduLmsDistrictMstr e"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByDistrictId", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.districtId = :districtId"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByCreatedBy", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByCreatedDate", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByModifyBy", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.modifyBy = :modifyBy"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByModifyDate", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.modifyDate = :modifyDate"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByStatus", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.status = :status"),
    @NamedQuery(name = "EduLmsDistrictMstr.findByDistrictName", query = "SELECT e FROM EduLmsDistrictMstr e WHERE e.districtName = :districtName")})
public class EduLmsDistrictMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "district_id")
    private Integer districtId;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modify_by")
    private String modifyBy;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Column(name = "status")
    private String status;
    @Column(name = "district_name")
    private String districtName;
    @OneToMany(mappedBy = "districtId")
    private Collection<EduLmsUniversityMstr> eduLmsUniversityMstrCollection;
    @OneToMany(mappedBy = "districtId")
    private Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
    private EduLmsCountryMstr countryId;
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @ManyToOne
    private EduLmsStateMstr stateId;

    public EduLmsDistrictMstr() {
    }

    public EduLmsDistrictMstr(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @XmlTransient
    public Collection<EduLmsUniversityMstr> getEduLmsUniversityMstrCollection() {
        return eduLmsUniversityMstrCollection;
    }

    public void setEduLmsUniversityMstrCollection(Collection<EduLmsUniversityMstr> eduLmsUniversityMstrCollection) {
        this.eduLmsUniversityMstrCollection = eduLmsUniversityMstrCollection;
    }

    @XmlTransient
    public Collection<EduLmsInstituteReg> getEduLmsInstituteRegCollection() {
        return eduLmsInstituteRegCollection;
    }

    public void setEduLmsInstituteRegCollection(Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection) {
        this.eduLmsInstituteRegCollection = eduLmsInstituteRegCollection;
    }

    public EduLmsCountryMstr getCountryId() {
        return countryId;
    }

    public void setCountryId(EduLmsCountryMstr countryId) {
        this.countryId = countryId;
    }

    public EduLmsStateMstr getStateId() {
        return stateId;
    }

    public void setStateId(EduLmsStateMstr stateId) {
        this.stateId = stateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (districtId != null ? districtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EduLmsDistrictMstr)) {
            return false;
        }
        EduLmsDistrictMstr other = (EduLmsDistrictMstr) object;
        if ((this.districtId == null && other.districtId != null) || (this.districtId != null && !this.districtId.equals(other.districtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr[ districtId=" + districtId + " ]";
    }
    
}
