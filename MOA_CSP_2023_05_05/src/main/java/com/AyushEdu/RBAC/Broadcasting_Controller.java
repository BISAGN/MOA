package com.AyushEdu.RBAC;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.dao.BroadcastingDao;
import com.AyushEdu.dao.NotificationDAOImpl;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Attachment_MasterDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Broadcasting_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	NotificationDAOImpl nti;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@Autowired
	BroadcastingDao hdao;
	
	@Autowired
	CommonController common;

//	@Autowired
//	private DistrictDao Dis_Dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	ValidationController validation;
	// -------------------------------Broadcasting For page Open -------------------------------------

		 @RequestMapping(value = "BroadcastingUrl", method = RequestMethod.GET)
		 public ModelAndView District(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			 
//			 try {
//					if(request.getHeader("Referer") == null ) { 
//					//	 session.invalidate();
//						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return new ModelAndView("redirect:/landingpage");
//					 }
//					
					String roleid1 = session.getAttribute("roleid").toString();
					String role = session.getAttribute("role").toString();
//					 Boolean val = roledao.ScreenRedirect("District", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
						
			 Mmap.put("msg", msg);
			 Mmap.put("state_id", common.getMedStateName(sessionFactory));
			 Mmap.put("getRoleList", common.getRoleList(sessionFactory,role));
//System.out.println(common.getRoleList(sessionFactory,""));
			 Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
			 Mmap.put("getRolebyAccess", common.getRolebyAccess(sessionFactory));
//			 
//			 } catch (Exception e) {
//					e.printStackTrace();
//				}
			 return new ModelAndView("BroadcastingTiles");
		 }

			@PostMapping(value = "/BroadcastingAction")
			public ModelAndView BroadcastingAction(@Validated @ModelAttribute("TB_NOTIFICATION") TB_NOTIFICATION nt, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, 
					RedirectAttributes ra) {

				String MSG = request.getParameter("MSG");
				String rolehidden = request.getParameter("rolehidden");

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				if (MSG == null || MSG.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter MSG.");
					return new ModelAndView("redirect:BroadcastingUrl");
				}
				if (rolehidden == null || rolehidden.trim().equals("")) {
					ra.addAttribute("msg", "Please Select Send To.");
					return new ModelAndView("redirect:BroadcastingUrl");
				}
//				if (request.getParameter("rolehidden") == null || request.getParameter("rolehidden").trim().equals("")) {
//					ra.addAttribute("msg", "Please Select Send To");
//					return new ModelAndView("redirect:BroadcastingUrl");
//				}
				String role_name=request.getParameter("rolehidden_name");
				Long c = (Long) sessionHQL
						.createQuery(
								"select count(id) from TB_NOTIFICATION where message=:message ")
						.setParameter("message", MSG).uniqueResult();

				System.err.println("rolehidden==========="+rolehidden);
				System.out.println("length "+rolehidden.split(",").length);

//				try {
//					for(int i=0;i<rolehidden.split(",").length;i++) {
						nti.getRoleUserId(rolehidden);
						String noti=common.broadcasting(MSG, nti.getRoleUserId(rolehidden), role_name, sessionFactory, session)!=0?"Data Saved Successfully.":"Data Not Saved Successfully.";
						
					//}
					
						ra.addAttribute("msg", noti);

//				} catch (RuntimeException e) {
//					try {
//
//						ra.addAttribute("msg", "roll back transaction");
//					} catch (RuntimeException rbe) {
//						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
////					}
//					throw e;
//				} finally {
//					if (sessionHQL != null) {
//						sessionHQL.close();
//					}
//				}
				
				return new ModelAndView("redirect:BroadcastingUrl");
			}

			/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
			@PostMapping("/getFilterBroadcasting_data")

				public @ResponseBody List<Map<String, Object>> DataTableAttachment_MasterDataList(int startPage, int pageLength,
					String orderColunm, String orderType, String Search,String MSG,String receiver)
			{
					
					return hdao.DataTableBroadcastingDataList(startPage, pageLength, orderColunm, orderType,Search,MSG,receiver);

			}

			@PostMapping("/getTotalBroadcasting_dataCount")
			public @ResponseBody long DataTableAttachment_MasterDataTotalCount(HttpSession sessionUserId, String Search, String MSG,String receiver) {
					
					return hdao.DataTableBroadcastingDataTotalCount(Search, MSG,receiver);
				}
}
