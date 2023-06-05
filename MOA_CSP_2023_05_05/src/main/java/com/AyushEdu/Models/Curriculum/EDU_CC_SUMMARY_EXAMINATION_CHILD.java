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
@Table(name = "edu_cc_summary_examination_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class EDU_CC_SUMMARY_EXAMINATION_CHILD {

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private Integer exam_paper; 
	private Integer theory_comp_marks; 
//	private Integer pract_cb; 
//	private Integer practical_marks; 
//	private Integer viva_cb; 
//	private Integer viva_marks; 
//	private Integer ele_cb; 
//	private Integer elective_marks; 
//	private Integer ia_cb; 
//	private Integer ia_marks; 
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private Integer p_id;
	private Integer status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getExam_paper() {
		return exam_paper;
	}
	public void setExam_paper(Integer exam_paper) {
		this.exam_paper = exam_paper;
	}
	public Integer getTheory_comp_marks() {
		return theory_comp_marks;
	}
	public void setTheory_comp_marks(Integer theory_comp_marks) {
		this.theory_comp_marks = theory_comp_marks;
	}
//	public Integer getPract_cb() {
//		return pract_cb;
//	}
//	public void setPract_cb(Integer pract_cb) {
//		this.pract_cb = pract_cb;
//	}
//	public Integer getPractical_marks() {
//		return practical_marks;
//	}
//	public void setPractical_marks(Integer practical_marks) {
//		this.practical_marks = practical_marks;
//	}
//	public Integer getViva_cb() {
//		return viva_cb;
//	}
//	public void setViva_cb(Integer viva_cb) {
//		this.viva_cb = viva_cb;
//	}
//	public Integer getViva_marks() {
//		return viva_marks;
//	}
//	public void setViva_marks(Integer viva_marks) {
//		this.viva_marks = viva_marks;
//	}
//	public Integer getEle_cb() {
//		return ele_cb;
//	}
//	public void setEle_cb(Integer ele_cb) {
//		this.ele_cb = ele_cb;
//	}
//	public Integer getElective_marks() {
//		return elective_marks;
//	}
//	public void setElective_marks(Integer elective_marks) {
//		this.elective_marks = elective_marks;
//	}
//	public Integer getIa_cb() {
//		return ia_cb;
//	}
//	public void setIa_cb(Integer ia_cb) {
//		this.ia_cb = ia_cb;
//	}
//	public Integer getIa_marks() {
//		return ia_marks;
//	}
//	public void setIa_marks(Integer ia_marks) {
//		this.ia_marks = ia_marks;
//	}
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
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
