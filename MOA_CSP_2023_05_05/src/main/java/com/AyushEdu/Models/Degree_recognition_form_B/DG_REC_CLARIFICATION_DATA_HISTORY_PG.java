package com.AyushEdu.Models.Degree_recognition_form_B;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "dg_rec_clarification_data_history_pg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_CLARIFICATION_DATA_HISTORY_PG {
	
	private int id;
	private Date date_of_current;
	private int tbl_id;
	private String clarification_remarks;
	private int institute_id;
	private int university_id;
	private String created_by;
	private Date created_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_of_current() {
		return date_of_current;
	}
	public void setDate_of_current(Date date_of_current) {
		this.date_of_current = date_of_current;
	}
	public int getTbl_id() {
		return tbl_id;
	}
	public void setTbl_id(int tbl_id) {
		this.tbl_id = tbl_id;
	}
	public String getClarification_remarks() {
		return clarification_remarks;
	}
	public void setClarification_remarks(String clarification_remarks) {
		this.clarification_remarks = clarification_remarks;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
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
	
	

	
}
