package com.AyushEdu.Models.Curriculum;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "edu_cc_tb_link_ga_and_po", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

public class EDU_CC_TB_LINK_GA_AND_PO {
	
	private int id;
	private int system_id;
	private int degree_id;
	private int graduateattribute_id;
	private int programoutcome_id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getGraduateattribute_id() {
		return graduateattribute_id;
	}
	public void setGraduateattribute_id(int graduateattribute_id) {
		this.graduateattribute_id = graduateattribute_id;
	}
	
	public int getProgramoutcome_id() {
		return programoutcome_id;
	}
	public void setProgramoutcome_id(int programoutcome_id) {
		this.programoutcome_id = programoutcome_id;
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

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
