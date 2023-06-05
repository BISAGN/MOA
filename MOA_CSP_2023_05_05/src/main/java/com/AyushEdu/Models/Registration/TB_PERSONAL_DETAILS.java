package com.AyushEdu.Models.Registration;

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
@Table(name="edu_reg_gradu_personal_dtls", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class TB_PERSONAL_DETAILS {
	
	private int id;
	private String pers_name;
	private String pers_father_name;
	private String pers_mother_name;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pers_date_of_birth;
	private String pers_gender;
	private String pers_mob_no;
	private String pers_email;
	private String pers_category;
	private String pers_religion;
	private Integer pers_marital_status;
	private Integer pers_nationality;
	private int state_id;
	private int district_id;
	private String village;
	private String pers_aadhar_no;
	private String pers_permanent_house_no;
	private String pers_permanent_village;
	private String pers_permanent_postoffice;
	private String pers_permanent_tehsil;
	private String pers_permanent_policestation;
	private String pers_permanent_district;
	private Integer pers_permanent_state;
	private Integer pers_permanent_pincode;
	private String pers_permanent_lendmark;

	private String pers_present_house_no;
	private String pers_present_village;
	private String  pers_present_postoffice;
	private String pers_present_tehsil;
	private String pers_present_policestation;
	private String pers_present_district;
	private Integer pers_present_state;
	private Integer pers_present_pincode;
	private String pers_present_lendmark;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private int p_id;
	private Integer neet_rank;
	private Integer neet_marks;
	private String neet_percentile;
	
	private String pers_middel_name;
	private String pers_surname;
	
	private Integer cand_prifix;
//	private Integer pers_permanent_pincode;
//	private Integer pers_permanent_state;
	
	private String neet_roll_no;
	private String neet_application_no;
	private Integer status;
	
	
	private String corre_house_no;
	private String corre_village;
	private String  corre_postoffice;
	private String corre_tehsil;
	private String corre_policestation;
	private String corre_district;
	private Integer corre_state;
	private Integer corre_pincode;
	private String corre_lendmark;
	
	
//	add quota and councelling
	
	private Integer quota_id;
	private Integer counselling_authority;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPers_name() {
		return pers_name;
	}
	public void setPers_name(String pers_name) {
		this.pers_name = pers_name;
	}
	public String getPers_father_name() {
		return pers_father_name;
	}
	public void setPers_father_name(String pers_father_name) {
		this.pers_father_name = pers_father_name;
	}
	public String getPers_mother_name() {
		return pers_mother_name;
	}
	public void setPers_mother_name(String pers_mother_name) {
		this.pers_mother_name = pers_mother_name;
	}
	public Date getPers_date_of_birth() {
		return pers_date_of_birth;
	}
	public void setPers_date_of_birth(Date pers_date_of_birth) {
		this.pers_date_of_birth = pers_date_of_birth;
	}
	public String getPers_gender() {
		return pers_gender;
	}
	public void setPers_gender(String pers_gender) {
		this.pers_gender = pers_gender;
	}
	public String getPers_mob_no() {
		return pers_mob_no;
	}
	public void setPers_mob_no(String pers_mob_no) {
		this.pers_mob_no = pers_mob_no;
	}
	public String getPers_email() {
		return pers_email;
	}
	public void setPers_email(String pers_email) {
		this.pers_email = pers_email;
	}
	public String getPers_category() {
		return pers_category;
	}
	public void setPers_category(String pers_category) {
		this.pers_category = pers_category;
	}
	public String getPers_religion() {
		return pers_religion;
	}
	public void setPers_religion(String pers_religion) {
		this.pers_religion = pers_religion;
	}
	
	public Integer getPers_marital_status() {
		return pers_marital_status;
	}
	public void setPers_marital_status(Integer pers_marital_status) {
		this.pers_marital_status = pers_marital_status;
	}
	
	public Integer getPers_nationality() {
		return pers_nationality;
	}
	public void setPers_nationality(Integer pers_nationality) {
		this.pers_nationality = pers_nationality;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getPers_aadhar_no() {
		return pers_aadhar_no;
	}
	public void setPers_aadhar_no(String pers_aadhar_no) {
		this.pers_aadhar_no = pers_aadhar_no;
	}
	public String getPers_permanent_house_no() {
		return pers_permanent_house_no;
	}
	public void setPers_permanent_house_no(String pers_permanent_house_no) {
		this.pers_permanent_house_no = pers_permanent_house_no;
	}
	public String getPers_permanent_village() {
		return pers_permanent_village;
	}
	public void setPers_permanent_village(String pers_permanent_village) {
		this.pers_permanent_village = pers_permanent_village;
	}
	public String getPers_permanent_postoffice() {
		return pers_permanent_postoffice;
	}
	public void setPers_permanent_postoffice(String pers_permanent_postoffice) {
		this.pers_permanent_postoffice = pers_permanent_postoffice;
	}
	public String getPers_permanent_tehsil() {
		return pers_permanent_tehsil;
	}
	public void setPers_permanent_tehsil(String pers_permanent_tehsil) {
		this.pers_permanent_tehsil = pers_permanent_tehsil;
	}
	public String getPers_permanent_policestation() {
		return pers_permanent_policestation;
	}
	public void setPers_permanent_policestation(String pers_permanent_policestation) {
		this.pers_permanent_policestation = pers_permanent_policestation;
	}
	public String getPers_permanent_district() {
		return pers_permanent_district;
	}
	public void setPers_permanent_district(String pers_permanent_district) {
		this.pers_permanent_district = pers_permanent_district;
	}
	public Integer getPers_permanent_state() {
		return pers_permanent_state;
	}
	public void setPers_permanent_state(Integer pers_permanent_state) {
		this.pers_permanent_state = pers_permanent_state;
	}
	public Integer getPers_permanent_pincode() {
		return pers_permanent_pincode;
	}
	public void setPers_permanent_pincode(Integer pers_permanent_pincode) {
		this.pers_permanent_pincode = pers_permanent_pincode;
	}
	public String getPers_present_house_no() {
		return pers_present_house_no;
	}
	public void setPers_present_house_no(String pers_present_house_no) {
		this.pers_present_house_no = pers_present_house_no;
	}
	public String getPers_present_village() {
		return pers_present_village;
	}
	public void setPers_present_village(String pers_present_village) {
		this.pers_present_village = pers_present_village;
	}
	public String getPers_present_postoffice() {
		return pers_present_postoffice;
	}
	public void setPers_present_postoffice(String pers_present_postoffice) {
		this.pers_present_postoffice = pers_present_postoffice;
	}
	public String getPers_present_tehsil() {
		return pers_present_tehsil;
	}
	public void setPers_present_tehsil(String pers_present_tehsil) {
		this.pers_present_tehsil = pers_present_tehsil;
	}
	public String getPers_present_policestation() {
		return pers_present_policestation;
	}
	public void setPers_present_policestation(String pers_present_policestation) {
		this.pers_present_policestation = pers_present_policestation;
	}
	public String getPers_present_district() {
		return pers_present_district;
	}
	public void setPers_present_district(String pers_present_district) {
		this.pers_present_district = pers_present_district;
	}
	public Integer getPers_present_state() {
		return pers_present_state;
	}
	public void setPers_present_state(Integer pers_present_state) {
		this.pers_present_state = pers_present_state;
	}
	public Integer getPers_present_pincode() {
		return pers_present_pincode;
	}
	public void setPers_present_pincode(Integer pers_present_pincode) {
		this.pers_present_pincode = pers_present_pincode;
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
	public String getPers_permanent_lendmark() {
		return pers_permanent_lendmark;
	}
	public void setPers_permanent_lendmark(String pers_permanent_lendmark) {
		this.pers_permanent_lendmark = pers_permanent_lendmark;
	}
	public String getPers_present_lendmark() {
		return pers_present_lendmark;
	}
	public void setPers_present_lendmark(String pers_present_lendmark) {
		this.pers_present_lendmark = pers_present_lendmark;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public Integer getNeet_rank() {
		return neet_rank;
	}
	public void setNeet_rank(Integer neet_rank) {
		this.neet_rank = neet_rank;
	}
	public Integer getNeet_marks() {
		return neet_marks;
	}
	public void setNeet_marks(Integer neet_marks) {
		this.neet_marks = neet_marks;
	}
	public String getNeet_percentile() {
		return neet_percentile;
	}
	public void setNeet_percentile(String neet_percentile) {
		this.neet_percentile = neet_percentile;
	}
	public String getPers_middel_name() {
		return pers_middel_name;
	}
	public void setPers_middel_name(String pers_middel_name) {
		this.pers_middel_name = pers_middel_name;
	}
	public String getPers_surname() {
		return pers_surname;
	}
	public void setPers_surname(String pers_surname) {
		this.pers_surname = pers_surname;
	}
	public Integer getCand_prifix() {
		return cand_prifix;
	}
	public void setCand_prifix(Integer cand_prifix) {
		this.cand_prifix = cand_prifix;
	}
	public String getNeet_roll_no() {
		return neet_roll_no;
	}
	public void setNeet_roll_no(String neet_roll_no) {
		this.neet_roll_no = neet_roll_no;
	}
	public String getNeet_application_no() {
		return neet_application_no;
	}
	public void setNeet_application_no(String neet_application_no) {
		this.neet_application_no = neet_application_no;
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
	public String getCorre_postoffice() {
		return corre_postoffice;
	}
	public void setCorre_postoffice(String corre_postoffice) {
		this.corre_postoffice = corre_postoffice;
	}
	public String getCorre_tehsil() {
		return corre_tehsil;
	}
	public void setCorre_tehsil(String corre_tehsil) {
		this.corre_tehsil = corre_tehsil;
	}
	public String getCorre_policestation() {
		return corre_policestation;
	}
	public void setCorre_policestation(String corre_policestation) {
		this.corre_policestation = corre_policestation;
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
	public Integer getQuota_id() {
		return quota_id;
	}
	public void setQuota_id(Integer quota_id) {
		this.quota_id = quota_id;
	}
	public Integer getCounselling_authority() {
		return counselling_authority;
	}
	public void setCounselling_authority(Integer counselling_authority) {
		this.counselling_authority = counselling_authority;
	}
	
}
