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
@Table(name = "tb_book_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_BOOK_DTL {
	
	private int id;
	private String book_name;
	private String book_number ;
	private String book_author;
	private int book_price ;
	private int book_dept ;
	private Date book_published;
	private Date date_of_entered;
	private int total_book_qty ;
	private int status ;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	public String getBook_number() {
		return book_number;
	}
	public void setBook_number(String book_number) {
		this.book_number = book_number;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
	public int getBook_dept() {
		return book_dept;
	}
	public void setBook_dept(int book_dept) {
		this.book_dept = book_dept;
	}
	public Date getBook_published() {
		return book_published;
	}
	public void setBook_published(Date book_published) {
		this.book_published = book_published;
	}
	public Date getDate_of_entered() {
		return date_of_entered;
	}
	public void setDate_of_entered(Date date_of_entered) {
		this.date_of_entered = date_of_entered;
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
	public int getTotal_book_qty() {
		return total_book_qty;
	}
	public void setTotal_book_qty(int total_book_qty) {
		this.total_book_qty = total_book_qty;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}