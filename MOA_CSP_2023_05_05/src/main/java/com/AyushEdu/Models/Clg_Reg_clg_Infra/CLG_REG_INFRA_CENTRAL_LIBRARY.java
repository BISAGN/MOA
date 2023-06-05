package com.AyushEdu.Models.Clg_Reg_clg_Infra;

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
@Table(name="clg_reg_infra_central_library", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class CLG_REG_INFRA_CENTRAL_LIBRARY {
	
	private int id;
	private int p_id;
	private String text_book;
	private String regerence_book;
	private String govn_publication;
	private String new_addition;
	private String book_bank;
	private int total_book_no;
	private int subscribed_no;
	private int complementary_no;
	private int news_paper_no;
	private int back_issue_no;
	private String library_working_hours;
	private String student_reading_room_capacity;
	private String faculty_reading_room_capacity;
	private String rading_room_purpose;
	private String home_lending;
	private String photocopying_facility;
	private int computers_facility;
	private int cataloguing_books;
	private String cataloguing_system;
	private String librarian_name;
	private String librarian_qualification;
	private String other_information;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private int s_id;
	private int elibrary_check;
	private int total_computers;
	private int total_subscription;
	private int total_ebooks;
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
	public String getText_book() {
		return text_book;
	}
	public void setText_book(String text_book) {
		this.text_book = text_book;
	}
	public String getRegerence_book() {
		return regerence_book;
	}
	public void setRegerence_book(String regerence_book) {
		this.regerence_book = regerence_book;
	}
	public String getGovn_publication() {
		return govn_publication;
	}
	public void setGovn_publication(String govn_publication) {
		this.govn_publication = govn_publication;
	}
	public String getNew_addition() {
		return new_addition;
	}
	public void setNew_addition(String new_addition) {
		this.new_addition = new_addition;
	}
	public String getBook_bank() {
		return book_bank;
	}
	public void setBook_bank(String book_bank) {
		this.book_bank = book_bank;
	}
	public int getTotal_book_no() {
		return total_book_no;
	}
	public void setTotal_book_no(int total_book_no) {
		this.total_book_no = total_book_no;
	}
	public int getSubscribed_no() {
		return subscribed_no;
	}
	public void setSubscribed_no(int subscribed_no) {
		this.subscribed_no = subscribed_no;
	}
	public int getComplementary_no() {
		return complementary_no;
	}
	public void setComplementary_no(int complementary_no) {
		this.complementary_no = complementary_no;
	}
	public int getNews_paper_no() {
		return news_paper_no;
	}
	public void setNews_paper_no(int news_paper_no) {
		this.news_paper_no = news_paper_no;
	}
	public int getBack_issue_no() {
		return back_issue_no;
	}
	public void setBack_issue_no(int back_issue_no) {
		this.back_issue_no = back_issue_no;
	}
	public String getLibrary_working_hours() {
		return library_working_hours;
	}
	public void setLibrary_working_hours(String library_working_hours) {
		this.library_working_hours = library_working_hours;
	}
	public String getStudent_reading_room_capacity() {
		return student_reading_room_capacity;
	}
	public void setStudent_reading_room_capacity(String student_reading_room_capacity) {
		this.student_reading_room_capacity = student_reading_room_capacity;
	}
	public String getFaculty_reading_room_capacity() {
		return faculty_reading_room_capacity;
	}
	public void setFaculty_reading_room_capacity(String faculty_reading_room_capacity) {
		this.faculty_reading_room_capacity = faculty_reading_room_capacity;
	}
	public String getRading_room_purpose() {
		return rading_room_purpose;
	}
	public void setRading_room_purpose(String rading_room_purpose) {
		this.rading_room_purpose = rading_room_purpose;
	}
	public String getHome_lending() {
		return home_lending;
	}
	public void setHome_lending(String home_lending) {
		this.home_lending = home_lending;
	}
	public String getPhotocopying_facility() {
		return photocopying_facility;
	}
	public void setPhotocopying_facility(String photocopying_facility) {
		this.photocopying_facility = photocopying_facility;
	}
	public int getComputers_facility() {
		return computers_facility;
	}
	public void setComputers_facility(int computers_facility) {
		this.computers_facility = computers_facility;
	}
	public int getCataloguing_books() {
		return cataloguing_books;
	}
	public void setCataloguing_books(int cataloguing_books) {
		this.cataloguing_books = cataloguing_books;
	}
	public String getCataloguing_system() {
		return cataloguing_system;
	}
	public void setCataloguing_system(String cataloguing_system) {
		this.cataloguing_system = cataloguing_system;
	}
	public String getLibrarian_name() {
		return librarian_name;
	}
	public void setLibrarian_name(String librarian_name) {
		this.librarian_name = librarian_name;
	}
	public String getLibrarian_qualification() {
		return librarian_qualification;
	}
	public void setLibrarian_qualification(String librarian_qualification) {
		this.librarian_qualification = librarian_qualification;
	}
	public String getOther_information() {
		return other_information;
	}
	public void setOther_information(String other_information) {
		this.other_information = other_information;
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
	public int getElibrary_check() {
		return elibrary_check;
	}
	public void setElibrary_check(int elibrary_check) {
		this.elibrary_check = elibrary_check;
	}
	public int getTotal_computers() {
		return total_computers;
	}
	public void setTotal_computers(int total_computers) {
		this.total_computers = total_computers;
	}
	public int getTotal_subscription() {
		return total_subscription;
	}
	public void setTotal_subscription(int total_subscription) {
		this.total_subscription = total_subscription;
	}
	public int getTotal_ebooks() {
		return total_ebooks;
	}
	public void setTotal_ebooks(int total_ebooks) {
		this.total_ebooks = total_ebooks;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	
}

