package com.AyushEdu.Models.Clg_Reg_clg_Infra;

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
@Table(name="clg_reg_infra_constructed_area", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_CONSTRUCTED_AREA {
	
	private int id;
	private int p_id;
	private String college_const;
	private String lecturer_hall;
	private String exam_hall;
	private String central_library;
	private String boys_common_room;
	private String girl_common_room;
	private String canteen;
	private String administrative_section;
	private String total_area_of_college;
	private String total_lecture_hall;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
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
	public String getCollege_const() {
		return college_const;
	}
	public void setCollege_const(String college_const) {
		this.college_const = college_const;
	}
	public String getLecturer_hall() {
		return lecturer_hall;
	}
	public void setLecturer_hall(String lecturer_hall) {
		this.lecturer_hall = lecturer_hall;
	}
	public String getExam_hall() {
		return exam_hall;
	}
	public void setExam_hall(String exam_hall) {
		this.exam_hall = exam_hall;
	}
	public String getCentral_library() {
		return central_library;
	}
	public void setCentral_library(String central_library) {
		this.central_library = central_library;
	}
	public String getBoys_common_room() {
		return boys_common_room;
	}
	public void setBoys_common_room(String boys_common_room) {
		this.boys_common_room = boys_common_room;
	}
	public String getGirl_common_room() {
		return girl_common_room;
	}
	public void setGirl_common_room(String girl_common_room) {
		this.girl_common_room = girl_common_room;
	}
	public String getCanteen() {
		return canteen;
	}
	public void setCanteen(String canteen) {
		this.canteen = canteen;
	}
	public String getAdministrative_section() {
		return administrative_section;
	}
	public void setAdministrative_section(String administrative_section) {
		this.administrative_section = administrative_section;
	}
	public String getTotal_area_of_college() {
		return total_area_of_college;
	}
	public void setTotal_area_of_college(String total_area_of_college) {
		this.total_area_of_college = total_area_of_college;
	}
	public String getTotal_lecture_hall() {
		return total_lecture_hall;
	}
	public void setTotal_lecture_hall(String total_lecture_hall) {
		this.total_lecture_hall = total_lecture_hall;
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
	
	
	
}
