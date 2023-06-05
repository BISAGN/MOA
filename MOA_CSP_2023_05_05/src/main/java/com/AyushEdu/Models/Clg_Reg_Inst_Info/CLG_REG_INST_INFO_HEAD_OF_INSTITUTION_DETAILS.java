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
@Table(name="clg_reg_inst_info_head_of_institution_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS {
	
	
		
	private int id;
	private int p_id;
	private String name_of_principal;
	private String cch_reg_no;
	private int state_reg;
	private String state_reg_no;
	private Date dob;
	private Date date_of_join_princi;
	private String professor_exp;
	private String reader_exp;
	private String lecturer_exp;
	private String demonstraror_exp;
	private String address_line1;
	private String address_line2;
	private int state_add_detai;
	private String city;
	private int pincode;
	private BigInteger office_phone_no;
	private BigInteger residence_phone_no;
	private BigInteger mobile_no;
	private String email_id;
	
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	private int head_std;
	private int headre_std;
	private String teacher_code;
	private int district_add_detai;
	
	
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getName_of_principal() {
		return name_of_principal;
	}
	public void setName_of_principal(String name_of_principal) {
		this.name_of_principal = name_of_principal;
	}
	public String getCch_reg_no() {
		return cch_reg_no;
	}
	public void setCch_reg_no(String cch_reg_no) {
		this.cch_reg_no = cch_reg_no;
	}
	public int getState_reg() {
		return state_reg;
	}
	public void setState_reg(int state_reg) {
		this.state_reg = state_reg;
	}
	public String getState_reg_no() {
		return state_reg_no;
	}
	public void setState_reg_no(String state_reg_no) {
		this.state_reg_no = state_reg_no;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDate_of_join_princi() {
		return date_of_join_princi;
	}
	public void setDate_of_join_princi(Date date_of_join_princi) {
		this.date_of_join_princi = date_of_join_princi;
	}
	public String getProfessor_exp() {
		return professor_exp;
	}
	public void setProfessor_exp(String professor_exp) {
		this.professor_exp = professor_exp;
	}
	public String getReader_exp() {
		return reader_exp;
	}
	public void setReader_exp(String reader_exp) {
		this.reader_exp = reader_exp;
	}
	public String getLecturer_exp() {
		return lecturer_exp;
	}
	public void setLecturer_exp(String lecturer_exp) {
		this.lecturer_exp = lecturer_exp;
	}
	public String getDemonstraror_exp() {
		return demonstraror_exp;
	}
	public void setDemonstraror_exp(String demonstraror_exp) {
		this.demonstraror_exp = demonstraror_exp;
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
	public int getState_add_detai() {
		return state_add_detai;
	}
	public void setState_add_detai(int state_add_detai) {
		this.state_add_detai = state_add_detai;
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
	public BigInteger getOffice_phone_no() {
		return office_phone_no;
	}
	public void setOffice_phone_no(BigInteger office_phone_no) {
		this.office_phone_no = office_phone_no;
	}
	public BigInteger getResidence_phone_no() {
		return residence_phone_no;
	}
	public void setResidence_phone_no(BigInteger residence_phone_no) {
		this.residence_phone_no = residence_phone_no;
	}
	public BigInteger getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(BigInteger mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
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
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	public int getHead_std() {
		return head_std;
	}
	public void setHead_std(int head_std) {
		this.head_std = head_std;
	}
	public int getHeadre_std() {
		return headre_std;
	}
	public void setHeadre_std(int headre_std) {
		this.headre_std = headre_std;
	}
	public String getTeacher_code() {
		return teacher_code;
	}
	public void setTeacher_code(String teacher_code) {
		this.teacher_code = teacher_code;
	}
	public int getDistrict_add_detai() {
		return district_add_detai;
	}
	public void setDistrict_add_detai(int district_add_detai) {
		this.district_add_detai = district_add_detai;
	}
	
	
	
	

	
	
	
	
	
	
	
	

}
