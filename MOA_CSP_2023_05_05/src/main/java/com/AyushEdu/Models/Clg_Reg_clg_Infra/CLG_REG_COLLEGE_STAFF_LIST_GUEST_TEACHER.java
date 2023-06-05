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
@Table(name="clg_reg_college_staff_list_guest_teacher", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER {
	
	private int id;
	private int s_id;
	private int institute_id;
	private int guest_teacher_id;
	private String guest_teacher_doc;
	private String guest_teacher_remark;
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
	public int getGuest_teacher_id() {
		return guest_teacher_id;
	}
	public void setGuest_teacher_id(int guest_teacher_id) {
		this.guest_teacher_id = guest_teacher_id;
	}
	public String getGuest_teacher_doc() {
		return guest_teacher_doc;
	}
	public void setGuest_teacher_doc(String guest_teacher_doc) {
		this.guest_teacher_doc = guest_teacher_doc;
	}
	public String getGuest_teacher_remark() {
		return guest_teacher_remark;
	}
	public void setGuest_teacher_remark(String guest_teacher_remark) {
		this.guest_teacher_remark = guest_teacher_remark;
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
