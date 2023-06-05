package com.AyushEdu.Models.Clg_Reg_Inst_Info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_inst_info_head_of_institution_details_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD {
	
	
	
	private int id;
	private int p_id;
	private int quali_type;
	private String awarding_authority;
	private String passing_year;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	
	
	
	
	
	
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
	public int getQuali_type() {
		return quali_type;
	}
	public void setQuali_type(int quali_type) {
		this.quali_type = quali_type;
	}
	public String getAwarding_authority() {
		return awarding_authority;
	}
	public void setAwarding_authority(String awarding_authority) {
		this.awarding_authority = awarding_authority;
	}
	public String getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(String passing_year) {
		this.passing_year = passing_year;
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
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	
	

	
	
	
	
	
	
	

}
