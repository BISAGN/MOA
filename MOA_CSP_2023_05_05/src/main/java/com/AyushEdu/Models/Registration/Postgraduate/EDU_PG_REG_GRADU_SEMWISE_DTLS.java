package com.AyushEdu.Models.Registration.Postgraduate;

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
@Table(name="edu_pg_reg_gradu_semwise_dtls", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_PG_REG_GRADU_SEMWISE_DTLS {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private String subject;
	private String semwise_no_of_atmpts;
	
	private int maxmark_y1_;
	private int obtainmark_y1_;
	
	private int maxmark_y2_;
	private int obtainmark_y2_;
	
	private int maxmark_y3_;
	private int obtainmark_y3_;
	
	private int maxmark_y4_;
	private int obtainmark_y4_;
	
	private String percentage_of_marks;
	private String remarks;
	
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private Integer p_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSemwise_no_of_atmpts() {
		return semwise_no_of_atmpts;
	}
	public void setSemwise_no_of_atmpts(String semwise_no_of_atmpts) {
		this.semwise_no_of_atmpts = semwise_no_of_atmpts;
	}
	public int getMaxmark_y1_() {
		return maxmark_y1_;
	}
	public void setMaxmark_y1_(int maxmark_y1_) {
		this.maxmark_y1_ = maxmark_y1_;
	}
	public int getObtainmark_y1_() {
		return obtainmark_y1_;
	}
	public void setObtainmark_y1_(int obtainmark_y1_) {
		this.obtainmark_y1_ = obtainmark_y1_;
	}
	public int getMaxmark_y2_() {
		return maxmark_y2_;
	}
	public void setMaxmark_y2_(int maxmark_y2_) {
		this.maxmark_y2_ = maxmark_y2_;
	}
	public int getObtainmark_y2_() {
		return obtainmark_y2_;
	}
	public void setObtainmark_y2_(int obtainmark_y2_) {
		this.obtainmark_y2_ = obtainmark_y2_;
	}
	public int getMaxmark_y3_() {
		return maxmark_y3_;
	}
	public void setMaxmark_y3_(int maxmark_y3_) {
		this.maxmark_y3_ = maxmark_y3_;
	}
	public int getObtainmark_y3_() {
		return obtainmark_y3_;
	}
	public void setObtainmark_y3_(int obtainmark_y3_) {
		this.obtainmark_y3_ = obtainmark_y3_;
	}
	public int getMaxmark_y4_() {
		return maxmark_y4_;
	}
	public void setMaxmark_y4_(int maxmark_y4_) {
		this.maxmark_y4_ = maxmark_y4_;
	}
	public int getObtainmark_y4_() {
		return obtainmark_y4_;
	}
	public void setObtainmark_y4_(int obtainmark_y4_) {
		this.obtainmark_y4_ = obtainmark_y4_;
	}
	public String getPercentage_of_marks() {
		return percentage_of_marks;
	}
	public void setPercentage_of_marks(String percentage_of_marks) {
		this.percentage_of_marks = percentage_of_marks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	
}
