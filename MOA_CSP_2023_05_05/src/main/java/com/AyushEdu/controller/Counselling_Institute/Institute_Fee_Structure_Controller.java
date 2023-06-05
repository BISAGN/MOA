package com.AyushEdu.controller.Counselling_Institute;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Counselling_Institute.CoInstituteotherdetail;
import com.AyushEdu.dao.Counselling_Institute.Institute_Fee_Structure_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Institute_Fee_Structure_Controller {
	

	@Autowired
	CommonController common;
	
	@Autowired
	Institute_Fee_Structure_DAO Idao;

	@RequestMapping(value = "/InstituteInformation", method = RequestMethod.GET)
	public ModelAndView InstituteInformation(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.err.println("HELLO---------------HELLO");
			Mmap.put("msg", msg);
			Mmap.addAttribute("langugae", "english");
			Mmap.addAttribute("getsystemlist",common.getsystemlist());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("InstituteInformation");
	}
	
	//	GET DATA TABLE DATA
	@PostMapping("/getFilterInstitute_Fee_Structure_data")

	public @ResponseBody List<Map<String, Object>> getFilterProvisionalFormAReport_data(int startPage, int pageLength, 
			String Search, String orderColunm, String orderType, String institute_id) {
		
		return Idao.DataTableInstitute_Fee_Structure_DataList(startPage, pageLength, Search, orderColunm, orderType, institute_id);
		

	}

	//	GET DATA COUNT IN DATA TABLE
	@PostMapping("/getTotalInstitute_Fee_Structure_dataCount")
	public @ResponseBody long getTotalProvisionalFormAReport_dataCount(HttpSession sessionUserId, String Search, String institute_id) {
		
		return Idao.DataTableInstitute_Fee_Structure_DataTotalCount(Search, institute_id);
		
	}
	

	//	GET INSTITUTE LIST
	@PostMapping("/getInstituteList")
	public @ResponseBody ArrayList<ArrayList<String>> getInstituteList(String system_id) {
		
		ArrayList<ArrayList<String>> data = Idao.getInstituteListbySystem(system_id);
		
		return data;
	}
	
	//GET INSTITUTE FEE DETAILS
	 @RequestMapping(value = "/Institute_All_Fee_Details", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> Institute_All_Fee_Details(ModelMap Mmap,String veiw_id) {
		 
		 ArrayList<ArrayList<String>> list = Idao.getFee_Detailslist(veiw_id);
		 
		 ArrayList<ArrayList<String>> fee_list = new ArrayList<ArrayList<String>>();
		 
		 
		 for (int j = 0; j < list.size(); j++) {
			 
		 ArrayList<String> list1 = new ArrayList<String>();
		 list1.add(list.get(j).get(0));
		 list1.add(list.get(j).get(1));
		 list1.add(list.get(j).get(2));
		 if(list.get(j).get(3).equals("t")){
		 list1.add("YES");
		 }else {
			 list1.add("NO");
		 }
		 fee_list.add(list1);
		 }
		 
		 System.err.println("FEES LIST========--------------"+fee_list);
		 System.err.println("LIST========--------------"+list);
		
			return fee_list;
		}
	
	 //GET IMAGE PATH IN TABLE
	 @RequestMapping(value = "/MedicalImagePath_Fee", method = RequestMethod.GET)
	 public void MedicalImagePath_Fee(@ModelAttribute("i_id") String id,@ModelAttribute("id4") String myImg, ModelMap model,
	 		HttpServletRequest request, HttpServletResponse response) throws IOException {
	 	
//	 	System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

	 	final int BUFFER_SIZE = 4096;

	 	
	 	String filePath = Idao.getImagePath(id);

//	 	System.out.println("chgukhdfguhkdfhgkj---" + filePath);

	 	model.put("filePath", filePath);

	 	ServletContext context = request.getSession().getServletContext();

	 	try {

	 		if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

	 			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//	 			request.getRealPath("/") + "/srv/Document/No_Image.jpg";

	 			File downloadFile = new File(fullPath);

	 			FileInputStream inputStream = new FileInputStream(downloadFile);

	 			String mimeType = context.getMimeType(fullPath);

	 			if (mimeType == null) {

	 				mimeType = "application/octet-stream";

	 			}

	 			response.setContentType(mimeType);

	 			response.setContentLength((int) downloadFile.length());

	 			OutputStream outStream = response.getOutputStream();

	 			byte[] buffer = new byte[BUFFER_SIZE];

	 			int bytesRead = -1;

	 			while ((bytesRead = inputStream.read(buffer)) != -1) {

	 				outStream.write(buffer, 0, bytesRead);

	 			}

	 			inputStream.close();

	 			outStream.close();

	 		} else {

	 			String fullPath = filePath;

	 			File downloadFile = new File(fullPath);

	 			FileInputStream inputStream = new FileInputStream(downloadFile);

	 			String mimeType = context.getMimeType(fullPath);

	 			if (mimeType == null) {

	 				mimeType = "application/octet-stream";

	 			}

	 			response.setContentType(mimeType);

	 			response.setContentLength((int) downloadFile.length());

	 			OutputStream outStream = response.getOutputStream();

	 			byte[] buffer = new byte[BUFFER_SIZE];

	 			int bytesRead = -1;

	 			while ((bytesRead = inputStream.read(buffer)) != -1) {

	 				outStream.write(buffer, 0, bytesRead);

	 			}

	 			inputStream.close();

	 			outStream.close();

	 		}

	 	} catch (Exception ex) {

	 		System.out.println(ex);
	 		
	 	//	admin//js//img//No_Image.jpg
	 		
	 		
	 		
	 		String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//	 				request.getRealPath("/") + "/srv/Document/No_Image.jpg";
	 		File downloadFile = new File(fullPath);

	 		FileInputStream inputStream = new FileInputStream(downloadFile);

	 		String mimeType = context.getMimeType(fullPath);

	 		if (mimeType == null) {

	 			mimeType = "application/octet-stream";

	 		}

	 		response.setContentType(mimeType);

	 		response.setContentLength((int) downloadFile.length());

	 		OutputStream outStream = response.getOutputStream();

	 		byte[] buffer = new byte[BUFFER_SIZE];

	 		int bytesRead = -1;

	 		while ((bytesRead = inputStream.read(buffer)) != -1) {

	 			outStream.write(buffer, 0, bytesRead);

	 		}

	 		inputStream.close();

	 		outStream.close();
	 	}
	 }
	 
	
}