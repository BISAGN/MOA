package com.AyushEdu.Models.TT_Lecture;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lec_plan_for_student", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class EDU_LEC_PLAN_FOR_STUDENTS {

	
	private int id;
	private int professional;
	private int faculty;
	private int course_name;
	private int topic;
	private int learning_objective;
	private int instructional_method;
	private Date sdate;
	private int student_id;
	private String modified_by;
	private Date modified_dt;
	private String created_by;
	private Date created_dt;
	private String created_role;
	private int attended;
	private int institute_id;
	private String start_time;
	private String end_time;
	private int sub_topic;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProfessional() {
		return professional;
	}
	public void setProfessional(int professional) {
		this.professional = professional;
	}
	public int getFaculty() {
		return faculty;
	}
	public void setFaculty(int faculty) {
		this.faculty = faculty;
	}
	public int getCourse_name() {
		return course_name;
	}
	public void setCourse_name(int course_name) {
		this.course_name = course_name;
	}
	public int getTopic() {
		return topic;
	}
	public void setTopic(int topic) {
		this.topic = topic;
	}
	public int getLearning_objective() {
		return learning_objective;
	}
	public void setLearning_objective(int learning_objective) {
		this.learning_objective = learning_objective;
	}
	public int getInstructional_method() {
		return instructional_method;
	}
	public void setInstructional_method(int instructional_method) {
		this.instructional_method = instructional_method;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getCreated_role() {
		return created_role;
	}
	public void setCreated_role(String created_role) {
		this.created_role = created_role;
	}
	public int getAttended() {
		return attended;
	}
	public void setAttended(int attended) {
		this.attended = attended;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getSub_topic() {
		return sub_topic;
	}
	public void setSub_topic(int sub_topic) {
		this.sub_topic = sub_topic;
	}
	
	
}
