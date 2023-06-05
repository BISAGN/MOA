package com.AyushEdu.Models.Policy;

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
@Table(name = "tb_policy_initial_draft", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_POLICY_INITIAL_DRAFT {
	
	private int id;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String policy_type;
	private int policy_category;
	private int policy_sub_category;
	private String policy_unique_id;
	private String policy_draft_file;
	private String purpose;
	private String scope;
	private String policy_title;
	private String policy_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date initial_date;
	private String level_one_status;
	private String level_two_status;
	private String level_three_status;
	private String level_four_status;
	private Integer final_status;
	private String forward_status;
	private String remark;
	
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
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getPolicy_type() {
		return policy_type;
	}
	public void setPolicy_type(String policy_type) {
		this.policy_type = policy_type;
	}
	public int getPolicy_category() {
		return policy_category;
	}
	public void setPolicy_category(int policy_category) {
		this.policy_category = policy_category;
	}
	public int getPolicy_sub_category() {
		return policy_sub_category;
	}
	public void setPolicy_sub_category(int policy_sub_category) {
		this.policy_sub_category = policy_sub_category;
	}
	public String getPolicy_unique_id() {
		return policy_unique_id;
	}
	public void setPolicy_unique_id(String policy_unique_id) {
		this.policy_unique_id = policy_unique_id;
	}
	public String getPolicy_draft_file() {
		return policy_draft_file;
	}
	public void setPolicy_draft_file(String policy_draft_file) {
		this.policy_draft_file = policy_draft_file;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getPolicy_title() {
		return policy_title;
	}
	public void setPolicy_title(String policy_title) {
		this.policy_title = policy_title;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public Date getInitial_date() {
		return initial_date;
	}
	public void setInitial_date(Date initial_date) {
		this.initial_date = initial_date;
	}
	public String getLevel_one_status() {
		return level_one_status;
	}
	public void setLevel_one_status(String level_one_status) {
		this.level_one_status = level_one_status;
	}
	public String getLevel_two_status() {
		return level_two_status;
	}
	public void setLevel_two_status(String level_two_status) {
		this.level_two_status = level_two_status;
	}
	public String getLevel_three_status() {
		return level_three_status;
	}
	public void setLevel_three_status(String level_three_status) {
		this.level_three_status = level_three_status;
	}
	public String getLevel_four_status() {
		return level_four_status;
	}
	public void setLevel_four_status(String level_four_status) {
		this.level_four_status = level_four_status;
	}
	public Integer getFinal_status() {
		return final_status;
	}
	public void setFinal_status(Integer final_status) {
		this.final_status = final_status;
	}
	public String getForward_status() {
		return forward_status;
	}
	public void setForward_status(String forward_status) {
		this.forward_status = forward_status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
