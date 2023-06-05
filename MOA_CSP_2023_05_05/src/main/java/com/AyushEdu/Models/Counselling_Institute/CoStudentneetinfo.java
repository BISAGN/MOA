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
@Table(name = "co_studentneetinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoStudentneetinfo.findAll", query = "SELECT c FROM CoStudentneetinfo c"),
    @NamedQuery(name = "CoStudentneetinfo.findBySnid", query = "SELECT c FROM CoStudentneetinfo c WHERE c.snid = :snid"),
    @NamedQuery(name = "CoStudentneetinfo.findByDob", query = "SELECT c FROM CoStudentneetinfo c WHERE c.dob = :dob"),
    @NamedQuery(name = "CoStudentneetinfo.findByAadhaarnumber", query = "SELECT c FROM CoStudentneetinfo c WHERE c.aadhaarnumber = :aadhaarnumber"),
    @NamedQuery(name = "CoStudentneetinfo.findByNeetrollnumber", query = "SELECT c FROM CoStudentneetinfo c WHERE c.neetrollnumber = :neetrollnumber"),
    @NamedQuery(name = "CoStudentneetinfo.findByApplicationnumber", query = "SELECT c FROM CoStudentneetinfo c WHERE c.applicationnumber = :applicationnumber"),
    @NamedQuery(name = "CoStudentneetinfo.findByNeetmarks", query = "SELECT c FROM CoStudentneetinfo c WHERE c.neetmarks = :neetmarks"),
    @NamedQuery(name = "CoStudentneetinfo.findByNeetrank", query = "SELECT c FROM CoStudentneetinfo c WHERE c.neetrank = :neetrank"),
    @NamedQuery(name = "CoStudentneetinfo.findByYear", query = "SELECT c FROM CoStudentneetinfo c WHERE c.year = :year"),
    @NamedQuery(name = "CoStudentneetinfo.findByCreateby", query = "SELECT c FROM CoStudentneetinfo c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoStudentneetinfo.findByCreatedate", query = "SELECT c FROM CoStudentneetinfo c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CoStudentneetinfo.findByModifyby", query = "SELECT c FROM CoStudentneetinfo c WHERE c.modifyby = :modifyby"),
    @NamedQuery(name = "CoStudentneetinfo.findByModifydate", query = "SELECT c FROM CoStudentneetinfo c WHERE c.modifydate = :modifydate")})
public class CoStudentneetinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "snid")
    private Long snid;
    @Basic(optional = false)
    @Column(name = "dob")
    private String dob;
    @Basic(optional = false)
    @Column(name = "aadhaarnumber")
    private String aadhaarnumber;
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
    @Column(name = "year")
    private String year;
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
    
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private TbLmsCategoryMstr categoryid;

    public CoStudentneetinfo() {
    }

    public CoStudentneetinfo(Long snid) {
        this.snid = snid;
    }

    public CoStudentneetinfo(Long snid, String dob, String aadhaarnumber, String neetrollnumber, String applicationnumber, int neetmarks, int neetrank, int createby, Date createdate) {
        this.snid = snid;
        this.dob = dob;
        this.aadhaarnumber = aadhaarnumber;
        this.neetrollnumber = neetrollnumber;
        this.applicationnumber = applicationnumber;
        this.neetmarks = neetmarks;
        this.neetrank = neetrank;
        this.createby = createby;
        this.createdate = createdate;
    }

    public Long getSnid() {
        return snid;
    }

    public void setSnid(Long snid) {
        this.snid = snid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAadhaarnumber() {
        return aadhaarnumber;
    }

    public void setAadhaarnumber(String aadhaarnumber) {
        this.aadhaarnumber = aadhaarnumber;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public TbLmsCategoryMstr getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(TbLmsCategoryMstr categoryid) {
		this.categoryid = categoryid;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (snid != null ? snid.hashCode() : 0);
        return hash;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoStudentneetinfo)) {
            return false;
        }
        CoStudentneetinfo other = (CoStudentneetinfo) object;
        if ((this.snid == null && other.snid != null) || (this.snid != null && !this.snid.equals(other.snid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoStudentneetinfo[ snid=" + snid + " ]";
    }
    
}
