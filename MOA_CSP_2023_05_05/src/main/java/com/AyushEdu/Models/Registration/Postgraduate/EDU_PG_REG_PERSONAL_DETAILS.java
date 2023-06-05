package com.AyushEdu.Models.Registration.Postgraduate;

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
@Table(name="edu_pg_reg_personal_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class EDU_PG_REG_PERSONAL_DETAILS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private String first_name ;
	private String middel_name;
	private String surname;
	private String father_name;
	private String mother_name;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_birth;
	private Integer gender;
	private String mob_no;
	private String email;
	private Integer category;
	private Integer religion;
	private Integer marital_status;
	private Integer nationality;
	private String aadhar_no;
	
	private String permanent_house_no;
	private String permanent_add_line1;
	private String permanent_add_line2;
	private Integer permanent_state;
	private Integer permanent_district;
	private String permanent_village;
	private Integer permanent_pincode;
	private String permanent_lendmark;
	
	private String present_house_no;
	private String present_add_line1;
	private String present_add_line2;
	private Integer present_state;
	private Integer present_district;
	private String present_village;
	private Integer present_pincode;
	private String present_lendmark;
	
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private Integer p_id;
	
	private Integer status;
	
	
	private String corre_house_no;
	private String corre_village;
	private String corre_district;
	private Integer corre_state;
	private Integer corre_pincode;
	private String corre_lendmark;
	private String corre_add_line1;
	private String corre_add_line2;
	
	
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
	public String getMiddel_name() {
		return middel_name;
	}
	public void setMiddel_name(String middel_name) {
		this.middel_name = middel_name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getMob_no() {
		return mob_no;
	}
	public void setMob_no(String mob_no) {
		this.mob_no = mob_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getReligion() {
		return religion;
	}
	public void setReligion(Integer religion) {
		this.religion = religion;
	}
	public Integer getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(Integer marital_status) {
		this.marital_status = marital_status;
	}
	public Integer getNationality() {
		return nationality;
	}
	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	public String getPermanent_house_no() {
		return permanent_house_no;
	}
	public void setPermanent_house_no(String permanent_house_no) {
		this.permanent_house_no = permanent_house_no;
	}
	public String getPermanent_add_line1() {
		return permanent_add_line1;
	}
	public void setPermanent_add_line1(String permanent_add_line1) {
		this.permanent_add_line1 = permanent_add_line1;
	}
	public String getPermanent_add_line2() {
		return permanent_add_line2;
	}
	public void setPermanent_add_line2(String permanent_add_line2) {
		this.permanent_add_line2 = permanent_add_line2;
	}
	public Integer getPermanent_state() {
		return permanent_state;
	}
	public void setPermanent_state(Integer permanent_state) {
		this.permanent_state = permanent_state;
	}
	public Integer getPermanent_district() {
		return permanent_district;
	}
	public void setPermanent_district(Integer permanent_district) {
		this.permanent_district = permanent_district;
	}
	public String getPermanent_village() {
		return permanent_village;
	}
	public void setPermanent_village(String permanent_village) {
		this.permanent_village = permanent_village;
	}
	public Integer getPermanent_pincode() {
		return permanent_pincode;
	}
	public void setPermanent_pincode(Integer permanent_pincode) {
		this.permanent_pincode = permanent_pincode;
	}
	public String getPermanent_lendmark() {
		return permanent_lendmark;
	}
	public void setPermanent_lendmark(String permanent_lendmark) {
		this.permanent_lendmark = permanent_lendmark;
	}
	public String getPresent_house_no() {
		return present_house_no;
	}
	public void setPresent_house_no(String present_house_no) {
		this.present_house_no = present_house_no;
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
	public Integer getPresent_state() {
		return present_state;
	}
	public void setPresent_state(Integer present_state) {
		this.present_state = present_state;
	}
	public Integer getPresent_district() {
		return present_district;
	}
	public void setPresent_district(Integer present_district) {
		this.present_district = present_district;
	}
	public String getPresent_village() {
		return present_village;
	}
	public void setPresent_village(String present_village) {
		this.present_village = present_village;
	}
	public Integer getPresent_pincode() {
		return present_pincode;
	}
	public void setPresent_pincode(Integer present_pincode) {
		this.present_pincode = present_pincode;
	}
	public String getPresent_lendmark() {
		return present_lendmark;
	}
	public void setPresent_lendmark(String present_lendmark) {
		this.present_lendmark = present_lendmark;
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
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCorre_house_no() {
		return corre_house_no;
	}
	public void setCorre_house_no(String corre_house_no) {
		this.corre_house_no = corre_house_no;
	}
	public String getCorre_village() {
		return corre_village;
	}
	public void setCorre_village(String corre_village) {
		this.corre_village = corre_village;
	}
	
	public String getCorre_district() {
		return corre_district;
	}
	public void setCorre_district(String corre_district) {
		this.corre_district = corre_district;
	}
	public Integer getCorre_state() {
		return corre_state;
	}
	public void setCorre_state(Integer corre_state) {
		this.corre_state = corre_state;
	}
	public Integer getCorre_pincode() {
		return corre_pincode;
	}
	public void setCorre_pincode(Integer corre_pincode) {
		this.corre_pincode = corre_pincode;
	}
	public String getCorre_lendmark() {
		return corre_lendmark;
	}
	public void setCorre_lendmark(String corre_lendmark) {
		this.corre_lendmark = corre_lendmark;
	}
	public String getCorre_add_line1() {
		return corre_add_line1;
	}
	public void setCorre_add_line1(String corre_add_line1) {
		this.corre_add_line1 = corre_add_line1;
	}
	public String getCorre_add_line2() {
		return corre_add_line2;
	}
	public void setCorre_add_line2(String corre_add_line2) {
		this.corre_add_line2 = corre_add_line2;
	}
	

}
