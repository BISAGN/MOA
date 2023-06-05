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
@Table(name="hosp_equipments_document", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HOSP_EQUIPMENTES_UP_DOCUMENT {
	
	private int id;
	private int institute_id;
	private String sother_equip;
	private String patho_bio_equip;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSother_equip() {
		return sother_equip;
	}
	public void setSother_equip(String sother_equip) {
		this.sother_equip = sother_equip;
	}
	public String getPatho_bio_equip() {
		return patho_bio_equip;
	}
	public void setPatho_bio_equip(String patho_bio_equip) {
		this.patho_bio_equip = patho_bio_equip;
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
		
	
}
