package com.AyushEdu.Models.Feedback;

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
@Table(name="tb_feedback_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class TB_FEEDBACK_DETAILS {
	
	private int id;
	private int user_id;
	private int feedback_for;
	private int feedback_rating;
	private String feedback_details;
	private int sentiment;
	private int feedback_subcat;

	private String created_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;

	 
	 
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getFeedback_for() {
		return feedback_for;
	}
	public void setFeedback_for(int feedback_for) {
		this.feedback_for = feedback_for;
	}
	
	public int getFeedback_rating() {
		return feedback_rating;
	}
	public void setFeedback_rating(int feedback_rating) {
		this.feedback_rating = feedback_rating;
	}
	
	public String getFeedback_details() {
		return feedback_details;
	}
	public void setFeedback_details(String feedback_details) {
		this.feedback_details = feedback_details;
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
	public int getSentiment() {
		return sentiment;
	}
	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}
	public int getFeedback_subcat() {
		return feedback_subcat;
	}
	public void setFeedback_subcat(int feedback_subcat) {
		this.feedback_subcat = feedback_subcat;
	}
	
	
	
	
}
