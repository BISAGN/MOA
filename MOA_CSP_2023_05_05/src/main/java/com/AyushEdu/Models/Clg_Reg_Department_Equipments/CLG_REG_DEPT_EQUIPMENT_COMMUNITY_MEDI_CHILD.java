package com.AyushEdu.Models.Clg_Reg_Department_Equipments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_dept_equipment_community_medi_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD {
	
	private int id;
	private int p_id;
	private String family_welfare;
	private String source_nutrition;
	private String source_vitamin;
	private int inst_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
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
	public String getFamily_welfare() {
		return family_welfare;
	}
	public void setFamily_welfare(String family_welfare) {
		this.family_welfare = family_welfare;
	}
	public String getSource_nutrition() {
		return source_nutrition;
	}
	public void setSource_nutrition(String source_nutrition) {
		this.source_nutrition = source_nutrition;
	}
	public String getSource_vitamin() {
		return source_vitamin;
	}
	public void setSource_vitamin(String source_vitamin) {
		this.source_vitamin = source_vitamin;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
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
	
}
