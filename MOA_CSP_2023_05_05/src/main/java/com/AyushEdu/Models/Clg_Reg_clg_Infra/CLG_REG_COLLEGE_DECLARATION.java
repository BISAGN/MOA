package com.AyushEdu.Models.Clg_Reg_clg_Infra;

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
@Table(name="clg_reg_college_declaration", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_COLLEGE_DECLARATION {
	
	private int id;
	private int s_id;
	private int institute_id;
	private String notarizedundertaking;
	private String notarizedaffidavit;
	private int prin_declaration;
	private int mange_declaration;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public String getNotarizedundertaking() {
		return notarizedundertaking;
	}
	public void setNotarizedundertaking(String notarizedundertaking) {
		this.notarizedundertaking = notarizedundertaking;
	}
	public String getNotarizedaffidavit() {
		return notarizedaffidavit;
	}
	public void setNotarizedaffidavit(String notarizedaffidavit) {
		this.notarizedaffidavit = notarizedaffidavit;
	}
	public int getPrin_declaration() {
		return prin_declaration;
	}
	public void setPrin_declaration(int prin_declaration) {
		this.prin_declaration = prin_declaration;
	}
	public int getMange_declaration() {
		return mange_declaration;
	}
	public void setMange_declaration(int mange_declaration) {
		this.mange_declaration = mange_declaration;
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
