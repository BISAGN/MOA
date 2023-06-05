package com.AyushEdu.Models.B_Regulation;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_b_regulation", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_EDU_B_REGULATION {

	private int id;
	private String nrh_en_no;
	private String first_name;
	private int gender ;
	private int pre_district;
	private int pre_state;
	private BigInteger pre_pincode;
	private int per_district=0;
	private int per_state=0;
	private BigInteger per_pincode;
	private String aadhaar_no;
	private int status=0;
	private BigInteger mo_no;
	private BigInteger alt_mo_no1;
	private BigInteger alt_mo_no2;
	private String email_id;
	private String alt_email_id1;
	private String alt_email_id2;
	
	

	private Date dob;
	private int nationality;
	private int degree;
//	private String University;
	//private String month_year;
	private Date date_of_reg;
//	private String name_reg;
//	private String reg_renew_permanent;
//	private String name_of_hospital_teaching;
	private int reg_no=0;
	private String photo_path;
	private String father_name;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int del_status;
	private int institute_status;
	private int state_status;	
	private int nrh_status;
	private int user_id;
	private String reg_auth;
	private String photo_first_cer_path;
	private int registration_for_type;
	private String valid_up_to;
	private String pre_address_details1;
	private String pre_address_details2;
	private String pre_address_details3;
	private String per_address_details1;
	private String per_address_details2;
	private String per_address_details3;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNrh_en_no() {
		return nrh_en_no;
	}
	public void setNrh_en_no(String nrh_en_no) {
		this.nrh_en_no = nrh_en_no;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getPre_district() {
		return pre_district;
	}
	public void setPre_district(int pre_district) {
		this.pre_district = pre_district;
	}
	public int getPre_state() {
		return pre_state;
	}
	public void setPre_state(int pre_state) {
		this.pre_state = pre_state;
	}
	public BigInteger getPre_pincode() {
		return pre_pincode;
	}
	public void setPre_pincode(BigInteger pre_pincode) {
		this.pre_pincode = pre_pincode;
	}
	public int getPer_district() {
		return per_district;
	}
	public void setPer_district(int per_district) {
		this.per_district = per_district;
	}
	public int getPer_state() {
		return per_state;
	}
	public void setPer_state(int per_state) {
		this.per_state = per_state;
	}
	public BigInteger getPer_pincode() {
		return per_pincode;
	}
	public void setPer_pincode(BigInteger per_pincode) {
		this.per_pincode = per_pincode;
	}
	public String getAadhaar_no() {
		return aadhaar_no;
	}
	public void setAadhaar_no(String aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigInteger getMo_no() {
		return mo_no;
	}
	public void setMo_no(BigInteger mo_no) {
		this.mo_no = mo_no;
	}
	public BigInteger getAlt_mo_no1() {
		return alt_mo_no1;
	}
	public void setAlt_mo_no1(BigInteger alt_mo_no1) {
		this.alt_mo_no1 = alt_mo_no1;
	}
	public BigInteger getAlt_mo_no2() {
		return alt_mo_no2;
	}
	public void setAlt_mo_no2(BigInteger alt_mo_no2) {
		this.alt_mo_no2 = alt_mo_no2;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getAlt_email_id1() {
		return alt_email_id1;
	}
	public void setAlt_email_id1(String alt_email_id1) {
		this.alt_email_id1 = alt_email_id1;
	}
	public String getAlt_email_id2() {
		return alt_email_id2;
	}
	public void setAlt_email_id2(String alt_email_id2) {
		this.alt_email_id2 = alt_email_id2;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getNationality() {
		return nationality;
	}
	public void setNationality(int nationality) {
		this.nationality = nationality;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public Date getDate_of_reg() {
		return date_of_reg;
	}
	public void setDate_of_reg(Date date_of_reg) {
		this.date_of_reg = date_of_reg;
	}
	public int getReg_no() {
		return reg_no;
	}
	public void setReg_no(int reg_no) {
		this.reg_no = reg_no;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
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
	public int getDel_status() {
		return del_status;
	}
	public void setDel_status(int del_status) {
		this.del_status = del_status;
	}
	public int getInstitute_status() {
		return institute_status;
	}
	public void setInstitute_status(int institute_status) {
		this.institute_status = institute_status;
	}
	public int getState_status() {
		return state_status;
	}
	public void setState_status(int state_status) {
		this.state_status = state_status;
	}
	public int getNrh_status() {
		return nrh_status;
	}
	public void setNrh_status(int nrh_status) {
		this.nrh_status = nrh_status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getReg_auth() {
		return reg_auth;
	}
	public void setReg_auth(String reg_auth) {
		this.reg_auth = reg_auth;
	}
	public String getPhoto_first_cer_path() {
		return photo_first_cer_path;
	}
	public void setPhoto_first_cer_path(String photo_first_cer_path) {
		this.photo_first_cer_path = photo_first_cer_path;
	}
	public int getRegistration_for_type() {
		return registration_for_type;
	}
	public void setRegistration_for_type(int registration_for_type) {
		this.registration_for_type = registration_for_type;
	}
	public String getValid_up_to() {
		return valid_up_to;
	}
	public void setValid_up_to(String valid_up_to) {
		this.valid_up_to = valid_up_to;
	}
	public String getPre_address_details1() {
		return pre_address_details1;
	}
	public void setPre_address_details1(String pre_address_details1) {
		this.pre_address_details1 = pre_address_details1;
	}
	public String getPre_address_details2() {
		return pre_address_details2;
	}
	public void setPre_address_details2(String pre_address_details2) {
		this.pre_address_details2 = pre_address_details2;
	}
	public String getPre_address_details3() {
		return pre_address_details3;
	}
	public void setPre_address_details3(String pre_address_details3) {
		this.pre_address_details3 = pre_address_details3;
	}
	public String getPer_address_details1() {
		return per_address_details1;
	}
	public void setPer_address_details1(String per_address_details1) {
		this.per_address_details1 = per_address_details1;
	}
	public String getPer_address_details2() {
		return per_address_details2;
	}
	public void setPer_address_details2(String per_address_details2) {
		this.per_address_details2 = per_address_details2;
	}
	public String getPer_address_details3() {
		return per_address_details3;
	}
	public void setPer_address_details3(String per_address_details3) {
		this.per_address_details3 = per_address_details3;
	}
	
	
 
}
