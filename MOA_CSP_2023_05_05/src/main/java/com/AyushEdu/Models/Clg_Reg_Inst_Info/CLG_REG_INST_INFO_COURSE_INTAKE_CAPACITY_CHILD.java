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
@Table(name="clg_reg_inst_info_course_intake_capacity_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD {
	
	
	private int id;
	private int p_id;
	private String ayush_permission_letter;
	private String upload_court_order;
	private String inst_not_admit_stu_without_permission;
	private String selection_of_students;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	private String lastfiveguide;
	private String neet_score;
	private String undertakingofstudent;
	private String biometricattendance;
	private String intimatedcheck;
	private String undertakingcheck;
	
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
	public String getAyush_permission_letter() {
		return ayush_permission_letter;
	}
	public void setAyush_permission_letter(String ayush_permission_letter) {
		this.ayush_permission_letter = ayush_permission_letter;
	}
	public String getUpload_court_order() {
		return upload_court_order;
	}
	public void setUpload_court_order(String upload_court_order) {
		this.upload_court_order = upload_court_order;
	}
	public String getInst_not_admit_stu_without_permission() {
		return inst_not_admit_stu_without_permission;
	}
	public void setInst_not_admit_stu_without_permission(String inst_not_admit_stu_without_permission) {
		this.inst_not_admit_stu_without_permission = inst_not_admit_stu_without_permission;
	}
	public String getSelection_of_students() {
		return selection_of_students;
	}
	public void setSelection_of_students(String selection_of_students) {
		this.selection_of_students = selection_of_students;
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
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	public String getLastfiveguide() {
		return lastfiveguide;
	}
	public void setLastfiveguide(String lastfiveguide) {
		this.lastfiveguide = lastfiveguide;
	}
	public String getNeet_score() {
		return neet_score;
	}
	public void setNeet_score(String neet_score) {
		this.neet_score = neet_score;
	}
	public String getUndertakingofstudent() {
		return undertakingofstudent;
	}
	public void setUndertakingofstudent(String undertakingofstudent) {
		this.undertakingofstudent = undertakingofstudent;
	}
	public String getBiometricattendance() {
		return biometricattendance;
	}
	public void setBiometricattendance(String biometricattendance) {
		this.biometricattendance = biometricattendance;
	}
	public String getIntimatedcheck() {
		return intimatedcheck;
	}
	public void setIntimatedcheck(String intimatedcheck) {
		this.intimatedcheck = intimatedcheck;
	}
	public String getUndertakingcheck() {
		return undertakingcheck;
	}
	public void setUndertakingcheck(String undertakingcheck) {
		this.undertakingcheck = undertakingcheck;
	}
	
	

}
