package com.AyushEdu.Models.Clg_Reg_hos_ipd_opd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_hos_bed_existed_tbl", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_HOS_BED_EXISTED_TBL {
	private int id;
	private int p_id;
	private int sp_id;
	private int dep_id;
	
	private int existbed;
	private int addionalbed;
	private int totalbed;
	private int grand_totalexisted;
	private float bed_occupancy;
	private int status;
	private int institute_id;
//	private int month_total;
//	private int grand_total;
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
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}
	public int getExistbed() {
		return existbed;
	}
	public void setExistbed(int existbed) {
		this.existbed = existbed;
	}
	public int getAddionalbed() {
		return addionalbed;
	}
	public void setAddionalbed(int addionalbed) {
		this.addionalbed = addionalbed;
	}
	public int getTotalbed() {
		return totalbed;
	}
	public void setTotalbed(int totalbed) {
		this.totalbed = totalbed;
	}
	public int getGrand_totalexisted() {
		return grand_totalexisted;
	}
	public void setGrand_totalexisted(int grand_totalexisted) {
		this.grand_totalexisted = grand_totalexisted;
	}
	public float getBed_occupancy() {
		return bed_occupancy;
	}
	public void setBed_occupancy(float bed_occupancy) {
		this.bed_occupancy = bed_occupancy;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
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
