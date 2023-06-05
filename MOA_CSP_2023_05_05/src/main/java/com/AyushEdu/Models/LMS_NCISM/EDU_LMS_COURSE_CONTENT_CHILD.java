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
@Table(name = "edu_lms_course_content_child", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_LMS_COURSE_CONTENT_CHILD {
	
	private int id;
	private int module;
	private int level_of_module;
	private String ref_video;
	private String video_duration;
	private String upload_file;
	private String other_note;
	private String 	upload_ppt;
	private int p_id;
	private String created_by;
	private String modified_by;
	private Date created_date;
	private Date modified_date;
	private int status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getRef_video() {
		return ref_video;
	}
	public void setRef_video(String ref_video) {
		this.ref_video = ref_video;
	}
	public String getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
	}
	public String getVideo_duration() {
		return video_duration;
	}
	public void setVideo_duration(String video_duration) {
		this.video_duration = video_duration;
	}
	public String getOther_note() {
		return other_note;
	}
	public void setOther_note(String other_note) {
		this.other_note = other_note;
	}
	public String getUpload_ppt() {
		return upload_ppt;
	}
	public void setUpload_ppt(String upload_ppt) {
		this.upload_ppt = upload_ppt;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public int getLevel_of_module() {
		return level_of_module;
	}
	public void setLevel_of_module(int level_of_module) {
		this.level_of_module = level_of_module;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
