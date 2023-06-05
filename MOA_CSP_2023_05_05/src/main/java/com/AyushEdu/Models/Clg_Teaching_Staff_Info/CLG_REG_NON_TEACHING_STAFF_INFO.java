package com.AyushEdu.Models.Clg_Teaching_Staff_Info;
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
@Table(name="clg_reg_non_teaching_staff_info", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_NON_TEACHING_STAFF_INFO {
	private int id;
	private int s_id;
	private int institute_id;
	private String post;
	private int post_id;
	private String available_staff;
	private String nonteaching_acquittance;
	private int total_staff;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAvailable_staff() {
		return available_staff;
	}
	public void setAvailable_staff(String available_staff) {
		this.available_staff = available_staff;
	}
	public int getTotal_staff() {
		return total_staff;
	}
	public void setTotal_staff(int total_staff) {
		this.total_staff = total_staff;
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
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getNonteaching_acquittance() {
		return nonteaching_acquittance;
	}
	public void setNonteaching_acquittance(String nonteaching_acquittance) {
		this.nonteaching_acquittance = nonteaching_acquittance;
	}
}
