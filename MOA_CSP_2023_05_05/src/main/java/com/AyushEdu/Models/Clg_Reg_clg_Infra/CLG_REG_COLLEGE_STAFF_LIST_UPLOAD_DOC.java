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
@Table(name="clg_reg_college_staff_list_upload_doc", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC {
	
	private int id;
	private int s_id;
	private int institute_id;
	private String full_time_teacher_affidavit;
	private String guest_teacher_affidavit;
	private String miscellaneous_doc;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int declaration_check;
	
	
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
	public String getFull_time_teacher_affidavit() {
		return full_time_teacher_affidavit;
	}
	public void setFull_time_teacher_affidavit(String full_time_teacher_affidavit) {
		this.full_time_teacher_affidavit = full_time_teacher_affidavit;
	}
	public String getGuest_teacher_affidavit() {
		return guest_teacher_affidavit;
	}
	public void setGuest_teacher_affidavit(String guest_teacher_affidavit) {
		this.guest_teacher_affidavit = guest_teacher_affidavit;
	}
	public String getMiscellaneous_doc() {
		return miscellaneous_doc;
	}
	public void setMiscellaneous_doc(String miscellaneous_doc) {
		this.miscellaneous_doc = miscellaneous_doc;
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
	public int getDeclaration_check() {
		return declaration_check;
	}
	public void setDeclaration_check(int declaration_check) {
		this.declaration_check = declaration_check;
	}
	
	
}
