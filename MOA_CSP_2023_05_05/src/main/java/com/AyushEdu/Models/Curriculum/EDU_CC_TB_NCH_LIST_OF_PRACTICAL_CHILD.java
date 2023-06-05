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
@Table(name = "edu_cc_tb_nch_list_of_practical_child", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class EDU_CC_TB_NCH_LIST_OF_PRACTICAL_CHILD {
	private int id;
	private int p_id;
	private String sub_practical;
	private int term_id;
	private String hours;
	private String demo_perform;
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
	
	public String getSub_practical() {
		return sub_practical;
	}
	public void setSub_practical(String sub_practical) {
		this.sub_practical = sub_practical;
	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}

	public String getDemo_perform() {
		return demo_perform;
	}
	public void setDemo_perform(String demo_perform) {
		this.demo_perform = demo_perform;
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
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	
	
}
