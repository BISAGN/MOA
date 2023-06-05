package com.AyushEdu.Models.Curriculum;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_cc_summary_teach_hrs_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_CC_SUMMARY_TEACH_HRS_CHILD {

	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private Integer hours_type; 
	private Integer lecture_type; 
	private Integer paper; 
	private Integer no_of_hours; 
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private Integer status;
	private Integer p_id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getHours_type() {
		return hours_type;
	}
	public void setHours_type(Integer hours_type) {
		this.hours_type = hours_type;
	}
	public Integer getLecture_type() {
		return lecture_type;
	}
	public void setLecture_type(Integer lecture_type) {
		this.lecture_type = lecture_type;
	}
	public Integer getPaper() {
		return paper;
	}
	public void setPaper(Integer paper) {
		this.paper = paper;
	}
	public Integer getNo_of_hours() {
		return no_of_hours;
	}
	public void setNo_of_hours(Integer no_of_hours) {
		this.no_of_hours = no_of_hours;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	} 
	
	
	
	 
	
	
	
}
