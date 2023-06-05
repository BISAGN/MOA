package com.AyushEdu.Models.Clg_Reg_Inst_Info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_inst_info_intake_capacity", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY {
	
	
	private int id;
	private int p_id;
	private String intake_capacity;
	private String year;
	private int permission;
	private int inst_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int amitted_seat;
	private int permitted_seat;
	private int sanctioned_seat;
	private int govt_quota_ug;
	private int mang_quota_ug;
	private String court_order;
	private String last_student;
	private Date last_stu_add_date;
	private int appeared_stu_ug;
	private int passed_stu_ug;
	
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
	public String getIntake_capacity() {
		return intake_capacity;
	}
	public void setIntake_capacity(String intake_capacity) {
		this.intake_capacity = intake_capacity;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
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
	public int getAmitted_seat() {
		return amitted_seat;
	}
	public void setAmitted_seat(int amitted_seat) {
		this.amitted_seat = amitted_seat;
	}
	public int getPermitted_seat() {
		return permitted_seat;
	}
	public void setPermitted_seat(int permitted_seat) {
		this.permitted_seat = permitted_seat;
	}
	public int getSanctioned_seat() {
		return sanctioned_seat;
	}
	public void setSanctioned_seat(int sanctioned_seat) {
		this.sanctioned_seat = sanctioned_seat;
	}
	public int getGovt_quota_ug() {
		return govt_quota_ug;
	}
	public void setGovt_quota_ug(int govt_quota_ug) {
		this.govt_quota_ug = govt_quota_ug;
	}
	public int getMang_quota_ug() {
		return mang_quota_ug;
	}
	public void setMang_quota_ug(int mang_quota_ug) {
		this.mang_quota_ug = mang_quota_ug;
	}
	public String getCourt_order() {
		return court_order;
	}
	public void setCourt_order(String court_order) {
		this.court_order = court_order;
	}
	public String getLast_student() {
		return last_student;
	}
	public void setLast_student(String last_student) {
		this.last_student = last_student;
	}
	public Date getLast_stu_add_date() {
		return last_stu_add_date;
	}
	public void setLast_stu_add_date(Date last_stu_add_date) {
		this.last_stu_add_date = last_stu_add_date;
	}
	public int getAppeared_stu_ug() {
		return appeared_stu_ug;
	}
	public void setAppeared_stu_ug(int appeared_stu_ug) {
		this.appeared_stu_ug = appeared_stu_ug;
	}
	public int getPassed_stu_ug() {
		return passed_stu_ug;
	}
	public void setPassed_stu_ug(int passed_stu_ug) {
		this.passed_stu_ug = passed_stu_ug;
	}
	
	
	

}
