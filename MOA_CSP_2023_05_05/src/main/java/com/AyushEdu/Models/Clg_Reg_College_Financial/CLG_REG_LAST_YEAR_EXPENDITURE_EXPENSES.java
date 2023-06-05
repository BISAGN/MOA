package com.AyushEdu.Models.Clg_Reg_College_Financial;
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
@Table(name="clg_reg_last_year_expenditure_expenses", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_LAST_YEAR_EXPENDITURE_EXPENSES {
	private int id;
	private int s_id;
	private String mis_year;
	private String mis_attachment;
	private int institute_id;
	private String misce_expenditure;
	private int total_expenditure;
	private int grand_total_expenditure;
	private int total_income_last_year;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getMis_year() {
		return mis_year;
	}
	public void setMis_year(String mis_year) {
		this.mis_year = mis_year;
	}
	public String getMis_attachment() {
		return mis_attachment;
	}
	public void setMis_attachment(String mis_attachment) {
		this.mis_attachment = mis_attachment;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
	public String getMisce_expenditure() {
		return misce_expenditure;
	}
	public void setMisce_expenditure(String misce_expenditure) {
		this.misce_expenditure = misce_expenditure;
	}
	public int getTotal_expenditure() {
		return total_expenditure;
	}
	public void setTotal_expenditure(int total_expenditure) {
		this.total_expenditure = total_expenditure;
	}
	public int getGrand_total_expenditure() {
		return grand_total_expenditure;
	}
	public void setGrand_total_expenditure(int grand_total_expenditure) {
		this.grand_total_expenditure = grand_total_expenditure;
	}
	public int getTotal_income_last_year() {
		return total_income_last_year;
	}
	public void setTotal_income_last_year(int total_income_last_year) {
		this.total_income_last_year = total_income_last_year;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	
	
}
