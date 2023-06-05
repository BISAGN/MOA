package com.AyushEdu.Models.LMS_Teacher;

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
@Table(name="tb_nch_teacher_academic_experience_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD {
		
	
	private int id;
	private int p_id;
	private int type_of_experience;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String othertype_of_exp;
	private String organization_name;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date from_date_pro;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date to_date_pro;
	 private String designation_name;
	 
	
	
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
	public int getType_of_experience() {
		return type_of_experience;
	}
	public void setType_of_experience(int type_of_experience) {
		this.type_of_experience = type_of_experience;
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
	public String getOthertype_of_exp() {
		return othertype_of_exp;
	}
	public void setOthertype_of_exp(String othertype_of_exp) {
		this.othertype_of_exp = othertype_of_exp;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public Date getFrom_date_pro() {
		return from_date_pro;
	}
	public void setFrom_date_pro(Date from_date_pro) {
		this.from_date_pro = from_date_pro;
	}
	public Date getTo_date_pro() {
		return to_date_pro;
	}
	public void setTo_date_pro(Date to_date_pro) {
		this.to_date_pro = to_date_pro;
	}
	public String getDesignation_name() {
		return designation_name;
	}
	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}
	
	
	
	
	

}
