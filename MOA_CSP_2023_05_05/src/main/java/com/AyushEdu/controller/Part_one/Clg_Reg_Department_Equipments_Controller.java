package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_HOMEO_MATERIA_MEDICA;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_PEDIATRICS;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_PRACTICE_MEDICINE;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_PSYCHIATRY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_REPERTORY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIPMENT_SURGERY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEPT_EQUIP_ANATOMY_CHILD;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEP_EQUIP_ANATOMY;
import com.AyushEdu.Models.Clg_Reg_Department_Equipments.CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD;
import com.AyushEdu.dao.Part_One.Clg_Reg_Department_Equipment_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Department_Equipments_Controller {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Clg_Reg_Department_Equipment_Dao dedao; 
	
	@Autowired
	CommonController common;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	ValidationController validation;
	
	@RequestMapping(value = "admin/department_equipments", method = RequestMethod.GET)
	public ModelAndView department_equipments(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		
//		if(ibdao.getpid_from_userid(userid).size()!=0) {
//			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
//				Mmap.put("msg", "Please Save Basic details First");
//				return new ModelAndView("redirect:basics_information");
//
//			}else {
//				
//				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
//			}
//		}else {
//			
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}
//		
		String role = session.getAttribute("role").toString();
		String username = session.getAttribute("roleloginName").toString();
		
		if(role=="Institute_NCH") {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int parent_id = (int) sessionHQL
					.createQuery("select id from CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY where inst_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institude))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
		
		Mmap.put("msg", msg);
		Mmap.put("institude", institude);
		
		Mmap.put("dataobst", dedao.getAllinfo_obstetric_gynacology(Integer.parseInt(institude)));
		Mmap.put("dataanatomy", dedao.getAllinfo_anatomy_child(Integer.parseInt(institude)));
		
		Mmap.put("dataforAnatomy", dedao.getAllItemforAnatomy());
		Mmap.put("dataforAnatomy_id", dedao.getAllitem_for_anatomy());
		
		Mmap.put("datacommunity", dedao.getAllInfoCommunityMedicine());
		Mmap.put("datacommunity_id", dedao.getAllCommunityMedicine_id());
		
		Mmap.put("dataforensic_equipments", dedao.getForensicEquipmentDetails());
		Mmap.put("dataforensic_equipments_id", dedao.getAllForensic_Equip_Details());
		
		Mmap.put("dataforensic_act", dedao.getForensicActs());
		Mmap.put("dataforensic_act_id", dedao.getAllForensic_Acts());
		
		Mmap.put("datahomeo_ph", dedao.getHomeo_Pharmacy_child(Integer.parseInt(institude)));
		Mmap.put("datahomeophatic_pharmacy", dedao.getHomeophathic_Pharmacy());
		Mmap.put("datahomeophatic_pharmacy_id", dedao.getHomeophatic_Pharmacy_id());
		
		Mmap.put("datapatho_micro", dedao.getPatho_Micro());
		Mmap.put("datapatho_micro_id", dedao.getPatho_microbioDetails());
		
		Mmap.put("datapatho_bio", dedao.getPatho_Bio());
		Mmap.put("datapatho_bio_id", dedao.getPatho_biochemDetails());
		
		Mmap.put("data_bio", dedao.get_Bio());
		Mmap.put("data_bio_id", dedao.get_biochemDetails());
		
		Mmap.put("datamed", dedao.getPractice_Med(Integer.parseInt(institude)));
		Mmap.put("datareper", dedao.getRepertory(Integer.parseInt(institude)));
		Mmap.put("datasurgery", dedao.getSurgery(Integer.parseInt(institude)));
		Mmap.put("datahomeo_mat_med", dedao.getHMM(Integer.parseInt(institude)));
		Mmap.put("datapsychiatry", dedao.getPsych(Integer.parseInt(institude)));
		Mmap.put("datapediatric", dedao.getPediatric(Integer.parseInt(institude)));
		Mmap.put("dataorganon", dedao.getOrganon(Integer.parseInt(institude)));
		Mmap.put("dataorganon_id", dedao.getOrganon_id(Integer.parseInt(institude)));
		
		Mmap.put("datacomm", dedao.getAllComm(Integer.parseInt(institude)));
		
//		Mmap.put("basic_info_id", dedao.basic_info_id(userid).get(0).get(0));
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
		
		
		return new ModelAndView("department_equipments");
	}
	
	
	
	//---------------------------------Obstetric & Gynacology Save--------------------------------------------------
	
	
		 @PostMapping(value = "/obstetric_gynacology_Action")
		 public @ResponseBody Map<String, String> obstetric_gynacology_Action(ModelMap Mmap, HttpSession session,
				 HttpServletRequest request,RedirectAttributes ra, @RequestParam(value = "og_alco_lice_doc", required = false) MultipartFile og_alco_lice_dc,
				 @RequestParam(value = "og_photo_cm_doc", required = false) MultipartFile og_photo_cm_dc,
				 @RequestParam(value = "og_bill_equipment_doc", required = false) MultipartFile og_bill_equipment_dc) throws IOException, ParseException {
		 
		
		 
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String role = session.getAttribute("roleStaff_lvl").toString();
			
			System.err.println("role for regi-----------" + role + "---");

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			Map<String, String> data = new HashMap<>();

			try {
				
				int p_id=Integer.parseInt(request.getParameter("deprt_equip_hidden"));
				
					System.err.println("P-ID--------"+p_id);
					
					String og_equipments = request.getParameter("og_equipments");
					String og_additionalitem = request.getParameter("og_additionalitem");
					
					String copy_of_alchohol  = "copy_of_alchohol";
					String photo_of_cadavers  = "photo_of_cadavers";
					String upload_bill  = "upload_bill";
					
					String og_photo_cm_doc = request.getParameter("og_photo_cm_doc");
					String og_alco_lice_doc = request.getParameter("og_alco_lice_doc");
					String og_bill_equipment_doc = request.getParameter("og_bill_equipment_doc");
					
					
					if (og_equipments == null || og_equipments.trim().equals("")) {
						ra.addAttribute("msg", "Number of Equipments for Identification");
						return data;
					}
					
					if (validation.isOnlyNumer(og_equipments) == true) {
						data.put("msg", " Number of Equipments for Identification" + validation.isOnlyNumerMSG);
						return data;
				     }
					
					if (validation.maxlengthcheck10(og_equipments) == false) {
						data.put("msg", " Number of Equipments for Identification" + validation.MaxlengthcheckMSG10);
						return data;
					}
					
					if (og_additionalitem == null || og_additionalitem.trim().equals("")) {
						ra.addAttribute("msg", "Additional Items");
						return data;
					}
					
					if (validation.isAlphabetCDASH(og_additionalitem) == false) {
						data.put("msg", "Additional Items " + validation.isOnlyAlphabetMSG);
						return data;
					}
					
					if (validation.maxlengthcheck(og_additionalitem) == false) {
						data.put("msg", "Additional Items" + validation.MaxlengthcheckMSG);
						return data;
					}
					
					System.err.println("og_alco_lice_dc----------"+og_alco_lice_dc);
					
					
					if (!og_alco_lice_dc.isEmpty()) {
						copy_of_alchohol = upload_imagemethod(request,og_alco_lice_dc,session, copy_of_alchohol);
						System.err.println("copy_of_alchohol----------"+copy_of_alchohol);
					}
					else {
						copy_of_alchohol = request.getParameter("hid_og_alco_lice");
					}
					
					if (!og_photo_cm_dc.isEmpty()) {
						photo_of_cadavers = upload_imagemethod(request,og_photo_cm_dc,session, photo_of_cadavers);
						System.err.println("photo_of_cadavers----------"+photo_of_cadavers);
					}
					else {
						photo_of_cadavers = request.getParameter("hid_og_photo_cm");
					}
					
					if (!og_bill_equipment_dc.isEmpty()) {
						upload_bill = upload_imagemethod(request,og_bill_equipment_dc,session, upload_bill);
						System.err.println("upload_bill----------"+upload_bill);
					}
					else {
						upload_bill = request.getParameter("hid_og_bill_equipment");
					}
					
					

					
					if(p_id==0) {
						CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY rd =new CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY();
//						

					rd.setNo_of_equip_identi(og_equipments);
					rd.setAdditional_item(og_additionalitem);
					if(!copy_of_alchohol.equals("")) {
						rd.setCopy_of_alchoho(copy_of_alchohol);
						}
					if(!photo_of_cadavers.equals("")) {
						rd.setPhotographs_of_cadavers(photo_of_cadavers);
						}
					if(!upload_bill.equals("")) {
						rd.setUpload_purchase_bill(upload_bill);
						}
					
					

					rd.setUserid(Integer.parseInt(userid));
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					rd.setInst_id(Integer.parseInt(institude));
					
					rd.setCreated_by((userid));
					rd.setCreated_date(date);
					rd.setStatus(0);
					
					p_id = (int) sessionHQL.save(rd);
					
				
					
					 data.put("msg", "Save as Draft Successfully");
					sessionHQL.flush();
					sessionHQL.clear();
				}
					else {
//					
//					
//					
//					System.err.println("id for update-----else------" + id_for_update + "---");
					CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY urd = (CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY) sessionHQL.get(CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY.class,
							(p_id));
//					
					
					urd.setNo_of_equip_identi(og_equipments);
					urd.setAdditional_item(og_additionalitem);
					if(!copy_of_alchohol.equals("")) {
						urd.setCopy_of_alchoho(copy_of_alchohol);
						}
					if(!photo_of_cadavers.equals("")) {
						urd.setPhotographs_of_cadavers(photo_of_cadavers);
						}
					if(!upload_bill.equals("")) {
						urd.setUpload_purchase_bill(upload_bill);
						}

					urd.setUserid(Integer.parseInt(userid));
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					urd.setInst_id(Integer.parseInt(institude));
					
					urd.setModified_by((userid));
					urd.setModified_date(date);
					urd.setStatus(0);


	//

	//
					sessionHQL.update(urd);
					 data.put("msg", "Draft Update Successfully");
					sessionHQL.flush();
					sessionHQL.clear();
				}

					tx.commit();
//					data.put("msg", "Data Save As Draft Successfully.");
					//}

				} catch (RuntimeException e) {
					e.printStackTrace();
					try {
					} catch (RuntimeException rbe) {
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
					return data; 
		}
		 
		 
		 
		 
		 //----------------------------------UPLOAD DOC METHOD
		 
		//----------------------Upload pdf method-------------------------------------------
			
				public String upload_imagemethod(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
					
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
						fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+id+"."+extension;
						
						File serverFile = new File(fname);	               
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);	                
						stream.close();

					}else {

						
					}
					return fname;
					
					}
			
			
			
			
			//SAVE ANATOMY DETAILS-----------------------------------------------------------------------------------------------------------
			
				@PostMapping(value = "/Anatomy_Save_Draft_Action")
				public @ResponseBody String Anatomy_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

					if(request.getHeader("Referer") == null ) { 
						 session.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return  "redirect:/login";
					 }
					String role = session.getAttribute("role").toString();
					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_anatomy = dedao.getAllitem_for_anatomy();
					System.err.println("ABC-UG  4 APR----------------"+dedao.getAllitem_for_anatomy().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					
					
					try {
						
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEP_EQUIP_ANATOMY equip_anatomy =new CLG_REG_DEP_EQUIP_ANATOMY();
						
						for (int i = 0; i < item_list_anatomy.size(); i++) {
							
							String item_id_ana = request.getParameter("item_id_ana"+item_list_anatomy.get(i).get(0));
							String item_name_ana = request.getParameter("item_name_ana_"+item_list_anatomy.get(i).get(0));
							String available_num_ana = request.getParameter("available_num_ana_"+item_list_anatomy.get(i).get(0));
							String hid_item_ana = request.getParameter("hid_item_ana_"+item_list_anatomy.get(i).get(0));
							
							if (available_num_ana == null || available_num_ana.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_anatomy.get(i).get(1));
								return "Please Enter Available(Number/Quantity)"+item_list_anatomy.get(i).get(1);
								
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_ana) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							equip_anatomy.setItem_id(Integer.parseInt(item_id_ana));
							equip_anatomy.setItem_name(item_name_ana);
							equip_anatomy.setAvailable_num_ana(available_num_ana);
							equip_anatomy.setCreated_by(userid);
							equip_anatomy.setCreated_date(date);
							equip_anatomy.setInst_id(Integer.parseInt(institude));
							equip_anatomy.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_ana) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_anatomy= (Integer) sessionHQL.save(equip_anatomy);
								sessionHQL.flush();
								sessionHQL.clear();
							}
							else {
								CLG_REG_DEP_EQUIP_ANATOMY equip_anatomy_u = (CLG_REG_DEP_EQUIP_ANATOMY) sessionHQL
										.get(CLG_REG_DEP_EQUIP_ANATOMY.class, Integer.parseInt(hid_item_ana));
								
								equip_anatomy_u.setItem_id(Integer.parseInt(item_id_ana));
								equip_anatomy_u.setItem_name(item_name_ana);
								equip_anatomy_u.setAvailable_num_ana(available_num_ana);
								equip_anatomy_u.setModified_by((userid));
								equip_anatomy_u.setModified_date(date);
								sessionHQL.update(equip_anatomy_u);
								sessionHQL.flush();
								sessionHQL.clear();
								
							}
						
						}
						tx1.commit();
						
						Transaction tx = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIP_ANATOMY_CHILD anatomy_child =new CLG_REG_DEPT_EQUIP_ANATOMY_CHILD();
						
						String ana_add_item = request.getParameter("ana_add_item");
						String ana_cada_ava = request.getParameter("ana_cada_ava");
						String anatomy_act = request.getParameter("anatomy_act");
						String hid_ana_child = request.getParameter("hid_ana_child");
												
						if (ana_add_item == null || ana_add_item.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Additional Items.");
							return "Please Enter Total Additional Items.";
						}
						
						if (validation.isOnlyNumer(ana_add_item) == true) {
							ra.addAttribute("msg", " Additional Items" + validation.isOnlyNumerMSG);
							return " Additional Items" + validation.isOnlyNumerMSG;
					    }
						
						if (validation.maxlengthcheck10(ana_add_item) == false) {
							ra.addAttribute("msg", "Additional Items" + validation.MaxlengthcheckMSG10);
							return "Additional Items" + validation.MaxlengthcheckMSG10;
						}
						
						if (ana_cada_ava == null || ana_cada_ava.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Cadavers Available.");
							return "Please Enter Total Cadavers Available.";
						}
						
						if (validation.maxlengthcheck10(ana_cada_ava) == false) {
							ra.addAttribute("msg", "Cadavers Available" + validation.MaxlengthcheckMSG10);
							return "Cadavers Available" + validation.MaxlengthcheckMSG10;
						}
						
						if (validation.isOnlyNumer(ana_cada_ava) == true) {
							ra.addAttribute("msg", "Cadavers Available" + validation.isOnlyNumerMSG);
							return " Cadavers Available" + validation.isOnlyNumerMSG;
					    }
						
						if (anatomy_act.trim().equals("0")) {
							ra.addAttribute("msg", "Please Enter Whether College covered under the provisions of Anatomy Act.");
							return "Please Enter Whether College covered under the provisions of Anatomy Act.";
						}
						
						UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
						String institude = String.valueOf(ea.getInstitute_id());	
						
						
						anatomy_child.setTotal_add_item(ana_add_item);
						anatomy_child.setTotal_cadavers_avai(ana_cada_ava);
						anatomy_child.setAnatomy_act(Integer.parseInt(anatomy_act));
						anatomy_child.setCreated_by((userid));
						anatomy_child.setCreated_date(date);
						anatomy_child.setInst_id(Integer.parseInt(institude));
						anatomy_child.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_ana_child) == 0) {
						//		constructed_area_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_course_pg1= (Integer) sessionHQL.save(anatomy_child);
								sessionHQL.flush();
								sessionHQL.clear();
								//tx.commit();
								//return String.valueOf(hid_course_pg1) ;
						}
							else {

								CLG_REG_DEPT_EQUIP_ANATOMY_CHILD anatomy_child_u = (CLG_REG_DEPT_EQUIP_ANATOMY_CHILD) sessionHQL
										.get(CLG_REG_DEPT_EQUIP_ANATOMY_CHILD.class, Integer.parseInt(hid_ana_child));
								
								anatomy_child_u.setTotal_add_item(ana_add_item);
								anatomy_child_u.setTotal_cadavers_avai(ana_cada_ava);
								anatomy_child_u.setAnatomy_act(Integer.parseInt(anatomy_act));
								anatomy_child_u.setModified_by((userid));
								anatomy_child_u.setModified_date(date);
								sessionHQL.update(anatomy_child_u);
								sessionHQL.flush();
								sessionHQL.clear();
							
								
								
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
					return  "Data Save Successfully";
				}
			
			
			
			
			
			
			
				//FETCH ANATOMY DATA================================================================
				
				@RequestMapping(value = "admin/getAnatomy_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEP_EQUIP_ANATOMY> getAnatomy_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEP_EQUIP_ANATOMY where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 APR=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEP_EQUIP_ANATOMY> clist = (List<CLG_REG_DEP_EQUIP_ANATOMY>) q.list();
					System.err.println("q============1 APR=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}
			
				
				
				//SAVE Community_Medicine-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Community_medicine_Save_Draft_Action")
				public @ResponseBody String Community_medicine_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

					if(request.getHeader("Referer") == null ) { 
						 session.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return  "redirect:/login";
					 }
					String role = session.getAttribute("role").toString();
					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_community = dedao.getAllCommunityMedicine_id();
					System.err.println("ABC-UG  5 APR----------------"+dedao.getAllCommunityMedicine_id().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					
							
					

					try {
						
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI equip_com =new CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI();
						
						for (int i = 0; i < item_list_community.size(); i++) {
//							
							
							String item_id_cm = request.getParameter("item_id_cm"+item_list_community.get(i).get(0));
							String item_name_cm = request.getParameter("item_name_cm_"+item_list_community.get(i).get(0));
							String available_num_cm = request.getParameter("available_num_cm_"+item_list_community.get(i).get(0));
							String hid_item_cm = request.getParameter("hid_item_cm_"+item_list_community.get(i).get(0));
							
							if (available_num_cm == null || available_num_cm.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_community.get(i).get(1));
								return "Please Enter Available(Number/ Quantity)"+item_list_community.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_cm) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							equip_com.setItem_id(Integer.parseInt(item_id_cm));
							equip_com.setItem_name(item_name_cm);
							equip_com.setAvailable_num_cm(available_num_cm);
							equip_com.setCreated_by(userid);
							equip_com.setCreated_date(date);
							equip_com.setInst_id(Integer.parseInt(institude));
							equip_com.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_cm) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_cm1= (Integer) sessionHQL.save(equip_com);
								sessionHQL.flush();
								sessionHQL.clear();
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI equip_com_u = (CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI.class, Integer.parseInt(hid_item_cm));
								
								equip_com_u.setItem_id(Integer.parseInt(item_id_cm));
								equip_com_u.setItem_name(item_name_cm);
								equip_com_u.setAvailable_num_cm(available_num_cm);
								equip_com_u.setModified_by((userid));
								equip_com_u.setModified_date(date);
								sessionHQL.update(equip_com_u);
								sessionHQL.flush();
								sessionHQL.clear();
								
							}
						
						}
						tx1.commit();
						

						Transaction tx = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD cm_child =new CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD();
						
						String family_welfare = request.getParameter("comm_welfaredevices");
						String source_nutrition = request.getParameter("comm_src_nutrition");
						String source_vitamin = request.getParameter("comm_src_vitamins");
						String hid_cm_child = request.getParameter("hid_cm_child");
												
						if (family_welfare == null || family_welfare.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Family Welfare devices.");
							return "Please Enter Family Welfare devices.";
						}
						
						if (validation.isAlphabetCDASH(family_welfare) == false) {
							ra.addAttribute("msg", "Family Welfare devices" + validation.isOnlyAlphabetMSG);
							return  "Family Welfare devices " + validation.isOnlyAlphabetMSG;
						}
						
						if (validation.maxlengthcheck(family_welfare) == false) {
							ra.addAttribute("msg", "Family Welfare devices" + validation.MaxlengthcheckMSG);
							return  "Family Welfare devices" + validation.MaxlengthcheckMSG;
						}
						
						if (source_nutrition == null || source_nutrition.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Sources of Nutrition.");
							return "Please Enter Sources of Nutrition.";
						}
						
						if (validation.isAlphabetCDASH(source_nutrition) == false) {
							ra.addAttribute("msg", "Sources of Nutrition" + validation.isOnlyAlphabetMSG);
							return  "Sources of Nutrition" + validation.isOnlyAlphabetMSG;
						}
						
						if (validation.maxlengthcheck(source_nutrition) == false) {
							ra.addAttribute("msg", "Cadavers Available" + validation.MaxlengthcheckMSG);
							return "Cadavers Available" + validation.MaxlengthcheckMSG;
						}
						
						if (source_vitamin == null || source_vitamin.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Sources of Vitamins.");
							return "Please Enter Sources of Vitamins.";
						}
						
						if (validation.isAlphabetCDASH(source_vitamin) == false) {
							ra.addAttribute("msg", "Sources of Vitamins" + validation.isOnlyAlphabetMSG);
							return  "Sources of Vitamins" + validation.isOnlyAlphabetMSG;
						}
						
						if (validation.maxlengthcheck(source_vitamin) == false) {
							ra.addAttribute("msg", "Sources of Vitamins" + validation.MaxlengthcheckMSG);
							return  "Sources of Vitamins" + validation.MaxlengthcheckMSG;
						}
						
						UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
						String institude = String.valueOf(ea.getInstitute_id());	
						
						
						cm_child.setFamily_welfare(family_welfare);
						cm_child.setSource_nutrition(source_nutrition);
						cm_child.setSource_vitamin((source_vitamin));
						cm_child.setCreated_by((userid));
						cm_child.setCreated_date(date);
						cm_child.setInst_id(Integer.parseInt(institude));
						cm_child.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_cm_child) == 0) {
						//		constructed_area_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_course_pg1= (Integer) sessionHQL.save(cm_child);
								sessionHQL.flush();
								sessionHQL.clear();
								//tx.commit();
								//return String.valueOf(hid_course_pg1) ;
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD cm_child_u = (CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI_CHILD.class, Integer.parseInt(hid_cm_child));
								
								cm_child_u.setFamily_welfare(family_welfare);
								cm_child_u.setSource_nutrition(source_nutrition);
								cm_child_u.setSource_vitamin((source_vitamin));
								cm_child_u.setModified_by((userid));
								cm_child_u.setModified_date(date);
								sessionHQL.update(cm_child_u);
								sessionHQL.flush();
								sessionHQL.clear();
							
								
								
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
					return  "Data Save Successfully";
				}		
				
			
			
			
	//FETCH Community Medicine================================================================
				
				@RequestMapping(value = "admin/getcommunitymedicine_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI> getcommunitymedicine_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 APR=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI> clist = (List<CLG_REG_DEPT_EQUIPMENT_COMMUNITY_MEDI>) q.list();
					System.err.println("q============1 APR=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}
			
			
			
//SAVE Forensic_Equipment_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Forensic_Equipment_Details_Save_Draft_Action")
				public @ResponseBody String Forensic_Equipment_Details_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
//					String role = session.getAttribute("role").toString();
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					String msg="";
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_forensic_eq = dedao.getAllForensic_Equip_Details();
					ArrayList<ArrayList<String>> item_list_forensic_eq1 = dedao.getAllForensic_Acts();
					System.err.println("ABC-UG  5 Forensic----------------"+dedao.getAllCommunityMedicine_id().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					
							
					

					try {
						
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS equip_com =new CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS();
						
						for (int i = 0; i < item_list_forensic_eq.size(); i++) {
//							
							
							String item_id_fm_eq = request.getParameter("item_id_fm_eq"+item_list_forensic_eq.get(i).get(0));
							String item_name_fm_eq = request.getParameter("item_name_fm_eq"+item_list_forensic_eq.get(i).get(0));
							String available_num_quant = request.getParameter("available_num_quant"+item_list_forensic_eq.get(i).get(0));
							String hid_item_fm_eq = request.getParameter("hid_item_fm_eq"+item_list_forensic_eq.get(i).get(0));
							
							if (available_num_quant == null || available_num_quant.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_forensic_eq.get(i).get(1));
								return "Please Enter Available(Number/ Quantity)"+item_list_forensic_eq.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_quant) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity)"+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							equip_com.setItem_id(Integer.parseInt(item_id_fm_eq));
							equip_com.setItem_name(item_name_fm_eq);
							equip_com.setAvailable_num_quant(available_num_quant);
							equip_com.setCreated_by(userid);
							equip_com.setCreated_date(date);
							equip_com.setInst_id(Integer.parseInt(institude));
							equip_com.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_fm_eq) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_anatomy= (Integer) sessionHQL.save(equip_com);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg="Data Save Successfully";
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS equip_com_u = (CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS.class, Integer.parseInt(hid_item_fm_eq));
								
								equip_com_u.setItem_id(Integer.parseInt(item_id_fm_eq));
								equip_com_u.setItem_name(item_name_fm_eq);
								equip_com_u.setAvailable_num_quant(available_num_quant);
								equip_com_u.setModified_by((userid));
								equip_com_u.setModified_date(date);
								sessionHQL.update(equip_com_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg="Data Update Successfully";
								
							}
						
						}
						
						
						
						Transaction tx = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS forensic_act =new CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS();
						
						for (int i = 0; i < item_list_forensic_eq1.size(); i++) {
						
							String item_id_fm_act = request.getParameter("item_id_fm_act"+item_list_forensic_eq1.get(i).get(0));
							String act_item_name = request.getParameter("act_item_name"+item_list_forensic_eq1.get(i).get(0));
							String act_available_num_quant = request.getParameter("act_available_num_quant"+item_list_forensic_eq1.get(i).get(0));
							String hid_item_fm_act = request.getParameter("hid_item_fm_act"+item_list_forensic_eq1.get(i).get(0));
							
							if (act_available_num_quant == null || act_available_num_quant.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_forensic_eq1.get(i).get(1));
								return "Please Enter Available(Number/Quantity)"+item_list_forensic_eq1.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(act_available_num_quant) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity)"+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
						
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							forensic_act.setItem_id(Integer.parseInt(item_id_fm_act));
							forensic_act.setAct_item_name(act_item_name);
							forensic_act.setAct_available_num_quant(act_available_num_quant);
							forensic_act.setCreated_by(userid);
							forensic_act.setCreated_date(date);
							forensic_act.setInst_id(Integer.parseInt(institude));
							forensic_act.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_fm_act) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_fm_act1= (Integer) sessionHQL.save(forensic_act);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS forensic_act1 = (CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS.class, Integer.parseInt(hid_item_fm_act));
								
								forensic_act1.setItem_id(Integer.parseInt(item_id_fm_act));
								forensic_act1.setAct_item_name(act_item_name);
								forensic_act1.setAct_available_num_quant(act_available_num_quant);
								forensic_act1.setModified_by((userid));
								forensic_act1.setModified_date(date);
								sessionHQL.update(forensic_act1);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Update Successfully";
								
							}
						}
						
						

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
					return  msg;
				}		
			
//FETCH Forensic_Equipment_Details================================================================
				
				@RequestMapping(value = "admin/getforensic_eq_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS> getforensic_eq_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 Foren=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS> clist = (List<CLG_REG_DEPT_EQUIPMENT_FM_EQUIPMENT_DETAILS>) q.list();
					System.err.println("q============1 Foren=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}
			
				
//FETCH Forensic_Acts================================================================
				
				@RequestMapping(value = "admin/getforensic_acts_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS> getforensic_acts_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 Foren=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS> clist = (List<CLG_REG_DEPT_EQUIPMENT_FM_ACTS_LEGISLATION_REGULATIONS>) q.list();
					System.err.println("q============1 Foren=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}
			
//SAVE Homeophathic Pharmacy-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Homeophatic_Pharmacy_Save_Draft_Action")
				public @ResponseBody String Homeophatic_Pharmacy_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String role = session.getAttribute("role").toString();
					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					String msg="";
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_pharmacy = dedao.getHomeophatic_Pharmacy_id();
					System.err.println("ABC-UG  4 APR----------------"+dedao.getHomeophatic_Pharmacy_id().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					

					try {
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT homeo_pharmacy =new CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT();
						
						for (int i = 0; i < item_list_pharmacy.size(); i++) {
							
							String item_id_ph = request.getParameter("item_id_ph"+item_list_pharmacy.get(i).get(0));
							String item_name_ph = request.getParameter("item_name_ph"+item_list_pharmacy.get(i).get(0));
							String available_num_quant_ph = request.getParameter("available_num_quant_ph"+item_list_pharmacy.get(i).get(0));
							String hid_item_ph = request.getParameter("hid_item_ph"+item_list_pharmacy.get(i).get(0));
							
							if (available_num_quant_ph == null || available_num_quant_ph.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_pharmacy.get(i).get(1));
								return "Please Enter Available(Number/Quantity)"+item_list_pharmacy.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_quant_ph) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity)"+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							homeo_pharmacy.setItem_id(Integer.parseInt(item_id_ph));
							homeo_pharmacy.setItem_name(item_name_ph);
							homeo_pharmacy.setAvailable_num_quant(available_num_quant_ph);
							homeo_pharmacy.setCreated_by(userid);
							homeo_pharmacy.setCreated_date(date);
							homeo_pharmacy.setInst_id(Integer.parseInt(institude));
							homeo_pharmacy.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_ph) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_hmph= (Integer) sessionHQL.save(homeo_pharmacy);
								sessionHQL.flush();
								sessionHQL.clear();
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT homeo_pharmacy1 = (CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT.class, Integer.parseInt(hid_item_ph));
								
								homeo_pharmacy1.setItem_id(Integer.parseInt(item_id_ph));
								homeo_pharmacy1.setItem_name(item_name_ph);
								homeo_pharmacy1.setAvailable_num_quant(available_num_quant_ph);
								homeo_pharmacy1.setModified_by((userid));
								homeo_pharmacy1.setModified_date(date);
								sessionHQL.update(homeo_pharmacy1);
								sessionHQL.flush();
								sessionHQL.clear();
								
							}
						
						}
						tx1.commit();
						
						Transaction tx = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD homeo_pharmacy_child =new CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD();
						
						String no_species_planted = request.getParameter("hergar_soecies");
						String no_pots = request.getParameter("hergar_plantpot");
						String hergarirrigationcheck = request.getParameter("hergarirrigationcheck");
						String hergar_edu_tourcheck = request.getParameter("hergar_edu_tourcheck");
						String hid_ph_child = request.getParameter("hid_ph_child");
						
						
						if (no_species_planted == null || no_species_planted.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Number of Species Planted.");
							return "Please Enter Number of Species Planted.";
						}
						
						if (validation.isOnlyNumer(no_species_planted) == true) {
							ra.addAttribute("msg", "Number of Species Planted" + validation.isOnlyNumerMSG);
							return "Species Planted" + validation.isOnlyNumerMSG;
					    }
						
						if (validation.maxlengthcheck(no_species_planted) == false) {
							ra.addAttribute("msg", "Number of Species Planted" + validation.MaxlengthcheckMSG10);
							return  "Number of Species Planted" + validation.MaxlengthcheckMSG10;
						}
						
						if (no_pots == null || no_pots.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Plants on Pots.");
							return "Please Enter Plants on Pots.";
						}
						
						if (validation.isAlphabetCDASH(no_pots) == false) {
							ra.addAttribute("msg", " Plants on Pots" + validation.isOnlyAlphabetMSG);
							return  " Plants on Pots" + validation.isOnlyAlphabetMSG;
						}
						
						if (validation.maxlengthcheck(no_pots) == false) {
							ra.addAttribute("msg", "Plants on Pots" + validation.MaxlengthcheckMSG);
							return  "Plants on Pots" + validation.MaxlengthcheckMSG;
						}
												
						UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
						String institude = String.valueOf(ea.getInstitute_id());	
						
						
						homeo_pharmacy_child.setNo_species_planted(no_species_planted);
						homeo_pharmacy_child.setNo_pots(no_pots);
						homeo_pharmacy_child.setIrrigation_facility(Integer.parseInt(hergarirrigationcheck));
						homeo_pharmacy_child.setEducational_tour_conducted(Integer.parseInt(hergar_edu_tourcheck));
						homeo_pharmacy_child.setCreated_by((userid));
						homeo_pharmacy_child.setCreated_date(date);
						homeo_pharmacy_child.setInst_id(Integer.parseInt(institude));
						homeo_pharmacy_child.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_ph_child) == 0) {
						//		constructed_area_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_ph_child1= (Integer) sessionHQL.save(homeo_pharmacy_child);
								sessionHQL.flush();
								sessionHQL.clear();
								//tx.commit();
								//return String.valueOf(hid_course_pg1) ;
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD homeo_pharmacy_child1 = (CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_CHILD.class, Integer.parseInt(hid_ph_child));
								
								homeo_pharmacy_child1.setNo_species_planted(no_species_planted);
								homeo_pharmacy_child1.setNo_pots(no_pots);
								homeo_pharmacy_child1.setIrrigation_facility(Integer.parseInt(hergarirrigationcheck));
								homeo_pharmacy_child1.setEducational_tour_conducted(Integer.parseInt(hergar_edu_tourcheck));
								homeo_pharmacy_child1.setModified_by((userid));
								homeo_pharmacy_child1.setModified_date(date);
								sessionHQL.update(homeo_pharmacy_child1);
								sessionHQL.flush();
								sessionHQL.clear();
							
								
								
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
					return  "Data Save Successfully";
				}				
	
//FETCH Homeophatic_Pharmacy================================================================
				
				@RequestMapping(value = "admin/getHomeophatic_Pharmacy_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT> getHomeophatic_Pharmacy_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 APR=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT> clist = (List<CLG_REG_DEPT_EQUIPMENT_HOMEOPATHIC_PHARMACY_PARENT>) q.list();
					System.err.println("q============1 APR=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}			
	
//SAVE Pathology_MicroBiology_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Pathology_MicroBiology_Save_Draft_Action")
				public @ResponseBody String Pathology_MicroBiology_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
//					String role = session.getAttribute("role").toString();
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					String msg="";
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_patho_micro = dedao.getPatho_microbioDetails();
					System.err.println("ABC-UG  5 Forensic----------------"+dedao.getAllCommunityMedicine_id().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					
					try {
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY path_mic =new CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY();
						
						for (int i = 0; i < item_list_patho_micro.size(); i++) {
//							
							
							String item_id_pm = request.getParameter("item_id_pm"+item_list_patho_micro.get(i).get(0));
							String item_name_pm = request.getParameter("item_name_pm"+item_list_patho_micro.get(i).get(0));
							String available_num_quant_pm = request.getParameter("available_num_quant_pm"+item_list_patho_micro.get(i).get(0));
							String hid_item_pm = request.getParameter("hid_item_pm"+item_list_patho_micro.get(i).get(0));
							
							if (available_num_quant_pm == null || available_num_quant_pm.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_patho_micro.get(i).get(1));
								return "Please Enter Available(Number/Quantity)"+item_list_patho_micro.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_quant_pm) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity)"+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							path_mic.setItem_id(Integer.parseInt(item_id_pm));
							path_mic.setItem_name(item_name_pm);
							path_mic.setAvailable_num_quant(available_num_quant_pm);
							path_mic.setCreated_by(userid);
							path_mic.setCreated_date(date);
							path_mic.setInst_id(Integer.parseInt(institude));
							path_mic.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_pm) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_patho_micro= (Integer) sessionHQL.save(path_mic);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg= "Data Save Successfully";
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY path_micro = (CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY.class, Integer.parseInt(hid_item_pm));
								
								path_micro.setItem_id(Integer.parseInt(item_id_pm));
								path_micro.setItem_name(item_name_pm);
								path_micro.setAvailable_num_quant(available_num_quant_pm);
								path_micro.setModified_by((userid));
								path_micro.setModified_date(date);
								sessionHQL.update(path_micro);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg= "Data Update Successfully";
								
							}
						
						}
					
						
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
					return msg;
				}						
				
				
//FETCH Pathology_MicroBiology_Details================================================================
				
				@RequestMapping(value = "admin/getPathology_MicroBiology_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY> getPathology_MicroBiology_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 APR=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY> clist = (List<CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_MICROBIOLOGY>) q.list();
					System.err.println("q============1 APR=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}	
				
//SAVE Pathology_Biochemistry_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Pathology_Biochemistry_Save_Draft_Action")
				public @ResponseBody String Pathology_Biochemistry_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
//					String role = session.getAttribute("role").toString();
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					String msg="";
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_patho_bio = dedao.getPatho_biochemDetails();
					System.err.println("ABC-UG  5 Forensic----------------"+dedao.getPatho_biochemDetails().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					
					try {
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY path_bio =new CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY();
						
						for (int i = 0; i < item_list_patho_bio.size(); i++) {
//							
							
							String item_id_pb = request.getParameter("item_id_pb"+item_list_patho_bio.get(i).get(0));
							String item_name_pb = request.getParameter("item_name_pb"+item_list_patho_bio.get(i).get(0));
							String available_num_quant_pb = request.getParameter("available_num_quant_pb"+item_list_patho_bio.get(i).get(0));
							String hid_item_pb = request.getParameter("hid_item_pb"+item_list_patho_bio.get(i).get(0));
							
							if (available_num_quant_pb == null || available_num_quant_pb.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_patho_bio.get(i).get(1));
								return "Please Enter Available(Number/Quantity)"+item_list_patho_bio.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_quant_pb) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity)"+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							path_bio.setItem_id(Integer.parseInt(item_id_pb));
							path_bio.setItem_name(item_name_pb);
							path_bio.setAvailable_num_quant(available_num_quant_pb);
							path_bio.setCreated_by(userid);
							path_bio.setCreated_date(date);
							path_bio.setInst_id(Integer.parseInt(institude));
							path_bio.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_pb) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_patho_micro= (Integer) sessionHQL.save(path_bio);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg="Data Save Successfully";
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY path_bioch = (CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY.class, Integer.parseInt(hid_item_pb));
								
								path_bioch.setItem_id(Integer.parseInt(item_id_pb));
								path_bioch.setItem_name(item_name_pb);
								path_bioch.setAvailable_num_quant(available_num_quant_pb);
								path_bioch.setModified_by((userid));
								path_bioch.setModified_date(date);
								sessionHQL.update(path_bioch);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg="Data Update Successfully";
								
							}
						
						}
						
						
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
					return  msg;
				}	
				
//FETCH Pathology_Biochemistry_Details================================================================
				
				@RequestMapping(value = "admin/getPathology_Biochemistry_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY> getPathology_Biochemistry_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 APR=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY> clist = (List<CLG_REG_DEPT_EQUIPMENT_PATHOLOGY_BIOCHEMISTRY>) q.list();
					System.err.println("q============1 APR=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}	
				
//SAVE Biochemistry_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Biochemistry_Save_Draft_Action")
				public @ResponseBody String Biochemistry_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
//					String role = session.getAttribute("role").toString();
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					String msg="";
					
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					ArrayList<ArrayList<String>> item_list_bio = dedao.get_biochemDetails();
					System.err.println("ABC-UG  5 Forensic----------------"+dedao.get_biochemDetails().size());

					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					
					
				//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					
					try {
						
						Transaction tx1 = sessionHQL.beginTransaction();
						CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY bio =new CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY();
						
						for (int i = 0; i < item_list_bio.size(); i++) {
//							
							
							String item_id_b = request.getParameter("item_id_b"+item_list_bio.get(i).get(0));
							String item_name_b = request.getParameter("item_name_b"+item_list_bio.get(i).get(0));
							String available_num_quant_b = request.getParameter("available_num_quant_b"+item_list_bio.get(i).get(0));
							String hid_item_b = request.getParameter("hid_item_b"+item_list_bio.get(i).get(0));
							
							if (available_num_quant_b == null || available_num_quant_b.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Available(Number/Quantity)"+item_list_bio.get(i).get(1));
								return "Please Enter Available(Number/Quantity)"+item_list_bio.get(i).get(1);
							}
							
							if (validation.isOnlyNumerandDotMSG(available_num_quant_b) == false) {
								ra.addAttribute("msg","Available(Number/ Quantity)"+ validation.isOnlyNumerandDotMSG);
								return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
							}
							
							UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
							String institude = String.valueOf(ea.getInstitute_id());
							
							bio.setItem_id(Integer.parseInt(item_id_b));
							bio.setItem_name(item_name_b);
							bio.setAvailable_num_quant(available_num_quant_b);
							bio.setCreated_by(userid);
							bio.setCreated_date(date);
							bio.setInst_id(Integer.parseInt(institude));
							bio.setP_id(Integer.parseInt(p_id));
							
							
							
							
							if (Integer.parseInt(hid_item_b) == 0) {
							//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
								int hid_item_bio= (Integer) sessionHQL.save(bio);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg="Data Save Successfully";
							}
							else {
								CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY bioch = (CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY.class, Integer.parseInt(hid_item_b));
								
								bioch.setItem_id(Integer.parseInt(item_id_b));
								bioch.setItem_name(item_name_b);
								bioch.setAvailable_num_quant(available_num_quant_b);
								bioch.setModified_by((userid));
								bioch.setModified_date(date);
								sessionHQL.update(bioch);
								sessionHQL.flush();
								sessionHQL.clear();
								tx1.commit();
								msg="Data Update Successfully";
								
							}
						
						}
						
						
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
					return  msg;
				}	
				
//FETCH Biochemistry_Details================================================================
				
				@RequestMapping(value = "admin/get_Biochemistry_data", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY> get_Biochemistry_data(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
			//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY where inst_id=:inst_id");
					
					//System.err.println("q============1 APR=="+q);
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					System.err.println("q============1 APR=="+q);
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY> clist = (List<CLG_REG_DEPT_EQUIPMENT_BIOCHEMISTRY>) q.list();
					System.err.println("q============1 APR=="+clist);
					tx.commit();
					sessionHQL.close();
					return clist;
				}	
				
//SAVE Practice_of_Medicine_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Practice_of_Medicine_Save_Draft_Action")
				public @ResponseBody String Practice_of_Medicine_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String pra_medicine_equide = request.getParameter("pra_medicine_equide");
					String pra_medicine_add_item = request.getParameter("pra_medicine_add_item");
					String hid_med = request.getParameter("hid_med");
					
					if (pra_medicine_equide == null || pra_medicine_equide.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Number of Equipments for Identification.");
						return "Please Enter Number of Equipments for Identification.";
					}
					
					if (validation.isOnlyNumer(pra_medicine_equide) == true) {
						ra.addAttribute("msg", " Number of Equipments for Identification" + validation.isOnlyNumerMSG);
						return " Number of Equipments for Identification" + validation.isOnlyNumerMSG;
				     }
					
					if (validation.maxlengthcheck10(pra_medicine_equide) == false) {
						ra.addAttribute("msg", " Number of Equipments for Identification" + validation.MaxlengthcheckMSG10);
						return  "Sources of Vitamins" + validation.MaxlengthcheckMSG10;
					}
					
					if (pra_medicine_add_item == null || pra_medicine_add_item.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Additional Items.");
						return "Please Enter Additional Items.";
					}
					
					if (validation.isAlphabetCDASH(pra_medicine_add_item) == false) {
						ra.addAttribute("msg", "Additional Items " + validation.isOnlyAlphabetMSG);
						return  "Additional Items " + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(pra_medicine_add_item) == false) {
						ra.addAttribute("msg", "Additional Items " + validation.MaxlengthcheckMSG);
						return  "Additional Items " + validation.MaxlengthcheckMSG;
					}
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_PRACTICE_MEDICINE sur =new CLG_REG_DEPT_EQUIPMENT_PRACTICE_MEDICINE();
						
						sur.setNo_equip_identi(pra_medicine_equide);
						sur.setAddi_item(pra_medicine_add_item);
						sur.setCreated_by(userid);
						sur.setCreated_date(date);
						sur.setInst_id(Integer.parseInt(institude));
						sur.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_med) == 0) {
								int hid_med1= (Integer) sessionHQL.save(sur);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_PRACTICE_MEDICINE sur_u = (CLG_REG_DEPT_EQUIPMENT_PRACTICE_MEDICINE) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_PRACTICE_MEDICINE.class, Integer.parseInt(hid_med));
								
								sur_u.setNo_equip_identi(pra_medicine_equide);
								sur_u.setAddi_item(pra_medicine_add_item);
								sessionHQL.update(sur_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Update Successfully";
								
							}
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
					return  msg;
				}
				
//SAVE Repertory_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Repertory_Save_Draft_Action")
				public @ResponseBody String Repertory_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String repertory_computer = request.getParameter("repertory_computer");
					String repertory_software = request.getParameter("repertory_software");
					String repertory_detail_lab = request.getParameter("repertory_detail_lab");
					String repertory_other_item = request.getParameter("repertory_other_item");
					String hid_rep = request.getParameter("hid_rep");
					
					if (repertory_computer == null || repertory_computer.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Number of Computers.");
						return "Please Enter Total Number of Computers.";
					}
					
					if (validation.isOnlyNumer(repertory_computer) == true) {
						ra.addAttribute("msg", "Total Number of Computers" + validation.isOnlyNumerMSG);
						return "Total Number of Computers" + validation.isOnlyNumerMSG;
				     }
					
					if (validation.maxlengthcheck10(repertory_computer) == false) {
						ra.addAttribute("msg", "Total Number of Computers" + validation.MaxlengthcheckMSG10);
						return  "Total Number of Computers" + validation.MaxlengthcheckMSG10;
					}
					
					if (repertory_software == null || repertory_software.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Number of Software.");
						return "Please Enter Total Number of Software.";
					}
					
					if (validation.isOnlyNumer(repertory_software) == true) {
						ra.addAttribute("msg", "Total Number of Software" + validation.isOnlyNumerMSG);
						return "Total Number of Software" + validation.isOnlyNumerMSG;
				     }
					
					if (validation.maxlengthcheck10(repertory_software) == false) {
						ra.addAttribute("msg", "Total Number of Software" + validation.MaxlengthcheckMSG10);
						return  "Total Number of Software" + validation.MaxlengthcheckMSG10;
					}
					
					if (repertory_detail_lab == null || repertory_detail_lab.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Details of Computer Lab.");
						return "Please Enter Number of Details of Computer Lab.";
					}
					
					if (validation.isAlphabetCDASH(repertory_detail_lab) == false) {
						ra.addAttribute("msg", "Details of Computer Lab " + validation.isOnlyAlphabetMSG);
						return "Details of Computer Lab " + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(repertory_detail_lab) == false) {
						ra.addAttribute("msg", "Details of Computer Lab " + validation.MaxlengthcheckMSG);
						return  "Details of Computer Lab " + validation.MaxlengthcheckMSG;
					}
					
					if (repertory_other_item == null || repertory_other_item.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Other Items.");
						return "Please Enter Other Items.";
					}
					
					if (validation.isAlphabetCDASH(repertory_other_item) == false) {
						ra.addAttribute("msg", " Other Items" + validation.isOnlyAlphabetMSG);
						return " Other Items" + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(repertory_other_item) == false) {
						ra.addAttribute("msg", " Other Items" + validation.MaxlengthcheckMSG);
						return " Other Items" + validation.MaxlengthcheckMSG;
					}
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_REPERTORY rep =new CLG_REG_DEPT_EQUIPMENT_REPERTORY();
						
						rep.setTotal_num_comp(repertory_computer);
						rep.setTotal_num_software(repertory_software);
						rep.setDetail_comp_lab(repertory_detail_lab);
						rep.setOther_item(repertory_other_item);
						rep.setCreated_by(userid);
						rep.setCreated_date(date);
						rep.setInst_id(Integer.parseInt(institude));
						rep.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_rep) == 0) {
								int hid_rep1= (Integer) sessionHQL.save(rep);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg= "Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_REPERTORY rep_u = (CLG_REG_DEPT_EQUIPMENT_REPERTORY) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_REPERTORY.class, Integer.parseInt(hid_rep));
								
								rep_u.setTotal_num_comp(repertory_computer);
								rep_u.setTotal_num_software(repertory_software);
								rep_u.setDetail_comp_lab(repertory_detail_lab);
								rep_u.setOther_item(repertory_other_item);
								sessionHQL.update(rep_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg= "Data Update Successfully";
								
							}
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
					return msg;
				}
				
//SAVE Surgery_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Surgery_Save_Draft_Action")
				public @ResponseBody String Surgery_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String surgery_equide = request.getParameter("surgery_equide");
					String surgery_item = request.getParameter("surgery_item");
					String hid_sur = request.getParameter("hid_sur");
					
					if (surgery_equide == null || surgery_equide.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Number of Equipments for Identification.");
						return "Please Enter Number of Equipments for Identification.";
					}
					
					if (validation.isOnlyNumer(surgery_equide) == true) {
						ra.addAttribute("msg", "Number of Equipments for Identification" + validation.isOnlyNumerMSG);
						return "Number of Equipments for Identification" + validation.isOnlyNumerMSG;
				     }
					
					if (validation.maxlengthcheck10(surgery_equide) == false) {
						ra.addAttribute("msg", "Number of Equipments for Identification" + validation.MaxlengthcheckMSG10);
						return  "Number of Equipments for Identification" + validation.MaxlengthcheckMSG10;
					}
					
					if (surgery_item == null || surgery_item.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Additional Items.");
						return "Please Enter Additional Items.";
					}
					
					if (validation.isAlphabetCDASH(surgery_item) == false) {
						ra.addAttribute("msg", "Additional Items " + validation.isOnlyAlphabetMSG);
						return "Additional Items " + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(surgery_item) == false) {
						ra.addAttribute("msg", "Additional Items" + validation.MaxlengthcheckMSG);
						return "Additional Items" + validation.MaxlengthcheckMSG;
					}
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_SURGERY sur =new CLG_REG_DEPT_EQUIPMENT_SURGERY();
						
						sur.setNo_equip_identi(surgery_equide);
						sur.setAddi_item(surgery_item);
						sur.setCreated_by(userid);
						sur.setCreated_date(date);
						sur.setInst_id(Integer.parseInt(institude));
						sur.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_sur) == 0) {
								int hid_sur1= (Integer) sessionHQL.save(sur);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_SURGERY sur_u = (CLG_REG_DEPT_EQUIPMENT_SURGERY) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_SURGERY.class, Integer.parseInt(hid_sur));
								
								sur_u.setNo_equip_identi(surgery_equide);
								sur_u.setAddi_item(surgery_item);
								sessionHQL.update(sur_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Update Successfully";
								
							}
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
					return  msg;
				}
				
//SAVE Homeophathy_Materia_Medica_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/HMM_Save_Draft_Action")
				public @ResponseBody String HMM_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String hommm_inter_charts = request.getParameter("hommm_inter_charts");
					String hommm_pic_wise_charts = request.getParameter("hommm_pic_wise_charts");
					String hommm_item = request.getParameter("hommm_item");
					String hid_hmm = request.getParameter("hid_hmm");
					
					if (hommm_inter_charts == null || hommm_inter_charts.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes.");
						return "Please Enter Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes.";
					}
					
					if (validation.isAlphabetCDASH(hommm_inter_charts) == false) {
						ra.addAttribute("msg", "Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes. " + validation.isOnlyAlphabetMSG);
						return "Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes." + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(hommm_inter_charts) == false) {
						ra.addAttribute("msg", "Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes." + validation.MaxlengthcheckMSG);
						return "Charts on medicines in Hom. Materia Medica interesting charts on Ophidia groups, Spider family, Nosodes and Sarcodes." + validation.MaxlengthcheckMSG;
					}
					
					if (hommm_pic_wise_charts == null || hommm_pic_wise_charts.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Number of Charts for Typical Picture-wise presentation of drugs.");
						return "Please Enter Total Number of Charts for Typical Picture-wise presentation of drugs.";
					}
					
					if (validation.isOnlyNumer(hommm_pic_wise_charts) == true) {
						ra.addAttribute("msg", " Total Number of Charts for Typical Picture-wise presentation of drugs." + validation.isOnlyNumerMSG);
						return "Total Number of Charts for Typical Picture-wise presentation of drugs." + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(hommm_pic_wise_charts) == false) {
						ra.addAttribute("msg", "Total Number of Charts for Typical Picture-wise presentation of drugs." + validation.MaxlengthcheckMSG10);
						return "Total Number of Charts for Typical Picture-wise presentation of drugs." + validation.MaxlengthcheckMSG10;
					}
					
					if (hommm_item == null || hommm_item.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Additional Items.");
						return "Please Enter Additional Items.";
					}
					
					if (validation.isAlphabetCDASH(hommm_item) == false) {
						ra.addAttribute("msg", "Additional Items " + validation.isOnlyAlphabetMSG);
						return "Additional Items " + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(hommm_item) == false) {
						ra.addAttribute("msg", "Additional Items" + validation.MaxlengthcheckMSG);
						return "Additional Items" + validation.MaxlengthcheckMSG;
					}
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_HOMEO_MATERIA_MEDICA hom_mm =new CLG_REG_DEPT_EQUIPMENT_HOMEO_MATERIA_MEDICA();
						
						hom_mm.setCharts(hommm_inter_charts);
						hom_mm.setTotal_charts(hommm_pic_wise_charts);
						hom_mm.setAddi_item(hommm_item);
						hom_mm.setCreated_by(userid);
						hom_mm.setCreated_date(date);
						hom_mm.setInst_id(Integer.parseInt(institude));
						hom_mm.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_hmm) == 0) {
								int hid_hmm1= (Integer) sessionHQL.save(hom_mm);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_HOMEO_MATERIA_MEDICA hom_mm_u = (CLG_REG_DEPT_EQUIPMENT_HOMEO_MATERIA_MEDICA) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_HOMEO_MATERIA_MEDICA.class, Integer.parseInt(hid_hmm));
								
								hom_mm_u.setCharts(hommm_inter_charts);
								hom_mm_u.setTotal_charts(hommm_pic_wise_charts);
								hom_mm_u.setAddi_item(hommm_item);
								sessionHQL.update(hom_mm_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Update Successfully";
								
							}
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
					return  msg;
				}
				
//SAVE Psychiatry_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Psychiatry_Save_Draft_Action")
				public @ResponseBody String Psychiatry_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String psychiatry_charts = request.getParameter("psychiatry_charts");
					String psychiatry_models = request.getParameter("psychiatry_models");
					String psychiatry_librarybook = request.getParameter("psychiatry_librarybook");
					String psychiatry_item = request.getParameter("psychiatry_item");
					String hid_py = request.getParameter("hid_py");
					
					if (psychiatry_charts == null || psychiatry_charts.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total number of Charts.");
						return "Please Enter Total number of Charts.";
					}
					
					if (validation.isOnlyNumer(psychiatry_charts) == true) {
						ra.addAttribute("msg", " Total number of Charts" + validation.isOnlyNumerMSG);
						return "Total number of Charts" + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(psychiatry_charts) == false) {
						ra.addAttribute("msg", "Total number of Charts" + validation.MaxlengthcheckMSG10);
						return "Total number of Charts" + validation.MaxlengthcheckMSG10;
					}
					
					if (psychiatry_models == null || psychiatry_models.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Number of Models.");
						return "Please Enter Total Number of Models.";
					}
					
					if (validation.isOnlyNumer(psychiatry_models) == true) {
						ra.addAttribute("msg", " Total number of Models" + validation.isOnlyNumerMSG);
						return "Total number of Models" + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(psychiatry_models) == false) {
						ra.addAttribute("msg", "Total number of Charts" + validation.MaxlengthcheckMSG10);
						return "Total number of Models" + validation.MaxlengthcheckMSG10;
					}
					
					if (psychiatry_librarybook == null || psychiatry_librarybook.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Number of books in Departmental Library.");
						return "Please Enter Number of books in Departmental Library.";
					}
					
					if (validation.isOnlyNumer(psychiatry_librarybook) == true) {
						ra.addAttribute("msg", " Number of books in Departmental Library" + validation.isOnlyNumerMSG);
						return "Number of books in Departmental Library" + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(psychiatry_librarybook) == false) {
						ra.addAttribute("msg", "Number of books in Departmental Library" + validation.MaxlengthcheckMSG10);
						return "Number of books in Departmental Library" + validation.MaxlengthcheckMSG10;
					}
					
					if (psychiatry_item == null || psychiatry_item.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Other Items.");
						return "Please Enter Other Items.";
					}
					
					if (validation.isAlphabetCDASH(psychiatry_item) == false) {
						ra.addAttribute("msg", "Other Items " + validation.isOnlyAlphabetMSG);
						return "Other Items" + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(psychiatry_item) == false) {
						ra.addAttribute("msg", "Other Items" + validation.MaxlengthcheckMSG);
						return "Other Items" + validation.MaxlengthcheckMSG;
					}
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_PSYCHIATRY psyc =new CLG_REG_DEPT_EQUIPMENT_PSYCHIATRY();
						
						psyc.setTotal_charts(psychiatry_charts);
						psyc.setTotal_models(psychiatry_models);
						psyc.setNum_depart_lib(psychiatry_librarybook);
						psyc.setOther_items(psychiatry_item);
						psyc.setCreated_by(userid);
						psyc.setCreated_date(date);
						psyc.setInst_id(Integer.parseInt(institude));
						psyc.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_py) == 0) {
								int hid_py1= (Integer) sessionHQL.save(psyc);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_PSYCHIATRY psyc_u = (CLG_REG_DEPT_EQUIPMENT_PSYCHIATRY) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_PSYCHIATRY.class, Integer.parseInt(hid_py));
								
								psyc_u.setTotal_charts(psychiatry_charts);
								psyc_u.setTotal_models(psychiatry_models);
								psyc_u.setNum_depart_lib(psychiatry_librarybook);
								psyc_u.setOther_items(psychiatry_item);
								sessionHQL.update(psyc_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Update Successfully";
								
							}
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
					return  msg;
				}
				
//SAVE Pediatrics_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Pediatrics_Save_Draft_Action")
				public @ResponseBody String Pediatrics_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra) throws ParseException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String pediatrics_charts = request.getParameter("pediatrics_charts");
					String pediatrics_models = request.getParameter("pediatrics_models");
					String pediatrics_librarybook = request.getParameter("pediatrics_librarybook");
					String pediatrics_item = request.getParameter("pediatrics_item");
					String hid_pd = request.getParameter("hid_pd");
					
					if (pediatrics_charts == null || pediatrics_charts.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total number of Charts.");
						return "Please Enter Total number of Charts.";
					}
					
					if (validation.isOnlyNumer(pediatrics_charts) == true) {
						ra.addAttribute("msg", " Total number of Charts" + validation.isOnlyNumerMSG);
						return "Total number of Charts" + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(pediatrics_charts) == false) {
						ra.addAttribute("msg", "Total number of Charts" + validation.MaxlengthcheckMSG10);
						return "Total number of Charts" + validation.MaxlengthcheckMSG10;
					}
					
					if (pediatrics_models == null || pediatrics_models.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Number of Models.");
						return "Please Enter Total Number of Models.";
					}
					
					if (validation.isOnlyNumer(pediatrics_models) == true) {
						ra.addAttribute("msg", " Total number of Models" + validation.isOnlyNumerMSG);
						return "Total number of Models" + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(pediatrics_models) == false) {
						ra.addAttribute("msg", "Total number of Models" + validation.MaxlengthcheckMSG10);
						return "Total number of Models" + validation.MaxlengthcheckMSG10;
					}
					
					if (pediatrics_librarybook == null || pediatrics_librarybook.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Number of books in Departmental Library.");
						return "Please Enter Number of books in Departmental Library.";
					}
					
					if (validation.isOnlyNumer(pediatrics_librarybook) == true) {
						ra.addAttribute("msg", " Number of books in Departmental Library" + validation.isOnlyNumerMSG);
						return "Number of books in Departmental Library" + validation.isOnlyNumerMSG;
				    }
					
					if (validation.maxlengthcheck10(pediatrics_librarybook) == false) {
						ra.addAttribute("msg", "Number of books in Departmental Library" + validation.MaxlengthcheckMSG10);
						return "Number of books in Departmental Library" + validation.MaxlengthcheckMSG10;
					}
					
					if (pediatrics_item == null || pediatrics_item.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Other Items.");
						return "Please Enter Other Items.";
					}
					
					if (validation.isAlphabetCDASH(pediatrics_item) == false) {
						ra.addAttribute("msg", "Other Items " + validation.isOnlyAlphabetMSG);
						return "Other Items" + validation.isOnlyAlphabetMSG;
					}
					
					if (validation.maxlengthcheck(pediatrics_item) == false) {
						ra.addAttribute("msg", "Other Items" + validation.MaxlengthcheckMSG);
						return "Other Items" + validation.MaxlengthcheckMSG;
					}
					
//					String hid_guest_faculty = request.getParameter("hid_guest_faculty"+indno);
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_PEDIATRICS ped =new CLG_REG_DEPT_EQUIPMENT_PEDIATRICS();
						
						ped.setTotal_charts(pediatrics_charts);
						ped.setTotal_models(pediatrics_models);
						ped.setNum_depart_lib(pediatrics_librarybook);
						ped.setOther_items(pediatrics_item);
						ped.setCreated_by(userid);
						ped.setCreated_date(date);
						ped.setInst_id(Integer.parseInt(institude));
						ped.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_pd) == 0) {
								int hid_pd1= (Integer) sessionHQL.save(ped);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_PEDIATRICS ped_u = (CLG_REG_DEPT_EQUIPMENT_PEDIATRICS) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_PEDIATRICS.class, Integer.parseInt(hid_pd));
								
								ped_u.setTotal_charts(pediatrics_charts);
								ped_u.setTotal_models(pediatrics_models);
								ped_u.setNum_depart_lib(pediatrics_librarybook);
								ped_u.setOther_items(pediatrics_item);
								sessionHQL.update(ped_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Update Successfully";
								
							}
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
					return  msg;
				}
				
//SAVE Organon_of_Medicine_Details-----------------------------------------------------------------------------------------------------------
				
				@PostMapping(value = "/Organon_of_Medicine_Save_Draft_Action")
				public @ResponseBody String Organon_of_Medicine_Save_Draft_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
						RedirectAttributes ra,String hid_camera) throws ParseException, IOException {

//					if(request.getHeader("Referer") == null ) { 
//						 session.invalidate();
//						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return  "redirect:/login";
//					 }
					String msg="";
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					
					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for(int i=1; i <= count_hidden_att; i++) {
						
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					
					String p_id = dedao.getpid_from_userid_anatomy(userid).get(0).get(0);
					
					String founder_name = request.getParameter("founder_name"+i);
					String date_of_birth = request.getParameter("date_of_birth"+i);
					String date_of_death = request.getParameter("date_of_death"+i);
					String avail_num = request.getParameter("avail_num"+i);
					String upload_img_hid = request.getParameter("upload_img_hid"+i);
					MultipartFile photo_path = mul.getFile("photo_path"+i);
					
					String hid_or_med = request.getParameter("hid_or_med");
					
					String photo_path_att = "";
					if (photo_path != null && !photo_path.isEmpty()) {
						photo_path_att = common.fileupload(photo_path.getBytes(), photo_path.getOriginalFilename(),
								"UploadHardCopy1");
					} else {
						photo_path_att = upload_img_hid;
					}
					
					if (founder_name == null || founder_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Founder & Philosophers Name.");
						return "Please Enter Total Number of Models.";
					}
					
					if (validation.maxlengthcheck50(avail_num) == false) {
						ra.addAttribute("msg", "Total number of Models" + validation.MaxlengthcheckMSG50);
						return "Total number of Models" + validation.MaxlengthcheckMSG50;
					}
					
					if (validation.isAlphabetCDASH(founder_name) == false) {
						ra.addAttribute("msg", "Founder & Philosophers Name" + validation.isOnlyAlphabetMSG);
						return "Founder & Philosophers Name" + validation.isOnlyAlphabetMSG;
					}
					
					if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", " Please Enter Date of Birth." + i);
						return "Please Enter Date of Birth.";
					}
					
					if (datePickerFormat.parse(date_of_birth).compareTo(datePickerFormat.parse(date_of_birth)) < 0) {
						ra.addAttribute("msg", "Date of Death Should Be Less Than Date of Birth. " + i);
						return "Date of Death Should Be Less Than Date of Birth. ";
					}
					
					if (date_of_death == null || date_of_death.trim().equals("") || date_of_death.equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Enter Date of Death" + i);
						return "Please Enter Date of Death.";
					}
					
					if (datePickerFormat.parse(date_of_death).compareTo(datePickerFormat.parse(date_of_death)) < 0) {
						ra.addAttribute("msg", "Date of Death Should Be Greater Than Date of Birth. " + i);
						return "Date of Death Should Be Greater Than Date of Birth. ";
					}
					
					if (avail_num == null || avail_num.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Available(Number/ Quantity).");
						return "Please Enter Available(Number/ Quantity).";
					}
					
					if (validation.isOnlyNumerandDotMSG(avail_num) == false) {
						ra.addAttribute("msg","Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG);
						return "Available(Number/ Quantity) "+ validation.isOnlyNumerandDotMSG;
					}
					
					if (validation.maxlengthcheck10(avail_num) == false) {
						ra.addAttribute("msg", "Total number of Models" + validation.MaxlengthcheckMSG10);
						return "Total number of Models" + validation.MaxlengthcheckMSG10;
					}
					
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					try {
						CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE om =new CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE();
						
						om.setFounder_philoso_name(founder_name);
						om.setDate_of_birth(datePickerFormat.parse(date_of_birth));
						om.setDate_of_death(datePickerFormat.parse(date_of_death));
						om.setAvailable_num_quant(avail_num);
						om.setPhoto_path(photo_path_att);
						om.setCreated_by(userid);
						om.setCreated_date(date);
						om.setInst_id(Integer.parseInt(institude));
						om.setP_id(Integer.parseInt(p_id));
						
							if (Integer.parseInt(hid_or_med) == 0) {
								int hid_or_med1= (Integer) sessionHQL.save(om);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Save Successfully";
						}
							else {

								CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE om_u = (CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE) sessionHQL
										.get(CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE.class, Integer.parseInt(hid_or_med));
								
								om_u.setFounder_philoso_name(founder_name);
								om_u.setDate_of_birth(datePickerFormat.parse(date_of_birth));
								om_u.setDate_of_death(datePickerFormat.parse(date_of_death));
								om_u.setAvailable_num_quant(avail_num);
								om_u.setPhoto_path(photo_path_att);
								sessionHQL.update(om_u);
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								msg="Data Updated Successfully";
								
							}
							
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
					}
					return  msg;
				}
				
				
//ADD MORE FETCH FOR Organon_of_Medicine_Details------------------------------------------------------------------------------------
				@RequestMapping(value = "admin/getAdd_More_Organon_of_Medicine_Details", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE> getAdd_More_Organon_of_Medicine_Details(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institude = String.valueOf(ea.getInstitute_id());
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL
							.createQuery("from CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE where inst_id=:inst_id ");
					
					q.setParameter("inst_id", Integer.parseInt(institude));
					
					@SuppressWarnings("unchecked")
					List<CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE> clist = (List<CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE>) q.list();
				
					tx.commit();
					sessionHQL.close();
					return clist;
				}
				
				
				
				
//ADD MORE DELETE FOR Organon_of_Medicine_Details----------------------------------------------------------------------------
				@PostMapping(value = "/delete_Organon_of_Medicine_Details")
				public @ResponseBody String delete_Organon_of_Medicine_Details(String hid_library,HttpSession session1) {

					String msg="";

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					System.err.println("doc_attach_id===================="+hid_library);
					try {
						String hqlUpdate = "delete from CLG_REG_DEPT_EQUIPMENT_ORGANON_MEDICINE where id=:id";
						int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_library)).executeUpdate();
						tx.commit();
						session.close();
						if (app > 0) {
							msg = "Data Deleted Successfully.";
						} else {
							msg = "Data not Deleted.";
						}
					} catch (Exception e) {
						msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
					}finally {
						
					}
					return msg;
				}

}
