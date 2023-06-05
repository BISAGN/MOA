package com.AyushEdu.Models.Degree_recognition_form_A;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_hospital_attached_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_HOSPITAL_ATTACHED {
	private int id;
	private String name_homoeopathic_medical_clg;
	private String attached_homoeopath_hospital;
	private String super_speciality_hospital;
	private Date mou_date;
	private String copy_of_mou;
	private String name_of_hospital_staff;
	private String designation;
	private String qualification;
	private String fulltime_parttime;
	private String remarks_form_c;
	private int user_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_status;
	private int university_approved_status;
	private int council_approved_status;
	private String current_month_year;
	private int university_id;
	private String reject_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_homoeopathic_medical_clg() {
		return name_homoeopathic_medical_clg;
	}
	public void setName_homoeopathic_medical_clg(String name_homoeopathic_medical_clg) {
		this.name_homoeopathic_medical_clg = name_homoeopathic_medical_clg;
	}
	public String getAttached_homoeopath_hospital() {
		return attached_homoeopath_hospital;
	}
	public void setAttached_homoeopath_hospital(String attached_homoeopath_hospital) {
		this.attached_homoeopath_hospital = attached_homoeopath_hospital;
	}
	public String getSuper_speciality_hospital() {
		return super_speciality_hospital;
	}
	public void setSuper_speciality_hospital(String super_speciality_hospital) {
		this.super_speciality_hospital = super_speciality_hospital;
	}
	public String getName_of_hospital_staff() {
		return name_of_hospital_staff;
	}
	public void setName_of_hospital_staff(String name_of_hospital_staff) {
		this.name_of_hospital_staff = name_of_hospital_staff;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getRemarks_form_c() {
		return remarks_form_c;
	}
	public void setRemarks_form_c(String remarks_form_c) {
		this.remarks_form_c = remarks_form_c;
	}
	
	public Date getMou_date() {
		return mou_date;
	}
	public void setMou_date(Date mou_date) {
		this.mou_date = mou_date;
	}
	public String getCopy_of_mou() {
		return copy_of_mou;
	}
	public void setCopy_of_mou(String copy_of_mou) {
		this.copy_of_mou = copy_of_mou;
	}
	public String getFulltime_parttime() {
		return fulltime_parttime;
	}
	public void setFulltime_parttime(String fulltime_parttime) {
		this.fulltime_parttime = fulltime_parttime;
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
	public int getUniversity_approved_status() {
		return university_approved_status;
	}
	public void setUniversity_approved_status(int university_approved_status) {
		this.university_approved_status = university_approved_status;
	}
	public int getCouncil_approved_status() {
		return council_approved_status;
	}
	public void setCouncil_approved_status(int council_approved_status) {
		this.council_approved_status = council_approved_status;
	}
	public String getCurrent_month_year() {
		return current_month_year;
	}
	public void setCurrent_month_year(String current_month_year) {
		this.current_month_year = current_month_year;
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
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
}
