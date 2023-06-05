package com.AyushEdu.Models.LMS_Master;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "edu_lms_link_course_set_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class EDU_LMS_LINK_COURSE_SET_MSTR {
	
		 private int id ;
		 private int system_id;
		 private String created_by;
		 @DateTimeFormat(pattern = "dd/MM/yyyy")
		 private Date created_date;
		 private String modified_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		 private Date modified_date;
		 private int degree_id;
		 
		 private String course_video;
		 private String description;
		 
		
		
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public int getSystem_id() {
			return system_id;
		}
		public void setSystem_id(int system_id) {
			this.system_id = system_id;
		}
		public int getDegree_id() {
			return degree_id;
		}
		public void setDegree_id(int degree_id) {
			this.degree_id = degree_id;
		}
		public String getCourse_video() {
			return course_video;
		}
		public void setCourse_video(String course_video) {
			this.course_video = course_video;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
}
