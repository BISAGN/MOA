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
@Table(name="clg_reg_last_academic_year_staff_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS {
	private int id;
	private int s_id;
	private int institute_id;
	private int teacher_info_id;
	private String teacher_info;
	private String guide_list;
	private String teaching_attendance;
	private String non_teaching_attendance;
	private int professor;
	private int associate_prof;
	private int assistant_prof;
	private int total;
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
	public int getTeacher_info_id() {
		return teacher_info_id;
	}
	public void setTeacher_info_id(int teacher_info_id) {
		this.teacher_info_id = teacher_info_id;
	}
	public String getTeacher_info() {
		return teacher_info;
	}
	public void setTeacher_info(String teacher_info) {
		this.teacher_info = teacher_info;
	}
	public int getProfessor() {
		return professor;
	}
	public void setProfessor(int professor) {
		this.professor = professor;
	}
	public int getAssociate_prof() {
		return associate_prof;
	}
	public void setAssociate_prof(int associate_prof) {
		this.associate_prof = associate_prof;
	}
	public int getAssistant_prof() {
		return assistant_prof;
	}
	public void setAssistant_prof(int assistant_prof) {
		this.assistant_prof = assistant_prof;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
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
	public String getGuide_list() {
		return guide_list;
	}
	public void setGuide_list(String guide_list) {
		this.guide_list = guide_list;
	}
	public String getTeaching_attendance() {
		return teaching_attendance;
	}
	public void setTeaching_attendance(String teaching_attendance) {
		this.teaching_attendance = teaching_attendance;
	}
	public String getNon_teaching_attendance() {
		return non_teaching_attendance;
	}
	public void setNon_teaching_attendance(String non_teaching_attendance) {
		this.non_teaching_attendance = non_teaching_attendance;
	}
}
