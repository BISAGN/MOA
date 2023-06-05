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
@Table(name = "dg_rec_teaching_staff_child_b", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_TEACHING_STAFF_B {
	
	private int id;
	private String name_of_college_pg;
	private String name_of_teaching_staff;
	private int phone;
	private String email_id;
	private String designation;
	private String department;
	private int registration_no;
	private Date date_of_reg;
	private Date date_of_birth;
	private String qualification_awarding_authority;
	private String year_of_award;
	private Date date_of_appointment;
	private String fulltime_parttime;
	private String post_teaching;
	private Date exp_from;
	private Date exp_to;
	private String total_teaching_exp_in_year;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int user_id;
	private int university_id;
	private int university_approved_status;
	private int council_approved_status;
	private String current_month_year;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_teaching_staff() {
		return name_of_teaching_staff;
	}
	public void setName_of_teaching_staff(String name_of_teaching_staff) {
		this.name_of_teaching_staff = name_of_teaching_staff;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(int registration_no) {
		this.registration_no = registration_no;
	}
	public Date getDate_of_reg() {
		return date_of_reg;
	}
	public void setDate_of_reg(Date date_of_reg) {
		this.date_of_reg = date_of_reg;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getQualification_awarding_authority() {
		return qualification_awarding_authority;
	}
	public void setQualification_awarding_authority(String qualification_awarding_authority) {
		this.qualification_awarding_authority = qualification_awarding_authority;
	}
	public String getYear_of_award() {
		return year_of_award;
	}
	public void setYear_of_award(String year_of_award) {
		this.year_of_award = year_of_award;
	}
	public Date getDate_of_appointment() {
		return date_of_appointment;
	}
	public void setDate_of_appointment(Date date_of_appointment) {
		this.date_of_appointment = date_of_appointment;
	}
	public String getFulltime_parttime() {
		return fulltime_parttime;
	}
	public void setFulltime_parttime(String fulltime_parttime) {
		this.fulltime_parttime = fulltime_parttime;
	}
	public String getPost_teaching() {
		return post_teaching;
	}
	public void setPost_teaching(String post_teaching) {
		this.post_teaching = post_teaching;
	}
	public Date getExp_from() {
		return exp_from;
	}
	public void setExp_from(Date exp_from) {
		this.exp_from = exp_from;
	}
	public Date getExp_to() {
		return exp_to;
	}
	public void setExp_to(Date exp_to) {
		this.exp_to = exp_to;
	}
	public String getTotal_teaching_exp_in_year() {
		return total_teaching_exp_in_year;
	}
	public void setTotal_teaching_exp_in_year(String total_teaching_exp_in_year) {
		this.total_teaching_exp_in_year = total_teaching_exp_in_year;
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
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
	}
	public String getName_of_college_pg() {
		return name_of_college_pg;
	}
	public void setName_of_college_pg(String name_of_college_pg) {
		this.name_of_college_pg = name_of_college_pg;
	}

}
