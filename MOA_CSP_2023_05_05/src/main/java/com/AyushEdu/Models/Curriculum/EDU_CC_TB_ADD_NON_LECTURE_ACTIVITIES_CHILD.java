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
@Table(name = "edu_cc_tb_add_non_lecture_activities_child", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_CHILD {

	private int id;
	private int p_id;
	private String non_lecture_tl_method;
	private int no_of_activities;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	public String getNon_lecture_tl_method() {
		return non_lecture_tl_method;
	}
	public void setNon_lecture_tl_method(String non_lecture_tl_method) {
		this.non_lecture_tl_method = non_lecture_tl_method;
	}
	public int getNo_of_activities() {
		return no_of_activities;
	}
	public void setNo_of_activities(int no_of_activities) {
		this.no_of_activities = no_of_activities;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
}
