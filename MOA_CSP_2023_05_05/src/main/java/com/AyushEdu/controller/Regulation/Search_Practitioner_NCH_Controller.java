package com.AyushEdu.controller.Regulation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Search_NCH_PracDAO;
import com.AyushEdu.dao.Regulation.Search_State_PracDAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_Practitioner_NCH_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Autowired
	Search_State_PracDAO  Rdao;

	
	@Autowired
	Search_NCH_PracDAO  NRdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/Search_NCH_pracUrl", method = RequestMethod.GET)
	public ModelAndView Search_NCH_pracUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_NCH_pracUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
	    Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
	    Mmap.put("getgenderList", common.getgenderList(sessionFactory));

		// HET CHANGES
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		// HET CHANGES
		} catch (Exception e) {
			e.printStackTrace();
	    }
		return new ModelAndView("Search_nch1_Prac_Tiles");
	}
	
	@PostMapping("/getFilter_NCH_Prac_data")
	public @ResponseBody List<Map<String, Object>> getFilter_NCH_Prac_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String institute_status ,String gender,
			  String registration_state ,String per_state, String per_district,String type_of_degree,String  degree_name ,String place_of_working,
			String dob,String date_of_reg,String institute_name,String type_status) {
 		return NRdao.DataTableSeacrh_NCH_PracDataList(startPage, pageLength, Search, orderColunm, orderType, first_name, institute_status ,gender, 
				 registration_state,  per_state,  per_district,  type_of_degree,   degree_name ,place_of_working,dob,date_of_reg,institute_name, type_status);

	}
	
	@PostMapping("/getTotalNCH_Prac_dataCount")
	public @ResponseBody long getTotalState_Prac_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String institute_status,String gender ,
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
			String dob,String date_of_reg,String institute_name,String type_status) {
		return NRdao.DataTableSeacrh_NCH_PracDataTotalCount(Search, first_name,institute_status,gender,   registration_state ,  per_state,per_district,  type_of_degree
				,   degree_name ,  place_of_working ,dob,date_of_reg,institute_name, type_status);

	}
 
	@RequestMapping(value = "/reject_NCHregulation_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> reject_NCHregulation_Data(String a,String status,String tempSt,HttpSession session) throws ParseException {	
		String username = session.getAttribute("username").toString();
		
		String[] tempSt2 = tempSt.split(",");
		String[] id_list = a.split(":");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		for (int i = 0; i < id_list.length; i++) {
			
			id = Integer.parseInt(id_list[i]);
			System.out.println("id_list============= "+id_list[i]);
			System.out.println("remarks======================== "+tempSt2[i]);
			list2.add(NRdao.reject_NCHPracData(Integer.toString(id),status,tempSt2[i]));
		}
		return list2;
	}
	
	@RequestMapping(value = "/Approve_NCHregulation_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_NCHregulation_Data(String a,String upto,String status,String per_state,HttpSession session) throws ParseException {	
		
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		String username = session.getAttribute("username").toString();
 		String valid_upto1 = upto;
		
 		String state_reg_no ="";
		String[] id_list = a.split(":");
		String[] id_list2 = upto.split(":");

		String nrh_en_no ="";
		 
		
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String date;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			nrh_en_no  = NRdao.getMaxAID();
			state_reg_no  = Rdao.getMaxAID();
			date = id_list2[i];
			 
			list2.add(NRdao.approve_NCHregData(Integer.toString(id),date,status,username,state_reg_no,per_state, nrh_en_no));
			//end
		}
 
			 
		return list2;
	}
	
	 @RequestMapping(value = "/get_Parctname_by_NCHReject_id", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_Parctname_by_NCHReject_id(String id) {
	    	ArrayList<ArrayList<String>> data = NRdao.get_Parctname_by_NCHReject_idata(id);
	    	
	    	System.err.println("hiiiiiii");
	    	
	    	return data;
	 	}
}