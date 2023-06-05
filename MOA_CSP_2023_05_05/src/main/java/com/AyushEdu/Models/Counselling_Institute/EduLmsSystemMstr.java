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
@Table(name = "edu_lms_system_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsSystemMstr.findAll", query = "SELECT e FROM EduLmsSystemMstr e"),
    @NamedQuery(name = "EduLmsSystemMstr.findById", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.id = :id"),
    @NamedQuery(name = "EduLmsSystemMstr.findBySystemName", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.systemName = :systemName"),
    @NamedQuery(name = "EduLmsSystemMstr.findByCreatedBy", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsSystemMstr.findByCreatedDt", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.createdDt = :createdDt"),
    @NamedQuery(name = "EduLmsSystemMstr.findByModifiedBy", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EduLmsSystemMstr.findByModifiedDt", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.modifiedDt = :modifiedDt"),
    @NamedQuery(name = "EduLmsSystemMstr.findByStatus", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.status = :status"),
    @NamedQuery(name = "EduLmsSystemMstr.findByCreatedRole", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.createdRole = :createdRole"),
    @NamedQuery(name = "EduLmsSystemMstr.findBySystemAbbr", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.systemAbbr = :systemAbbr"),
    @NamedQuery(name = "EduLmsSystemMstr.findByCommissionId", query = "SELECT e FROM EduLmsSystemMstr e WHERE e.commissionId = :commissionId")})
public class EduLmsSystemMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "system_name")
    private String systemName;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIME)
    private Date createdDt;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_dt")
    @Temporal(TemporalType.TIME)
    private Date modifiedDt;
    @Column(name = "status")
    private String status;
    @Column(name = "created_role")
    private String createdRole;
    @Column(name = "system_abbr")
    private String systemAbbr;
    @Column(name = "commission_id")
    private Integer commissionId;
    @OneToMany(mappedBy = "systemId")
    private Collection<EduLmsInstituteReg> eduLmsInstituteRegCollection;

    public EduLmsSystemMstr() {
    }

    public EduLmsSystemMstr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDt() {
        return modifiedDt;
    }

    public void setModifiedDt(Date modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedRole() {
        return createdRole;
    }

    public void setCreatedRole(String createdRole) {
        this.createdRole = createdRole;
    }

    public String getSystemAbbr() {
        return systemAbbr;
    }

    public void setSystemAbbr(String systemAbbr) {
        this.systemAbbr = systemAbbr;
    }

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
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
        if (!(object instanceof EduLmsSystemMstr)) {
            return false;
        }
        EduLmsSystemMstr other = (EduLmsSystemMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsSystemMstr[ id=" + id + " ]";
    }
    
}
