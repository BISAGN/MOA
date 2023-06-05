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
@Table(name="clg_reg_other_hos_dtl_labour_room", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM {
	
	private int id;
	private int s_id;
	private int p_id;
	private String labour_room;
	private String antenatal_room;
	private String neonatal_care;
	private String other_facilities;
	private int total_deliveries;
	private int total_procedures;
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
	public String getLabour_room() {
		return labour_room;
	}
	public void setLabour_room(String labour_room) {
		this.labour_room = labour_room;
	}
	public String getAntenatal_room() {
		return antenatal_room;
	}
	public void setAntenatal_room(String antenatal_room) {
		this.antenatal_room = antenatal_room;
	}
	public String getNeonatal_care() {
		return neonatal_care;
	}
	public void setNeonatal_care(String neonatal_care) {
		this.neonatal_care = neonatal_care;
	}
	public String getOther_facilities() {
		return other_facilities;
	}
	public void setOther_facilities(String other_facilities) {
		this.other_facilities = other_facilities;
	}
	public int getTotal_deliveries() {
		return total_deliveries;
	}
	public void setTotal_deliveries(int total_deliveries) {
		this.total_deliveries = total_deliveries;
	}
	public int getTotal_procedures() {
		return total_procedures;
	}
	public void setTotal_procedures(int total_procedures) {
		this.total_procedures = total_procedures;
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
