/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AyushEdu.Models.Counselling_Institute;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rdp
 */

public class StudentEnrollment implements Serializable {

  
    private Long seid;
   
  
    private String neetrollnumber;
   
    private String applicationnumber;
   
    private int neetmarks;
   
    private int neetrank;
   
    private double tenthpercentage;
   
    private double twelvthpercentage;
    
    private String year;
    
    private String firstname;
    
    private String middlename;
  
    private String lastname;
    
    private String dob;
 
    private String mobilenumber;
   
    private String emaildi;
   
    private String aadhaarnumber;
   
   
    private int commtype;
  
    private String catname;
    
    

    public StudentEnrollment(int commtype,String neetrollnumber,String applicationnumber,int neetmarks,int neetrank,double tenthpercentage,double twelvthpercentage,String year,String firstname,String middlename,String lastname,String dob,String mobilenumber,String emaildi,String aadhaarnumber,Long seid,String catname) {
  	  this.commtype = commtype;
        this.neetrollnumber = neetrollnumber;
        this.applicationnumber = applicationnumber;
        this.neetmarks = neetmarks;
        this.neetrank = neetrank;
        this.tenthpercentage = tenthpercentage;
        this.twelvthpercentage = twelvthpercentage;
        this.year = year;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.dob = dob;
        this.mobilenumber = mobilenumber;
        this.emaildi = emaildi;
        this.aadhaarnumber = aadhaarnumber;
        this.seid = seid;
        
        this.catname = catname;
        
  }

   

    public Long getSeid() {
        return seid;
    }

    public void setSeid(Long seid) {
        this.seid = seid;
    }

    public String getNeetrollnumber() {
        return neetrollnumber;
    }

    public void setNeetrollnumber(String neetrollnumber) {
        this.neetrollnumber = neetrollnumber;
    }

    public String getApplicationnumber() {
        return applicationnumber;
    }

    public void setApplicationnumber(String applicationnumber) {
        this.applicationnumber = applicationnumber;
    }

    public int getNeetmarks() {
        return neetmarks;
    }

    public void setNeetmarks(int neetmarks) {
        this.neetmarks = neetmarks;
    }

    public int getNeetrank() {
        return neetrank;
    }

    public void setNeetrank(int neetrank) {
        this.neetrank = neetrank;
    }

    public double getTenthpercentage() {
        return tenthpercentage;
    }

    public void setTenthpercentage(double tenthpercentage) {
        this.tenthpercentage = tenthpercentage;
    }

    public double getTwelvthpercentage() {
        return twelvthpercentage;
    }

    public void setTwelvthpercentage(double twelvthpercentage) {
        this.twelvthpercentage = twelvthpercentage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmaildi() {
        return emaildi;
    }

    public void setEmaildi(String emaildi) {
        this.emaildi = emaildi;
    }

    public String getAadhaarnumber() {
        return aadhaarnumber;
    }

    public void setAadhaarnumber(String aadhaarnumber) {
        this.aadhaarnumber = aadhaarnumber;
    }

  
    public Integer getCommtype() {
        return commtype;
    }

    public void setCommtype(Integer commtype) {
        this.commtype = commtype;
    }

 
	
    
}
