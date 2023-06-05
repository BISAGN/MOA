package  com.AyushEdu.Models.LMS_NCISM;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_lms_course_content", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_COURSE_CONTENT_MSTR {

	private int id;
	private int system_name;
	private int course_name;
	private String created_by;
	private String modified_by;
	private Date created_date;
	private Date modified_date;
	private int app_status;
	private int type_of_content;
	private int degree_name;
	
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
	public int getSystem_name() {
		return system_name;
	}
	public void setSystem_name(int system_name) {
		this.system_name = system_name;
	}
	public int getCourse_name() {
		return course_name;
	}
	public void setCourse_name(int course_name) {
		this.course_name = course_name;
	}
	public int getApp_status() {
		return app_status;
	}
	public void setApp_status(int app_status) {
		this.app_status = app_status;
	}
	public int getType_of_content() {
		return type_of_content;
	}
	public void setType_of_content(int type_of_content) {
		this.type_of_content = type_of_content;
	}
	public int getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(int degree_name) {
		this.degree_name = degree_name;
	}
	
	
}
