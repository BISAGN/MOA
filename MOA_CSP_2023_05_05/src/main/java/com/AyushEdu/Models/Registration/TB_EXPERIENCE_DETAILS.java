package com.AyushEdu.Models.Registration;


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
@Table(name = "tb_total_experience", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_EXPERIENCE_DETAILS {
	
	private int id;
	private String exp_emp_name;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date exp_emp_fromdate;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date exp_emp_todate;
	private int exp_user_id;
	private String created_by;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modified_date;
	private String exp_emp_document;
	private int exp_total_month;
	private int exp_total_year;
	private int exp_total_day;
	private int p_id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExp_emp_name() {
		return exp_emp_name;
	}

	public void setExp_emp_name(String exp_emp_name) {
		this.exp_emp_name = exp_emp_name;
	}

	public Date getExp_emp_fromdate() {
		return exp_emp_fromdate;
	}

	public void setExp_emp_fromdate(Date exp_emp_fromdate) {
		this.exp_emp_fromdate = exp_emp_fromdate;
	}

	public Date getExp_emp_todate() {
		return exp_emp_todate;
	}

	public void setExp_emp_todate(Date exp_emp_todate) {
		this.exp_emp_todate = exp_emp_todate;
	}

	public int getExp_user_id() {
		return exp_user_id;
	}

	public void setExp_user_id(int exp_user_id) {
		this.exp_user_id = exp_user_id;
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

	public String getExp_emp_document() {
		return exp_emp_document;
	}

	public void setExp_emp_document(String exp_emp_document) {
		this.exp_emp_document = exp_emp_document;
	}

	public int getExp_total_month() {
		return exp_total_month;
	}

	public void setExp_total_month(int exp_total_month) {
		this.exp_total_month = exp_total_month;
	}

	public int getExp_total_year() {
		return exp_total_year;
	}

	public void setExp_total_year(int exp_total_year) {
		this.exp_total_year = exp_total_year;
	}

	public int getExp_total_day() {
		return exp_total_day;
	}

	public void setExp_total_day(int exp_total_day) {
		this.exp_total_day = exp_total_day;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
}
