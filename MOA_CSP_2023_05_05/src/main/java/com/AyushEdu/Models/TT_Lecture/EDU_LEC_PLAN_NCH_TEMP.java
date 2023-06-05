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
@Table(name = "edu_lec_plan_nch_temp", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class EDU_LEC_PLAN_NCH_TEMP {

	private int id;
	private int course_name;
	private int topic_id;
	private Date academic_year;
	private int professional;
	private int learning_outcome;
	private int instructional_method;
	private String lecture_hours;
	private String non_lecture_hours;
	private String time;
	private String time_rem;
	private Date fdate;
	private String activity_description;
	private String resources;
	private int assessment_method; 
	private String modified_by;
	private Date modified_dt;
	private String created_by;
	private Date created_dt;
	private String created_role;
	private int faculty;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(Date academic_year) {
		this.academic_year = academic_year;
	}
	public int getCourse_name() {
		return course_name;
	}
	public void setCourse_name(int course_name) {
		this.course_name = course_name;
	}	
	
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public int getProfessional() {
		return professional;
	}
	public void setProfessional(int professional) {
		this.professional = professional;
	}	
	public int getLearning_outcome() {
		return learning_outcome;
	}
	public void setLearning_outcome(int learning_outcome) {
		this.learning_outcome = learning_outcome;
	}
	public int getAssessment_method() {
		return assessment_method;
	}
	public int getInstructional_method() {
		return instructional_method;
	}
	public void setInstructional_method(int instructional_method) {
		this.instructional_method = instructional_method;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public void setAssessment_method(int assessment_method) {
		this.assessment_method = assessment_method;
	}
	public String getLecture_hours() {
		return lecture_hours;
	}
	public void setLecture_hours(String lecture_hours) {
		this.lecture_hours = lecture_hours;
	}
	public String getNon_lecture_hours() {
		return non_lecture_hours;
	}
	public void setNon_lecture_hours(String non_lecture_hours) {
		this.non_lecture_hours = non_lecture_hours;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime_rem() {
		return time_rem;
	}
	public void setTime_rem(String time_rem) {
		this.time_rem = time_rem;
	}
	public String getActivity_description() {
		return activity_description;
	}
	public void setActivity_description(String activity_description) {
		this.activity_description = activity_description;
	}
	public String getResources() {
		return resources;
	}
	public void setResources(String resources) {
		this.resources = resources;
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
	public int getFaculty() {
		return faculty;
	}
	public void setFaculty(int faculty) {
		this.faculty = faculty;
	}

	
}
