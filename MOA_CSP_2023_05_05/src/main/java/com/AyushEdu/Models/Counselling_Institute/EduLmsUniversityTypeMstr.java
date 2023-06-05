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
@Table(name = "edu_lms_university_type_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findAll", query = "SELECT e FROM EduLmsUniversityTypeMstr e"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findById", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.id = :id"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findByUniversityType", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.universityType = :universityType"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findByStatus", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.status = :status"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findByCreatedBy", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findByCreatedDate", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findByModifiedBy", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EduLmsUniversityTypeMstr.findByModifiedDate", query = "SELECT e FROM EduLmsUniversityTypeMstr e WHERE e.modifiedDate = :modifiedDate")})
public class EduLmsUniversityTypeMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "university_type")
    private String universityType;
    @Column(name = "status")
    private Integer status;
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

    public EduLmsUniversityTypeMstr() {
    }

    public EduLmsUniversityTypeMstr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityType() {
        return universityType;
    }

    public void setUniversityType(String universityType) {
        this.universityType = universityType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EduLmsUniversityTypeMstr)) {
            return false;
        }
        EduLmsUniversityTypeMstr other = (EduLmsUniversityTypeMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityTypeMstr[ id=" + id + " ]";
    }
    
}
