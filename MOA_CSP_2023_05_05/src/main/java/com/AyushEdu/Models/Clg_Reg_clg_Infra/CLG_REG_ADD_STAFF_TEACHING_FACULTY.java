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
@Table(name="clg_reg_add_staff_teaching_faculty", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_ADD_STAFF_TEACHING_FACULTY {
	
	private int id;
	private int s_id;
	private int institute_id;
	private int prefix_id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private Date date_of_appoinment;
	private String nature_of_appoinment;
	private String emp_id;
	private String emp_department;
	private String emp_qualification;
	private String emp_pay_scale;
	private String emp_designation;
	private String pan_no;
	private String aadhar_no;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private String ugpgcheck;
	private String attachment;
	private String reg_authority;
	private String reg_type;
	private String reg_no;
	
	
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
	public int getPrefix_id() {
		return prefix_id;
	}
	public void setPrefix_id(int prefix_id) {
		this.prefix_id = prefix_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getDate_of_appoinment() {
		return date_of_appoinment;
	}
	public void setDate_of_appoinment(Date date_of_appoinment) {
		this.date_of_appoinment = date_of_appoinment;
	}
	public String getNature_of_appoinment() {
		return nature_of_appoinment;
	}
	public void setNature_of_appoinment(String nature_of_appoinment) {
		this.nature_of_appoinment = nature_of_appoinment;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_department() {
		return emp_department;
	}
	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}
	public String getEmp_qualification() {
		return emp_qualification;
	}
	public void setEmp_qualification(String emp_qualification) {
		this.emp_qualification = emp_qualification;
	}
	public String getEmp_pay_scale() {
		return emp_pay_scale;
	}
	public void setEmp_pay_scale(String emp_pay_scale) {
		this.emp_pay_scale = emp_pay_scale;
	}
	public String getEmp_designation() {
		return emp_designation;
	}
	public void setEmp_designation(String emp_designation) {
		this.emp_designation = emp_designation;
	}
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
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
	public String getUgpgcheck() {
		return ugpgcheck;
	}
	public void setUgpgcheck(String ugpgcheck) {
		this.ugpgcheck = ugpgcheck;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getReg_authority() {
		return reg_authority;
	}
	public void setReg_authority(String reg_authority) {
		this.reg_authority = reg_authority;
	}
	public String getReg_type() {
		return reg_type;
	}
	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	
	
}
