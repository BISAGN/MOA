package com.AyushEdu.Models.Alumni;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "edu_lms_alumni_info_urls", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class EDU_LMS_ALUMNI_INFO_URLS_old {

		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private int id;
		private String user_id;
		private String alu_name;
		private String institute_id;
		private String domain_name;
		private String work_info;
		private String url;
		private String description;
		private String created_by;
		private Date created_date;
		private String modified_by;
		private Date modified_date;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUser_id() {
			return user_id;
		}
		public String getAlu_name() {
			return alu_name;
		}
		public void setAlu_name(String alu_name) {
			this.alu_name = alu_name;
		}
		public String getInstitute_id() {
			return institute_id;
		}
		public void setInstitute_id(String institute_id) {
			this.institute_id = institute_id;
		}
		public String getDomain_name() {
			return domain_name;
		}
		public void setDomain_name(String domain_name) {
			this.domain_name = domain_name;
		}
		public String getWork_info() {
			return work_info;
		}
		public void setWork_info(String work_info) {
			this.work_info = work_info;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
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
		
		
		
		
	
	
	
	

}
