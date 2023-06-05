/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AyushEdu.Models.Counselling_Institute;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "edu_lms_country_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsCountryMstr.findAll", query = "SELECT e FROM EduLmsCountryMstr e"),
    @NamedQuery(name = "EduLmsCountryMstr.findById", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.id = :id"),
    @NamedQuery(name = "EduLmsCountryMstr.findByName", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.name = :name"),
    @NamedQuery(name = "EduLmsCountryMstr.findByCreatedBy", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsCountryMstr.findByCreatedDate", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsCountryMstr.findByModifyBy", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.modifyBy = :modifyBy"),
    @NamedQuery(name = "EduLmsCountryMstr.findByModifyDate", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.modifyDate = :modifyDate"),
    @NamedQuery(name = "EduLmsCountryMstr.findByStatus", query = "SELECT e FROM EduLmsCountryMstr e WHERE e.status = :status")})
public class EduLmsCountryMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
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
    @OneToMany(mappedBy = "countryId")
    private Collection<EduLmsUniversityMstr> eduLmsUniversityMstrCollection;
    @OneToMany(mappedBy = "countryId")
    private Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private Collection<EduLmsStateMstr> eduLmsStateMstrCollection;
    @OneToMany(mappedBy = "countryId")
    private Collection<EduLmsDistrictMstr> eduLmsDistrictMstrCollection;

    public EduLmsCountryMstr() {
    }

    public EduLmsCountryMstr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlTransient
    public Collection<EduLmsStateMstr> getEduLmsStateMstrCollection() {
        return eduLmsStateMstrCollection;
    }

    public void setEduLmsStateMstrCollection(Collection<EduLmsStateMstr> eduLmsStateMstrCollection) {
        this.eduLmsStateMstrCollection = eduLmsStateMstrCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EduLmsCountryMstr)) {
            return false;
        }
        EduLmsCountryMstr other = (EduLmsCountryMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr[ id=" + id + " ]";
    }
    
}
