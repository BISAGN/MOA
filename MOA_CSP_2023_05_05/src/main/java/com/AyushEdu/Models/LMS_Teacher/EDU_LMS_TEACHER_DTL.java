package com.AyushEdu.Models.LMS_Teacher;

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
@Table(name="tb_nch_add_teacher_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_LMS_TEACHER_DTL {
	
	private int id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String gender;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_birth;
	private String father_name;
	private String mother_name;
	private String spouse_name;
	private String mobile_no;
	private String email;
	private String aadhar_no;
	private String pan_no;
	private String present_add_line1;
	private String present_add_line2;
	private String present_village;
	private int present_state;
	private int present_district;
	private int present_pincode;
	private String present_phn_no;
	
	private String per_add_line1;
	private String per_add_line2;
	private String per_village;
	private int per_state;
	private int per_district;
	private int per_pincode;
	private String per_phn_no;
	
	private String state_reg_no;
	private String state_board_name;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_reg;
	private String central_reg_no;
	private String cand_prefix;
	
	private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	 
	 private String academic_quali;
	 private String subject;
	 private String ayush_id;
	 private String teacher_code;
	 private int university_userid;
	 private int principal_userid;
	 private int user_id;
	 private int sub_quali_degree;
	 private int sub_deg_course;
	 
	 private String registration_type;
	 private String direct_reg_no;
	 private Date direct_date_of_reg;
	 private String name_of_department;
	 
	 private String adjunct_registration_no;
	 private String adjunct_state_no;
	 private int status;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date state_validity_upto;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date direct_validity_upto;
	 
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date cs_date_of_reg;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date other_date_of_reg;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date other_validity_upto;
	 
	 private int principal_status;
	 
	 private int institute_id;
	 private int university_id;
	 private String current_designation;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_joining;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nature_of_appointment;
	

	 

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getSpouse_name() {
		return spouse_name;
	}
	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public String getPresent_add_line1() {
		return present_add_line1;
	}
	public void setPresent_add_line1(String present_add_line1) {
		this.present_add_line1 = present_add_line1;
	}
	public String getPresent_add_line2() {
		return present_add_line2;
	}
	public void setPresent_add_line2(String present_add_line2) {
		this.present_add_line2 = present_add_line2;
	}
	public String getPresent_village() {
		return present_village;
	}
	public void setPresent_village(String present_village) {
		this.present_village = present_village;
	}
	
	public int getPresent_state() {
		return present_state;
	}
	public void setPresent_state(int present_state) {
		this.present_state = present_state;
	}
	public int getPresent_district() {
		return present_district;
	}
	public void setPresent_district(int present_district) {
		this.present_district = present_district;
	}
	public int getPresent_pincode() {
		return present_pincode;
	}
	public void setPresent_pincode(int present_pincode) {
		this.present_pincode = present_pincode;
	}
	public String getPresent_phn_no() {
		return present_phn_no;
	}
	public void setPresent_phn_no(String present_phn_no) {
		this.present_phn_no = present_phn_no;
	}
	public String getPer_add_line1() {
		return per_add_line1;
	}
	public void setPer_add_line1(String per_add_line1) {
		this.per_add_line1 = per_add_line1;
	}
	public String getPer_add_line2() {
		return per_add_line2;
	}
	public void setPer_add_line2(String per_add_line2) {
		this.per_add_line2 = per_add_line2;
	}
	public String getPer_village() {
		return per_village;
	}
	public void setPer_village(String per_village) {
		this.per_village = per_village;
	}
	public int getPer_state() {
		return per_state;
	}
	public void setPer_state(int per_state) {
		this.per_state = per_state;
	}
	public int getPer_district() {
		return per_district;
	}
	public void setPer_district(int per_district) {
		this.per_district = per_district;
	}
	public int getPer_pincode() {
		return per_pincode;
	}
	public void setPer_pincode(int per_pincode) {
		this.per_pincode = per_pincode;
	}
	public String getPer_phn_no() {
		return per_phn_no;
	}
	public void setPer_phn_no(String per_phn_no) {
		this.per_phn_no = per_phn_no;
	}
	public String getState_reg_no() {
		return state_reg_no;
	}
	public void setState_reg_no(String state_reg_no) {
		this.state_reg_no = state_reg_no;
	}
	public String getState_board_name() {
		return state_board_name;
	}
	public void setState_board_name(String state_board_name) {
		this.state_board_name = state_board_name;
	}
	public Date getDate_of_reg() {
		return date_of_reg;
	}
	public void setDate_of_reg(Date date_of_reg) {
		this.date_of_reg = date_of_reg;
	}
	public String getCentral_reg_no() {
		return central_reg_no;
	}
	public void setCentral_reg_no(String central_reg_no) {
		this.central_reg_no = central_reg_no;
	}
	public String getCand_prefix() {
		return cand_prefix;
	}
	public void setCand_prefix(String cand_prefix) {
		this.cand_prefix = cand_prefix;
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
	public String getAcademic_quali() {
		return academic_quali;
	}
	public void setAcademic_quali(String academic_quali) {
		this.academic_quali = academic_quali;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAyush_id() {
		return ayush_id;
	}
	public void setAyush_id(String ayush_id) {
		this.ayush_id = ayush_id;
	}
	public String getTeacher_code() {
		return teacher_code;
	}
	public void setTeacher_code(String teacher_code) {
		this.teacher_code = teacher_code;
	}
	public int getUniversity_userid() {
		return university_userid;
	}
	public void setUniversity_userid(int university_userid) {
		this.university_userid = university_userid;
	}
	public int getPrincipal_userid() {
		return principal_userid;
	}
	public void setPrincipal_userid(int principal_userid) {
		this.principal_userid = principal_userid;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getSub_quali_degree() {
		return sub_quali_degree;
	}
	public void setSub_quali_degree(int sub_quali_degree) {
		this.sub_quali_degree = sub_quali_degree;
	}
	public int getSub_deg_course() {
		return sub_deg_course;
	}
	public void setSub_deg_course(int sub_deg_course) {
		this.sub_deg_course = sub_deg_course;
	}
	public String getDirect_reg_no() {
		return direct_reg_no;
	}
	public void setDirect_reg_no(String direct_reg_no) {
		this.direct_reg_no = direct_reg_no;
	}
	public Date getDirect_date_of_reg() {
		return direct_date_of_reg;
	}
	public void setDirect_date_of_reg(Date direct_date_of_reg) {
		this.direct_date_of_reg = direct_date_of_reg;
	}
	public String getName_of_department() {
		return name_of_department;
	}
	public void setName_of_department(String name_of_department) {
		this.name_of_department = name_of_department;
	}
	public String getRegistration_type() {
		return registration_type;
	}
	public void setRegistration_type(String registration_type) {
		this.registration_type = registration_type;
	}
	public String getAdjunct_registration_no() {
		return adjunct_registration_no;
	}
	public void setAdjunct_registration_no(String adjunct_registration_no) {
		this.adjunct_registration_no = adjunct_registration_no;
	}
	public String getAdjunct_state_no() {
		return adjunct_state_no;
	}
	public void setAdjunct_state_no(String adjunct_state_no) {
		this.adjunct_state_no = adjunct_state_no;
	}
	public Date getState_validity_upto() {
		return state_validity_upto;
	}
	public void setState_validity_upto(Date state_validity_upto) {
		this.state_validity_upto = state_validity_upto;
	}
	public Date getDirect_validity_upto() {
		return direct_validity_upto;
	}
	public void setDirect_validity_upto(Date direct_validity_upto) {
		this.direct_validity_upto = direct_validity_upto;
	}
	public Date getCs_date_of_reg() {
		return cs_date_of_reg;
	}
	public void setCs_date_of_reg(Date cs_date_of_reg) {
		this.cs_date_of_reg = cs_date_of_reg;
	}
	public Date getOther_date_of_reg() {
		return other_date_of_reg;
	}
	public void setOther_date_of_reg(Date other_date_of_reg) {
		this.other_date_of_reg = other_date_of_reg;
	}
	public Date getOther_validity_upto() {
		return other_validity_upto;
	}
	public void setOther_validity_upto(Date other_validity_upto) {
		this.other_validity_upto = other_validity_upto;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPrincipal_status() {
		return principal_status;
	}
	public void setPrincipal_status(int principal_status) {
		this.principal_status = principal_status;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	 public String getCurrent_designation() {
		return current_designation;
	}
	public void setCurrent_designation(String current_designation) {
		this.current_designation = current_designation;
	}
	public Date getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public Date getNature_of_appointment() {
		return nature_of_appointment;
	}
	public void setNature_of_appointment(Date nature_of_appointment) {
		this.nature_of_appointment = nature_of_appointment;
	}


}
