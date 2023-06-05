package com.AyushEdu.Models.LMS_Student;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_student_course_enroll_sequence", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE {
	
	private int id;
	private int course_id;
	private int user_id;
	
	private String level_of_module;
	private int module_id;
	private int cc_id;
	private int sequence;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String time;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLevel_of_module() {
		return level_of_module;
	}
	public void setLevel_of_module(String level_of_module) {
		this.level_of_module = level_of_module;
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
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public int getCc_id() {
		return cc_id;
	}
	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
