package com.AyushEdu.controller.Part_one.Masters;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.AyushEdu.Models.Part_One.Masters.CLG_REG_NATURE_OF_APPOINTMENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Part_One.Masters.ClgAppointDAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Clg_Appoinment_Master_Controller {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RoleBaseMenuDAO roledao;
    @Autowired
    CommonController common;
    @Autowired
    ValidationController validation;

    @Autowired
    ClgAppointDAO clgAppointDAO;



    @RequestMapping(value = "/ClgAppointUrl", method = RequestMethod.GET)
    public ModelAndView ClgAppointUrl(ModelMap Mmap, HttpSession session,
                                      @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//TODO: remove return after adding menu
        //try {
        //  System.out.println(request.toString());
//            if(request.getHeader("Referer") == null ) {
////			 session.invalidate();
//                Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//                return new ModelAndView("redirect:/landingpage");
//            }
//            String roleid1 = session.getAttribute("roleid").toString();
//            Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);
//            if(val == false) {
//                return new ModelAndView("AccessTiles");
//            }

//            Mmap.put("msg", msg);
//            Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Mmap.put("msg", msg);
        Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
        return new ModelAndView("Clg_Appoinment_Tiles");

    }

    @PostMapping("/getFilterClgAppoint_data")

    public @ResponseBody List<Map<String, Object>> getFilterClgAppoint_data(int startPage, int pageLength,

                                                                            String Search, String orderColunm, String orderType, String clg_appoint_name,String status) {

        System.out.println(clg_appoint_name);

        return clgAppointDAO.DataTableClgAppointDataList(
                startPage,
                pageLength,
                Search,
                orderColunm,
                orderType,
                clg_appoint_name,
                status);

    }

    @PostMapping(value = "/ClgAppointmentAction")
    public ModelAndView ClgAppointmentAction(@Validated @ModelAttribute("ClgAppointCMD") CLG_REG_NATURE_OF_APPOINTMENT td, BindingResult result,
                                             HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
                                             RedirectAttributes ra) {


        System.out.println(td.toString());
//
//        if(request.getHeader("Referer") == null ) {
//            //	 session.invalidate();
//            model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//            return new ModelAndView("redirect:/login");
//        }
//        String role = session.getAttribute("role").toString();
//        String roleid1 = session.getAttribute("roleid").toString();
//        Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);
//        if(val == false) {
//            return new ModelAndView("AccessTiles");
//        }
        String clg_appoint_name = request.getParameter("clg_appoint_name");
        String status = request.getParameter("status");

        if (clg_appoint_name == null || clg_appoint_name.trim().equals("")) {
            ra.addAttribute("msg", "Please Enter System.");
            return new ModelAndView("redirect:ClgAppointUrl");
        }

        if (validation.maxlengthcheck100(clg_appoint_name) == false) {
            ra.addAttribute("msg","System "+ validation.MaxlengthcheckMSG100);
            return new ModelAndView("redirect:ClgAppointUrl");
        }
        if (validation.isOnlyAlphabetDASH(clg_appoint_name) == false) {
            ra.addAttribute("msg","System "+ validation.isOnlyAlphabetMSGDASH);
            return new ModelAndView("redirect:ClgAppointUrl");
        }

        if (status == null || status.trim().equals("0")) {
            ra.addAttribute("msg", "Please Select Status.");
            return new ModelAndView("redirect:ClgAppointUrl");
        }
        if (status == "2" || status.trim().equals("2")) {
            ra.addAttribute("msg", "Only Select Active Status.");
            return new ModelAndView("redirect:ClgAppointUrl");
        }
        int id = td.getId() > 0 ? td.getId() : 0;
        Date date = new Date();
        String username = principal.getName();
//		String clg_appoint_name = principal.getName();

        Session sessionHQL = this.sessionFactory.openSession();
        Transaction tx = sessionHQL.beginTransaction();

        try {
            Long c = (Long) sessionHQL.createQuery(
                            "select count(id) from  CLG_REG_NATURE_OF_APPOINTMENT where upper(nature_of_appoinment)=:nature_of_appoinment and status=:status and id !=:id")
                    .setParameter("nature_of_appoinment", clg_appoint_name.toUpperCase())
                    .setParameter("status", Integer.parseInt(status))
                    .setParameter("id", id).uniqueResult();
            int idd =0;
            if (id == 0) {
                td.setNature_of_appoinment(clg_appoint_name);
                td.setCreated_by(username);
                td.setModified_by(username);
                td.setModified_by(username);
                td.setCreated_date(date);
                td.setModified_date(date);
                if (c == 0) {
                    idd = (int)sessionHQL.save(td);
                    sessionHQL.flush();
                    sessionHQL.clear();
                    ra.addAttribute("msg", "Data Saved Successfully.");
                } else {
                    ra.addAttribute("msg", "Data already Exist.");
                }
            }else{
                td.setNature_of_appoinment(clg_appoint_name);
                td.setModified_by(username);
                td.setModified_date(date);
                if (c == 0) {
                    td.setId(id);
                    String msg = clgAppointDAO.updateNatureOfAppointment(td);
                    //For Core Directory
                    //DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
                    ra.addAttribute("msg", "Data Updated Successfully.");
                } else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
                    ra.addAttribute("msg", "Data already Exist.");
                }
            }

            tx.commit();
            //For Core Directory
//            SM_dirdao.Insert_System_Mstr_Data(idd);

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

        return new ModelAndView("redirect:ClgAppointUrl");
    }



    //controller for delete
    @RequestMapping(value = "/Nature_Appointment_delete_Url2", method = RequestMethod.POST)
    public ModelAndView Nature_Of_Appointment_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
                                                         HttpServletRequest request, ModelMap model, HttpSession session1) {

        List<String> liststr = new ArrayList<String>();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String username = session1.getAttribute("username").toString();
        try {

            int app = session.createQuery(
                            "update CLG_REG_NATURE_OF_APPOINTMENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
                    .setParameter("id", id).setParameter("modified_by", username)
                    .setParameter("modified_date", new Date())
                    .setParameter("status", 2).executeUpdate();


            tx.commit();
            //For Core Directory
//            DM_dirdao.Delete_Uni_Type_Mstr_Data(id);
            session.close();
            if (app > 0) {
                System.err.println("check delete"+(app > 0));
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
        return new ModelAndView("redirect:ClgAppointUrl");
    }



    @PostMapping("/getTotalClg_Appoint_dataCount")
    public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String clg_appoint_name) {
        return clgAppointDAO.DataTableClgAppointDataTotalCount(Search, clg_appoint_name);

    }


}
