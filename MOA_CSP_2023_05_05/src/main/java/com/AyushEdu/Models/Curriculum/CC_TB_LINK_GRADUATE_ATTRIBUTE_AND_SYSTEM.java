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
@Table(name = "edu_cc_tb_link_graduate_attribute_and_system", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM {
	
	private int id;
	private int system;
	private int degree;
	private int graduate_attribute;
	private int status;
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
	public int getSystem() {
		return system;
	}
	public void setSystem(int system) {
		this.system = system;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getGraduate_attribute() {
		return graduate_attribute;
	}
	public void setGraduate_attribute(int graduate_attribute) {
		this.graduate_attribute = graduate_attribute;
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
	


}
