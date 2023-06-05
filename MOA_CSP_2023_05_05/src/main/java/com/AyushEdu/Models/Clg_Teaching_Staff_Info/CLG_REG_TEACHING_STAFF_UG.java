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
@Table(name="clg_reg_teaching_staff_ug", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_TEACHING_STAFF_UG {
	private int id;
	private int s_id;
	private int institute_id;
	private int department_id;
	private String department_name;
	private String prof_full_time;
	private String associate_prof_full_time;
	private String assistant_prof_full_time;
	private int total;
	private int total_full_time;
	private String consultant_modern_medi;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int total_professor_full_time;
	private int total_ass_professor_full_time;
	private int total_lect_professor_full_time;
	private int total_total;
	private String teaching_acquittance;
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
	
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getProf_full_time() {
		return prof_full_time;
	}
	public void setProf_full_time(String prof_full_time) {
		this.prof_full_time = prof_full_time;
	}
	
	public String getAssociate_prof_full_time() {
		return associate_prof_full_time;
	}
	public void setAssociate_prof_full_time(String associate_prof_full_time) {
		this.associate_prof_full_time = associate_prof_full_time;
	}
	
	public String getAssistant_prof_full_time() {
		return assistant_prof_full_time;
	}
	public void setAssistant_prof_full_time(String assistant_prof_full_time) {
		this.assistant_prof_full_time = assistant_prof_full_time;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal_full_time() {
		return total_full_time;
	}
	public void setTotal_full_time(int total_full_time) {
		this.total_full_time = total_full_time;
	}
	
	public String getConsultant_modern_medi() {
		return consultant_modern_medi;
	}
	public void setConsultant_modern_medi(String consultant_modern_medi) {
		this.consultant_modern_medi = consultant_modern_medi;
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
	public int getTotal_professor_full_time() {
		return total_professor_full_time;
	}
	public void setTotal_professor_full_time(int total_professor_full_time) {
		this.total_professor_full_time = total_professor_full_time;
	}
	
	public int getTotal_ass_professor_full_time() {
		return total_ass_professor_full_time;
	}
	public void setTotal_ass_professor_full_time(int total_ass_professor_full_time) {
		this.total_ass_professor_full_time = total_ass_professor_full_time;
	}
	
	public int getTotal_lect_professor_full_time() {
		return total_lect_professor_full_time;
	}
	public void setTotal_lect_professor_full_time(int total_lect_professor_full_time) {
		this.total_lect_professor_full_time = total_lect_professor_full_time;
	}
	public int getTotal_total() {
		return total_total;
	}
	public void setTotal_total(int total_total) {
		this.total_total = total_total;
	}
	
	public String getTeaching_acquittance() {
		return teaching_acquittance;
	}
	public void setTeaching_acquittance(String teaching_acquittance) {
		this.teaching_acquittance = teaching_acquittance;
	}
	
	

}
