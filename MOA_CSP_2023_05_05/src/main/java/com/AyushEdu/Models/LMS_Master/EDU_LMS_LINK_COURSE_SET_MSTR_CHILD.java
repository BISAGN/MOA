package com.AyushEdu.Models.LMS_Master;

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
@Table(name = "edu_lms_link_course_set_mstr_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class EDU_LMS_LINK_COURSE_SET_MSTR_CHILD {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	 private int id ;
	 private int p_id ;
	 private int set_id;
	 private int course_id;
	 private String set_demo_video;
	 private String created_by;
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	 private Date created_date;
	 private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	 private Date modified_date;
	
	
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
	public int getSet_id() {
		return set_id;
	}
	public void setSet_id(int set_id) {
		this.set_id = set_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	public String getSet_demo_video() {
		return set_demo_video;
	}
	public void setSet_demo_video(String set_demo_video) {
		this.set_demo_video = set_demo_video;
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
