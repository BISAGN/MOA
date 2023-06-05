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
@Table(name = "edu_lms_state_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsStateMstr.findAll", query = "SELECT e FROM EduLmsStateMstr e"),
    @NamedQuery(name = "EduLmsStateMstr.findByStateId", query = "SELECT e FROM EduLmsStateMstr e WHERE e.stateId = :stateId"),
    @NamedQuery(name = "EduLmsStateMstr.findByStateName", query = "SELECT e FROM EduLmsStateMstr e WHERE e.stateName = :stateName"),
    @NamedQuery(name = "EduLmsStateMstr.findByCreatedBy", query = "SELECT e FROM EduLmsStateMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsStateMstr.findByCreatedDate", query = "SELECT e FROM EduLmsStateMstr e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsStateMstr.findByModifyBy", query = "SELECT e FROM EduLmsStateMstr e WHERE e.modifyBy = :modifyBy"),
    @NamedQuery(name = "EduLmsStateMstr.findByModifyDate", query = "SELECT e FROM EduLmsStateMstr e WHERE e.modifyDate = :modifyDate"),
    @NamedQuery(name = "EduLmsStateMstr.findByStatus", query = "SELECT e FROM EduLmsStateMstr e WHERE e.status = :status"),
    @NamedQuery(name = "EduLmsStateMstr.findByStateAbbr", query = "SELECT e FROM EduLmsStateMstr e WHERE e.stateAbbr = :stateAbbr")})
public class EduLmsStateMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "state_id")
    private Integer stateId;
    @Column(name = "state_name")
    private String stateName;
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
    @Column(name = "state_abbr")
    private String stateAbbr;
    @OneToMany(mappedBy = "stateId")
    private Collection<EduLmsUniversityMstr> eduLmsUniversityMstrCollection;
    @OneToMany(mappedBy = "stateId")
    private Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EduLmsCountryMstr countryId;
    @OneToMany(mappedBy = "stateId")
    private Collection<EduLmsDistrictMstr> eduLmsDistrictMstrCollection;

    public EduLmsStateMstr() {
    }

    public EduLmsStateMstr(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
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

    @XmlTransient
    public Collection<EduLmsDistrictMstr> getEduLmsDistrictMstrCollection() {
        return eduLmsDistrictMstrCollection;
    }

    public void setEduLmsDistrictMstrCollection(Collection<EduLmsDistrictMstr> eduLmsDistrictMstrCollection) {
        this.eduLmsDistrictMstrCollection = eduLmsDistrictMstrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EduLmsStateMstr)) {
            return false;
        }
        EduLmsStateMstr other = (EduLmsStateMstr) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr[ stateId=" + stateId + " ]";
    }
    
}
