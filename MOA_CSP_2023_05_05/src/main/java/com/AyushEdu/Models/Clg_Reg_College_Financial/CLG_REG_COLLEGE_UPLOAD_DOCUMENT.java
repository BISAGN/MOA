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
@Table(name="clg_reg_college_upload_document", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_COLLEGE_UPLOAD_DOCUMENT {
	
	private int id;
	private int p_id;
	private int s_id;
	private int institute_id;
	private String medicine_bill;
	private String book_bill;
	private String college_acct_statement;
	private String proof_esi;
	private String form_16;
	private String proof_pf;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public String getMedicine_bill() {
		return medicine_bill;
	}
	public void setMedicine_bill(String medicine_bill) {
		this.medicine_bill = medicine_bill;
	}
	public String getBook_bill() {
		return book_bill;
	}
	public void setBook_bill(String book_bill) {
		this.book_bill = book_bill;
	}
	public String getCollege_acct_statement() {
		return college_acct_statement;
	}
	public void setCollege_acct_statement(String college_acct_statement) {
		this.college_acct_statement = college_acct_statement;
	}
	public String getProof_esi() {
		return proof_esi;
	}
	public void setProof_esi(String proof_esi) {
		this.proof_esi = proof_esi;
	}
	public String getForm_16() {
		return form_16;
	}
	public void setForm_16(String form_16) {
		this.form_16 = form_16;
	}
	public String getProof_pf() {
		return proof_pf;
	}
	public void setProof_pf(String proof_pf) {
		this.proof_pf = proof_pf;
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
