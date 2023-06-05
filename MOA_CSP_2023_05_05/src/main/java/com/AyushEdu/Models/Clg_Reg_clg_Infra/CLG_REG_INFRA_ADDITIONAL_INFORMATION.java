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
@Table(name="clg_reg_infra_additional_information", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_ADDITIONAL_INFORMATION {
	
	private int id;
	private int p_id;
	private int trasport_facility;
	private int sports_facility;
	private int inspection_pending;
	private int penalty_amount;
	private int swasthya_rakshan_programme;
	private int compliance_report;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
	private int ambulance_facility;
	private int compliance_report_check;
	private String compliance_report_doc;
	private int institute_id;
	
	
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
	public int getTrasport_facility() {
		return trasport_facility;
	}
	public void setTrasport_facility(int trasport_facility) {
		this.trasport_facility = trasport_facility;
	}
	public int getSports_facility() {
		return sports_facility;
	}
	public void setSports_facility(int sports_facility) {
		this.sports_facility = sports_facility;
	}
	public int getInspection_pending() {
		return inspection_pending;
	}
	public void setInspection_pending(int inspection_pending) {
		this.inspection_pending = inspection_pending;
	}
	public int getPenalty_amount() {
		return penalty_amount;
	}
	public void setPenalty_amount(int penalty_amount) {
		this.penalty_amount = penalty_amount;
	}
	public int getSwasthya_rakshan_programme() {
		return swasthya_rakshan_programme;
	}
	public void setSwasthya_rakshan_programme(int swasthya_rakshan_programme) {
		this.swasthya_rakshan_programme = swasthya_rakshan_programme;
	}
	public int getCompliance_report() {
		return compliance_report;
	}
	public void setCompliance_report(int compliance_report) {
		this.compliance_report = compliance_report;
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
	public int getAmbulance_facility() {
		return ambulance_facility;
	}
	public void setAmbulance_facility(int ambulance_facility) {
		this.ambulance_facility = ambulance_facility;
	}
	public int getCompliance_report_check() {
		return compliance_report_check;
	}
	public void setCompliance_report_check(int compliance_report_check) {
		this.compliance_report_check = compliance_report_check;
	}
	public String getCompliance_report_doc() {
		return compliance_report_doc;
	}
	public void setCompliance_report_doc(String compliance_report_doc) {
		this.compliance_report_doc = compliance_report_doc;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
	
}

