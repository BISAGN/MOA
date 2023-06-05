/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AyushEdu.Models.Counselling_Institute;

import com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;
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
@Table(name = "co_seatallocationmatrix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoSeatallocationmatrix.findAll", query = "SELECT c FROM CoSeatallocationmatrix c"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCid", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.cid = :cid"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByTotalseat", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.totalseat = :totalseat"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCentralCounPercentage", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.centralCounPercentage = :centralCounPercentage"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCentralSeat", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.centralSeat = :centralSeat"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByStateCounPercentage", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.stateCounPercentage = :stateCounPercentage"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByStateSeat", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.stateSeat = :stateSeat"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCatPercentage", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.catPercentage = :catPercentage"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCatSeat", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.catSeat = :catSeat"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByStatus", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.status = :status"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCreateby", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.createby = :createby"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByCreatedate", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByModifyby", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.modifyby = :modifyby"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByModifydate", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.modifydate = :modifydate"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByYear", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.year = :year"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByTransferedSeats", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.transferedSeats = :transferedSeats"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByTransferBy", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.transferBy = :transferBy"),
    @NamedQuery(name = "CoSeatallocationmatrix.findByTransferDate", query = "SELECT c FROM CoSeatallocationmatrix c WHERE c.transferDate = :transferDate")})
public class CoSeatallocationmatrix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cid")
    private Integer cid;
    @Basic(optional = false)
    @Column(name = "totalseat")
    private int totalseat;
    @Basic(optional = false)
    @Column(name = "central_coun_percentage")
    private double centralCounPercentage;
    @Basic(optional = false)
    @Column(name = "central_seat")
    private int centralSeat;
    @Basic(optional = false)
    @Column(name = "state_coun_percentage")
    private double stateCounPercentage;
    @Basic(optional = false)
    @Column(name = "state_seat")
    private int stateSeat;
    @Basic(optional = false)
    @Column(name = "cat_percentage")
    private double catPercentage;
    @Basic(optional = false)
    @Column(name = "cat_seat")
    private int catSeat;
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
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @Column(name = "transfered_seats")
    private int transferedSeats;
    @Column(name = "transfer_by")
    private Integer transferBy;
    @Column(name = "transfer_status")
    private String transfer_status;
    
    
    @Column(name = "transfer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;
    @JoinColumn(name = "insid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EduLmsInstituteReg insid;
    @JoinColumn(name = "catid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TbLmsCategoryMstr catid;

    public CoSeatallocationmatrix() {
    }

    public CoSeatallocationmatrix(Integer cid) {
        this.cid = cid;
    }

    public CoSeatallocationmatrix(Integer cid, int totalseat, double centralCounPercentage, int centralSeat, double stateCounPercentage, int stateSeat, double catPercentage, int catSeat, String status, int createby, Date createdate, int transferedSeats) {
        this.cid = cid;
        this.totalseat = totalseat;
        this.centralCounPercentage = centralCounPercentage;
        this.centralSeat = centralSeat;
        this.stateCounPercentage = stateCounPercentage;
        this.stateSeat = stateSeat;
        this.catPercentage = catPercentage;
        this.catSeat = catSeat;
        this.status = status;
        this.createby = createby;
        this.createdate = createdate;
        this.transferedSeats = transferedSeats;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public int getTotalseat() {
        return totalseat;
    }

    public void setTotalseat(int totalseat) {
        this.totalseat = totalseat;
    }

    public double getCentralCounPercentage() {
        return centralCounPercentage;
    }

    public void setCentralCounPercentage(double centralCounPercentage) {
        this.centralCounPercentage = centralCounPercentage;
    }

    public int getCentralSeat() {
        return centralSeat;
    }

    public void setCentralSeat(int centralSeat) {
        this.centralSeat = centralSeat;
    }

    public double getStateCounPercentage() {
        return stateCounPercentage;
    }

    public void setStateCounPercentage(double stateCounPercentage) {
        this.stateCounPercentage = stateCounPercentage;
    }

    public int getStateSeat() {
        return stateSeat;
    }

    public void setStateSeat(int stateSeat) {
        this.stateSeat = stateSeat;
    }

    public double getCatPercentage() {
        return catPercentage;
    }

    public void setCatPercentage(double catPercentage) {
        this.catPercentage = catPercentage;
    }

    public int getCatSeat() {
        return catSeat;
    }

    public void setCatSeat(int catSeat) {
        this.catSeat = catSeat;
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

    public int getTransferedSeats() {
        return transferedSeats;
    }

    public void setTransferedSeats(int transferedSeats) {
        this.transferedSeats = transferedSeats;
    }

    public Integer getTransferBy() {
        return transferBy;
    }

    public void setTransferBy(Integer transferBy) {
        this.transferBy = transferBy;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public EduLmsInstituteReg getInsid() {
        return insid;
    }

    public void setInsid(EduLmsInstituteReg insid) {
        this.insid = insid;
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
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    public String getTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(String transfer_status) {
		this.transfer_status = transfer_status;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoSeatallocationmatrix)) {
            return false;
        }
        CoSeatallocationmatrix other = (CoSeatallocationmatrix) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.CoSeatallocationmatrix[ cid=" + cid + " ]";
    }
    
}
