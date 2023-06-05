package com.AyushEdu.Models;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_marque", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class TB_MAERQUE {
	
	
	private int id;
	private String msg;
	private Date from_date;
	private Date to_date;
	private String marque_for;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int highlight;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getMarque_for() {
		return marque_for;
	}
	public void setMarque_for(String marque_for) {
		this.marque_for = marque_for;
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
	public int getHighlight() {
		return highlight;
	}
	public void setHighlight(int highlight) {
		this.highlight = highlight;
	}
	
	
}
