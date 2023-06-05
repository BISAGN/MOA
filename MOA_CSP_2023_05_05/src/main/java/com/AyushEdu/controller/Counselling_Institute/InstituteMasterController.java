package com.AyushEdu.controller.Counselling_Institute;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Models.Counselling_Institute.CoFeescategorytype;
import com.AyushEdu.Models.Counselling_Institute.CoInstituteotherdetail;
import com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDegreeCateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsSystemMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityMstr;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Counselling_Institute.InstituteMasterDao;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;

@Controller
public class InstituteMasterController {

	@Autowired
	InstituteMasterDao instituteMasterDao;
	
	@Autowired
	Type_of_Degree_MstrDao  TDdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/InstitutemasterUrl", method = RequestMethod.GET)
	public ModelAndView InstitutemasterUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("InstitutemasterUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String roleid = request.getSession().getAttribute("roleid").toString();
//		Boolean val = roleBaseMenuDAO.ScreenRedirect("StudentEnrollmentUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
//		Mmap.put("msg", msg);
		return new ModelAndView("Institutemaster_Tiles");
	}

//	@RequestMapping(value = "/InstituteInformation", method = RequestMethod.GET)
//	public ModelAndView InstituteInformation(ModelMap Mmap, HttpSession session, HttpServletRequest request,
//			@RequestParam(value = "msg", required = false) String msg) {
////		Boolean val = roleBaseMenuDAO.ScreenRedirect("StudentEnrollmentUrl", roleid);
////		if (val == false) {
////			return new ModelAndView("AccessTiles");
////		}
////		if (request.getHeader("Referer") == null) {
////			msg = "";
////		}
////		Mmap.put("msg", msg);
//		Mmap.addAttribute("langugae", "english");
//		Mmap.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
//		return new ModelAndView("InstituteInformation");
//	}

	@ResponseBody
	@RequestMapping(value = "/admin/SaveInstituteData", method = RequestMethod.POST, produces = { "application/json" })
	public String SaveInstituteData(HttpServletRequest request, @RequestPart String jsondata,
			@RequestPart MultipartFile image) {
		JSONArray jsonp = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		String res = "";
		try {
			object = (JSONObject) jsonparser.parse(jsondata);

			res = SaveInstitutedData(object, request, image, "", "N");
		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Something went wrong");
			res = jsonobjectout.toJSONString();
		}

		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/SaveInstituteDataFoUpdate", method = RequestMethod.POST, produces = {
			"application/json" })
	public String SaveInstituteDataFoUpdate(HttpServletRequest request, @RequestPart String jsondata) {
		JSONArray jsonp = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		String res = "";
		try {
			object = (JSONObject) jsonparser.parse(jsondata);
			MultipartFile image = null;
			res = SaveInstitutedData(object, request, image, "", "N");
		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Something went wrong");
			res = jsonobjectout.toJSONString();
		}

		return res;
	}

	public String SaveInstitutedData(JSONObject object, HttpServletRequest request, MultipartFile image,
			String imagename, String excelupload) throws IOException {
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		String rolename = request.getSession().getAttribute("rolename").toString();
		String systemtype = "";
		String university = "";
		String inscode = "";
		String insname = "";
		String mobilenumber = "";
		String email = "";
		String country = "";
		String state = "";
		String district = "";
		String address = "";
		String insabb = "";
		String res = "";

		String actiontype = "Edit";
		if (object.get("actiontype") != null) {
			actiontype = object.get("actiontype").toString();
		}
		if (object.get("systemtype") == null || object.get("systemtype") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select System Type");
			return jsonobjectout.toJSONString();
		} else {
			systemtype = object.get("systemtype").toString();
			if (systemtype.equalsIgnoreCase("-1")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Select System Type");
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("university") == null || object.get("university") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select University");
			return jsonobjectout.toJSONString();
		} else {
			university = object.get("university").toString();
			if (university.equalsIgnoreCase("-1")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Select University");
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("inscode") == null || object.get("inscode") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Institute Code");
			return jsonobjectout.toJSONString();
		} else {
			inscode = object.get("inscode").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(inscode, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Code " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(inscode, 16, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Code " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphaNumericWithoutSpaceRegExp(inscode);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("insname") == null || object.get("insname") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Institute Name");
			return jsonobjectout.toJSONString();
		} else {
			insname = object.get("insname").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(insname, 4);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(insname, 128, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabeAndSpaceRegExp(insname);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Name " + res);
				return jsonobjectout.toJSONString();
			}
		}
		if (actiontype.equalsIgnoreCase("add")) {
			if (!excelupload.equalsIgnoreCase("Y")) {
				String message = councellingValidationController.checkFileFormats(image, image.getOriginalFilename(),
						"image");
				if (!message.equalsIgnoreCase("success")) {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", message + " For Institute Photo");
					return jsonobjectout.toJSONString();
				}
			}
		} else {
			if (image != null) {
				String message = councellingValidationController.checkFileFormats(image, image.getOriginalFilename(),
						"image");
				if (!message.equalsIgnoreCase("success")) {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", message + " For Institute Photo");
					return jsonobjectout.toJSONString();
				}
			}
		}

		if (object.get("mobilenumber") == null || object.get("mobilenumber") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Mobile Number");
			return jsonobjectout.toJSONString();
		} else {
			mobilenumber = object.get("mobilenumber").toString();
			res = councellingValidationController.MinimumLengthCheck(mobilenumber, 10);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Mobile Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(mobilenumber, 10, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Mobile Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(mobilenumber);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Mobile Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("insabb") == null || object.get("insabb") == "")

		{
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Institute Abbreviation");
			return jsonobjectout.toJSONString();
		} else {
			insabb = object.get("insabb").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(insabb, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Abbreviation " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(insabb, 8, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Abbreviation " + res);
				return jsonobjectout.toJSONString();
			}

//			res = councellingValidationController.OnlyAlphaNumericWithoutSpaceRegExp(insabb);
			res = councellingValidationController.OnlyAlphaNumericWithoutSpaceRegExp(insabb);
			
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Abbreviation " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("email") == null || object.get("email") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Email");
			return jsonobjectout.toJSONString();
		} else {
			email = object.get("email").toString().trim();
			if (email.length() < 8) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Email must be of atleast 8 letters.");
				return jsonobjectout.toJSONString();
			} else if (email.length() > 128) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Email must be of Maximum 128 letters.");
				return jsonobjectout.toJSONString();
			} else {
				String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
						+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				// Compile regular expression to get the pattern
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);

				if (!matcher.matches()) {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Enter Valid Email");
					return jsonobjectout.toJSONString();
				}
			}
		}

		if (object.get("country") == null || object.get("country") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select Country");
			return jsonobjectout.toJSONString();
		} else {
			country = object.get("country").toString();
			if (country.equalsIgnoreCase("-1")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Select Country");
				return jsonobjectout.toJSONString();
			}
		}
		if (object.get("state") == null || object.get("state") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select State");
			return jsonobjectout.toJSONString();
		} else {
			state = object.get("state").toString();
			if (state.equalsIgnoreCase("-1")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Select State");
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("district") == null || object.get("district") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select District");
			return jsonobjectout.toJSONString();
		} else {
			district = object.get("district").toString();
			if (district.equalsIgnoreCase("-1")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Select District");
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("address") == null || object.get("address") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Address");
			return jsonobjectout.toJSONString();
		} else {
			address = object.get("address").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(address, 6);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Address " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(address, 256, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Address " + res);
				return jsonobjectout.toJSONString();
			}

		}

		boolean checkresforinscode = false;
		boolean checkresforinsname = false;

		EduLmsInstituteReg eduLmsInstituteReg = null;
		int systemtypeint = Integer.parseInt(systemtype);
		int universityint = Integer.parseInt(university);

		List Checkinscodeexist = instituteMasterDao.CheckInstituteCodeExist(inscode.trim().toLowerCase(), systemtypeint,
				universityint);

		List Checkinsnameexist = instituteMasterDao.CheckInstiuteNameExist(insname.trim().toLowerCase(), systemtypeint,
				universityint);
		if (actiontype.equalsIgnoreCase("add")) {
			if (Checkinscodeexist.isEmpty()) {
				checkresforinscode = true;
			}

			if (Checkinsnameexist.isEmpty()) {
				checkresforinsname = true;
			}
		} else {

			if (Checkinscodeexist.isEmpty()) {
				checkresforinscode = true;
			} else {
				if (Checkinscodeexist.size() > 1) {
					checkresforinscode = false;
				} else {
					eduLmsInstituteReg = instituteMasterDao
							.GetInstituteDataByID(Integer.parseInt(object.get("insid").toString()));
					if (eduLmsInstituteReg.getCode().trim().equalsIgnoreCase(inscode)) {
						checkresforinscode = true;
					} else {
						checkresforinscode = false;
					}
				}
			}

			if (Checkinsnameexist.isEmpty()) {
				checkresforinsname = true;
			} else {
				if (Checkinsnameexist.size() > 1) {
					checkresforinsname = false;
				} else {
					eduLmsInstituteReg = instituteMasterDao
							.GetInstituteDataByID(Integer.parseInt(object.get("insid").toString()));
					if (eduLmsInstituteReg.getInstituteName().trim().equalsIgnoreCase(insname)) {
						checkresforinsname = true;
					} else {
						checkresforinsname = false;
					}
				}
			}

		}

		if (checkresforinscode && checkresforinsname) {
			if (!actiontype.equalsIgnoreCase("add")) {
				eduLmsInstituteReg = instituteMasterDao
						.GetInstituteDataByID(Integer.parseInt(object.get("insid").toString()));
			}

			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			// int stateid =
			// Integer.parseInt(request.getSession().getAttribute("stateid").toString());
			if (actiontype.equalsIgnoreCase("add")) {
				eduLmsInstituteReg = new EduLmsInstituteReg();
			}

			eduLmsInstituteReg.setAddress(address);
			eduLmsInstituteReg.setAppStatus("1");
			eduLmsInstituteReg.setCode(inscode);

			eduLmsInstituteReg.setCollegeAbbr(insabb);

			String collegeUniqueID = instituteMasterDao.GETCollgegeUniqueIDwithIncrement(systemtypeint);
			if (collegeUniqueID == null || collegeUniqueID == "" || collegeUniqueID.equalsIgnoreCase("")) {
				String getsystemabr = instituteMasterDao.GetSystemABR(systemtypeint);
				collegeUniqueID = getsystemabr + "0001";
			}
			eduLmsInstituteReg.setCollegeUniqueId(collegeUniqueID);

			EduLmsCountryMstr eduLmsCountryMstr = new EduLmsCountryMstr();
			eduLmsCountryMstr.setId(Integer.parseInt(country));
			eduLmsInstituteReg.setCountryId(eduLmsCountryMstr);
			EduLmsDistrictMstr eduLmsDistrictMstr = new EduLmsDistrictMstr();
			eduLmsDistrictMstr.setDistrictId(Integer.parseInt(district));
			eduLmsInstituteReg.setDistrictId(eduLmsDistrictMstr);
			eduLmsInstituteReg.setInstituteEmail(email);
			eduLmsInstituteReg.setInstituteMobNo(mobilenumber);
			eduLmsInstituteReg.setInstituteName(insname);

			EduLmsStateMstr eduLmsStateMstr = new EduLmsStateMstr();
			eduLmsStateMstr.setStateId(Integer.parseInt(state));
			eduLmsInstituteReg.setStateId(eduLmsStateMstr);
			eduLmsInstituteReg.setStatus("1");
			EduLmsSystemMstr eduLmsSystemMstr = new EduLmsSystemMstr();
			eduLmsSystemMstr.setId(systemtypeint);
			eduLmsInstituteReg.setSystemId(eduLmsSystemMstr);
			EduLmsUniversityMstr eduLmsUniversityMstr = new EduLmsUniversityMstr();
			eduLmsUniversityMstr.setId(universityint);
			eduLmsInstituteReg.setUniversityId(eduLmsUniversityMstr);

			// eduLmsInstituteReg.setUploadImage(image);
//			https://stackoverflow.com/questions/2043393/convert-image-jpg-to-base64-in-excel-vba

//			Convert image (jpg) to base64 in Excel VBA
			if (!excelupload.equalsIgnoreCase("Y")) {
				try {
					if (image != null) {
						String path = request.getSession().getAttribute("InstituteImage").toString();
						byte[] bytes = image.getBytes();

						File dir = new File(path);
						if (!dir.exists()) {
							dir.mkdirs();
						}

						String curdate = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());
						String filename = image.getOriginalFilename();
						String photoname = dir.getAbsolutePath()
								+ File.separator + curdate.replace(":", "").toString().replace(".", ".").toString()
										.replace(" ", "").toString().replace("-", "").toString()
								+ "_" + mobilenumber + "_" + filename;

						File serverFile = new File(photoname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();

						eduLmsInstituteReg.setUploadImage(photoname);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				eduLmsInstituteReg.setUploadImage(imagename);
			}
			String username = request.getSession().getAttribute("username").toString();

			if (actiontype.equalsIgnoreCase("add")) {
				eduLmsInstituteReg.setCreatedBy(username);
				eduLmsInstituteReg.setCreatedDate(new Date());
			} else {
				eduLmsInstituteReg.setModifiedBy(username);
				eduLmsInstituteReg.setModifiedDate(new Date());
			}
			instituteMasterDao.SaveInstituteData(eduLmsInstituteReg, actiontype);

			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "1");
			if (actiontype.equalsIgnoreCase("add")) {
				jsonobjectout.put("message", "Institute added Successfully");
			} else {
				jsonobjectout.put("message", "Institute Updated Successfully");
			}
			return jsonobjectout.toJSONString();
		} else {
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			if (!checkresforinscode) {
				jsonobjectout.put("message", "Institute Code (" + inscode + ") already Exist");
			} else if (!checkresforinsname) {
				jsonobjectout.put("message", "Institute Name  (" + insname + ") already Exist");
			}

			return jsonobjectout.toJSONString();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadInstitutedData", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadInstitutedData(HttpServletRequest request, @RequestBody String data) {

		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
		List<EduLmsInstituteReg> TotaleduLmsInstituteRegs = null;
		try {

			object = (JSONObject) jsonParser.parse(data);

			String username = request.getSession().getAttribute("username").toString();
			String rolename = request.getSession().getAttribute("rolename").toString();
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			eduLmsInstituteRegs = instituteMasterDao.LoadInstituteData(username, data, rolename,userid);
			int counter = Integer.parseInt(object.get("startPage").toString()) + 1;
			int hidval = 1;

			if (!eduLmsInstituteRegs.isEmpty()) {
				for (EduLmsInstituteReg eduLmsInstituteReg : eduLmsInstituteRegs) {

					object = new JSONObject();
					object.put("srno", "<span class='avtar avatar-blue'>" + counter + "</span>");
					object.put("systemtype", eduLmsInstituteReg.getSystemId().getSystemName());
					object.put("university", eduLmsInstituteReg.getUniversityId().getUniversityName());
					object.put("inscode", eduLmsInstituteReg.getCode());
					object.put("insname", eduLmsInstituteReg.getInstituteName());
					object.put("instimage", eduLmsInstituteReg.getUploadImage());
					object.put("insabb", eduLmsInstituteReg.getCollegeAbbr());
					object.put("mobilenumber", eduLmsInstituteReg.getInstituteMobNo());
					object.put("email", eduLmsInstituteReg.getInstituteEmail());
					object.put("country", eduLmsInstituteReg.getCountryId().getName());
					object.put("state", eduLmsInstituteReg.getStateId().getStateName());
					object.put("district", eduLmsInstituteReg.getDistrictId().getDistrictName());
					object.put("address", eduLmsInstituteReg.getAddress());
					object.put("insuniquecode", eduLmsInstituteReg.getCollegeUniqueId());
					
					System.out.println("---eduLmsInstituteReg.getUploadImage---"+eduLmsInstituteReg.getUploadImage());
					
					if (eduLmsInstituteReg.getUploadImage() != null) {
						String imagestr = imageEncoderDecoder(eduLmsInstituteReg.getUploadImage());
						
						System.out.println("--imagestr--"+imagestr);
						
						if (imagestr.equalsIgnoreCase("")) {
							object.put("instimage", "Image is not uploaded");
						} else {
							object.put("instimage",
									"<img src='data:image/png;base64," + imagestr
											+ "' style='height:100px;width:100px;' onclick=\"ViewImage('" + imagestr
											+ "')\" />");
						}
					} else {

						object.put("instimage", "");
					}

//					object.put("instimage",
//							"<img class='d-block img5050 imageZomm' alt='No Image' id='insImg" +eduLmsInstituteReg.getId()
//									+ "' src='MedicalImagePath?i_id=" + eduLmsInstituteReg.getId() + "' onclick='imageView("
//									+ eduLmsInstituteReg.getId() + ");' />");
					String f = "";

					String hidden = "<input type='hidden' name='hid" + hidval + "' id='hid" + hidval + "' value='"
							+ AES.encrypt(eduLmsInstituteReg.getId() + "") + "' /> ";
					f = "<i class='action_icons action_update edtcls' title='Edit Data'></i>";
					f += "<i class='action_icons action_delete delcls' title='Delete Data'></i>";

					object.put("action",
							"<ul class=\"buttons-group\"><li><a href=\"#\" class=\"main-btn active-btn btn-hover btn-sm edtcls\"><i class=\"lni lni-pencil-alt\"></i></a></li><li><a href=\"#\" class=\"main-btn danger-btn btn-hover btn-sm delcls\"><i class=\"lni lni-trash-can\"></i></a></li></ul>"
									+ hidden);

					jSONArray.add(object);
					counter++;
					hidval++;

				}

				TotaleduLmsInstituteRegs = instituteMasterDao.LoadInstituteDataForCount(username, rolename,userid);

				object1.put("institutelist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("institutelist", jSONArray);
			}

			object1.put("status", "1");
			object1.put("message", "Success");
			if (TotaleduLmsInstituteRegs != null) {
				object1.put("TotalCount", TotaleduLmsInstituteRegs.size());
			} else {
				object1.put("TotalCount", 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("status", "0");
			object1.put("message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	public String imageEncoderDecoder(String imagepath) throws IOException {
		String imageString = "";
		// image path declaration
		// String imgPath = "src/main/resources/images/bean.png";

		// read image from file
		try {
			FileInputStream stream = new FileInputStream(imagepath);

			// get byte array from image stream
			int bufLength = 2048;
			byte[] buffer = new byte[2048];
			byte[] data;

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int readLength;
			while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
				out.write(buffer, 0, readLength);
			}

			data = out.toByteArray();
			imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

			out.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageString;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/GetInstituteDataForUpdate", method = RequestMethod.POST, produces = {
			"application/json" })
	public String GetInstituteDataForUpdate(HttpServletRequest request, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {

			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("insid") != null) {

				int insid = Integer.parseInt(AES.decrypt(jsonObject.get("insid").toString()));
				EduLmsInstituteReg eduLmsInstituteReg = instituteMasterDao.GetInstituteDataByID(insid);
				if (eduLmsInstituteReg != null) {
					jsonobjectout.put("systemtype", eduLmsInstituteReg.getSystemId().getId());
					jsonobjectout.put("university", eduLmsInstituteReg.getUniversityId().getId());
					jsonobjectout.put("inscode", eduLmsInstituteReg.getCode());
					jsonobjectout.put("insname", eduLmsInstituteReg.getInstituteName());
					jsonobjectout.put("insabb", eduLmsInstituteReg.getCollegeAbbr());
					jsonobjectout.put("mobilenumber", eduLmsInstituteReg.getInstituteMobNo());
					jsonobjectout.put("email", eduLmsInstituteReg.getInstituteEmail());
					jsonobjectout.put("country", eduLmsInstituteReg.getCountryId().getId());
					jsonobjectout.put("state", eduLmsInstituteReg.getStateId().getStateId());
					jsonobjectout.put("district", eduLmsInstituteReg.getDistrictId().getDistrictId());
					jsonobjectout.put("address", eduLmsInstituteReg.getAddress());
					jsonobjectout.put("insid", eduLmsInstituteReg.getId());
					jsonobjectout.put("imagedata", imageEncoderDecoder(eduLmsInstituteReg.getUploadImage()));
					jsonobjectout.put("status", "1");
					jsonobjectout.put("message", "Success");
					returnstring = jsonobjectout.toJSONString();
				} else {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "No Data Found");
					returnstring = jsonobjectout.toJSONString();
				}

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "No Data Found");
				returnstring = jsonobjectout.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Failure");
			returnstring = jsonobjectout.toJSONString();
		}

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/DeleteInstitutedData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String DeleteInstitutedData(@RequestBody String data, HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {
			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("insid") != null) {
				String username = request.getSession().getAttribute("userId").toString();
				int insid = Integer.parseInt(AES.decrypt(jsonObject.get("insid").toString()));
				EduLmsInstituteReg eduLmsInstituteReg = instituteMasterDao.GetInstituteDataByID(insid);
				if (eduLmsInstituteReg != null) {

//					String sessionuserid = request.getSession().getAttribute("userId_for_jnlp").toString();
					eduLmsInstituteReg.setModifiedBy(username);
					eduLmsInstituteReg.setModifiedDate(new Date());
					eduLmsInstituteReg.setStatus("0");
					instituteMasterDao.SaveInstituteData(eduLmsInstituteReg, "Edit");
					jsonobjectout.put("status", "1");
					jsonobjectout.put("message", "Institute Deleted Successfully");
					returnstring = jsonobjectout.toJSONString();

				} else {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "No Data Found");
					returnstring = jsonobjectout.toJSONString();
				}

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "No Data Found");
				returnstring = jsonobjectout.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Failure");
			returnstring = jsonobjectout.toJSONString();
		}

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/UploadExcelForInstitute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String UploadExcelForInstitute(HttpServletRequest request) throws Exception {

		HttpSession httpSession = request.getSession();

		JSONObject responsedata = new JSONObject();
		JSONArray jSONArray = new JSONArray();
		JSONParser jsonParser = new JSONParser();
		JSONObject responsedata1 = new JSONObject();
		boolean checkaadhaarnumber = false;
		String response = "";
		try {
			JSONArray sheetArray = new JSONArray();
			String checkDoc = "";
			ArrayList<String> columnNames = new ArrayList<String>();

			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile fileDoc = multipartRequest.getFile("uploadDocument");
				String documentAttachmentName = multipartRequest.getParameter("documentAttachmentName");
				String uploadDocName = multipartRequest.getParameter("uploadedDocumentName");
				String attachmentName = multipartRequest.getParameter("attachmentName");

				MultipartFile zipfileDoc = multipartRequest.getFile("zipuploadDocument");
				String zipdocumentAttachmentName = multipartRequest.getParameter("zipdocumentAttachmentName");
				String zipuploadDocName = multipartRequest.getParameter("zipuploadedDocumentName");
				String zipattachmentName = multipartRequest.getParameter("zipattachmentName");

				ZipInputStream zis = new ZipInputStream(zipfileDoc.getInputStream());
				Map map = new HashMap();
				try {
					ZipEntry entry;
					while ((entry = zis.getNextEntry()) != null) {

						if (!entry.isDirectory()) {

							String s = String.format("Entry: %s len %d added %TD", entry.getName(), entry.getSize(),
									new Date(entry.getTime()));
							System.out.println(s);
							String path = request.getSession().getAttribute("InstituteImage").toString();
							File dir = new File(path);
							if (!dir.exists()) {
								dir.mkdirs();
							}

							String curdate = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());
							String filename = "";
							if (entry.getName().contains("/")) {
								String f[] = entry.getName().split("/");
								filename = f[1].toString();
							} else {
								filename = entry.getName();
							}

							String photoname = dir.getAbsolutePath() + File.separator
									+ curdate.replace(":", "").toString().replace(".", ".").toString().replace(" ", "")
											.toString().replace("-", "").toString()
									+ "_" + filename;

//						File file = new File(photoname);
//						System.out.println(file.getPath());
							byte data[] = new byte[4096];
							BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(photoname));
							int n = -1;
							while ((n = zis.read(data)) != -1) {
								out.write(data, 0, n);
							}
							out.flush();
							out.close();
							map.put(filename.substring(0, filename.indexOf(".")), photoname);

						}
					}
				} finally {
					zis.close();
				}

				CouncellingValidationController councellingValidationController = new CouncellingValidationController();
				if (fileDoc.getSize() != 0) {
					checkDoc = councellingValidationController.checkFileFormats(fileDoc, uploadDocName, "xls");
					if (checkDoc.equalsIgnoreCase("Success")) {
						DataFormatter formatter = new DataFormatter();
						HSSFWorkbook wb = new HSSFWorkbook(fileDoc.getInputStream());
						// creating a Sheet object to retrieve the object
						HSSFSheet sheet = wb.getSheetAt(0);
						//StudentEnrollMentController studentEnrollMentController = new StudentEnrollMentController();
						// evaluating cell type
						FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
						for (Row row : sheet) // iteration over row using for each loop
						{

							if (!isRowEmpty(row)) {
								JSONObject jsonObject = new JSONObject();
								if (row.getRowNum() != 0) {
									for (int j = 0; j < columnNames.size(); j++) {
										row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
										if (row.getCell(j) != null) {
											if (row.getCell(j).getCellTypeEnum() == CellType.STRING) {
												jsonObject.put(columnNames.get(j), row.getCell(j).getStringCellValue());
											} else if (row.getCell(j).getCellTypeEnum() == CellType.NUMERIC) {
												jsonObject.put(columnNames.get(j),
														row.getCell(j).getNumericCellValue());
											} else if (row.getCell(j).getCellTypeEnum() == CellType.BOOLEAN) {
												jsonObject.put(columnNames.get(j),
														row.getCell(j).getBooleanCellValue());
											} else if (row.getCell(j).getCellTypeEnum() == CellType.BLANK) {
												jsonObject.put(columnNames.get(j), "");
											}
										} else {
											jsonObject.put(columnNames.get(j), "");
										}

									}

									sheetArray.add(jsonObject);
								} else {
									// store column names
									for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
										columnNames.add(row.getCell(k).getStringCellValue());
									}
								}
							}

						}
						System.out.println("sheetArray" + sheetArray.toJSONString());
						int count = 2;
						outer: for (int i = 0; i < sheetArray.size(); i++) {
//
							JSONObject object = (JSONObject) sheetArray.get(i);
							object.put("actiontype", "add");
							MultipartFile image = null;
							String imagepath = (String) map.get(object.get("inscode").toString());
							if (imagepath == null || imagepath.equalsIgnoreCase("")) {
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("message",
										"Please Provide Inscode according to Image Name on " + count + " Row");
								jsonObject.put("status", "0");
								response = jsonObject.toJSONString();
								break outer;
							}
							response = SaveInstitutedData(object, request, image, imagepath, "Y");
							JSONObject jsonObject = (JSONObject) jsonParser.parse(response);

							if (jsonObject.get("status").toString().equalsIgnoreCase("0")) {
								String message = jsonObject.get("message").toString();
								jsonObject.put("message", message + " on " + count + " Row");
								response = jsonObject.toJSONString();
								break outer;
							}
							count++;
//
//							Row row = sheet.getRow(count);
//							count++;
//							Cell cell = row.createCell(16);
//
//							cell.setCellValue(response);
						}
//
//						FileOutputStream output_file = new FileOutputStream(new File("poi-testt.xls"));
//
//						// write changes
//						wb.write(output_file);
//						output_file.close();

					} else {
						responsedata.put("status", "0");
						responsedata.put("message", checkDoc);
						response = responsedata.toJSONString();

					}
				} else {
					responsedata.put("status", "0");
					responsedata.put("message", "Please Choose File");
					response = responsedata.toJSONString();
				}
			}

		} catch (Exception e) {
			responsedata.put("status", "0");
			responsedata.put("message", "Error");
			response = responsedata.toJSONString();
			e.printStackTrace();

		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadSignUpData", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadSignUpData(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {

			// Add Server Side Validation TODO

			String username = request.getSession().getAttribute("userId").toString();
			EduLmsInstituteReg eduLmsInstituteReg = instituteMasterDao.GetInstituteDataByUsername(username);
			System.err.println("-----eduLmsInstituteReg -- "+eduLmsInstituteReg);
			if (eduLmsInstituteReg != null) {
				jsonobjectout.put("systemtype", eduLmsInstituteReg.getSystemId().getId());
				jsonobjectout.put("university", eduLmsInstituteReg.getUniversityId().getId());
				jsonobjectout.put("inscode", eduLmsInstituteReg.getCode());
				jsonobjectout.put("insname", eduLmsInstituteReg.getInstituteName());
				jsonobjectout.put("insabb", eduLmsInstituteReg.getCollegeAbbr());
				jsonobjectout.put("mobilenumber", eduLmsInstituteReg.getInstituteMobNo());
				jsonobjectout.put("email", eduLmsInstituteReg.getInstituteEmail());
				jsonobjectout.put("country", eduLmsInstituteReg.getCountryId().getId());
				jsonobjectout.put("state", eduLmsInstituteReg.getStateId().getStateId());
				jsonobjectout.put("district", eduLmsInstituteReg.getDistrictId().getDistrictId());
				jsonobjectout.put("address", eduLmsInstituteReg.getAddress());
				jsonobjectout.put("total_sanctioned_seat", eduLmsInstituteReg.getTotal_sanctioned_seat());

				jsonobjectout.put("insid", AES.encrypt(eduLmsInstituteReg.getId() + ""));
				jsonobjectout.put("imagedata", imageEncoderDecoder(eduLmsInstituteReg.getUploadImage()));
				jsonobjectout.put("status", "1");
				jsonobjectout.put("message", "Success");
				returnstring = jsonobjectout.toJSONString();
			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "No Data Found");
				returnstring = jsonobjectout.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Failure");
			returnstring = jsonobjectout.toJSONString();
		}

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadFeesCategoryType", method = RequestMethod.POST, produces = {
			"application/json" })
	public String LoadFeesCategoryType(HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();

		try {

//			String rolename = request.getSession().getAttribute("rolename").toString();
//			System.out.println("Role Name --> " + rolename);

//			String userId = request.getSession().getAttribute("userId").toString();
//			System.out.println("userId--> " + userId);
			List<CoFeescategorytype> CoFeescategorytype = instituteMasterDao.GetFees();

			if (!CoFeescategorytype.isEmpty()) {
				for (CoFeescategorytype co_feescategorytype : CoFeescategorytype) {
					object = new JSONObject();

					object.put("ftid", co_feescategorytype.getFtid());
					object.put("feestype", co_feescategorytype.getFeestype());
					jSONArray.add(object);
				}

				object1.put("feesList", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("feesList", jSONArray);
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
	@RequestMapping(value = "/admin/SaveInstituteOtherData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String SaveInstituteOtherData(HttpServletRequest request, @RequestPart String jsondata,
			@RequestPart(value = "image", required = false) MultipartFile image, String actiontype) throws ParseException {
		
		System.err.println("\n\n *****In---SaveInstituteOtherData*****"+"\n\n");
		
		JSONArray jsonp = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		String res = "";
		JSONArray jsonArray = new JSONArray();
		//try {

			// List<CoInstituteotherdetail> coInstituteotherdetail =
			// instituteMasterDao.GetInstituteOtherDetail();
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			System.out.println(jsondata);
			jsonArray = (JSONArray) jsonparser.parse(jsondata);

			if (!actiontype.equalsIgnoreCase("add")) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(0);
				int insiddata = Integer.parseInt(AES.decrypt(jsonObject.get("insid").toString()));
				List<CoInstituteotherdetail> coInstituteotherdetails = instituteMasterDao
						.GetInstituteOTHERDataByID(insiddata);
				for (CoInstituteotherdetail coInstituteotherdetail : coInstituteotherdetails) {
					coInstituteotherdetail.setStatus("0");
					coInstituteotherdetail.setModifyby(userid);
					coInstituteotherdetail.setModifydate(new Date());
					instituteMasterDao.DeleteInstituteOtherDetail(coInstituteotherdetail);
				}

			}

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				int totalseat = Integer.parseInt(jsonObject.get("totalseat").toString());
				boolean hostelfacility = (boolean) jsonObject.get("hostelfacility");
				//boolean libraryfacility = (boolean) jsonObject.get("libraryfacility");
				int categoryid = Integer.parseInt(jsonObject.get("categoryid").toString());
				int feesid = Integer.parseInt(jsonObject.get("feesid").toString());
				String fees_sub_cat = jsonObject.get("fees_sub_cat").toString().toUpperCase();
				double feesvalue = Double.parseDouble(jsonObject.get("feesvalue").toString());
				int insid = Integer.parseInt(AES.decrypt(jsonObject.get("insid").toString()));
				
				String fop = jsonObject.get("fop").toString();
				String no_of_part = jsonObject.get("no_of_part").toString();
//				boolean number_of_amountu1 = (boolean) jsonObject.get("number_of_amountu1");
//				String number_of_amountu1 = (jsonObject.get("number_of_amountu1").toString());
//				System.err.println("\n\n number_of_amountu12-----------"+number_of_amountu1+"\n\n");
				
				int systemtype = Integer.parseInt(jsonObject.get("systemtype").toString());
				
						
				String mobilenumber = "";
				String email = "";
				String country = "";
				String state = "";
				String district = "";
				String address = "";
				String insabb = "";
				String totalseat1 = "";
				String categoryid1 = "";
				String feesid1 = "";
				String fees_sub_cat1 = "";
				String feesvalue1 = "";
				String no_of_part1 = "";
				if (jsonObject.get("mobilenumber") == null || jsonObject.get("mobilenumber") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Enter Mobile Number");
					return jsonobjectout.toJSONString();
				} else {
					mobilenumber = jsonObject.get("mobilenumber").toString();
					res = councellingValidationController.MinimumLengthCheck(mobilenumber, 10);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Mobile Number " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MaximumLengthCheck(mobilenumber, 10, "number");
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Mobile Number " + res);
						return jsonobjectout.toJSONString();
					}

					res = councellingValidationController.AllowOnlyDigit(mobilenumber);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Mobile Number " + res);
						return jsonobjectout.toJSONString();
					}
				}

				if (jsonObject.get("insabb") == null || jsonObject.get("insabb") == "")

				{
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Enter Institute Abbreviation");
					return jsonobjectout.toJSONString();
				} else {
					insabb = jsonObject.get("insabb").toString().trim();
					res = councellingValidationController.MinimumLengthCheck(insabb, 2);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Institute Abbreviation " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MaximumLengthCheck(insabb, 8, "String");
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Institute Abbreviation " + res);
						return jsonobjectout.toJSONString();
					}

//					res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(insabb);
					res = councellingValidationController.OnlyAlphaNumericWithoutSpaceRegExp(insabb);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Institute Abbreviation " + res);
						return jsonobjectout.toJSONString();
					}
				}

				if (jsonObject.get("email") == null || jsonObject.get("email") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Enter Email");
					return jsonobjectout.toJSONString();
				} else {
					email = jsonObject.get("email").toString().trim();
					if (email.length() < 8) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Email must be of atleast 8 letters.");
						return jsonobjectout.toJSONString();
					} else if (email.length() > 128) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Email must be of Maximum 128 letters.");
						return jsonobjectout.toJSONString();
					} else {
						String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
								+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
						// Compile regular expression to get the pattern
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(email);

						if (!matcher.matches()) {
							jsonobjectout.put("status", "0");
							jsonobjectout.put("message", "Please Enter Valid Email");
							return jsonobjectout.toJSONString();
						}
					}
				}

				if (jsonObject.get("country") == null || jsonObject.get("country") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Select Country");
					return jsonobjectout.toJSONString();
				} else {
					country = jsonObject.get("country").toString();
					if (country.equalsIgnoreCase("-1")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Please Select Country");
						return jsonobjectout.toJSONString();
					}
				}
				if (jsonObject.get("state") == null || jsonObject.get("state") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Select State");
					return jsonobjectout.toJSONString();
				} else {
					state = jsonObject.get("state").toString();
					if (state.equalsIgnoreCase("-1")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Please Select State");
						return jsonobjectout.toJSONString();
					}
				}

				if (jsonObject.get("district") == null || jsonObject.get("district") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Select District");
					return jsonobjectout.toJSONString();
				} else {
					district = jsonObject.get("district").toString();
					if (district.equalsIgnoreCase("-1")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Please Select District");
						return jsonobjectout.toJSONString();
					}
				}

				if (jsonObject.get("address") == null || jsonObject.get("address") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Enter Address");
					return jsonobjectout.toJSONString();
				} else {
					address = jsonObject.get("address").toString().trim();
					res = councellingValidationController.MinimumLengthCheck(address, 6);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Address " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MaximumLengthCheck(address, 256, "String");
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Address " + res);
						return jsonobjectout.toJSONString();
					}

				}

				if (jsonObject.get("totalseat") == null || jsonObject.get("totalseat") == ""
						|| jsonObject.get("totalseat").toString().equalsIgnoreCase("0")) {
					jsonobjectout.put("status", "0");

					jsonobjectout.put("message", "Please Enter Total Seat");
					return jsonobjectout.toJSONString();
				} else {
					totalseat1 = jsonObject.get("totalseat").toString();
					res = councellingValidationController.MinimumLengthCheck(totalseat1, 1);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", " Total Seat " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MaximumLengthCheck(totalseat1, 5, "number");
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", " Total Seat " + res);
						return jsonobjectout.toJSONString();
					}

					res = councellingValidationController.AllowOnlyDigit(totalseat1);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", " Total Seat " + res);
						return jsonobjectout.toJSONString();
					}
				}

//				if (jsonObject.get("categoryid") == null || jsonObject.get("categoryid") == "") {
//					jsonobjectout.put("status", "0");
//					jsonobjectout.put("message", "Please Select Category");
//					return jsonobjectout.toJSONString();
//				} else {
//					categoryid1 = jsonObject.get("categoryid").toString();
//					if (categoryid1.equalsIgnoreCase("-1")) {
//						jsonobjectout.put("status", "0");
//						jsonobjectout.put("message", "Please Select Category");
//						return jsonobjectout.toJSONString();
//					}
//				}

				if (jsonObject.get("feesid") == null || jsonObject.get("feesid") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Select Fees Type");
					return jsonobjectout.toJSONString();
				} else {
					feesid1 = jsonObject.get("feesid").toString();
					if (feesid1.equalsIgnoreCase("-1")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Please Select Fees Type");
						return jsonobjectout.toJSONString();
					}
				}
				
				if (jsonObject.get("fees_sub_cat") == null || jsonObject.get("fees_sub_cat") == "") {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Enter Fees Sub-Category");
					return jsonobjectout.toJSONString();
				} else {
					fees_sub_cat1 = jsonObject.get("fees_sub_cat").toString().trim();
					res = councellingValidationController.OnlyAlphabeAndSpaceRegExp(fees_sub_cat1);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Fees Sub-Category " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MinimumLengthCheck(fees_sub_cat1, 1);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Fees Sub-Category " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MaximumLengthCheck(fees_sub_cat1, 150, "String");
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Fees Sub-Category " + res);
						return jsonobjectout.toJSONString();
					}

				}

				if (jsonObject.get("feesvalue") == null || jsonObject.get("feesvalue") == ""
						) {
					jsonobjectout.put("status", "0");

					jsonobjectout.put("message", "Please Enter Fees Value");
					return jsonobjectout.toJSONString();
				} else {
					feesvalue1 = jsonObject.get("feesvalue").toString().trim();
					res = councellingValidationController.MinimumLengthCheck(feesvalue1, 1);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", " Fees Value " + res);
						return jsonobjectout.toJSONString();
					}
					res = councellingValidationController.MaximumLengthCheck(feesvalue1, 8, "number");
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", " Fees Value " + res);
						return jsonobjectout.toJSONString();
					}

					res = councellingValidationController.AllowOnlyDigit(feesvalue1);
					if (!res.equalsIgnoreCase("true")) {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", " Fees Value " + res);
						return jsonobjectout.toJSONString();
					}
				}
				

//					no_of_part1 = jsonObject.get("no_of_part").toString().trim();
//					res = councellingValidationController.MinimumLengthCheck(no_of_part1, 1);
//					if (!res.equalsIgnoreCase("true")) {
//						jsonobjectout.put("status", "0");
//						jsonobjectout.put("message", " No of Part " + res);
//						return jsonobjectout.toJSONString();
//					}
//					res = councellingValidationController.MaximumLengthCheck(no_of_part1, 8, "number");
//					if (!res.equalsIgnoreCase("true")) {
//						jsonobjectout.put("status", "0");
//						jsonobjectout.put("message", " No of Part " + res);
//						return jsonobjectout.toJSONString();
//					}

//					res = councellingValidationController.AllowOnlyDigit(no_of_part1);
//					if (!res.equalsIgnoreCase("true")) {
//						jsonobjectout.put("status", "0");
//						jsonobjectout.put("message", " No of Part " + res);
//						return jsonobjectout.toJSONString();
//					}
			
				boolean checkinsdetailalraedyexist = instituteMasterDao.CheckInstituteOtherDetailAlreadyExist(insid,
						feesid, categoryid,fees_sub_cat);
				if (checkinsdetailalraedyexist) {
					EduLmsInstituteReg eduLmsInstituteReg = instituteMasterDao.GetInstituteDataByID(insid);

					eduLmsInstituteReg.setCollegeAbbr(insabb);
					eduLmsInstituteReg.setInstituteMobNo(mobilenumber);
					
					eduLmsInstituteReg.setInstituteEmail(email);
					EduLmsCountryMstr eduLmsCountryMstr = new EduLmsCountryMstr();
					eduLmsCountryMstr.setId(Integer.parseInt(country));
					eduLmsInstituteReg.setCountryId(eduLmsCountryMstr);

					EduLmsStateMstr eduLmsStateMstr = new EduLmsStateMstr();
					eduLmsStateMstr.setStateId(Integer.parseInt(state));
					eduLmsInstituteReg.setStateId(eduLmsStateMstr);
					EduLmsDistrictMstr eduLmsDistrictMstr = new EduLmsDistrictMstr();
					eduLmsDistrictMstr.setDistrictId(Integer.parseInt(district));
					eduLmsInstituteReg.setDistrictId(eduLmsDistrictMstr);
					eduLmsInstituteReg.setAddress(address);
				
					if (fop.equals("1")) {
						eduLmsInstituteReg.setNo_of_part(1);
				    } 
					if (fop.equals("2")) {
						eduLmsInstituteReg.setNo_of_part(Integer.parseInt(no_of_part));
				    } 

					
					
					
					try {
						if (image != null) {
							
							String path ="/srv" + File.separator + "Image";
							byte[] bytes = image.getBytes();

							File dir = new File(path);
							if (!dir.exists()) {
								dir.mkdirs();
							}

							String curdate = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());
							String filename = image.getOriginalFilename();
							String photoname = dir.getAbsolutePath() + File.separator
									+ curdate.replace(":", "").toString().replace(".", ".").toString().replace(" ", "")
											.toString().replace("-", "").toString()
									+ "_" + mobilenumber + "_" + filename;

							File serverFile = new File(photoname);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();

							eduLmsInstituteReg.setUploadImage(photoname);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					CoInstituteotherdetail coInstituteotherdetails = new CoInstituteotherdetail();
					TbLmsCategoryMstr tbLmsCategoryMstr = new TbLmsCategoryMstr();
					tbLmsCategoryMstr.setId(categoryid);
					coInstituteotherdetails.setCatid(tbLmsCategoryMstr);

					coInstituteotherdetails.setCreateby(userid);
					coInstituteotherdetails.setCreatedate(new Date());

					CoFeescategorytype coFeescategorytype = new CoFeescategorytype();
					coFeescategorytype.setFtid(feesid);
					coInstituteotherdetails.setFeesid(coFeescategorytype);

					coInstituteotherdetails.setHostelfacility(hostelfacility);

//					EduLmsInstituteReg eduLmsInstituteReg = new EduLmsInstituteReg();
					eduLmsInstituteReg.setId(insid);
					coInstituteotherdetails.setInid(eduLmsInstituteReg);

					//coInstituteotherdetails.setLibraryfacility();
					coInstituteotherdetails.setStatus("1");
					coInstituteotherdetails.setTotalseat(totalseat);
					coInstituteotherdetails.setFees_sub_cat(fees_sub_cat);
					coInstituteotherdetails.setFeesvalue(feesvalue);
					
					coInstituteotherdetails.setSystemId(systemtype);
					
					EduLmsDegreeCateMstr eduLmsDegreeCateMstr = instituteMasterDao.GetIdDegreeType("UG");
					if(eduLmsDegreeCateMstr != null) {
						EduLmsDegreeCateMstr eduLmsDegreeCateMstr2 = new EduLmsDegreeCateMstr();
						eduLmsDegreeCateMstr2.setId(eduLmsDegreeCateMstr.getId());
						coInstituteotherdetails.setDegreetype(eduLmsDegreeCateMstr2);
					}
					
					instituteMasterDao.saveInstituteOtherDetail(coInstituteotherdetails);

					instituteMasterDao.SaveInstituteData(eduLmsInstituteReg, "Edit");

				} else {
					jsonobjectout = new JSONObject();
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Fees Type already Exist against Category");
					res = jsonobjectout.toJSONString();
					return res;
				}

			}
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "1");
			jsonobjectout.put("message", "Details Added Successfully");
			res = jsonobjectout.toJSONString();
			return jsonobjectout.toJSONString();
			/*
			 * } catch (Exception e) { e.printStackTrace(); jsonobjectout = new
			 * JSONObject(); jsonobjectout.put("status", "0"); jsonobjectout.put("message",
			 * e); res = jsonobjectout.toJSONString(); }
			 */

		//return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadInstituteOtherData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String LoadInstituteOtherData(HttpServletRequest request, @RequestBody String data) {

		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
		List<EduLmsInstituteReg> TotaleduLmsInstituteRegs = null;
		try {

			object = (JSONObject) jsonParser.parse(data);

			String username = request.getSession().getAttribute("username").toString();
			int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			String rolename = request.getSession().getAttribute("rolename").toString();
			eduLmsInstituteRegs = instituteMasterDao.LoadInstituteData(username, data, rolename,userId);
			int counter = Integer.parseInt(object.get("startPage").toString()) + 1;
			int hidval = 1;

			if (!eduLmsInstituteRegs.isEmpty()) {
				for (EduLmsInstituteReg eduLmsInstituteReg : eduLmsInstituteRegs) {

					object = new JSONObject();
					object.put("srno", "<span class='avtar avatar-blue'>" + counter + "</span>");
					object.put("systemtype", eduLmsInstituteReg.getSystemId().getSystemName());
					object.put("university", eduLmsInstituteReg.getUniversityId().getUniversityName());
					object.put("inscode", eduLmsInstituteReg.getCode());
					object.put("insname", eduLmsInstituteReg.getInstituteName());
					object.put("instimage", eduLmsInstituteReg.getUploadImage());
					object.put("insabb", eduLmsInstituteReg.getCollegeAbbr());
					object.put("mobilenumber", eduLmsInstituteReg.getInstituteMobNo());
					object.put("email", eduLmsInstituteReg.getInstituteEmail());
					object.put("country", eduLmsInstituteReg.getCountryId().getName());
					object.put("state", eduLmsInstituteReg.getStateId().getStateName());
					object.put("district", eduLmsInstituteReg.getDistrictId().getDistrictName());
					object.put("address", eduLmsInstituteReg.getAddress());
					object.put("insuniquecode", eduLmsInstituteReg.getCollegeUniqueId());
					object.put("total_seat", eduLmsInstituteReg.getTotal_sanctioned_seat());
					
					if (eduLmsInstituteReg.getUploadImage() != null) {
						String imagestr = imageEncoderDecoder(eduLmsInstituteReg.getUploadImage());
						
						
						if (imagestr.equalsIgnoreCase("")) {
							object.put("instimage", "Image is not uploaded");
						} else {
							object.put("instimage",
									"<img src='data:image/png;base64," + imagestr
											+ "' class='imageZomm' onclick=\"ViewImage('" + imagestr
											+ "')\" />");
						}
					} else {

						object.put("instimage", "");
					}

//					object.put("instimage",
//							"<img class='d-block img5050 imageZomm' alt='No Image' id='insImg" +eduLmsInstituteReg.getId()
//									+ "' src='MedicalImagePath?i_id=" + eduLmsInstituteReg.getId() + "' onclick='imageView("
//									+ eduLmsInstituteReg.getId() + ");' />");
					String f = "";

					String hidden = "<input type='hidden' name='hid" + hidval + "' id='hid" + hidval + "' value='"
							+ AES.encrypt(eduLmsInstituteReg.getId() + "") + "' /> ";
					f = "<i class='action_icons action_update edtcls' title='Edit Data'></i>";
					f += "<i class='action_icons action_delete delcls' title='Delete Data'></i>";

					object.put("action",
							"<ul class=\"buttons-group\"><li><a href=\"#\" class=\"main-btn active-btn btn-hover btn-sm edtcls\"><i class=\"lni lni-pencil-alt\"></i></a></li></a></li></ul>"
									+ hidden);

					object.put("viewOtherDetails", "<ul class=\"buttons-group mainbtn\">\n"
							+ "				<li><input id=\"viewothderdetailbtn\" name=\"viewothderdetailbtn\"\n"
							+ "					class=\"main-btn dark-btn btn-hover btn-sm btnview viewothderdetailbtn\" type=\"button\" value=\"View Fees Structure\"\n"
							+ "					tabindex=\"13\" /></li>\n" + "\n" + "				"
							+ "			</ul>" + hidden);

					jSONArray.add(object);
					counter++;
					hidval++;

				}

				TotaleduLmsInstituteRegs = instituteMasterDao.LoadInstituteDataForCount(username, rolename,userId);

				object1.put("institutelist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("institutelist", jSONArray);
			}

			object1.put("status", "1");
			object1.put("message", "Success");
			if (TotaleduLmsInstituteRegs != null) {
				object1.put("TotalCount", TotaleduLmsInstituteRegs.size());
			} else {
				object1.put("TotalCount", 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("status", "0");
			object1.put("message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadOtherDetailsPOPUP", method = RequestMethod.POST, produces = {
			"application/json" })
	public String LoadOtherDetailsPOPUP(HttpServletRequest request, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {

			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("insid") != null) {

				int insid = Integer.parseInt(AES.decrypt(jsonObject.get("insid").toString()));
				List<CoInstituteotherdetail> instituteotherdetails = instituteMasterDao
						.GetInstituteOTHERDataByID(insid);
				if (instituteotherdetails != null) {
					if (instituteotherdetails.size() > 0) {
						for (CoInstituteotherdetail coInstituteotherdetail : instituteotherdetails) {
							jsonObject = new JSONObject();
							jsonObject.put("category", coInstituteotherdetail.getCatid().getCategory());
							jsonObject.put("feestype", coInstituteotherdetail.getFeesid().getFeestype());
							jsonObject.put("fees_sub_cat", coInstituteotherdetail.getFees_sub_cat());
							jsonObject.put("feesvalue", coInstituteotherdetail.getFeesvalue());

							if (coInstituteotherdetail.getLibraryfacility()) {
								jsonObject.put("library", "YES");
							} else {
								jsonObject.put("library", "NO");
							}
							if (coInstituteotherdetail.getHostelfacility()) {
								jsonObject.put("hostel", "YES");
							} else {
								jsonObject.put("hostel", "NO");
							}

							jsonObject.put("totalseat", coInstituteotherdetail.getTotalseat());
							jsonArray1.add(jsonObject);
						}
						jsonobjectout.put("status", "1");
						jsonobjectout.put("message", "success");
						jsonobjectout.put("data", jsonArray1);
						returnstring = jsonobjectout.toJSONString();
					} else {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "No Data Found");
						returnstring = jsonobjectout.toJSONString();
					}
				} else {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "No Data Found");
					returnstring = jsonobjectout.toJSONString();
				}

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "No Data Found");
				returnstring = jsonobjectout.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Failure");
			returnstring = jsonobjectout.toJSONString();
		}

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/GetInstituteOtherDataForUpdate", method = RequestMethod.POST, produces = {
			"application/json" })
	public String GetInstituteOtherDataForUpdate(HttpServletRequest request, @RequestBody String data) {
		System.err.println("errrrrrrrrrrrrrrrrrrrrror");
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {

			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("insid") != null) {

				int insid = Integer.parseInt(AES.decrypt(jsonObject.get("insid").toString()));
				List<CoInstituteotherdetail> instituteotherdetails = instituteMasterDao
						.GetInstituteOTHERDataByID(insid);
				
//				List<EduLmsInstituteReg> instituteregdetails =instituteMasterDao.GetInstituteregdataByID(insid);
				
				if (instituteotherdetails != null) {
					if (instituteotherdetails.size() > 0) {
						for (CoInstituteotherdetail coInstituteotherdetail : instituteotherdetails) {
							jsonObject = new JSONObject();
							jsonObject.put("category", coInstituteotherdetail.getCatid().getId());
							jsonObject.put("categoryname", coInstituteotherdetail.getCatid().getCategory());
							jsonObject.put("feestype", coInstituteotherdetail.getFeesid().getFtid());
							jsonObject.put("fees_sub_cat", coInstituteotherdetail.getFees_sub_cat());
							jsonObject.put("feesvalue", coInstituteotherdetail.getFeesvalue());

							jsonObject.put("library", coInstituteotherdetail.getLibraryfacility());

							jsonObject.put("hostel", coInstituteotherdetail.getHostelfacility());

							jsonObject.put("totalseat", coInstituteotherdetail.getTotalseat());
							
							EduLmsInstituteReg eduLmsInstituteReg = instituteMasterDao.GetInstituteDataByID(insid);
							jsonObject.put("no_of_part", eduLmsInstituteReg.getNo_of_part());
						
							jsonArray1.add(jsonObject);
						}
						
						jsonobjectout.put("status", "1");
						jsonobjectout.put("message", "success");
						jsonobjectout.put("data", jsonArray1);
						returnstring = jsonobjectout.toJSONString();
					} else {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "No Data Found");
						returnstring = jsonobjectout.toJSONString();
					}
				} else {
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "No Data Found");
					returnstring = jsonobjectout.toJSONString();
				}

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "No Data Found");
				returnstring = jsonobjectout.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Failure");
			returnstring = jsonobjectout.toJSONString();
		}

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/GetTotalSeatsOfInstitute", method = RequestMethod.POST, produces = {
			"application/json" })
	public String GetTotalSeatsOfInstitute(HttpServletRequest request, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {

			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("insid") != null) {

				int insid = Integer.parseInt(jsonObject.get("insid").toString());
				List<CoInstituteotherdetail> instituteotherdetails = instituteMasterDao
						.GetInstituteOTHERDataByID(insid);
				if (instituteotherdetails != null) {
					if (instituteotherdetails.size() > 0) {
						CoInstituteotherdetail coInstituteotherdetail = instituteotherdetails.get(0);

						jsonobjectout.put("status", "1");
						jsonobjectout.put("message", "Success");
						jsonobjectout.put("totalseat", coInstituteotherdetail.getTotalseat());
						returnstring = jsonobjectout.toJSONString();
					} else {
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Institute Detail Not Found");
						returnstring = jsonobjectout.toJSONString();
					}
				}

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Institute Detail Not Found");
				returnstring = jsonobjectout.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Failure");
			returnstring = jsonobjectout.toJSONString();
		}

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadInstituteOtherDataGeneral", method = RequestMethod.POST, produces = {
			"application/json" })
	public String LoadInstituteOtherDataGeneral(HttpServletRequest request, @RequestBody String data) {

		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		List<EduLmsInstituteReg> eduLmsInstituteRegs = null;
		List<EduLmsInstituteReg> TotaleduLmsInstituteRegs = null;
		try {
			System.out.println("Data" + data);
			object = (JSONObject) jsonParser.parse(data);

			eduLmsInstituteRegs = instituteMasterDao.LoadInstituteDataGeneral(data);
			int counter = Integer.parseInt(object.get("startPage").toString()) + 1;
			int hidval = 1;

			if (!eduLmsInstituteRegs.isEmpty()) {
				for (EduLmsInstituteReg eduLmsInstituteReg : eduLmsInstituteRegs) {

					object = new JSONObject();
					object.put("srno", "<span class='avtar avatar-blue'>" + counter + "</span>");
					object.put("systemtype", eduLmsInstituteReg.getSystemId().getSystemName());
					object.put("university", eduLmsInstituteReg.getUniversityId().getUniversityName());
					object.put("inscode", eduLmsInstituteReg.getCode());
					object.put("insname", eduLmsInstituteReg.getInstituteName());
					object.put("instimage", eduLmsInstituteReg.getUploadImage());
					object.put("insabb", eduLmsInstituteReg.getCollegeAbbr());
					object.put("mobilenumber", eduLmsInstituteReg.getInstituteMobNo());
					object.put("email", eduLmsInstituteReg.getInstituteEmail());
					object.put("country", eduLmsInstituteReg.getCountryId().getName());
					object.put("state", eduLmsInstituteReg.getStateId().getStateName());
					object.put("district", eduLmsInstituteReg.getDistrictId().getDistrictName());
					object.put("address", eduLmsInstituteReg.getAddress());
					object.put("insuniquecode", eduLmsInstituteReg.getCollegeUniqueId());
					int totalseats = instituteMasterDao.GetTotalSeatsOFInstitute(eduLmsInstituteReg.getId());
					
					object.put("TotalSeats", totalseats);
					if (eduLmsInstituteReg.getUploadImage() != null) {
						String imagestr = imageEncoderDecoder(eduLmsInstituteReg.getUploadImage());
						if (imagestr.equalsIgnoreCase("")) {
							object.put("instimage", "Image is not uploaded");
						} else {
							object.put("instimage",
									"<img src='data:image/png;base64," + imagestr
											+ "' class='imageZomm' onclick=\"ViewImage('" + imagestr
											+ "')\" />");
						}
					} else {

						object.put("instimage", "");
					}

//					object.put("instimage",
//							"<img class='d-block img5050 imageZomm' alt='No Image' id='insImg" +eduLmsInstituteReg.getId()
//									+ "' src='MedicalImagePath?i_id=" + eduLmsInstituteReg.getId() + "' onclick='imageView("
//									+ eduLmsInstituteReg.getId() + ");' />");
					String f = "";

					String hidden = "<input type='hidden' name='hid" + hidval + "' id='hid" + hidval + "' value='"
							+ AES.encrypt(eduLmsInstituteReg.getId() + "") + "' /> ";
					f = "<i class='action_icons action_update edtcls' title='Edit Data'></i>";
					f += "<i class='action_icons action_delete delcls' title='Delete Data'></i>";

					object.put("action",
							"<ul class=\"buttons-group\"><li><a href=\"#\" class=\"main-btn active-btn btn-hover btn-sm edtcls\"><i class=\"lni lni-pencil-alt\"></i></a></li></a></li></ul>"
									+ hidden);

					object.put("viewOtherDetails", "<ul class=\"buttons-group mainbtn\">\n"
							+ "				<li><input id=\"viewothderdetailbtn\" name=\"viewothderdetailbtn\"\n"
							+ "					class=\"main-btn info-btn btn-hover viewothderdetailbtn\" type=\"button\" value=\"View Other Details\"\n"
							+ "					tabindex=\"13\" /></li>\n" + "\n" + "				"
							+ "			</ul>" + hidden);

					jSONArray.add(object);
					counter++;
					hidval++;

				}

				TotaleduLmsInstituteRegs = instituteMasterDao.LoadInstituteDataForCountGeneral(data);

				object1.put("institutelist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("institutelist", jSONArray);
			}

			object1.put("status", "1");
			object1.put("message", "Success");
			if (TotaleduLmsInstituteRegs != null) {
				object1.put("TotalCount", TotaleduLmsInstituteRegs.size());
			} else {
				object1.put("TotalCount", 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			object1 = new JSONObject();
			object1.put("status", "0");
			object1.put("message", "Something went wrong");
		}

		return object1.toJSONString();
	}

	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

}
