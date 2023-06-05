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
@Table(name="clg_reg_student_dtl_upload_doc", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_STUDENT_DTL_UPLOAD_DOC {
	
	private int id;
	private int institute_id;
	private int intimatedcheck;
	private int undertakingcheck;
	private String lastfiveguide;
	private String neet_score;
	private String undertakingofstudent;
	private String biometricattendance;
	private String total_intern;
	private int pro_regcheck;
	private int rotationcheck;
	private String internsdutyopd;
	private String internsdutyipd;
	private int migrationcheck;
	private String migration_list;
	private String prescribe;
	private String seminar;
	private int internship_not_completed;
	private String house_job;
	private int no_house_job;
	private String graded_teaching;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getIntimatedcheck() {
		return intimatedcheck;
	}
	public void setIntimatedcheck(int intimatedcheck) {
		this.intimatedcheck = intimatedcheck;
	}
	public int getUndertakingcheck() {
		return undertakingcheck;
	}
	public void setUndertakingcheck(int undertakingcheck) {
		this.undertakingcheck = undertakingcheck;
	}
	public String getLastfiveguide() {
		return lastfiveguide;
	}
	public void setLastfiveguide(String lastfiveguide) {
		this.lastfiveguide = lastfiveguide;
	}
	public String getNeet_score() {
		return neet_score;
	}
	public void setNeet_score(String neet_score) {
		this.neet_score = neet_score;
	}
	public String getUndertakingofstudent() {
		return undertakingofstudent;
	}
	public void setUndertakingofstudent(String undertakingofstudent) {
		this.undertakingofstudent = undertakingofstudent;
	}
	public String getBiometricattendance() {
		return biometricattendance;
	}
	public void setBiometricattendance(String biometricattendance) {
		this.biometricattendance = biometricattendance;
	}
	public String getTotal_intern() {
		return total_intern;
	}
	public void setTotal_intern(String total_intern) {
		this.total_intern = total_intern;
	}
	public int getPro_regcheck() {
		return pro_regcheck;
	}
	public void setPro_regcheck(int pro_regcheck) {
		this.pro_regcheck = pro_regcheck;
	}
	public int getRotationcheck() {
		return rotationcheck;
	}
	public void setRotationcheck(int rotationcheck) {
		this.rotationcheck = rotationcheck;
	}
	public String getInternsdutyopd() {
		return internsdutyopd;
	}
	public void setInternsdutyopd(String internsdutyopd) {
		this.internsdutyopd = internsdutyopd;
	}
	public String getInternsdutyipd() {
		return internsdutyipd;
	}
	public void setInternsdutyipd(String internsdutyipd) {
		this.internsdutyipd = internsdutyipd;
	}
	public int getMigrationcheck() {
		return migrationcheck;
	}
	public void setMigrationcheck(int migrationcheck) {
		this.migrationcheck = migrationcheck;
	}
	public String getMigration_list() {
		return migration_list;
	}
	public void setMigration_list(String migration_list) {
		this.migration_list = migration_list;
	}
	public String getPrescribe() {
		return prescribe;
	}
	public void setPrescribe(String prescribe) {
		this.prescribe = prescribe;
	}
	public String getSeminar() {
		return seminar;
	}
	public void setSeminar(String seminar) {
		this.seminar = seminar;
	}
	public int getInternship_not_completed() {
		return internship_not_completed;
	}
	public void setInternship_not_completed(int internship_not_completed) {
		this.internship_not_completed = internship_not_completed;
	}
	public String getHouse_job() {
		return house_job;
	}
	public void setHouse_job(String house_job) {
		this.house_job = house_job;
	}
	public int getNo_house_job() {
		return no_house_job;
	}
	public void setNo_house_job(int no_house_job) {
		this.no_house_job = no_house_job;
	}
	public String getGraded_teaching() {
		return graded_teaching;
	}
	public void setGraded_teaching(String graded_teaching) {
		this.graded_teaching = graded_teaching;
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
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	

}
