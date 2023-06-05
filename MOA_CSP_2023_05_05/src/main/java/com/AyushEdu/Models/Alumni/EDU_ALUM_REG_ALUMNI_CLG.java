package com.AyushEdu.Models.Alumni;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_alum_register_alumni_clg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_ALUM_REG_ALUMNI_CLG {
	
	private int id;
	private String alum_name;
	private String alum_email;
	private String alum_mo_no;
	private String alum_passing_year;
	private String alum_batch;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int institute_id;
	private int sector;
	
	private int degree_id;
	private int specialization_id;
	private String others;
	private String alum_address;
	private String alum_insta_id;
	private String alum_fb_id;
	private String alum_linkdin_id;
	private String alum_curr_occu;
	private int user_id;
	private String alum_photo;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlum_name() {
		return alum_name;
	}
	public void setAlum_name(String alum_name) {
		this.alum_name = alum_name;
	}
	public String getAlum_email() {
		return alum_email;
	}
	public void setAlum_email(String alum_email) {
		this.alum_email = alum_email;
	}
	public String getAlum_mo_no() {
		return alum_mo_no;
	}
	public void setAlum_mo_no(String alum_mo_no) {
		this.alum_mo_no = alum_mo_no;
	}
	public String getAlum_passing_year() {
		return alum_passing_year;
	}
	public void setAlum_passing_year(String alum_passing_year) {
		this.alum_passing_year = alum_passing_year;
	}
	public String getAlum_batch() {
		return alum_batch;
	}
	public void setAlum_batch(String alum_batch) {
		this.alum_batch = alum_batch;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}
	public int getSpecialization_id() {
		return specialization_id;
	}
	public void setSpecialization_id(int specialization_id) {
		this.specialization_id = specialization_id;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getAlum_address() {
		return alum_address;
	}
	public void setAlum_address(String alum_address) {
		this.alum_address = alum_address;
	}
	public String getAlum_insta_id() {
		return alum_insta_id;
	}
	public void setAlum_insta_id(String alum_insta_id) {
		this.alum_insta_id = alum_insta_id;
	}
	public String getAlum_fb_id() {
		return alum_fb_id;
	}
	public void setAlum_fb_id(String alum_fb_id) {
		this.alum_fb_id = alum_fb_id;
	}
	public String getAlum_linkdin_id() {
		return alum_linkdin_id;
	}
	public void setAlum_linkdin_id(String alum_linkdin_id) {
		this.alum_linkdin_id = alum_linkdin_id;
	}
	public String getAlum_curr_occu() {
		return alum_curr_occu;
	}
	public void setAlum_curr_occu(String alum_curr_occu) {
		this.alum_curr_occu = alum_curr_occu;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAlum_photo() {
		return alum_photo;
	}
	public void setAlum_photo(String alum_photo) {
		this.alum_photo = alum_photo;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	

}
