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
@Table(name = "edu_lms_university_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsUniversityMstr.findAll", query = "SELECT e FROM EduLmsUniversityMstr e"),
    @NamedQuery(name = "EduLmsUniversityMstr.findById", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.id = :id"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByUniversityName", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.universityName = :universityName"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByUniversityCode", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.universityCode = :universityCode"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByAddress", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.address = :address"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByCityName", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.cityName = :cityName"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByStatus", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.status = :status"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByCreatedBy", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByCreatedDate", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByModifiedBy", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByModifiedDate", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByUniversityType", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.universityType = :universityType"),
    @NamedQuery(name = "EduLmsUniversityMstr.findByOrganizationId", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.organizationId = :organizationId"),
    @NamedQuery(name = "EduLmsUniversityMstr.findBySystemId", query = "SELECT e FROM EduLmsUniversityMstr e WHERE e.systemId = :systemId")})
public class EduLmsUniversityMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "university_name")
    private String universityName;
    @Column(name = "university_code")
    private String universityCode;
    @Column(name = "address")
    private String address;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "status")
    private String status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "university_type")
    private Integer universityType;
    @Column(name = "organization_id")
    private Integer organizationId;
    @Column(name = "system_id")
    private Integer systemId;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
    private EduLmsCountryMstr countryId;
    @JoinColumn(name = "district_id", referencedColumnName = "district_id")
    @ManyToOne
    private EduLmsDistrictMstr districtId;
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @ManyToOne
    private EduLmsStateMstr stateId;
    @OneToMany(mappedBy = "universityId")
    private Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection;

    public EduLmsUniversityMstr() {
    }

    public EduLmsUniversityMstr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getUniversityType() {
        return universityType;
    }

    public void setUniversityType(Integer universityType) {
        this.universityType = universityType;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public EduLmsCountryMstr getCountryId() {
        return countryId;
    }

    public void setCountryId(EduLmsCountryMstr countryId) {
        this.countryId = countryId;
    }

    public EduLmsDistrictMstr getDistrictId() {
        return districtId;
    }

    public void setDistrictId(EduLmsDistrictMstr districtId) {
        this.districtId = districtId;
    }

    public EduLmsStateMstr getStateId() {
        return stateId;
    }

    public void setStateId(EduLmsStateMstr stateId) {
        this.stateId = stateId;
    }

    @XmlTransient
    public Collection<EduLmsInstituteReg> getEduLmsInstituteRegCollection() {
        return eduLmsInstituteRegCollection;
    }

    public void setEduLmsInstituteRegCollection(Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection) {
        this.eduLmsInstituteRegCollection = eduLmsInstituteRegCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EduLmsUniversityMstr)) {
            return false;
        }
        EduLmsUniversityMstr other = (EduLmsUniversityMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityMstr[ id=" + id + " ]";
    }
    
}
