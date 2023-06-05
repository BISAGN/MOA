package com.AyushEdu.Models.Library_mgmt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

@Entity
@Table(name = "tb_smart_book_system", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class SmartBookSystem
{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
    int id;
    int book_id;
    int member_id;
    Date date_of_booked;
    Date date_of_return;
    String created_by;
	Date created_date;
	String modified_by;
	Date modified_date;
	int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public Date getDate_of_booked() {
		return date_of_booked;
	}
	public void setDate_of_booked(Date date_of_booked) {
		this.date_of_booked = date_of_booked;
	}
	public Date getDate_of_return() {
		return date_of_return;
	}
	public void setDate_of_return(Date date_of_return) {
		this.date_of_return = date_of_return;
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
	
	
	
	
	
	

}
