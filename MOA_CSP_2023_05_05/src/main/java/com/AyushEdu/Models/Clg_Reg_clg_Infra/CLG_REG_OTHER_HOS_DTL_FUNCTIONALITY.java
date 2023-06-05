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
@Table(name="clg_reg_other_hos_dtl_functionality", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY {
	
	private int id;
	private int s_id;
	private int p_id;
	private int name_of_opd;
	private String name_of_opd_remark;
	private int opd_case_register;
	private String opd_case_register_remark;
	private int opd_medicine;
	private String opd_medicine_remark;
	private int case_receipt_opd;
	private String case_receipt_opd_remark;
	private int name_of_ipd;
	private String name_of_ipd_remark;
	private int ipd_case_sheets;
	private String ipd_case_sheets_remark;
	private int discharge_cards;
	private String discharge_cards_remark;
	private int ip_medicine;
	private String ip_medicine_remark;
	private int nursing_duty_roster;
	private String nursing_duty_roster_remark;
	private int doctor_duty_roaster;
	private String doctor_duty_roaster_remark;
	private int ip_diet_register;
	private String ip_diet_register_remark;
	private int cash_receipt_ipd;
	private String cash_receipt_ipd_remark;
	private String name_of_multispecialty_hospital;
	private String address_of_multispecialty_hospital;
	private String doc_of_multispecialty_hospital;
	private String ipd_diet_register_doc;
	private Date date_of_mou_sign;
	private String validity_of_mou;
	private String area_of_mou;
	private String investigation_of_hospital;
	private String training_details;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
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
	public int getName_of_opd() {
		return name_of_opd;
	}
	public void setName_of_opd(int name_of_opd) {
		this.name_of_opd = name_of_opd;
	}
	public String getName_of_opd_remark() {
		return name_of_opd_remark;
	}
	public void setName_of_opd_remark(String name_of_opd_remark) {
		this.name_of_opd_remark = name_of_opd_remark;
	}
	public int getOpd_case_register() {
		return opd_case_register;
	}
	public void setOpd_case_register(int opd_case_register) {
		this.opd_case_register = opd_case_register;
	}
	public String getOpd_case_register_remark() {
		return opd_case_register_remark;
	}
	public void setOpd_case_register_remark(String opd_case_register_remark) {
		this.opd_case_register_remark = opd_case_register_remark;
	}
	public int getOpd_medicine() {
		return opd_medicine;
	}
	public void setOpd_medicine(int opd_medicine) {
		this.opd_medicine = opd_medicine;
	}
	public String getOpd_medicine_remark() {
		return opd_medicine_remark;
	}
	public void setOpd_medicine_remark(String opd_medicine_remark) {
		this.opd_medicine_remark = opd_medicine_remark;
	}
	public int getCase_receipt_opd() {
		return case_receipt_opd;
	}
	public void setCase_receipt_opd(int case_receipt_opd) {
		this.case_receipt_opd = case_receipt_opd;
	}
	public String getCase_receipt_opd_remark() {
		return case_receipt_opd_remark;
	}
	public void setCase_receipt_opd_remark(String case_receipt_opd_remark) {
		this.case_receipt_opd_remark = case_receipt_opd_remark;
	}
	public int getName_of_ipd() {
		return name_of_ipd;
	}
	public void setName_of_ipd(int name_of_ipd) {
		this.name_of_ipd = name_of_ipd;
	}
	public String getName_of_ipd_remark() {
		return name_of_ipd_remark;
	}
	public void setName_of_ipd_remark(String name_of_ipd_remark) {
		this.name_of_ipd_remark = name_of_ipd_remark;
	}
	public int getIpd_case_sheets() {
		return ipd_case_sheets;
	}
	public void setIpd_case_sheets(int ipd_case_sheets) {
		this.ipd_case_sheets = ipd_case_sheets;
	}
	public String getIpd_case_sheets_remark() {
		return ipd_case_sheets_remark;
	}
	public void setIpd_case_sheets_remark(String ipd_case_sheets_remark) {
		this.ipd_case_sheets_remark = ipd_case_sheets_remark;
	}
	public int getDischarge_cards() {
		return discharge_cards;
	}
	public void setDischarge_cards(int discharge_cards) {
		this.discharge_cards = discharge_cards;
	}
	public String getDischarge_cards_remark() {
		return discharge_cards_remark;
	}
	public void setDischarge_cards_remark(String discharge_cards_remark) {
		this.discharge_cards_remark = discharge_cards_remark;
	}
	public int getIp_medicine() {
		return ip_medicine;
	}
	public void setIp_medicine(int ip_medicine) {
		this.ip_medicine = ip_medicine;
	}
	public String getIp_medicine_remark() {
		return ip_medicine_remark;
	}
	public void setIp_medicine_remark(String ip_medicine_remark) {
		this.ip_medicine_remark = ip_medicine_remark;
	}
	public int getNursing_duty_roster() {
		return nursing_duty_roster;
	}
	public void setNursing_duty_roster(int nursing_duty_roster) {
		this.nursing_duty_roster = nursing_duty_roster;
	}
	public String getNursing_duty_roster_remark() {
		return nursing_duty_roster_remark;
	}
	public void setNursing_duty_roster_remark(String nursing_duty_roster_remark) {
		this.nursing_duty_roster_remark = nursing_duty_roster_remark;
	}
	public int getDoctor_duty_roaster() {
		return doctor_duty_roaster;
	}
	public void setDoctor_duty_roaster(int doctor_duty_roaster) {
		this.doctor_duty_roaster = doctor_duty_roaster;
	}
	public String getDoctor_duty_roaster_remark() {
		return doctor_duty_roaster_remark;
	}
	public void setDoctor_duty_roaster_remark(String doctor_duty_roaster_remark) {
		this.doctor_duty_roaster_remark = doctor_duty_roaster_remark;
	}
	public int getIp_diet_register() {
		return ip_diet_register;
	}
	public void setIp_diet_register(int ip_diet_register) {
		this.ip_diet_register = ip_diet_register;
	}
	public String getIp_diet_register_remark() {
		return ip_diet_register_remark;
	}
	public void setIp_diet_register_remark(String ip_diet_register_remark) {
		this.ip_diet_register_remark = ip_diet_register_remark;
	}
	public int getCash_receipt_ipd() {
		return cash_receipt_ipd;
	}
	public void setCash_receipt_ipd(int cash_receipt_ipd) {
		this.cash_receipt_ipd = cash_receipt_ipd;
	}
	public String getCash_receipt_ipd_remark() {
		return cash_receipt_ipd_remark;
	}
	public void setCash_receipt_ipd_remark(String cash_receipt_ipd_remark) {
		this.cash_receipt_ipd_remark = cash_receipt_ipd_remark;
	}
	public String getName_of_multispecialty_hospital() {
		return name_of_multispecialty_hospital;
	}
	public void setName_of_multispecialty_hospital(String name_of_multispecialty_hospital) {
		this.name_of_multispecialty_hospital = name_of_multispecialty_hospital;
	}
	public String getAddress_of_multispecialty_hospital() {
		return address_of_multispecialty_hospital;
	}
	public void setAddress_of_multispecialty_hospital(String address_of_multispecialty_hospital) {
		this.address_of_multispecialty_hospital = address_of_multispecialty_hospital;
	}
	public String getDoc_of_multispecialty_hospital() {
		return doc_of_multispecialty_hospital;
	}
	public void setDoc_of_multispecialty_hospital(String doc_of_multispecialty_hospital) {
		this.doc_of_multispecialty_hospital = doc_of_multispecialty_hospital;
	}
	public String getIpd_diet_register_doc() {
		return ipd_diet_register_doc;
	}
	public void setIpd_diet_register_doc(String ipd_diet_register_doc) {
		this.ipd_diet_register_doc = ipd_diet_register_doc;
	}
	public Date getDate_of_mou_sign() {
		return date_of_mou_sign;
	}
	public void setDate_of_mou_sign(Date date_of_mou_sign) {
		this.date_of_mou_sign = date_of_mou_sign;
	}
	public String getValidity_of_mou() {
		return validity_of_mou;
	}
	public void setValidity_of_mou(String validity_of_mou) {
		this.validity_of_mou = validity_of_mou;
	}
	public String getArea_of_mou() {
		return area_of_mou;
	}
	public void setArea_of_mou(String area_of_mou) {
		this.area_of_mou = area_of_mou;
	}
	public String getInvestigation_of_hospital() {
		return investigation_of_hospital;
	}
	public void setInvestigation_of_hospital(String investigation_of_hospital) {
		this.investigation_of_hospital = investigation_of_hospital;
	}
	public String getTraining_details() {
		return training_details;
	}
	public void setTraining_details(String training_details) {
		this.training_details = training_details;
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
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
}
