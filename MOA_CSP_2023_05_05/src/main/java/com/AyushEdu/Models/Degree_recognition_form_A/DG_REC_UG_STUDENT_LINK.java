package com.AyushEdu.Models.Degree_recognition_form_A;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dg_rec_ug_student_link", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class DG_REC_UG_STUDENT_LINK {
	
	private int id;
//	private int college_id;
	private int user_id;
	private int t_a_id;
	private int t_b_id;
	private int t_c_id;
	private int t_d_id;
	private int t_e_id;
	private int t_f_id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public int getCollege_id() {
//		return college_id;
//	}
//	public void setCollege_id(int college_id) {
//		this.college_id = college_id;
//	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getT_a_id() {
		return t_a_id;
	}
	public void setT_a_id(int t_a_id) {
		this.t_a_id = t_a_id;
	}
	public int getT_b_id() {
		return t_b_id;
	}
	public void setT_b_id(int t_b_id) {
		this.t_b_id = t_b_id;
	}
	public int getT_c_id() {
		return t_c_id;
	}
	public void setT_c_id(int t_c_id) {
		this.t_c_id = t_c_id;
	}
	public int getT_d_id() {
		return t_d_id;
	}
	public void setT_d_id(int t_d_id) {
		this.t_d_id = t_d_id;
	}
	public int getT_e_id() {
		return t_e_id;
	}
	public void setT_e_id(int t_e_id) {
		this.t_e_id = t_e_id;
	}
	public int getT_f_id() {
		return t_f_id;
	}
	public void setT_f_id(int t_f_id) {
		this.t_f_id = t_f_id;
	}
	
}
