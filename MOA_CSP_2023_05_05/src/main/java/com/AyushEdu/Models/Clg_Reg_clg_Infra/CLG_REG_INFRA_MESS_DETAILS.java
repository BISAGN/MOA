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
@Table(name="clg_reg_infra_mess_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_MESS_DETAILS {
	
	private int id;
	private int s_id;
	private int p_id;
	private int institute_id;
	private String type_of_mess;
	private String mess_total_area;
	private int mess_total_cooks;
	private int mess_total_capacity;
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
	public String getType_of_mess() {
		return type_of_mess;
	}
	public void setType_of_mess(String type_of_mess) {
		this.type_of_mess = type_of_mess;
	}
	public String getMess_total_area() {
		return mess_total_area;
	}
	public void setMess_total_area(String mess_total_area) {
		this.mess_total_area = mess_total_area;
	}
	public int getMess_total_cooks() {
		return mess_total_cooks;
	}
	public void setMess_total_cooks(int mess_total_cooks) {
		this.mess_total_cooks = mess_total_cooks;
	}
	public int getMess_total_capacity() {
		return mess_total_capacity;
	}
	public void setMess_total_capacity(int mess_total_capacity) {
		this.mess_total_capacity = mess_total_capacity;
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

