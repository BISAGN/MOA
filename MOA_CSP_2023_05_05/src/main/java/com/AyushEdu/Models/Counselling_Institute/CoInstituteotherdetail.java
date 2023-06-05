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
@Table(name = "co_instituteotherdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoInstituteotherdetail.findAll", query = "SELECT c FROM CoInstituteotherdetail c"),
    @NamedQuery(name = "CoInstituteotherdetail.findByInfid", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.infid = :infid"),
    @NamedQuery(name = "CoInstituteotherdetail.findByTotalseat", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.totalseat = :totalseat"),
    @NamedQuery(name = "CoInstituteotherdetail.findByHostelfacility", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.hostelfacility = :hostelfacility"),
    @NamedQuery(name = "CoInstituteotherdetail.findByLibraryfacility", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.libraryfacility = :libraryfacility"),
    @NamedQuery(name = "CoInstituteotherdetail.findByFeesSubCat", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.fees_sub_cat = :fees_sub_cat"),
    @NamedQuery(name = "CoInstituteotherdetail.findByFeesvalue", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.feesvalue = :feesvalue"),
    @NamedQuery(name = "CoInstituteotherdetail.findByStatus", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.status = :status"),
    @NamedQuery(name = "CoInstituteotherdetail.findByCreateby", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoInstituteotherdetail.findByCreatedate", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CoInstituteotherdetail.findByModifyby", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.modifyby = :modifyby"),
    @NamedQuery(name = "CoInstituteotherdetail.findByModifydate", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.modifydate = :modifydate"),
	@NamedQuery(name = "CoInstituteotherdetail.findBySystemId", query = "SELECT c FROM CoInstituteotherdetail c WHERE c.systemId = :systemId")})

public class CoInstituteotherdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "infid")
    private Integer infid;
    @Column(name = "totalseat")
    private Integer totalseat;
    @Basic(optional = false)
    @Column(name = "hostelfacility")
    private boolean hostelfacility;
    @Basic(optional = false)
    @Column(name = "libraryfacility")
    private boolean libraryfacility;
    @Basic(optional = false)
    @Column(name = "fees_sub_cat")
    private String fees_sub_cat;
    @Basic(optional = false)
    @Column(name = "feesvalue")
    private double feesvalue;
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
    @JoinColumn(name = "feesid", referencedColumnName = "ftid")
    @ManyToOne(optional = false)
    private CoFeescategorytype feesid;
    @JoinColumn(name = "degreetype", referencedColumnName = "id")
    @ManyToOne
    private EduLmsDegreeCateMstr degreetype;
    @JoinColumn(name = "inid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EduLmsInstituteReg inid;
    @JoinColumn(name = "catid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TbLmsCategoryMstr catid;
    @Column(name = "system_id")
    private Integer systemId;

    public CoInstituteotherdetail() {
    }

    public CoInstituteotherdetail(Integer infid) {
        this.infid = infid;
    }

    public CoInstituteotherdetail(Integer infid, boolean hostelfacility, boolean libraryfacility, String fees_sub_cat, double feesvalue, String status, int createby, Date createdate) {
        this.infid = infid;
        this.hostelfacility = hostelfacility;
        this.libraryfacility = libraryfacility;
        this.fees_sub_cat = fees_sub_cat;
        this.feesvalue = feesvalue;
        this.status = status;
        this.createby = createby;
        this.createdate = createdate;
    }

    public Integer getInfid() {
        return infid;
    }

    public void setInfid(Integer infid) {
        this.infid = infid;
    }

    public Integer getTotalseat() {
        return totalseat;
    }

    public void setTotalseat(Integer totalseat) {
        this.totalseat = totalseat;
    }

    public boolean getHostelfacility() {
        return hostelfacility;
    }

    public void setHostelfacility(boolean hostelfacility) {
        this.hostelfacility = hostelfacility;
    }

    public boolean getLibraryfacility() {
        return libraryfacility;
    }

    public void setLibraryfacility(boolean libraryfacility) {
        this.libraryfacility = libraryfacility;
    }

    public double getFeesvalue() {
        return feesvalue;
    }

    public void setFeesvalue(double feesvalue) {
        this.feesvalue = feesvalue;
    }

    public String getFees_sub_cat() {
		return fees_sub_cat;
	}

	public void setFees_sub_cat(String fees_sub_cat) {
		this.fees_sub_cat = fees_sub_cat;
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

    public CoFeescategorytype getFeesid() {
        return feesid;
    }

    public void setFeesid(CoFeescategorytype feesid) {
        this.feesid = feesid;
    }

    public EduLmsDegreeCateMstr getDegreetype() {
        return degreetype;
    }

    public void setDegreetype(EduLmsDegreeCateMstr degreetype) {
        this.degreetype = degreetype;
    }

    public EduLmsInstituteReg getInid() {
        return inid;
    }

    public void setInid(EduLmsInstituteReg inid) {
        this.inid = inid;
    }

    public TbLmsCategoryMstr getCatid() {
        return catid;
    }

    public void setCatid(TbLmsCategoryMstr catid) {
        this.catid = catid;
    }
    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infid != null ? infid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoInstituteotherdetail)) {
            return false;
        }
        CoInstituteotherdetail other = (CoInstituteotherdetail) object;
        if ((this.infid == null && other.infid != null) || (this.infid != null && !this.infid.equals(other.infid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AyushEdu.Models.Counselling_Institute.CoInstituteotherdetail[ infid=" + infid + " ]";
    }
    
}
