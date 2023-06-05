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
@Table(name="clg_reg_inst_info_intake_capacity_pg", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INST_INFO_INTAKE_CAPACITY_PG {
	
	private int id;
	private int p_id;
	private String intake_capacity_pg;
	private String year;
	private int permission;
	private int inst_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	private String admitted_seat;
	private String permitted_seat;
	private String sanction_seat;
	private String student_govt_quota;
	private String student_mgmt_quota;
	private int admitted_court_order;
	private String last_stu_admitted;
	private Date date_stu_admitted;
	private String total_stu_app_exam;
	private String total_stu_pass_exam;
	
	
	
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
	public String getIntake_capacity_pg() {
		return intake_capacity_pg;
	}
	public void setIntake_capacity_pg(String intake_capacity_pg) {
		this.intake_capacity_pg = intake_capacity_pg;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getInst_id() {
		return inst_id;
	}
	public void setInst_id(int inst_id) {
		this.inst_id = inst_id;
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
	public String getAdmitted_seat() {
		return admitted_seat;
	}
	public void setAdmitted_seat(String admitted_seat) {
		this.admitted_seat = admitted_seat;
	}
	public String getPermitted_seat() {
		return permitted_seat;
	}
	public void setPermitted_seat(String permitted_seat) {
		this.permitted_seat = permitted_seat;
	}
	public String getSanction_seat() {
		return sanction_seat;
	}
	public void setSanction_seat(String sanction_seat) {
		this.sanction_seat = sanction_seat;
	}
	public String getStudent_govt_quota() {
		return student_govt_quota;
	}
	public void setStudent_govt_quota(String student_govt_quota) {
		this.student_govt_quota = student_govt_quota;
	}
	public String getStudent_mgmt_quota() {
		return student_mgmt_quota;
	}
	public void setStudent_mgmt_quota(String student_mgmt_quota) {
		this.student_mgmt_quota = student_mgmt_quota;
	}
	public int getAdmitted_court_order() {
		return admitted_court_order;
	}
	public void setAdmitted_court_order(int admitted_court_order) {
		this.admitted_court_order = admitted_court_order;
	}
	public String getLast_stu_admitted() {
		return last_stu_admitted;
	}
	public void setLast_stu_admitted(String last_stu_admitted) {
		this.last_stu_admitted = last_stu_admitted;
	}
	public Date getDate_stu_admitted() {
		return date_stu_admitted;
	}
	public void setDate_stu_admitted(Date date_stu_admitted) {
		this.date_stu_admitted = date_stu_admitted;
	}
	public String getTotal_stu_app_exam() {
		return total_stu_app_exam;
	}
	public void setTotal_stu_app_exam(String total_stu_app_exam) {
		this.total_stu_app_exam = total_stu_app_exam;
	}
	public String getTotal_stu_pass_exam() {
		return total_stu_pass_exam;
	}
	public void setTotal_stu_pass_exam(String total_stu_pass_exam) {
		this.total_stu_pass_exam = total_stu_pass_exam;
	}
	
	
	
	
	
	
	

}
