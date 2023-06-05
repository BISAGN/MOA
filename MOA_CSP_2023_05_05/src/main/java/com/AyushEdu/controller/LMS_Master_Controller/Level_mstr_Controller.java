package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
import com.AyushEdu.Core_Directory.Course_Master_CD_DAO;
import com.AyushEdu.Core_Directory.Level_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_LEVEL_MSTR;

import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.LevelDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Level_mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Level_Master_CD_DAO  Lvl_dirdao;

	@Autowired
	LevelDAO ldao;
	
	//==========================================OPEN PAGE LEVEL========================================== 
	
		@RequestMapping(value = "/LevelUrl", method = RequestMethod.GET)
		public ModelAndView LevelUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			try {
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("LevelUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
			 Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

			 } catch (Exception e) {
					e.printStackTrace();
				}
			return new ModelAndView("Level_Tiles");

		}
		
		//==========================================SAVE LEVEL NAME========================================== 	

		
		@PostMapping(value = "/LevelAction")
		public ModelAndView LevelAction(@Validated @ModelAttribute("LevelCMD") EDU_LMS_LEVEL_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {

			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			}
			
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LevelUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String level_name = request.getParameter("level_name");
			String sequence_name = request.getParameter("sequence_name");
			//System.out.println("sequence_name===========>"+sequence_name);
			String status = request.getParameter("status");

			if (level_name == null || level_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Level.");
				return new ModelAndView("redirect:LevelUrl");
			}
			if (validation.maxlengthcheck50(level_name) == false) {
				ra.addAttribute("msg", "Level " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:LevelUrl");
			}
			if (validation.isOnlyAlphabetNumeric(level_name) == false) {
				ra.addAttribute("msg", "Level " + validation.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:LevelUrl");
			}
			if (sequence_name == null || sequence_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Sequence.");
				return new ModelAndView("redirect:LevelUrl");
			}
			if (validation.maxlengthcheck2(sequence_name) == false) {
				ra.addAttribute("msg", "Sequence " + validation.MaxlengthcheckMSG2);
				return new ModelAndView("redirect:LevelUrl");
			}
			if (validation.isOnlyNumer(sequence_name) == true) {
				ra.addAttribute("msg", "Sequence " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:LevelUrl");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:LevelUrl");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:LevelUrl");
			}

			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
//			String system_name = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LMS_LEVEL_MSTR where upper(level_name)=:level_name and upper(sequence_name)=:sequence_name and upper(status)=:status and id !=:id")
						.setParameter("level_name", td.getLevel_name().toUpperCase())
						.setParameter("sequence_name", td.getSequence_name().toUpperCase())
						.setParameter("status", td.getStatus().toUpperCase())
						.setParameter("id", id).uniqueResult();
				
				int idd =0;
				if (id == 0) {
					td.setLevel_name(level_name);
					td.setSequence_name(sequence_name);
					td.setCreated_by(username);
					td.setCreated_date(date);
//					td.setCreated_dt(created_dt);
					if (c == 0) {
						 idd = (int)sessionHQL.save(td);
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();
				//For Core Directory
				Lvl_dirdao.Insert_Level_Master_Data(idd);
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

			return new ModelAndView("redirect:LevelUrl");
		}
		
		@PostMapping("/getFilterlevel_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String level_name, String status,String sequence_name) {

			return ldao.DataTablelevelDataList(startPage, pageLength, Search, orderColunm, orderType, level_name,status,sequence_name);

		}

		@PostMapping("/getTotallevel_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String level_name,String sequence_name) {
			return ldao.DataTablelevelDataTotalCount(Search, level_name,sequence_name);
			
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_levelUrl", method = RequestMethod.POST)
				public ModelAndView Edit_levelUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {

					 try {
						 
					if(request.getHeader("Referer") == null ) { 
						sessionEdit.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					}
					
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("LevelUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
			//		Session sessionHQL = this.sessionFactory.openSession();
				//	Transaction tx = sessionHQL.beginTransaction();
					EDU_LMS_LEVEL_MSTR level_Details = ldao.getlevelByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					//Mmap.put("level_name", common.getLevelList(sessionFactory));
					Mmap.put("level_Details", level_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("levelinfo", ldao.getlevelByid(Integer.parseInt(updateid)));
					
				//	tx.commit();
				//	sessionHQL.close();
				 } catch (Exception e) {
						e.printStackTrace();
					}
					return new ModelAndView("editLevel_Tiles");
				}
				
				//edit action
				@RequestMapping(value = "/edit_level_Action", method = RequestMethod.POST)
				public ModelAndView edit_level_Action(@ModelAttribute("edit_levelCMD") EDU_LMS_LEVEL_MSTR rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

					if(request.getHeader("Referer") == null ) { 
					//	session.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					}
					
					String roleid1 = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("LevelUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					String username = session.getAttribute("username").toString();

					int id = Integer.parseInt(request.getParameter("id"));
					String level_name = request.getParameter("level_name").trim();
					String sequence_name = request.getParameter("sequence_name").trim();
					String status = request.getParameter("status");
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
					if (level_name == null || level_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Level.");
						return new ModelAndView("redirect:LevelUrl");
					}
					if (validation.maxlengthcheck50(level_name) == false) {
						ra.addAttribute("msg", "Level " + validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:LevelUrl");
					}
					if (validation.isOnlyAlphabetNumeric(level_name) == false) {
						ra.addAttribute("msg", "Level " + validation.isOnlyAlphabetNumericMSG);
						return new ModelAndView("redirect:LevelUrl");
					}
					if (sequence_name == null || sequence_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Sequence.");
						return new ModelAndView("redirect:LevelUrl");
					}
					if (validation.maxlengthcheck2(sequence_name) == false) {
						ra.addAttribute("msg", "Sequence " + validation.MaxlengthcheckMSG2);
						return new ModelAndView("redirect:LevelUrl");
					}
					if (validation.isOnlyNumer(sequence_name) == true) {
						ra.addAttribute("msg", "Sequence " + validation.isOnlyNumerMSG);
						return new ModelAndView("redirect:LevelUrl");
					}
					if (status == null || status.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Status.");
						return new ModelAndView("redirect:LevelUrl");
					}
					if (status == "2" || status.trim().equals("2")) {
						ra.addAttribute("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:LevelUrl");
					}
					
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_LMS_LEVEL_MSTR where level_name=:level_name and sequence_name=:sequence_name and status=:status and id !=:id");
						q0.setParameter("level_name", level_name);
						
						q0.setParameter("sequence_name", sequence_name);

						q0.setParameter("status", status);

						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();

						if (c == 0) {
							String hql = "update EDU_LMS_LEVEL_MSTR set level_name=:level_name,sequence_name=:sequence_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
									+ " where id=:id";

							Query query = session1.createQuery(hql).setParameter("level_name", level_name).setParameter("sequence_name", sequence_name).setParameter("status", status)
									.setParameter("modified_by", username).setParameter("modified_date", new Date())
									.setParameter("id", id);
							msg = query.executeUpdate() > 0 ? "1" : "0";
							tx.commit();
							//For Core Directory
							Lvl_dirdao.Update_Level_Master_Data( id,level_name,status,username,new Date(),sequence_name);
							if (msg.equals("1")) {
								ra.addAttribute("msg", "Data Updated Successfully.");
							} else {
								ra.addAttribute("msg", "Data Not Updated.");
							}
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}

					} catch (RuntimeException e) {
						try {
							tx.rollback();
							ra.addAttribute("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
						}
						throw e;

					} finally {
						if (session1 != null) {
							session1.close();
						}
					}
					
					return new ModelAndView("redirect:LevelUrl");
				}
				
				@RequestMapping(value = "/delete_LevelUrl", method = RequestMethod.POST)
				public ModelAndView delete_Url2(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {

					if(request.getHeader("Referer") == null ) { 
				//		session1.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					}
					
					String roleid1 = session1.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("LevelUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"update EDU_LMS_LEVEL_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", id).setParameter("modified_by", username)
								.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

						
						tx.commit();
						Lvl_dirdao.Delete_Level_Master_Data(id);  
						session.close();
						if (app > 0) {
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
					return new ModelAndView("redirect:LevelUrl");
				}

		
}
