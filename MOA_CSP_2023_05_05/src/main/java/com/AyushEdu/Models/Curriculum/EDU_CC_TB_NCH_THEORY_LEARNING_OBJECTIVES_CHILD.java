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
@Table(name = "edu_cc_tb_nch_theory_learning_object_child", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD {
	private int id;
	private int p_id;
	private String generic_competency;
	private String subject_area;
	private int millers_level;
	private String specific_competency;
	private String slo_outcome;
	private int blooms_domain;
	private int guilberts_level;
	private int mk_dk;
	private int tl_methods;
	private int form_assessment;
	private int summ_assessment;
	private String int_departments;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getGeneric_competency() {
		return generic_competency;
	}
	public void setGeneric_competency(String generic_competency) {
		this.generic_competency = generic_competency;
	}
	public String getSubject_area() {
		return subject_area;
	}
	public void setSubject_area(String subject_area) {
		this.subject_area = subject_area;
	}
	public int getMillers_level() {
		return millers_level;
	}
	public void setMillers_level(int millers_level) {
		this.millers_level = millers_level;
	}
	public String getSpecific_competency() {
		return specific_competency;
	}
	public void setSpecific_competency(String specific_competency) {
		this.specific_competency = specific_competency;
	}
	public String getSlo_outcome() {
		return slo_outcome;
	}
	public void setSlo_outcome(String slo_outcome) {
		this.slo_outcome = slo_outcome;
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
	public int getMk_dk() {
		return mk_dk;
	}
	public void setMk_dk(int mk_dk) {
		this.mk_dk = mk_dk;
	}
	public int getTl_methods() {
		return tl_methods;
	}
	public void setTl_methods(int tl_methods) {
		this.tl_methods = tl_methods;
	}
	public int getForm_assessment() {
		return form_assessment;
	}
	public void setForm_assessment(int form_assessment) {
		this.form_assessment = form_assessment;
	}
	public int getSumm_assessment() {
		return summ_assessment;
	}
	public void setSumm_assessment(int summ_assessment) {
		this.summ_assessment = summ_assessment;
	}
	public String getInt_departments() {
		return int_departments;
	}
	public void setInt_departments(String int_departments) {
		this.int_departments = int_departments;
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
