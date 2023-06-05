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
@Table(name="clg_reg_remarks", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_REMARKS {
	
	
	private int id;
	private int inst_id;
	private int s_id;
	private String bsc_info_inst_addr_dtl_rmk;
	private String bsc_info_mgmt_dtl_rmk;
	private String bsc_info_basicinfo_dtl_rmk;
	private String bsc_info_reg_num_dtl_rmk;
	private String bsc_info_num_yrs_exp_rmk;
	private String bsc_info_hoi_addr_rmk;
	private String bsc_info_nr_airpo_dtl_rmk;
	private String bsc_info_nr_railwy_dtl_rmk;
	private String bsc_info_other_relevant_dtl_rmk;
	private String bsc_info_police_dtl_rmk;
	private String bsc_info_int_cap_ug_rmk;
	private String bsc_info_int_cap_pg_rmk;
	private String bsc_info_course_int_cap_ug_rmk;
	private String bsc_info_course_int_cap_pg_rmk;
	private String bsc_info_dtl_of_land_rmk;
	private String clg_infra_clg_coun_web_dtl_rmk;
	private String clg_infra_cam_loc_rmk;
	private String clg_infra_progress_inst_rmk;
	private String clg_infra_details_hostel_rmk;
	private String clg_infra_details_mess_rmk;
	private String clg_infra_herbal_garden_rmk;
	private String clg_infra_additional_info_rmk;
	private String clg_infra_dtl_various_clg_teach_dept_rmk;
	private String clg_infra_dtl_teach_dept_rmk;
	private String clg_infra_total_dtl_dept_rmk;
	private String clg_infra_books_rmk;
	private String clg_infra_no_of_journal_rmk;
	private String clg_infra_lib_dtl_rmk;
	private String clg_infra_lib_ass_rmk;
	private String clg_dep_comp_printer_avail_rmk;
	private String clg_dep_ug_rmk;
	private String clg_dep_pg_rmk;
	private String clg_dep_acad_performance_rmk;
	private String clg_fin_financial_capa_rmk;
	private String clg_fin_budget_clg_hosp_rmk;
	private String clg_fin_finan_the_proj_rmk;
	private String clg_fin_revenue_gener_rmk;
	private String clg_fin_expend_incurred_rmk;
	private String clg_fin_oper_rsult_rmk;
	private String clg_fin_bank_account_rmk;
	private String clg_fin_expense_staff_rmk;
	private String clg_fin_expense_purchase_rmk;
	private String clg_fin_misc_expense_rmk;
	private String clg_document_rmk;
	private String stu_dtl_info_admit_stude_rmk;
	private String stu_dtl_upl_doc_rmk;
	private String stu_dtl_pass_stu_rmk;
	private String stu_dtl_intern_housejod_rmk;
	private String clg_staff_salary_info_rmk;
	private String clg_staff_tchr_prmotion_rmk;
	private String clg_staff_upload_doc_rmk;
	private String clg_staff_list_list_of_tchr_rmk;
	private String clg_staff_list_guest_fac_rmk;
	private String clg_staff_list_teaching_fac_rmk;
	private String clg_staff_list_non_tach_fac_rmk;
	private String clg_staff_list_upload_doc_rmk;
	private String dep_equip_obstr_gyna_rmk;
	private String dep_equip_anatomy_rmk;
	private String dep_equip_cm_medi_rmk;
	private String dep_equip_equip_dtl_rmk;
	private String dep_equip_acts_regu_rmk;
	private String dep_equip_hom_phar_rmk;
	private String dep_equip_organon_medi_rmk;
	private String dep_equip_patho_micro_rmk;
	private String dep_equip_physio_biocheme_rmk;
	private String dep_equip_biochemestry_rmk;
	private String dep_equip_prac_of_medici_rmk;
	private String dep_equip_repetory_rmk;
	private String dep_equip_surgery_rmk;
	private String dep_equip_hom_mat_medi_rmk;
	private String dep_equip_psychiatry_rmk;
	private String dep_equip_pediatrics_rmk;
	private String hosp_infra_hos_admi_rmk;
	private String hosp_infra_hos_opd_rmk;
	private String hosp_infra_hos_ipd_rmk;
	private String hosp_infra_ot_rmk;
	private String hosp_infra_rehab_uni_rmk;
	private String hosp_infra_clini_lab_rmk;
	private String hosp_infra_radio_sono_rmk;
	private String hosp_infra_hos_kitchen_rmk;
	private String hosp_infra_store_dtl_rmk;
	private String hosp_infra_other_infra_dtl_rmk;
	private String clg_staff_teaching_staff_ug_rmk;
	private String clg_staff_teaching_staff_pg_rmk;
	private String clg_staff_non_tching_staff_rmk;
	private String clg_staff_last_aca_yr_rmk;
	private String hos_opd_ipd_opd_patients_rmk;
	private String hos_opd_ipd_ipd_patients_rmk;
	private String hos_opd_ipd_bed_day_occu_rmk;
	private String hos_opd_ipd_bed_existed_rmk;
	private String hos_opd_ipd_upload_doc_rmk;
	private String bsc_info_quali_rmk;
	private String other_hos_dtl_maint_record_rmk;
	private String other_hos_dtl_labour_room_rmk;
	private String other_hos_dtl_ot_rmk;
	private String other_hos_dtl_investi_rmk;
	private String other_hos_dtl_clini_lab_rmk;
	private String other_hos_dtl_verifi_of_func_rmk;
	private String hosp_staff_dtl_hospital_staff_rmk;
	private String hosp_staff_dtl_upload_doc_rmk;
	private String staff_dtl_medical_staff_rmk;
	private String staff_dtl_paramedical_staff_rmk;
	private String staff_dtl_auxillary_staff_rmk;
	private String hospi_equip_instru_equip_rmk;
	private String decl_affi_indem_bond_rmk;
	private String decl_decl_by_princi_rmk;
	private String decl_decl_by_mngt_rmk;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String other_hos_dtl_mou_rmk;
	private String hospi_equip_upload_doc_rmk;

	
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getBsc_info_inst_addr_dtl_rmk() {
		return bsc_info_inst_addr_dtl_rmk;
	}
	public void setBsc_info_inst_addr_dtl_rmk(String bsc_info_inst_addr_dtl_rmk) {
		this.bsc_info_inst_addr_dtl_rmk = bsc_info_inst_addr_dtl_rmk;
	}
	public String getBsc_info_mgmt_dtl_rmk() {
		return bsc_info_mgmt_dtl_rmk;
	}
	public void setBsc_info_mgmt_dtl_rmk(String bsc_info_mgmt_dtl_rmk) {
		this.bsc_info_mgmt_dtl_rmk = bsc_info_mgmt_dtl_rmk;
	}
	public String getBsc_info_basicinfo_dtl_rmk() {
		return bsc_info_basicinfo_dtl_rmk;
	}
	public void setBsc_info_basicinfo_dtl_rmk(String bsc_info_basicinfo_dtl_rmk) {
		this.bsc_info_basicinfo_dtl_rmk = bsc_info_basicinfo_dtl_rmk;
	}
	public String getBsc_info_reg_num_dtl_rmk() {
		return bsc_info_reg_num_dtl_rmk;
	}
	public void setBsc_info_reg_num_dtl_rmk(String bsc_info_reg_num_dtl_rmk) {
		this.bsc_info_reg_num_dtl_rmk = bsc_info_reg_num_dtl_rmk;
	}
	public String getBsc_info_num_yrs_exp_rmk() {
		return bsc_info_num_yrs_exp_rmk;
	}
	public void setBsc_info_num_yrs_exp_rmk(String bsc_info_num_yrs_exp_rmk) {
		this.bsc_info_num_yrs_exp_rmk = bsc_info_num_yrs_exp_rmk;
	}
	public String getBsc_info_hoi_addr_rmk() {
		return bsc_info_hoi_addr_rmk;
	}
	public void setBsc_info_hoi_addr_rmk(String bsc_info_hoi_addr_rmk) {
		this.bsc_info_hoi_addr_rmk = bsc_info_hoi_addr_rmk;
	}
	public String getBsc_info_nr_airpo_dtl_rmk() {
		return bsc_info_nr_airpo_dtl_rmk;
	}
	public void setBsc_info_nr_airpo_dtl_rmk(String bsc_info_nr_airpo_dtl_rmk) {
		this.bsc_info_nr_airpo_dtl_rmk = bsc_info_nr_airpo_dtl_rmk;
	}
	public String getBsc_info_nr_railwy_dtl_rmk() {
		return bsc_info_nr_railwy_dtl_rmk;
	}
	public void setBsc_info_nr_railwy_dtl_rmk(String bsc_info_nr_railwy_dtl_rmk) {
		this.bsc_info_nr_railwy_dtl_rmk = bsc_info_nr_railwy_dtl_rmk;
	}
	public String getBsc_info_other_relevant_dtl_rmk() {
		return bsc_info_other_relevant_dtl_rmk;
	}
	public void setBsc_info_other_relevant_dtl_rmk(String bsc_info_other_relevant_dtl_rmk) {
		this.bsc_info_other_relevant_dtl_rmk = bsc_info_other_relevant_dtl_rmk;
	}
	public String getBsc_info_police_dtl_rmk() {
		return bsc_info_police_dtl_rmk;
	}
	public void setBsc_info_police_dtl_rmk(String bsc_info_police_dtl_rmk) {
		this.bsc_info_police_dtl_rmk = bsc_info_police_dtl_rmk;
	}
	public String getBsc_info_int_cap_ug_rmk() {
		return bsc_info_int_cap_ug_rmk;
	}
	public void setBsc_info_int_cap_ug_rmk(String bsc_info_int_cap_ug_rmk) {
		this.bsc_info_int_cap_ug_rmk = bsc_info_int_cap_ug_rmk;
	}
	public String getBsc_info_int_cap_pg_rmk() {
		return bsc_info_int_cap_pg_rmk;
	}
	public void setBsc_info_int_cap_pg_rmk(String bsc_info_int_cap_pg_rmk) {
		this.bsc_info_int_cap_pg_rmk = bsc_info_int_cap_pg_rmk;
	}
	public String getBsc_info_course_int_cap_ug_rmk() {
		return bsc_info_course_int_cap_ug_rmk;
	}
	public void setBsc_info_course_int_cap_ug_rmk(String bsc_info_course_int_cap_ug_rmk) {
		this.bsc_info_course_int_cap_ug_rmk = bsc_info_course_int_cap_ug_rmk;
	}
	public String getBsc_info_course_int_cap_pg_rmk() {
		return bsc_info_course_int_cap_pg_rmk;
	}
	public void setBsc_info_course_int_cap_pg_rmk(String bsc_info_course_int_cap_pg_rmk) {
		this.bsc_info_course_int_cap_pg_rmk = bsc_info_course_int_cap_pg_rmk;
	}
	public String getBsc_info_dtl_of_land_rmk() {
		return bsc_info_dtl_of_land_rmk;
	}
	public void setBsc_info_dtl_of_land_rmk(String bsc_info_dtl_of_land_rmk) {
		this.bsc_info_dtl_of_land_rmk = bsc_info_dtl_of_land_rmk;
	}
	public String getClg_infra_clg_coun_web_dtl_rmk() {
		return clg_infra_clg_coun_web_dtl_rmk;
	}
	public void setClg_infra_clg_coun_web_dtl_rmk(String clg_infra_clg_coun_web_dtl_rmk) {
		this.clg_infra_clg_coun_web_dtl_rmk = clg_infra_clg_coun_web_dtl_rmk;
	}
	public String getClg_infra_cam_loc_rmk() {
		return clg_infra_cam_loc_rmk;
	}
	public void setClg_infra_cam_loc_rmk(String clg_infra_cam_loc_rmk) {
		this.clg_infra_cam_loc_rmk = clg_infra_cam_loc_rmk;
	}
	public String getClg_infra_progress_inst_rmk() {
		return clg_infra_progress_inst_rmk;
	}
	public void setClg_infra_progress_inst_rmk(String clg_infra_progress_inst_rmk) {
		this.clg_infra_progress_inst_rmk = clg_infra_progress_inst_rmk;
	}
	public String getClg_infra_details_hostel_rmk() {
		return clg_infra_details_hostel_rmk;
	}
	public void setClg_infra_details_hostel_rmk(String clg_infra_details_hostel_rmk) {
		this.clg_infra_details_hostel_rmk = clg_infra_details_hostel_rmk;
	}
	public String getClg_infra_details_mess_rmk() {
		return clg_infra_details_mess_rmk;
	}
	public void setClg_infra_details_mess_rmk(String clg_infra_details_mess_rmk) {
		this.clg_infra_details_mess_rmk = clg_infra_details_mess_rmk;
	}
	public String getClg_infra_herbal_garden_rmk() {
		return clg_infra_herbal_garden_rmk;
	}
	public void setClg_infra_herbal_garden_rmk(String clg_infra_herbal_garden_rmk) {
		this.clg_infra_herbal_garden_rmk = clg_infra_herbal_garden_rmk;
	}
	public String getClg_infra_additional_info_rmk() {
		return clg_infra_additional_info_rmk;
	}
	public void setClg_infra_additional_info_rmk(String clg_infra_additional_info_rmk) {
		this.clg_infra_additional_info_rmk = clg_infra_additional_info_rmk;
	}
	public String getClg_infra_dtl_various_clg_teach_dept_rmk() {
		return clg_infra_dtl_various_clg_teach_dept_rmk;
	}
	public void setClg_infra_dtl_various_clg_teach_dept_rmk(String clg_infra_dtl_various_clg_teach_dept_rmk) {
		this.clg_infra_dtl_various_clg_teach_dept_rmk = clg_infra_dtl_various_clg_teach_dept_rmk;
	}
	public String getClg_infra_dtl_teach_dept_rmk() {
		return clg_infra_dtl_teach_dept_rmk;
	}
	public void setClg_infra_dtl_teach_dept_rmk(String clg_infra_dtl_teach_dept_rmk) {
		this.clg_infra_dtl_teach_dept_rmk = clg_infra_dtl_teach_dept_rmk;
	}
	public String getClg_infra_total_dtl_dept_rmk() {
		return clg_infra_total_dtl_dept_rmk;
	}
	public void setClg_infra_total_dtl_dept_rmk(String clg_infra_total_dtl_dept_rmk) {
		this.clg_infra_total_dtl_dept_rmk = clg_infra_total_dtl_dept_rmk;
	}
	public String getClg_infra_books_rmk() {
		return clg_infra_books_rmk;
	}
	public void setClg_infra_books_rmk(String clg_infra_books_rmk) {
		this.clg_infra_books_rmk = clg_infra_books_rmk;
	}
	public String getClg_infra_no_of_journal_rmk() {
		return clg_infra_no_of_journal_rmk;
	}
	public void setClg_infra_no_of_journal_rmk(String clg_infra_no_of_journal_rmk) {
		this.clg_infra_no_of_journal_rmk = clg_infra_no_of_journal_rmk;
	}
	public String getClg_infra_lib_dtl_rmk() {
		return clg_infra_lib_dtl_rmk;
	}
	public void setClg_infra_lib_dtl_rmk(String clg_infra_lib_dtl_rmk) {
		this.clg_infra_lib_dtl_rmk = clg_infra_lib_dtl_rmk;
	}
	public String getClg_infra_lib_ass_rmk() {
		return clg_infra_lib_ass_rmk;
	}
	public void setClg_infra_lib_ass_rmk(String clg_infra_lib_ass_rmk) {
		this.clg_infra_lib_ass_rmk = clg_infra_lib_ass_rmk;
	}
	public String getClg_dep_comp_printer_avail_rmk() {
		return clg_dep_comp_printer_avail_rmk;
	}
	public void setClg_dep_comp_printer_avail_rmk(String clg_dep_comp_printer_avail_rmk) {
		this.clg_dep_comp_printer_avail_rmk = clg_dep_comp_printer_avail_rmk;
	}
	public String getClg_dep_ug_rmk() {
		return clg_dep_ug_rmk;
	}
	public void setClg_dep_ug_rmk(String clg_dep_ug_rmk) {
		this.clg_dep_ug_rmk = clg_dep_ug_rmk;
	}
	public String getClg_dep_pg_rmk() {
		return clg_dep_pg_rmk;
	}
	public void setClg_dep_pg_rmk(String clg_dep_pg_rmk) {
		this.clg_dep_pg_rmk = clg_dep_pg_rmk;
	}
	public String getClg_dep_acad_performance_rmk() {
		return clg_dep_acad_performance_rmk;
	}
	public void setClg_dep_acad_performance_rmk(String clg_dep_acad_performance_rmk) {
		this.clg_dep_acad_performance_rmk = clg_dep_acad_performance_rmk;
	}
	public String getClg_fin_financial_capa_rmk() {
		return clg_fin_financial_capa_rmk;
	}
	public void setClg_fin_financial_capa_rmk(String clg_fin_financial_capa_rmk) {
		this.clg_fin_financial_capa_rmk = clg_fin_financial_capa_rmk;
	}
	public String getClg_fin_budget_clg_hosp_rmk() {
		return clg_fin_budget_clg_hosp_rmk;
	}
	public void setClg_fin_budget_clg_hosp_rmk(String clg_fin_budget_clg_hosp_rmk) {
		this.clg_fin_budget_clg_hosp_rmk = clg_fin_budget_clg_hosp_rmk;
	}
	public String getClg_fin_finan_the_proj_rmk() {
		return clg_fin_finan_the_proj_rmk;
	}
	public void setClg_fin_finan_the_proj_rmk(String clg_fin_finan_the_proj_rmk) {
		this.clg_fin_finan_the_proj_rmk = clg_fin_finan_the_proj_rmk;
	}
	public String getClg_fin_revenue_gener_rmk() {
		return clg_fin_revenue_gener_rmk;
	}
	public void setClg_fin_revenue_gener_rmk(String clg_fin_revenue_gener_rmk) {
		this.clg_fin_revenue_gener_rmk = clg_fin_revenue_gener_rmk;
	}
	public String getClg_fin_expend_incurred_rmk() {
		return clg_fin_expend_incurred_rmk;
	}
	public void setClg_fin_expend_incurred_rmk(String clg_fin_expend_incurred_rmk) {
		this.clg_fin_expend_incurred_rmk = clg_fin_expend_incurred_rmk;
	}
	public String getClg_fin_oper_rsult_rmk() {
		return clg_fin_oper_rsult_rmk;
	}
	public void setClg_fin_oper_rsult_rmk(String clg_fin_oper_rsult_rmk) {
		this.clg_fin_oper_rsult_rmk = clg_fin_oper_rsult_rmk;
	}
	public String getClg_fin_bank_account_rmk() {
		return clg_fin_bank_account_rmk;
	}
	public void setClg_fin_bank_account_rmk(String clg_fin_bank_account_rmk) {
		this.clg_fin_bank_account_rmk = clg_fin_bank_account_rmk;
	}
	public String getClg_fin_expense_staff_rmk() {
		return clg_fin_expense_staff_rmk;
	}
	public void setClg_fin_expense_staff_rmk(String clg_fin_expense_staff_rmk) {
		this.clg_fin_expense_staff_rmk = clg_fin_expense_staff_rmk;
	}
	public String getClg_fin_expense_purchase_rmk() {
		return clg_fin_expense_purchase_rmk;
	}
	public void setClg_fin_expense_purchase_rmk(String clg_fin_expense_purchase_rmk) {
		this.clg_fin_expense_purchase_rmk = clg_fin_expense_purchase_rmk;
	}
	public String getClg_fin_misc_expense_rmk() {
		return clg_fin_misc_expense_rmk;
	}
	public void setClg_fin_misc_expense_rmk(String clg_fin_misc_expense_rmk) {
		this.clg_fin_misc_expense_rmk = clg_fin_misc_expense_rmk;
	}
	public String getStu_dtl_info_admit_stude_rmk() {
		return stu_dtl_info_admit_stude_rmk;
	}
	public void setStu_dtl_info_admit_stude_rmk(String stu_dtl_info_admit_stude_rmk) {
		this.stu_dtl_info_admit_stude_rmk = stu_dtl_info_admit_stude_rmk;
	}
	public String getStu_dtl_upl_doc_rmk() {
		return stu_dtl_upl_doc_rmk;
	}
	public void setStu_dtl_upl_doc_rmk(String stu_dtl_upl_doc_rmk) {
		this.stu_dtl_upl_doc_rmk = stu_dtl_upl_doc_rmk;
	}
	public String getStu_dtl_pass_stu_rmk() {
		return stu_dtl_pass_stu_rmk;
	}
	public void setStu_dtl_pass_stu_rmk(String stu_dtl_pass_stu_rmk) {
		this.stu_dtl_pass_stu_rmk = stu_dtl_pass_stu_rmk;
	}
	public String getStu_dtl_intern_housejod_rmk() {
		return stu_dtl_intern_housejod_rmk;
	}
	public void setStu_dtl_intern_housejod_rmk(String stu_dtl_intern_housejod_rmk) {
		this.stu_dtl_intern_housejod_rmk = stu_dtl_intern_housejod_rmk;
	}
	public String getClg_staff_salary_info_rmk() {
		return clg_staff_salary_info_rmk;
	}
	public void setClg_staff_salary_info_rmk(String clg_staff_salary_info_rmk) {
		this.clg_staff_salary_info_rmk = clg_staff_salary_info_rmk;
	}
	public String getClg_staff_tchr_prmotion_rmk() {
		return clg_staff_tchr_prmotion_rmk;
	}
	public void setClg_staff_tchr_prmotion_rmk(String clg_staff_tchr_prmotion_rmk) {
		this.clg_staff_tchr_prmotion_rmk = clg_staff_tchr_prmotion_rmk;
	}
	public String getClg_staff_upload_doc_rmk() {
		return clg_staff_upload_doc_rmk;
	}
	public void setClg_staff_upload_doc_rmk(String clg_staff_upload_doc_rmk) {
		this.clg_staff_upload_doc_rmk = clg_staff_upload_doc_rmk;
	}
	public String getClg_staff_list_list_of_tchr_rmk() {
		return clg_staff_list_list_of_tchr_rmk;
	}
	public void setClg_staff_list_list_of_tchr_rmk(String clg_staff_list_list_of_tchr_rmk) {
		this.clg_staff_list_list_of_tchr_rmk = clg_staff_list_list_of_tchr_rmk;
	}
	public String getClg_staff_list_guest_fac_rmk() {
		return clg_staff_list_guest_fac_rmk;
	}
	public void setClg_staff_list_guest_fac_rmk(String clg_staff_list_guest_fac_rmk) {
		this.clg_staff_list_guest_fac_rmk = clg_staff_list_guest_fac_rmk;
	}
	public String getClg_staff_list_teaching_fac_rmk() {
		return clg_staff_list_teaching_fac_rmk;
	}
	public void setClg_staff_list_teaching_fac_rmk(String clg_staff_list_teaching_fac_rmk) {
		this.clg_staff_list_teaching_fac_rmk = clg_staff_list_teaching_fac_rmk;
	}
	public String getClg_staff_list_non_tach_fac_rmk() {
		return clg_staff_list_non_tach_fac_rmk;
	}
	public void setClg_staff_list_non_tach_fac_rmk(String clg_staff_list_non_tach_fac_rmk) {
		this.clg_staff_list_non_tach_fac_rmk = clg_staff_list_non_tach_fac_rmk;
	}
	public String getClg_staff_list_upload_doc_rmk() {
		return clg_staff_list_upload_doc_rmk;
	}
	public void setClg_staff_list_upload_doc_rmk(String clg_staff_list_upload_doc_rmk) {
		this.clg_staff_list_upload_doc_rmk = clg_staff_list_upload_doc_rmk;
	}
	public String getDep_equip_obstr_gyna_rmk() {
		return dep_equip_obstr_gyna_rmk;
	}
	public void setDep_equip_obstr_gyna_rmk(String dep_equip_obstr_gyna_rmk) {
		this.dep_equip_obstr_gyna_rmk = dep_equip_obstr_gyna_rmk;
	}
	public String getDep_equip_anatomy_rmk() {
		return dep_equip_anatomy_rmk;
	}
	public void setDep_equip_anatomy_rmk(String dep_equip_anatomy_rmk) {
		this.dep_equip_anatomy_rmk = dep_equip_anatomy_rmk;
	}
	public String getDep_equip_cm_medi_rmk() {
		return dep_equip_cm_medi_rmk;
	}
	public void setDep_equip_cm_medi_rmk(String dep_equip_cm_medi_rmk) {
		this.dep_equip_cm_medi_rmk = dep_equip_cm_medi_rmk;
	}
	public String getDep_equip_equip_dtl_rmk() {
		return dep_equip_equip_dtl_rmk;
	}
	public void setDep_equip_equip_dtl_rmk(String dep_equip_equip_dtl_rmk) {
		this.dep_equip_equip_dtl_rmk = dep_equip_equip_dtl_rmk;
	}
	public String getDep_equip_acts_regu_rmk() {
		return dep_equip_acts_regu_rmk;
	}
	public void setDep_equip_acts_regu_rmk(String dep_equip_acts_regu_rmk) {
		this.dep_equip_acts_regu_rmk = dep_equip_acts_regu_rmk;
	}
	public String getDep_equip_hom_phar_rmk() {
		return dep_equip_hom_phar_rmk;
	}
	public void setDep_equip_hom_phar_rmk(String dep_equip_hom_phar_rmk) {
		this.dep_equip_hom_phar_rmk = dep_equip_hom_phar_rmk;
	}
	public String getDep_equip_organon_medi_rmk() {
		return dep_equip_organon_medi_rmk;
	}
	public void setDep_equip_organon_medi_rmk(String dep_equip_organon_medi_rmk) {
		this.dep_equip_organon_medi_rmk = dep_equip_organon_medi_rmk;
	}
	public String getDep_equip_patho_micro_rmk() {
		return dep_equip_patho_micro_rmk;
	}
	public void setDep_equip_patho_micro_rmk(String dep_equip_patho_micro_rmk) {
		this.dep_equip_patho_micro_rmk = dep_equip_patho_micro_rmk;
	}
	public String getDep_equip_physio_biocheme_rmk() {
		return dep_equip_physio_biocheme_rmk;
	}
	public void setDep_equip_physio_biocheme_rmk(String dep_equip_physio_biocheme_rmk) {
		this.dep_equip_physio_biocheme_rmk = dep_equip_physio_biocheme_rmk;
	}
	public String getDep_equip_biochemestry_rmk() {
		return dep_equip_biochemestry_rmk;
	}
	public void setDep_equip_biochemestry_rmk(String dep_equip_biochemestry_rmk) {
		this.dep_equip_biochemestry_rmk = dep_equip_biochemestry_rmk;
	}
	public String getDep_equip_prac_of_medici_rmk() {
		return dep_equip_prac_of_medici_rmk;
	}
	public void setDep_equip_prac_of_medici_rmk(String dep_equip_prac_of_medici_rmk) {
		this.dep_equip_prac_of_medici_rmk = dep_equip_prac_of_medici_rmk;
	}
	public String getDep_equip_repetory_rmk() {
		return dep_equip_repetory_rmk;
	}
	public void setDep_equip_repetory_rmk(String dep_equip_repetory_rmk) {
		this.dep_equip_repetory_rmk = dep_equip_repetory_rmk;
	}
	public String getDep_equip_surgery_rmk() {
		return dep_equip_surgery_rmk;
	}
	public void setDep_equip_surgery_rmk(String dep_equip_surgery_rmk) {
		this.dep_equip_surgery_rmk = dep_equip_surgery_rmk;
	}
	public String getDep_equip_hom_mat_medi_rmk() {
		return dep_equip_hom_mat_medi_rmk;
	}
	public void setDep_equip_hom_mat_medi_rmk(String dep_equip_hom_mat_medi_rmk) {
		this.dep_equip_hom_mat_medi_rmk = dep_equip_hom_mat_medi_rmk;
	}
	public String getDep_equip_psychiatry_rmk() {
		return dep_equip_psychiatry_rmk;
	}
	public void setDep_equip_psychiatry_rmk(String dep_equip_psychiatry_rmk) {
		this.dep_equip_psychiatry_rmk = dep_equip_psychiatry_rmk;
	}
	public String getDep_equip_pediatrics_rmk() {
		return dep_equip_pediatrics_rmk;
	}
	public void setDep_equip_pediatrics_rmk(String dep_equip_pediatrics_rmk) {
		this.dep_equip_pediatrics_rmk = dep_equip_pediatrics_rmk;
	}
	public String getHosp_infra_hos_admi_rmk() {
		return hosp_infra_hos_admi_rmk;
	}
	public void setHosp_infra_hos_admi_rmk(String hosp_infra_hos_admi_rmk) {
		this.hosp_infra_hos_admi_rmk = hosp_infra_hos_admi_rmk;
	}
	public String getHosp_infra_hos_opd_rmk() {
		return hosp_infra_hos_opd_rmk;
	}
	public void setHosp_infra_hos_opd_rmk(String hosp_infra_hos_opd_rmk) {
		this.hosp_infra_hos_opd_rmk = hosp_infra_hos_opd_rmk;
	}
	public String getHosp_infra_hos_ipd_rmk() {
		return hosp_infra_hos_ipd_rmk;
	}
	public void setHosp_infra_hos_ipd_rmk(String hosp_infra_hos_ipd_rmk) {
		this.hosp_infra_hos_ipd_rmk = hosp_infra_hos_ipd_rmk;
	}
	public String getHosp_infra_ot_rmk() {
		return hosp_infra_ot_rmk;
	}
	public void setHosp_infra_ot_rmk(String hosp_infra_ot_rmk) {
		this.hosp_infra_ot_rmk = hosp_infra_ot_rmk;
	}
	public String getHosp_infra_rehab_uni_rmk() {
		return hosp_infra_rehab_uni_rmk;
	}
	public void setHosp_infra_rehab_uni_rmk(String hosp_infra_rehab_uni_rmk) {
		this.hosp_infra_rehab_uni_rmk = hosp_infra_rehab_uni_rmk;
	}
	public String getHosp_infra_clini_lab_rmk() {
		return hosp_infra_clini_lab_rmk;
	}
	public void setHosp_infra_clini_lab_rmk(String hosp_infra_clini_lab_rmk) {
		this.hosp_infra_clini_lab_rmk = hosp_infra_clini_lab_rmk;
	}
	public String getHosp_infra_radio_sono_rmk() {
		return hosp_infra_radio_sono_rmk;
	}
	public void setHosp_infra_radio_sono_rmk(String hosp_infra_radio_sono_rmk) {
		this.hosp_infra_radio_sono_rmk = hosp_infra_radio_sono_rmk;
	}
	public String getHosp_infra_hos_kitchen_rmk() {
		return hosp_infra_hos_kitchen_rmk;
	}
	public void setHosp_infra_hos_kitchen_rmk(String hosp_infra_hos_kitchen_rmk) {
		this.hosp_infra_hos_kitchen_rmk = hosp_infra_hos_kitchen_rmk;
	}
	public String getHosp_infra_store_dtl_rmk() {
		return hosp_infra_store_dtl_rmk;
	}
	public void setHosp_infra_store_dtl_rmk(String hosp_infra_store_dtl_rmk) {
		this.hosp_infra_store_dtl_rmk = hosp_infra_store_dtl_rmk;
	}
	public String getHosp_infra_other_infra_dtl_rmk() {
		return hosp_infra_other_infra_dtl_rmk;
	}
	public void setHosp_infra_other_infra_dtl_rmk(String hosp_infra_other_infra_dtl_rmk) {
		this.hosp_infra_other_infra_dtl_rmk = hosp_infra_other_infra_dtl_rmk;
	}
	public String getClg_staff_teaching_staff_ug_rmk() {
		return clg_staff_teaching_staff_ug_rmk;
	}
	public void setClg_staff_teaching_staff_ug_rmk(String clg_staff_teaching_staff_ug_rmk) {
		this.clg_staff_teaching_staff_ug_rmk = clg_staff_teaching_staff_ug_rmk;
	}
	public String getClg_staff_teaching_staff_pg_rmk() {
		return clg_staff_teaching_staff_pg_rmk;
	}
	public void setClg_staff_teaching_staff_pg_rmk(String clg_staff_teaching_staff_pg_rmk) {
		this.clg_staff_teaching_staff_pg_rmk = clg_staff_teaching_staff_pg_rmk;
	}
	public String getClg_staff_non_tching_staff_rmk() {
		return clg_staff_non_tching_staff_rmk;
	}
	public void setClg_staff_non_tching_staff_rmk(String clg_staff_non_tching_staff_rmk) {
		this.clg_staff_non_tching_staff_rmk = clg_staff_non_tching_staff_rmk;
	}
	public String getClg_staff_last_aca_yr_rmk() {
		return clg_staff_last_aca_yr_rmk;
	}
	public void setClg_staff_last_aca_yr_rmk(String clg_staff_last_aca_yr_rmk) {
		this.clg_staff_last_aca_yr_rmk = clg_staff_last_aca_yr_rmk;
	}
	public String getHos_opd_ipd_opd_patients_rmk() {
		return hos_opd_ipd_opd_patients_rmk;
	}
	public void setHos_opd_ipd_opd_patients_rmk(String hos_opd_ipd_opd_patients_rmk) {
		this.hos_opd_ipd_opd_patients_rmk = hos_opd_ipd_opd_patients_rmk;
	}
	public String getHos_opd_ipd_ipd_patients_rmk() {
		return hos_opd_ipd_ipd_patients_rmk;
	}
	public void setHos_opd_ipd_ipd_patients_rmk(String hos_opd_ipd_ipd_patients_rmk) {
		this.hos_opd_ipd_ipd_patients_rmk = hos_opd_ipd_ipd_patients_rmk;
	}
	public String getHos_opd_ipd_bed_day_occu_rmk() {
		return hos_opd_ipd_bed_day_occu_rmk;
	}
	public void setHos_opd_ipd_bed_day_occu_rmk(String hos_opd_ipd_bed_day_occu_rmk) {
		this.hos_opd_ipd_bed_day_occu_rmk = hos_opd_ipd_bed_day_occu_rmk;
	}
	public String getHos_opd_ipd_bed_existed_rmk() {
		return hos_opd_ipd_bed_existed_rmk;
	}
	public void setHos_opd_ipd_bed_existed_rmk(String hos_opd_ipd_bed_existed_rmk) {
		this.hos_opd_ipd_bed_existed_rmk = hos_opd_ipd_bed_existed_rmk;
	}
	public String getHos_opd_ipd_upload_doc_rmk() {
		return hos_opd_ipd_upload_doc_rmk;
	}
	public void setHos_opd_ipd_upload_doc_rmk(String hos_opd_ipd_upload_doc_rmk) {
		this.hos_opd_ipd_upload_doc_rmk = hos_opd_ipd_upload_doc_rmk;
	}
	public String getBsc_info_quali_rmk() {
		return bsc_info_quali_rmk;
	}
	public void setBsc_info_quali_rmk(String bsc_info_quali_rmk) {
		this.bsc_info_quali_rmk = bsc_info_quali_rmk;
	}
	public String getOther_hos_dtl_maint_record_rmk() {
		return other_hos_dtl_maint_record_rmk;
	}
	public void setOther_hos_dtl_maint_record_rmk(String other_hos_dtl_maint_record_rmk) {
		this.other_hos_dtl_maint_record_rmk = other_hos_dtl_maint_record_rmk;
	}
	public String getOther_hos_dtl_labour_room_rmk() {
		return other_hos_dtl_labour_room_rmk;
	}
	public void setOther_hos_dtl_labour_room_rmk(String other_hos_dtl_labour_room_rmk) {
		this.other_hos_dtl_labour_room_rmk = other_hos_dtl_labour_room_rmk;
	}
	public String getOther_hos_dtl_ot_rmk() {
		return other_hos_dtl_ot_rmk;
	}
	public void setOther_hos_dtl_ot_rmk(String other_hos_dtl_ot_rmk) {
		this.other_hos_dtl_ot_rmk = other_hos_dtl_ot_rmk;
	}
	public String getOther_hos_dtl_investi_rmk() {
		return other_hos_dtl_investi_rmk;
	}
	public void setOther_hos_dtl_investi_rmk(String other_hos_dtl_investi_rmk) {
		this.other_hos_dtl_investi_rmk = other_hos_dtl_investi_rmk;
	}
	public String getOther_hos_dtl_clini_lab_rmk() {
		return other_hos_dtl_clini_lab_rmk;
	}
	public void setOther_hos_dtl_clini_lab_rmk(String other_hos_dtl_clini_lab_rmk) {
		this.other_hos_dtl_clini_lab_rmk = other_hos_dtl_clini_lab_rmk;
	}
	public String getOther_hos_dtl_verifi_of_func_rmk() {
		return other_hos_dtl_verifi_of_func_rmk;
	}
	public void setOther_hos_dtl_verifi_of_func_rmk(String other_hos_dtl_verifi_of_func_rmk) {
		this.other_hos_dtl_verifi_of_func_rmk = other_hos_dtl_verifi_of_func_rmk;
	}
	public String getHosp_staff_dtl_hospital_staff_rmk() {
		return hosp_staff_dtl_hospital_staff_rmk;
	}
	public void setHosp_staff_dtl_hospital_staff_rmk(String hosp_staff_dtl_hospital_staff_rmk) {
		this.hosp_staff_dtl_hospital_staff_rmk = hosp_staff_dtl_hospital_staff_rmk;
	}
	public String getHosp_staff_dtl_upload_doc_rmk() {
		return hosp_staff_dtl_upload_doc_rmk;
	}
	public void setHosp_staff_dtl_upload_doc_rmk(String hosp_staff_dtl_upload_doc_rmk) {
		this.hosp_staff_dtl_upload_doc_rmk = hosp_staff_dtl_upload_doc_rmk;
	}
	public String getStaff_dtl_medical_staff_rmk() {
		return staff_dtl_medical_staff_rmk;
	}
	public void setStaff_dtl_medical_staff_rmk(String staff_dtl_medical_staff_rmk) {
		this.staff_dtl_medical_staff_rmk = staff_dtl_medical_staff_rmk;
	}
	public String getStaff_dtl_paramedical_staff_rmk() {
		return staff_dtl_paramedical_staff_rmk;
	}
	public void setStaff_dtl_paramedical_staff_rmk(String staff_dtl_paramedical_staff_rmk) {
		this.staff_dtl_paramedical_staff_rmk = staff_dtl_paramedical_staff_rmk;
	}
	public String getStaff_dtl_auxillary_staff_rmk() {
		return staff_dtl_auxillary_staff_rmk;
	}
	public void setStaff_dtl_auxillary_staff_rmk(String staff_dtl_auxillary_staff_rmk) {
		this.staff_dtl_auxillary_staff_rmk = staff_dtl_auxillary_staff_rmk;
	}
	public String getHospi_equip_instru_equip_rmk() {
		return hospi_equip_instru_equip_rmk;
	}
	public void setHospi_equip_instru_equip_rmk(String hospi_equip_instru_equip_rmk) {
		this.hospi_equip_instru_equip_rmk = hospi_equip_instru_equip_rmk;
	}
	public String getDecl_affi_indem_bond_rmk() {
		return decl_affi_indem_bond_rmk;
	}
	public void setDecl_affi_indem_bond_rmk(String decl_affi_indem_bond_rmk) {
		this.decl_affi_indem_bond_rmk = decl_affi_indem_bond_rmk;
	}
	public String getDecl_decl_by_princi_rmk() {
		return decl_decl_by_princi_rmk;
	}
	public void setDecl_decl_by_princi_rmk(String decl_decl_by_princi_rmk) {
		this.decl_decl_by_princi_rmk = decl_decl_by_princi_rmk;
	}
	public String getDecl_decl_by_mngt_rmk() {
		return decl_decl_by_mngt_rmk;
	}
	public void setDecl_decl_by_mngt_rmk(String decl_decl_by_mngt_rmk) {
		this.decl_decl_by_mngt_rmk = decl_decl_by_mngt_rmk;
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
	

	public String getOther_hos_dtl_mou_rmk() {
		return other_hos_dtl_mou_rmk;
	}
	public void setOther_hos_dtl_mou_rmk(String other_hos_dtl_mou_rmk) {
		this.other_hos_dtl_mou_rmk = other_hos_dtl_mou_rmk;
	}

	
    public String getHospi_equip_upload_doc_rmk() {
		return hospi_equip_upload_doc_rmk;
	}
	public void setHospi_equip_upload_doc_rmk(String hospi_equip_upload_doc_rmk) {
		this.hospi_equip_upload_doc_rmk = hospi_equip_upload_doc_rmk;
	}
	public String getClg_document_rmk() {
		return clg_document_rmk;
	}
	public void setClg_document_rmk(String clg_document_rmk) {
		this.clg_document_rmk = clg_document_rmk;
	}
	
}
