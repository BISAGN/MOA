package com.AyushEdu.Models.QuizBank;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_question_band_parent", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class EDU_LMS_QUESTION_BAND_PARENT {

	private int id;
	private int system_id;
	private int course_id;
	private int set_id;
	private int level_id;
	private int type_id;
	private String question;
	private String marks;
	private String question_upload;
	private String time;
	private String status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	private int module_id;
	private String exam_name;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
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
	public int getSet_id() {
		return set_id;
	}
	public void setSet_id(int set_id) {
		this.set_id = set_id;
	}
	public int getLevel_id() {
		return level_id;
	}
	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getQuestion_upload() {
		return question_upload;
	}
	public void setQuestion_upload(String question_upload) {
		this.question_upload = question_upload;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	
}
