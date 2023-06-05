
package com.AyushEdu.Models.Collaboration;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_col_org_colb", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_COL_ORG_COLB {
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	
	private int id;
	private int userid;
	private String collaborationtype;
	private String collaborationsector;
	private String collaborationcategory;
	private String collaborationtitle;
	private String collaborationdescription;
	private Date from_date;
	private Date to_date;
	private String collaborationsign;
	private Date created_date;
	private String created_by;
	private String modified_by;
	private Date modified_date;
	
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
//		private Date date_of_birth;
	
	public Date getCreated_date() {
		return created_date;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCollaborationtype() {
		return collaborationtype;
	}

	public void setCollaborationtype(String collaborationtype) {
		this.collaborationtype = collaborationtype;
	}

	public String getCollaborationsector() {
		return collaborationsector;
	}

	public void setCollaborationsector(String collaborationsector) {
		this.collaborationsector = collaborationsector;
	}

	public String getCollaborationcategory() {
		return collaborationcategory;
	}

	public void setCollaborationcategory(String collaborationcategory) {
		this.collaborationcategory = collaborationcategory;
	}

	public String getCollaborationtitle() {
		return collaborationtitle;
	}

	public void setCollaborationtitle(String collaborationtitle) {
		this.collaborationtitle = collaborationtitle;
	}

	public String getCollaborationdescription() {
		return collaborationdescription;
	}

	public void setCollaborationdescription(String collaborationdescription) {
		this.collaborationdescription = collaborationdescription;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getCollaborationsign() {
		return collaborationsign;
	}

	public void setCollaborationsign(String collaborationsign) {
		this.collaborationsign = collaborationsign;
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
	
	public void setId(int id) {
		this.id = id;
	}


}
	