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
@Table(name="clg_reg_add_staff_guest_faculty", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_ADD_STAFF_GUEST_FACULTY {
	
	private int id;
	private int s_id;
	private int institute_id;
	private int guest_prefix_id;
	private String guest_first_name;
	private String guest_middle_name;
	private String guest_last_name;
	private Date guest_date_of_appoinment;
	private String guest_nature_of_appoinment;
	private String guest_emp_id;
	private String guest_emp_department;
	private String guest_emp_qualification;
	private String guest_emp_pay_scale;
	private String guest_emp_designation;
	private String guest_pan_no;
	private String guest_aadhar_no;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private String appoinment_letter;
	private String exe_certi;
	private String guest_teaching_attachment;
	
	
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
	public int getGuest_prefix_id() {
		return guest_prefix_id;
	}
	public void setGuest_prefix_id(int guest_prefix_id) {
		this.guest_prefix_id = guest_prefix_id;
	}
	public String getGuest_first_name() {
		return guest_first_name;
	}
	public void setGuest_first_name(String guest_first_name) {
		this.guest_first_name = guest_first_name;
	}
	public String getGuest_middle_name() {
		return guest_middle_name;
	}
	public void setGuest_middle_name(String guest_middle_name) {
		this.guest_middle_name = guest_middle_name;
	}
	public String getGuest_last_name() {
		return guest_last_name;
	}
	public void setGuest_last_name(String guest_last_name) {
		this.guest_last_name = guest_last_name;
	}
	public Date getGuest_date_of_appoinment() {
		return guest_date_of_appoinment;
	}
	public void setGuest_date_of_appoinment(Date guest_date_of_appoinment) {
		this.guest_date_of_appoinment = guest_date_of_appoinment;
	}
	public String getGuest_nature_of_appoinment() {
		return guest_nature_of_appoinment;
	}
	public void setGuest_nature_of_appoinment(String guest_nature_of_appoinment) {
		this.guest_nature_of_appoinment = guest_nature_of_appoinment;
	}
	public String getGuest_emp_id() {
		return guest_emp_id;
	}
	public void setGuest_emp_id(String guest_emp_id) {
		this.guest_emp_id = guest_emp_id;
	}
	public String getGuest_emp_department() {
		return guest_emp_department;
	}
	public void setGuest_emp_department(String guest_emp_department) {
		this.guest_emp_department = guest_emp_department;
	}
	public String getGuest_emp_qualification() {
		return guest_emp_qualification;
	}
	public void setGuest_emp_qualification(String guest_emp_qualification) {
		this.guest_emp_qualification = guest_emp_qualification;
	}
	public String getGuest_emp_pay_scale() {
		return guest_emp_pay_scale;
	}
	public void setGuest_emp_pay_scale(String guest_emp_pay_scale) {
		this.guest_emp_pay_scale = guest_emp_pay_scale;
	}
	public String getGuest_emp_designation() {
		return guest_emp_designation;
	}
	public void setGuest_emp_designation(String guest_emp_designation) {
		this.guest_emp_designation = guest_emp_designation;
	}
	public String getGuest_pan_no() {
		return guest_pan_no;
	}
	public void setGuest_pan_no(String guest_pan_no) {
		this.guest_pan_no = guest_pan_no;
	}
	public String getGuest_aadhar_no() {
		return guest_aadhar_no;
	}
	public void setGuest_aadhar_no(String guest_aadhar_no) {
		this.guest_aadhar_no = guest_aadhar_no;
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
	public String getAppoinment_letter() {
		return appoinment_letter;
	}
	public void setAppoinment_letter(String appoinment_letter) {
		this.appoinment_letter = appoinment_letter;
	}
	public String getExe_certi() {
		return exe_certi;
	}
	public void setExe_certi(String exe_certi) {
		this.exe_certi = exe_certi;
	}
	public String getGuest_teaching_attachment() {
		return guest_teaching_attachment;
	}
	public void setGuest_teaching_attachment(String guest_teaching_attachment) {
		this.guest_teaching_attachment = guest_teaching_attachment;
	}
	
}
