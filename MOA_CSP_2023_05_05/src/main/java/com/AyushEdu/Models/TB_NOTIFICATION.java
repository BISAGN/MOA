package com.AyushEdu.Models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_notification", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_NOTIFICATION {
	
	private int id;
	private String from_name_send;
	private String to_name_sent;
	private String status;
	private String message;
	private String created_by;
	 @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	 private String to_name_receive;
	 private String url_id;
	 private String url_value;
	 private String role;
	 
	 
	 @Id
	 @GeneratedValue(strategy = IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom_name_send() {
		return from_name_send;
	}
	public void setFrom_name_send(String from_name_send) {
		this.from_name_send = from_name_send;
	}
	public String getTo_name_sent() {
		return to_name_sent;
	}
	public void setTo_name_sent(String to_name_sent) {
		this.to_name_sent = to_name_sent;
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
	public String getTo_name_receive() {
		return to_name_receive;
	}
	public void setTo_name_receive(String to_name_receive) {
		this.to_name_receive = to_name_receive;
	}
	public String getUrl_id() {
		return url_id;
	}
	public void setUrl_id(String url_id) {
		this.url_id = url_id;
	}
	public String getUrl_value() {
		return url_value;
	}
	public void setUrl_value(String url_value) {
		this.url_value = url_value;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	 
	
}
