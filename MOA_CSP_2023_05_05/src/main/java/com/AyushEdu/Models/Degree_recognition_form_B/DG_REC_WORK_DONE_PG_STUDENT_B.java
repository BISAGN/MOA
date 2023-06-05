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
@Table(name = "dg_rec_work_done_pg_student_child_b", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_WORK_DONE_PG_STUDENT_B {
	
	private int id;
	private String student_name_pg;
	private Date from_date;
	private Date to_date;
	private String name_of_topic;
	private String conclusion_obtain;
	private Date date_of_submission_pg;
	private String publication;
	private String mention_article;
	
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
	public String getStudent_name_pg() {
		return student_name_pg;
	}
	public void setStudent_name_pg(String student_name_pg) {
		this.student_name_pg = student_name_pg;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getName_of_topic() {
		return name_of_topic;
	}
	public void setName_of_topic(String name_of_topic) {
		this.name_of_topic = name_of_topic;
	}
	public String getConclusion_obtain() {
		return conclusion_obtain;
	}
	public void setConclusion_obtain(String conclusion_obtain) {
		this.conclusion_obtain = conclusion_obtain;
	}
	public Date getDate_of_submission_pg() {
		return date_of_submission_pg;
	}
	public void setDate_of_submission_pg(Date date_of_submission_pg) {
		this.date_of_submission_pg = date_of_submission_pg;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public String getMention_article() {
		return mention_article;
	}
	public void setMention_article(String mention_article) {
		this.mention_article = mention_article;
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
