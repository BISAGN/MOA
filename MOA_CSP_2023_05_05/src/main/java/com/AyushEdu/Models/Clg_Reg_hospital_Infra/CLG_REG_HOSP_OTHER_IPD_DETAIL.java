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
@Table(name="clg_reg_hosp_other_ipd_detail", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_HOSP_OTHER_IPD_DETAIL {
	
	
	private int id;
	private int ipd_casulty_service;
	private String ipd_casulty_document;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institute_id;
	private int treatment_outcome_ipd;
	private String treatment_outcome_ipd_document;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public int getTreatment_outcome_ipd() {
		return treatment_outcome_ipd;
	}
	public void setTreatment_outcome_ipd(int treatment_outcome_ipd) {
		this.treatment_outcome_ipd = treatment_outcome_ipd;
	}
	public int getIpd_casulty_service() {
		return ipd_casulty_service;
	}
	public void setIpd_casulty_service(int ipd_casulty_service) {
		this.ipd_casulty_service = ipd_casulty_service;
	}
	public String getIpd_casulty_document() {
		return ipd_casulty_document;
	}
	public void setIpd_casulty_document(String ipd_casulty_document) {
		this.ipd_casulty_document = ipd_casulty_document;
	}
	public String getTreatment_outcome_ipd_document() {
		return treatment_outcome_ipd_document;
	}
	public void setTreatment_outcome_ipd_document(String treatment_outcome_ipd_document) {
		this.treatment_outcome_ipd_document = treatment_outcome_ipd_document;
	}
	
	

}
