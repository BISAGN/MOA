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
@Table(name="clg_reg_add_staff_non_teaching_staff", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_ADD_STAFF_NON_TEACHING_STAFF {
	
	private int id;
	private int s_id;
	private int institute_id;
	private int non_prefix_id;
	private String non_first_name;
	private String non_middle_name;
	private String non_last_name;
	private Date non_date_of_appoinment;
	private String non_nature_of_appoinment;
	private String non_emp_id;
	private String non_emp_department;
	private String non_emp_qualification;
	private String non_emp_pay_scale;
	private String non_emp_designation;
	private String non_pan_no;
	private String non_aadhar_no;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private String non_appoinment_letter;
	private String non_exe_certi;
	private String non_teaching_attachment;
	
	
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
	public int getNon_prefix_id() {
		return non_prefix_id;
	}
	public void setNon_prefix_id(int non_prefix_id) {
		this.non_prefix_id = non_prefix_id;
	}
	public String getNon_first_name() {
		return non_first_name;
	}
	public void setNon_first_name(String non_first_name) {
		this.non_first_name = non_first_name;
	}
	public String getNon_middle_name() {
		return non_middle_name;
	}
	public void setNon_middle_name(String non_middle_name) {
		this.non_middle_name = non_middle_name;
	}
	public String getNon_last_name() {
		return non_last_name;
	}
	public void setNon_last_name(String non_last_name) {
		this.non_last_name = non_last_name;
	}
	public Date getNon_date_of_appoinment() {
		return non_date_of_appoinment;
	}
	public void setNon_date_of_appoinment(Date non_date_of_appoinment) {
		this.non_date_of_appoinment = non_date_of_appoinment;
	}
	public String getNon_nature_of_appoinment() {
		return non_nature_of_appoinment;
	}
	public void setNon_nature_of_appoinment(String non_nature_of_appoinment) {
		this.non_nature_of_appoinment = non_nature_of_appoinment;
	}
	public String getNon_emp_id() {
		return non_emp_id;
	}
	public void setNon_emp_id(String non_emp_id) {
		this.non_emp_id = non_emp_id;
	}
	public String getNon_emp_department() {
		return non_emp_department;
	}
	public void setNon_emp_department(String non_emp_department) {
		this.non_emp_department = non_emp_department;
	}
	public String getNon_emp_qualification() {
		return non_emp_qualification;
	}
	public void setNon_emp_qualification(String non_emp_qualification) {
		this.non_emp_qualification = non_emp_qualification;
	}
	public String getNon_emp_pay_scale() {
		return non_emp_pay_scale;
	}
	public void setNon_emp_pay_scale(String non_emp_pay_scale) {
		this.non_emp_pay_scale = non_emp_pay_scale;
	}
	public String getNon_emp_designation() {
		return non_emp_designation;
	}
	public void setNon_emp_designation(String non_emp_designation) {
		this.non_emp_designation = non_emp_designation;
	}
	public String getNon_pan_no() {
		return non_pan_no;
	}
	public void setNon_pan_no(String non_pan_no) {
		this.non_pan_no = non_pan_no;
	}
	public String getNon_aadhar_no() {
		return non_aadhar_no;
	}
	public void setNon_aadhar_no(String non_aadhar_no) {
		this.non_aadhar_no = non_aadhar_no;
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
	public String getNon_appoinment_letter() {
		return non_appoinment_letter;
	}
	public void setNon_appoinment_letter(String non_appoinment_letter) {
		this.non_appoinment_letter = non_appoinment_letter;
	}
	public String getNon_exe_certi() {
		return non_exe_certi;
	}
	public void setNon_exe_certi(String non_exe_certi) {
		this.non_exe_certi = non_exe_certi;
	}
	public String getNon_teaching_attachment() {
		return non_teaching_attachment;
	}
	public void setNon_teaching_attachment(String non_teaching_attachment) {
		this.non_teaching_attachment = non_teaching_attachment;
	}
	
}
