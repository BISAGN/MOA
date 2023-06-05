package com.AyushEdu.Models.Alumni;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_alum_sign_up", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_ALUM_SIGN_UP {
	
	private int id;
	private int institute;
	private String name;
	private String email;
	private String mob_no;
	private String year_leave;
	private String address;
	private String present_status;
	private String pre_wrk_plc;
	private String area_intrst;
	private String nostalgia;
	private String intern_certi;
	private Date created_date;
	private String created_by;
	private String modified_by;
	private Date modified_date;
	private int status;
	private String aadhar_no;
	
	private int country_id;
	private int state_id;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInstitute() {
		return institute;
	}
	public void setInstitute(int institute) {
		this.institute = institute;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMob_no() {
		return mob_no;
	}
	public void setMob_no(String mob_no) {
		this.mob_no = mob_no;
	}
	public String getYear_leave() {
		return year_leave;
	}
	public void setYear_leave(String year_leave) {
		this.year_leave = year_leave;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPresent_status() {
		return present_status;
	}
	public void setPresent_status(String present_status) {
		this.present_status = present_status;
	}
	public String getPre_wrk_plc() {
		return pre_wrk_plc;
	}
	public void setPre_wrk_plc(String pre_wrk_plc) {
		this.pre_wrk_plc = pre_wrk_plc;
	}
	public String getArea_intrst() {
		return area_intrst;
	}
	public void setArea_intrst(String area_intrst) {
		this.area_intrst = area_intrst;
	}
	public String getNostalgia() {
		return nostalgia;
	}
	public void setNostalgia(String nostalgia) {
		this.nostalgia = nostalgia;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
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
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getIntern_certi() {
		return intern_certi;
	}
	public void setIntern_certi(String intern_certi) {
		this.intern_certi = intern_certi;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

}
