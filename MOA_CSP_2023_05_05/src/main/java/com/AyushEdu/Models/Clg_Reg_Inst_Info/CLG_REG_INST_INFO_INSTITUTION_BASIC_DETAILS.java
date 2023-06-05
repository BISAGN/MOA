package com.AyushEdu.Models.Clg_Reg_Inst_Info;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_inst_info_institution_basic_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS {
	
	
	private int id;
	private String address_line1;
	private String address_line2;
	private int state_id;
	private String city;
	private int pincode;
	private BigInteger college_phone_no;
	private BigInteger hospital_phone_no;
	private BigInteger mobile_no;
	private String fax_no;
	private String email_id;
	private String website;
	private int institute_type;
	private String name_of_managing_body;
	
	private String name_of_management_contact;
	private String mngt_address_line1;
	private String mngt_address_line2;
	private String mngt_city;
	private int mngt_state;
	private BigInteger mngt_office_phone_no;
	private BigInteger mngt_residence_phone_no;
	private BigInteger mngt_mobile_no;
	private String mngt_email_id;
	private String name_of_society;
	private String society_reg_no;
	private String year_of_establish_society;
	private Date dop_state_govn;
	private Date dop_central_govn;
	private String name_of_uni_affilate;
	private Date doa_university;
	private String year_of_establish_college;
	private Date doc_affilation_university;
	private int fax_code;
	private int mang_office_code;
	private int mang_residence_code;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int userid;
	private int status;
	private int inst_id;
	private Date doc_last_aff_university;
	
	private String reg_certi_of_society;
	private String copy_of_society;
	private String undertaking_letter_furnish_bank_guarantee;
	private String noc_doc_state_gov;
	private String affiliaion_doc_concern_uni;
	
	private String uni_app_letter;
	private String inspection_last_academic_yr;
	private int district_inst_add;
	private int mngt_district;
	private int college_phone_code;
	private int hospital_phone_code;
	
	
	
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public BigInteger getCollege_phone_no() {
		return college_phone_no;
	}
	public void setCollege_phone_no(BigInteger college_phone_no) {
		this.college_phone_no = college_phone_no;
	}
	public BigInteger getHospital_phone_no() {
		return hospital_phone_no;
	}
	public void setHospital_phone_no(BigInteger hospital_phone_no) {
		this.hospital_phone_no = hospital_phone_no;
	}
	public BigInteger getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(BigInteger mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getFax_no() {
		return fax_no;
	}
	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getInstitute_type() {
		return institute_type;
	}
	public void setInstitute_type(int institute_type) {
		this.institute_type = institute_type;
	}
	public String getName_of_managing_body() {
		return name_of_managing_body;
	}
	public void setName_of_managing_body(String name_of_managing_body) {
		this.name_of_managing_body = name_of_managing_body;
	}
	public String getName_of_management_contact() {
		return name_of_management_contact;
	}
	public void setName_of_management_contact(String name_of_management_contact) {
		this.name_of_management_contact = name_of_management_contact;
	}
	public String getMngt_address_line1() {
		return mngt_address_line1;
	}
	public void setMngt_address_line1(String mngt_address_line1) {
		this.mngt_address_line1 = mngt_address_line1;
	}
	public String getMngt_address_line2() {
		return mngt_address_line2;
	}
	public void setMngt_address_line2(String mngt_address_line2) {
		this.mngt_address_line2 = mngt_address_line2;
	}
	

	public int getMngt_state() {
		return mngt_state;
	}
	public void setMngt_state(int mngt_state) {
		this.mngt_state = mngt_state;
	}
	public String getMngt_city() {
		return mngt_city;
	}
	public void setMngt_city(String mngt_city) {
		this.mngt_city = mngt_city;
	}
	public BigInteger getMngt_office_phone_no() {
		return mngt_office_phone_no;
	}
	public void setMngt_office_phone_no(BigInteger mngt_office_phone_no) {
		this.mngt_office_phone_no = mngt_office_phone_no;
	}
	public BigInteger getMngt_residence_phone_no() {
		return mngt_residence_phone_no;
	}
	public void setMngt_residence_phone_no(BigInteger mngt_residence_phone_no) {
		this.mngt_residence_phone_no = mngt_residence_phone_no;
	}
	public BigInteger getMngt_mobile_no() {
		return mngt_mobile_no;
	}
	public void setMngt_mobile_no(BigInteger mngt_mobile_no) {
		this.mngt_mobile_no = mngt_mobile_no;
	}
	public String getMngt_email_id() {
		return mngt_email_id;
	}
	public void setMngt_email_id(String mngt_email_id) {
		this.mngt_email_id = mngt_email_id;
	}
	public String getName_of_society() {
		return name_of_society;
	}
	public void setName_of_society(String name_of_society) {
		this.name_of_society = name_of_society;
	}
	public String getSociety_reg_no() {
		return society_reg_no;
	}
	public void setSociety_reg_no(String society_reg_no) {
		this.society_reg_no = society_reg_no;
	}
	public String getYear_of_establish_society() {
		return year_of_establish_society;
	}
	public void setYear_of_establish_society(String year_of_establish_society) {
		this.year_of_establish_society = year_of_establish_society;
	}
	public Date getDop_state_govn() {
		return dop_state_govn;
	}
	public void setDop_state_govn(Date dop_state_govn) {
		this.dop_state_govn = dop_state_govn;
	}
	public Date getDop_central_govn() {
		return dop_central_govn;
	}
	public void setDop_central_govn(Date dop_central_govn) {
		this.dop_central_govn = dop_central_govn;
	}
	public String getName_of_uni_affilate() {
		return name_of_uni_affilate;
	}
	public void setName_of_uni_affilate(String name_of_uni_affilate) {
		this.name_of_uni_affilate = name_of_uni_affilate;
	}
	public Date getDoa_university() {
		return doa_university;
	}
	public void setDoa_university(Date doa_university) {
		this.doa_university = doa_university;
	}
	public String getYear_of_establish_college() {
		return year_of_establish_college;
	}
	public void setYear_of_establish_college(String year_of_establish_college) {
		this.year_of_establish_college = year_of_establish_college;
	}
	public Date getDoc_affilation_university() {
		return doc_affilation_university;
	}
	public void setDoc_affilation_university(Date doc_affilation_university) {
		this.doc_affilation_university = doc_affilation_university;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	public int getFax_code() {
		return fax_code;
	}
	public void setFax_code(int fax_code) {
		this.fax_code = fax_code;
	}
	public int getMang_office_code() {
		return mang_office_code;
	}
	public void setMang_office_code(int mang_office_code) {
		this.mang_office_code = mang_office_code;
	}
	public int getMang_residence_code() {
		return mang_residence_code;
	}
	public void setMang_residence_code(int mang_residence_code) {
		this.mang_residence_code = mang_residence_code;
	}
	public Date getDoc_last_aff_university() {
		return doc_last_aff_university;
	}
	public void setDoc_last_aff_university(Date doc_last_aff_university) {
		this.doc_last_aff_university = doc_last_aff_university;
	}
	public String getReg_certi_of_society() {
		return reg_certi_of_society;
	}
	public void setReg_certi_of_society(String reg_certi_of_society) {
		this.reg_certi_of_society = reg_certi_of_society;
	}
	public String getCopy_of_society() {
		return copy_of_society;
	}
	public void setCopy_of_society(String copy_of_society) {
		this.copy_of_society = copy_of_society;
	}
	public String getUndertaking_letter_furnish_bank_guarantee() {
		return undertaking_letter_furnish_bank_guarantee;
	}
	public void setUndertaking_letter_furnish_bank_guarantee(String undertaking_letter_furnish_bank_guarantee) {
		this.undertaking_letter_furnish_bank_guarantee = undertaking_letter_furnish_bank_guarantee;
	}
	public String getNoc_doc_state_gov() {
		return noc_doc_state_gov;
	}
	public void setNoc_doc_state_gov(String noc_doc_state_gov) {
		this.noc_doc_state_gov = noc_doc_state_gov;
	}
	public String getAffiliaion_doc_concern_uni() {
		return affiliaion_doc_concern_uni;
	}
	public void setAffiliaion_doc_concern_uni(String affiliaion_doc_concern_uni) {
		this.affiliaion_doc_concern_uni = affiliaion_doc_concern_uni;
	}
	public String getUni_app_letter() {
		return uni_app_letter;
	}
	public void setUni_app_letter(String uni_app_letter) {
		this.uni_app_letter = uni_app_letter;
	}
	public String getInspection_last_academic_yr() {
		return inspection_last_academic_yr;
	}
	public void setInspection_last_academic_yr(String inspection_last_academic_yr) {
		this.inspection_last_academic_yr = inspection_last_academic_yr;
	}
	

	public int getDistrict_inst_add() {
		return district_inst_add;
	}
	public void setDistrict_inst_add(int district_inst_add) {
		this.district_inst_add = district_inst_add;
	}
	public int getMngt_district() {
		return mngt_district;
	}
	public void setMngt_district(int mngt_district) {
		this.mngt_district = mngt_district;
	}
	public int getCollege_phone_code() {
		return college_phone_code;
	}
	public void setCollege_phone_code(int college_phone_code) {
		this.college_phone_code = college_phone_code;
	}
	public int getHospital_phone_code() {
		return hospital_phone_code;
	}
	public void setHospital_phone_code(int hospital_phone_code) {
		this.hospital_phone_code = hospital_phone_code;
	}
	
	
	

	
	

}
