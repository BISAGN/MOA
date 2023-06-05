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

import com.AyushEdu.Models.UserLogin;

/**
 *
 * @author rdp
 */
@Entity
@Table(name = "co_studentenrollment")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CoStudentenrollment.findAll", query = "SELECT c FROM CoStudentenrollment c"),
		@NamedQuery(name = "CoStudentenrollment.findBySeid", query = "SELECT c FROM CoStudentenrollment c WHERE c.seid = :seid"),
		@NamedQuery(name = "CoStudentenrollment.findByNeetrollnumber", query = "SELECT c FROM CoStudentenrollment c WHERE c.neetrollnumber = :neetrollnumber"),
		@NamedQuery(name = "CoStudentenrollment.findByApplicationnumber", query = "SELECT c FROM CoStudentenrollment c WHERE c.applicationnumber = :applicationnumber"),
		@NamedQuery(name = "CoStudentenrollment.findByNeetmarks", query = "SELECT c FROM CoStudentenrollment c WHERE c.neetmarks = :neetmarks"),
		@NamedQuery(name = "CoStudentenrollment.findByNeetrank", query = "SELECT c FROM CoStudentenrollment c WHERE c.neetrank = :neetrank"),
		@NamedQuery(name = "CoStudentenrollment.findByTenthpercentage", query = "SELECT c FROM CoStudentenrollment c WHERE c.tenthpercentage = :tenthpercentage"),
		@NamedQuery(name = "CoStudentenrollment.findByTwelvthpercentage", query = "SELECT c FROM CoStudentenrollment c WHERE c.twelvthpercentage = :twelvthpercentage"),
		@NamedQuery(name = "CoStudentenrollment.findByYear", query = "SELECT c FROM CoStudentenrollment c WHERE c.year = :year"),
		@NamedQuery(name = "CoStudentenrollment.findByFirstname", query = "SELECT c FROM CoStudentenrollment c WHERE c.firstname = :firstname"),
		@NamedQuery(name = "CoStudentenrollment.findByMiddlename", query = "SELECT c FROM CoStudentenrollment c WHERE c.middlename = :middlename"),
		@NamedQuery(name = "CoStudentenrollment.findByLastname", query = "SELECT c FROM CoStudentenrollment c WHERE c.lastname = :lastname"),
		@NamedQuery(name = "CoStudentenrollment.findByDob", query = "SELECT c FROM CoStudentenrollment c WHERE c.dob = :dob"),
		@NamedQuery(name = "CoStudentenrollment.findByMobilenumber", query = "SELECT c FROM CoStudentenrollment c WHERE c.mobilenumber = :mobilenumber"),
		@NamedQuery(name = "CoStudentenrollment.findByEmaildi", query = "SELECT c FROM CoStudentenrollment c WHERE c.emaildi = :emaildi"),
		@NamedQuery(name = "CoStudentenrollment.findByAadhaarnumber", query = "SELECT c FROM CoStudentenrollment c WHERE c.aadhaarnumber = :aadhaarnumber"),
		@NamedQuery(name = "CoStudentenrollment.findByCreateby", query = "SELECT c FROM CoStudentenrollment c WHERE c.createby = :createby"),
		@NamedQuery(name = "CoStudentenrollment.findByCreatedate", query = "SELECT c FROM CoStudentenrollment c WHERE c.createdate = :createdate"),
		@NamedQuery(name = "CoStudentenrollment.findByModifyby", query = "SELECT c FROM CoStudentenrollment c WHERE c.modifyby = :modifyby"),
		@NamedQuery(name = "CoStudentenrollment.findByModifydate", query = "SELECT c FROM CoStudentenrollment c WHERE c.modifydate = :modifydate"),
		@NamedQuery(name = "CoStudentenrollment.findByStatus", query = "SELECT c FROM CoStudentenrollment c WHERE c.status = :status"),
		@NamedQuery(name = "CoStudentenrollment.findByRoundstatus", query = "SELECT c FROM CoStudentenrollment c WHERE c.roundstatus = :roundstatus"),
		@NamedQuery(name = "CoStudentenrollment.findByRound", query = "SELECT c FROM CoStudentenrollment c WHERE c.round = :round"),
		@NamedQuery(name = "CoStudentenrollment.findByPaymentstatus", query = "SELECT c FROM CoStudentenrollment c WHERE c.paymentstatus = :paymentstatus"),
		@NamedQuery(name = "CoStudentenrollment.findByAddress", query = "SELECT c FROM CoStudentenrollment c WHERE c.address = :address") })
public class CoStudentenrollment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "seid")
	private Long seid;
	@Basic(optional = false)
	@Column(name = "neetrollnumber")
	private String neetrollnumber;
	@Basic(optional = false)
	@Column(name = "applicationnumber")
	private String applicationnumber;
	@Basic(optional = false)
	@Column(name = "neetmarks")
	private int neetmarks;
	@Basic(optional = false)
	@Column(name = "neetrank")
	private int neetrank;
	@Basic(optional = false)
	@Column(name = "tenthpercentage")
	private double tenthpercentage;
	@Basic(optional = false)
	@Column(name = "twelvthpercentage")
	private double twelvthpercentage;
	@Basic(optional = false)
	@Column(name = "year")
	private String year;
	@Basic(optional = false)
	@Column(name = "firstname")
	private String firstname;
	@Basic(optional = false)
	@Column(name = "middlename")
	private String middlename;
	@Basic(optional = false)
	@Column(name = "lastname")
	private String lastname;
	@Basic(optional = false)
	@Column(name = "dob")
	private String dob;
	@Basic(optional = false)
	@Column(name = "mobilenumber")
	private String mobilenumber;
	@Basic(optional = false)
	@Column(name = "emaildi")
	private String emaildi;
	@Basic(optional = false)
	@Column(name = "aadhaarnumber")
	private String aadhaarnumber;
	@Basic(optional = false)
	@Column(name = "createby")
	private int createby;
	@Basic(optional = false)
	@Column(name = "createdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;
	@Column(name = "modifyby")
	private Integer modifyby;
	@Column(name = "modifydate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifydate;
	@Column(name = "status")
	private String status;
	@Column(name = "roundstatus")
	private String roundstatus;
	@Column(name = "round")
	private Integer round;
	@Column(name = "paymentstatus")
	private String paymentstatus;
	@Column(name = "address")
	private String address;
	@JoinColumn(name = "countryid", referencedColumnName = "id")
	@ManyToOne
	private EduLmsCountryMstr countryid;
	@JoinColumn(name = "districtid", referencedColumnName = "district_id")
	@ManyToOne
	private EduLmsDistrictMstr districtid;
	@JoinColumn(name = "stateid", referencedColumnName = "state_id")
	@ManyToOne
	private EduLmsStateMstr stateid;
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	@ManyToOne
	private UserLogin userid;
	@JoinColumn(name = "catid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private TbLmsCategoryMstr catid;
//	@Basic(optional = true)
//	@Column(name = "processfor")
//	private String processfor;
//	@Basic(optional = true)
//	@Column(name = "commtype")
//	private int commtype;

	public CoStudentenrollment() {
	}

	public CoStudentenrollment(Long seid) {
		this.seid = seid;
	}

//	public String getProcessfor() {
//		return processfor;
//	}
//
//	public void setProcessfor(String processfor) {
//		this.processfor = processfor;
//	}
//
//	public int getCommtype() {
//		return commtype;
//	}
//
//	public void setCommtype(int commtype) {
//		this.commtype = commtype;
//	}

	public CoStudentenrollment(Long seid, String neetrollnumber, String applicationnumber, int neetmarks, int neetrank,
			double tenthpercentage, double twelvthpercentage, String year, String firstname, String middlename,
			String lastname, String dob, String mobilenumber, String emaildi, String aadhaarnumber, int createby,
			Date createdate) {
		this.seid = seid;
		this.neetrollnumber = neetrollnumber;
		this.applicationnumber = applicationnumber;
		this.neetmarks = neetmarks;
		this.neetrank = neetrank;
		this.tenthpercentage = tenthpercentage;
		this.twelvthpercentage = twelvthpercentage;
		this.year = year;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.dob = dob;
		this.mobilenumber = mobilenumber;
		this.emaildi = emaildi;
		this.aadhaarnumber = aadhaarnumber;
		this.createby = createby;
		this.createdate = createdate;
	}

	public Long getSeid() {
		return seid;
	}

	public void setSeid(Long seid) {
		this.seid = seid;
	}

	public String getNeetrollnumber() {
		return neetrollnumber;
	}

	public void setNeetrollnumber(String neetrollnumber) {
		this.neetrollnumber = neetrollnumber;
	}

	public String getApplicationnumber() {
		return applicationnumber;
	}

	public void setApplicationnumber(String applicationnumber) {
		this.applicationnumber = applicationnumber;
	}

	public int getNeetmarks() {
		return neetmarks;
	}

	public void setNeetmarks(int neetmarks) {
		this.neetmarks = neetmarks;
	}

	public int getNeetrank() {
		return neetrank;
	}

	public void setNeetrank(int neetrank) {
		this.neetrank = neetrank;
	}

	public double getTenthpercentage() {
		return tenthpercentage;
	}

	public void setTenthpercentage(double tenthpercentage) {
		this.tenthpercentage = tenthpercentage;
	}

	public double getTwelvthpercentage() {
		return twelvthpercentage;
	}

	public void setTwelvthpercentage(double twelvthpercentage) {
		this.twelvthpercentage = twelvthpercentage;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getEmaildi() {
		return emaildi;
	}

	public void setEmaildi(String emaildi) {
		this.emaildi = emaildi;
	}

	public String getAadhaarnumber() {
		return aadhaarnumber;
	}

	public void setAadhaarnumber(String aadhaarnumber) {
		this.aadhaarnumber = aadhaarnumber;
	}

	public int getCreateby() {
		return createby;
	}

	public void setCreateby(int createby) {
		this.createby = createby;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getModifyby() {
		return modifyby;
	}

	public void setModifyby(Integer modifyby) {
		this.modifyby = modifyby;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoundstatus() {
		return roundstatus;
	}

	public void setRoundstatus(String roundstatus) {
		this.roundstatus = roundstatus;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EduLmsCountryMstr getCountryid() {
		return countryid;
	}

	public void setCountryid(EduLmsCountryMstr countryid) {
		this.countryid = countryid;
	}

	public EduLmsDistrictMstr getDistrictid() {
		return districtid;
	}

	public void setDistrictid(EduLmsDistrictMstr districtid) {
		this.districtid = districtid;
	}

	public EduLmsStateMstr getStateid() {
		return stateid;
	}

	public void setStateid(EduLmsStateMstr stateid) {
		this.stateid = stateid;
	}

	public UserLogin getUserid() {
		return userid;
	}

	public void setUserid(UserLogin userid) {
		this.userid = userid;
	}

	public TbLmsCategoryMstr getCatid() {
		return catid;
	}

	public void setCatid(TbLmsCategoryMstr catid) {
		this.catid = catid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (seid != null ? seid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CoStudentenrollment)) {
			return false;
		}
		CoStudentenrollment other = (CoStudentenrollment) object;
		if ((this.seid == null && other.seid != null) || (this.seid != null && !this.seid.equals(other.seid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.AyushEdu.Models.Counselling_Institute.CoStudentenrollment[ seid=" + seid + " ]";
	}

}
