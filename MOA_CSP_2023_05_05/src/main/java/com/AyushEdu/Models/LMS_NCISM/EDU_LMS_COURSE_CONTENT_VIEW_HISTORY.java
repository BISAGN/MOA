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
@Table(name = "edu_lms_course_content_view_history", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_LMS_COURSE_CONTENT_VIEW_HISTORY {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private String user_id;
	private String video_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String level_of_module;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
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
	public String getLevel_of_module() {
		return level_of_module;
	}
	public void setLevel_of_module(String level_of_module) {
		this.level_of_module = level_of_module;
	}
}
