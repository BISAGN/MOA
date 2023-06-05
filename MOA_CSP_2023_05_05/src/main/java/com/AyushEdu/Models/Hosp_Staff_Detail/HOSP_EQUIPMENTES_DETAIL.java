package com.AyushEdu.Models.Hosp_Staff_Detail;

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
@Table(name="hosp_equipments_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HOSP_EQUIPMENTES_DETAIL {
	
	private int id;
	private int sp_id;
	private int articles;
	private int total_equipments;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private int institute_id;
//	private int p_id;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public int getArticles() {
		return articles;
	}
	public void setArticles(int articles) {
		this.articles = articles;
	}
	public int getTotal_equipments() {
		return total_equipments;
	}
	public void setTotal_equipments(int total_equipments) {
		this.total_equipments = total_equipments;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
//	public int getP_id() {
//		return p_id;
//	}
//	public void setP_id(int p_id) {
//		this.p_id = p_id;
//	}
	
	
}
