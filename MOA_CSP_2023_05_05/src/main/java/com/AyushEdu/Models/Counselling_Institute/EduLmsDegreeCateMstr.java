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
@Table(name = "edu_lms_degree_cate_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsDegreeCateMstr.findAll", query = "SELECT e FROM EduLmsDegreeCateMstr e"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findById", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.id = :id"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findByDegreeCate", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.degreeCate = :degreeCate"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findByCreatedBy", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findByCreatedDate", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findByModifiedBy", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findByModifiedDate", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "EduLmsDegreeCateMstr.findByStatus", query = "SELECT e FROM EduLmsDegreeCateMstr e WHERE e.status = :status")})
public class EduLmsDegreeCateMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "degree_cate")
    private String degreeCate;
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
    @Column(name = "status")
    private Integer status;
    @OneToMany(mappedBy = "degreetype")
    private Collection<CoInstituteotherdetail> coInstituteotherdetailCollection;

    public EduLmsDegreeCateMstr() {
    }

    public EduLmsDegreeCateMstr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDegreeCate() {
        return degreeCate;
    }

    public void setDegreeCate(String degreeCate) {
        this.degreeCate = degreeCate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<CoInstituteotherdetail> getCoInstituteotherdetailCollection() {
        return coInstituteotherdetailCollection;
    }

    public void setCoInstituteotherdetailCollection(Collection<CoInstituteotherdetail> coInstituteotherdetailCollection) {
        this.coInstituteotherdetailCollection = coInstituteotherdetailCollection;
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
        if (!(object instanceof EduLmsDegreeCateMstr)) {
            return false;
        }
        EduLmsDegreeCateMstr other = (EduLmsDegreeCateMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.seat_allocation_entity.EduLmsDegreeCateMstr[ id=" + id + " ]";
    }
    
}
