package com.AyushEdu.Models.Degree_recognition_form_A;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_migrated_student_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_MIGRATED_STUDENT {
	private int id;
	private String name_of_college_d;
	private String courses_offeres;
	private String admission_capacity_of_course;
	private int user_id;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int university_id;
	private int p_id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public String getName_of_college_d() {
		return name_of_college_d;
	}
	public void setName_of_college_d(String name_of_college_d) {
		this.name_of_college_d = name_of_college_d;
	}
	public String getCourses_offeres() {
		return courses_offeres;
	}
	public void setCourses_offeres(String courses_offeres) {
		this.courses_offeres = courses_offeres;
	}
	public String getAdmission_capacity_of_course() {
		return admission_capacity_of_course;
	}
	public void setAdmission_capacity_of_course(String admission_capacity_of_course) {
		this.admission_capacity_of_course = admission_capacity_of_course;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
}
