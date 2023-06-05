package com.AyushEdu.controller.Counselling_Institute;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Models.Counselling_Institute.CoCommissiontype;
import com.AyushEdu.Models.Counselling_Institute.CoRoundgeneration;
import com.AyushEdu.Models.Counselling_Institute.CoStudentenrollment;
import com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;
import com.AyushEdu.controller.Counselling_Institute.AES;
import com.AyushEdu.controller.Counselling_Institute.CouncellingValidationController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Counselling_Institute.RegistrationFeeMatserDao;
import com.AyushEdu.dao.Counselling_Institute.RoundGenerationDao;
import com.AyushEdu.dao.Counselling_Institute.StudentEnrollmentDao;
@Controller
public class StudentEnrollMentController {

	@Autowired
	StudentEnrollmentDao studentEnrollmentDao;

	@Autowired
	RoleBaseMenuDAO roleBaseMenuDAO;

	@Autowired
	RegistrationFeeMatserDao registrationFeeMatserDao;

	@Autowired
	RoundGenerationDao roundGenerationDao;

	@Autowired
	Base64Coder b64;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	@RequestMapping(value = "/admin/StudentEnrollmentUrl", method = RequestMethod.GET)
	public ModelAndView AllDashboard(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentEnrollmentUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		Mmap.put("msg", msg);
		return new ModelAndView("StudentEnrollment_Tiles");

	}

	@ResponseBody
	@RequestMapping(value = "/admin/LoadCommissionType", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadCommissionType(HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();

		try {
			System.out.println("dsg");

			List<CoCommissiontype> coCommissiontypes = studentEnrollmentDao.GetCommissionType();

			System.out.println("coCommissiontypes.size()" + coCommissiontypes.size());
			if (!coCommissiontypes.isEmpty()) {
				for (CoCommissiontype coCommissiontype : coCommissiontypes) {
					object = new JSONObject();

					object.put("id", coCommissiontype.getComid());
					object.put("name", coCommissiontype.getComname());
					jSONArray.add(object);
				}

				object1.put("commissionlist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("commissionlist", jSONArray);
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
	@RequestMapping(value = "/admin/LoadCategory", method = RequestMethod.POST, produces = { "application/json" })
	public String LoadCategory(HttpServletRequest request) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();

		try {
			System.out.println("dsg");

			List<TbLmsCategoryMstr> tbLmsCategoryMstrs = studentEnrollmentDao.GetCategoryType();

			System.out.println("tbLmsCategoryMstrs.size()" + tbLmsCategoryMstrs.size());
			if (!tbLmsCategoryMstrs.isEmpty()) {
				for (TbLmsCategoryMstr tbLmsCategoryMstr : tbLmsCategoryMstrs) {
					object = new JSONObject();

					object.put("id", tbLmsCategoryMstr.getId());
					object.put("name", tbLmsCategoryMstr.getCategory());
					jSONArray.add(object);
				}

				object1.put("categorylist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("categorylist", jSONArray);
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
	@RequestMapping(value = "/admin/SaveStudentEnrollmentData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String SaveStudentEnrollmentData(HttpServletRequest request, @RequestBody String data) {
		JSONArray jsonp = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		String res = "";
		try {
			object = (JSONObject) jsonparser.parse(data);

//			res = SaveStudentEnrolledData(object, request);
			res = SaveStudentSignUpData(object, request);
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
	@RequestMapping(value = "/admin/UploadExcelForStudentEnrollment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String UploadExcelForStudentEnrollment(HttpServletRequest request) throws Exception {

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
				CouncellingValidationController councellingValidationController = new CouncellingValidationController();
				if (fileDoc.getSize() != 0) {
					checkDoc = councellingValidationController.checkFileFormats(fileDoc, uploadDocName, "xls");
					if (checkDoc.equalsIgnoreCase("Success")) {
						DataFormatter formatter = new DataFormatter();
						HSSFWorkbook wb = new HSSFWorkbook(fileDoc.getInputStream());
						// creating a Sheet object to retrieve the object
						HSSFSheet sheet = wb.getSheetAt(0);
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
//						System.out.println("sheetArray" + sheetArray.toJSONString());
						int count = 2;
						outer: for (int i = 0; i < sheetArray.size(); i++) {
//
							JSONObject object = (JSONObject) sheetArray.get(i);
							object.put("actiontype", "add");
							response = SaveStudentEnrolledData(object, request);
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
	@RequestMapping(value = "/admin/LoadStudentEnrolledData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String LoadStudentEnrolledData(HttpServletRequest request, @RequestBody String data) {
		JSONArray jSONArray = new JSONArray();
		JSONObject object = new JSONObject();

		JSONObject object1 = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		List<CoStudentenrollment> coStudentenrollments1 = null;
		try {
			System.out.println("dsg");
			object = (JSONObject) jsonParser.parse(data);
			String yearval = request.getSession().getAttribute("yearval").toString();
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			List<CoStudentenrollment> coStudentenrollments = studentEnrollmentDao.LoadStudentEnrolledData(yearval,
					userid, data);
			int counter = Integer.parseInt(object.get("startPage").toString()) + 1;
			int hidval = 1;
			System.out.println("coStudentenrollments.size()" + coStudentenrollments.size());
			if (!coStudentenrollments.isEmpty()) {
				for (CoStudentenrollment coStudentenrollment : coStudentenrollments) {

					object = new JSONObject();
					object.put("srno", "<span class='avtar avatar-blue'>" + counter + "</span>");
//					String rolename = request.getSession().getAttribute("rolename").toString();
//					String getcomtype = "";
//					if (rolename.equalsIgnoreCase("State Council")) {
//						getcomtype = studentEnrollmentDao.getStateName(coStudentenrollment.getCommtype());
//					} else {
//						getcomtype = studentEnrollmentDao.GetCommissionTypeFromID(coStudentenrollment.getCommtype());
//					}
//					object.put("comptype", getcomtype);
					object.put("neetrollnumber", coStudentenrollment.getNeetrollnumber());
					object.put("appnumber", coStudentenrollment.getApplicationnumber());
					object.put("neetmarks", coStudentenrollment.getNeetmarks());
					object.put("neetrank", coStudentenrollment.getNeetrank());
					object.put("tenthpercentage", coStudentenrollment.getTenthpercentage());
					object.put("twelvthpercentage", coStudentenrollment.getTwelvthpercentage());
					object.put("year", coStudentenrollment.getYear());
					object.put("name", coStudentenrollment.getFirstname() + " " + coStudentenrollment.getMiddlename()
							+ " " + coStudentenrollment.getLastname());
					object.put("dob", coStudentenrollment.getDob());
					object.put("mobileid", coStudentenrollment.getMobilenumber());
					object.put("emailid", coStudentenrollment.getEmaildi());
					object.put("aadhaarnumber", coStudentenrollment.getAadhaarnumber());
//					object.put("category",studentEnrollmentDao.GetCategoryFromSEID(coStudentenrollment.getSeid()));
					object.put("category", coStudentenrollment.getCatid().getCategory());
					String f = "";
//					String hidden = "<input type='hidden' name='hid" + counter + "' id='hid" + counter + "' value='"
//							+ AES.encrypt(institutemst.getInid() + "") + "' /> ";

					String hidden = "<input type='hidden' name='hid" + hidval + "' id='hid" + hidval + "' value='"
							+ AES.encrypt(coStudentenrollment.getSeid() + "") + "' /> ";
					f = "<i class='action_icons action_update edtcls' title='Edit Data'></i>";
					f += "<i class='action_icons action_delete delcls' title='Delete Data'></i>";

					object.put("action",
							"<ul class=\"buttons-group\"><li><a href=\"#\" class=\"main-btn active-btn btn-hover btn-sm edtcls\"><i class=\"lni lni-pencil-alt\"></i></a></li><li><a href=\"#\" class=\"main-btn danger-btn btn-hover btn-sm delcls\"><i class=\"lni lni-trash-can\"></i></a></li></ul>"
									+ hidden);
					jSONArray.add(object);
					counter++;
					hidval++;

				}

				coStudentenrollments1 = studentEnrollmentDao.LoadStudentEnrolledDataForCount(yearval, userid);

				object1.put("studentlist", jSONArray);
			} else {
				jSONArray = new JSONArray();
				object1.put("studentlist", jSONArray);
			}

			object1.put("status", "1");
			object1.put("message", "Success");
			if (coStudentenrollments1 != null) {
				object1.put("TotalCount", coStudentenrollments1.size());
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

	public String SaveStudentEnrolledData(JSONObject object, HttpServletRequest request) {
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();
		// String rolename = request.getSession().getAttribute("rolename").toString();
		String comtype = "";
		String neetrollno = "";
		String appnumber = "";
		String neetmarks = "";
		String neetrank = "";
		String twelvethpercentage = "";
		String tenthpercentage = "";
		String year = "";
		String firstname = "";
		String middlename = "";
		String lastname = "";
		String dob = "";
		String mobilenumber = "";
		String emailid = "";
		String aadhaatnumber = "";
		String category = "";
		String res = "";
		String actiontype = "Edit";
		if (object.get("actiontype") != null) {
			actiontype = object.get("actiontype").toString();
		}

//		if (!rolename.equalsIgnoreCase("State Council")) {
//			if (object.get("comtype") == null || object.get("comtype") == "") {
//				jsonobjectout.put("status", "0");
//				jsonobjectout.put("message", "Please Choose Commission Type");
//				return jsonobjectout.toJSONString();
//			} else {
//				comtype = object.get("comtype").toString().trim();
//			}
//
//		}

		if (object.get("neetrollno") == null || object.get("neetrollno") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter NEET Roll Number");
			return jsonobjectout.toJSONString();
		} else {
			neetrollno = object.get("neetrollno").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(neetrollno, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(neetrollno, 10, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(neetrollno);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("appnumber") == null || object.get("appnumber") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Application Number");
			return jsonobjectout.toJSONString();
		} else {
			appnumber = object.get("appnumber").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(appnumber, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Application Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(appnumber, 10, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Application Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphaNumericWithoutSpaceRegExp(appnumber);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Application Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("neetmarks") == null || object.get("neetmarks") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter NEET Marks");
			return jsonobjectout.toJSONString();
		} else {
			neetmarks = object.get("neetmarks").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(neetmarks, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Marks " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(neetmarks, 3, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Marks " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(neetmarks);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Marks " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("neetrank") == null || object.get("neetrank") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter NEET Rank");
			return jsonobjectout.toJSONString();
		} else {
			neetrank = object.get("neetrank").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(neetrank, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Rank " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(neetrank, 6, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Rank " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(neetrank);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Rank " + res);
				return jsonobjectout.toJSONString();
			}
		}
		// TO DO Percentage Valdiation

		if (object.get("12thpercentage") == null || object.get("12thpercentage") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter 12th Percentage");
			return jsonobjectout.toJSONString();
		} else {
			twelvethpercentage = object.get("12thpercentage").toString().trim();
			if (Double.parseDouble(twelvethpercentage) > 0 && Double.parseDouble(twelvethpercentage) <= 100) {

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "12th Percentage should be between 0 to 100");
				return jsonobjectout.toJSONString();
			}
		}
		if (object.get("10thpercentage") == null || object.get("10thpercentage") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter 10th Percentage");
			return jsonobjectout.toJSONString();
		} else {
			tenthpercentage = object.get("10thpercentage").toString().trim();
			if (Double.parseDouble(tenthpercentage) > 0 && Double.parseDouble(tenthpercentage) <= 100) {

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "10th Percentage should be between 0 to 100");
				return jsonobjectout.toJSONString();
			}

		}
		System.out.println(object.get("year").toString());

		if (object.get("year") == null || object.get("year") == "") {
			System.out.println("sesionyear");
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Year");
			return jsonobjectout.toJSONString();
		} else {
			year = object.get("year").toString().trim();
			String sesionyear = request.getSession().getAttribute("yearval").toString();
			System.out.println("sesionyear" + sesionyear);
			if (!year.equalsIgnoreCase(sesionyear)) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Enter Year");
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("firstname") == null || object.get("firstname") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter First Name");
			return jsonobjectout.toJSONString();
		} else {
			firstname = object.get("firstname").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(firstname, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "First Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(firstname, 20, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "First Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(firstname);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "First Name " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("middlename") == null || object.get("middlename") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Middle Name");
			return jsonobjectout.toJSONString();
		} else {
			middlename = object.get("middlename").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(middlename, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Middle Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(middlename, 20, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Middle Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(middlename);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Middle Name " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("lastname") == null || object.get("lastname") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Last Name");
			return jsonobjectout.toJSONString();
		} else {
			lastname = object.get("lastname").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(lastname, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Last Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(lastname, 20, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Last Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(lastname);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Last Name " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("dob") == null || object.get("dob") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select Date of Birth");
			return jsonobjectout.toJSONString();
		} else {
			dob = object.get("dob").toString().trim();
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

		String email = "";
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

		if (object.get("aadhaatnumber") == null || object.get("aadhaatnumber") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Aadhaar Number");
			return jsonobjectout.toJSONString();
		} else {
			aadhaatnumber = object.get("aadhaatnumber").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(aadhaatnumber, 12);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(aadhaatnumber, 12, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(aadhaatnumber);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("category") == null || object.get("category") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Choose Category");
			return jsonobjectout.toJSONString();
		} else {
			category = object.get("category").toString().trim();
		}
		boolean checkres = false;
		boolean checkresforappnumber = false;

		CoStudentenrollment coStudentenrollment = null;
		List CheckNeetrollnoexist = studentEnrollmentDao.CheckNEETRollNumberexist(neetrollno.trim().toLowerCase());

		List Checkappnumberexist = studentEnrollmentDao.CheckApplciationNumberexist(appnumber.trim().toLowerCase());
		if (actiontype.equalsIgnoreCase("add")) {
			if (CheckNeetrollnoexist.isEmpty()) {
				checkres = true;
			}

			if (Checkappnumberexist.isEmpty()) {
				checkresforappnumber = true;
			}
		} else {

			if (CheckNeetrollnoexist.isEmpty()) {
				checkres = true;
			} else {
				if (CheckNeetrollnoexist.size() > 1) {
					checkres = false;
				} else {
					coStudentenrollment = studentEnrollmentDao
							.GetStudentEnrolledDataByID(Long.parseLong(object.get("seid").toString()));
					if (coStudentenrollment.getNeetrollnumber().trim().equalsIgnoreCase(neetrollno)) {
						checkres = true;
					} else {
						checkres = false;
					}
				}
			}

			if (Checkappnumberexist.isEmpty()) {
				checkresforappnumber = true;
			} else {
				if (Checkappnumberexist.size() > 1) {
					checkresforappnumber = false;
				} else {
					coStudentenrollment = studentEnrollmentDao
							.GetStudentEnrolledDataByID(Long.parseLong(object.get("seid").toString()));
					if (coStudentenrollment.getApplicationnumber().trim().equalsIgnoreCase(appnumber)) {
						checkresforappnumber = true;
					} else {
						checkresforappnumber = false;
					}
				}
			}

		}

		if (checkres && checkresforappnumber) {
			if (!actiontype.equalsIgnoreCase("add")) {
				coStudentenrollment = studentEnrollmentDao
						.GetStudentEnrolledDataByID(Long.parseLong(object.get("seid").toString()));
			}

			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			int stateid = Integer.parseInt(request.getSession().getAttribute("stateid").toString());
			if (actiontype.equalsIgnoreCase("add")) {
				coStudentenrollment = new CoStudentenrollment();
			}
			coStudentenrollment.setAadhaarnumber(aadhaatnumber);
			coStudentenrollment.setApplicationnumber(appnumber);
			TbLmsCategoryMstr tbLmsCategoryMstr = new TbLmsCategoryMstr();
			tbLmsCategoryMstr.setId(Integer.parseInt(category));
			coStudentenrollment.setCatid(tbLmsCategoryMstr);

//			if (!comtype.equalsIgnoreCase("")) {
//				coStudentenrollment.setCommtype(Integer.parseInt(comtype));
//			} else {
//				coStudentenrollment.setCommtype(stateid);
//			}

			coStudentenrollment.setDob(dob);
			coStudentenrollment.setEmaildi(email);
			coStudentenrollment.setFirstname(firstname);
			coStudentenrollment.setLastname(lastname);
			coStudentenrollment.setMiddlename(middlename);
			coStudentenrollment.setMobilenumber(mobilenumber);
			coStudentenrollment.setNeetmarks(Integer.parseInt(neetmarks));
			coStudentenrollment.setNeetrank(Integer.parseInt(neetrank));
			coStudentenrollment.setNeetrollnumber(neetrollno);
			coStudentenrollment.setTenthpercentage(Double.parseDouble(tenthpercentage));
			coStudentenrollment.setTwelvthpercentage(Double.parseDouble(twelvethpercentage));
			coStudentenrollment.setYear(year);
			coStudentenrollment.setStatus("1");
			if (actiontype.equalsIgnoreCase("add")) {
				coStudentenrollment.setRound(0);
				coStudentenrollment.setRoundstatus("0");
				coStudentenrollment.setCreateby(userid);
				coStudentenrollment.setCreatedate(new Date());
			} else {
				coStudentenrollment.setModifyby(userid);
				coStudentenrollment.setModifydate(new Date());
			}
			studentEnrollmentDao.SaveStudentEnrommentData(coStudentenrollment, actiontype);

			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "1");
			if (actiontype.equalsIgnoreCase("add")) {
				jsonobjectout.put("message", "Student Enrolled Successfully");
			} else {
				jsonobjectout.put("message", "Student Updated Successfully");
			}
			return jsonobjectout.toJSONString();
		} else {
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			if (!checkres) {
				jsonobjectout.put("message", "NEET Roll Number (" + neetrollno + ") already Exist");
			} else if (!checkresforappnumber) {
				jsonobjectout.put("message", "Applcaition Number (" + appnumber + ") already Exist");
			}

			return jsonobjectout.toJSONString();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/admin/GetStudentDataForUpdate", method = RequestMethod.POST, produces = {
			"application/json" })
	public String GetStudentDataForUpdate(HttpServletRequest request, @RequestBody String data) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {

			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("seid") != null) {

				long seid = Long.parseLong(AES.decrypt(jsonObject.get("seid").toString()));
				CoStudentenrollment coStudentenrollment = studentEnrollmentDao.GetStudentEnrolledDataByID(seid);
				if (coStudentenrollment != null) {
//					jsonobjectout.put("comptype", coStudentenrollment.getCommtype());
					jsonobjectout.put("neetrollnumber", coStudentenrollment.getNeetrollnumber());
					jsonobjectout.put("appnumber", coStudentenrollment.getApplicationnumber());
					jsonobjectout.put("neetmarks", coStudentenrollment.getNeetmarks());
					jsonobjectout.put("neetrank", coStudentenrollment.getNeetrank());
					jsonobjectout.put("tenthpercentage", coStudentenrollment.getTenthpercentage());
					jsonobjectout.put("twelvthpercentage", coStudentenrollment.getTwelvthpercentage());
					jsonobjectout.put("year", coStudentenrollment.getYear());
					jsonobjectout.put("firstname", coStudentenrollment.getFirstname());
					jsonobjectout.put("middlename", coStudentenrollment.getMiddlename());
					jsonobjectout.put("lastname", coStudentenrollment.getLastname());
					jsonobjectout.put("dob", coStudentenrollment.getDob());
					jsonobjectout.put("mobileid", coStudentenrollment.getMobilenumber());
					jsonobjectout.put("emailid", coStudentenrollment.getEmaildi());
					jsonobjectout.put("aadhaarnumber", coStudentenrollment.getAadhaarnumber());
					jsonobjectout.put("category", coStudentenrollment.getCatid().getId());
					jsonobjectout.put("seid", coStudentenrollment.getSeid());
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
		System.out.println("Output-->" + returnstring);

		return returnstring;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/DeleteStudentEnrolledData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String DeleteStudentEnrolledData(@RequestBody String data, HttpServletRequest request) {

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();
		JSONParser jsonp = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String returnstring = "";
		try {
			// Add Server Side Validation TODO
			jsonObject = (JSONObject) jsonp.parse(data);
			if (jsonObject.get("seid") != null) {
				int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
				long seid = Long.parseLong(AES.decrypt(jsonObject.get("seid").toString()));
				CoStudentenrollment coStudentenrollment = studentEnrollmentDao.GetStudentEnrolledDataByID(seid);
				if (coStudentenrollment != null) {

					String sessionuserid = request.getSession().getAttribute("userId_for_jnlp").toString();
					coStudentenrollment.setModifyby(userid);
					coStudentenrollment.setModifydate(new Date());
					coStudentenrollment.setStatus("0");
					studentEnrollmentDao.SaveStudentEnrommentData(coStudentenrollment, "Edit");
					jsonobjectout.put("status", "1");
					jsonobjectout.put("message", "Student Deleted Successfully");
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
		System.out.println("Output-->" + returnstring);

		return returnstring;
	}

	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	public String SaveStudentSignUpData(JSONObject object, HttpServletRequest request1) {
		JSONObject jsonobjectout = new JSONObject();
		CouncellingValidationController councellingValidationController = new CouncellingValidationController();

		String comtype = "";
		String neetrollno = "";
		String appnumber = "";
		String neetmarks = "";
		String neetrank = "";
		String twelvethpercentage = "";
		String tenthpercentage = "";
		String yearval = "";
		String firstname = "";
		String middlename = "";
		String lastname = "";
		String dob = "";
		String mobilenumber = "";
		String emailid = "";
		String aadhaatnumber = "";
		String category = "";
		String res = "";
		String country = "";
		String state = "";
		String district = "";
		String address = "";

		if (object.get("firstname") == null || object.get("firstname") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter First Name");
			return jsonobjectout.toJSONString();
		} else {
			firstname = object.get("firstname").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(firstname, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "First Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(firstname, 20, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "First Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(firstname);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "First Name " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("middlename") == null || object.get("middlename") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Middle Name");
			return jsonobjectout.toJSONString();
		} else {
			middlename = object.get("middlename").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(middlename, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Middle Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(middlename, 20, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Middle Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(middlename);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Middle Name " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("lastname") == null || object.get("lastname") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Last Name");
			return jsonobjectout.toJSONString();
		} else {
			lastname = object.get("lastname").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(lastname, 2);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Last Name " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(lastname, 20, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Last Name " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphabetWithoutSpaceRegExp(lastname);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Last Name " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("dob") == null || object.get("dob") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Select Date of Birth");
			return jsonobjectout.toJSONString();
		} else {
			dob = object.get("dob").toString().trim();
		}
		if (object.get("category") == null || object.get("category") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Choose Category");
			return jsonobjectout.toJSONString();
		} else {
			category = object.get("category").toString().trim();
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

		String email = "";
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

		if (object.get("aadhaatnumber") == null || object.get("aadhaatnumber") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Aadhaar Number");
			return jsonobjectout.toJSONString();
		} else {
			aadhaatnumber = object.get("aadhaatnumber").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(aadhaatnumber, 12);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(aadhaatnumber, 12, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(aadhaatnumber);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number " + res);
				return jsonobjectout.toJSONString();
			}
		}
		if (object.get("neetrollno") == null || object.get("neetrollno") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter NEET Roll Number");
			return jsonobjectout.toJSONString();
		} else {
			neetrollno = object.get("neetrollno").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(neetrollno, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(neetrollno, 10, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(neetrollno);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Roll Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("appnumber") == null || object.get("appnumber") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Application Number");
			return jsonobjectout.toJSONString();
		} else {
			appnumber = object.get("appnumber").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(appnumber, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Application Number " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(appnumber, 10, "String");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Application Number " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.OnlyAlphaNumericWithoutSpaceRegExp(appnumber);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Application Number " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("neetmarks") == null || object.get("neetmarks") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter NEET Marks");
			return jsonobjectout.toJSONString();
		} else {
			neetmarks = object.get("neetmarks").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(neetmarks, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Marks " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(neetmarks, 3, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Marks " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(neetmarks);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Marks " + res);
				return jsonobjectout.toJSONString();
			}
		}

		if (object.get("neetrank") == null || object.get("neetrank") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter NEET Rank");
			return jsonobjectout.toJSONString();
		} else {
			neetrank = object.get("neetrank").toString().trim();
			res = councellingValidationController.MinimumLengthCheck(neetrank, 1);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Rank " + res);
				return jsonobjectout.toJSONString();
			}
			res = councellingValidationController.MaximumLengthCheck(neetrank, 6, "number");
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Rank " + res);
				return jsonobjectout.toJSONString();
			}

			res = councellingValidationController.AllowOnlyDigit(neetrank);
			if (!res.equalsIgnoreCase("true")) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "NEET Rank " + res);
				return jsonobjectout.toJSONString();
			}
		}
		String sesionyear = "";
		// TO DO Percentage Valdiation
		if (object.get("year") == null || object.get("year") == "") {
			System.out.println("sesionyear");
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter Year");
			return jsonobjectout.toJSONString();
		} else {
			yearval = object.get("year").toString().trim();

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String date = sdf.format(d);
			System.out.println("date-->" + date);
			int year = d.getYear();

			int currentYear = year + 1900;
			int nextYear = year + 1900 + 1;
			System.out.println(currentYear);
			System.out.println(nextYear);
			sesionyear = currentYear + "-" + nextYear;

			System.out.println("sesionyear" + sesionyear);
			if (!yearval.equalsIgnoreCase(sesionyear)) {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Enter Year");
				return jsonobjectout.toJSONString();
			}
		}
		if (object.get("12thpercentage") == null || object.get("12thpercentage") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter 12th Percentage");
			return jsonobjectout.toJSONString();
		} else {
			twelvethpercentage = object.get("12thpercentage").toString().trim();
			if (Double.parseDouble(twelvethpercentage) > 0 && Double.parseDouble(twelvethpercentage) <= 100) {

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "12th Percentage should be between 0 to 100");
				return jsonobjectout.toJSONString();
			}
		}
		if (object.get("10thpercentage") == null || object.get("10thpercentage") == "") {
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Please Enter 10th Percentage");
			return jsonobjectout.toJSONString();
		} else {
			tenthpercentage = object.get("10thpercentage").toString().trim();
			if (Double.parseDouble(tenthpercentage) > 0 && Double.parseDouble(tenthpercentage) <= 100) {

			} else {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "10th Percentage should be between 0 to 100");
				return jsonobjectout.toJSONString();
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

		System.out.println(object.get("year").toString());
//		String processfor = "";
//		int commtypedata = 0;
//		CoRoundgeneration getlastdateofallround = roundGenerationDao.GetlastDateAfterAllRoundForCommission(sesionyear,
//				1);
//
//		if (getlastdateofallround != null) {
//			Date currentdate = new Date();
//			Date getlastdateofcom1 = getlastdateofallround.getResultenddate();
//
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(getlastdateofcom1);
//			// use add() method to add the days to the given date
//			cal.add(Calendar.DAY_OF_MONTH, 3);
//			getlastdateofcom1 = cal.getTime();
//
//			int result = currentdate.compareTo(getlastdateofcom1);
//			if (result > 0) {
//				processfor = "State Council";
//				commtypedata = Integer.parseInt(state);
//
//			} else {
//				processfor = "Commission";
//				commtypedata = 1;
//			}
//		} else {
//			jsonobjectout = new JSONObject();
//			jsonobjectout.put("status", "0");
//			jsonobjectout.put("message", "Registration is not allowed for selected State right now");
//			// jsonobjectout.put("Checkpayment", Checkpayment);
//			return jsonobjectout.toJSONString();
//		}

		int createby = Integer.parseInt(request1.getSession().getAttribute("createby").toString());
		boolean checkres = false;
		boolean checkresforappnumber = false;
		boolean checkresforaadhaarnumber = false;

		CoStudentenrollment coStudentenrollment = new CoStudentenrollment();
		List CheckNeetrollnoexist = studentEnrollmentDao.CheckNEETRollNumberexist(neetrollno.trim().toLowerCase());

		List Checkappnumberexist = studentEnrollmentDao.CheckApplciationNumberexist(appnumber.trim().toLowerCase());

		List Cehckaadhaarnumberexist = studentEnrollmentDao.CheckAadaarNumberexist(appnumber.trim().toLowerCase());

//		List CheckNeetrollnoexist = studentEnrollmentDao
//				.CheckNEETRollNumberexistForCouncilORState(neetrollno.trim().toLowerCase(), createby);
//
//		List Checkappnumberexist = studentEnrollmentDao
//				.CheckApplciationNumberexistForCouncilORState(appnumber.trim().toLowerCase(), createby);
//
//		List Cehckaadhaarnumberexist = studentEnrollmentDao
//				.CheckAadaarNumberexistForCouncilORState(appnumber.trim().toLowerCase(), createby);

		if (CheckNeetrollnoexist.isEmpty()) {
			checkres = true;
		}

		if (Checkappnumberexist.isEmpty()) {
			checkresforappnumber = true;
		}

		if (Cehckaadhaarnumberexist.isEmpty()) {
			checkresforaadhaarnumber = true;
		}

		if (checkres && checkresforappnumber && checkresforaadhaarnumber) {

			boolean checkuplaodedinfo = studentEnrollmentDao.CheckUplaodedInformation(aadhaatnumber, yearval);
			System.out.println(checkuplaodedinfo);
			if (checkuplaodedinfo) {
				boolean checkneetdetails = studentEnrollmentDao.ValdiateNEEtDetails(aadhaatnumber, appnumber,
						neetrollno, neetmarks, neetrank, yearval, dob, Integer.parseInt(category));

				if (checkneetdetails) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:yy");
					String datefrm = sdf.format(new Date());

					List<CoRoundgeneration> coRoundgeneration = roundGenerationDao.CheckRegistrationAllow(datefrm,
							yearval);
					if (coRoundgeneration != null) {

						if (coRoundgeneration.size() > 0) {
							boolean CheckAllowreg = false;
							breakloop: for (CoRoundgeneration coRoundgeneration2 : coRoundgeneration) {
								int commtype = coRoundgeneration2.getCommtype();
								if (commtype == 1) {
									CheckAllowreg = true;
								} else {
									if (commtype == Integer.parseInt(state)) {
										CheckAllowreg = true;
										break breakloop;
									} else {
										CheckAllowreg = false;
									}
								}
							}

							if (CheckAllowreg) {
								coStudentenrollment = new CoStudentenrollment();

								coStudentenrollment.setAadhaarnumber(aadhaatnumber);
								coStudentenrollment.setApplicationnumber(appnumber);
								TbLmsCategoryMstr tbLmsCategoryMstr = new TbLmsCategoryMstr();
								tbLmsCategoryMstr.setId(Integer.parseInt(category));
								coStudentenrollment.setCatid(tbLmsCategoryMstr);

								coStudentenrollment.setDob(dob);
								coStudentenrollment.setEmaildi(email);
								coStudentenrollment.setFirstname(firstname);
								coStudentenrollment.setLastname(lastname);
								coStudentenrollment.setMiddlename(middlename);
								coStudentenrollment.setMobilenumber(mobilenumber);
								coStudentenrollment.setNeetmarks(Integer.parseInt(neetmarks));
								coStudentenrollment.setNeetrank(Integer.parseInt(neetrank));
								coStudentenrollment.setNeetrollnumber(neetrollno);
								coStudentenrollment.setTenthpercentage(Double.parseDouble(tenthpercentage));
								coStudentenrollment.setTwelvthpercentage(Double.parseDouble(twelvethpercentage));
								coStudentenrollment.setYear(yearval);

								coStudentenrollment.setRound(0);
								coStudentenrollment.setRoundstatus("0");
								coStudentenrollment.setCreateby(createby);

								EduLmsCountryMstr eduLmsCountryMstr = new EduLmsCountryMstr();
								eduLmsCountryMstr.setId(Integer.parseInt(country));
								coStudentenrollment.setCountryid(eduLmsCountryMstr);

								EduLmsDistrictMstr eduLmsDistrictMstr = new EduLmsDistrictMstr();
								eduLmsDistrictMstr.setDistrictId(Integer.parseInt(district));
								coStudentenrollment.setDistrictid(eduLmsDistrictMstr);

								EduLmsStateMstr eduLmsStateMstr = new EduLmsStateMstr();
								eduLmsStateMstr.setStateId(Integer.parseInt(state));
								coStudentenrollment.setStateid(eduLmsStateMstr);

								coStudentenrollment.setAddress(address);

								coStudentenrollment.setCreatedate(new Date());
//								coStudentenrollment.setCommtype(commtypedata);
//								coStudentenrollment.setProcessfor(processfor);
								boolean Checkpayment = registrationFeeMatserDao
										.CheckPaymentForCategory(Integer.parseInt(category), yearval, createby);
								if (Checkpayment) {
									coStudentenrollment.setPaymentstatus("N");
									coStudentenrollment.setStatus("1");
								} else {
									coStudentenrollment.setPaymentstatus("Y");
									coStudentenrollment.setStatus("1");
								}

								studentEnrollmentDao.SaveStudentEnrommentData(coStudentenrollment, "add");

								jsonobjectout = new JSONObject();
								jsonobjectout.put("status", "1");
								jsonobjectout.put("Checkpayment", Checkpayment);

								String encuserid = AES.encrypt(coStudentenrollment.getSeid() + "_userid");
								jsonobjectout.put("userid", b64.encode(encuserid.getBytes()));

								jsonobjectout.put("message", "Your Registration is Completed Successfully.");

								return jsonobjectout.toJSONString();
							} else {
								jsonobjectout = new JSONObject();
								jsonobjectout.put("status", "0");
								jsonobjectout.put("message",
										"Registration is not allowed for selected State right now");
								// jsonobjectout.put("Checkpayment", Checkpayment);
								return jsonobjectout.toJSONString();
							}
						} else {
							jsonobjectout = new JSONObject();
							jsonobjectout.put("status", "0");
							jsonobjectout.put("message", "Registration Process is not yet started");
							// jsonobjectout.put("Checkpayment", Checkpayment);
							return jsonobjectout.toJSONString();
						}
					} else {
						jsonobjectout = new JSONObject();
						jsonobjectout.put("status", "0");
						jsonobjectout.put("message", "Registration Process is not yet started");
						// jsonobjectout.put("Checkpayment", Checkpayment);
						return jsonobjectout.toJSONString();
					}
				} else {
//					boolean Checkpayment = registrationFeeMatserDao.CheckPaymentForCategory(Integer.parseInt(category),
//							yearval, coStudentenrollment.getCreateby());
					jsonobjectout = new JSONObject();
					jsonobjectout.put("status", "0");
					jsonobjectout.put("message", "Please Correct Your NEET Information or Category or Date OF Birth");
					// jsonobjectout.put("Checkpayment", Checkpayment);
					return jsonobjectout.toJSONString();
				}
			} else {
				jsonobjectout = new JSONObject();
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Aadhaar Number does not exist");
				return jsonobjectout.toJSONString();
			}

		} else {
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			if (!checkres) {
				jsonobjectout.put("message", "NEET Roll Number " + neetrollno + " already Exist");
			} else if (!checkresforappnumber) {
				jsonobjectout.put("message", "Applcaition Number " + appnumber + " already Exist");
			} else if (checkresforaadhaarnumber) {
				jsonobjectout.put("message", "Aadhaar Number " + aadhaatnumber + " already Exist");
			}

			return jsonobjectout.toJSONString();
		}

	}

	@RequestMapping(value = "/admin/db_formcontrolUrl", method = RequestMethod.GET)
	public ModelAndView db_formcontrolUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("db_formcontrolUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("db_formcontrolTiles");
	}

	@RequestMapping(value = "/admin/ViewmeritUrl", method = RequestMethod.GET)
	public ModelAndView ViewmeritUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("ViewmeritUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Viewmerit");
	}

	@RequestMapping(value = "/PayfeesUrl", method = RequestMethod.GET)
	public ModelAndView PayfeesUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PayfeesUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		System.out.println("PayFrees");
		Mmap.put("msg", msg);
		return new ModelAndView("Payfees");
	}

	@RequestMapping(value = "/admin/PaybasicfeesUrl", method = RequestMethod.GET)
	public ModelAndView PaybasicfeesUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PaybasicfeesUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("basicfees");
	}

}
