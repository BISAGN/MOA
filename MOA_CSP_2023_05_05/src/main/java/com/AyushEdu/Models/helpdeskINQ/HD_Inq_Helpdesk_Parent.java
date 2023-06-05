package com.AyushEdu.Models.helpdeskINQ;
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
@Table(name="hd_inq_helpdesk_p", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HD_Inq_Helpdesk_Parent {
	
	private int id;
	private int state;
	private int Inq_Cat;
	private String description;
	private String attachment;
	private String email;
	private String mobile_no;
	private String inq_no;
	private int status;
	private int system_id;
	private String categary_id;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_dt;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_dt;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 
	
	public String getCategary_id() {
		return categary_id;
	}
	public void setCategary_id(String categary_id) {
		this.categary_id = categary_id;
	}
	public String getInq_no() {
		return inq_no;
	}
	public void setInq_no(String inq_no) {
		this.inq_no = inq_no;
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
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getInq_Cat() {
		return Inq_Cat;
	}
	public void setInq_Cat(int inq_Cat) {
		Inq_Cat = inq_Cat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	
}



 
