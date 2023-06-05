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
@Table(name = "tb_lms_category_mstr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbLmsCategoryMstr.findAll", query = "SELECT t FROM TbLmsCategoryMstr t"),
    @NamedQuery(name = "TbLmsCategoryMstr.findById", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.id = :id"),
    @NamedQuery(name = "TbLmsCategoryMstr.findByCategory", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.category = :category"),
    @NamedQuery(name = "TbLmsCategoryMstr.findByStatus", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.status = :status"),
    @NamedQuery(name = "TbLmsCategoryMstr.findByCreatedBy", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.createdBy = :createdBy"),
    @NamedQuery(name = "TbLmsCategoryMstr.findByCreatedDate", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.createdDate = :createdDate"),
    @NamedQuery(name = "TbLmsCategoryMstr.findByModifiedBy", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "TbLmsCategoryMstr.findByModifiedDate", query = "SELECT t FROM TbLmsCategoryMstr t WHERE t.modifiedDate = :modifiedDate")})
public class TbLmsCategoryMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "category")
    private String category;
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

    public TbLmsCategoryMstr() {
    }
    
    public TbLmsCategoryMstr(String category) {
    	this.category=category;
    }

    public TbLmsCategoryMstr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbLmsCategoryMstr)) {
            return false;
        }
        TbLmsCategoryMstr other = (TbLmsCategoryMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr[ id=" + id + " ]";
    }
    
}
