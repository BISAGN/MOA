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
@Table(name = "edu_cc_tb_nch_t4_learning_object_child", 
uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

public class CC_TB_NCH_T4_LEARNING_OBJECT_CHILD {

	private int id;
	private int p_id;
	private String generic_compte;
	private String subject_area;
	private int millers_knows;
	private String specific_compet;
	private String spec_learn_object;
	private int blooms_domain;
	private int guilberts_level;
	private int must_know_dknow_nk;
	private int tl_mthd_med;
	private int formtive_assessmt;
	private int sumtive_assessmt;
	private String integ_horivtspi;
	private int status;
	private int nch_term;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getGeneric_compte() {
		return generic_compte;
	}
	public void setGeneric_compte(String generic_compte) {
		this.generic_compte = generic_compte;
	}
	public String getSubject_area() {
		return subject_area;
	}
	public void setSubject_area(String subject_area) {
		this.subject_area = subject_area;
	}
	public int getMillers_knows() {
		return millers_knows;
	}
	public void setMillers_knows(int millers_knows) {
		this.millers_knows = millers_knows;
	}
	public String getSpecific_compet() {
		return specific_compet;
	}
	public void setSpecific_compet(String specific_compet) {
		this.specific_compet = specific_compet;
	}
	public String getSpec_learn_object() {
		return spec_learn_object;
	}
	public void setSpec_learn_object(String spec_learn_object) {
		this.spec_learn_object = spec_learn_object;
	}
	public int getBlooms_domain() {
		return blooms_domain;
	}
	public void setBlooms_domain(int blooms_domain) {
		this.blooms_domain = blooms_domain;
	}
	
	public int getGuilberts_level() {
		return guilberts_level;
	}
	public void setGuilberts_level(int guilberts_level) {
		this.guilberts_level = guilberts_level;
	}
	public int getMust_know_dknow_nk() {
		return must_know_dknow_nk;
	}
	public void setMust_know_dknow_nk(int must_know_dknow_nk) {
		this.must_know_dknow_nk = must_know_dknow_nk;
	}
	public int getTl_mthd_med() {
		return tl_mthd_med;
	}
	public void setTl_mthd_med(int tl_mthd_med) {
		this.tl_mthd_med = tl_mthd_med;
	}
	public int getFormtive_assessmt() {
		return formtive_assessmt;
	}
	public void setFormtive_assessmt(int formtive_assessmt) {
		this.formtive_assessmt = formtive_assessmt;
	}
	public int getSumtive_assessmt() {
		return sumtive_assessmt;
	}
	public void setSumtive_assessmt(int sumtive_assessmt) {
		this.sumtive_assessmt = sumtive_assessmt;
	}
	public String getInteg_horivtspi() {
		return integ_horivtspi;
	}
	public void setInteg_horivtspi(String integ_horivtspi) {
		this.integ_horivtspi = integ_horivtspi;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNch_term() {
		return nch_term;
	}
	public void setNch_term(int nch_term) {
		this.nch_term = nch_term;
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
