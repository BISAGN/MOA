package com.AyushEdu.Models.Collaboration;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_col_org_sector", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_COL_ORG_SECTOR {
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	private int id;
	private String sector_type;
	private int status;
	private Date created_date;
	private String created_by;
	private String modified_by;
	private Date modified_date;
	
	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

	public int getId() {
		return id;
	}
	
	
	public String getSector_type() {
		return sector_type;
	}

	public void setSector_type(String sector_type) {
		this.sector_type = sector_type;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setId(int id) {
		this.id = id;
	}


}
	