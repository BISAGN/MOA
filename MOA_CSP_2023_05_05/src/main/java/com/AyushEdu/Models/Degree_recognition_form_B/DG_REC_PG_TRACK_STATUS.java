package com.AyushEdu.Models.Degree_recognition_form_B;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_pg_track_status", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_PG_TRACK_STATUS {

	private int id;
	private int tbl_id;
	private String month_year;
	private int institute_id;
	private int university_id;
	private int inst_status;
	private int uni_status;
	private int commi_status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTbl_id() {
		return tbl_id;
	}
	public void setTbl_id(int tbl_id) {
		this.tbl_id = tbl_id;
	}
	public String getMonth_year() {
		return month_year;
	}
	public void setMonth_year(String month_year) {
		this.month_year = month_year;
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
	public int getInst_status() {
		return inst_status;
	}
	public void setInst_status(int inst_status) {
		this.inst_status = inst_status;
	}
	public int getUni_status() {
		return uni_status;
	}
	public void setUni_status(int uni_status) {
		this.uni_status = uni_status;
	}
	public int getCommi_status() {
		return commi_status;
	}
	public void setCommi_status(int commi_status) {
		this.commi_status = commi_status;
	}
	
}
