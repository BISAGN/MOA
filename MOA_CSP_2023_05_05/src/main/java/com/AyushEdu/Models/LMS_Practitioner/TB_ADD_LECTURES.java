package com.AyushEdu.Models.LMS_Practitioner;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_add_lectures", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_ADD_LECTURES {
	
	private int id;
	private String created_by;
	private String modified_by;
	private Date created_date;
	private Date modified_date;
	private int system;
	private int degree;
	private Date from_date;
	private Date to_date;
	private String lec_video;
	private String lec_pdf;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
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
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getLec_video() {
		return lec_video;
	}
	public void setLec_video(String lec_video) {
		this.lec_video = lec_video;
	}
	public String getLec_pdf() {
		return lec_pdf;
	}
	public void setLec_pdf(String lec_pdf) {
		this.lec_pdf = lec_pdf;
	}
	

}
