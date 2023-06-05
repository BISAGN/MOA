package com.AyushEdu.Models.Degree_recognition_NCISM;


import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_medical_qua_diaplay_35_ncism", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })


public class DG_REC_MEDICAL_QUA_DISPLAY_35 {
	
	
	private int id;
	private String state;
	private String university_name;
	private String college_name ;
	private String quali_medical ;
	private String abbrve_medical ;
	private String sequ_code;
	private String velidity_per;
	private String degree_certificate;
	private String copy_notification;
    private int status;
	private int user_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getQuali_medical() {
		return quali_medical;
	}
	public void setQuali_medical(String quali_medical) {
		this.quali_medical = quali_medical;
	}
	public String getAbbrve_medical() {
		return abbrve_medical;
	}
	public void setAbbrve_medical(String abbrve_medical) {
		this.abbrve_medical = abbrve_medical;
	}
	public String getSequ_code() {
		return sequ_code;
	}
	public void setSequ_code(String sequ_code) {
		this.sequ_code = sequ_code;
	}
	public String getVelidity_per() {
		return velidity_per;
	}
	public void setVelidity_per(String velidity_per) {
		this.velidity_per = velidity_per;
	}
	public String getDegree_certificate() {
		return degree_certificate;
	}
	public void setDegree_certificate(String degree_certificate) {
		this.degree_certificate = degree_certificate;
	}
	public String getCopy_notification() {
		return copy_notification;
	}
	public void setCopy_notification(String copy_notification) {
		this.copy_notification = copy_notification;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
