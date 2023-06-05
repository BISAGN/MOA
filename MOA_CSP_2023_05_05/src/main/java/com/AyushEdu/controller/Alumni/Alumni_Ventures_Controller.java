package com.AyushEdu.controller.Alumni;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_POST;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Alumni_Ventures_DAO;

@Controller
public class Alumni_Ventures_Controller {

	@Autowired
	Alumni_Ventures_DAO AlumVenDao;
	
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	

	@RequestMapping(value = "/admin/Alumni_VenturesUrl", method = RequestMethod.GET)
	public ModelAndView Alumni_VenturesUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		//SECURITY -- RIDDHI
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Alumni_VenturesUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("getFeedCategoryList",common.getFeedCategoryList(sessionFactory));
		return new ModelAndView("Alumni_VenturesTiles");

	}

	@ResponseBody
	@RequestMapping(value = "/admin/SaveAlumVenturesData", method = RequestMethod.POST, produces = {
			"application/json" })
	public String SaveAlumVenturesData(HttpServletRequest request,@RequestPart String jsondata,@RequestPart MultipartFile image,HttpSession session) {
	
//		if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
//		 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//		 return new ModelAndView("redirect:/landingpage");
//	 }
//	String roleid1 = session.getAttribute("roleid").toString();
//	 Boolean val = roledao.ScreenRedirect("Alumni_VenturesUrl", roleid1);		
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//	}
		// CouncellingValidationController councellingValidationController = new
		// CouncellingValidationController();
		// String rolename = request.getSession().getAttribute("rolename").toString();
		JSONArray jsonp = new JSONArray();
		JSONObject object = new JSONObject();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobjectout = new JSONObject();
		String feed_cat ="";
		String title = "";
		String description = "";
		String upload_img = "";
		String post_date = "";
		String batch = "";
		String res = "";
		String actiontype = "Edit";
		try {
			
			//System.out.println("jsondata"+jsondata);
			object = (JSONObject) jsonparser.parse(jsondata);
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			if (object.get("actiontype") != null) {
				actiontype = object.get("actiontype").toString().trim();
			}
		
			if (object.get("feed_cat") == "0") {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Enter Feed Categories");
				return jsonobjectout.toJSONString();
			} else {
				feed_cat = String.valueOf(object.get("feed_cat"));
			
			}
			
			if (object.get("title") == null || object.get("title") == "") {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Enter Title");
				return jsonobjectout.toJSONString();
			} else {
				title = String.valueOf(object.get("title")).trim();
			}

			if (object.get("description") == null || object.get("description") == "") {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Enter Description");
				return jsonobjectout.toJSONString();
			} else {
				description = object.get("description").toString().trim();
			}
		 
//			if (object.get("upload_img") == null || object.get("upload_img") == "") {
//				jsonobjectout.put("status", "0");
//				jsonobjectout.put("message", "Please Upload image");
//				return jsonobjectout.toJSONString();
//			} else {
				//upload_img = object.get("upload_img").toString().trim();
				upload_img = gd(request, image, session,upload_img);
		//	}

//			if (object.get("post_date") == null || object.get("post_date") == "") {
//				jsonobjectout.put("status", "0");
//				jsonobjectout.put("message", "Please Select Post Date");
//				return jsonobjectout.toJSONString();
//			}
			if (object.get("batch") == null || object.get("batch") == "") {
				jsonobjectout.put("status", "0");
				jsonobjectout.put("message", "Please Select Post Date");
				return jsonobjectout.toJSONString();
			} else {
				batch = object.get("batch").toString().trim();
			}
			
			EDU_ALUM_ALUMNI_POST alum_ven = null;
			boolean checkTitlename = false;
			
//			List CheckTitleExist = AlumVenDao.CheckTitleNameExist(title.trim().toLowerCase());
//			
//			if (actiontype.equalsIgnoreCase("add")) {
//				
//				if (CheckTitleExist.isEmpty()) {
//					checkTitlename = true;
//				}
//			} else {
//
//				
//				if (CheckTitleExist.isEmpty()) {
//					checkTitlename = true;
//				} else {
//					if (CheckTitleExist.size() > 1) {
//						checkTitlename = false;
//					} else {
//						alum_ven = AlumVenDao.GetInstituteDataByID(Integer.parseInt(object.get("insid").toString()));
//						if (eduLmsInstituteReg.getInstituteName().trim().equalsIgnoreCase(insname)) {
//							checkresforinsname = true;
//						} else {
//							checkresforinsname = false;
//						}
//					}
//				}
//
//			}
			

			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
//			Date post_dt = null;
//			try {
//				post_dt = format.parse(String.valueOf(object.get("post_date")));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		
			if (actiontype.equalsIgnoreCase("add")) {
				alum_ven = new EDU_ALUM_ALUMNI_POST();
			}else {
				System.out.println(object.get("id").toString());
				int id = Integer.parseInt(object.get("id").toString());
				alum_ven  = AlumVenDao.GetAlumVenturesDataByID(id);
			}
			alum_ven.setTitle(title);
			alum_ven.setDescription(description);
			alum_ven.setUploadImg(upload_img);
			alum_ven.setPostDate(new Date());
			alum_ven.setBatch(batch);
			alum_ven.setStatus(1);
			
			EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR Feed_cat_mst = new EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR();
			Feed_cat_mst.setId(Integer.parseInt(feed_cat));
			alum_ven.setFeedCatId(Feed_cat_mst);

			if (actiontype.equalsIgnoreCase("add")) {
				alum_ven.setCreatedBy(userid);
				alum_ven.setCreatedDate(new Date());
			}
			else {
				alum_ven.setModifiedBy(userid);
				alum_ven.setModifiedDate(new Date());
			}

			AlumVenDao.SaveAlumVenturesData(alum_ven, actiontype);

			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "1");
			if (actiontype.equalsIgnoreCase("add")) {
				jsonobjectout.put("message", "Data Saved Successfully");
			}

			else {
				jsonobjectout.put("message", "Data Updated Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonobjectout = new JSONObject();
			jsonobjectout.put("status", "0");
			jsonobjectout.put("message", "Something went wrong");
			res = jsonobjectout.toJSONString();
		}
		return jsonobjectout.toJSONString();
	}

public String gd(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
		
	String extension=""; //add line
	String fname = ""; //add line
	
	request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
	
	MultipartFile file = mul;
	
	if (!file.getOriginalFilename().isEmpty()) {
		
		byte[] bytes = file.getBytes();
		String  mnhFilePath = session.getAttribute(id).toString();
		
        File dir = new File(mnhFilePath);
		if (!dir.exists())
			dir.mkdirs();
		String filename = file.getOriginalFilename();
				
		int j = filename.lastIndexOf('.');
		if (j >= 0) {
			extension = filename.substring(j+1);
		}
		java.util.Date date1= new java.util.Date();
		fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
		
		File serverFile = new File(fname);	               
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);	                
		stream.close();

	}else {

		
	}
	return fname;
	
	}


@RequestMapping(value = "/admin/LoadAlumVenturesData", method = RequestMethod.POST, produces = { "application/json" })
public @ResponseBody String LoadAlumVenturesData(HttpServletRequest request, @RequestBody String data) {

	JSONArray jSONArray = new JSONArray();
	JSONObject object = new JSONObject();

	JSONObject object1 = new JSONObject();
	JSONParser jsonParser = new JSONParser();
	List<EDU_ALUM_ALUMNI_POST> alum_ventures = null;
	List<EDU_ALUM_ALUMNI_POST> Totalalum_ventures = null;
	try {

		

		object = (JSONObject) jsonParser.parse(data);

		//String username = request.getSession().getAttribute("username").toString();
		int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		
		alum_ventures = AlumVenDao.Loadalum_ventureData(userid, data);
		int counter = Integer.parseInt(object.get("startPage").toString()) + 1;
		int hidval = 1;

		if (!alum_ventures.isEmpty()) {
			for (EDU_ALUM_ALUMNI_POST alumventures : alum_ventures) {
				DateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				object = new JSONObject();
				object.put("srno", "<span class='avtar avatar-blue'>" + counter + "</span>");
				object.put("feed_cat", alumventures.getFeedCatId().getFeed_category());
				object.put("title", alumventures.getTitle());
				object.put("description", alumventures.getDescription());
//				String  dt =format.alumventures.getPostDate().toString();
//				object.put("postdate",dt.substring(0,10));
				object.put("postdate",formater.format(alumventures.getPostDate()));
				object.put("batch", alumventures.getBatch());
				
				String imagestr = imageEncoderDecoder(request, alumventures.getUploadImg());
				object.put("Ventuimage", "<img src='data:image/png;base64," + imagestr
						+ "' style='height:100px;width:100px;' onclick=\"ViewImage('" + imagestr + "')\" />");
				
				String f = "";

				String hidden = "<input type='hidden' name='hid" + hidval + "' id='hid" + hidval + "' value='"
						+encrypt(alumventures.getId() + "") + "' /> ";
				f = "<i class='action_icons action_update edtcls' title='Edit Data'></i>";
				f += "<i class='action_icons action_delete delcls' title='Delete Data'></i>";

				object.put("action",
						"<ul class=\"buttons-group\"><li><a href=\"#\" class=\"main-btn active-btn btn-hover btn-sm edtcls\"><i class=\"lni lni-pencil-alt\"></i></a></li><li><a href=\"#\" class=\"main-btn danger-btn btn-hover btn-sm delcls\"><i class=\"lni lni-trash-can\"></i></a></li></ul>"
								+ hidden);

				jSONArray.add(object);
				counter++;
				hidval++;

			}

			Totalalum_ventures = AlumVenDao.LoadalumVenturesDataForCount(userid);

			object1.put("alumVentureslist", jSONArray);
		} else {
			jSONArray = new JSONArray();
			object1.put("alumVentureslist", jSONArray);
		}

		object1.put("status", "1");
		object1.put("message", "Success");
		if (Totalalum_ventures != null) {
			object1.put("TotalCount", Totalalum_ventures.size());
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
@RequestMapping(value = "/admin/GetAlumVenturesDataForUpdate", method = RequestMethod.POST, produces = {
		"application/json" })
public String GetAlumVenturesDataForUpdate(HttpServletRequest request, @RequestBody String data) {
	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArray1 = new JSONArray();
	JSONParser jsonp = new JSONParser();
	JSONObject jsonobjectout = new JSONObject();
	String returnstring = "";
	try {

		// Add Server Side Validation TODO
		jsonObject = (JSONObject) jsonp.parse(data);
		if (jsonObject.get("ventid") != null) {
			DateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			int ventid = Integer.parseInt(decrypt(jsonObject.get("ventid").toString()));
			EDU_ALUM_ALUMNI_POST alum_ventures = AlumVenDao.GetAlumVenturesDataByID(ventid);
			if (alum_ventures != null) {
				jsonobjectout.put("feed_cat", alum_ventures.getFeedCatId().getId());
				jsonobjectout.put("title", alum_ventures.getTitle());
				jsonobjectout.put("description", alum_ventures.getDescription());
				jsonobjectout.put("post_date",formater.format(alum_ventures.getPostDate()));
				//jsonobjectout.put("post_date", alum_ventures.getPostDate());
				jsonobjectout.put("batch", alum_ventures.getBatch());
				jsonobjectout.put("imagedata", imageEncoderDecoder(request,alum_ventures.getUploadImg()));
				jsonobjectout.put("status", "1");
				jsonobjectout.put("message", "Success");
				jsonobjectout.put("vent_id",alum_ventures.getId());
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
@RequestMapping(value = "/admin/DeleteAlumVenturesData", method = RequestMethod.POST, produces = {
		"application/json" })
public String DeleteAlumVenturesData(@RequestBody String data, HttpServletRequest request) {

	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArray1 = new JSONArray();
	JSONParser jsonp = new JSONParser();
	JSONObject jsonobjectout = new JSONObject();
	String returnstring = "";
	try {
		// Add Server Side Validation TODO
		jsonObject = (JSONObject) jsonp.parse(data);
		if (jsonObject.get("ventid") != null) {
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			int ventid = Integer.parseInt(decrypt(jsonObject.get("ventid").toString()));
			EDU_ALUM_ALUMNI_POST alum_ventures = AlumVenDao.GetAlumVenturesDataByID(ventid);
			if (alum_ventures != null) {

//				String sessionuserid = request.getSession().getAttribute("userId_for_jnlp").toString();
				alum_ventures.setModifiedBy(userid);
				alum_ventures.setModifiedDate(new Date());
				alum_ventures.setStatus(2);
				AlumVenDao.SaveAlumVenturesData(alum_ventures,"Edit");
				jsonobjectout.put("status", "1");
				jsonobjectout.put("message", "Data Deleted Successfully");
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



private static SecretKeySpec secretKey;
private static byte[] key;
private static String passphrase = "Vision4@Bisag";
public static void setKey(String myKey) {
	MessageDigest sha = null;
	try {
		key = myKey.getBytes("UTF-8");
		sha = MessageDigest.getInstance("SHA-256");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		secretKey = new SecretKeySpec(key, "AES");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
}

public static String encrypt(String strToEncrypt) {
	try {
		setKey(passphrase);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public static String decrypt(String strToDecrypt) {
	try {
		setKey(passphrase);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String imageEncoderDecoder(HttpServletRequest request,String imagepath) throws IOException {

	// image path declaration
	// String imgPath = "src/main/resources/images/bean.png";

	// read image from file
	FileInputStream stream = null;
	try {
	 stream = new FileInputStream(imagepath);
	}catch(IOException e){
		String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
		stream = new FileInputStream(fullPath);
	}

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
	String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

	out.close();
	stream.close();
	return imageString;
}

}
