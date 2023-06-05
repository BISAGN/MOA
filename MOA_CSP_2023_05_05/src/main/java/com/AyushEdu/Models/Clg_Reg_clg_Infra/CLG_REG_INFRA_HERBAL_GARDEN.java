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
@Table(name="clg_reg_infra_herbal_garden", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_HERBAL_GARDEN {
	
	private int id;
	private int p_id;
	private String total_area;
	private int total_cultivated_species;
	private String plant_species_list;
	private String herbal_garden_list;
	private int irrigation_facility;
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
	public String getTotal_area() {
		return total_area;
	}
	public void setTotal_area(String total_area) {
		this.total_area = total_area;
	}
	public int getTotal_cultivated_species() {
		return total_cultivated_species;
	}
	public void setTotal_cultivated_species(int total_cultivated_species) {
		this.total_cultivated_species = total_cultivated_species;
	}
	public String getPlant_species_list() {
		return plant_species_list;
	}
	public void setPlant_species_list(String plant_species_list) {
		this.plant_species_list = plant_species_list;
	}
	public String getHerbal_garden_list() {
		return herbal_garden_list;
	}
	public void setHerbal_garden_list(String herbal_garden_list) {
		this.herbal_garden_list = herbal_garden_list;
	}
	public int getIrrigation_facility() {
		return irrigation_facility;
	}
	public void setIrrigation_facility(int irrigation_facility) {
		this.irrigation_facility = irrigation_facility;
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

