package com.AyushEdu.Models.Placement_Mgmt;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_placement_company_reg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_PLACEMENT_COMPANY_REG {
	
	private int id;
	private String company_name;
	private String name;
	private String email_id;
	private String mobile_no;
	private String ph_no;
	private String address;
	private int state=0;
	private int per_district=0;
	private String pincode;
	private String hours_from;
	private String hours_to;
//	private String product;
	private String web_url;
	private String photo_path;
	private String photo_path_pic;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int notified;
	private int status = 0;
	private String awards;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getPh_no() {
		return ph_no;
	}
	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPer_district() {
		return per_district;
	}
	public void setPer_district(int per_district) {
		this.per_district = per_district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getHours_from() {
		return hours_from;
	}
	public void setHours_from(String hours_from) {
		this.hours_from = hours_from;
	}
	public String getHours_to() {
		return hours_to;
	}
	public void setHours_to(String hours_to) {
		this.hours_to = hours_to;
	}
	//	public String getProduct() {
//		return product;
//	}
//	public void setProduct(String product) {
//		this.product = product;
//	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getPhoto_path_pic() {
		return photo_path_pic;
	}
	public void setPhoto_path_pic(String photo_path_pic) {
		this.photo_path_pic = photo_path_pic;
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
	public int getNotified() {
		return notified;
	}
	public void setNotified(int notified) {
		this.notified = notified;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
