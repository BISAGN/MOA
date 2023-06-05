package com.AyushEdu.Models.Clg_Reg_hospital_Infra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_other_infra_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_OTHER_INFRA_DETAILS {
	
	private int id;
	private int ambulance_serv;
	private int sitting_arrangment;
	private int central_research_lab;
	private int casualty_dept;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institute_id;
	private String ambulance_document;
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmbulance_serv() {
		return ambulance_serv;
	}
	public void setAmbulance_serv(int ambulance_serv) {
		this.ambulance_serv = ambulance_serv;
	}
	public int getSitting_arrangment() {
		return sitting_arrangment;
	}
	public void setSitting_arrangment(int sitting_arrangment) {
		this.sitting_arrangment = sitting_arrangment;
	}
	public int getCentral_research_lab() {
		return central_research_lab;
	}
	public void setCentral_research_lab(int central_research_lab) {
		this.central_research_lab = central_research_lab;
	}
	public int getCasualty_dept() {
		return casualty_dept;
	}
	public void setCasualty_dept(int casualty_dept) {
		this.casualty_dept = casualty_dept;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public String getAmbulance_document() {
		return ambulance_document;
	}
	public void setAmbulance_document(String ambulance_document) {
		this.ambulance_document = ambulance_document;
	}
	
	
}
