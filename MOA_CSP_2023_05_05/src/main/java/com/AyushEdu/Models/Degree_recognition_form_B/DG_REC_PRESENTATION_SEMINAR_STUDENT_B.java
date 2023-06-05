package com.AyushEdu.Models.Degree_recognition_form_B;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_presentation_seminar_student_child_b", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_PRESENTATION_SEMINAR_STUDENT_B {
	
	private int id;
	private String student_name_presen;
	private String list_of_presentations;
	private String title_of_seminar_attended;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int user_id;
	private int inst_status;
	private int university_approved_status;
	private int council_approved_status;
	private int university_id;
	private String current_month_year;
	private String reject_remarks;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudent_name_presen() {
		return student_name_presen;
	}
	public void setStudent_name_presen(String student_name_presen) {
		this.student_name_presen = student_name_presen;
	}
	public String getList_of_presentations() {
		return list_of_presentations;
	}
	public void setList_of_presentations(String list_of_presentations) {
		this.list_of_presentations = list_of_presentations;
	}
	public String getTitle_of_seminar_attended() {
		return title_of_seminar_attended;
	}
	public void setTitle_of_seminar_attended(String title_of_seminar_attended) {
		this.title_of_seminar_attended = title_of_seminar_attended;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getInst_status() {
		return inst_status;
	}
	public void setInst_status(int inst_status) {
		this.inst_status = inst_status;
	}
	public int getUniversity_approved_status() {
		return university_approved_status;
	}
	public void setUniversity_approved_status(int university_approved_status) {
		this.university_approved_status = university_approved_status;
	}
	public int getCouncil_approved_status() {
		return council_approved_status;
	}
	public void setCouncil_approved_status(int council_approved_status) {
		this.council_approved_status = council_approved_status;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
}
