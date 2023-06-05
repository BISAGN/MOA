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
@Table(name = "edu_cc_tb_t3_learning_object_child", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

public class CC_TB_T3_LEARNING_OBJECT_CHILD {
	
	private int id;
	private int p_id;
	private int a3_couse_outcome;
	private String b3_learning_obj;
	private int c3_domain_sub;
	private int d3_desirable_know;
	private int e3_level_show_know;
	private int f3_t_l_method;
	private int g3_assessment;
	private int h3_formative_summative;
	private int i3_term;
	private String j3_integration;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	public int getA3_couse_outcome() {
		return a3_couse_outcome;
	}
	public void setA3_couse_outcome(int a3_couse_outcome) {
		this.a3_couse_outcome = a3_couse_outcome;
	}
	public String getB3_learning_obj() {
		return b3_learning_obj;
	}
	public void setB3_learning_obj(String b3_learning_obj) {
		this.b3_learning_obj = b3_learning_obj;
	}
	public int getC3_domain_sub() {
		return c3_domain_sub;
	}
	public void setC3_domain_sub(int c3_domain_sub) {
		this.c3_domain_sub = c3_domain_sub;
	}
	public int getD3_desirable_know() {
		return d3_desirable_know;
	}
	public void setD3_desirable_know(int d3_desirable_know) {
		this.d3_desirable_know = d3_desirable_know;
	}
	public int getE3_level_show_know() {
		return e3_level_show_know;
	}
	public void setE3_level_show_know(int e3_level_show_know) {
		this.e3_level_show_know = e3_level_show_know;
	}
	public int getF3_t_l_method() {
		return f3_t_l_method;
	}
	public void setF3_t_l_method(int f3_t_l_method) {
		this.f3_t_l_method = f3_t_l_method;
	}
	public int getG3_assessment() {
		return g3_assessment;
	}
	public void setG3_assessment(int g3_assessment) {
		this.g3_assessment = g3_assessment;
	}
	public int getH3_formative_summative() {
		return h3_formative_summative;
	}
	public void setH3_formative_summative(int h3_formative_summative) {
		this.h3_formative_summative = h3_formative_summative;
	}
	public int getI3_term() {
		return i3_term;
	}
	public void setI3_term(int i3_term) {
		this.i3_term = i3_term;
	}
	public String getJ3_integration() {
		return j3_integration;
	}
	public void setJ3_integration(String j3_integration) {
		this.j3_integration = j3_integration;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
