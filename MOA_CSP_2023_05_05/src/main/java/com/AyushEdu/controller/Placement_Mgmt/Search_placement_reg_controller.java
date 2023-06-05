package com.AyushEdu.controller.Placement_Mgmt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.dao.Placement_Mgmt.Search_placement_reg_Dao;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_placement_reg_controller {

	@Autowired
	Type_of_Degree_MstrDao  TDdao;
	
	@Autowired
	private Search_placement_reg_Dao ARdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/search_placement_Reg_Url", method = RequestMethod.GET)
	public ModelAndView search_nch_Reg_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,RedirectAttributes ra) {

		try {
			
			//SECURITY - POOJA

			if (request.getHeader("Referer") == null) {
				ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			
			
		Mmap.put("msg", msg);
		Mmap.addAttribute("langugae", "english");
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		


	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("search_placement_reg_Tiles");
	}

	
	@PostMapping("/getSearch_placement_reg_data")
	public @ResponseBody List<Map<String, Object>> getSearch_placement_reg_data(int startPage, int pageLength,
			String Search,String orderColunm,String orderType){
		return ARdao.DataTableSearch_placement_reg(startPage, pageLength, Search, orderColunm, orderType);        
	}

	@PostMapping("/getSearch_placement_reg_dataCount")
	public @ResponseBody long getSearch_placement_reg_dataCount(String Search){
		System.err.println("--------------------------------HARSH------------------------------------");
		return ARdao.DataTableSearch_placement_reg_count(Search);
	}

	@RequestMapping(value = "/getDistrictOnstate_search_placement_report_student", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstate_search_placement_report_student(String selval) {
		return common.district(sessionFactory, selval);
	}
	
	@RequestMapping(value = "/MedicalImagePathplaceforstu_reg", method = RequestMethod.GET)
	public void MedicalImagePathplaceforstu_reg(@ModelAttribute("i_id") String id, @ModelAttribute("id5") String myImg,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String i_id = id;
		String filePath = ARdao.getImagePath(i_id);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();
		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//			request.getRealPath("/") + "/srv/Document/No_Image.jpg";

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

			// System.out.println(ex);

			// admin//js//img//No_Image.jpg

			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//				request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
