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
@Table(name="clg_reg_other_hos_dtl_maintenance_records", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS {
	
	private int id;
	private int s_id;
	private int institute_id;
	private int central_registration;
	private int opd_records;
	private int ipd_records;
	private int medical_record_room;
	private int account_section;
	private int created_by;
	private Date created_date;
	private int modified_by;
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
	public int getCentral_registration() {
		return central_registration;
	}
	public void setCentral_registration(int central_registration) {
		this.central_registration = central_registration;
	}
	public int getOpd_records() {
		return opd_records;
	}
	public void setOpd_records(int opd_records) {
		this.opd_records = opd_records;
	}
	public int getIpd_records() {
		return ipd_records;
	}
	public void setIpd_records(int ipd_records) {
		this.ipd_records = ipd_records;
	}
	public int getMedical_record_room() {
		return medical_record_room;
	}
	public void setMedical_record_room(int medical_record_room) {
		this.medical_record_room = medical_record_room;
	}
	public int getAccount_section() {
		return account_section;
	}
	public void setAccount_section(int account_section) {
		this.account_section = account_section;
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
	
}
