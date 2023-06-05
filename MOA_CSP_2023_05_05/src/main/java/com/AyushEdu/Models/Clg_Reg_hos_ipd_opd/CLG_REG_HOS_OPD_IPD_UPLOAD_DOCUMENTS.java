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
@Table(name="clg_reg_hos_opd_ipd_upload_documents", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS {
	private int id;
	private int p_id;
	private int sp_id;
	private int dep_id;
	
	private String add_diet_ecg_doc;
	private String xray_usg_opdipd;
	private String register_opdipd;
	private String medi_stock_opdipd;
	private String last_inveopdipd;
	
	private int status;
	private int institute_id;
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
	public String getAdd_diet_ecg_doc() {
		return add_diet_ecg_doc;
	}
	public void setAdd_diet_ecg_doc(String add_diet_ecg_doc) {
		this.add_diet_ecg_doc = add_diet_ecg_doc;
	}
	public String getXray_usg_opdipd() {
		return xray_usg_opdipd;
	}
	public void setXray_usg_opdipd(String xray_usg_opdipd) {
		this.xray_usg_opdipd = xray_usg_opdipd;
	}
	public String getRegister_opdipd() {
		return register_opdipd;
	}
	public void setRegister_opdipd(String register_opdipd) {
		this.register_opdipd = register_opdipd;
	}
	public String getMedi_stock_opdipd() {
		return medi_stock_opdipd;
	}
	public void setMedi_stock_opdipd(String medi_stock_opdipd) {
		this.medi_stock_opdipd = medi_stock_opdipd;
	}
	public String getLast_inveopdipd() {
		return last_inveopdipd;
	}
	public void setLast_inveopdipd(String last_inveopdipd) {
		this.last_inveopdipd = last_inveopdipd;
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
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	
	
}
