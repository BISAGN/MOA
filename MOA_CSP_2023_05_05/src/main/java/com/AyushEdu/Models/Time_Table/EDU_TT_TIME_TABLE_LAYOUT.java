package com.AyushEdu.Models.Time_Table;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_tt_time_table_layout", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_TT_TIME_TABLE_LAYOUT {
	
	
	private int id;
	private int degree;
	private int course;
	private int faculty;
	private int classroom;
	private int professional;
	private int institute_id;
	private String start_time;
	private String end_time;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String ttday;
	private String lec_type;
	private int extra_class_status;

	public int getExtra_class_status() {
		return extra_class_status;
	}
	public void setExtra_class_status(int extra_class_status) {
		this.extra_class_status = extra_class_status;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public int getFaculty() {
		return faculty;
	}
	public void setFaculty(int faculty) {
		this.faculty = faculty;
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
	public int getClassroom() {
		return classroom;
	}
	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	public int getProfessional() {
		return professional;
	}
	public void setProfessional(int professional) {
		this.professional = professional;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getLec_type() {
		return lec_type;
	}
	public void setLec_type(String lec_type) {
		this.lec_type = lec_type;
	}
	public String getTtday() {
		return ttday;
	}
	public void setTtday(String ttday) {
		this.ttday = ttday;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
		
}