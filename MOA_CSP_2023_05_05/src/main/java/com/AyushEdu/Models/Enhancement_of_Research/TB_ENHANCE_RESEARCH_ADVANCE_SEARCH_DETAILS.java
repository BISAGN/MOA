package com.AyushEdu.Models.Enhancement_of_Research;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_enhance_research_advance_search_details", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS {
	
	private int id;
	private int medicine_system;
	private int category;
	private int search_field = 0;
	private String title;
	private String hyperlink;
	private String desc_content;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String upload_paper;
	private int status;
	private int institute_name;
	private String author_name;
	private String journal_name;
	private String abstract_content;
	

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMedicine_system() {
		return medicine_system;
	}
	public void setMedicine_system(int medicine_system) {
		this.medicine_system = medicine_system;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getSearch_field() {
		return search_field;
	}
	public void setSearch_field(int search_field) {
		this.search_field = search_field;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHyperlink() {
		return hyperlink;
	}
	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}
	public String getDesc_content() {
		return desc_content;
	}
	public void setDesc_content(String desc_content) {
		this.desc_content = desc_content;
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
	public String getUpload_paper() {
		return upload_paper;
	}
	public void setUpload_paper(String upload_paper) {
		this.upload_paper = upload_paper;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(int institute_name) {
		this.institute_name = institute_name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getJournal_name() {
		return journal_name;
	}
	public void setJournal_name(String journal_name) {
		this.journal_name = journal_name;
	}
	public String getAbstract_content() {
		return abstract_content;
	}
	public void setAbstract_content(String abstract_content) {
		this.abstract_content = abstract_content;
	}

}
