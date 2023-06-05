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
@Table(name="clg_reg_other_hos_dtl_clinical_laboratory", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY {
	
	private int id;
	private int s_id;
	private int p_id;
	private int hematological_test;
	private int bio_chemical_test;
	private int serological_test;
	private int microbiological_test;
	private int orthology_test;
	private int total_investigation;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
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
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getHematological_test() {
		return hematological_test;
	}
	public void setHematological_test(int hematological_test) {
		this.hematological_test = hematological_test;
	}
	public int getBio_chemical_test() {
		return bio_chemical_test;
	}
	public void setBio_chemical_test(int bio_chemical_test) {
		this.bio_chemical_test = bio_chemical_test;
	}
	public int getSerological_test() {
		return serological_test;
	}
	public void setSerological_test(int serological_test) {
		this.serological_test = serological_test;
	}
	public int getMicrobiological_test() {
		return microbiological_test;
	}
	public void setMicrobiological_test(int microbiological_test) {
		this.microbiological_test = microbiological_test;
	}
	public int getOrthology_test() {
		return orthology_test;
	}
	public void setOrthology_test(int orthology_test) {
		this.orthology_test = orthology_test;
	}
	public int getTotal_investigation() {
		return total_investigation;
	}
	public void setTotal_investigation(int total_investigation) {
		this.total_investigation = total_investigation;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
	
}
