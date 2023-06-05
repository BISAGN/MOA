package com.AyushEdu.dao.Alumni;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_EVENT;

public interface Alumni_CREATE_EVENT_DAO {

	public EDU_ALUM_ALUMNI_EVENT getEventByid(int id);

	public String updateEvent(EDU_ALUM_ALUMNI_EVENT td);


	public List<Map<String, Object>> DataTableEventDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String title, String description, String upload_img, String venue,
			String date_time, String batch,String userid);

	public long DataTableEventDataTotalCount1(String Search, String title, String description, String upload_img,
			String venue, String date_time, String batch,String userid);
	
//	public ArrayList<ArrayList<String>> Getupcomingeventsdata();
	
	public List<Map<String, Object>> Getupcomingeventsdata();
	
	public List<Map<String, Object>> Getevent_divdata(String event_id);
	
	public String getEventImg(String id);
	
	public ArrayList<ArrayList<String>> getFeedsData();
	
	public String getFeedsImg(String id);
	
	public String getAlumniPP(String id);
	
	public List<Map<String, Object>> Getbatchmatealumnidata();
	
	public List<Map<String, Object>> GetAlumnibatchdata(String userid);
	
	public List<ArrayList<String>> getTotaleventinterestedCount(String id);
	
	public List<ArrayList<String>> getTotaleventparticipateCount(String id);
	
	public  ArrayList<ArrayList<String>> DataTableSearchEventDataList(int startPage, int pageLength, String Search,
			 String orderColunm, String orderType, String event_id, String name, String interested, String participating, String event_date);

	public long DataTableSearchEventDataTotalCount1(String Search,  String event_id, String name, String interested, String participating, String event_date);

}
