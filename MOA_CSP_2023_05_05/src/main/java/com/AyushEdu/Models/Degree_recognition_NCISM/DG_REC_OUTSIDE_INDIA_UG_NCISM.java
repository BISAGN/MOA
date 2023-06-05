package com.AyushEdu.Models.Degree_recognition_NCISM;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_outside_india_ug_ncism", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_OUTSIDE_INDIA_UG_NCISM {
	
	private int id;
	private String inst_coures_name;
	private String aprrove_cours_name;
	private String uni_name;;
	private int country_id;
	private String state;
	private String district;
	private String city;
	private String uni_address;
	private String reg_email_id;
	private String con_per_name;
	private int con_per_desg;
	private String con_per_mob_no;
	private String con_per_email_id;
	private int madical_stream;
	private String nomlat_degree;
	private String abbre_degree;
	private String y_fir_bat;
	private String m_fir_bat;
	private String y_fir_bat_std_award;
	private String m_fir_bat_std_award;
	private int condition;
	private String y_last_bat_std_award;
	private String m_last_bat_std_award;
	private int institute_status;
	private int university_status;
	private int ncism_status;
	private int user_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInst_coures_name() {
		return inst_coures_name;
	}
	public void setInst_coures_name(String inst_coures_name) {
		this.inst_coures_name = inst_coures_name;
	}
	public String getAprrove_cours_name() {
		return aprrove_cours_name;
	}
	public void setAprrove_cours_name(String aprrove_cours_name) {
		this.aprrove_cours_name = aprrove_cours_name;
	}
	public String getUni_name() {
		return uni_name;
	}
	public void setUni_name(String uni_name) {
		this.uni_name = uni_name;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUni_address() {
		return uni_address;
	}
	public void setUni_address(String uni_address) {
		this.uni_address = uni_address;
	}
	public String getReg_email_id() {
		return reg_email_id;
	}
	public void setReg_email_id(String reg_email_id) {
		this.reg_email_id = reg_email_id;
	}
	public String getCon_per_name() {
		return con_per_name;
	}
	public void setCon_per_name(String con_per_name) {
		this.con_per_name = con_per_name;
	}
	public int getCon_per_desg() {
		return con_per_desg;
	}
	public void setCon_per_desg(int con_per_desg) {
		this.con_per_desg = con_per_desg;
	}
	
	public String getCon_per_mob_no() {
		return con_per_mob_no;
	}
	public void setCon_per_mob_no(String con_per_mob_no) {
		this.con_per_mob_no = con_per_mob_no;
	}
	public String getCon_per_email_id() {
		return con_per_email_id;
	}
	public void setCon_per_email_id(String con_per_email_id) {
		this.con_per_email_id = con_per_email_id;
	}
	public int getMadical_stream() {
		return madical_stream;
	}
	public void setMadical_stream(int madical_stream) {
		this.madical_stream = madical_stream;
	}
	public String getNomlat_degree() {
		return nomlat_degree;
	}
	public void setNomlat_degree(String nomlat_degree) {
		this.nomlat_degree = nomlat_degree;
	}
	public String getAbbre_degree() {
		return abbre_degree;
	}
	public void setAbbre_degree(String abbre_degree) {
		this.abbre_degree = abbre_degree;
	}
	public String getY_fir_bat() {
		return y_fir_bat;
	}
	public void setY_fir_bat(String y_fir_bat) {
		this.y_fir_bat = y_fir_bat;
	}
	public String getM_fir_bat() {
		return m_fir_bat;
	}
	public void setM_fir_bat(String m_fir_bat) {
		this.m_fir_bat = m_fir_bat;
	}
	public String getY_fir_bat_std_award() {
		return y_fir_bat_std_award;
	}
	public void setY_fir_bat_std_award(String y_fir_bat_std_award) {
		this.y_fir_bat_std_award = y_fir_bat_std_award;
	}
	public String getM_fir_bat_std_award() {
		return m_fir_bat_std_award;
	}
	public void setM_fir_bat_std_award(String m_fir_bat_std_award) {
		this.m_fir_bat_std_award = m_fir_bat_std_award;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public String getY_last_bat_std_award() {
		return y_last_bat_std_award;
	}
	public void setY_last_bat_std_award(String y_last_bat_std_award) {
		this.y_last_bat_std_award = y_last_bat_std_award;
	}
	public String getM_last_bat_std_award() {
		return m_last_bat_std_award;
	}
	public void setM_last_bat_std_award(String m_last_bat_std_award) {
		this.m_last_bat_std_award = m_last_bat_std_award;
	}
	public int getInstitute_status() {
		return institute_status;
	}
	public void setInstitute_status(int institute_status) {
		this.institute_status = institute_status;
	}
	public int getUniversity_status() {
		return university_status;
	}
	public void setUniversity_status(int university_status) {
		this.university_status = university_status;
	}
	public int getNcism_status() {
		return ncism_status;
	}
	public void setNcism_status(int ncism_status) {
		this.ncism_status = ncism_status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	

}
