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
@Table(name = "co_roundgeneration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoRoundgeneration.findAll", query = "SELECT c FROM CoRoundgeneration c"),
    @NamedQuery(name = "CoRoundgeneration.findByRid", query = "SELECT c FROM CoRoundgeneration c WHERE c.rid = :rid"),
    @NamedQuery(name = "CoRoundgeneration.findByCommtype", query = "SELECT c FROM CoRoundgeneration c WHERE c.commtype = :commtype"),
    @NamedQuery(name = "CoRoundgeneration.findByRolename", query = "SELECT c FROM CoRoundgeneration c WHERE c.rolename = :rolename"),
    @NamedQuery(name = "CoRoundgeneration.findByStartdate", query = "SELECT c FROM CoRoundgeneration c WHERE c.startdate = :startdate"),
    @NamedQuery(name = "CoRoundgeneration.findByEnddate", query = "SELECT c FROM CoRoundgeneration c WHERE c.enddate = :enddate"),
    @NamedQuery(name = "CoRoundgeneration.findByMeritdate", query = "SELECT c FROM CoRoundgeneration c WHERE c.meritdate = :meritdate"),
    @NamedQuery(name = "CoRoundgeneration.findByStatus", query = "SELECT c FROM CoRoundgeneration c WHERE c.status = :status"),
    @NamedQuery(name = "CoRoundgeneration.findByCreateby", query = "SELECT c FROM CoRoundgeneration c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoRoundgeneration.findByCreatedate", query = "SELECT c FROM CoRoundgeneration c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CoRoundgeneration.findByModifyby", query = "SELECT c FROM CoRoundgeneration c WHERE c.modifyby = :modifyby"),
    @NamedQuery(name = "CoRoundgeneration.findByModifydate", query = "SELECT c FROM CoRoundgeneration c WHERE c.modifydate = :modifydate")})
public class CoRoundgeneration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rid")
    private Integer rid;
    @Basic(optional = false)
    @Column(name = "commtype")
    private int commtype;
    @Basic(optional = false)
    @Column(name = "rolename")
    private String rolename;
    @Basic(optional = false)
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Basic(optional = false)
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Basic(optional = false)
    @Column(name = "meritdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meritdate;
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
    @Column(name = "modifyby")
    private Integer modifyby;
    @Column(name = "modifydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifydate;
    @Column(name = "resultdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resultdate;
    @Column(name = "resultenddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resultenddate;
    
    @Column(name = "regstartdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regstartdate;
    
    @Column(name = "regenddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regenddate;
    
    
    @Column(name = "year")
    private String year;
    
    @Column(name = "round")
    private int round;

    public CoRoundgeneration() {
    }

    public CoRoundgeneration(Integer rid) {
        this.rid = rid;
    }

    public CoRoundgeneration(Integer rid, int commtype, String rolename, Date startdate, Date enddate, Date meritdate, String status, int createby, Date createdate) {
        this.rid = rid;
        this.commtype = commtype;
        this.rolename = rolename;
        this.startdate = startdate;
        this.enddate = enddate;
        this.meritdate = meritdate;
        this.status = status;
        this.createby = createby;
        this.createdate = createdate;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getMeritdate() {
        return meritdate;
    }

    public void setMeritdate(Date meritdate) {
        this.meritdate = meritdate;
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

    public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Date getResultdate() {
		return resultdate;
	}

	public void setResultdate(Date resultdate) {
		this.resultdate = resultdate;
	}

	public Date getResultenddate() {
		return resultenddate;
	}

	public void setResultenddate(Date resultenddate) {
		this.resultenddate = resultenddate;
	}

	public Date getRegstartdate() {
		return regstartdate;
	}

	public void setRegstartdate(Date regstartdate) {
		this.regstartdate = regstartdate;
	}

	public Date getRegenddate() {
		return regenddate;
	}

	public void setRegenddate(Date regenddate) {
		this.regenddate = regenddate;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoRoundgeneration)) {
            return false;
        }
        CoRoundgeneration other = (CoRoundgeneration) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoRoundgeneration[ rid=" + rid + " ]";
    }
    
}
