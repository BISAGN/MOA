package com.AyushEdu.Models.Exp_Excel;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author rdp
 *
 */
@Entity
@Table(name = "edu_lms_student_details", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class EDU_LMS_STUDENT_DETAILS {
	private int id;
	private String ayush_id;
	private String name;
	private String last_name;
	private Date dob;
	private String aadhar_card;
	private String email;
	private String mobile_no;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
//	private int institude_id;
	private Date admission_date;
	private int system;
	private int degree;
	private String enrollment_no;
	private Date upload_date;
	private String gender;
	private String semester;
	private int university_userid;
	private int institude_userid;
	private int verified_status;
	private String neet_application_no;
	private int neet_marks;
	private String neet_percentile;
	private int neet_rank;
	private String neet_roll_no;
	private int fee_paid_status;
	private int late_admission_status;
	private String university_enrollment_no;
	private int quota;
	private int counc_auth;
	private int category;
	private String mother_name;
	private String father_name;
	private String inst_code;
	private int state;
	private int pg_subject;
	private int intake_id;
	private Date fees_date;
	private String fees_receipt;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAyush_id() {
		return ayush_id;
	}
	public void setAyush_id(String ayush_id) {
		this.ayush_id = ayush_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAadhar_card() {
		return aadhar_card;
	}
	public void setAadhar_card(String aadhar_card) {
		this.aadhar_card = aadhar_card;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
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
	public String getEnrollment_no() {
		return enrollment_no;
	}
	public void setEnrollment_no(String enrollment_no) {
		this.enrollment_no = enrollment_no;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
//	public int getInstitude_id() {
//		return institude_id;
//	}
//	public void setInstitude_id(int institude_id) {
//		this.institude_id = institude_id;
//	}
	public int getSystem() {
		return system;
	}
	public void setSystem(int system) {
		this.system = system;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getUniversity_userid() {
		return university_userid;
	}
	public void setUniversity_userid(int university_userid) {
		this.university_userid = university_userid;
	}
	public int getInstitude_userid() {
		return institude_userid;
	}
	public void setInstitude_userid(int institude_userid) {
		this.institude_userid = institude_userid;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getAdmission_date() {
		return admission_date;
	}
	public void setAdmission_date(Date admission_date) {
		this.admission_date = admission_date;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getNeet_application_no() {
		return neet_application_no;
	}
	public void setNeet_application_no(String neet_application_no) {
		this.neet_application_no = neet_application_no;
	}
	public int getNeet_marks() {
		return neet_marks;
	}
	public void setNeet_marks(int neet_marks) {
		this.neet_marks = neet_marks;
	}
	public String getNeet_percentile() {
		return neet_percentile;
	}
	public void setNeet_percentile(String neet_percentile) {
		this.neet_percentile = neet_percentile;
	}
	public int getNeet_rank() {
		return neet_rank;
	}
	public void setNeet_rank(int neet_rank) {
		this.neet_rank = neet_rank;
	}
	public String getNeet_roll_no() {
		return neet_roll_no;
	}
	public void setNeet_roll_no(String neet_roll_no) {
		this.neet_roll_no = neet_roll_no;
	}
	public int getFee_paid_status() {
		return fee_paid_status;
	}
	public void setFee_paid_status(int fee_paid_status) {
		this.fee_paid_status = fee_paid_status;
	}
	public int getLate_admission_status() {
		return late_admission_status;
	}
	public void setLate_admission_status(int late_admission_status) {
		this.late_admission_status = late_admission_status;
	}
	public String getUniversity_enrollment_no() {
		return university_enrollment_no;
	}
	public void setUniversity_enrollment_no(String university_enrollment_no) {
		this.university_enrollment_no = university_enrollment_no;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public int getCounc_auth() {
		return counc_auth;
	}
	public void setCounc_auth(int counc_auth) {
		this.counc_auth = counc_auth;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getVerified_status() {
		return verified_status;
	}
	public void setVerified_status(int verified_status) {
		this.verified_status = verified_status;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getInst_code() {
		return inst_code;
	}
	public void setInst_code(String inst_code) {
		this.inst_code = inst_code;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getPg_subject() {
		return pg_subject;
	}
	public void setPg_subject(int pg_subject) {
		this.pg_subject = pg_subject;
	}
	public int getIntake_id() {
		return intake_id;
	}
	public void setIntake_id(int intake_id) {
		this.intake_id = intake_id;
	}
	public Date getFees_date() {
		return fees_date;
	}
	public void setFees_date(Date fees_date) {
		this.fees_date = fees_date;
	}
	public String getFees_receipt() {
		return fees_receipt;
	}
	public void setFees_receipt(String fees_receipt) {
		this.fees_receipt = fees_receipt;
	}
	
}
