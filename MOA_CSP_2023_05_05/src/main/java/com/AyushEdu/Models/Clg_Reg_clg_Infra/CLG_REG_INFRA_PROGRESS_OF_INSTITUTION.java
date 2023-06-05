package com.AyushEdu.Models.Clg_Reg_clg_Infra;

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
@Table(name="clg_reg_infra_progress_of_institution", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_PROGRESS_OF_INSTITUTION {
	
	private int id;
	private int p_id;
	private String cons_clg_hospital;
	private String app_teaching_staff;
	private String app_non_teaching_staff;
	private String app_paramedical;
	private String expansion_various_dept;
	private String expansion_herbal_ganden;
	private String hospital_opd;
	private String hospital_ipd;
	private String seminars;
	private String pulication_by_clg;
	private String research_activities;
	private String award_details;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
	private int institute_id;
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getCons_clg_hospital() {
		return cons_clg_hospital;
	}
	public void setCons_clg_hospital(String cons_clg_hospital) {
		this.cons_clg_hospital = cons_clg_hospital;
	}
	public String getApp_teaching_staff() {
		return app_teaching_staff;
	}
	public void setApp_teaching_staff(String app_teaching_staff) {
		this.app_teaching_staff = app_teaching_staff;
	}
	public String getApp_non_teaching_staff() {
		return app_non_teaching_staff;
	}
	public void setApp_non_teaching_staff(String app_non_teaching_staff) {
		this.app_non_teaching_staff = app_non_teaching_staff;
	}
	public String getApp_paramedical() {
		return app_paramedical;
	}
	public void setApp_paramedical(String app_paramedical) {
		this.app_paramedical = app_paramedical;
	}
	public String getExpansion_various_dept() {
		return expansion_various_dept;
	}
	public void setExpansion_various_dept(String expansion_various_dept) {
		this.expansion_various_dept = expansion_various_dept;
	}
	public String getExpansion_herbal_ganden() {
		return expansion_herbal_ganden;
	}
	public void setExpansion_herbal_ganden(String expansion_herbal_ganden) {
		this.expansion_herbal_ganden = expansion_herbal_ganden;
	}
	public String getHospital_opd() {
		return hospital_opd;
	}
	public void setHospital_opd(String hospital_opd) {
		this.hospital_opd = hospital_opd;
	}
	public String getHospital_ipd() {
		return hospital_ipd;
	}
	public void setHospital_ipd(String hospital_ipd) {
		this.hospital_ipd = hospital_ipd;
	}
	public String getSeminars() {
		return seminars;
	}
	public void setSeminars(String seminars) {
		this.seminars = seminars;
	}
	public String getPulication_by_clg() {
		return pulication_by_clg;
	}
	public void setPulication_by_clg(String pulication_by_clg) {
		this.pulication_by_clg = pulication_by_clg;
	}
	public String getResearch_activities() {
		return research_activities;
	}
	public void setResearch_activities(String research_activities) {
		this.research_activities = research_activities;
	}
	public String getAward_details() {
		return award_details;
	}
	public void setAward_details(String award_details) {
		this.award_details = award_details;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}


}
