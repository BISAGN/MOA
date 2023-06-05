package com.AyushEdu.Models.Library_mgmt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

@Entity
@Table(name = "tb_member_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_MEMBER_DTL
{
 	
  	  
    private int id;
 	private  String member_name;
 	private Date member_joined_date;
//    Date member_taken_book;
//    Date member_return_book;
//    int member_books_quantity;
 	private  int member_dept;
 	private int member_gender;
 	private int member_age;
 	private String created_by;
 	private 	Date created_date;
 	private String modified_by;
 	private Date modified_date;
//    String member_fee;
 	private int state_name;
 	private String city_name;
 	private int member_district_name;
 	private String member_phone_number;
 	private String member_id;
 	private int member_type;
// 	private String ayush_id;
 	@Id
  	@GeneratedValue(strategy = IDENTITY)
  	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public Date getMember_joined_date() {
		return member_joined_date;
	}
	public void setMember_joined_date(Date member_joined_date) {
		this.member_joined_date = member_joined_date;
	}
	public int getMember_dept() {
		return member_dept;
	}
	public void setMember_dept(int member_dept) {
		this.member_dept = member_dept;
	}
	public int getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(int member_gender) {
		this.member_gender = member_gender;
	}
	public int getMember_age() {
		return member_age;
	}
	public void setMember_age(int member_age) {
		this.member_age = member_age;
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
	
	public String getMember_phone_number() {
		return member_phone_number;
	}
	public void setMember_phone_number(String member_phone_number) {
		this.member_phone_number = member_phone_number;
	}
	public int getState_name() {
		return state_name;
	}
	public void setState_name(int state_name) {
		this.state_name = state_name;
	}
	
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public int getMember_district_name() {
		return member_district_name;
	}
	public void setMember_district_name(int member_district_name) {
		this.member_district_name = member_district_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getMember_type() {
		return member_type;
	}
	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}
	
 

}
