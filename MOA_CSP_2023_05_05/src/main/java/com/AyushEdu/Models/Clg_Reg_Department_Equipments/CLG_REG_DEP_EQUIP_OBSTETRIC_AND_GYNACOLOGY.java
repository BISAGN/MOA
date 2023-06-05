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
@Table(name="clg_reg_dep_equip_obstetric_and_gynacology", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY {
	
	

	private int id;
	private String no_of_equip_identi;
	private String additional_item;
	private String copy_of_alchoho;
	private String photographs_of_cadavers;
	private String upload_purchase_bill;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	private int s_id;
	private int userid;
	private int status;
	
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo_of_equip_identi() {
		return no_of_equip_identi;
	}
	public void setNo_of_equip_identi(String no_of_equip_identi) {
		this.no_of_equip_identi = no_of_equip_identi;
	}
	public String getAdditional_item() {
		return additional_item;
	}
	public void setAdditional_item(String additional_item) {
		this.additional_item = additional_item;
	}
	public String getCopy_of_alchoho() {
		return copy_of_alchoho;
	}
	public void setCopy_of_alchoho(String copy_of_alchoho) {
		this.copy_of_alchoho = copy_of_alchoho;
	}
	public String getPhotographs_of_cadavers() {
		return photographs_of_cadavers;
	}
	public void setPhotographs_of_cadavers(String photographs_of_cadavers) {
		this.photographs_of_cadavers = photographs_of_cadavers;
	}
	public String getUpload_purchase_bill() {
		return upload_purchase_bill;
	}
	public void setUpload_purchase_bill(String upload_purchase_bill) {
		this.upload_purchase_bill = upload_purchase_bill;
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
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

}
