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
@Table(name="clg_reg_inst_info_police_station_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_POLICE_STATION_DETAILS {
	
	
	private int id;
	private int p_id;
	private String name_of_nearest_police_station;
	private String addr_line1;
	private String addr_line2;
	private int state;
	private String city;
	private int pincode;
	private BigInteger phone_number;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	private int police_std_code;
	private int sp_std_code;
	private BigInteger sp_phone_number;
	private int district_id;
	
	
	
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
	public String getName_of_nearest_police_station() {
		return name_of_nearest_police_station;
	}
	public void setName_of_nearest_police_station(String name_of_nearest_police_station) {
		this.name_of_nearest_police_station = name_of_nearest_police_station;
	}
	public String getAddr_line1() {
		return addr_line1;
	}
	public void setAddr_line1(String addr_line1) {
		this.addr_line1 = addr_line1;
	}
	public String getAddr_line2() {
		return addr_line2;
	}
	public void setAddr_line2(String addr_line2) {
		this.addr_line2 = addr_line2;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public BigInteger getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(BigInteger phone_number) {
		this.phone_number = phone_number;
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
	public int getPolice_std_code() {
		return police_std_code;
	}
	public void setPolice_std_code(int police_std_code) {
		this.police_std_code = police_std_code;
	}
	public int getSp_std_code() {
		return sp_std_code;
	}
	public void setSp_std_code(int sp_std_code) {
		this.sp_std_code = sp_std_code;
	}
	public BigInteger getSp_phone_number() {
		return sp_phone_number;
	}
	public void setSp_phone_number(BigInteger sp_phone_number) {
		this.sp_phone_number = sp_phone_number;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	
	
	
	

}
