package com.AyushEdu.controller.Counselling_Institute;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsSystemMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityMstr;
import com.AyushEdu.dao.Counselling_Institute.InstituteMasterDao_old;
import com.AyushEdu.dao.Counselling_Institute.SeatAllocationDao;

@Controller
public class CommonController_counselling {

	@Autowired
	InstituteMasterDao_old instituteMasterDao;
	
	@Autowired
	SeatAllocationDao seatAllocationDao;

	

//	@ResponseBody
//	@RequestMapping(value = "/admin/LoadCategory", method = RequestMethod.POST, produces = { "application/json" })
//	public String LoadCategory(HttpServletRequest request) {
//		JSONArray jSONArray = new JSONArray();
//		JSONObject object = new JSONObject();
//
//		JSONObject object1 = new JSONObject();
//
//		try {
//			System.out.println("dsg");
//
//			List<TbLmsCategoryMstr> tbLmsCategoryMstrs = instituteMasterDao.GetCategoryType();
//
//			System.out.println("tbLmsCategoryMstrs.size()" + tbLmsCategoryMstrs.size());
//			if (!tbLmsCategoryMstrs.isEmpty()) {
//				for (TbLmsCategoryMstr tbLmsCategoryMstr : tbLmsCategoryMstrs) {
//					object = new JSONObject();
//
//					object.put("id", tbLmsCategoryMstr.getId());
//					object.put("name", tbLmsCategoryMstr.getCategory());
//					jSONArray.add(object);
//				}
//
//				object1.put("categorylist", jSONArray);
//			} else {
//				jSONArray = new JSONArray();
//				object1.put("categorylist", jSONArray);
//			}
//
//			object1.put("Status", "1");
//			object1.put("Message", "Success");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			object1 = new JSONObject();
//			object1.put("Status", "0");
//			object1.put("Message", "Something went wrong");
//		}
//
//		return object1.toJSONString();
//	}

	

	@ResponseBody
	@RequestMapping(value = "/admin/LoadCountry", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadCountry(HttpServletRequest request) {
		
		System.err.println("\n\n**--**--**--**---**--LoadCountry");
		
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();

		try {

			List<EduLmsCountryMstr> eduLmsCountryMstrs = instituteMasterDao.GetCountry();

			if (!eduLmsCountryMstrs.isEmpty()) {
				for (EduLmsCountryMstr eduLmsCountryMstr : eduLmsCountryMstrs) {
					object = new JSONObject();

					object.put("id", eduLmsCountryMstr.getId());
					object.put("name", eduLmsCountryMstr.getName());
					jSONArray.add(object);
				}

				object1.put("Countrylist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("Countrylist", jSONArray);
			}

			object1.put("Status", "1");
			object1.put("Message", "Success");

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("Status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "/admin/GetStateData", method = RequestMethod.POST, produces = { "application/json" })
	public String GetStateData(@RequestBody String data, HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			object = (JSONObject) jsonParser.parse(data);
			if (object.get("country") != null) {
				int countryid = Integer.parseInt(object.get("country").toString());
				List<EduLmsStateMstr> list1 = instituteMasterDao.getStateData(countryid);

				if (!list1.isEmpty()) {
					for (int i = 0; i < list1.size(); i++) {
						object = new JSONObject();
						EduLmsStateMstr statemaster = list1.get(i);
						object.put("id", statemaster.getStateId());
						object.put("name", statemaster.getStateName());
						jSONArray.add(object);
					}

					object1.put("stateList", jSONArray);
				} else {
					jSONArray = new JSONArray();
					object1.put("stateList", jSONArray);
				}
				object1.put("Status", "1");
				object1.put("Message", "Success");
			} else {
				object1.put("Status", "0");
				object1.put("Message", "State Not Found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("Status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "/admin/GetDistrictData", method = RequestMethod.POST, produces = { "application/json" })
	public String GetDistrictData(@RequestBody String data, HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		JSONObject object1 = new JSONObject();

		try {
			object = (JSONObject) jsonParser.parse(data);
			if (object.get("country") != null && object.get("state") != null) {
				int countryid = Integer.parseInt(object.get("country").toString());
				int stateid = Integer.parseInt(object.get("state").toString());
				List<EduLmsDistrictMstr> list1 = instituteMasterDao.getDistrictData(countryid, stateid);

				if (!list1.isEmpty()) {
					for (int i = 0; i < list1.size(); i++) {
						object = new JSONObject();
						EduLmsDistrictMstr districtmst = list1.get(i);
						object.put("id", districtmst.getDistrictId());
						object.put("name", districtmst.getDistrictName());
						jSONArray.add(object);
					}

					object1.put("districtList", jSONArray);
				} else {
					jSONArray = new JSONArray();
					object1.put("districtList", jSONArray);
				}
				object1.put("Status", "1");
				object1.put("Message", "Success");
			} else {
				object1.put("Status", "0");
				object1.put("Message", "District Not Found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("Status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadSystemType", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadSystemType(HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();

		try {

			List<EduLmsSystemMstr> eduLmsSystemMstrs = instituteMasterDao.GetSystemType();

			if (!eduLmsSystemMstrs.isEmpty()) {
				for (EduLmsSystemMstr eduLmsSystemMstr : eduLmsSystemMstrs) {
					object = new JSONObject();

					object.put("id", eduLmsSystemMstr.getId());
					object.put("name", eduLmsSystemMstr.getSystemName());
					jSONArray.add(object);
				}

				object1.put("SystemList", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("SystemList", jSONArray);
			}

			object1.put("Status", "1");
			object1.put("Message", "Success");

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("Status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadUniversity", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadUniversity(HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();

		try {

			List<EduLmsUniversityMstr> eduLmsUniversityMstrs = instituteMasterDao.GetUniversity();

			if (!eduLmsUniversityMstrs.isEmpty()) {
				for (EduLmsUniversityMstr eduLmsUniversityMstr : eduLmsUniversityMstrs) {
					object = new JSONObject();

					object.put("id", eduLmsUniversityMstr.getId());
					object.put("name", eduLmsUniversityMstr.getUniversityName());
					jSONArray.add(object);
				}

				object1.put("UnivercityList", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("UnivercityList", jSONArray);
			}

			object1.put("status", "1");
			object1.put("message", "Success");

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadInstituteName", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadInstituteName(HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
		try {
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			String username = request.getSession().getAttribute("username").toString();
			String rolename = request.getSession().getAttribute("rolename").toString();
			System.out.println("rolename" + rolename);
			if (rolename.equalsIgnoreCase("State Council")) {
				int stateid = Integer.parseInt(request.getSession().getAttribute("stateid").toString());
				eduLmsInstituteRegs = instituteMasterDao.LoadInstituteDataForState(stateid);
			} else {
				eduLmsInstituteRegs = instituteMasterDao.LoadInstituteDataForCount(username,rolename,userid);
			}

			if (!eduLmsInstituteRegs.isEmpty()) {
				for (EduLmsInstituteReg eduLmsInstituteReg : eduLmsInstituteRegs) {
					object = new JSONObject();

					object.put("id", eduLmsInstituteReg.getId());
					object.put("name", eduLmsInstituteReg.getInstituteName());
					jSONArray.add(object);
				}

				object1.put("institutelist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("institutelist", jSONArray);
			}

			object1.put("Status", "1");
			object1.put("Message", "Success");

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("Status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}

//	@ResponseBody
//	@RequestMapping(value = "/admin/GetStatePercentageForCounscel", method = RequestMethod.POST, produces = {
//			"application/json" })
//	public String GetStatePercentageForCounscel(HttpServletRequest request, @RequestBody String data) {
//		JSONArray jSONArray = new JSONArray();
//		JSONObject object = new JSONObject();
//		JSONParser joParser = new JSONParser();
//
//		JSONObject object1 = new JSONObject();
//		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
//		try {
//
//			String username = request.getSession().getAttribute("username").toString();
//			String rolename = request.getSession().getAttribute("rolename").toString();
//
//			object = (JSONObject) joParser.parse(data);
//			int inid = Integer.parseInt(object.get("insid").toString());
//
//			String returnstring = seatAllocationDao.GetStatePercentageFromInsID(inid);
//
//			return returnstring;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			object1 = new JSONObject();
//			object1.put("status", "0");
//			object1.put("message", "Something went wrong");
//			return object1.toJSONString();
//		}
//
//	}

//	@ResponseBody
//	@RequestMapping(value = "/admin/LoadInstituteAccordingToSystem", method = RequestMethod.POST, produces = {
//			"application/json" })
//	public String LoadInstituteAccordingToSystem(HttpServletRequest request, @RequestBody String data) {
//		JSONArray jSONArray = new JSONArray();
//		JSONObject object = new JSONObject();
//
//		JSONObject object1 = new JSONObject();
//		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
//		JSONParser jsonParser = new JSONParser();
//		try {
//			object = (JSONObject) jsonParser.parse(data);
//			int userID = Integer.parseInt(request.getSession().getAttribute("userId").toString());
//			CoStudentenrollment studentEnrollment = studentEnrollmentDao.GetStudentEnrolledDataByLoginId(userID);
//			if (studentEnrollment != null) {
//				int systemid = Integer.parseInt(object.get("systemid").toString());
//				String systemnname = object.get("systemname").toString();
//				int createby = studentEnrollment.getCreateby();
//				long seid = studentEnrollment.getSeid();
//				int stateid =studentEnrollment.getStateid().getStateId();
//				int round = 0;
//				if(request.getSession().getAttribute("round") != null) {
//				round = Integer.parseInt(request.getSession().getAttribute("round").toString());
//				}
//				// int createby = 1009;
//				List coSeatallocationmatrixs = null;
//				String commission = object.get("roundfor").toString();
//				coSeatallocationmatrixs = seatAllocationDao.GetInstituteDataAccordingToSystem(systemid, systemnname,
//						createby, seid, round, commission,stateid);
//
//				if (!coSeatallocationmatrixs.isEmpty()) {
//					for (int i = 0; i < coSeatallocationmatrixs.size(); i++) {
//						Object[] objects = (Object[]) coSeatallocationmatrixs.get(i);
//						object = new JSONObject();
//						object.put("insid", objects[0].toString());
//						object.put("insname", objects[1].toString());
//						jSONArray.add(object);
//					}
//				}
//			} else {
//				object1 = new JSONObject();
//				object1.put("Status", "0");
//				object1.put("Message", "User Not Found");
//			}
//			object1.put("Status", "1");
//			object1.put("Message", "Success");
//			object1.put("inslist", jSONArray);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			object1 = new JSONObject();
//			object1.put("Status", "0");
//			object1.put("Message", "Something went wrong");
//		}
//
//		return object1.toJSONString();
//	}

	@RequestMapping(value = "/admin/getUniversityList", method = RequestMethod.POST)
	public @ResponseBody List<EduLmsUniversityMstr> getUniversityList(HttpServletRequest request) {

		List<EduLmsUniversityMstr> clist = instituteMasterDao.getUniversityList();

		return clist;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/admin/LoadInstituteAccordingToSystemGeneral", method = RequestMethod.POST, produces = {
//			"application/json" })
//	public String LoadInstituteAccordingToSystemGeneral(HttpServletRequest request, @RequestBody String data) {
//		JSONArray jSONArray = new JSONArray();
//		JSONObject object = new JSONObject();
//
//		JSONObject object1 = new JSONObject();
//		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
//		JSONParser jsonParser = new JSONParser();
//		try {
//			object = (JSONObject) jsonParser.parse(data);
//			int systemid = 0;
//				if(!object.get("systemid").toString().equalsIgnoreCase("ALL")) {
//					systemid = Integer.parseInt(object.get("systemid").toString());
//				}
//				String systemnname = object.get("systemname").toString();
//				
//				List coSeatallocationmatrixs = null;
//				coSeatallocationmatrixs = seatAllocationDao.GetInstituteDataAccordingToSystemGeneral(systemid);
//						
//
//				if (!coSeatallocationmatrixs.isEmpty()) {
//					for (int i = 0; i < coSeatallocationmatrixs.size(); i++) {
//						Object[] objects = (Object[]) coSeatallocationmatrixs.get(i);
//						object = new JSONObject();
//						object.put("insid", objects[0].toString());
//						object.put("insname", objects[1].toString());
//						jSONArray.add(object);
//					}
//				}
//			
//			object1.put("Status", "1");
//			object1.put("Message", "Success");
//			object1.put("inslist", jSONArray);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			object1 = new JSONObject();
//			object1.put("Status", "0");
//			object1.put("Message", "Something went wrong");
//		}
//
//		return object1.toJSONString();
//	}
	
	
	@ResponseBody
	@RequestMapping(value = "/admin/LoadInstituteAccordingToSystemGeneral", method = RequestMethod.POST, produces = {
			"application/json" })
	public String LoadInstituteAccordingToSystemGeneral(HttpServletRequest request, @RequestBody String data) {
		
		System.err.println("IN-------------------LoadInstituteAccordingToSystemGeneral");
		
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
		JSONParser jsonParser = new JSONParser();
		try {
			object = (JSONObject) jsonParser.parse(data);
			int systemid = 0;
				if(!object.get("systemid").toString().equalsIgnoreCase("ALL")) {
					systemid = Integer.parseInt(object.get("systemid").toString());
				}
				String systemnname = object.get("systemname").toString();
				System.err.println("system id------"+systemid);
				List coSeatallocationmatrixs = null;
				coSeatallocationmatrixs = seatAllocationDao.GetInstituteDataAccordingToSystemGeneral(systemid);
						

				if (!coSeatallocationmatrixs.isEmpty()) {
					for (int i = 0; i < coSeatallocationmatrixs.size(); i++) {
						Object[] objects = (Object[]) coSeatallocationmatrixs.get(i);
						object = new JSONObject();
						object.put("insid", objects[0].toString());
						object.put("insname", objects[1].toString());
						jSONArray.add(object);
					}
				}
			
			object1.put("Status", "1");
			object1.put("Message", "Success");
			object1.put("inslist", jSONArray);
			
			System.err.println("object1---"+object1.get("inslist"));

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("Status", "0");
			object1.put("Message", "Something went wrong");
		}

		return object1.toJSONString();
	}
	
	
}
