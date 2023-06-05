package com.AyushEdu.dao.Regulation;

import java.util.ArrayList;

public interface Practitioner_RegistrationDAO {

	public ArrayList<ArrayList<String>> Getaayushid_fetch(String ayush_id);
	
	public ArrayList<ArrayList<String>> Getnrhno_fetch(String nrh_no);
	
	public ArrayList<ArrayList<String>> Getayus_abha_arh_data_fetch(String ayus) ;

	public ArrayList<ArrayList<String>> Getnewdatavalidfetch(String newusername) ;

	public ArrayList<ArrayList<String>> getstatelistFromInstitute(String institute_name, String user_id);

	public ArrayList<ArrayList<String>> getInstitutelistFromState(String state_name, String user_id);

}
