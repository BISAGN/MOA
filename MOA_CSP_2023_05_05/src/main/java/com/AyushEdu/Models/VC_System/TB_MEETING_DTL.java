package com.AyushEdu.Models.VC_System;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_meeting_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_MEETING_DTL {	
	
	private int id;
	private String meeting_id;
	private String name;
	private String attendeepw_id;
	private String moderatorpw_id;
	private String fullname;
	private String welcome;
	private int record;
	private int autostartrecording;
	private int allowstartstoprecording;
	private String link;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	public int getAutostartrecording() {
		return autostartrecording;
	}
	public void setAutostartrecording(int autostartrecording) {
		this.autostartrecording = autostartrecording;
	}
	public int getAllowstartstoprecording() {
		return allowstartstoprecording;
	}
	public void setAllowstartstoprecording(int allowstartstoprecording) {
		this.allowstartstoprecording = allowstartstoprecording;
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
	public String getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(String meeting_id) {
		this.meeting_id = meeting_id;
	}
	public String getAttendeepw_id() {
		return attendeepw_id;
	}
	public void setAttendeepw_id(String attendeepw_id) {
		this.attendeepw_id = attendeepw_id;
	}
	public String getModeratorpw_id() {
		return moderatorpw_id;
	}
	public void setModeratorpw_id(String moderatorpw_id) {
		this.moderatorpw_id = moderatorpw_id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}