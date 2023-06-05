package com.AyushEdu.Models.Library_mgmt;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_member_book_req", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_MEMBER_BOOK_REQ {

	
	private int id;
	private String member_id;
	private String book_select ;
	private String book_charges;
	private Date book_req_date ;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int return_status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBook_select() {
		return book_select;
	}
	public void setBook_select(String book_select) {
		this.book_select = book_select;
	}
	public String getBook_charges() {
		return book_charges;
	}
	public void setBook_charges(String book_charges) {
		this.book_charges = book_charges;
	}
	public Date getBook_req_date() {
		return book_req_date;
	}
	public void setBook_req_date(Date book_req_date) {
		this.book_req_date = book_req_date;
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
	public int getReturn_status() {
		return return_status;
	}
	public void setReturn_status(int return_status) {
		this.return_status = return_status;
	}
	

	
}
