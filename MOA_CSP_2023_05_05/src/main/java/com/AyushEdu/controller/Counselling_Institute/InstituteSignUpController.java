package com.AyushEdu.controller.Counselling_Institute;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsSystemMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityMstr;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Counselling_Institute.InstituteMasterDao_old;

@Controller
public class InstituteSignUpController {

	@Autowired
	InstituteMasterDao_old instituteMasterDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/InstituteDetail", method = RequestMethod.GET)
	public ModelAndView InstitutemasterUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = request.getSession().getAttribute("roleid").toString();
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("InstituteDetail", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		Mmap.put("msg", msg);
		return new ModelAndView("fillInstituteOtherDetail");
	}

	@ResponseBody
	@RequestMapping(value = "/admin/SaveInstituteSignUp", method = RequestMethod.POST, produces = {
			"application/json" })
	public String SaveInstituteSignUp(HttpServletRequest request, @RequestPart String jsondata,
			@RequestPart MultipartFile image) {
		JSONArray jsonp = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		String res = "";
		try {
			object = (JSONObject) jsonparser.parse(jsondata);

			// String rolename = request.getSession().getAttribute("rolename").toString();
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

			String message = councellingValidationController.checkFileFormats(image, image.getOriginalFilename(),
					"image");
			if (!message.equalsIgnoreCase("success")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", message + " For Institute Photo");
				return jsonobjectout.toJSONString();
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

				res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(insabb);
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
			boolean checkresforinsmob = false;
			boolean checkresforinsemail = false;

			EduLmsInstituteReg eduLmsInstituteReg = null;
			int systemtypeint = Integer.parseInt(systemtype);
			int universityint = Integer.parseInt(university);

			List Checkinscodeexist = instituteMasterDao.CheckInstituteCodeExist(inscode.trim().toLowerCase(),
					systemtypeint, universityint);

			List Checkinsnameexist = instituteMasterDao.CheckInstiuteNameExist(insname.trim().toLowerCase(),
					systemtypeint, universityint);

			List CheckInsMobileExist = instituteMasterDao.CheckInstiuteMobileExist(mobilenumber.trim().toLowerCase(),
					systemtypeint, universityint);

			List CheckInsEmailExist = instituteMasterDao.CheckInstiuteEmailExist(email.trim().toLowerCase(),
					systemtypeint, universityint);

			if (Checkinscodeexist.isEmpty()) {
				checkresforinscode = true;
			}

			if (Checkinsnameexist.isEmpty()) {
				checkresforinsname = true;
			}
			if (CheckInsMobileExist.isEmpty()) {
				checkresforinsmob = true;
			}
			if (CheckInsEmailExist.isEmpty()) {
				checkresforinsemail = true;
			}

			if (checkresforinscode && checkresforinsname && checkresforinsmob && checkresforinsmob) {

				// int userid =
				// Integer.parseInt(request.getSession().getAttribute("userId").toString());
				// int stateid =
				// Integer.parseInt(request.getSession().getAttribute("stateid").toString());

				eduLmsInstituteReg = new EduLmsInstituteReg();

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
//				https://stackoverflow.com/questions/2043393/convert-image-jpg-to-base64-in-excel-vba

//				Convert image (jpg) to base64 in Excel VBA

				try {
					if (image != null) {
						request.getSession().setAttribute("Image", "/srv" + File.separator + "Image");
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

				// String username = request.getSession().getAttribute("username").toString();

				// eduLmsInstituteReg.setCreatedBy("d");
				eduLmsInstituteReg.setCreatedDate(new Date());

				instituteMasterDao.SaveInstituteSignUpData(eduLmsInstituteReg, "add");

				jsonobjectout = new JSONObject();
				jsonobjectout.put("status", "1");

				jsonobjectout.put("message", "Your Sign Up Completed Successfully");

				return jsonobjectout.toJSONString();
			} else {
				jsonobjectout = new JSONObject();
				jsonobjectout.put("status", "0");
				if (!checkresforinscode) {
					jsonobjectout.put("message", "Institute Code " + inscode + " already Exist");
				} else if (!checkresforinsname) {
					jsonobjectout.put("message", "Institute Name  " + insname + " already Exist");
				} else if (!checkresforinsmob) {
					jsonobjectout.put("message", "Mobile Number  " + mobilenumber + " already Exist");
				} else if (!checkresforinsemail) {
					jsonobjectout.put("message", "Email Id  " + email + " already Exist");
				}

				return jsonobjectout.toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Something went wrong");
			res = jsonobjectout.toJSONString();
		}

		return res;
	}
}
