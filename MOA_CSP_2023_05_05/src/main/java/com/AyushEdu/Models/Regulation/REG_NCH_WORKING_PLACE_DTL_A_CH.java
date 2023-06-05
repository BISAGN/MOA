package com.AyushEdu.Models.Regulation;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "reg_nch_working_place_dtl_a_ch", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class REG_NCH_WORKING_PLACE_DTL_A_CH {
	private int id;
	private int place_of_working;
	private String landline;
	private String email;
	private int authority_type;
	private String name_of_res_p;
	private int regulation_p_id;
	private Date created_date ;
	private String created_by;
	private Date modified_date ;
	private String modified_by;
	private String place_of_working_name;
	private String hos_address1;
	private String hos_address2;
	private String hos_address3;
	private int hos_state;
	private int hos_district;
	private Date date_pract_from;
	private Date date_pract_to;
	private String mobile_no;
	private int status;
	private String adjunct_place;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlace_of_working() {
		return place_of_working;
	}
	public void setPlace_of_working(int place_of_working) {
		this.place_of_working = place_of_working;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAuthority_type() {
		return authority_type;
	}
	public void setAuthority_type(int authority_type) {
		this.authority_type = authority_type;
	}
	
	public int getRegulation_p_id() {
		return regulation_p_id;
	}
	public void setRegulation_p_id(int regulation_p_id) {
		this.regulation_p_id = regulation_p_id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getName_of_res_p() {
		return name_of_res_p;
	}
	public void setName_of_res_p(String name_of_res_p) {
		this.name_of_res_p = name_of_res_p;
	}
	public String getPlace_of_working_name() {
		return place_of_working_name;
	}
	public void setPlace_of_working_name(String place_of_working_name) {
		this.place_of_working_name = place_of_working_name;
	}
	public String getHos_address1() {
		return hos_address1;
	}
	public void setHos_address1(String hos_address1) {
		this.hos_address1 = hos_address1;
	}
	public String getHos_address2() {
		return hos_address2;
	}
	public void setHos_address2(String hos_address2) {
		this.hos_address2 = hos_address2;
	}
	public String getHos_address3() {
		return hos_address3;
	}
	public void setHos_address3(String hos_address3) {
		this.hos_address3 = hos_address3;
	}
	public int getHos_state() {
		return hos_state;
	}
	public void setHos_state(int hos_state) {
		this.hos_state = hos_state;
	}
	public int getHos_district() {
		return hos_district;
	}
	public void setHos_district(int hos_district) {
		this.hos_district = hos_district;
	}
	public Date getDate_pract_from() {
		return date_pract_from;
	}
	public void setDate_pract_from(Date date_pract_from) {
		this.date_pract_from = date_pract_from;
	}
	public Date getDate_pract_to() {
		return date_pract_to;
	}
	public void setDate_pract_to(Date date_pract_to) {
		this.date_pract_to = date_pract_to;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdjunct_place() {
		return adjunct_place;
	}
	public void setAdjunct_place(String adjunct_place) {
		this.adjunct_place = adjunct_place;
	}
	
	
	
	
	
}
