package com.AyushEdu.Models.Clg_Reg_Department_Equipments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_dept_equipment_homeopathic_pharmacy_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD {
	
	
	private int id;
	private int p_id;
	private String no_species_planted;
	private String no_pots;
	private int irrigation_facility;
	private int educational_tour_conducted;
	private int inst_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
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
	public String getNo_species_planted() {
		return no_species_planted;
	}
	public void setNo_species_planted(String no_species_planted) {
		this.no_species_planted = no_species_planted;
	}
	public String getNo_pots() {
		return no_pots;
	}
	public void setNo_pots(String no_pots) {
		this.no_pots = no_pots;
	}
	public int getIrrigation_facility() {
		return irrigation_facility;
	}
	public void setIrrigation_facility(int irrigation_facility) {
		this.irrigation_facility = irrigation_facility;
	}
	public int getEducational_tour_conducted() {
		return educational_tour_conducted;
	}
	public void setEducational_tour_conducted(int educational_tour_conducted) {
		this.educational_tour_conducted = educational_tour_conducted;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
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
