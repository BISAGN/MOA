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
@Table(name="clg_reg_other_hos_dtl_operation_theatre", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE {
	
	private int id;
	private int s_id;
	private int p_id;
	private String operation_theatre;
	private String air_condition;
	private String pre_operative_room;
	private String sterilization_room;
	private String wash_room;
	private String other_facility;
	private String fumigation_facility;
	private int total_operations;
	private int total_minor_procedures;
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
	public String getOperation_theatre() {
		return operation_theatre;
	}
	public void setOperation_theatre(String operation_theatre) {
		this.operation_theatre = operation_theatre;
	}
	public String getAir_condition() {
		return air_condition;
	}
	public void setAir_condition(String air_condition) {
		this.air_condition = air_condition;
	}
	public String getPre_operative_room() {
		return pre_operative_room;
	}
	public void setPre_operative_room(String pre_operative_room) {
		this.pre_operative_room = pre_operative_room;
	}
	public String getSterilization_room() {
		return sterilization_room;
	}
	public void setSterilization_room(String sterilization_room) {
		this.sterilization_room = sterilization_room;
	}
	public String getWash_room() {
		return wash_room;
	}
	public void setWash_room(String wash_room) {
		this.wash_room = wash_room;
	}
	public String getOther_facility() {
		return other_facility;
	}
	public void setOther_facility(String other_facility) {
		this.other_facility = other_facility;
	}
	public String getFumigation_facility() {
		return fumigation_facility;
	}
	public void setFumigation_facility(String fumigation_facility) {
		this.fumigation_facility = fumigation_facility;
	}
	public int getTotal_operations() {
		return total_operations;
	}
	public void setTotal_operations(int total_operations) {
		this.total_operations = total_operations;
	}
	public int getTotal_minor_procedures() {
		return total_minor_procedures;
	}
	public void setTotal_minor_procedures(int total_minor_procedures) {
		this.total_minor_procedures = total_minor_procedures;
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
