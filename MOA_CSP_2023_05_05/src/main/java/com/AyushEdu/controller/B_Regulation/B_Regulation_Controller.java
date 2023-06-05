package com.AyushEdu.controller.B_Regulation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.B_Regulation.EDU_B_REGULATION_HOSPITAL_CHILD;
import com.AyushEdu.Models.B_Regulation.EDU_B_REGULATION_MEDICAL_DEGREE_CHILD;
import com.AyushEdu.Models.B_Regulation.TB_EDU_B_REGULATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;

import com.AyushEdu.controller.Regulation.ExcelUserListReportView;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.B_Regulation.Edu_B_RegDao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class B_Regulation_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Edu_B_RegDao BRdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/B_Regulation_Url", method = RequestMethod.GET)
	public ModelAndView B_Regulation_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		//SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("B_Regulation_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		//System.err.println(common.getMedNationality(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
//		 Mmap.put("MedStateName", common.getInstituteList(sessionFactory));
		
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		
		try {
			int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			 
			if (data != 0) {
				Mmap.put("setDataCMD", BRdao.getDataByUserNameForDraft(data));
				Mmap.put("setAddMoreMedicalCMD", BRdao.medicalData(data));
				Mmap.put("setAddMoreHospCMD", BRdao.HospitalData(data));
				Mmap.put("CheckNRH", BRdao.CheckNRH(data));
				Mmap.put("setRegAuth", BRdao.RegAuth(data));
				String pid = BRdao.getUserId(data);
				Mmap.put("p_id", pid);
				
//				--20_06_2022 urmik
				System.out.println("p iddd--jjjjjjjjjjj------="+pid);
				
				//Mmap.put("p_id", pid);
				
				if (pid != null && !pid.trim().equals("")) {
					int data2 = Integer.parseInt(pid);
					TB_EDU_B_REGULATION INF = (TB_EDU_B_REGULATION) sessionHQ.get(TB_EDU_B_REGULATION.class, data2);
System.err.println("check the data"+data2);
					if (INF.getStatus() == 0) {
						System.out.println("1--------="+pid);
						
						Mmap.put("hid", "2");

					} else if (INF.getStatus() == 1) {
						System.out.println("2--------="+pid);
						Mmap.put("hid", "1");
					} else {
						System.out.println("3--------="+pid);
						Mmap.put("hid", "0");
 					}
					
//					Mmap.put("p_id", pid);
					
				} else {
					System.out.println("4--------="+pid);
					Mmap.put("p_id", "0");

				}

			} else {
				Mmap.put("setData", "0");
				Mmap.put("hid", "0");
				Mmap.put("p_id", "0");

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));

		// HET CHANGES

//	    ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(0,0,"","active");
//		 Mmap.put("list", list);
		
		return new ModelAndView("b_regulation_Tiles");
	}
	
	
	@PostMapping(value = "/b_Regulation_Action")
	public ModelAndView b_Regulation_Action(@Validated @ModelAttribute("b_RegulationCMD") TB_EDU_B_REGULATION td,
			BindingResult result, HttpServletRequest request, MultipartHttpServletRequest mul, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra) throws IOException, ParseException {

		//SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("B_Regulation_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		String username = session.getAttribute("username").toString();

		String dob1 = request.getParameter("dob");
		Date dob = null;
		if (!dob1.equals("DD/MMM/YYYY") && !dob1.equals("DD/MM/YYYY")) {
			dob = formate.parse(dob1);
		}
		String saveDraft = request.getParameter("SaveDraft");

//		String date_of_reg1 = request.getParameter("date_of_reg");
//		Date date_of_reg = null;
//		if (!date_of_reg1.equals("DD/MM/YYYY")) {
//			date_of_reg = formate.parse(date_of_reg1);
//		}
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		Session sessionHQ = this.sessionFactory.openSession();
		String adhar = request.getParameter("aadhaar_no");
		// BigInteger mo_no = new BigInteger(request.getParameter("mo_no"));
		// BigInteger alt_mo_no = new BigInteger(request.getParameter("alt_mo_no"));

		String reg_no = request.getParameter("reg_no");
		String pract_status = request.getParameter("Regulationstatus");
		String NRHstatus = request.getParameter("NRHstatus");

		String photo_path = gd(request, mul, session, "photo_path");
		String upload_img_hid = request.getParameter("upload_img_hid");
		String first_name = request.getParameter("first_name");
		String father_name = request.getParameter("father_name");
		String email_id = request.getParameter("email_id").trim();
		String alterEmail_id1 = request.getParameter("alt_email_id1");
		String alterEmail_id2 = request.getParameter("alt_email_id2");
		String nationality = request.getParameter("nationality");
//					
		String per_address1 = request.getParameter("per_address");
		String per_address2 = request.getParameter("per_address2");
		String per_address3 = request.getParameter("per_address3");

		String pre_address1 = request.getParameter("pre_address");
		String pre_address2 = request.getParameter("pre_address2");
		String pre_address3 = request.getParameter("pre_address3");

		String per_state = request.getParameter("per_state");
		String per_district = request.getParameter("per_district");
		String per_pincode = request.getParameter("per_pincode");
		String pre_state = request.getParameter("pre_state");
		String pre_district = request.getParameter("pre_district");
		String pre_pincode = request.getParameter("pre_pincode");

		String parent_id = request.getParameter("p_id");
	
		String reg_auth = request.getParameter("reg_auth");

		System.out.println("check the parent id ---------"+parent_id);
		String valid_up_to = request.getParameter("valid_up_to");
		BigInteger mo_no = BigInteger.ZERO;
		BigInteger alt_mo_no1 = BigInteger.ZERO;
		BigInteger alt_mo_no2 = BigInteger.ZERO;
		int id = td.getId() > 0 ? td.getId() : 0;

		if (!request.getParameter("mo_no").equals("")) {
			mo_no = new BigInteger(request.getParameter("mo_no"));
		}

		if (!request.getParameter("alt_mo_no1").equals("")) {
			alt_mo_no1 = new BigInteger(request.getParameter("alt_mo_no1"));
		}

		if (!request.getParameter("alt_mo_no2").equals("")) {
			alt_mo_no2 = new BigInteger(request.getParameter("alt_mo_no2"));
		}

		if (saveDraft != null && saveDraft.equals("1")) {

			/* personal details */
			if (upload_img_hid == null || upload_img_hid.trim().equals("")) {
				ra.addAttribute("msg", "Please Select Photo");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (first_name == null || first_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter First Name");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (father_name == null || father_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Father Name");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (adhar == null || adhar.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Aadhar Number");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (mo_no.equals("0")) {
				ra.addAttribute("msg", "Please Enter Mobile Number");
				return new ModelAndView("redirect:B_Regulation_Url");
			}

			if (email_id == null || email_id.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Id");
				return new ModelAndView("redirect:B_Regulation_Url");
			}

			if (dob == null || dob.equals("0")) {
				ra.addAttribute("msg", "Please Enter Date Of Birth");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (nationality.equals("0")) {
				ra.addAttribute("msg", "Please Enter Nationality");
				return new ModelAndView("redirect:B_Regulation_Url");
			}

			/* address details */

			if (per_address1 == null || per_address1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address Line 1");
				return new ModelAndView("redirect:B_Regulation_Url");
			}

			if (per_state.equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent State");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (per_district.equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent District");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (per_pincode == null || per_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Pincode");
				return new ModelAndView("redirect:B_Regulation_Url");
			}

//			if (pre_address == null || pre_address.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Present Address Line 1");
//				return new ModelAndView("redirect:B_Regulation_Url");
//			}
			if (pre_state.equals("0")) {
				ra.addAttribute("msg", "Please Select Present State");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (pre_district.equals("0")) {
				ra.addAttribute("msg", "Please Select Present District");
				return new ModelAndView("redirect:B_Regulation_Url");
			}
			if (pre_pincode == null || pre_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Present Pincode");
				return new ModelAndView("redirect:B_Regulation_Url");
			}

			if (photo_path.trim().equals("")) {
				photo_path = upload_img_hid;
			}

		}

//		MultipartFile attachment2 = mul.getFile("photo_first_cer_path");
//		String photo_first_cer_path = common.fileupload(attachment2.getBytes(), attachment2.getOriginalFilename(),
//				"UploadHardCopy1");

		// getUSERID
		try {
			int data = (int) sessionHQL.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();

			if (data != 0) {
				td.setUser_id(data);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			sessionHQL.flush();
			sessionHQL.clear();
//			sessionHQL.close();
//			tx.commit();
		}

		//try {
//			Long c = (Long) sessionHQL.createQuery(
//					"select count(id) from  TB_EDU_B_REGULATION where upper(nrh_en_no)=:nrh_en_no and upper(first_name)=:first_name  and id !=:id")
//					.setParameter("nrh_en_no", td.getNrh_en_no().toUpperCase())
//					.setParameter("first_name", td.getFirst_name().toUpperCase()).setParameter("father_name", td.getFather_name().toUpperCase()).setParameter("id", id).uniqueResult();

			if (Integer.parseInt(parent_id) == 0) {
				td.setCreated_by(username);
				td.setCreated_date(new Date());
//				if (c == 0) {
				td.setFirst_name(first_name);
				
				td.setAadhaar_no((adhar));
				td.setMo_no((mo_no));
				if (per_pincode != null && !per_pincode.equals("")) {
					BigInteger perpincode = BigInteger.ZERO;
					perpincode = new BigInteger(per_pincode);

					td.setPer_pincode(perpincode);

				}
				if (pre_pincode != null && !pre_pincode.equals("")) {
					BigInteger prepincode = BigInteger.ZERO;
					prepincode = new BigInteger(pre_pincode);

					td.setPre_pincode(prepincode);

				}
				if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
					td.setAlt_mo_no1(alt_mo_no1);

				}
				if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
					td.setAlt_mo_no2(alt_mo_no2);

				}


				if (alterEmail_id1 != null && !alterEmail_id1.equals("")) {
					td.setAlt_email_id1(alterEmail_id1);

				}
				if (alterEmail_id2 != null && !alterEmail_id2.equals("")) {
					td.setAlt_email_id2(alterEmail_id2);

				}

				if (reg_no != null && !reg_no.equals("")) {
					td.setReg_no(Integer.parseInt(reg_no));

				}
				if (pract_status != null && !pract_status.equals("") && pract_status.equals("0") && NRHstatus != null
						&& NRHstatus.equals("0")) {
					td.setStatus(0);
					td.setInstitute_status(-1);
					td.setState_status(-1);
					td.setNrh_status(-1);

				} else if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0")
						&& NRHstatus != null && NRHstatus.equals("0")) {
					td.setStatus(1);
					td.setInstitute_status(0);
					td.setState_status(-1);
					td.setNrh_status(-1);

				}

				else if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0")
						&& NRHstatus != null && !NRHstatus.equals("0")) {
					ra.addAttribute("hid", "1");
					td.setStatus(1);
					td.setInstitute_status(1);
					td.setState_status(0);
					td.setNrh_status(-1);

				}

//				if (per_address1 != null || !per_address1.trim().equals("")) {
//				if (photo_first_cer_path != "") {
//					td.setPhoto_first_cer_path(photo_first_cer_path);
//				}

				td.setReg_auth(reg_auth);
//				td.setRegistration_for_type(Integer.parseInt(registration_for_type));
				td.setValid_up_to(valid_up_to);
				td.setPre_address_details1(pre_address1);
				td.setPre_address_details2(pre_address2);
				td.setPre_address_details3(pre_address3);
				td.setPer_address_details1(per_address1);
				td.setPer_address_details2(per_address2);
				td.setPer_address_details3(per_address3);
				
//				21-06-2022 urmik changes
				
				
				td.setPer_district(Integer.parseInt(per_district));
				td.setPer_state(Integer.parseInt(per_state));
				td.setPre_district(Integer.parseInt(pre_district));
				td.setPre_state(Integer.parseInt(pre_state));
//				}
				td.setPhoto_path(photo_path);
//				td.setDate_of_reg(date_of_reg);
				td.setDel_status(0);
				int p_id = (int) sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();

				if (p_id > 0) {
					// HET CHANGES
					int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));

					for (int j = 1; j <= count_hidden_att_name_med; j++) {

						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String DegreeName = request.getParameter("DegreeName" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						MultipartFile attachment = mul.getFile("attachment" + j);
						
			//SECURITY-----
						if (attachment.getOriginalFilename().isEmpty()) {
							ra.addAttribute("msg","Please Upload File");
							return new ModelAndView("redirect:B_Regulation_Url");
						}
						if (!attachment.getOriginalFilename().isEmpty()) {
							 if (attachment.getOriginalFilename().split("[.]").length > 2) {
								 ra.addAttribute("msg", "Invalid file extension for Document");
									return new ModelAndView("redirect:AlumniSignup_Url");
							}
							
							
							
							String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
							if (!upload_fileEXt.equals("pdf")) {
								ra.addAttribute("msg","Please Upload File of .pdf extention");
								return new ModelAndView("redirect:B_Regulation_Url");
							}
							long filesize = attachment.getSize() / 1024;
							if (filesize > 200) {
								ra.addAttribute("msg","File size should be 200 kb or less");
								return new ModelAndView("redirect:B_Regulation_Url");
							}
						}
				//SECURITY-----

						EDU_B_REGULATION_MEDICAL_DEGREE_CHILD ec = new EDU_B_REGULATION_MEDICAL_DEGREE_CHILD();
						// HET VALIDATION
//			        	 if ( typeOfDegree == null || typeOfDegree.equals("0")) {
//								model.put("msg", "Please Select Type Of Degree");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						} 
//			        	 if ( monthYearOfDegree == null || monthYearOfDegree.equals("") || monthYearOfDegree.equals("dd/mm/yyyy")) {
//								model.put("msg", "Please Enter Month Year Of Degree");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						} 
//			        	 if ( NameOfUniversity == null || NameOfUniversity.equals("")) {
//								model.put("msg", "Please Enter Name Of University");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						}
//						if (attachment == null || attachment.equals("")) {
//						model.put("msg", "Please Upload Attachment");
//						return new ModelAndView("redirect:B_Regulation_Url");
//					}

						if (!attachment.isEmpty()) {
							String pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(),
									"UploadHardCopy1");
							if (pic != "") {
								ec.setAttachment_path(pic);
							}
						}
						ec.setRegulation_p_id(p_id);

						if (typeOfDegree != null && !typeOfDegree.equals("")) {
							ec.setType_of_degree(Integer.parseInt(typeOfDegree));
						}
						if (DegreeName != null && !DegreeName.equals("")) {
							ec.setDegree_name(Integer.parseInt(DegreeName));
						}
						ec.setName_of_institute(NameOfUniversity);
						ec.setMonth_and_year_of_degree(monthYearOfDegree);

						ec.setCreated_date(new Date());
						ec.setCreated_by(username);
						ec.setMonth_and_year_of_degree(monthYearOfDegree);

						sessionHQL.save(ec);
						sessionHQL.flush();
						sessionHQL.clear();

					}
				}
				
				if (p_id > 0) {
				
					

						int count_hidden_att_Hospital = Integer
								.parseInt(request.getParameter("count_hidden_att_Hospital"));

						for (int j = 1; j <= count_hidden_att_Hospital; j++) {

							String place_of_working = request.getParameter("place_of_working" + j);
							String place_of_working_name = request.getParameter("place_of_working_name" + j);

							String landline = request.getParameter("landline" + j);
							
							String address1 = request.getParameter("hos_address1_" + j);
							String address2 = request.getParameter("hos_address2_" + j);
							String address3 = request.getParameter("hos_address3_" + j);

							String hos_state = request.getParameter("hos_state" + j);
							String hos_district = request.getParameter("hos_district" + j);
							String email = request.getParameter("email" + j);

							String authority_type = request.getParameter("authority_type" + j);
							String name_of_res_p = request.getParameter("name_of_res_p" + j);


							String mobile_no = request.getParameter("mobileHosp" + j);

							EDU_B_REGULATION_HOSPITAL_CHILD Hc = new EDU_B_REGULATION_HOSPITAL_CHILD();

							// HET VALIDATION
//		        	 if ( place_of_working == null || place_of_working.equals("0")) {
//							model.put("msg", "Please Select Place of Working");
//							return new ModelAndView("redirect:B_Regulation_Url");
//					} 
//		        	 if ( landline == null || landline.equals("")) {
//							model.put("msg", "Please Enter Landline No");
//							return new ModelAndView("redirect:B_Regulation_Url");
//					} 
//		        	 if ( address == null || address.equals("")) {
//							model.put("msg", "Please Enter Address");
//							return new ModelAndView("redirect:B_Regulation_Url");
//					}
//					if (email == null || email.equals("")) {
//					model.put("msg", "Please Enter Email");
//					return new ModelAndView("redirect:B_Regulation_Url");
//				}
//					if (authority_type == null || authority_type.equals("")) {
//						model.put("msg", "Please Enter Authority Type");
//						return new ModelAndView("redirect:B_Regulation_Url");
//					}if (name_of_res_p == null || name_of_res_p.equals("")) {
//						model.put("msg", "Please Enter Name of Responsible Person Name");
//						return new ModelAndView("redirect:B_Regulation_Url");
//					}

							Hc.setMobile_no(mobile_no);


							Hc.setPlace_of_working_name(place_of_working_name);
							Hc.setHos_address1(address1);
							Hc.setHos_address2(address2);
							Hc.setHos_address3(address3);

							if (hos_state != null && !hos_state.equals("")) {
								Hc.setHos_state(Integer.parseInt(hos_state));
							}

							if (hos_district != null && !hos_district.equals("")) {
								Hc.setHos_district(Integer.parseInt(hos_district));
							}

							Hc.setRegulation_p_id(p_id);

							if (place_of_working != null && !place_of_working.equals("")) {
								Hc.setPlace_of_working(Integer.parseInt(place_of_working));
							}

							Hc.setLandline(landline);

							Hc.setEmail(email);

							if (authority_type != null && !authority_type.equals("")) {
								Hc.setAuthority_type(Integer.parseInt(authority_type));
							}

							Hc.setName_of_res_p(name_of_res_p);

							Hc.setModified_date(new Date());
							Hc.setModified_by(username);

							sessionHQL.save(Hc);
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
				
 
				tx.commit();
				ra.addAttribute("msg", "Saved Successfully.");

			} else {

				if (Integer.parseInt(parent_id) != 0) {
//					TB_EDU_B_REGULATION EE2= (TB_EDU_B_REGULATION)sessionHQL.get(TB_B_EDU_REGULATION.class, parent_id);
					td.setId(Integer.parseInt(parent_id));
					//System.err.println("=====" + td.getId());
					td.setModified_by(username);
					td.setModified_date(new Date());
//				if (c == 0) {
					td.setFirst_name(first_name);
					td.setAadhaar_no((adhar));
					td.setMo_no((mo_no));

					
					if (per_pincode != null && !per_pincode.equals("")) {
						BigInteger perpincode = BigInteger.ZERO;
						perpincode = new BigInteger(per_pincode);

						td.setPer_pincode(perpincode);

					}
					
					if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
						
						td.setAlt_mo_no1(alt_mo_no1);

					}
					System.err.println("==pppp===" + td.getAlt_mo_no1());
					if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
						td.setAlt_mo_no2(alt_mo_no2);

					}
//					if (photo_first_cer_path != "") {
//						td.setPhoto_first_cer_path(photo_first_cer_path);
//					}
//					if (photo_first_cer_path != null && !photo_first_cer_path.trim().equals("")  &&  photo_path.trim().equals("")) {
//						photo_path = upload_img_hid;
//					}

					td.setReg_auth(reg_auth);
//					td.setRegistration_for_type(Integer.parseInt(registration_for_type));
					td.setValid_up_to(valid_up_to);
//				td.setAlt_mo_no1((alt_mo_no1));
//				td.setAlt_mo_no2((alt_mo_no2));
					if (pre_pincode != null && !pre_pincode.equals("")) {
						BigInteger prepincode = BigInteger.ZERO;
						prepincode = new BigInteger(pre_pincode);

						td.setPre_pincode(prepincode);

					}
					if (alterEmail_id1 != null && !alterEmail_id1.equals("")) {
						td.setAlt_email_id1(alterEmail_id1);

					}
					if (alterEmail_id2 != null && !alterEmail_id2.equals("")) {
						td.setAlt_email_id2(alterEmail_id2);

					}

					if (reg_no != null && !reg_no.equals("")) {
						td.setReg_no(Integer.parseInt(reg_no));

					}
					//System.err.println("abcdaaaaaaa===="+pract_status+"======="+NRHstatus);

					if (pract_status != null && !pract_status.equals("") && pract_status.equals("0")
							&& NRHstatus != null && NRHstatus.equals("0")) {
						td.setStatus(0);
						td.setInstitute_status(-1);
						td.setState_status(-1);
						td.setNrh_status(-1);

					} else if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0")
							&& NRHstatus != null && NRHstatus.equals("0")) {
						td.setStatus(1);
						td.setInstitute_status(0);
						td.setState_status(-1);
						td.setNrh_status(-1);

					}

					else if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0")
							&& NRHstatus != null && !NRHstatus.equals("0")) {
						ra.addAttribute("hid", "1");
						td.setStatus(1);
						td.setInstitute_status(1);
						td.setState_status(0);
						td.setNrh_status(-1);

					}

//				if (per_address1 != null || !per_address1.trim().equals("")) {
 					td.setReg_auth(reg_auth);
//					td.setRegistration_for_type(Integer.parseInt(registration_for_type));
					td.setValid_up_to(valid_up_to);
					td.setPre_address_details1(pre_address1);
					td.setPre_address_details2(pre_address2);
					td.setPre_address_details3(pre_address3);
					td.setPer_address_details1(per_address1);
					td.setPer_address_details2(per_address2);
					td.setPer_address_details3(per_address3);
					
//					21-06-2022 urmik changes
					
					
					td.setPer_district(Integer.parseInt(per_district));
					td.setPer_state(Integer.parseInt(per_state));
					td.setPre_district(Integer.parseInt(pre_district));
					td.setPre_state(Integer.parseInt(pre_state));
					
//				}
					td.setPhoto_path(photo_path);
//					td.setDate_of_reg(date_of_reg);
					td.setDel_status(0);
					sessionHQL.update(td);
					sessionHQL.flush();
					sessionHQL.clear();
					int p_id = td.getId();
					if (p_id > 0) {
						// HET CHANGES
						int data = 0;
						try {
							data = (int) sessionHQL .createQuery( "Delete from EDU_B_REGULATION_MEDICAL_DEGREE_CHILD where regulation_p_id=:id")
									.setParameter("id", td.getId()).executeUpdate();
							System.err.println("========" + data);

						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						} finally {
							sessionHQL.flush();
							sessionHQL.clear();
//							sessionHQL.close();
//							tx.commit();
						}

						if (data != 0) {
							int count_hidden_att_name_med = Integer
									.parseInt(request.getParameter("count_hidden_att_name_med"));

							for (int j = 1; j <= count_hidden_att_name_med; j++) {

								String typeOfDegree = request.getParameter("typeOfDegree" + j);
								String DegreeName = request.getParameter("DegreeName" + j);
								String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
								String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
								MultipartFile attachment = mul.getFile("attachment" + j);
								
						//SECURITY-----
								if (attachment.getOriginalFilename().isEmpty()) {
									ra.addAttribute("msg","Please Upload File");
									return new ModelAndView("redirect:B_Regulation_Url");
								}
								if (!attachment.getOriginalFilename().isEmpty()) {
									
									 if (attachment.getOriginalFilename().split("[.]").length > 2) {
										 ra.addAttribute("msg", "Invalid file extension for Document");
											return new ModelAndView("redirect:AlumniSignup_Url");
									}
									
									String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
									if (!upload_fileEXt.equals("pdf")) {
										ra.addAttribute("msg","Please Upload File of .pdf extention");
										return new ModelAndView("redirect:B_Regulation_Url");
									}
									long filesize = attachment.getSize() / 1024;
									if (filesize > 200) {
										ra.addAttribute("msg","File size should be 200 kb or less");
										return new ModelAndView("redirect:B_Regulation_Url");
									}
								}
						//SECURITY-----

								EDU_B_REGULATION_MEDICAL_DEGREE_CHILD ec = new EDU_B_REGULATION_MEDICAL_DEGREE_CHILD();
// HET VALIDATION
//			        	 if ( typeOfDegree == null || typeOfDegree.equals("0")) {
//								model.put("msg", "Please Select Type Of Degree");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						} 
//			        	 if ( monthYearOfDegree == null || monthYearOfDegree.equals("") || monthYearOfDegree.equals("dd/mm/yyyy")) {
//								model.put("msg", "Please Enter Month Year Of Degree");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						} 
//			        	 if ( NameOfUniversity == null || NameOfUniversity.equals("")) {
//								model.put("msg", "Please Enter Name Of University");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						}
//						if (attachment == null || attachment.equals("")) {
//						model.put("msg", "Please Upload Attachment");
//						return new ModelAndView("redirect:B_Regulation_Url");
//					}

								if (!attachment.isEmpty()) {
									String pic = common.fileupload(attachment.getBytes(),
											attachment.getOriginalFilename(), "UploadHardCopy1");
									if (pic != "") {
										ec.setAttachment_path(pic);
									}
								}
								ec.setRegulation_p_id(p_id);

								if (typeOfDegree != null && !typeOfDegree.equals("")) {
									ec.setType_of_degree(Integer.parseInt(typeOfDegree));
								}
								if (DegreeName != null && !DegreeName.equals("")) {
									ec.setDegree_name(Integer.parseInt(DegreeName));
								}
								ec.setName_of_institute(NameOfUniversity);
								ec.setMonth_and_year_of_degree(monthYearOfDegree);

								//ec.setModified_date(new Date());
								ec.setModified_by(username);
								ec.setMonth_and_year_of_degree(monthYearOfDegree);

								sessionHQL.save(ec);
								sessionHQL.flush();
								sessionHQL.clear();
							}
						}
					}

					if (p_id > 0) {
						System.out.println("------check theeee-data != 0------------");

						// HET CHANGES
						int data = 0;
						try {
							data = (int) sessionHQL
									.createQuery("Delete from EDU_B_REGULATION_HOSPITAL_CHILD where regulation_p_id=:id")
									.setParameter("id", td.getId()).executeUpdate();
							System.err.println("========" + data);

						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						} finally {
							sessionHQL.flush();
							sessionHQL.clear();
//							sessionHQL.close();
//							tx.commit();
						}

						if (data != 0) {
							System.out.println("------check theeee-data != 0------------"+(data != 0));

							int count_hidden_att_Hospital = Integer
									.parseInt(request.getParameter("count_hidden_att_Hospital"));

							for (int j = 1; j <= count_hidden_att_Hospital; j++) {

								String place_of_working = request.getParameter("place_of_working" + j);
								String place_of_working_name = request.getParameter("place_of_working_name" + j);

								String landline = request.getParameter("landline" + j);
								
								String address1 = request.getParameter("hos_address1_" + j);
								String address2 = request.getParameter("hos_address2_" + j);
								String address3 = request.getParameter("hos_address3_" + j);

								String hos_state = request.getParameter("hos_state" + j);
								String hos_district = request.getParameter("hos_district" + j);
								String email = request.getParameter("email" + j);

								String authority_type = request.getParameter("authority_type" + j);
								String name_of_res_p = request.getParameter("name_of_res_p" + j);

								
								System.out.println("-------check the place_of_working------------"+place_of_working);
								System.out.println("-------check the place_of_working_name------------"+place_of_working_name);
								System.out.println("-------check the place_of_working------------"+place_of_working);
								System.out.println("-------check the landline------------"+landline);
								System.out.println("-------check the hos_address1_------------"+address1);
								System.out.println("-------check the hos_address2_------------"+address2);
								System.out.println("-------check the hos_address3_------------"+address3);
								System.out.println("-------check the hos_state------------"+hos_state);
								System.out.println("-------check the hos_district_------------"+hos_district);
								System.out.println("-------check the email------------"+email);
								System.out.println("-------check the authority_type------------"+authority_type);
								System.out.println("-------check the name_of_res_p_------------"+name_of_res_p);
								
								
//							String date_pract_from = request.getParameter("date_pract_from" + j);
//							String date_pract_to = request.getParameter("date_pract_to" + j);
								String mobile_no = request.getParameter("mobileHosp" + j);

								EDU_B_REGULATION_HOSPITAL_CHILD Hc = new EDU_B_REGULATION_HOSPITAL_CHILD();

								// HET VALIDATION
//			        	 if ( place_of_working == null || place_of_working.equals("0")) {
//								model.put("msg", "Please Select Place of Working");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						} 
//			        	 if ( landline == null || landline.equals("")) {
//								model.put("msg", "Please Enter Landline No");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						} 
//			        	 if ( address == null || address.equals("")) {
//								model.put("msg", "Please Enter Address");
//								return new ModelAndView("redirect:B_Regulation_Url");
//						}
//						if (email == null || email.equals("")) {
//						model.put("msg", "Please Enter Email");
//						return new ModelAndView("redirect:B_Regulation_Url");
//					}
//						if (authority_type == null || authority_type.equals("")) {
//							model.put("msg", "Please Enter Authority Type");
//							return new ModelAndView("redirect:B_Regulation_Url");
//						}if (name_of_res_p == null || name_of_res_p.equals("")) {
//							model.put("msg", "Please Enter Name of Responsible Person Name");
//							return new ModelAndView("redirect:B_Regulation_Url");
//						}

								Hc.setMobile_no(mobile_no);

//						if (!date_pract_from.equals("DD/MM/YYYY")) {
//							Hc.setDate_pract_from(formate.parse(date_pract_from));
//						}
//
//						if (!date_pract_to.equals("DD/MM/YYYY")) {
//							Hc.setDate_pract_to(formate.parse(date_pract_to));
//						}
								Hc.setPlace_of_working_name(place_of_working_name);
								Hc.setHos_address1(address1);
								Hc.setHos_address2(address2);
								Hc.setHos_address3(address3);

								if (hos_state != null && !hos_state.equals("")) {
									Hc.setHos_state(Integer.parseInt(hos_state));
								}

								if (hos_district != null && !hos_district.equals("")) {
									Hc.setHos_district(Integer.parseInt(hos_district));
								}

								Hc.setRegulation_p_id(p_id);

								if (place_of_working != null && !place_of_working.equals("")) {
									Hc.setPlace_of_working(Integer.parseInt(place_of_working));
								}

								Hc.setLandline(landline);

								Hc.setEmail(email);

								if (authority_type != null && !authority_type.equals("")) {
									Hc.setAuthority_type(Integer.parseInt(authority_type));
								}

								Hc.setName_of_res_p(name_of_res_p);

								Hc.setModified_date(new Date());
								Hc.setModified_by(username);

								sessionHQL.save(Hc);
								sessionHQL.flush();
								sessionHQL.clear();
							}
						}
					}
				}
				tx.commit();
				ra.addAttribute("msg", "Updated Successfully.");
			}

//		} catch (RuntimeException e) {
//			try {
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
//			}
//			throw e;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//
//		}

		return new ModelAndView("redirect:B_Regulation_Url");
	}
	
	@RequestMapping(value = "/Edit_edu_b_reg_mstrUrl")
	public ModelAndView Edit_edu_b_reg_mstrUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpSession sessionEdit, HttpServletRequest request) {

//	 System.err.println("edit--dsfd--"+updateid);
		
		//	SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Edit_edu_b_reg_mstrUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			

		TB_EDU_B_REGULATION Edit_b_reg_mstr_Details = BRdao.getEdu_b_RegByid(Integer.parseInt(updateid));
		Mmap.put("Edit_b_reg_mstr_Details", Edit_b_reg_mstr_Details);
		Mmap.put("country_id", common.getMedCountryName(sessionFactory));
		Mmap.put("state_id", common.getMedStateName(sessionFactory));
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
//	Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("edit_b_regulation_Tiles");
	}
	
	
	//--------------------------------------------------Edit_Action--------------------------------------------------------------

		@RequestMapping(value = "/Edit_b_Regulation_Action")
		public ModelAndView Edit_b_Regulation_Action(@ModelAttribute("Edit_b_RegulationCMD") TB_EDU_B_REGULATION rs,
				BindingResult result, MultipartHttpServletRequest mul, HttpServletRequest request, ModelMap model,
				HttpSession session, @RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra)
				throws ParseException, java.text.ParseException, IOException {
			
			//	SECURITY -- RIDDHI 
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Edit_edu_b_reg_mstrUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));

			// start
			String photo_path = gd(request, mul, session, "photo_path");
			// end
			String upload_img_hid = request.getParameter("upload_img_hid").trim();

//		String nrh_en_no = request.getParameter("nrh_en_no").trim();
			String first_name = request.getParameter("first_name").trim();
			String aadhaar_no = request.getParameter("aadhaar_no").trim();
//		int mo_no = Integer.parseInt(request.getParameter("mo_no"));
//		int alt_mo_no = Integer.parseInt(request.getParameter("alt_mo_no"));
			BigInteger mo_no = new BigInteger(request.getParameter("mo_no"));
			BigInteger alt_mo_no = new BigInteger(request.getParameter("alt_mo_no"));
			int fax_no = Integer.parseInt(request.getParameter("fax_no"));
			String email_id = request.getParameter("email_id").trim();
			String father_name = request.getParameter("father_name").trim();
			String nationality = request.getParameter("nationality").trim();
			String per_address = request.getParameter("per_address").trim();
			String per_state = request.getParameter("per_state").trim();
			String per_district = request.getParameter("per_district").trim();
			int per_pincode = Integer.parseInt(request.getParameter("per_pincode"));
			String pre_address = request.getParameter("pre_address").trim();
			String pre_state = request.getParameter("pre_state").trim();
			String pre_district = request.getParameter("pre_district").trim();
			int pre_pincode = Integer.parseInt(request.getParameter("pre_pincode"));
			String name_reg = request.getParameter("name_reg").trim();
			String reg_renew_permanent = request.getParameter("reg_renew_permanent").trim();
			String degree = request.getParameter("degree").trim();
			String university = request.getParameter("university").trim();
			String month_year = request.getParameter("month_year").trim();
			int reg_no = Integer.parseInt(request.getParameter("reg_no"));
			String name_of_hospital_teaching = request.getParameter("name_of_hospital_teaching").trim();
			String name_of_patient = request.getParameter("name_of_patient").trim();
			String crh_no = request.getParameter("crh_no").trim();
			String cch_no = request.getParameter("cch_no").trim();
			String nch_no = request.getParameter("nch_no").trim();

			SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
			String dob1 = request.getParameter("dob");
			Date dob = null;
			dob = formate.parse(dob1);

			String date_of_reg1 = request.getParameter("date_of_reg");
			Date date_of_reg = null;
			date_of_reg = formate.parse(date_of_reg1);

//	 	System.err.println("edit--------dob---"+dob);
//	 	 
//	 	System.err.println("edit-sf-------dob1---"+dob1);

			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			if (photo_path.trim().equals("")) {
				photo_path = upload_img_hid;
			}
			// try {
			Query<?> q0 = session1.createQuery(
					"select count(id) from TB_EDU_B_REGULATION where  photo_path=:photo_path and  first_name=:first_name and aadhaar_no=:aadhaar_no"
							+ " and mo_no=:mo_no and alt_mo_no=:alt_mo_no and fax_no=:fax_no and email_id=:email_id and father_name=:father_name "
							+ "and nationality=:nationality and per_address=:per_address and per_state=:per_state and per_district=:per_district "
							+ "and per_pincode=:per_pincode and pre_address=:pre_address and pre_state=:pre_state and pre_district=:pre_district "
							+ "and pre_pincode=:pre_pincode and name_reg=:name_reg and reg_renew_permanent=:reg_renew_permanent "
							+ "and degree=:degree and university=:university and month_year=:month_year and reg_no=:reg_no "
							+ "and name_of_hospital_teaching=:name_of_hospital_teaching and name_of_patient=:name_of_patient "
							+ "and crh_no=:crh_no and cch_no=:cch_no and nch_no=:nch_no and dob=:dob and date_of_reg=:date_of_reg"
							+ " and id !=:id");
//			q0.setParameter("nrh_en_no", nrh_en_no);
			q0.setParameter("first_name", first_name);
			q0.setParameter("aadhaar_no", aadhaar_no);
			q0.setParameter("photo_path", photo_path);
			q0.setParameter("mo_no", mo_no);
			q0.setParameter("alt_mo_no", alt_mo_no);
			q0.setParameter("fax_no", fax_no);
			q0.setParameter("email_id", email_id);
			q0.setParameter("father_name", father_name);
//			---21-06-2022 urmik changes
			q0.setParameter("nationality",Integer.parseInt(nationality));
			q0.setParameter("per_address", per_address);
			q0.setParameter("per_state", per_state);
			q0.setParameter("per_district", per_district);
			q0.setParameter("per_pincode", per_pincode);
			q0.setParameter("pre_address", pre_address);
			q0.setParameter("pre_state", pre_state);
			q0.setParameter("pre_district", pre_district);
			q0.setParameter("pre_pincode", pre_pincode);
			q0.setParameter("name_reg", name_reg);
			q0.setParameter("reg_renew_permanent", reg_renew_permanent);
//			21-06-2022 urmik changes
			q0.setParameter("degree", Integer.parseInt(degree));
			q0.setParameter("university", university);
			q0.setParameter("month_year", month_year);
			q0.setParameter("reg_no", reg_no);
			q0.setParameter("name_of_hospital_teaching", name_of_hospital_teaching);
			q0.setParameter("name_of_patient", name_of_patient);
			q0.setParameter("crh_no", crh_no);
			q0.setParameter("cch_no", cch_no);
			q0.setParameter("nch_no", nch_no);
			q0.setParameter("dob", dob);
			q0.setParameter("date_of_reg", date_of_reg);
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_B_EDU_REGULATION set first_name=:first_name,aadhaar_no=:aadhaar_no,photo_path=:photo_path,"
						+ "mo_no=:mo_no,alt_mo_no=:alt_mo_no,fax_no=:fax_no,email_id=:email_id,father_name=:father_name,"
						+ "nationality=:nationality,per_address=:per_address,per_state=:per_state,per_district=:per_district,"
						+ "per_pincode=:per_pincode,pre_address=:pre_address,pre_state=:pre_state,pre_district=:pre_district,"
						+ "pre_pincode=:pre_pincode,name_reg=:name_reg,reg_renew_permanent=:reg_renew_permanent,degree=:degree,"
						+ "university=:university,month_year=:month_year,reg_no=:reg_no,name_of_hospital_teaching=:name_of_hospital_teaching,"
						+ "name_of_patient=:name_of_patient,crh_no=:crh_no,cch_no=:cch_no,nch_no=:nch_no"
						+ ",modified_by=:modified_by,modified_date=:modified_date,dob=:dob,date_of_reg=:date_of_reg"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("first_name", first_name)
						.setParameter("aadhaar_no", aadhaar_no).setParameter("mo_no", mo_no)
						.setParameter("photo_path", photo_path).setParameter("alt_mo_no", alt_mo_no)
						.setParameter("fax_no", fax_no).setParameter("email_id", email_id)
						.setParameter("father_name", father_name).setParameter("nationality", nationality)
						.setParameter("per_address", per_address).setParameter("per_state", per_state)
						.setParameter("per_district", per_district).setParameter("per_pincode", per_pincode)
						.setParameter("pre_address", pre_address).setParameter("pre_state", pre_state)
						.setParameter("pre_district", pre_district).setParameter("pre_pincode", pre_pincode)
						.setParameter("name_reg", name_reg).setParameter("reg_renew_permanent", reg_renew_permanent)
						.setParameter("degree", degree).setParameter("university", university)
						.setParameter("month_year", month_year).setParameter("reg_no", reg_no)
						.setParameter("name_of_hospital_teaching", name_of_hospital_teaching)
						.setParameter("name_of_patient", name_of_patient).setParameter("crh_no", crh_no)
						.setParameter("cch_no", cch_no).setParameter("nch_no", nch_no).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("dob", dob)
						.setParameter("date_of_reg", date_of_reg)

						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg.equals("1")) {

					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}

//		} catch (RuntimeException e) {
//			try {
//				tx.rollback();
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
//			}
//			throw e;
	//
//		} finally {
//			if (session1 != null) {
//				session1.close();
//			}
//		}
	//	
			return new ModelAndView("redirect:edu_search_b_reg_url");
		}

	//---------------------------------------End Edit_Action -----------------------------------------------------------------------

	
	
	@PostMapping(value = "/delete_edu_b_reg_mstr_Url")
	public ModelAndView delete_edu_b_reg_mstr_Url(@ModelAttribute("id2") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("B_Regulation_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update TB_EDU_B_REGULATION set modified_by=:modified_by,modified_date=:modified_date,del_status=:del_status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("del_status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check " + (app > 0));
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:edu_search_b_reg_url");
	}
	
	
	public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		// System.err.println("id----"+id);

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		MultipartFile file = mul.getFile(id);
		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute(id).toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {

		}
		return fname;

	}
	
	
	//------------------------------------------------Edit--Image_View------------------------------------------------------

		@RequestMapping(value = "/MedicalImagePath5", method = RequestMethod.GET)
		public void censusIdentityConvertpath(@ModelAttribute("i_id") String id, @ModelAttribute("id5") String myImg,
				ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

			final int BUFFER_SIZE = 4096;

			String i_id = id;

			String filePath = BRdao.getImagePath(i_id);

			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();

			try {

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

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

			//	System.out.println(ex);

				// admin//js//img//No_Image.jpg

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
	//end
	
	//-----------------------------Excel-----------------------------------

		@RequestMapping(value = "/Excel_Auth_Posted_queryb", method = RequestMethod.POST)
		public ModelAndView Excel_Auth_Posted_queryb(HttpServletRequest request, ModelMap model, HttpSession session,
				String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

//			SECURITY -- RIDDHI 
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("B_Regulation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			ArrayList<ArrayList<String>> Excel = BRdao.pdf_getAuth_and_Posted_StrenghReportDataList();

			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("NRH Enrollment No");
			TH.add("First Name");
			TH.add("Gender");
			TH.add("Photo Path");
			TH.add("Father Name");
			TH.add("Present Address");
			TH.add("Present District");
			TH.add("Present State");
			TH.add("Present Pincode");
			TH.add("Permanent Address");
			TH.add("Permanent District");
			TH.add("Permanent State");
			TH.add("Permanent Pincode");
			TH.add("Aadhaar No");
			TH.add("Fax No");
			TH.add("Mo. No");
			TH.add("Alternate Mo. No");
			TH.add("Email Id");
			TH.add("Date Of Birth");
			TH.add("Nationality");
			TH.add("Name Of Medical Degree");
			TH.add("Name of University");
			TH.add("Month And Year Of Passing");
			TH.add("Registration No.");
			TH.add("Date Of Registration");
			TH.add("Name Of The Register");
			TH.add("Registration Renewable Or Permanent");
			TH.add("Name Of Hospital Or Institute With Address");
			TH.add("Name Of Patient");
			TH.add("CRH No");
			TH.add("CCH No");
			TH.add("NCH No");

			String Heading = "\nAuth and Posted Strength Search Report";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
		
		@RequestMapping(value = "/getDegreedetailsb", method = RequestMethod.POST)
		public @ResponseBody  List<Map<String, Object>> getDegreeName(String typeofdegree) {
			 
			 List<Map<String, Object>> list =BRdao.getdegreedetailsb(typeofdegree);
			 System.err.println("------list 22_06 ====  "+list);
	 		return list;
		}
		
		
		@RequestMapping(value = "/getDistrictOnstateb", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstateb(String selval) {
		//	System.err.println("INSIDE");
			return common.district(sessionFactory, selval);

		}



}
