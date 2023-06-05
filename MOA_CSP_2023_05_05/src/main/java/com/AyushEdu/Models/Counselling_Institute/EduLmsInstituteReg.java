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
@Table(name = "edu_lms_institute_reg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EduLmsInstituteReg.findAll", query = "SELECT e FROM EduLmsInstituteReg e"),
    @NamedQuery(name = "EduLmsInstituteReg.findById", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.id = :id"),
    @NamedQuery(name = "EduLmsInstituteReg.findByInstituteName", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.instituteName = :instituteName"),
    @NamedQuery(name = "EduLmsInstituteReg.findByCode", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.code = :code"),
    @NamedQuery(name = "EduLmsInstituteReg.findByAddress", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.address = :address"),
    @NamedQuery(name = "EduLmsInstituteReg.findByStatus", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.status = :status"),
    @NamedQuery(name = "EduLmsInstituteReg.findByAppStatus", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.appStatus = :appStatus"),
    @NamedQuery(name = "EduLmsInstituteReg.findByCreatedBy", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "EduLmsInstituteReg.findByCreatedDate", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EduLmsInstituteReg.findByModifiedBy", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "EduLmsInstituteReg.findByModifiedDate", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "EduLmsInstituteReg.findByUploadImage", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.uploadImage = :uploadImage"),
    @NamedQuery(name = "EduLmsInstituteReg.findByInstituteEmail", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.instituteEmail = :instituteEmail"),
    @NamedQuery(name = "EduLmsInstituteReg.findByInstituteMobNo", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.instituteMobNo = :instituteMobNo"),
    @NamedQuery(name = "EduLmsInstituteReg.findByCollegeUniqueId", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.collegeUniqueId = :collegeUniqueId"),
    @NamedQuery(name = "EduLmsInstituteReg.findByCollegeAbbr", query = "SELECT e FROM EduLmsInstituteReg e WHERE e.collegeAbbr = :collegeAbbr")})
public class EduLmsInstituteReg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "institute_name")
    private String instituteName;
    @Column(name = "code")
    private String code;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private String status;
    @Column(name = "app_status")
    private String appStatus;
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
    @Column(name = "upload_image")
    private String uploadImage;
    @Column(name = "institute_email")
    private String instituteEmail;
    @Column(name = "institute_mob_no")
    private String instituteMobNo;
    @Column(name = "college_unique_id")
    private String collegeUniqueId;
    @Column(name = "college_abbr")
    private String collegeAbbr;
    @Column(name = "no_of_part")
    private Integer no_of_part;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
    private EduLmsCountryMstr countryId;
    @JoinColumn(name = "district_id", referencedColumnName = "district_id")
    @ManyToOne
    private EduLmsDistrictMstr districtId;
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @ManyToOne
    private EduLmsStateMstr stateId;
    @JoinColumn(name = "system_id", referencedColumnName = "id")
    @ManyToOne
    private EduLmsSystemMstr systemId;
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    @ManyToOne
    private EduLmsUniversityMstr universityId;
    
    @Column(name = "total_sanctioned_seat")
    private Integer total_sanctioned_seat;

    public EduLmsInstituteReg() {
    }

    public EduLmsInstituteReg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
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

    public String getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(String uploadImage) {
        this.uploadImage = uploadImage;
    }

    public String getInstituteEmail() {
        return instituteEmail;
    }

    public void setInstituteEmail(String instituteEmail) {
        this.instituteEmail = instituteEmail;
    }

    public String getInstituteMobNo() {
        return instituteMobNo;
    }

    public void setInstituteMobNo(String instituteMobNo) {
        this.instituteMobNo = instituteMobNo;
    }

    public String getCollegeUniqueId() {
        return collegeUniqueId;
    }

    public void setCollegeUniqueId(String collegeUniqueId) {
        this.collegeUniqueId = collegeUniqueId;
    }

    public String getCollegeAbbr() {
        return collegeAbbr;
    }

    public void setCollegeAbbr(String collegeAbbr) {
        this.collegeAbbr = collegeAbbr;
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

    public EduLmsSystemMstr getSystemId() {
        return systemId;
    }

    public void setSystemId(EduLmsSystemMstr systemId) {
        this.systemId = systemId;
    }

    public EduLmsUniversityMstr getUniversityId() {
        return universityId;
    }

    public void setUniversityId(EduLmsUniversityMstr universityId) {
        this.universityId = universityId;
    }

    public Integer getNo_of_part() {
		return no_of_part;
	}

	public void setNo_of_part(Integer no_of_part) {
		this.no_of_part = no_of_part;
	}
	
	public Integer getTotal_sanctioned_seat() {
		return total_sanctioned_seat;
	}

	public void setTotal_sanctioned_seat(Integer total_sanctioned_seat) {
		this.total_sanctioned_seat = total_sanctioned_seat;
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
        if (!(object instanceof EduLmsInstituteReg)) {
            return false;
        }
        EduLmsInstituteReg other = (EduLmsInstituteReg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg[ id=" + id + " ]";
    }
    
}
