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
@Table(name = "dg_rec_examiners_md_child_b", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_EXAMINERS_MD_B {
	
	private int id;
	private String name_of_homoeopathic_medical_college;
	private String name_of_guide;
	private String name_of_student_for_guide;
	private String topic_of_dissertation;
	private String whether_guide_participated_in_examination;
	private Date date_of_submission;
	private Date date_of_acceptance;
	private String whether_student_published_article;
	private String mention_details;
	private String article_title;
	private String month_year_exam;
	
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
	public String getName_of_homoeopathic_medical_college() {
		return name_of_homoeopathic_medical_college;
	}
	public void setName_of_homoeopathic_medical_college(String name_of_homoeopathic_medical_college) {
		this.name_of_homoeopathic_medical_college = name_of_homoeopathic_medical_college;
	}
	public String getName_of_guide() {
		return name_of_guide;
	}
	public void setName_of_guide(String name_of_guide) {
		this.name_of_guide = name_of_guide;
	}
	public String getName_of_student_for_guide() {
		return name_of_student_for_guide;
	}
	public void setName_of_student_for_guide(String name_of_student_for_guide) {
		this.name_of_student_for_guide = name_of_student_for_guide;
	}
	public String getTopic_of_dissertation() {
		return topic_of_dissertation;
	}
	public void setTopic_of_dissertation(String topic_of_dissertation) {
		this.topic_of_dissertation = topic_of_dissertation;
	}
	public String getWhether_guide_participated_in_examination() {
		return whether_guide_participated_in_examination;
	}
	public void setWhether_guide_participated_in_examination(String whether_guide_participated_in_examination) {
		this.whether_guide_participated_in_examination = whether_guide_participated_in_examination;
	}
	public Date getDate_of_submission() {
		return date_of_submission;
	}
	public void setDate_of_submission(Date date_of_submission) {
		this.date_of_submission = date_of_submission;
	}
	public Date getDate_of_acceptance() {
		return date_of_acceptance;
	}
	public void setDate_of_acceptance(Date date_of_acceptance) {
		this.date_of_acceptance = date_of_acceptance;
	}
	
	public String getWhether_student_published_article() {
		return whether_student_published_article;
	}
	public void setWhether_student_published_article(String whether_student_published_article) {
		this.whether_student_published_article = whether_student_published_article;
	}
	
	public String getMention_details() {
		return mention_details;
	}
	public void setMention_details(String mention_details) {
		this.mention_details = mention_details;
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
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getMonth_year_exam() {
		return month_year_exam;
	}
	public void setMonth_year_exam(String month_year_exam) {
		this.month_year_exam = month_year_exam;
	}
	
}
