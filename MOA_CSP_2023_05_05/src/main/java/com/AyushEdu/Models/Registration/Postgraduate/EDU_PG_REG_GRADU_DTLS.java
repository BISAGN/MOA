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
@Table(name="edu_pg_reg_gradu_dtls", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_PG_REG_GRADU_DTLS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private String name_of_exam;
	private String month_year;
	private String university;
	private String no_of_attempts;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private Integer p_id;
	private String universityother;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_exam() {
		return name_of_exam;
	}
	public void setName_of_exam(String name_of_exam) {
		this.name_of_exam = name_of_exam;
	}
	public String getMonth_year() {
		return month_year;
	}
	public void setMonth_year(String month_year) {
		this.month_year = month_year;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getNo_of_attempts() {
		return no_of_attempts;
	}
	public void setNo_of_attempts(String no_of_attempts) {
		this.no_of_attempts = no_of_attempts;
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
	public String getUniversityother() {
		return universityother;
	}
	public void setUniversityother(String universityother) {
		this.universityother = universityother;
	}
	
	
	
	
	
	
	
}
