package com.AyushEdu.Models.Degree_recognition_Fellowship_Grant;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_coding_courses_homoeopathy_mstr", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR {
	
	
	private int id;
	private Double sr_no;
	private String qualification;
	private String code;
	private String modified_by;
	private Date modified_date;
	private String created_by;
	private Date created_date;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Double getSr_no() {
		return sr_no;
	}
	public void setSr_no(Double sr_no) {
		this.sr_no = sr_no;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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



}
