package com.AyushEdu.Models.Clg_Teaching_Staff_Info;
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
@Table(name="clg_reg_teaching_staff_pg", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_TEACHING_STAFF_PG {
	
		private int id;
		private int institute_id;
		private int p_id;
		private int s_id;
		private int each_total;
		private String department;
		private int full_time_prof;
		private int asso_guest_faculty;
		private int assis_lect;
		private int total_teaching_staff;
		private int created_by;
		private Date created_date;
		private int modified_by;
		private Date modified_date;
		private int department_id;

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
		public int getP_id() {
			return p_id;
		}
		public void setP_id(int p_id) {
			this.p_id = p_id;
		}
		public int getS_id() {
			return s_id;
		}
		public void setS_id(int s_id) {
			this.s_id = s_id;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public int getFull_time_prof() {
			return full_time_prof;
		}
		public void setFull_time_prof(int full_time_prof) {
			this.full_time_prof = full_time_prof;
		}
		public int getAsso_guest_faculty() {
			return asso_guest_faculty;
		}
		public void setAsso_guest_faculty(int asso_guest_faculty) {
			this.asso_guest_faculty = asso_guest_faculty;
		}
		public int getAssis_lect() {
			return assis_lect;
		}
		public void setAssis_lect(int assis_lect) {
			this.assis_lect = assis_lect;
		}
		
		public int getTotal_teaching_staff() {
			return total_teaching_staff;
		}
		public void setTotal_teaching_staff(int total_teaching_staff) {
			this.total_teaching_staff = total_teaching_staff;
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
		public int getDepartment_id() {
			return department_id;
		}
		public void setDepartment_id(int department_id) {
			this.department_id = department_id;
		}
		public int getEach_total() {
			return each_total;
		}
		public void setEach_total(int each_total) {
			this.each_total = each_total;
		}
		
}
