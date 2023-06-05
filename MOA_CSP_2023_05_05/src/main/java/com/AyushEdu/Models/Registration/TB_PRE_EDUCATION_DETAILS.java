package com.AyushEdu.Models.Registration;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_reg_gradu_pre_edu_dtls_tbl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_PRE_EDUCATION_DETAILS {
	
	private int id;
	private String academic;
	private int passing_year;
	private String institute_name;
	private int total_marks;
	private String obtain_marks;
	private String grade;
	private String doc_path;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int p_id;
	private String board_or_university;
	private String school_or_collage;
	private String subject;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAcademic() {
		return academic;
	}
	public void setAcademic(String academic) {
		this.academic = academic;
	}
	public int getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(int passing_year) {
		this.passing_year = passing_year;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	public int getTotal_marks() {
		return total_marks;
	}
	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}
	public String getObtain_marks() {
		return obtain_marks;
	}
	public void setObtain_marks(String obtain_marks) {
		this.obtain_marks = obtain_marks;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getBoard_or_university() {
		return board_or_university;
	}
	public void setBoard_or_university(String board_or_university) {
		this.board_or_university = board_or_university;
	}
	public String getSchool_or_collage() {
		return school_or_collage;
	}
	public void setSchool_or_collage(String school_or_collage) {
		this.school_or_collage = school_or_collage;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
