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
@Table(name = "co_studentchoicefilling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoStudentchoicefilling.findAll", query = "SELECT c FROM CoStudentchoicefilling c"),
    @NamedQuery(name = "CoStudentchoicefilling.findBySid", query = "SELECT c FROM CoStudentchoicefilling c WHERE c.sid = :sid"),
    @NamedQuery(name = "CoStudentchoicefilling.findByRound", query = "SELECT c FROM CoStudentchoicefilling c WHERE c.round = :round"),
    @NamedQuery(name = "CoStudentchoicefilling.findByStatus", query = "SELECT c FROM CoStudentchoicefilling c WHERE c.status = :status"),
    @NamedQuery(name = "CoStudentchoicefilling.findByCreateby", query = "SELECT c FROM CoStudentchoicefilling c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoStudentchoicefilling.findByCreatedate", query = "SELECT c FROM CoStudentchoicefilling c WHERE c.createdate = :createdate")})
public class CoStudentchoicefilling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sid")
    private Long sid;
    @Basic(optional = false)
    @Column(name = "round")
    private String round;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "createby")
    private int createby;
    @Basic(optional = false)
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @JoinColumn(name = "seid", referencedColumnName = "seid")
    @ManyToOne(optional = false)
    private CoStudentenrollment seid;
    @JoinColumn(name = "inid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EduLmsInstituteReg inid;
    @JoinColumn(name = "assignedcategory", referencedColumnName = "id")
    @ManyToOne
    private TbLmsCategoryMstr assignedcategory;
    
    @Basic(optional = false)
    @Column(name = "commtype")
    private int commtype;
    
    @Basic(optional = false)
    @Column(name = "rolename")
    private String rolename;

    public CoStudentchoicefilling() {
    }

    public CoStudentchoicefilling(Long sid) {
        this.sid = sid;
    }

    public CoStudentchoicefilling(Long sid, String round, String status, int createby, Date createdate) {
        this.sid = sid;
        this.round = round;
        this.status = status;
        this.createby = createby;
        this.createdate = createdate;
       
    }
    
    public CoStudentchoicefilling(String neetrollnumber,int neetrank) {
        this.seid.setNeetrollnumber(neetrollnumber);
        this.seid.setNeetrank(neetrank);
    }


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public CoStudentenrollment getSeid() {
        return seid;
    }

    public void setSeid(CoStudentenrollment seid) {
        this.seid = seid;
    }

    public EduLmsInstituteReg getInid() {
        return inid;
    }

    public void setInid(EduLmsInstituteReg inid) {
        this.inid = inid;
    }

    public TbLmsCategoryMstr getAssignedcategory() {
        return assignedcategory;
    }

    public void setAssignedcategory(TbLmsCategoryMstr assignedcategory) {
        this.assignedcategory = assignedcategory;
    }

    public int getCommtype() {
		return commtype;
	}

	public void setCommtype(int commtype) {
		this.commtype = commtype;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoStudentchoicefilling)) {
            return false;
        }
        CoStudentchoicefilling other = (CoStudentchoicefilling) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoStudentchoicefilling[ sid=" + sid + " ]";
    }
    
}
