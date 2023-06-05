package com.AyushEdu.Models.Ug_Pg_Fee_Collection;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "edu_lms_fees_collect", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_FEES_COLLECT {
	
	private int id;
	//private int degree_id;
	private int term_id;
	private int student_name;
	private String academic_year;
	private int type_of_degree;
	private int degree_name;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public int getDegree_id() {
//		return degree_id;
//	}
//	public void setDegree_id(int degree_id) {
//		this.degree_id = degree_id;
//	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	
	public int getStudent_name() {
		return student_name;
	}
	public void setStudent_name(int student_name) {
		this.student_name = student_name;
	}
	public String getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
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
	public int getType_of_degree() {
		return type_of_degree;
	}
	public void setType_of_degree(int type_of_degree) {
		this.type_of_degree = type_of_degree;
	}
	public int getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(int degree_name) {
		this.degree_name = degree_name;
	}
	
	
	

}
