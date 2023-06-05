package com.AyushEdu.Models.Clg_Reg_clg_Infra;

import java.math.BigInteger;
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
@Table(name="clg_reg_infra_hostel_details", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_HOSTEL_DETAILS {
	
	private int id;
	private int p_id;
	private String boys_area;
	private int boys_own_rented;
	private int boys_room_no;
	private int boys_capacity;
	private int boys_mess_facility;
	private int boys_warden_facility;
	private String girls_area;
	private int girls_own_rented;
	private int girls_room_no;
	private int girls_capacity;
	private int girls_mess_facility;
	private int girls_warden_facility;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
	private int boys_occupied_room;
	private int girls_occupied_room;
	private String guest_area;
	private int guest_own_rented;
	private int guest_room_no;
	private int guest_capacity;
	private int guest_mess_facility;
	private int guest_warden_facility;
	private int guest_occupied_room;
	private int institute_id;
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getBoys_area() {
		return boys_area;
	}
	public void setBoys_area(String boys_area) {
		this.boys_area = boys_area;
	}
	public int getBoys_own_rented() {
		return boys_own_rented;
	}
	public void setBoys_own_rented(int boys_own_rented) {
		this.boys_own_rented = boys_own_rented;
	}
	public int getBoys_room_no() {
		return boys_room_no;
	}
	public void setBoys_room_no(int boys_room_no) {
		this.boys_room_no = boys_room_no;
	}
	public int getBoys_capacity() {
		return boys_capacity;
	}
	public void setBoys_capacity(int boys_capacity) {
		this.boys_capacity = boys_capacity;
	}
	public int getBoys_mess_facility() {
		return boys_mess_facility;
	}
	public void setBoys_mess_facility(int boys_mess_facility) {
		this.boys_mess_facility = boys_mess_facility;
	}
	public int getBoys_warden_facility() {
		return boys_warden_facility;
	}
	public void setBoys_warden_facility(int boys_warden_facility) {
		this.boys_warden_facility = boys_warden_facility;
	}
	public String getGirls_area() {
		return girls_area;
	}
	public void setGirls_area(String girls_area) {
		this.girls_area = girls_area;
	}
	public int getGirls_own_rented() {
		return girls_own_rented;
	}
	public void setGirls_own_rented(int girls_own_rented) {
		this.girls_own_rented = girls_own_rented;
	}
	public int getGirls_room_no() {
		return girls_room_no;
	}
	public void setGirls_room_no(int girls_room_no) {
		this.girls_room_no = girls_room_no;
	}
	public int getGirls_capacity() {
		return girls_capacity;
	}
	public void setGirls_capacity(int girls_capacity) {
		this.girls_capacity = girls_capacity;
	}
	public int getGirls_mess_facility() {
		return girls_mess_facility;
	}
	public void setGirls_mess_facility(int girls_mess_facility) {
		this.girls_mess_facility = girls_mess_facility;
	}
	public int getGirls_warden_facility() {
		return girls_warden_facility;
	}
	public void setGirls_warden_facility(int girls_warden_facility) {
		this.girls_warden_facility = girls_warden_facility;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getModified_by() {
		return modified_by;
	}
	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getBoys_occupied_room() {
		return boys_occupied_room;
	}
	public void setBoys_occupied_room(int boys_occupied_room) {
		this.boys_occupied_room = boys_occupied_room;
	}
	public int getGirls_occupied_room() {
		return girls_occupied_room;
	}
	public void setGirls_occupied_room(int girls_occupied_room) {
		this.girls_occupied_room = girls_occupied_room;
	}
	public String getGuest_area() {
		return guest_area;
	}
	public void setGuest_area(String guest_area) {
		this.guest_area = guest_area;
	}
	public int getGuest_own_rented() {
		return guest_own_rented;
	}
	public void setGuest_own_rented(int guest_own_rented) {
		this.guest_own_rented = guest_own_rented;
	}
	public int getGuest_room_no() {
		return guest_room_no;
	}
	public void setGuest_room_no(int guest_room_no) {
		this.guest_room_no = guest_room_no;
	}
	public int getGuest_capacity() {
		return guest_capacity;
	}
	public void setGuest_capacity(int guest_capacity) {
		this.guest_capacity = guest_capacity;
	}
	public int getGuest_mess_facility() {
		return guest_mess_facility;
	}
	public void setGuest_mess_facility(int guest_mess_facility) {
		this.guest_mess_facility = guest_mess_facility;
	}
	public int getGuest_warden_facility() {
		return guest_warden_facility;
	}
	public void setGuest_warden_facility(int guest_warden_facility) {
		this.guest_warden_facility = guest_warden_facility;
	}
	public int getGuest_occupied_room() {
		return guest_occupied_room;
	}
	public void setGuest_occupied_room(int guest_occupied_room) {
		this.guest_occupied_room = guest_occupied_room;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
}

