package com.AyushEdu.Models.Degree_recognition_NCISM;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_medical_qua_all_state35_ncism", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_MEDICAL_QUA_ALL_STATE35 {
	
	private int id;
	private String file_no;
	private Date file_date;
	private String all_university_name;
	private String college_name ;
	private String medical_qua ;
	private String medical_abbrv ;
	private String sequence_code;
	private Date velidity_period;
    private int status;
	private int user_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String  all_state;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile_no() {
		return file_no;
	}
	public void setFile_no(String file_no) {
		this.file_no = file_no;
	}
	public Date getFile_date() {
		return file_date;
	}
	public void setFile_date(Date file_date) {
		this.file_date = file_date;
	}
	public String getAll_university_name() {
		return all_university_name;
	}
	public void setAll_university_name(String all_university_name) {
		this.all_university_name = all_university_name;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getMedical_qua() {
		return medical_qua;
	}
	public void setMedical_qua(String medical_qua) {
		this.medical_qua = medical_qua;
	}
	public String getMedical_abbrv() {
		return medical_abbrv;
	}
	public void setMedical_abbrv(String medical_abbrv) {
		this.medical_abbrv = medical_abbrv;
	}
	public String getSequence_code() {
		return sequence_code;
	}
	public void setSequence_code(String sequence_code) {
		this.sequence_code = sequence_code;
	}
	
	public Date getVelidity_period() {
		return velidity_period;
	}
	public void setVelidity_period(Date velidity_period) {
		this.velidity_period = velidity_period;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getAll_state() {
		return all_state;
	}
	public void setAll_state(String all_state) {
		this.all_state = all_state;
	}
}
