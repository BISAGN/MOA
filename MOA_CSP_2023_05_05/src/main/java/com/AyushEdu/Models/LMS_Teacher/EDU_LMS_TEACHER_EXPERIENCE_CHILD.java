package com.AyushEdu.Models.LMS_Teacher;

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
@Table(name="tb_nch_teacher_exp_child", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class EDU_LMS_TEACHER_EXPERIENCE_CHILD {
	
	private int id;
	private String institute_name;
	private String depart_name;
	private String desig;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date from_date;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date to_date;
	 private String upload_file;
	 
	 private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	 
	 private int p_id;
	 private String employment_type;
	 private String honorarium;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstitute_name() {
		return institute_name;
	}

	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
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

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getEmployment_type() {
		return employment_type;
	}

	public void setEmployment_type(String employment_type) {
		this.employment_type = employment_type;
	}

	public String getHonorarium() {
		return honorarium;
	}

	public void setHonorarium(String honorarium) {
		this.honorarium = honorarium;
	}
	 
	
}
