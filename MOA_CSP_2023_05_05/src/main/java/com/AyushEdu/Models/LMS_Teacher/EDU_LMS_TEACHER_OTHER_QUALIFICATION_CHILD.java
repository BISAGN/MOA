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
@Table(name="tb_nch_teacher_other_quali_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD {
	
	private int id;
	private String name_of_exam_degree;
	private String subject;
	private String name_of_uni_inst;
	private String name_of_aff_uni;
	private String month_and_year;
	private int p_id;
	private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private int status;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_exam_degree() {
		return name_of_exam_degree;
	}
	public void setName_of_exam_degree(String name_of_exam_degree) {
		this.name_of_exam_degree = name_of_exam_degree;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName_of_uni_inst() {
		return name_of_uni_inst;
	}
	public void setName_of_uni_inst(String name_of_uni_inst) {
		this.name_of_uni_inst = name_of_uni_inst;
	}
	public String getName_of_aff_uni() {
		return name_of_aff_uni;
	}
	public void setName_of_aff_uni(String name_of_aff_uni) {
		this.name_of_aff_uni = name_of_aff_uni;
	}
	public String getMonth_and_year() {
		return month_and_year;
	}
	public void setMonth_and_year(String month_and_year) {
		this.month_and_year = month_and_year;
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
	
	

}
