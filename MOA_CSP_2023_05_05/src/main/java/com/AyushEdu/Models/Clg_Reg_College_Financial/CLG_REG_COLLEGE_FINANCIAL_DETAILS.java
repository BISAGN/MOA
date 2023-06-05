package com.AyushEdu.Models.Clg_Reg_College_Financial;
import java.math.BigInteger;
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
@Table(name="clg_reg_college_financial_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_COLLEGE_FINANCIAL_DETAILS {
	private int id;
	private int s_id;
	private BigInteger fix_deposite;
	private BigInteger current_acct;
	private BigInteger saving_acct;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int institute_id;
	private BigInteger project_cost;
	private BigInteger capital_cost_land;
	private BigInteger building_cost;
	private BigInteger plants_machinery;
	private BigInteger equipments;
	private BigInteger furniture_fixer;
	private BigInteger preliminary_operative_cost;
	private BigInteger others;
	private BigInteger contribution_applicants;
	private BigInteger grants;
	private BigInteger donation;
	private BigInteger equity;
	private BigInteger term_loan;
	private BigInteger other_source;
	private BigInteger fee_structure;
	private BigInteger hospital_charges;
	private BigInteger annual_revenue;
	private BigInteger operating_expenses;
	private BigInteger depreciation;
	private String income_statement;
	private String cash_flow_statement;
	private String balance_sheet;
	private String medicine_bill;
	private String book_bill;
	private String college_acct_statement;
	private String proof_esi;
	private String form_16;
	private String proof_pf;
	

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
	public BigInteger getFix_deposite() {
		return fix_deposite;
	}
	public void setFix_deposite(BigInteger fix_deposite) {
		this.fix_deposite = fix_deposite;
	}
	public BigInteger getCurrent_acct() {
		return current_acct;
	}
	public void setCurrent_acct(BigInteger current_acct) {
		this.current_acct = current_acct;
	}
	public BigInteger getSaving_acct() {
		return saving_acct;
	}
	public void setSaving_acct(BigInteger saving_acct) {
		this.saving_acct = saving_acct;
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
	public BigInteger getProject_cost() {
		return project_cost;
	}
	public void setProject_cost(BigInteger project_cost) {
		this.project_cost = project_cost;
	}
	public BigInteger getCapital_cost_land() {
		return capital_cost_land;
	}
	public void setCapital_cost_land(BigInteger capital_cost_land) {
		this.capital_cost_land = capital_cost_land;
	}
	public BigInteger getBuilding_cost() {
		return building_cost;
	}
	public void setBuilding_cost(BigInteger building_cost) {
		this.building_cost = building_cost;
	}
	public BigInteger getPlants_machinery() {
		return plants_machinery;
	}
	public void setPlants_machinery(BigInteger plants_machinery) {
		this.plants_machinery = plants_machinery;
	}
	public BigInteger getEquipments() {
		return equipments;
	}
	public void setEquipments(BigInteger equipments) {
		this.equipments = equipments;
	}
	public BigInteger getFurniture_fixer() {
		return furniture_fixer;
	}
	public void setFurniture_fixer(BigInteger furniture_fixer) {
		this.furniture_fixer = furniture_fixer;
	}
	public BigInteger getPreliminary_operative_cost() {
		return preliminary_operative_cost;
	}
	public void setPreliminary_operative_cost(BigInteger preliminary_operative_cost) {
		this.preliminary_operative_cost = preliminary_operative_cost;
	}
	public BigInteger getOthers() {
		return others;
	}
	public void setOthers(BigInteger others) {
		this.others = others;
	}
	public BigInteger getContribution_applicants() {
		return contribution_applicants;
	}
	public void setContribution_applicants(BigInteger contribution_applicants) {
		this.contribution_applicants = contribution_applicants;
	}
	public BigInteger getGrants() {
		return grants;
	}
	public void setGrants(BigInteger grants) {
		this.grants = grants;
	}
	public BigInteger getDonation() {
		return donation;
	}
	public void setDonation(BigInteger donation) {
		this.donation = donation;
	}
	public BigInteger getEquity() {
		return equity;
	}
	public void setEquity(BigInteger equity) {
		this.equity = equity;
	}
	public BigInteger getTerm_loan() {
		return term_loan;
	}
	public void setTerm_loan(BigInteger term_loan) {
		this.term_loan = term_loan;
	}
	public BigInteger getOther_source() {
		return other_source;
	}
	public void setOther_source(BigInteger other_source) {
		this.other_source = other_source;
	}
	public BigInteger getFee_structure() {
		return fee_structure;
	}
	public void setFee_structure(BigInteger fee_structure) {
		this.fee_structure = fee_structure;
	}
	public BigInteger getHospital_charges() {
		return hospital_charges;
	}
	public void setHospital_charges(BigInteger hospital_charges) {
		this.hospital_charges = hospital_charges;
	}
	public BigInteger getAnnual_revenue() {
		return annual_revenue;
	}
	public void setAnnual_revenue(BigInteger annual_revenue) {
		this.annual_revenue = annual_revenue;
	}
	public BigInteger getOperating_expenses() {
		return operating_expenses;
	}
	public void setOperating_expenses(BigInteger operating_expenses) {
		this.operating_expenses = operating_expenses;
	}
	public BigInteger getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(BigInteger depreciation) {
		this.depreciation = depreciation;
	}
	public String getIncome_statement() {
		return income_statement;
	}
	public void setIncome_statement(String income_statement) {
		this.income_statement = income_statement;
	}
	public String getCash_flow_statement() {
		return cash_flow_statement;
	}
	public void setCash_flow_statement(String cash_flow_statement) {
		this.cash_flow_statement = cash_flow_statement;
	}
	public String getBalance_sheet() {
		return balance_sheet;
	}
	public void setBalance_sheet(String balance_sheet) {
		this.balance_sheet = balance_sheet;
	}
	public String getMedicine_bill() {
		return medicine_bill;
	}
	public void setMedicine_bill(String medicine_bill) {
		this.medicine_bill = medicine_bill;
	}
	public String getBook_bill() {
		return book_bill;
	}
	public void setBook_bill(String book_bill) {
		this.book_bill = book_bill;
	}
	public String getCollege_acct_statement() {
		return college_acct_statement;
	}
	public void setCollege_acct_statement(String college_acct_statement) {
		this.college_acct_statement = college_acct_statement;
	}
	public String getProof_esi() {
		return proof_esi;
	}
	public void setProof_esi(String proof_esi) {
		this.proof_esi = proof_esi;
	}
	public String getForm_16() {
		return form_16;
	}
	public void setForm_16(String form_16) {
		this.form_16 = form_16;
	}
	public String getProof_pf() {
		return proof_pf;
	}
	public void setProof_pf(String proof_pf) {
		this.proof_pf = proof_pf;
	}
	
	
}
