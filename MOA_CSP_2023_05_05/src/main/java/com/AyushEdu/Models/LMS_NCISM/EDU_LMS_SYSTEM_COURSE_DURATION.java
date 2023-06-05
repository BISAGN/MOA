package com.AyushEdu.Models.LMS_NCISM;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_system_course_duration", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_LMS_SYSTEM_COURSE_DURATION {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private Date start_date;
	private Date end_date;
	private int system_id;
	private int course_id;
	private String course_fee;
	private String demo_video;
	private String course_switch_duration;
	private String cd_uniq_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int degree_id;
	private int term_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSystem_id() {
		return system_id;
	}
	public void setSystem_id(int system_id) {
		this.system_id = system_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getCourse_fee() {
		return course_fee;
	}
	public void setCourse_fee(String course_fee) {
		this.course_fee = course_fee;
	}
	public String getDemo_video() {
		return demo_video;
	}
	public void setDemo_video(String demo_video) {
		this.demo_video = demo_video;
	}
	public String getCourse_switch_duration() {
		return course_switch_duration;
	}
	public void setCourse_switch_duration(String course_switch_duration) {
		this.course_switch_duration = course_switch_duration;
	}
	public String getCd_uniq_id() {
		return cd_uniq_id;
	}
	public void setCd_uniq_id(String cd_uniq_id) {
		this.cd_uniq_id = cd_uniq_id;
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
	public int getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	
}
