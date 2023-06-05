package com.AyushEdu.Models.Registration.Postgraduate;

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
@Table(name = "edu_pg_gradu_examname_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_PG_GRADU_EXAMNAME {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	private int id;
	private String name_of_the_exam;
	private Integer system_id;
	private Integer degree_id;
	private int status;
	private Date created_date;
	private String created_by;
	private String modified_by;
	private Date modified_date;
	public Date getCreated_date() {
		return created_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_the_exam() {
		return name_of_the_exam;
	}
	public void setName_of_the_exam(String name_of_the_exam) {
		this.name_of_the_exam = name_of_the_exam;
	}
	
	public Integer getSystem_id() {
		return system_id;
	}
	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}
	public Integer getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(Integer degree_id) {
		this.degree_id = degree_id;
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
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}


}
	