package com.AyushEdu.controller.VC_System;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.VC_System.TB_MEETING_DTL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.VC_System.Create_MeetingDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Create_Meeting_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	private Create_MeetingDao cm;
	
	//-----------------------------------------SHA1_encrypt---------------------------------------//
	public static String SHA1_encrypt(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
 
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
 
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
 
            // return the HashText
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	//-----------------------------------------Create_Meeting---------------------------------------//
	
	@RequestMapping(value = "/Create_Meeting_Url", method = RequestMethod.GET)
	public ModelAndView Create_Meeting_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			//SECURITY ABHISHEK
			System.out.println("refer"+request.getHeader("Referer"));
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Create_Meeting_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Session sessionHQL = this.sessionFactory.openSession();
			int max_id = (int) sessionHQL.createQuery("select case when max(id) is null then 0 else max(id) end from TB_MEETING_DTL")
					.uniqueResult();
			System.err.println("max - "+String.valueOf(max_id+1));
			Mmap.put("max_id", "meeting"+String.valueOf(max_id+1));	
			Mmap.put("msg", msg);
			 String userid = session.getAttribute("userId_for_jnlp").toString();
					
	} catch (Exception e) {
		e.printStackTrace();
	}		
		return new ModelAndView("Create_MeetingTiles");
		
	}
	
	//-----------------------------------------Join_Meeting---------------------------------------//
	
	@RequestMapping(value = "/join_Meeting/{meetID}", method = RequestMethod.GET)
	public ModelAndView Join_Meeting_Url(ModelMap Mmap, 
			@PathVariable(value="meetID") String meetID) {
		Mmap.put(meetID, meetID);
		return new ModelAndView("Join_MeetingTiles");
		
	}
	
	@PostMapping("/getMeeting_Link")
	public @ResponseBody String getMeeting_Link(String meet_id,String join_name, String join_pass) throws ParseException {
		
//		String Link = "";
		meet_id = meet_id.split("id")[0];
		
		String uribase = "https://bisag.co.in/bigbluebutton/api/join?";
		String uripara = "";
		try {
			uripara = "fullName="+URLEncoder.encode(join_name, "UTF-8")
					+ "&meetingID="+meet_id
					+ "&password="+URLEncoder.encode(join_pass, "UTF-8")
					+ "&redirect=true";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		char ch = '+';   
		uripara = uripara.replace(' ', ch);
		String key = "102kHTt9AHbPlJ8MfiZMvx9uGdcyMiJbtvNnlue8";
		String checksum = SHA1_encrypt("join"+uripara+key);
		System.err.println("-----checksum----"+checksum);
		String checksumpara = "&checksum="+checksum;
		String uri = uribase+uripara+checksumpara;
		System.err.println("-----uri----"+uri);
		return uri;
	}
	
	//-----------------------------------------Join_Meeting End---------------------------------------//
	
	//========================================== Start Join_Meeting2==========================================//
//	
//	@RequestMapping(value = "/join_Meeting2/{meetID}", method = RequestMethod.GET)
//	public ModelAndView Join_Meeting2_Url(ModelMap Mmap, 
//			@PathVariable(value="meetID") String meetID) {
//		Mmap.put(meetID, meetID);
//		return new ModelAndView("Join_Meeting2Tiles");
//		
//	}
//	
//	@PostMapping("/getMeeting2_Link")
//	public @ResponseBody String getMeeting2_Link(String meet_id,String join_name, String join_pass) throws ParseException {
//		
////		String Link = "";
//		meet_id = meet_id.split("id")[0];
//		
//		String uribase = "https://bisag.co.in/bigbluebutton/api/join?";
//		String uripara = "";
//		try {
//			uripara = "fullName="+URLEncoder.encode(join_name, "UTF-8")
//					+ "&meetingID="+meet_id
//					+ "&password="+URLEncoder.encode(join_pass, "UTF-8")
//					+ "&redirect=true";
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		char ch = '+';   
//		uripara = uripara.replace(' ', ch);
//		String key = "102kHTt9AHbPlJ8MfiZMvx9uGdcyMiJbtvNnlue8";
//		String checksum = SHA1_encrypt("join"+uripara+key);
//		System.err.println("-----checksum----"+checksum);
//		String checksumpara = "&checksum="+checksum;
//		String uri = uribase+uripara+checksumpara;
//		System.err.println("-----uri----"+uri);
//		return uri;
//	}
	
	//========================================== End Join_Meeting2==========================================//
	
	//==========================================SAVE CREATE MEETING==========================================// 	
	
	@PostMapping(value = "/Create_Meeting_Action")
	public ModelAndView Create_Meeting_Action(@Validated @ModelAttribute("Create_Meeting_CMD") TB_MEETING_DTL td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra  ) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Create_Meeting_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Session sessiont = sessionFactory.openSession();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();

		String meetingid = request.getParameter("meeting_id");
		String name = request.getParameter("name");
		String attendeepw = request.getParameter("attendeepw_id");
		String moderatorpw = request.getParameter("moderatorpw_id");
		String welcome = request.getParameter("welcome");
		String record = request.getParameter("recordhid");
		String autostartrecording = request.getParameter("autostartrechid");
		String allowstartstoprecording = request.getParameter("allowstartstoprechid");
		String link = request.getParameter("linkhid");
		
		Date date = new Date();
		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
		Long c = (Long) sessionHQL.createQuery(
				"select count(*) from TB_MEETING_DTL where meeting_id=:meeting_id ")
				  .setParameter("meeting_id", meetingid)
				  .uniqueResult();
		
		if (id == 0) {
			td.setMeeting_id(meetingid);
			td.setName(name);	
			td.setAttendeepw_id(attendeepw);
			td.setModeratorpw_id(moderatorpw);
			td.setWelcome(welcome);
			if(record != null) {
				td.setRecord(Integer.parseInt(record));
			}
			if(autostartrecording != null) {
				td.setAutostartrecording(Integer.parseInt(autostartrecording));
			}
			if(allowstartstoprecording != null) {
				td.setAllowstartstoprecording(Integer.parseInt(allowstartstoprecording));
			}
			td.setLink(link);
			td.setCreated_by(username);
			td.setCreated_date(date);
		
	//-----------------------------------		
			String recordpara = "false";
			if (record.equals("1")) {
				recordpara= "true";
			}
			String autostartrecordingpara = "false";
			if (autostartrecording.equals("1")) {
				autostartrecordingpara= "true";
			}
			String allowstartstoprecordingpara = "false";
			if (allowstartstoprecording.equals("1")) {
				allowstartstoprecordingpara= "true";
			}
			System.err.println("----------------uhdidh--"+meetingid);
//			String linkpara = request.getParameter("linkhid");
			
//			String encodedQueryString = URLEncoder.encode(urlQueryString, "UTF-8");
			
			String uribase = "https://bisag.co.in/bigbluebutton/api/create?";
			String uripara = "";
			try {
				uripara = "allowStartStopRecording="+allowstartstoprecordingpara+""
						+ "&attendeePW="+URLEncoder.encode(attendeepw, "UTF-8")+""
								+ "&autoStartRecording="+autostartrecordingpara+""
								+ "&meetingID="+meetingid+""
										+ "&moderatorPW="+URLEncoder.encode(moderatorpw, "UTF-8")+""
												+ "&name="+URLEncoder.encode(name, "UTF-8")+""
														+ "&record="+recordpara+""
														+ "&welcome="+URLEncoder.encode(welcome, "UTF-8")+"";
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			char ch = '+';   
			uripara = uripara.replace(' ', ch);
			String key = "102kHTt9AHbPlJ8MfiZMvx9uGdcyMiJbtvNnlue8";
			String checksum = SHA1_encrypt("create"+uripara+key);
			System.err.println("-----checksum----"+checksum);
			String checksumpara = "&checksum="+checksum;
			String uri = uribase+uripara+checksumpara;
			System.err.println("-----uripara----"+uripara);
//			String uri ="https://reqres.in/api/users?page=2";
			RestTemplate restTemplate = new RestTemplate();
			String Result = restTemplate.getForObject(uri, String.class);
			System.err.println("res -"+(Result));
//			System.err.println("res -"+(new JSONObject(Result)).getString("response"));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
			DocumentBuilder builder; 
			String returncode = "";
			try {
				builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(Result)));  
				System.err.println(document);
				System.err.println("returncode - "+document.getElementsByTagName("returncode").item(0).getChildNodes().item(0).getNodeValue());
				returncode = document.getElementsByTagName("returncode").item(0).getChildNodes().item(0).getNodeValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
	//------------------------------------------------
				if (c == 0 && returncode.equals("SUCCESS")) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Meeting Created Successfully.");
				} else {
					ra.addAttribute("msg", "Meeting already Exist.");
				}
			}

			tx.commit();
			} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
		}
			
			return new ModelAndView("redirect:Create_Meeting_Url");
	}

//==========================================SEARCH CREATE MEETING ==========================================// 	

	@PostMapping("/getFilterCreate_Meeting_data")
	public @ResponseBody List<Map<String, Object>> getFilterCreate_Meeting_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String meeting_id, String name, String attendeepw_id, String moderatorpw_id, String welcome, String record, String autostartrecording, String allowstartstoprecording, HttpSession session) {
		
		return cm.DataTableCreateMeetingDataList(startPage, pageLength,  orderColunm, orderType,Search, meeting_id, name, attendeepw_id, moderatorpw_id, welcome, record, autostartrecording, allowstartstoprecording);
	}
	
	@PostMapping("/getTotalCreate_Meeting_dataCount")
	public @ResponseBody long getTotalCreate_Meeting_dataCount(HttpSession sessionUserId, String Search, String meeting_id, String name, String attendeepw_id, String moderatorpw_id, String welcome, String record, String autostartrecording, String allowstartstoprecording, HttpSession session) {
	
		return cm.DataTableCreateMeetingDataTotalCount(Search, meeting_id, name, attendeepw_id, moderatorpw_id, welcome, record, autostartrecording, allowstartstoprecording);
	}

// -------------------------SEARCH Battalion  -------------------------------------//
 
		 @RequestMapping(value = "/admin/getSearch_Create_Meeting", method = RequestMethod.POST)
			public ModelAndView getSearch_Create_Meeting(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg) {
			 try {
				 if(request.getHeader("Referer") == null ) { 
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Create_Meeting_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

					Mmap.addAttribute("msg", msg);
					 
					} catch (Exception e) {
							e.printStackTrace();
							}
					return new ModelAndView("Create_MeetingTiles");
					
		 }
		 
		// -------------------------Delete Battalion  -------------------------------------//
			
		 @RequestMapping(value = "/Create_Meeting_Delete_Url", method = RequestMethod.POST)
			public ModelAndView Create_Meeting_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
			 if(request.getHeader("Referer") == null ) { 
				
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Create_Meeting_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"delete from TB_MEETING_DTL where id=:id")
							.setParameter("id", id).executeUpdate();
					
					tx.commit();
					session.close();
					if (app > 0) {
						System.err.println("check delete"+(app > 0));
						liststr.add("Data Deleted Successfully.");
					} else {
						liststr.add("Data not Deleted.");
					}
					ra.addAttribute("msg", liststr.get(0));
				} catch (Exception e) {
					e.printStackTrace();
					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					ra.addAttribute("msg", liststr.get(0));
					
				}
				return new ModelAndView("redirect:Create_Meeting_Url");
			}
				
}
