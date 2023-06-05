package com.AyushEdu.Models.LMS_Teacher;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_nch_teacher_quali_attach_subchild", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD {
	
	private int id;
	private int parent_id;
	private String name_of_attachment;
	private String attachment;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	private int certificate_type;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getName_of_attachment() {
		return name_of_attachment;
	}
	public void setName_of_attachment(String name_of_attachment) {
		this.name_of_attachment = name_of_attachment;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCertificate_type() {
		return certificate_type;
	}
	public void setCertificate_type(int certificate_type) {
		this.certificate_type = certificate_type;
	}
	
	
	

}
