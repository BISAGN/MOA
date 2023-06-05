package com.AyushEdu.Models.Time_Table;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "edu_tt_academic_details_mstr", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class EDU_TT_ACADEMIC_DETAILS_MSTR {
	
	private int id;
	private String academic_details_name;
	private String refer_code;
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
	public String getAcademic_details_name() {
		return academic_details_name;
	}
	public void setAcademic_details_name(String academic_details_name) {
		this.academic_details_name = academic_details_name;
	}
	public String getRefer_code() {
		return refer_code;
	}
	public void setRefer_code(String refer_code) {
		this.refer_code = refer_code;
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
