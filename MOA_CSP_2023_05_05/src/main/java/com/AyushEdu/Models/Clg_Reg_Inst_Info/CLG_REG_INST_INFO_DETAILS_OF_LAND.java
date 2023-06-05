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
@Table(name="clg_reg_inst_info_details_of_land", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_DETAILS_OF_LAND {
	
	private int id;
	private int p_id;
	private String total_area_of_land_society;
	private int ownership_of_land;
	private int land_in_the_name;
	private int distribution_of_entire_land;
	private int land_availability_with_society;
	private int same_society_trust;
	private String college_and_hospital_constructed;
	private String college_and_hospital_building;
	private String total_area_of_land_alloted_clg;
	private String total_area_of_land_alloted_clg_hospital;
	private String total_area_of_land_alloted_clg_hostels;
	private String college_and_hospital_building_shifted;
	private String management_of_college_changed;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int inst_id;
	
	private String land_document_clg;
	private String building_plan;
	private String statement_doc_architect;
	private String audited_balance_sheet;
	private String annual_report;
	private String additional_document;
	
	
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
	public String getTotal_area_of_land_society() {
		return total_area_of_land_society;
	}
	public void setTotal_area_of_land_society(String total_area_of_land_society) {
		this.total_area_of_land_society = total_area_of_land_society;
	}
	public int getOwnership_of_land() {
		return ownership_of_land;
	}
	public void setOwnership_of_land(int ownership_of_land) {
		this.ownership_of_land = ownership_of_land;
	}
	public int getLand_in_the_name() {
		return land_in_the_name;
	}
	public void setLand_in_the_name(int land_in_the_name) {
		this.land_in_the_name = land_in_the_name;
	}
	public int getDistribution_of_entire_land() {
		return distribution_of_entire_land;
	}
	public void setDistribution_of_entire_land(int distribution_of_entire_land) {
		this.distribution_of_entire_land = distribution_of_entire_land;
	}
	public int getLand_availability_with_society() {
		return land_availability_with_society;
	}
	public void setLand_availability_with_society(int land_availability_with_society) {
		this.land_availability_with_society = land_availability_with_society;
	}
	public int getSame_society_trust() {
		return same_society_trust;
	}
	public void setSame_society_trust(int same_society_trust) {
		this.same_society_trust = same_society_trust;
	}
	public String getCollege_and_hospital_constructed() {
		return college_and_hospital_constructed;
	}
	public void setCollege_and_hospital_constructed(String college_and_hospital_constructed) {
		this.college_and_hospital_constructed = college_and_hospital_constructed;
	}
	public String getCollege_and_hospital_building() {
		return college_and_hospital_building;
	}
	public void setCollege_and_hospital_building(String college_and_hospital_building) {
		this.college_and_hospital_building = college_and_hospital_building;
	}
	public String getTotal_area_of_land_alloted_clg() {
		return total_area_of_land_alloted_clg;
	}
	public void setTotal_area_of_land_alloted_clg(String total_area_of_land_alloted_clg) {
		this.total_area_of_land_alloted_clg = total_area_of_land_alloted_clg;
	}
	public String getTotal_area_of_land_alloted_clg_hospital() {
		return total_area_of_land_alloted_clg_hospital;
	}
	public void setTotal_area_of_land_alloted_clg_hospital(String total_area_of_land_alloted_clg_hospital) {
		this.total_area_of_land_alloted_clg_hospital = total_area_of_land_alloted_clg_hospital;
	}
	public String getTotal_area_of_land_alloted_clg_hostels() {
		return total_area_of_land_alloted_clg_hostels;
	}
	public void setTotal_area_of_land_alloted_clg_hostels(String total_area_of_land_alloted_clg_hostels) {
		this.total_area_of_land_alloted_clg_hostels = total_area_of_land_alloted_clg_hostels;
	}
	public String getCollege_and_hospital_building_shifted() {
		return college_and_hospital_building_shifted;
	}
	public void setCollege_and_hospital_building_shifted(String college_and_hospital_building_shifted) {
		this.college_and_hospital_building_shifted = college_and_hospital_building_shifted;
	}
	public String getManagement_of_college_changed() {
		return management_of_college_changed;
	}
	public void setManagement_of_college_changed(String management_of_college_changed) {
		this.management_of_college_changed = management_of_college_changed;
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
	public String getAdditional_document() {
		return additional_document;
	}
	public void setAdditional_document(String additional_document) {
		this.additional_document = additional_document;
	}
	
	
	
	
	
	
	
	
	

}
