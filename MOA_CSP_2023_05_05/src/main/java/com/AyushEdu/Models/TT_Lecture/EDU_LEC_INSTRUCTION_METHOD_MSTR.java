package com.AyushEdu.Models.TT_Lecture;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lec_instruction_method_mstr", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class EDU_LEC_INSTRUCTION_METHOD_MSTR {
	
	private int id;
	private String instructional_method_name;
	private String modified_by;
	private Date modified_dt;
	private String created_by;
	private Date created_dt;
	private String created_role;
	
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstructional_method_name() {
		return instructional_method_name;
	}
	public void setInstructional_method_name(String instructional_method_name) {
		this.instructional_method_name = instructional_method_name;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getCreated_role() {
		return created_role;
	}
	public void setCreated_role(String created_role) {
		this.created_role = created_role;
	}

}

