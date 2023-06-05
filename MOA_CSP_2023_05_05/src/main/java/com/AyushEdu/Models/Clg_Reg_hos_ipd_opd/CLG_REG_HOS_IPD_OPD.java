package com.AyushEdu.Models.Clg_Reg_hos_ipd_opd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_hos_opd_ipd", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_HOS_IPD_OPD {
	private int id;
	private int p_id;
	private int sp_id;
	private int dep_id;
	
	private int jan;
	private int feb;
	private int mar;
	private int appr;
	private int may;
	private int jun;
	private int july;
	private int aug;
	private int sep;
	private int oct;
	private int nov;
	private int dec;
	private int status;
	private int institute_id;
//	private int month_total;
//	private int grand_total;
	private int total_wardsopd;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}
	public int getJan() {
		return jan;
	}
	public void setJan(int jan) {
		this.jan = jan;
	}
	public int getFeb() {
		return feb;
	}
	public void setFeb(int feb) {
		this.feb = feb;
	}
	public int getMar() {
		return mar;
	}
	public void setMar(int mar) {
		this.mar = mar;
	}
	public int getAppr() {
		return appr;
	}
	public void setAppr(int appr) {
		this.appr = appr;
	}
	public int getMay() {
		return may;
	}
	public void setMay(int may) {
		this.may = may;
	}
	public int getJun() {
		return jun;
	}
	public void setJun(int jun) {
		this.jun = jun;
	}
	public int getJuly() {
		return july;
	}
	public void setJuly(int july) {
		this.july = july;
	}
	public int getAug() {
		return aug;
	}
	public void setAug(int aug) {
		this.aug = aug;
	}
	public int getSep() {
		return sep;
	}
	public void setSep(int sep) {
		this.sep = sep;
	}
	public int getOct() {
		return oct;
	}
	public void setOct(int oct) {
		this.oct = oct;
	}
	public int getNov() {
		return nov;
	}
	public void setNov(int nov) {
		this.nov = nov;
	}
	public int getDec() {
		return dec;
	}
	public void setDec(int dec) {
		this.dec = dec;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
//	 public int getMonth_total() {
//			return month_total;
//	}
//	public void setMonth_total(int month_total) {
//		this.month_total = month_total;
//	}
//	public int getGrand_total() {
//		return grand_total;
//	}
//	public void setGrand_total(int grand_total) {
//		this.grand_total = grand_total;
//	}
	public int getTotal_wardsopd() {
		return total_wardsopd;
	}
	public void setTotal_wardsopd(int total_wardsopd) {
		this.total_wardsopd = total_wardsopd;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	
	
	
	
}
