package com.AyushEdu.Models.Regulation;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reg_nch_formaa", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class REG_NCH_FORM_AA {
	
	private int id;
	private int reg_state = 0;
	private int to_state = 0;
	private int status=0;
 	private int p_id=0;
	private int user_id=0;
	private String address ;
	private String st_temp_no ;
	
	
	@DateTimeFormat(pattern = "DD/MM/YYYY")
	private Date from_date;
	@DateTimeFormat(pattern = "DD/MM/YYYY")
	private Date to_date;
 
	
	
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private int pay_status=0; 
	private String reject_remarks ;
	 	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReg_state() {
		return reg_state;
	}
	public void setReg_state(int reg_state) {
		this.reg_state = reg_state;
	}
	public int getTo_state() {
		return to_state;
	}
	public void setTo_state(int to_state) {
		this.to_state = to_state;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getSt_temp_no() {
		return st_temp_no;
	}
	public void setSt_temp_no(String st_temp_no) {
		this.st_temp_no = st_temp_no;
	}
	public int getPay_status() {
		return pay_status;
	}
	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	 
	 
	
	
	

}
