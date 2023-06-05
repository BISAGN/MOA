package com.AyushEdu.Models.B_Regulation;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_b_regulation_medical_degree_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_B_REGULATION_MEDICAL_DEGREE_CHILD {

	private int id;
	private int type_of_degree;
	private String name_of_institute;
	private String attachment_path ;
	private String month_and_year_of_degree;
	private int regulation_p_id;
	private Date created_date ;
	private String created_by;
	private Date modified_date ;
	private String modified_by;
	private int degree_name;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType_of_degree() {
		return type_of_degree;
	}
	public void setType_of_degree(int type_of_degree) {
		this.type_of_degree = type_of_degree;
	}
	public String getName_of_institute() {
		return name_of_institute;
	}
	public void setName_of_institute(String name_of_institute) {
		this.name_of_institute = name_of_institute;
	}
	public String getAttachment_path() {
		return attachment_path;
	}
	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}
	public String getMonth_and_year_of_degree() {
		return month_and_year_of_degree;
	}
	public void setMonth_and_year_of_degree(String month_and_year_of_degree) {
		this.month_and_year_of_degree = month_and_year_of_degree;
	}
	public int getRegulation_p_id() {
		return regulation_p_id;
	}
	public void setRegulation_p_id(int regulation_p_id) {
		this.regulation_p_id = regulation_p_id;
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
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public int getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(int degree_name) {
		this.degree_name = degree_name;
	}
}
