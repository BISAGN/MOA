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
@Table(name = "dg_rec_admitted_student_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_ADMITTED_STUDENT {
	private int id;
	
	private String student_name;
	private Date date_of_admission;
	private String neet_rank;
	private int rank;
	private int marks;
	private String all_india;
	private String state;
	private String management_quota;
	private String admission_authority;
	private String court_order;
	private String court_order_file;
	private Date date_of_enroll_university;
	private String uni_enroll_number;
	private Date date_of_intern_compl;
	private String remarks_form_b;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int user_id;
	private int inst_status;
	private int university_approved_status;
	private int council_approved_status;
	private String upload_excel;
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
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Date getDate_of_admission() {
		return date_of_admission;
	}
	public void setDate_of_admission(Date date_of_admission) {
		this.date_of_admission = date_of_admission;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getAll_india() {
		return all_india;
	}
	public void setAll_india(String all_india) {
		this.all_india = all_india;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAdmission_authority() {
		return admission_authority;
	}
	public void setAdmission_authority(String admission_authority) {
		this.admission_authority = admission_authority;
	}
	public String getCourt_order() {
		return court_order;
	}
	public void setCourt_order(String court_order) {
		this.court_order = court_order;
	}
	public String getRemarks_form_b() {
		return remarks_form_b;
	}
	public void setRemarks_form_b(String remarks_form_b) {
		this.remarks_form_b = remarks_form_b;
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
	public String getUpload_excel() {
		return upload_excel;
	}
	public void setUpload_excel(String upload_excel) {
		this.upload_excel = upload_excel;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public Date getDate_of_enroll_university() {
		return date_of_enroll_university;
	}
	public void setDate_of_enroll_university(Date date_of_enroll_university) {
		this.date_of_enroll_university = date_of_enroll_university;
	}
	public String getUni_enroll_number() {
		return uni_enroll_number;
	}
	public void setUni_enroll_number(String uni_enroll_number) {
		this.uni_enroll_number = uni_enroll_number;
	}
	public Date getDate_of_intern_compl() {
		return date_of_intern_compl;
	}
	public void setDate_of_intern_compl(Date date_of_intern_compl) {
		this.date_of_intern_compl = date_of_intern_compl;
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
	public String getCourt_order_file() {
		return court_order_file;
	}
	public void setCourt_order_file(String court_order_file) {
		this.court_order_file = court_order_file;
	}
	public String getManagement_quota() {
		return management_quota;
	}
	public void setManagement_quota(String management_quota) {
		this.management_quota = management_quota;
	}
	public String getNeet_rank() {
		return neet_rank;
	}
	public void setNeet_rank(String neet_rank) {
		this.neet_rank = neet_rank;
	}
	
}
