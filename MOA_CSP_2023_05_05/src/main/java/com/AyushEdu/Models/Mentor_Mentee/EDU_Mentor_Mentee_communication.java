package com.AyushEdu.Models.Mentor_Mentee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="edu_mentor_mentee_communication", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_Mentor_Mentee_communication {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false) 
	private int id;
	private int faculty_user_id;
	private int student_user_id;
	private int status;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private String message;
	private int from_msg;
	private int to_msg;
	private String file;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFaculty_user_id() {
		return faculty_user_id;
	}
	public void setFaculty_user_id(int faculty_user_id) {
		this.faculty_user_id = faculty_user_id;
	}
	public int getStudent_user_id() {
		return student_user_id;
	}
	public void setStudent_user_id(int student_user_id) {
		this.student_user_id = student_user_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getFrom_msg() {
		return from_msg;
	}
	public void setFrom_msg(int from_msg) {
		this.from_msg = from_msg;
	}
	public int getTo_msg() {
		return to_msg;
	}
	public void setTo_msg(int to_msg) {
		this.to_msg = to_msg;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
