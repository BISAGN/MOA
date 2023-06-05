package com.AyushEdu.Models.Alumni;

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
 * @author bisag
 */
@Entity
@Table(name = "edu_alum_alumni_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findAll", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findById", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.id = :id"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByTitle", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.title = :title"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByDescription", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.description = :description"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByUploadImg", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.uploadImg = :uploadImg"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByPostDate", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.postDate = :postDate"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByBatch", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.batch = :batch"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByCreatedBy", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByCreatedDate", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByModifiedBy", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByModifiedDate", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "EDU_ALUM_ALUMNI_POST.findByStatus", query = "SELECT e FROM EDU_ALUM_ALUMNI_POST e WHERE e.status = :status")})
public class EDU_ALUM_ALUMNI_POST implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "upload_img")
    private String uploadImg;
    @Column(name = "post_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @Column(name = "batch")
    private String batch;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "feed_cat_id", referencedColumnName = "id")
    @ManyToOne
    private EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR feedCatId;

    public EDU_ALUM_ALUMNI_POST() {
    }

    public EDU_ALUM_ALUMNI_POST(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadImg() {
        return uploadImg;
    }

    public void setUploadImg(String uploadImg) {
        this.uploadImg = uploadImg;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
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

    public EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR getFeedCatId() {
        return feedCatId;
    }

    public void setFeedCatId(EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR feedCatId) {
        this.feedCatId = feedCatId;
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
        if (!(object instanceof EDU_ALUM_ALUMNI_POST)) {
            return false;
        }
        EDU_ALUM_ALUMNI_POST other = (EDU_ALUM_ALUMNI_POST) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.EDU_ALUM_ALUMNI_POST[ id=" + id + " ]";
    }
    
}

