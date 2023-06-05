package com.AyushEdu.Models.Clg_Reg_clg_Infra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_clg_dept_ug", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_CLG_DEPT_UG {
	
	private int id;
	private int p_id;
	private String department;
	private String computer_printer_avail;
	private String dept_library_books;
	private String teacher_training_material;
	private String museum_charts;
	private String museum_models;
	private String museum_specimens;
	private String practical;
	private String tutorial;
	private String seminar;
	private String theory;
	private int department_id;
//	private String edu_tours_teach_pract_purpose;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int institute_id;
	
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getComputer_printer_avail() {
		return computer_printer_avail;
	}
	public void setComputer_printer_avail(String computer_printer_avail) {
		this.computer_printer_avail = computer_printer_avail;
	}
	public String getDept_library_books() {
		return dept_library_books;
	}
	public void setDept_library_books(String dept_library_books) {
		this.dept_library_books = dept_library_books;
	}
	public String getTeacher_training_material() {
		return teacher_training_material;
	}
	public void setTeacher_training_material(String teacher_training_material) {
		this.teacher_training_material = teacher_training_material;
	}
	public String getMuseum_charts() {
		return museum_charts;
	}
	public void setMuseum_charts(String museum_charts) {
		this.museum_charts = museum_charts;
	}
	public String getMuseum_models() {
		return museum_models;
	}
	public void setMuseum_models(String museum_models) {
		this.museum_models = museum_models;
	}
	public String getMuseum_specimens() {
		return museum_specimens;
	}
	public void setMuseum_specimens(String museum_specimens) {
		this.museum_specimens = museum_specimens;
	}
	public String getPractical() {
		return practical;
	}
	public void setPractical(String practical) {
		this.practical = practical;
	}
	public String getTutorial() {
		return tutorial;
	}
	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}
	public String getSeminar() {
		return seminar;
	}
	public void setSeminar(String seminar) {
		this.seminar = seminar;
	}
	public String getTheory() {
		return theory;
	}
	public void setTheory(String theory) {
		this.theory = theory;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
//	public String getEdu_tours_teach_pract_purpose() {
//		return edu_tours_teach_pract_purpose;
//	}
//	public void setEdu_tours_teach_pract_purpose(String edu_tours_teach_pract_purpose) {
//		this.edu_tours_teach_pract_purpose = edu_tours_teach_pract_purpose;
//	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	

}
