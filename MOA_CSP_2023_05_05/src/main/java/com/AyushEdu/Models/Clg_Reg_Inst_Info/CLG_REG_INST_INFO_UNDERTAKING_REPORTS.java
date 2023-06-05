package com.AyushEdu.Models.Clg_Reg_Inst_Info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="clg_reg_inst_info_undertaking_reports", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_UNDERTAKING_REPORTS {
	
	
	private int id;
	private int p_id;
	private String reg_certi_of_society;
	private String copy_of_society;
	private String undertaking_letter_furnish_bank_guarantee;
	private String noc_doc_state_gov;
	private String affiliaion_doc_concern_uni;
	
	private String ayush_permission_letter;
	private String upload_court_order;
	private String land_document_clg;
	private String building_plan;
	private String statement_doc_architect;
	
	private String inst_not_admit_stu_without_permission;
	private String audited_balance_sheet;
	private String annual_report;
	private String selection_of_students;
	private String additional_document;
	
	
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	
	
	
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
	public String getReg_certi_of_society() {
		return reg_certi_of_society;
	}
	public void setReg_certi_of_society(String reg_certi_of_society) {
		this.reg_certi_of_society = reg_certi_of_society;
	}
	public String getCopy_of_society() {
		return copy_of_society;
	}
	public void setCopy_of_society(String copy_of_society) {
		this.copy_of_society = copy_of_society;
	}
	public String getUndertaking_letter_furnish_bank_guarantee() {
		return undertaking_letter_furnish_bank_guarantee;
	}
	public void setUndertaking_letter_furnish_bank_guarantee(String undertaking_letter_furnish_bank_guarantee) {
		this.undertaking_letter_furnish_bank_guarantee = undertaking_letter_furnish_bank_guarantee;
	}
	public String getNoc_doc_state_gov() {
		return noc_doc_state_gov;
	}
	public void setNoc_doc_state_gov(String noc_doc_state_gov) {
		this.noc_doc_state_gov = noc_doc_state_gov;
	}
	public String getAffiliaion_doc_concern_uni() {
		return affiliaion_doc_concern_uni;
	}
	public void setAffiliaion_doc_concern_uni(String affiliaion_doc_concern_uni) {
		this.affiliaion_doc_concern_uni = affiliaion_doc_concern_uni;
	}
	public String getAyush_permission_letter() {
		return ayush_permission_letter;
	}
	public void setAyush_permission_letter(String ayush_permission_letter) {
		this.ayush_permission_letter = ayush_permission_letter;
	}
	public String getUpload_court_order() {
		return upload_court_order;
	}
	public void setUpload_court_order(String upload_court_order) {
		this.upload_court_order = upload_court_order;
	}
	public String getLand_document_clg() {
		return land_document_clg;
	}
	public void setLand_document_clg(String land_document_clg) {
		this.land_document_clg = land_document_clg;
	}
	public String getBuilding_plan() {
		return building_plan;
	}
	public void setBuilding_plan(String building_plan) {
		this.building_plan = building_plan;
	}
	public String getStatement_doc_architect() {
		return statement_doc_architect;
	}
	public void setStatement_doc_architect(String statement_doc_architect) {
		this.statement_doc_architect = statement_doc_architect;
	}
	public String getInst_not_admit_stu_without_permission() {
		return inst_not_admit_stu_without_permission;
	}
	public void setInst_not_admit_stu_without_permission(String inst_not_admit_stu_without_permission) {
		this.inst_not_admit_stu_without_permission = inst_not_admit_stu_without_permission;
	}
	public String getAudited_balance_sheet() {
		return audited_balance_sheet;
	}
	public void setAudited_balance_sheet(String audited_balance_sheet) {
		this.audited_balance_sheet = audited_balance_sheet;
	}
	public String getAnnual_report() {
		return annual_report;
	}
	public void setAnnual_report(String annual_report) {
		this.annual_report = annual_report;
	}
	public String getSelection_of_students() {
		return selection_of_students;
	}
	public void setSelection_of_students(String selection_of_students) {
		this.selection_of_students = selection_of_students;
	}
	public String getAdditional_document() {
		return additional_document;
	}
	public void setAdditional_document(String additional_document) {
		this.additional_document = additional_document;
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
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
