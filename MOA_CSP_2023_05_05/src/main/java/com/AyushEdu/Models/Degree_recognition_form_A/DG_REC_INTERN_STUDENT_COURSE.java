package com.AyushEdu.Models.Degree_recognition_form_A;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_intern_student_course_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_INTERN_STUDENT_COURSE {
	private int id;
	private String name_of_students;
	private String year_of_admission;
	private Date date_of_result_final_year;
	private int provisional_reg_no;
	private String year_of_provisional_reg;
	private Date date_of_starting_internship;
	private Date date_of_completion_internship;
	private String remark_form_f;
	private int user_id;
	private int inst_status;
	private int university_approved_status;
	private int council_approved_status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
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
	public String getName_of_students() {
		return name_of_students;
	}
	public void setName_of_students(String name_of_students) {
		this.name_of_students = name_of_students;
	}
	public Date getDate_of_result_final_year() {
		return date_of_result_final_year;
	}
	public void setDate_of_result_final_year(Date date_of_result_final_year) {
		this.date_of_result_final_year = date_of_result_final_year;
	}
	public int getProvisional_reg_no() {
		return provisional_reg_no;
	}
	public void setProvisional_reg_no(int provisional_reg_no) {
		this.provisional_reg_no = provisional_reg_no;
	}
	public Date getDate_of_starting_internship() {
		return date_of_starting_internship;
	}
	public void setDate_of_starting_internship(Date date_of_starting_internship) {
		this.date_of_starting_internship = date_of_starting_internship;
	}
	public Date getDate_of_completion_internship() {
		return date_of_completion_internship;
	}
	public void setDate_of_completion_internship(Date date_of_completion_internship) {
		this.date_of_completion_internship = date_of_completion_internship;
	}
	public String getRemark_form_f() {
		return remark_form_f;
	}
	public void setRemark_form_f(String remark_form_f) {
		this.remark_form_f = remark_form_f;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getYear_of_admission() {
		return year_of_admission;
	}
	public void setYear_of_admission(String year_of_admission) {
		this.year_of_admission = year_of_admission;
	}
	public String getYear_of_provisional_reg() {
		return year_of_provisional_reg;
	}
	public void setYear_of_provisional_reg(String year_of_provisional_reg) {
		this.year_of_provisional_reg = year_of_provisional_reg;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
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
	public int getInst_status() {
		return inst_status;
	}
	public void setInst_status(int inst_status) {
		this.inst_status = inst_status;
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
