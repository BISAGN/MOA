package com.AyushEdu.Models.Clg_Teaching_Staff_Info;
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
@Table(name="clg_reg_staff_salary_information", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_STAFF_SALARY_INFORMATION {
	private int id;
	private int institute_id;
	private int p_id;
	
	private String bankpaycheck;
	private String bankpayremarks;
	
	private String gpfdeductcheck;
	private String gpfdeductremarks;
	
	private String cchnormscheck;
	private String cchnormsremarks;
	
	private String staff_payscale;
	private String staff_payscale_remarks;
	
	private String payscalegradepay;
	private String payscalegradepay_remarks;
	
	private String ass_pro_pay;
	private String ass_pro_pay_remarks;
	
	private String lectass_pro_pay;
	private String lectass_pro_pay_remarks;
	
	private String bankpay_attachment;
	private String gpfdeduct_attachment;
	
	private String cchnorms_attachment;
	private String staff_payscale_attachment;
	
	private String payscalegradepay_attachment;
	private String ass_pro_payattachment;
	
	private String lectass_pro_payattachment;
	
	private int s_id;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
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
	public String getBankpaycheck() {
		return bankpaycheck;
	}
	public void setBankpaycheck(String bankpaycheck) {
		this.bankpaycheck = bankpaycheck;
	}
	public String getBankpayremarks() {
		return bankpayremarks;
	}
	public void setBankpayremarks(String bankpayremarks) {
		this.bankpayremarks = bankpayremarks;
	}
	public String getGpfdeductcheck() {
		return gpfdeductcheck;
	}
	public void setGpfdeductcheck(String gpfdeductcheck) {
		this.gpfdeductcheck = gpfdeductcheck;
	}
	public String getGpfdeductremarks() {
		return gpfdeductremarks;
	}
	public void setGpfdeductremarks(String gpfdeductremarks) {
		this.gpfdeductremarks = gpfdeductremarks;
	}
	public String getCchnormscheck() {
		return cchnormscheck;
	}
	public void setCchnormscheck(String cchnormscheck) {
		this.cchnormscheck = cchnormscheck;
	}
	public String getCchnormsremarks() {
		return cchnormsremarks;
	}
	public void setCchnormsremarks(String cchnormsremarks) {
		this.cchnormsremarks = cchnormsremarks;
	}
	public String getStaff_payscale() {
		return staff_payscale;
	}
	public void setStaff_payscale(String staff_payscale) {
		this.staff_payscale = staff_payscale;
	}
	public String getStaff_payscale_remarks() {
		return staff_payscale_remarks;
	}
	public void setStaff_payscale_remarks(String staff_payscale_remarks) {
		this.staff_payscale_remarks = staff_payscale_remarks;
	}
	public String getPayscalegradepay() {
		return payscalegradepay;
	}
	public void setPayscalegradepay(String payscalegradepay) {
		this.payscalegradepay = payscalegradepay;
	}
	public String getPayscalegradepay_remarks() {
		return payscalegradepay_remarks;
	}
	public void setPayscalegradepay_remarks(String payscalegradepay_remarks) {
		this.payscalegradepay_remarks = payscalegradepay_remarks;
	}
	public String getAss_pro_pay() {
		return ass_pro_pay;
	}
	public void setAss_pro_pay(String ass_pro_pay) {
		this.ass_pro_pay = ass_pro_pay;
	}
	public String getAss_pro_pay_remarks() {
		return ass_pro_pay_remarks;
	}
	public void setAss_pro_pay_remarks(String ass_pro_pay_remarks) {
		this.ass_pro_pay_remarks = ass_pro_pay_remarks;
	}
	public String getLectass_pro_pay() {
		return lectass_pro_pay;
	}
	public void setLectass_pro_pay(String lectass_pro_pay) {
		this.lectass_pro_pay = lectass_pro_pay;
	}
	public String getLectass_pro_pay_remarks() {
		return lectass_pro_pay_remarks;
	}
	public void setLectass_pro_pay_remarks(String lectass_pro_pay_remarks) {
		this.lectass_pro_pay_remarks = lectass_pro_pay_remarks;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getBankpay_attachment() {
		return bankpay_attachment;
	}
	public void setBankpay_attachment(String bankpay_attachment) {
		this.bankpay_attachment = bankpay_attachment;
	}
	public String getGpfdeduct_attachment() {
		return gpfdeduct_attachment;
	}
	public void setGpfdeduct_attachment(String gpfdeduct_attachment) {
		this.gpfdeduct_attachment = gpfdeduct_attachment;
	}
	public String getCchnorms_attachment() {
		return cchnorms_attachment;
	}
	public void setCchnorms_attachment(String cchnorms_attachment) {
		this.cchnorms_attachment = cchnorms_attachment;
	}
	public String getStaff_payscale_attachment() {
		return staff_payscale_attachment;
	}
	public void setStaff_payscale_attachment(String staff_payscale_attachment) {
		this.staff_payscale_attachment = staff_payscale_attachment;
	}
	public String getPayscalegradepay_attachment() {
		return payscalegradepay_attachment;
	}
	public void setPayscalegradepay_attachment(String payscalegradepay_attachment) {
		this.payscalegradepay_attachment = payscalegradepay_attachment;
	}
	public String getAss_pro_payattachment() {
		return ass_pro_payattachment;
	}
	public void setAss_pro_payattachment(String ass_pro_payattachment) {
		this.ass_pro_payattachment = ass_pro_payattachment;
	}
	public String getLectass_pro_payattachment() {
		return lectass_pro_payattachment;
	}
	public void setLectass_pro_payattachment(String lectass_pro_payattachment) {
		this.lectass_pro_payattachment = lectass_pro_payattachment;
	}

	
}
