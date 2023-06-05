package com.AyushEdu.Models.helpdeskINQ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="hd_inq_in_helpdesk_p", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class HD_Inq_In_Helpdesk_P {
	
	private int id;
	private int inq_cat;
	private String description;
//	private String inq_no;
	private String created_by;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_dt;
	private String modified_by;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_dt;
	private String attachment;
	private int userid;
	private int status;
	private int state;
	private String inq_no;
	private String email;
	private String mobile_no;
	private int system_id;
	private String categary_id;
	private int module;
	private int sub_module;
	private int screen_name;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInq_cat() {
		return inq_cat;
	}
	public void setInq_cat(int inq_cat) {
		this.inq_cat = inq_cat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public String getInq_no() {
//		return inq_no;
//	}
//	public void setInq_no(String inq_no) {
//		this.inq_no = inq_no;
//	}
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
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getInq_no() {
		return inq_no;
	}
	public void setInq_no(String inq_no) {
		this.inq_no = inq_no;
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
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public int getSub_module() {
		return sub_module;
	}
	public void setSub_module(int sub_module) {
		this.sub_module = sub_module;
	}
	public int getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(int screen_name) {
		this.screen_name = screen_name;
	}
	

	
}
