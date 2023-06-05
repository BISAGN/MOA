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
@Table(name="clg_reg_inst_info_information_of_connectivity", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY {
	
	private int id;
	private int p_id;
	private String nearest_airport_name;
	private String approx_distance;
	private String nearest_railway_station;
	private String approx_dist_railway;
	private String nearest_inst_name;
	private String city;

	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	private int other_state;
	private int other_district;
	
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
	public String getNearest_airport_name() {
		return nearest_airport_name;
	}
	public void setNearest_airport_name(String nearest_airport_name) {
		this.nearest_airport_name = nearest_airport_name;
	}
	public String getApprox_distance() {
		return approx_distance;
	}
	public void setApprox_distance(String approx_distance) {
		this.approx_distance = approx_distance;
	}
	public String getNearest_railway_station() {
		return nearest_railway_station;
	}
	public void setNearest_railway_station(String nearest_railway_station) {
		this.nearest_railway_station = nearest_railway_station;
	}
	public String getApprox_dist_railway() {
		return approx_dist_railway;
	}
	public void setApprox_dist_railway(String approx_dist_railway) {
		this.approx_dist_railway = approx_dist_railway;
	}
	public String getNearest_inst_name() {
		return nearest_inst_name;
	}
	public void setNearest_inst_name(String nearest_inst_name) {
		this.nearest_inst_name = nearest_inst_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public int getOther_state() {
		return other_state;
	}
	public void setOther_state(int other_state) {
		this.other_state = other_state;
	}
	public int getOther_district() {
		return other_district;
	}
	public void setOther_district(int other_district) {
		this.other_district = other_district;
	}
	
	

	



	
	
	
	
	

}
