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
@Table(name = "edu_lms_paper_generation", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class EDU_LMS_PAPER_GENERATION {
	
	private int id;
	private int system_id;
	private int course_id;
	private int set_id;
	private int total_que;
	private int hard;
	private int medium;
	private int easy;
	private int passing_marks;
	private int total_marks;
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
	public int getTotal_que() {
		return total_que;
	}
	public void setTotal_que(int total_que) {
		this.total_que = total_que;
	}
	public int getHard() {
		return hard;
	}
	public void setHard(int hard) {
		this.hard = hard;
	}
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getEasy() {
		return easy;
	}
	public void setEasy(int easy) {
		this.easy = easy;
	}
	public int getPassing_marks() {
		return passing_marks;
	}
	public void setPassing_marks(int passing_marks) {
		this.passing_marks = passing_marks;
	}
	public int getTotal_marks() {
		return total_marks;
	}
	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
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
