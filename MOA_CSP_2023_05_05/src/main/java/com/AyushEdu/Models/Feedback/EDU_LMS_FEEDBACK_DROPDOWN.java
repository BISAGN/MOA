package com.AyushEdu.Models.Feedback;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tb_lms_feedback_dropdown", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_LMS_FEEDBACK_DROPDOWN {
	
	

	private int id;
	private String dropdown_name;
	private String dropdown_subname;
	private String dropdown_list;
	private String dropdown_alignment;
	private int required;
	
	private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
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
	public String getDropdown_name() {
		return dropdown_name;
	}
	public void setDropdown_name(String dropdown_name) {
		this.dropdown_name = dropdown_name;
	}
	public String getDropdown_subname() {
		return dropdown_subname;
	}
	public void setDropdown_subname(String dropdown_subname) {
		this.dropdown_subname = dropdown_subname;
	}
	public String getDropdown_list() {
		return dropdown_list;
	}
	public void setDropdown_list(String dropdown_list) {
		this.dropdown_list = dropdown_list;
	}
	public String getDropdown_alignment() {
		return dropdown_alignment;
	}
	public void setDropdown_alignment(String dropdown_alignment) {
		this.dropdown_alignment = dropdown_alignment;
	}
	public int getRequired() {
		return required;
	}
	public void setRequired(int required) {
		this.required = required;
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
