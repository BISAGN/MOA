package com.AyushEdu.Models.LMS_Teacher;

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
@Table(name="tb_nch_teacher_quali_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_LMS_TEACHER_QUALIFICATION_CHILD {
	
	
	private int id;
	private String type_of_degree;
	private String course;
	private String month_and_year;
	private int roll_no;
	private String name_of_institute;
	private int percentage;
	private int p_id;
	private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private int status;
	private int subject;
	private String affiliated_university;
	private String otheruniversity;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_of_degree() {
		return type_of_degree;
	}
	public void setType_of_degree(String type_of_degree) {
		this.type_of_degree = type_of_degree;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getMonth_and_year() {
		return month_and_year;
	}
	public void setMonth_and_year(String month_and_year) {
		this.month_and_year = month_and_year;
	}
	public int getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}
	public String getName_of_institute() {
		return name_of_institute;
	}
	public void setName_of_institute(String name_of_institute) {
		this.name_of_institute = name_of_institute;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSubject() {
		return subject;
	}
	public void setSubject(int subject) {
		this.subject = subject;
	}
	public String getAffiliated_university() {
		return affiliated_university;
	}
	public void setAffiliated_university(String affiliated_university) {
		this.affiliated_university = affiliated_university;
	}
	public String getOtheruniversity() {
		return otheruniversity;
	}
	public void setOtheruniversity(String otheruniversity) {
		this.otheruniversity = otheruniversity;
	}
	

}
