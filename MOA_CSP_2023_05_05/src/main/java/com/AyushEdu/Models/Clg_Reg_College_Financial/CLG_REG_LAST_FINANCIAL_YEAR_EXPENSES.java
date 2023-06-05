package com.AyushEdu.Models.Clg_Reg_College_Financial;
import java.math.BigInteger;
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
@Table(name="clg_reg_last_financial_year_expenses", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES {
	private int id;
	private int p_id;
	private int s_id;
	private int institute_id;
	private String year;
	private String staff_salary;
	private BigInteger total_salary;
	private String attachment;
	private String  salary_statment;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStaff_salary() {
		return staff_salary;
	}
	public void setStaff_salary(String staff_salary) {
		this.staff_salary = staff_salary;
	}
	
	public BigInteger getTotal_salary() {
		return total_salary;
	}
	public void setTotal_salary(BigInteger total_salary) {
		this.total_salary = total_salary;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
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
	public String getSalary_statment() {
		return salary_statment;
	}
	public void setSalary_statment(String salary_statment) {
		this.salary_statment = salary_statment;
	}
	
}
