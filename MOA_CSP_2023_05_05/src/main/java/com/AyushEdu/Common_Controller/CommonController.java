package com.AyushEdu.Common_Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.TB_LDAP_MODULE_MASTER;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_EVENT;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_NATURE_OF_APPOINMENT_MSTR;
import com.AyushEdu.Models.Collaboration.TB_COL_ORG_CATEGORY;
import com.AyushEdu.Models.Collaboration.TB_COL_ORG_SECTOR;
import com.AyushEdu.Models.Collaboration.TB_COL_ORG_TYPE;
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM;
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE;
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_PRACTICAL_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_ADD_COURSE_OUTCOME_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_C3_DOMAIN_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_G3_ASSESSMENT_METHOD_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GRADUATE_ATTRIBUTES_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GUILBERTS_LEVEL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_H3_ASSESSMENT_TYPE_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_I3_TERM_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PAPER_MASTER;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TOPICS_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_HOURS_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_TEACHING_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_EVALUATION_METHOD_PA_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_NCH_PRACTICAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;
import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_CATEGORY_MSTR;
import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR;
import com.AyushEdu.Models.Examination.CC_TB_ATTEMPT_MSTR;
import com.AyushEdu.Models.Examination.EXAM_RESULT_STATUS;
import com.AyushEdu.Models.Examination.EXAM_TB_EXAM_SERIAL_MSTR;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYS_DEG_MAP_INST;
import com.AyushEdu.Models.LMS_Master.Document_mst;
import com.AyushEdu.Models.LMS_Master.EDU_ACADEMIC_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_DIVISION_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LINK_SYS_DEG_PROF_TERM;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COUNSELING_AUTHORITY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DOCUMENT_TYPE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_FACULTY_TYPE_OF_EXP_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_INTAKE_TYPE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_LEVEL_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ORGANIZATION_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TERM_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_CONTENT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_TYPE_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_LMS_MARITAL_STATUS_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_LMS_RELIGION_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;
import com.AyushEdu.Models.LMS_Master.recr_category_mst;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_SYSTEM_COURSE_DURATION;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_UPLODE_CERTIFICATE;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_DTL;
import com.AyushEdu.Models.Masters.EDU_LMS_QUOTA_MSTR;
import com.AyushEdu.Models.Masters.EDU_LMS_REGULATION_NAME_OF_RES_OWNER_MSTR;
import com.AyushEdu.Models.Masters.EDU_LMS_REGULATION_PLACE_OF_WORKING_MSTR;
import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;
import com.AyushEdu.Models.Policy_Model_Master.TB_POLICYCATEGORY_MASTER;
import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;
import com.AyushEdu.Models.QuizBank.EDU_LMS_PAPER_GENERATION;
import com.AyushEdu.Models.Registration.TB_COLLAGE_MSTR;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_GRADU_EXAMNAME;
import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_COURSE_MSTR;
import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_SUBJECT_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DESIGNATION_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_EMPLOYEMENT_TYPE_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_PREFIX_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_SALARY_STATUS_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;
import com.AyushEdu.Models.helpdeskINQ.HD_INQUIRY_CATEGORY_MSTR;
import com.AyushEdu.config.VideoService;
import com.AyushEdu.dao.CommonDao.Commondao;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CommonController {

	@Autowired
	Commondao cmdao;
	@Autowired
	private VideoService service;
	@Autowired
	private SessionFactory sessionFactory;

	public @ResponseBody List<TB_POLICYCATEGORY_MASTER> getPolicyCategoryList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_POLICYCATEGORY_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_POLICYCATEGORY_MASTER> clist = (List<TB_POLICYCATEGORY_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_SUBPOLICYCATEGORY> getSubPolicyCategoryList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_SUBPOLICYCATEGORY where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_SUBPOLICYCATEGORY> clist = (List<TB_SUBPOLICYCATEGORY>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getCategoryDataList", method = RequestMethod.POST)
	public @ResponseBody List<TB_POLICY_INITIAL_DRAFT> getCategoryDataList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select distinct policy_category from TB_POLICY_INITIAL_DRAFT where order by id ");

		@SuppressWarnings("unchecked")
		List<TB_POLICY_INITIAL_DRAFT> clist = (List<TB_POLICY_INITIAL_DRAFT>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_POLICY_INITIAL_DRAFT> getPolicy(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_POLICY_INITIAL_DRAFT  order by id ");

		@SuppressWarnings("unchecked")
		List<TB_POLICY_INITIAL_DRAFT> clist = (List<TB_POLICY_INITIAL_DRAFT>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public List<String> getDraftStatusList(String role, SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select codevalue,label from T_Domain_Value where domainid='DRAFT_STATUS' and role =:r order by codevalue");
			q1.setParameter("r", role);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getpolicytypeList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select codevalue,label from T_Domain_Value where domainid='POLICY_TYPE' order by codevalue");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

//		===========================================START LMS ============================================================= 

	public List<String> getActiveInActiveList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select codevalue,label from T_Domain_Value where domainid='AC_INAC_STATUS' order by codevalue");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getcoursenameList", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcoursenameList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id, course_name from EDU_LMS_ELECTIVE_COURSE_MASTER where status='1'");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	//change on 12/12/2022 NCH and NCISM Both
public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemListbyrole(SessionFactory sessionFactory,String role) {
		
		System.err.println("-----role ++ "+role);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select distinct sm.id,sm.system_name from EDU_LMS_SYSTEM_MSTR sm ,Role r \n"
				+ "where r.staff_lvl=sm.created_role  and sm.status='1' and  r.role=:role order by sm.system_name ");
		q.setParameter("role", role);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemList(SessionFactory sessionFactory, String role) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Query q = sessionHQL
				.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' and created_role=:role order by id ");
		q.setParameter("role", role);

		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemByNCISM(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Query q = sessionHQL
				.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' and created_role='NCISM' order by id ");
		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public List<String> getMedCountryName(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL
					.createQuery("select distinct id,name from TB_COUNTRY where status='1' order  by name");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getMedStateName(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select distinct state_id,state_name from TB_STATE where status='1' order  by state_name");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getMedDistrictName(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select distinct district_id,district_name from EDU_LMS_DISTRICT_MSTR where status='1' order  by district_name");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION where status='1' and app_status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getsysList", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getsysList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id,system_name from EDU_LMS_SYSTEM_MSTR WHERE status='1' order by system_name");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	public @ResponseBody List<EDU_LMS_TERM_MASTER> getProfessionList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select distinct term, prof_name from EDU_LMS_TERM_MASTER where status='1' order by term ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}	
	
//			===========================================END LMS ============================================================= 

	// coutry_state
	@RequestMapping(value = "/getcountryState", method = RequestMethod.POST)
	public @ResponseBody List<String> getcountryState(SessionFactory sessionFactory, String user_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"SELECT cast(u.country_id as text),cast(u.state_id as text) FROM UserLogin u where userId =:userid");
		q.setParameter("userid", Integer.parseInt(user_id));
		@SuppressWarnings("unchecked")
		// List<UserLogin> clist = (List<UserLogin>) q.list();
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/getSetList", method = RequestMethod.POST)
	public @ResponseBody List<TB_SET_MASTER> getSetList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select  id,setname from TB_SET_MASTER  where status='1' order by id  ");

		@SuppressWarnings("unchecked")
		List<TB_SET_MASTER> clist = (List<TB_SET_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public List<String> getCourseNamelist(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select distinct cm.course_name, lcm.course_name from EDU_LMS_ELECTIVE_COURSE_MASTER cm ,EDU_LMS_COURSE_MASTER lcm  where cast(cm.course_name as integer)=lcm.id and cm.status='1' order  by lcm.course_name");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<String> getModuleName(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id, module_name from EDU_LMS_MODULE_MSTR where status='1' order  by module_name ");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getModulnameList", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getModulnameList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id,module_name from EDU_LMS_MODULE_MSTR where status='1' ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_MODULE_MSTR> clist = (List<EDU_LMS_MODULE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public int saveFunction(Object obj, SessionFactory sessionFactory) {
		Session session1 = sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		int id = (Integer) session1.save(obj);
		session1.getTransaction().commit();
		session1.close();
		return id;
	}

	@RequestMapping(value = "/getcoursenameListALL", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcoursenameListALL(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_ELECTIVE_COURSE_MASTER where status='1'");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getSetListALL", method = RequestMethod.POST)
	public @ResponseBody List<TB_SET_MASTER> getSetListALL(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id, setname from TB_SET_MASTER where status='1' order by id");

		@SuppressWarnings("unchecked")
		List<TB_SET_MASTER> clist = (List<TB_SET_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "admin/kmlLOFileDownload6")
	public void kmlLOFileDownload6(@ModelAttribute("kmlFileId65") String id,
			@ModelAttribute("fildname") String fildname, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath = cmdao.getTopicVideoPath(Integer.parseInt(id));
		
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();

		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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

			String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
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

	@RequestMapping(value = "admin/kmlLOFileDownload6_p_id")
	public void kmlLOFileDownload6_p_id(@ModelAttribute("kmlFileId65") String id,
			@ModelAttribute("fildname") String fildname, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath = cmdao.getTopicVideoPathp_id(Integer.parseInt(id));

		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();

		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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

			String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
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

	@RequestMapping(value = "/getapp_instituteNameList", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getapp_instituteNameList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select distinct id, institute_name from EDU_LMS_INSTITUTE_REGISTRATION where app_status='1' and status!='2' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_DEGREE_MASTER> getDegreeList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_DEGREE_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_MASTER> clist = (List<EDU_LMS_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getModulnameList_course", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getModulnameList_course(SessionFactory sessionFactory,
			String course_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select id,module_name from EDU_LMS_MODULE_MSTR where course_id=:course_id and  status='1'");
		q.setParameter("course_id", course_id);
		@SuppressWarnings("unchecked")

		List<EDU_LMS_MODULE_MSTR> clist = (List<EDU_LMS_MODULE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_TYPE_OF_DEGREE_MASTER> getTypeOfDegree(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_TYPE_OF_DEGREE_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TYPE_OF_DEGREE_MASTER> clist = (List<EDU_LMS_TYPE_OF_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
//HET CHANGES

//				public @ResponseBody List<EDU_LMS_TYPE_OF_DEGREE_MASTER> getTypeOfDegree(SessionFactory sessionFactory) {
//					Session sessionHQL = sessionFactory.openSession();
//					Transaction tx = sessionHQL.beginTransaction();
//					Query q = sessionHQL.createQuery("from EDU_LMS_TYPE_OF_DEGREE_MASTER where status='1' order by id ");
//					
//					@SuppressWarnings("unchecked")
//					List<EDU_LMS_TYPE_OF_DEGREE_MASTER> clist = (List<EDU_LMS_TYPE_OF_DEGREE_MASTER>) q.list();
//					tx.commit();
//					sessionHQL.close();
//					return clist;
//				}

	public @ResponseBody List<EDU_LMS_REGULATION_PLACE_OF_WORKING_MSTR> getPlaceOfWorking(
			SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_REGULATION_PLACE_OF_WORKING_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_REGULATION_PLACE_OF_WORKING_MSTR> clist = (List<EDU_LMS_REGULATION_PLACE_OF_WORKING_MSTR>) q
				.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_REGULATION_NAME_OF_RES_OWNER_MSTR> getNameOfResOwner(
			SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from EDU_LMS_REGULATION_NAME_OF_RES_OWNER_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_REGULATION_NAME_OF_RES_OWNER_MSTR> clist = (List<EDU_LMS_REGULATION_NAME_OF_RES_OWNER_MSTR>) q
				.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	/* photo upload */
	public String currentDateWithTimeStampString() {
		java.util.Date date = new java.util.Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
	}
	/* photo upload */

	public String fileupload(byte[] b, String name, String type) // ,String id
	{
		String extension = "";
		String fname = "";
		try {
			byte[] bytes = b;
			File dir = new File("/srv/Document/");
			if (!dir.exists())
				dir.mkdirs();

			String filename = name;

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}

			// Create the file on server
			fname = dir.getAbsolutePath() + File.separator + "" + type + "_" + currentDateWithTimeStampString() + "."
					+ extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fname;
	}

	public @ResponseBody List<EDU_LMS_DEGREE_MASTER> DegreeName(SessionFactory sessionFactory, String DegreeName) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from EDU_LMS_DEGREE_MASTER where type_of_degree=:type_of_degree order by id ");
		q.setParameter("type_of_degree", Integer.parseInt(DegreeName));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_MASTER> clist = (List<EDU_LMS_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> district(SessionFactory sessionFactory, String roleid) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"from EDU_LMS_DISTRICT_MSTR where status='1' and state_id=:state_id order  by district_name ");
		q.setParameter("state_id", Integer.parseInt(roleid));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_DISTRICT_MSTR> clist = (List<EDU_LMS_DISTRICT_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	// HET CHANGES

	public String getAlphaNumericString() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(16);
		for (int i = 0; i < 16; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public Cipher EncryptionSHA256Algo(HttpSession session, String enckey) {
		try {
			// String KeySpec = session.getAttribute("KeySpec").toString();
			String KeySpec = "dc0da04af8fee58593442bf834b30739";
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(enckey.toCharArray(), hex(KeySpec), 1000, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(hex(KeySpec)));
			return cipher;
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static byte[] hex(String str) {
		try {
			return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException(e);
		}
	}

	public List<String> getMedNationality(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select distinct nationality_id,nationality from recr_nationality_mst where status='A'");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

//-----------------district_state
	@RequestMapping(value = "/getdistlistfor_mapping", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getdistlistfor_mapping() {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Query q = sessionHQL
				.createQuery("select d.district_id,d.district_name from EDU_LMS_DISTRICT_MSTR d WHERE d.status='1' ");

//							Query q = sessionHQL.createQuery("select d.district_id,d.district_name from EDU_LMS_DISTRICT_MSTR d, EDU_LMS_STATE_DIST_MAP sd  WHERE d.status='1'  and  sd.district_id != d.district_id");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_DISTRICT_MSTR> clist = (List<EDU_LMS_DISTRICT_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	public List<String> getInstIdfromUserid(SessionFactory sessionFactory, String userid) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("select cast(institute_id as text) from UserLogin where userid=:userid");
			q1.setParameter("userid", Integer.parseInt(userid));
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@RequestMapping(value = "/getSystem_nametoid", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystem_nametoid(String system_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_SYSTEM_MSTR where system_name=:system_name and status='1'");
		q.setParameter("system_name", system_name);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getInstid_fromname", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstid_fromname(String instname) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION where institute_name=:institute_name ");
		q.setParameter("institute_name", instname);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getstateid_fromname", method = RequestMethod.POST)
	public @ResponseBody List<TB_STATE> getstateid_fromname(String statename) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_STATE where state_name=:state_name ");
		q.setParameter("state_name", statename);
		@SuppressWarnings("unchecked")
		List<TB_STATE> clist = (List<TB_STATE>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getQuotaIdFromName", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_QUOTA_MSTR> getQuotaFromId(String quota) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_QUOTA_MSTR where quota=:quota ");
		q.setParameter("quota", quota);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_QUOTA_MSTR> clist = (List<EDU_LMS_QUOTA_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getCoAuIDfromName", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> getCoAuIDfromName(String couauth) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_COUNSELING_AUTHORITY_MSTR where counseling_authority=:counseling_authority and status=1 ");
		q.setParameter("counseling_authority", couauth);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> clist = (List<EDU_LMS_COUNSELING_AUTHORITY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getcatIDfromName", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_CATEGORY_MSTR> getcatIDfromName(String cat) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_CATEGORY_MSTR where category=:category ");
		q.setParameter("category", cat);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_CATEGORY_MSTR> clist = (List<EDU_LMS_CATEGORY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getDegree_nametoid", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DEGREE_MASTER> getDegree_nametoid(String degree_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_DEGREE_MASTER where degree_name=:degree_name and status='1'");
		q.setParameter("degree_name", degree_name);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_MASTER> clist = (List<EDU_LMS_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_TYPE_OF_DEGREE_MASTER> gettype_of_degree(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select id,type_of_degree from EDU_LMS_TYPE_OF_DEGREE_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TYPE_OF_DEGREE_MASTER> clist = (List<EDU_LMS_TYPE_OF_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<String> getInstListFromSystem(String degree_id) {
//							System.err.println("degree----->"+degree_id);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select cast(institute_id as text) from EDU_LMS_SYS_DEG_MAP_INST where degree_id=:degree_id");
		q.setParameter("degree_id", Integer.parseInt(degree_id));
		@SuppressWarnings("unchecked")
		List<String> clist = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

//	public @ResponseBody List<EDU_LMS_TERM_MASTER> gettermList(SessionFactory sessionFactory) {
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery(
//				"select distinct term_id, prof_name from TB_SET_MASTER where status='1' order by term_id ");
//
//		@SuppressWarnings("unchecked")
//		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}
	
	public @ResponseBody List<EDU_LMS_TERM_MASTER> gettermList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select distinct term,prof_name from EDU_LMS_TERM_MASTER where status='1' order by term ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<EDU_LMS_TERM_MASTER> NCISMgettermList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select distinct id,prof_name from EDU_LMS_TERM_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getDegreeListbysystem", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem(SessionFactory sessionFactory,
			String system_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select d.id,d.degree_name from EDU_LMS_SYS_DEG_MAP_MASTER sdm ,EDU_LMS_DEGREE_MASTER d where sdm.status='1' and d.id = sdm.degree_name  and system_name=:system_name order by sdm.id");
		q.setParameter("system_name", Integer.parseInt(system_name));
		@SuppressWarnings("unchecked")

		List<EDU_LMS_SYS_DEG_MAP_MASTER> clist = (List<EDU_LMS_SYS_DEG_MAP_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public int saveFunction(Object obj) {
		Session session1 = sessionFactory.getSessionFactory().openSession();
		session1.beginTransaction();
		int id = (Integer) session1.save(obj);
		session1.getTransaction().commit();
		session1.close();
		return id;
	}

	public @ResponseBody List<EDU_LMS_TYPE_OF_CONTENT_MSTR> getTypeOfcontent(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select id,type_of_content from EDU_LMS_TYPE_OF_CONTENT_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TYPE_OF_CONTENT_MSTR> clist = (List<EDU_LMS_TYPE_OF_CONTENT_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_LEVEL_MSTR> getlevel_Ofcontentlist(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id,level_name from EDU_LMS_LEVEL_MSTR where status='1' order by id ");
		@SuppressWarnings("unchecked")
		List<EDU_LMS_LEVEL_MSTR> clist = (List<EDU_LMS_LEVEL_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getfreecoursename", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_FREE_COURSE> getfreecoursename(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(" from EDU_LMS_FREE_COURSE WHERE id!=0 ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_FREE_COURSE> clist = (List<EDU_LMS_FREE_COURSE>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	@RequestMapping(value = "/getStudentlist", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UPLODE_CERTIFICATE> getStudentlist(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(" from EDU_LMS_UPLODE_CERTIFICATE WHERE id!=0 ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UPLODE_CERTIFICATE> clist = (List<EDU_LMS_UPLODE_CERTIFICATE>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	@RequestMapping(value = "/getgenderList", method = RequestMethod.POST)
	public @ResponseBody List<String> getgenderList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select id, gender_name from EDU_LMS_GENDER_MSTR where status='1' order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public @ResponseBody List<Document_mst> getDocList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id,doc_name from Document_mst order by id ");
		// Query q = sessionHQL.createQuery("from Document_mst where status='1' order by
		// id ");

		@SuppressWarnings("unchecked")
		List<Document_mst> clist = (List<Document_mst>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public List<String> getInstitudeState(SessionFactory sessionFactory, String institude) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select cast(state_id as text) from EDU_LMS_INSTITUTE_REGISTRATION where id=:institude");
			q1.setParameter("institude", Integer.parseInt(institude));
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public @ResponseBody List<EDU_LMS_PAPER_GENERATION> getExamList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("SELECT id,exam_name from EDU_LMS_PAPER_GENERATION where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_PAPER_GENERATION> clist = (List<EDU_LMS_PAPER_GENERATION>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getModuleList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_MODULE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_MODULE_MSTR> clist = (List<EDU_LMS_MODULE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "admin/getcourselistby_degree", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcourselistby_degree(Integer degree_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select ecm.id,upper(cm.course_name) from EDU_LMS_ELECTIVE_COURSE_MASTER ecm,EDU_LMS_COURSE_MASTER cm\n"
						+ " where cm.id=cast(ecm.course_name as integer) and ecm.degree_id=:degree_id and ecm.status='1' order by cm.course_name");
		q.setParameter("degree_id", degree_id);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "admin/getcourselistby_degree_andsystem", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcourselistby_degree_andsystem(String degree_id,
			String system_id) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select ecm.id,cm.course_name from EDU_LMS_ELECTIVE_COURSE_MASTER ecm,EDU_LMS_COURSE_MASTER cm,EDU_LMS_SYSTEM_ELE_COURSE_LINK ecl\n"
						+ "where cm.id=cast(ecm.course_name as integer) and ecl.elec_course_id=ecm.id and ecl.degree_id=:degree_id and ecl.system_id=:system_id");
		q.setParameter("degree_id", Integer.parseInt(degree_id)).setParameter("system_id", Integer.parseInt(system_id));

		@SuppressWarnings("unchecked")
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}
//	public @ResponseBody List<TB_POLICYCATEGORY_MASTER> getPolicyCategoryList(SessionFactory sessionFactory2) {
//		Session sessionHQL = sessionFactory2.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery("from TB_POLICYCATEGORY_MASTER where status='1' order by id ");
//		
//		@SuppressWarnings("unchecked")
//		List<TB_POLICYCATEGORY_MASTER> clist = (List<TB_POLICYCATEGORY_MASTER>) q.list();
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}

//	public @ResponseBody List<TB_SUBPOLICYCATEGORY> getSubPolicyCategoryList(SessionFactory sessionFactory2) {
//		Session sessionHQL = sessionFactory2.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery("from TB_SUBPOLICYCATEGORY where status='1' order by id ");
//		
//		@SuppressWarnings("unchecked")
//		List<TB_SUBPOLICYCATEGORY> clist = (List<TB_SUBPOLICYCATEGORY>) q.list();
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}
//	
	public Date convertStringToDate(String sdate) {
		Date date = null;
		try {
			if (sdate.contains("/")) {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
			}
			if (sdate.contains("-")) {
				date = new SimpleDateFormat("dd-MM-yyyy").parse(sdate);
			}
		} catch (ParseException e) {
			System.out.println("convertStringToDate : " + e.getMessage());
		}
		return date;
	}

	// ====================================DOCUMENT_NAME_DROPDOWN=========================

	@RequestMapping(value = "/getDoc_nameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getDoc_nameList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select codevalue,label from T_Domain_Value  where domainid='DOC_NAME' order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	// ====================================ACADEMIC_DROPDOWN=========================

	@RequestMapping(value = "/getAcademicList", method = RequestMethod.POST)
	public @ResponseBody List<String> getAcademicList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select codevalue,label from T_Domain_Value  where domainid='ACADEMIC' order by label");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/getclgNameDataList", method = RequestMethod.POST)
	public @ResponseBody List<TB_COLLAGE_MSTR> getclgNameDataList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select distinct id, collage_name from TB_COLLAGE_MSTR order by id ");

		@SuppressWarnings("unchecked")
		List<TB_COLLAGE_MSTR> clist = (List<TB_COLLAGE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public String fileupload(byte[] b, String name, int id, String type) {
		String extension = "";
		String fname = "";
		try {
			byte[] bytes = b; // file.getBytes();
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "CRPF_Document//" + id);
			File dir = new File("/srv/student_Document/" + id);
			if (!dir.exists())
				dir.mkdirs();

			String filename = name; // file.getOriginalFilename();

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}

			// Create the file on server
			// java.util.Date date= new java.util.Date();
			// fname = dir.getAbsolutePath() + File.separator+""+type+"__" + (new
			// Timestamp(date.getTime())).toString().replace(":",
			// "").toString().replace(".", ".").toString().replace("
			// ","").toString().replace("-","").toString()+ "."+extension;
			fname = dir.getAbsolutePath() + File.separator + "" + type + "__" + currentDateWithTimeStampString() + "."
					+ extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fname;
	}

	@RequestMapping(value = "admin/getDegreefromSystem", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DEGREE_MASTER> getDegreefromSystem(String system_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select d.id,d.degree_name from EDU_LMS_SYS_DEG_MAP_MASTER sdm ,EDU_LMS_DEGREE_MASTER d where sdm.status='1' and d.status='1' and d.id = sdm.degree_name  and system_name=:system_name order by sdm.id ");
		q.setParameter("system_name", Integer.parseInt(system_id));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_MASTER> clist = (List<EDU_LMS_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "admin/getcoursefromdegree", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcoursefromdegree(String degree_id,String type_of_content) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select ecm.course_name,cm.course_name \n"
				+ "from  EDU_LMS_ELECTIVE_COURSE_MASTER ecm ,EDU_LMS_COURSE_MASTER cm ,EDU_LMS_TYPE_OF_CONTENT_MSTR tcm\n"
				+ "where cast(ecm.course_name as integer)=cm.id and ecm.degree_id=:degree_id and tcm.id=:type_of_content\n"
				+ "order by cm.course_name ");
		q.setParameter("degree_id", Integer.parseInt(degree_id));
		q.setParameter("type_of_content", Integer.parseInt(type_of_content));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}


	@RequestMapping(value = "admin/getmodulefromcourse", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getmodulefromcourse(String course_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				" select cast(mm.id as text),mm.module_name from EDU_LMS_MODULE_MSTR mm,EDU_LMS_ELECTIVE_COURSE_MASTER ecm where \n"
						+ " mm.course_id=ecm.id and course_name=:course_id");
		q.setParameter("course_id", course_id);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_MODULE_MSTR> clist = (List<EDU_LMS_MODULE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getDegreeListbysemester", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DEGREE_MASTER> getDegreeListbysemester(String degree_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select id,semester from EDU_LMS_DEGREE_MASTER where status='1' and id=:id order by id");
		q.setParameter("id", Integer.parseInt(degree_id));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_MASTER> clist = (List<EDU_LMS_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getProfListbysemester", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DEGREE_MASTER> getProfListbysemester(String degree_id) {
		
		System.err.println("------------deg "+degree_id);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select distinct tm.id,tm.prof_name from EDU_LMS_TERM_MASTER tm, EDU_LMS_DEGREE_MASTER dm where cast(dm.semester as text)=cast(tm.id as text) and  \n"
						+ "tm.status='1' and dm.id=:id order by tm.id");
		q.setParameter("id", Integer.parseInt(degree_id));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_MASTER> clist = (List<EDU_LMS_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getUniverCityList", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniverCityList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_UNIVERSITY_MSTR order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getUniversityList", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversityList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select distinct id, university_name from EDU_LMS_UNIVERSITY_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
//	@RequestMapping(value = "/getUniversityListbySystem", method = RequestMethod.POST)
//	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversityListbySystem(SessionFactory sessionFactory,String system_id ) {
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL
//				.createQuery("select distinct id, university_name from EDU_LMS_UNIVERSITY_MSTR where status='1' and system_id=:system_id order by id ");
//
//		q.setParameter("system_id", Integer.parseInt(system_id));
//		@SuppressWarnings("unchecked")
//		List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}
	
//			@RequestMapping(value = "/sub_access_lvl", method = RequestMethod.POST)
//			public @ResponseBody List<Role> sub_access_lvl(SessionFactory sessionFactory) {
//				Session sessionHQL = sessionFactory.openSession();
//				Transaction tx = sessionHQL.beginTransaction();
//				Query q = sessionHQL.createQuery("select role_id, sub_access_lvl from  Role  where staff_lvl='NCH'");
//				
//				@SuppressWarnings("unchecked")
//				List<Role> clist = (List<Role>) q.list();
//				tx.commit();
//				sessionHQL.close();
//				return clist;
//			}

	public @ResponseBody List<EDU_LMS_TEACHER_DTL> getTeacherList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id,first_name from EDU_LMS_TEACHER_DTL ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TEACHER_DTL> clist = (List<EDU_LMS_TEACHER_DTL>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/kmlLOFileDownload666")
	public void kmlLOFileDownload666(@ModelAttribute("kmlFileId65") String id,
			@ModelAttribute("fildname") String fildname, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath = "";

		filePath = cmdao.getdemoVideoPath(Integer.parseInt(id), Integer.parseInt(fildname));

//				System.out.println("filePath----------------" + filePath);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();

		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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

			String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
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

	public @ResponseBody List<UserLogin> getAllInfoLogin(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q1 = sessionHQL.createQuery("from UserLogin where userid=:userid");

		@SuppressWarnings("unchecked")
		List<UserLogin> clist = (List<UserLogin>) q1.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<UserLogin> getAllInfoLogin(SessionFactory sessionFactory, String userid) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q1 = sessionHQL.createQuery("from UserLogin where userid=:userid");
		q1.setParameter("userid", Integer.parseInt(userid));
		@SuppressWarnings("unchecked")
		List<UserLogin> clist = (List<UserLogin>) q1.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_COURSE_MASTER> getCourseList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_COURSE_MASTER where status='1' order by course_name ");
		@SuppressWarnings("unchecked")
		List<EDU_LMS_COURSE_MASTER> clist = (List<EDU_LMS_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@GetMapping(value = "/admin/video/{p_id}", produces = "video/mp4")
	public @ResponseBody Mono<Resource> getVideo(@PathVariable String p_id, @RequestHeader("Range") String range,
			HttpServletRequest request) throws FileNotFoundException {

		String filePath = cmdao.getTopicVideoPath2(Integer.parseInt(p_id.split(",")[0]),
				Integer.parseInt(p_id.split(",")[1]));
		return service.getVideo(filePath);
	}

	
	public @ResponseBody List<recr_category_mst> getcastcategorylist(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q1 = sessionHQL.createQuery("select id,category from EDU_LMS_CATEGORY_MSTR where status='1' order by id");
		
		@SuppressWarnings("unchecked")
		List<recr_category_mst> clist = (List<recr_category_mst>) q1.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_CATEGORY_MSTR> getcategoryList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_CATEGORY_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_CATEGORY_MSTR> clist = (List<EDU_LMS_CATEGORY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_LMS_RELIGION_MSTR> getreligionList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_LMS_RELIGION_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_LMS_RELIGION_MSTR> clist = (List<TB_LMS_RELIGION_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_LMS_MARITAL_STATUS_MSTR> getmaritalstatusList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_LMS_MARITAL_STATUS_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_LMS_MARITAL_STATUS_MSTR> clist = (List<TB_LMS_MARITAL_STATUS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_LEVEL_MSTR> getsequenceList(SessionFactory sessionFactory, String lvl_module) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_LEVEL_MSTR where id=:id  and status='1' ");
		q.setParameter("id", Integer.parseInt(lvl_module));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_LEVEL_MSTR> clist = (List<EDU_LMS_LEVEL_MSTR>) q.list();
		System.out.println("list          " + clist);
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public List<String> getCoursePubName(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL
					.createQuery("select id,name from EDU_LMS_COURSE_PUBLISHER_MSTR where status='1' order  by name");
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public @ResponseBody List<EDU_DOC_ATTACHMENTS_MSTR> getnameofDoc(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_DOC_ATTACHMENTS_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_DOC_ATTACHMENTS_MSTR> clist = (List<EDU_DOC_ATTACHMENTS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	public @ResponseBody List<EDU_FACULTY_COURSE_MSTR> getFMcourseList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_FACULTY_COURSE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_FACULTY_COURSE_MSTR> clist = (List<EDU_FACULTY_COURSE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_FACULTY_SUBJECT_MSTR> getSubjectList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_FACULTY_SUBJECT_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_FACULTY_SUBJECT_MSTR> clist = (List<EDU_FACULTY_SUBJECT_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_TERM_MASTER> gettermListforSet(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select distinct tm.id,tm.prof_name from EDU_LMS_TERM_MASTER tm  where tm.status='1' order by tm.id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_UNIVERSITY_TYPE_MSTR> getUniversityTypelList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_UNIVERSITY_TYPE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_TYPE_MSTR> clist = (List<EDU_LMS_UNIVERSITY_TYPE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<EDU_LMS_ORGANIZATION_MSTR> getOrganizationList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_ORGANIZATION_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_ORGANIZATION_MSTR> clist = (List<EDU_LMS_ORGANIZATION_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

////////////////term

	public @ResponseBody List<EDU_LMS_TERM_MASTER> getTerm() {
		Session sessionHQL = sessionFactory.openSession();

		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_TERM_MASTER where status='1' order by id");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	// ====================================DOCUMENT_NAME postgraduation
	// _DROPDOWN=========================

	@RequestMapping(value = "/getDoc_name_pgList", method = RequestMethod.POST)
	public @ResponseBody List<String> getDoc_name_pgList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id,doc_name from EDU_PG_DOC_UPLOAD_MSTR  where status=1 order by id");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@RequestMapping(value = "/getCourselistbySet", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_COURSE_DURATION> getCourselistbySet(SessionFactory sessionFactory,
			String course_id) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Query q = sessionHQL.createQuery(
				"select distinct sm.id,sm.setname from EDU_LMS_SYSTEM_COURSE_DURATION cd,EDU_LMS_COURSE_MASTER cm,EDU_LMS_ELECTIVE_COURSE_MASTER ecm,\n"
						+ "EDU_LMS_LINK_COURSE_SET_MSTR_CHILD smc,TB_SET_MASTER sm where cm.id = cd.course_id and cast(ecm.course_name as text)=cast(cm.id as text) and smc.course_id=ecm.id\n"
						+ "and sm.id=smc.set_id and cm.status='1' and cd.course_id=:id");
		q.setParameter("id", Integer.parseInt(course_id));
		@SuppressWarnings("unchecked")

		List<EDU_LMS_SYSTEM_COURSE_DURATION> clist = (List<EDU_LMS_SYSTEM_COURSE_DURATION>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getCourselistbyModule", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getCourselistbyModule(SessionFactory sessionFactory,
			String course_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Query q = sessionHQL.createQuery(
				"select distinct mm.id,mm.module_name from EDU_LMS_MODULE_MSTR mm,EDU_LMS_ELECTIVE_COURSE_MASTER ecm,EDU_LMS_COURSE_MASTER cm where ecm.id=mm.course_id\n"
						+ "			and cast(cm.id as text) = cast(ecm.course_name as text) and cm.id =:course_id");
		q.setParameter("course_id", Integer.parseInt(course_id));
		@SuppressWarnings("unchecked")

		List<EDU_LMS_MODULE_MSTR> clist = (List<EDU_LMS_MODULE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}

	public @ResponseBody List<TB_NCH_DESIGNATION_MSTR> getDesignationList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_NCH_DESIGNATION_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_NCH_DESIGNATION_MSTR> clist = (List<TB_NCH_DESIGNATION_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_NCH_SALARY_STATUS_MSTR> getsalary_statusList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_NCH_SALARY_STATUS_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_NCH_SALARY_STATUS_MSTR> clist = (List<TB_NCH_SALARY_STATUS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_NCH_EMPLOYEMENT_TYPE_MSTR> getemploymnt_typeList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_NCH_EMPLOYEMENT_TYPE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_NCH_EMPLOYEMENT_TYPE_MSTR> clist = (List<TB_NCH_EMPLOYEMENT_TYPE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_NCH_PREFIX_MSTR> getPrefixList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_NCH_PREFIX_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_NCH_PREFIX_MSTR> clist = (List<TB_NCH_PREFIX_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	public @ResponseBody List<TB_NCH_REGISTRATION_TYPE_MSTR> getregistrationtypeList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_NCH_REGISTRATION_TYPE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<TB_NCH_REGISTRATION_TYPE_MSTR> clist = (List<TB_NCH_REGISTRATION_TYPE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getcoursebystudentEnroll", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcoursebystudentEnroll(SessionFactory sessionFactory,
			String user_id) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select cm.id,cm.course_name from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD sch,EDU_LMS_ELECTIVE_COURSE_MASTER ecm,EDU_LMS_COURSE_MASTER cm\n"
						+ "	where ecm.id=sch.course_id and cast(cm.id as text)=cast(ecm.course_name as text) and sch.user_id=:userid ");
		q.setParameter("userid", Integer.parseInt(user_id));
		@SuppressWarnings("unchecked")
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	public @ResponseBody List<EDU_FACULTY_COURSE_MSTR> getFacultyCourseList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_FACULTY_COURSE_MSTR where status='1' order by course_name ");
		@SuppressWarnings("unchecked")
		List<EDU_FACULTY_COURSE_MSTR> clist = (List<EDU_FACULTY_COURSE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<TB_LDAP_MODULE_MASTER> getScreenModuleList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_LDAP_MODULE_MASTER order by module_name");
		@SuppressWarnings("unchecked")
		List<TB_LDAP_MODULE_MASTER> clist = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR> getFeedCategoryList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR where status='1' order by id");
		@SuppressWarnings("unchecked")
		List<EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR> clist = (List<EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	public @ResponseBody List<EDU_DIVISION_MSTR> getDivisionList(SessionFactory sessionFactory, String role) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Query q = sessionHQL
				.createQuery("from EDU_DIVISION_MSTR where status='1' and created_role=:role order by id ");
		q.setParameter("role", role);
		
		@SuppressWarnings("unchecked")
		List<EDU_DIVISION_MSTR> dlist = (List<EDU_DIVISION_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return dlist;
	}
	public @ResponseBody List<TB_KNOWLEDGE_REPO_CATEGORY_MSTR> getCategoryList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_KNOWLEDGE_REPO_CATEGORY_MSTR order by category_repo");
		@SuppressWarnings("unchecked")
		List<TB_KNOWLEDGE_REPO_CATEGORY_MSTR> clist = (List<TB_KNOWLEDGE_REPO_CATEGORY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}


//	------------------------Strt------------Curriculum------------------------------------------------
	
//	public List<String> getCourseList(SessionFactory sessionFactory) {
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx1 = sessionHQL.beginTransaction();
//		try {
//			Query q1 = sessionHQL.createQuery(
//					"select id,course_name from EDU_LMS_COURSE_MASTER where status='1' order  by id");
//			List<String> list = (List<String>) q1.list();
//			tx1.commit();
//			return list;
//		} catch (RuntimeException e) {
//			return null;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//	}
	
//	public List<String> getCourseList(SessionFactory sessionFactory) {
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx1 = sessionHQL.beginTransaction();
//		try {
//			Query q1 = sessionHQL.createQuery(
//					"select e.id,c.course_name  from EDU_LMS_ELECTIVE_COURSE_MASTER e,EDU_LMS_COURSE_MASTER c where  c.id = cast(e.course_name as integer)"
//					+ "and e.status='1' order  by e.id");
//			List<String> list = (List<String>) q1.list();
//			tx1.commit();
//			return list;
//		} catch (RuntimeException e) {
//			return null;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//	}
	
	public @ResponseBody List<CC_TB_GRADUATE_ATTRIBUTES_MSTR> getGraduateattribute(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select g.id,concat(g.code,'-',g.graduate_attributes) as ga from CC_TB_GRADUATE_ATTRIBUTES_MSTR g where status='1' order by id asc");

		@SuppressWarnings("unchecked")
		List<CC_TB_GRADUATE_ATTRIBUTES_MSTR> clist = (List<CC_TB_GRADUATE_ATTRIBUTES_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
		}

	public @ResponseBody List<String> getProgramoutcome(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select pm.id,concat(pm.code,'-',pm.program_outcome) as po from CC_TB_PROGRAM_OUTCOME_MSTR pm where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<String> clist = (List<String>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_PROFESSIONAL_MSTR> getprofessionalList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_PROFESSIONAL_MSTR where status='1' ORDER BY id ASC  ");

		@SuppressWarnings("unchecked")
		List<CC_TB_PROFESSIONAL_MSTR> clist = (List<CC_TB_PROFESSIONAL_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

//	@RequestMapping(value = "admin/getcourselistby_degree_andsystem", method = RequestMethod.POST)
//	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcourselistby_degree_andsystem(String degree_id,String system_id) {
//		
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL
//				.createQuery("select ecm.id,cm.course_name from EDU_LMS_ELECTIVE_COURSE_MASTER ecm,EDU_LMS_COURSE_MASTER cm,EDU_LMS_SYSTEM_ELE_COURSE_LINK ecl\n"
//						+ "where cm.id=cast(ecm.course_name as integer) and ecl.elec_course_id=ecm.id and ecl.degree_id=:degree_id and ecl.system_id=:system_id");
//		q.setParameter("degree_id", Integer.parseInt(degree_id)).setParameter("system_id", Integer.parseInt( system_id));
//		@SuppressWarnings("unchecked")
//		List<EDU_LMS_ELECTIVE_COURSE_MASTER> clist = (List<EDU_LMS_ELECTIVE_COURSE_MASTER>) q.list();
//		
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}
	
	public @ResponseBody List<CC_TB_PAPER_MASTER> getpaperList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_PAPER_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_PAPER_MASTER> clist = (List<CC_TB_PAPER_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<EDU_LMS_TERM_MASTER> getCCTermList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_TERM_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_TYPE_OF_TEACHING_MSTR> getTypeofTeachingList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_TYPE_OF_TEACHING_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_TYPE_OF_TEACHING_MSTR> clist = (List<CC_TB_TYPE_OF_TEACHING_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_TYPE_OF_HOURS_MSTR> getTypeofHoursList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_TYPE_OF_HOURS_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_TYPE_OF_HOURS_MSTR> clist = (List<CC_TB_TYPE_OF_HOURS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
//	@RequestMapping(value = "/getTopicListbyCourse", method = RequestMethod.POST)
//	public @ResponseBody List<CC_TB_LIST_OF_TOPICS_PARENT> getTopicListbyCourse(SessionFactory sessionFactory,
//			String course_id) {
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery(
//				"select tm.id,tm.topic from CC_TB_LIST_OF_TOPICS_PARENT p ,CC_TB_LIST_OF_TOPICS_CHILD c,CC_TB_TOPICS_MSTR tm where "
//				+ " c.p_id=p.id and tm.id = c.topic_id and p.course_id=:course_id ");
//		q.setParameter("course_id", Integer.parseInt(course_id));
//		@SuppressWarnings("unchecked")
//
//		List<CC_TB_LIST_OF_TOPICS_PARENT> clist = (List<CC_TB_LIST_OF_TOPICS_PARENT>) q.list();
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}
	
	public @ResponseBody List<CC_TB_ADD_COURSE_OUTCOME_MSTR> getCourse_OutcomeList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_ADD_COURSE_OUTCOME_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_ADD_COURSE_OUTCOME_MSTR> clist = (List<CC_TB_ADD_COURSE_OUTCOME_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_C3_DOMAIN_MSTR> getc3_domain_subList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_C3_DOMAIN_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_C3_DOMAIN_MSTR> clist = (List<CC_TB_C3_DOMAIN_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR> getd3_desirable_knowList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR> clist = (List<CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR> gete3_level_show_knowList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR> clist = (List<CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR> getf3_t_l_methodList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR> clist = (List<CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_G3_ASSESSMENT_METHOD_MSTR> getg3_assessmentList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_G3_ASSESSMENT_METHOD_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_G3_ASSESSMENT_METHOD_MSTR> clist = (List<CC_TB_G3_ASSESSMENT_METHOD_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_H3_ASSESSMENT_TYPE_MSTR> geth3_formative_summativeList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_H3_ASSESSMENT_TYPE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_H3_ASSESSMENT_TYPE_MSTR> clist = (List<CC_TB_H3_ASSESSMENT_TYPE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_I3_TERM_MSTR> geti3_termList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_I3_TERM_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_I3_TERM_MSTR> clist = (List<CC_TB_I3_TERM_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_TOPICS_MSTR> getTopicList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_TOPICS_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_TOPICS_MSTR> clist = (List<CC_TB_TOPICS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT> getprofessionallistbydegree(SessionFactory sessionFactory, String degree_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT> clist = (List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT> getcourselistbyprofessionalanddegree(SessionFactory sessionFactory, String professional, String degree_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT> clist = (List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "admin/getprofession_listbydegree", method = RequestMethod.POST)
	public @ResponseBody List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT> getprofession_listbydegree(String degree_id) {
		
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("select distinct pm.id,pm.professional from EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT p,CC_TB_PROFESSIONAL_MSTR pm\n"
						+ "where pm.id = p.professional_id and p.degree_id=:degree_id ORDER BY pm.id DESC");
		
		q.setParameter("degree_id", Integer.parseInt(degree_id));
		@SuppressWarnings("unchecked")
		List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT> clist = (List<EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT>) q.list();
		
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_SUB_TOPICS_MSTR> getSubTopicList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_SUB_TOPICS_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_SUB_TOPICS_MSTR> clist = (List<CC_TB_SUB_TOPICS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getsubtopic_listbytopic", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_SUB_TOPICS_MSTR> getsubtopic_listbytopic(SessionFactory sessionFactory,String topic_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select id,sub_topic from CC_TB_SUB_TOPICS_MSTR  where status='1' and topic_id=:topic_id order by id");
		q.setParameter("topic_id", Integer.parseInt(topic_id));
		@SuppressWarnings("unchecked")

		List<CC_TB_SUB_TOPICS_MSTR> clist = (List<CC_TB_SUB_TOPICS_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getGA_by_Degree", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM> getGA_by_Degree(String degree_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select gm.id, concat(gm.code,'-',gm.graduate_attributes) as graduate_attributes  from CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM gs"
				+ " ,EDU_LMS_DEGREE_MASTER dm,CC_TB_GRADUATE_ATTRIBUTES_MSTR gm where "
				+ " dm.id = gs.degree and gm.id = gs.graduate_attribute and dm.id=:degree_id and gs.status=1");
		q.setParameter("degree_id", Integer.parseInt(degree_id));
		@SuppressWarnings("unchecked")

		List<CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM> clist = (List<CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	@RequestMapping(value = "/getPO_listby_Degree", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE> getPO_listby_Degree(String degree_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select distinct pm.id,concat(pm.code,'-',pm.program_outcome) as po from CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE psd"
				+ " ,EDU_LMS_DEGREE_MASTER dm,CC_TB_PROGRAM_OUTCOME_MSTR pm where "
				+ "  dm.id = psd.degree and pm.id = psd.program_outcome and dm.id=:degree_id and psd.status=1");
		q.setParameter("degree_id", Integer.parseInt(degree_id));
		@SuppressWarnings("unchecked")

		List<CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE> clist = (List<CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
//		public @ResponseBody List<String> getInstListFromSystem(String degree_id) {
//			Session sessionHQL = sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//			Query q = sessionHQL
//			.createQuery("select cast(institute_id as text) from EDU_LMS_SYS_DEG_MAP_INST where degree_id=:degree_id");
//			q.setParameter("degree_id", Integer.parseInt( degree_id));
//			@SuppressWarnings("unchecked")
//			List<String> clist = (List<String>) q.list();
//			tx.commit();
//			sessionHQL.close();
//			return clist;
//		}
//		
//		public @ResponseBody List<EDU_LMS_TYPE_OF_CONTENT_MSTR> getTypeOfcontent(SessionFactory sessionFactory) {
//			Session sessionHQL = sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//			Query q = sessionHQL.createQuery(
//					"select id,type_of_content from EDU_LMS_TYPE_OF_CONTENT_MSTR where status='1' order by id ");
//
//			@SuppressWarnings("unchecked")
//			List<EDU_LMS_TYPE_OF_CONTENT_MSTR> clist = (List<EDU_LMS_TYPE_OF_CONTENT_MSTR>) q.list();
//			tx.commit();
//			sessionHQL.close();
//			return clist;
//		}
		
		@RequestMapping(value = "admin/getcourselistby_professional", method = RequestMethod.POST)
		public @ResponseBody List<CC_TB_LIST_OF_TOPICS_PARENT> getcourselistby_professional(SessionFactory sessionFactory,String professional_id) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("select cm.id,cm.course_name from CC_TB_LIST_OF_TOPICS_PARENT tp,"
							+ "EDU_LMS_ELECTIVE_COURSE_MASTER ec,EDU_LMS_COURSE_MASTER cm,CC_TB_PROFESSIONAL_MSTR pm\n"
							+ "where ec.id=tp.course_id and cm.id=cast(ec.course_name as integer)  and pm.id=tp.professional_id and tp.professional_id=:professional_id");
			
			q.setParameter("professional_id", Integer.parseInt(professional_id));
			@SuppressWarnings("unchecked")
			List<CC_TB_LIST_OF_TOPICS_PARENT> clist = (List<CC_TB_LIST_OF_TOPICS_PARENT>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
			
		}
		
//		@RequestMapping(value = "/getDegreeListbysystem", method = RequestMethod.POST)
//		public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem(String system_name) {
//			Session sessionHQL = sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//			Query q = sessionHQL.createQuery(
//					"select d.id,d.degree_name from EDU_LMS_SYS_DEG_MAP_MASTER sdm ,EDU_LMS_DEGREE_MASTER d where sdm.status='1' and d.id = sdm.degree_name  and system_name=:system_name order by sdm.id");
//			q.setParameter("system_name", Integer.parseInt(system_name));
//			@SuppressWarnings("unchecked")
//
//			List<EDU_LMS_SYS_DEG_MAP_MASTER> clist = (List<EDU_LMS_SYS_DEG_MAP_MASTER>) q.list();
//			tx.commit();
//			sessionHQL.close();
//			return clist;
//		}
		
		@RequestMapping(value = "/getTopicListbyCourse", method = RequestMethod.POST)
		public @ResponseBody List<CC_TB_LIST_OF_TOPICS_PARENT> getTopicListbyCourse(
				String course_id) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery(
					"select  t.id,t.topic from CC_TB_LIST_OF_TOPICS_PARENT p ,CC_TB_LIST_OF_TOPICS_CHILD c,CC_TB_TOPICS_MSTR t "
					+ "where c.p_id = p.id and t.id = c.topic_id and p.course_id=:course_id and t.status=1 and p.status=0 and c.status=0 ");
			q.setParameter("course_id", Integer.parseInt(course_id));
			@SuppressWarnings("unchecked")

			List<CC_TB_LIST_OF_TOPICS_PARENT> clist = (List<CC_TB_LIST_OF_TOPICS_PARENT>) q.list();
			tx.commit();
			
			sessionHQL.close();
			
			return clist;
		}
		
		//Fetch Only Main Course by system-degree-professional
		///getCourseList=========================

		@RequestMapping(value = "admin/getCourseList_for_Curri", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_COURSE_MASTER> getCourseList_for_Curri(
				String system_id,String degree_id,String professional_id) {
			
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			String qry = " select distinct cm.id,cm.course_name \n"
					+ " from EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE m , EDU_LMS_COURSE_MASTER cm \n"
					+ " where cm.id=m.course_id and m.status=1 ";
			
			String system_qry = " and m.system_id=:system_id ";
			String degree_qry = " and m.degree_id=:degree_id ";
			String prof_qry = " and m.professional_id=:professional_id ";
			
			if(system_id != null && system_id!="") {
				qry += system_qry;
			}
			if(degree_id != null && degree_id!="") {
				qry += degree_qry;
			}
			if(professional_id != null  && professional_id!="") {
				qry += prof_qry;
			}
			
			Query q = sessionHQL.createQuery(qry);
			
			if(system_id != null && system_id!="") {
				q.setParameter("system_id", Integer.parseInt(system_id));
			}
			if(degree_id != null && degree_id!="") {
				q.setParameter("degree_id", Integer.parseInt(degree_id));
			}
			if(professional_id != null  && professional_id!="") {
				q.setParameter("professional_id", Integer.parseInt(professional_id));
			}
			
			@SuppressWarnings("unchecked")
			List<EDU_LMS_COURSE_MASTER> clist = (List<EDU_LMS_COURSE_MASTER>) q.list();
			
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		// Fetch all main course list 
		

public List<String> getMainCourseList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx1 = sessionHQL.beginTransaction();
			try {
				Query q1 = sessionHQL.createQuery(
						"select distinct id ,course_name from EDU_LMS_COURSE_MASTER p \n"
						+ " where p.type_of_content_id=5 and p.status='1' ORDER BY p.id DESC");
				List<String> list = (List<String>) q1.list();
				tx1.commit();
				return list;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		
@RequestMapping(value = "/getPracticalListby_Course1", method = RequestMethod.POST)
public @ResponseBody List<CC_TB_LIST_OF_PRACTICAL_PARENT> getPracticalListby_Course1(String course_id) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	Query q = sessionHQL.createQuery(
			"select c.id,concat(c.practical,'-',c.activity_practical_description) from CC_TB_LIST_OF_PRACTICAL_PARENT p ,CC_TB_LIST_OF_PRACTICAL_CHILD c "
			+ " where c.p_id=p.id and p.course_id=:course_id and c.status=0 ");
	q.setParameter("course_id", Integer.parseInt(course_id));
	@SuppressWarnings("unchecked")

	List<CC_TB_LIST_OF_PRACTICAL_PARENT> clist = (List<CC_TB_LIST_OF_PRACTICAL_PARENT>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}
		// CO from course
		
		@RequestMapping(value = "admin/getCobyCourse", method = RequestMethod.POST)
		public @ResponseBody List<CC_TB_ADD_COURSE_OUTCOME_MSTR> getCobyCourse(String course_id) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("select c.id,concat(c.co_code,'-',c.course_outcome) as co from CC_TB_ADD_COURSE_OUTCOME_MSTR c where c.course_id=:course_id and c.status=1 order by co");
			
			q.setParameter("course_id", Integer.parseInt(course_id));
			@SuppressWarnings("unchecked")
			List<CC_TB_ADD_COURSE_OUTCOME_MSTR> clist = (List<CC_TB_ADD_COURSE_OUTCOME_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		public @ResponseBody List<CC_TB_PAPER_MASTER> getPaperList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from CC_TB_PAPER_MASTER where status='1' order by paper ");

			@SuppressWarnings("unchecked")
			List<CC_TB_PAPER_MASTER> clist = (List<CC_TB_PAPER_MASTER>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
			public @ResponseBody List<EDU_CC_TB_QUESTION_TYPE_MSTR> getQuestionList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from EDU_CC_TB_QUESTION_TYPE_MSTR where status='1' order by question_type ");

			@SuppressWarnings("unchecked")
			List<EDU_CC_TB_QUESTION_TYPE_MSTR> clist = (List<EDU_CC_TB_QUESTION_TYPE_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
			// Get PO by ID from PO Mstr
			
			@RequestMapping(value = "/Pro_out_Data_Url", method = RequestMethod.POST)
			public @ResponseBody   ArrayList<ArrayList<String>> Pro_out_Data_Url(String hid) {
			 ArrayList<ArrayList<String>> list = cmdao.get_Po_Datalist(hid);
				return list;
			}
			
			public @ResponseBody List<CC_TB_EXAM_TYPE_MSTR> getExam_Type(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select id,exam_type from CC_TB_EXAM_TYPE_MSTR where status='1' order by id ");

				@SuppressWarnings("unchecked")
				List<CC_TB_EXAM_TYPE_MSTR> clist = (List<CC_TB_EXAM_TYPE_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			

			
			@RequestMapping(value = "admin/getCourseToTopic", method = RequestMethod.POST)
			public @ResponseBody List<CC_TB_TOPICS_MSTR> getCourseToTopic(String course_id,String degree_id) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("select distinct id,topic from CC_TB_TOPICS_MSTR where course_id=:course_id and degree_id =: degree_id and status='1'");
				
				q.setParameter("course_id", Integer.parseInt(course_id));
				q.setParameter("degree_id", Integer.parseInt(degree_id));

				@SuppressWarnings("unchecked")
				List<CC_TB_TOPICS_MSTR> clist = (List<CC_TB_TOPICS_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
//			public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemList(SessionFactory sessionFactory, String role) {
//				Session sessionHQL = sessionFactory.openSession();
//				Transaction tx = sessionHQL.beginTransaction();
//
//				Query q = sessionHQL
//						.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' and created_role=:role order by id ");
//				q.setParameter("role", role);
//
//				@SuppressWarnings("unchecked")
//				List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
//				tx.commit();
//				sessionHQL.close();
//				return clist;
//			}
			
			@RequestMapping(value = "/getNCHTopicListbyCourse", method = RequestMethod.POST)
			public @ResponseBody List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT> getNCHTopicListbyCourse(
					String course_id) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select distinct t.id,t.topic from EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT p ,EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD c,CC_TB_TOPICS_MSTR t "
						+ "where c.p_id = p.id and t.id = p.topic_id and p.course_id=:course_id and t.status=1 and p.status=0 and c.status=0 ");
				q.setParameter("course_id", Integer.parseInt(course_id));
				@SuppressWarnings("unchecked")

				List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT> clist = (List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT>) q.list();
				tx.commit();
				
				sessionHQL.close();
				
				return clist;
			}
			@RequestMapping(value = "/getlearning_outcome_listbytopic", method = RequestMethod.POST)
			public @ResponseBody List<CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT> getlearning_outcome_listbytopic(String topic_id) {
				
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						
						"select c.id,c.learning_outcome from CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT p ,CC_TB_NCH_TOPIC_LEARNING_OUTCOME_CHILD c "
						      + "  where c.p_id = p.id and  p.status=1 and c.status=1 and p.topic_id=:topic_id order by c.id");
				
				q.setParameter("topic_id", Integer.parseInt(topic_id));
				@SuppressWarnings("unchecked")

				List<CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT> clist = (List<CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			@RequestMapping(value = "/getDegreelistbySystem_type_Degree_Curri1", method = RequestMethod.POST)
			public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreelistbySystem_type_Degree_Curri1(
					String system_id,String type_of_degree) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select  d.id, d.degree_name from EDU_LMS_SYS_DEG_MAP_MASTER  m ,EDU_LMS_DEGREE_MASTER d where d.status='1' and d.id=m.degree_name "
						+ " and m.system_name=:system_id and d.type_of_degree=:type_of_degree order by m.id ");
				q.setParameter("system_id", Integer.parseInt(system_id));
				q.setParameter("type_of_degree", Integer.parseInt(type_of_degree));
				@SuppressWarnings("unchecked")

				List<EDU_LMS_SYS_DEG_MAP_MASTER> clist = (List<EDU_LMS_SYS_DEG_MAP_MASTER>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			
			///----------End----------------------Curriculum-------------------------------------------------------------
	
	
///----------Strt----------------------Examination-------------------------------------------------------------	
			
			public @ResponseBody List<CC_TB_ATTEMPT_MSTR> getAttemptList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from CC_TB_ATTEMPT_MSTR where status='1' order by id ");

				@SuppressWarnings("unchecked")
				List<CC_TB_ATTEMPT_MSTR> clist = (List<CC_TB_ATTEMPT_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			public @ResponseBody List<EXAM_TB_EXAM_SERIAL_MSTR> getExam_SerialList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from EXAM_TB_EXAM_SERIAL_MSTR where status='1' order by id ");

				@SuppressWarnings("unchecked")
				List<EXAM_TB_EXAM_SERIAL_MSTR> clist = (List<EXAM_TB_EXAM_SERIAL_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
					

			@RequestMapping(value = "/getinstitute_listbyuniversity", method = RequestMethod.POST)
			public @ResponseBody List<UserLogin> getinstitute_listbyuniversity(SessionFactory sessionFactory,String uni_id) {
//			System.err.println("===========uni_id=============="+uni_id);
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select distinct ir.id,ir.institute_name  from UserLogin l ,EDU_LMS_UNIVERSITY_MSTR um, EDU_LMS_INSTITUTE_REGISTRATION ir "
						+ " where um.id=l.university_id and ir.university_id=um.id and um.id=:uni_id ");
				q.setParameter("uni_id", Integer.parseInt(uni_id));
//				System.err.println("===========q=============="+q);
				@SuppressWarnings("unchecked")
				
				List<UserLogin> clist = (List<UserLogin>) q.list();
//				System.err.println("===========clist=============="+clist);
				tx.commit();
				sessionHQL.close();
				return clist;
			}


@RequestMapping(value = "admin/getCourseListByterm", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_COURSE_MASTER> getCourseListByterm(
				String system_id,String degree_id,String professional_id) {
			
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String qry = " select distinct cm.id,cm.course_name \n"
					+ " from EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE m , EDU_LMS_COURSE_MASTER cm,EDU_LMS_TERM_MASTER mm \n"
					+ " where cm.id=m.course_id  and m.status=1 ";
			
			String system_qry = " and m.system_id=:system_id ";
			String degree_qry = " and m.degree_id=:degree_id ";
			String prof_qry = " and mm.term=:term ";
			
			if(system_id != null) {
				qry += system_qry;
			}
			if(degree_id != null) {
				qry += degree_qry;
			}
			if(professional_id != null) {
				qry += prof_qry;
			}
			
			Query q = sessionHQL.createQuery(qry);
			
			if(system_id != null) {
				q.setParameter("system_id", Integer.parseInt(system_id));
			}
			if(degree_id != null ) {
				q.setParameter("degree_id", Integer.parseInt(degree_id));
			}
			if(professional_id != null) {
				q.setParameter("term", professional_id);
			}
			
			@SuppressWarnings("unchecked")
			List<EDU_LMS_COURSE_MASTER> clist = (List<EDU_LMS_COURSE_MASTER>) q.list();
			
			tx.commit();
			sessionHQL.close();
			
			return clist;
		}
		
		public @ResponseBody List<EXAM_RESULT_STATUS> getstudentresultstatuslist(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from EXAM_RESULT_STATUS where status='1' ORDER BY id ASC  ");

			@SuppressWarnings("unchecked")
			List<EXAM_RESULT_STATUS> clist = (List<EXAM_RESULT_STATUS>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}

			
			///----------End----------------------Examination-------------------------------------------------------------	
			
			public @ResponseBody List<EDU_ACADEMIC_MSTR> getEducationAcademicList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from EDU_ACADEMIC_MSTR where status='1' order by id ");
				@SuppressWarnings("unchecked")
				List<EDU_ACADEMIC_MSTR> clist = (List<EDU_ACADEMIC_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			
//			====Placement-Management
			
			
			
			public String fileupload2(byte[] b, String name, int id, String type) {
				String extension = "";
				String fname = "";
				try {
					byte[] bytes = b; // file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
//					File dir = new File(rootPath + File.separator + "CRPF_Document//" + id);
					 File dir = new File("/srv/student_Document/"+id);
					if (!dir.exists())
						dir.mkdirs();

					String filename = name; // file.getOriginalFilename();

					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}

					// Create the file on server
					// java.util.Date date= new java.util.Date();
					// fname = dir.getAbsolutePath() + File.separator+""+type+"__" + (new
					// Timestamp(date.getTime())).toString().replace(":",
					// "").toString().replace(".", ".").toString().replace("
					// ","").toString().replace("-","").toString()+ "."+extension;
					fname = dir.getAbsolutePath() + File.separator + "" + type + "__" + currentDateWithTimeStampString() + "."
							+ extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				return fname;
			}
			
			public @ResponseBody List<EDU_ALUM_ALUMNI_EVENT> getEvent_List(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				Query q = sessionHQL
						.createQuery("from EDU_ALUM_ALUMNI_EVENT order by id ");

				@SuppressWarnings("unchecked")
				List<EDU_ALUM_ALUMNI_EVENT> clist = (List<EDU_ALUM_ALUMNI_EVENT>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
//			====Placement-Management
			 
			public String fileupload1(byte[] b, String name, int id, String type) {
				String extension = "";
				String fname = "";
				try {
					byte[] bytes = b; // file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
//						File dir = new File(rootPath + File.separator + "CRPF_Document//" + id);
					File dir = new File("/srv/Placement_Doc/" + id);
					if (!dir.exists())
						dir.mkdirs();

					String filename = name; // file.getOriginalFilename();

					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}

					// Create the file on server
					// java.util.Date date= new java.util.Date();
					// fname = dir.getAbsolutePath() + File.separator+""+type+"__" + (new
					// Timestamp(date.getTime())).toString().replace(":",
					// "").toString().replace(".", ".").toString().replace("
					// ","").toString().replace("-","").toString()+ "."+extension;
					fname = dir.getAbsolutePath() + File.separator + "" + type + "__" + currentDateWithTimeStampString() + "."
							+ extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				return fname;
			} 
			
			
/* start Collaboration Method */		
			
//			Collaboration_Type dropdown
		
			public @ResponseBody List<TB_COL_ORG_TYPE> CollaborationIntypeList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from TB_COL_ORG_TYPE where status='1' order by id ");

				@SuppressWarnings("unchecked")
				List<TB_COL_ORG_TYPE> clist = (List<TB_COL_ORG_TYPE>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
	
//			Collaboration_Type dropdown
			
			public @ResponseBody List<TB_COL_ORG_SECTOR> CollaborationInsectorList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from TB_COL_ORG_SECTOR where status='1' order by id ");

				@SuppressWarnings("unchecked")
				List<TB_COL_ORG_SECTOR> clist = (List<TB_COL_ORG_SECTOR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			
//			Collaboration_Category dropdown
			
			public @ResponseBody List<TB_COL_ORG_CATEGORY> CollaborationIncategoryList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from TB_COL_ORG_CATEGORY where status='1' order by id ");

				@SuppressWarnings("unchecked")
				List<TB_COL_ORG_CATEGORY> clist = (List<TB_COL_ORG_CATEGORY>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
/* End Collaboration Method */		
			
			// start degree recognition
			public @ResponseBody List<EDU_LMS_NCH_STUDENT_DETAILS> getNCHstudentList(SessionFactory sessionFactory,String lvl_module) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from EDU_LMS_NCH_STUDENT_DETAILS where id=:id");
				q.setParameter("id",Integer.parseInt( lvl_module));
				@SuppressWarnings("unchecked")
				List<EDU_LMS_NCH_STUDENT_DETAILS> clist = (List<EDU_LMS_NCH_STUDENT_DETAILS>) q.list();
				System.out.println("list"+clist);
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			public List<String> getMedUniversityName(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx1 = sessionHQL.beginTransaction();
				try {
					Query q1 = sessionHQL
							.createQuery("select distinct id,university_name from EDU_LMS_UNIVERSITY_MSTR where status='1' order  by university_name");
					List<String> list = (List<String>) q1.list();
					tx1.commit();
					return list;
				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}

			public List<String> getMedInstituteName(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx1 = sessionHQL.beginTransaction();
				try {
					Query q1 = sessionHQL.createQuery(
							"select distinct id,institute_name from EDU_LMS_INSTITUTE_REGISTRATION where status='1' order  by institute_name");
					List<String> list = (List<String>) q1.list();
					System.err.println(q1+"-----list-----"+list);
					tx1.commit();
					return list;
				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}
				
			// end degree recognition
			
			@RequestMapping(value = "/getUniversity_nametoid", method = RequestMethod.POST)
			public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversity_nametoid(String university_name) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from EDU_LMS_UNIVERSITY_MSTR where university_name=:university_name and status='1'");
				q.setParameter("university_name", university_name);
				@SuppressWarnings("unchecked")
				List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			

public List<String> getUniIdfromUserid(SessionFactory sessionFactory, String userid) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("select cast(university_id as text) from UserLogin where userid=:userid");
			q1.setParameter("userid", Integer.parseInt(userid));
			List<String> list = (List<String>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
public @ResponseBody List<EDU_PG_GRADU_EXAMNAME> getnameofexam_pg_graduformList(SessionFactory sessionFactory,
		String system_id, String degree_id) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
//	Query q = sessionHQL.createQuery(
//			"from EDU_PG_GRADU_EXAMNAME where status='1' and system_id=:system_id and degree_id=:degree_id  order by id ");
	
	Query q = sessionHQL.createQuery(
			"from EDU_PG_GRADU_EXAMNAME where status='1' and system_id=:system_id  order by id ");
	
	q.setParameter("system_id", Integer.parseInt(system_id));
//	q.setParameter("degree_id", Integer.parseInt(degree_id));
	@SuppressWarnings("unchecked")
	List<EDU_PG_GRADU_EXAMNAME> clist = (List<EDU_PG_GRADU_EXAMNAME>) q.list();
	tx.commit();
	sessionHQL.close();

	return clist;
}

	/* <!-- Start  DegreeCateList Dropdown Method--> */
	public @ResponseBody List<EDU_LMS_DEGREE_CATE_MSTR> DegreeCateList(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_DEGREE_CATE_MSTR where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_DEGREE_CATE_MSTR> clist = (List<EDU_LMS_DEGREE_CATE_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	

	
	public @ResponseBody List<EDU_LMS_TYPE_OF_DEGREE_MASTER> getdegreefrom_type_of_degree(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery(
				"select id,type_of_degree from EDU_LMS_DEGREE_MASTER where status='1' order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_TYPE_OF_DEGREE_MASTER> clist = (List<EDU_LMS_TYPE_OF_DEGREE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	/* <!-- End  DegreeCateList Dropdown Method--> */
	
	


public List<String> getuniversitylistbyStafflevel(SessionFactory sessionFactory, String staff_lvl) {
		Session sessionHQL = this.sessionFactory.openSession();
 		Transaction tx1 = sessionHQL.beginTransaction();
 		
		Query q = sessionHQL.createQuery(" select distinct  cast(um.id as string)  , um.university_name from EDU_LMS_UNIVERSITY_MSTR um ,EDU_LMS_ORGANIZATION_MSTR om, Role ro where \r\n"
				+ "om.id=um.organization_id and ro.staff_lvl=om.organization and ro.staff_lvl=:staff_lvl order by cast(um.id as string)");
		q.setParameter("staff_lvl", staff_lvl);
		@SuppressWarnings("unchecked")
		List<String> clist = (List<String>) q.list();
		tx1.commit();
		sessionHQL.close();
		
		System.out.println("clist----->   "+clist);
		
		return clist;
	}
	


@SuppressWarnings("null")
@RequestMapping(value = "/kmlFileDownloadFinalDynemic44")
public void kmlFileDownload44(@ModelAttribute("kmlId2") String id,@ModelAttribute("val444") String val,
	@ModelAttribute("fildname1") String fildname,
 ModelMap model ,HttpServletRequest request,HttpServletResponse response,
	HttpSession session) throws IOException{
	
final int BUFFER_SIZE = 4096;
System.out.println("fildname==============="+fildname);
		
//String base64EncodedDcryptedad2 = "";
//String enckey2 = "commonPwdEncKeys";
//id = id.replace(" ", "+");
//base64EncodedDcryptedad2 = hex_asciiDao.decrypt(String.valueOf(id), enckey2, session);

//String id2 = (base64EncodedDcryptedad2);	

String filePath = cmdao.getFilePath_DynemicQueryForDoc(id,val,fildname);


model.put("filePath",filePath);


ServletContext context = request.getSession().getServletContext();
try{
if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
{
    @SuppressWarnings("deprecation")
	String fullPath =  request.getRealPath("/")+"admin/assets/db_img/noimage.jpeg";
          File downloadFile = new File(fullPath);
          FileInputStream inputStream = new FileInputStream(downloadFile);
          String mimeType = context.getMimeType(fullPath);
          if (mimeType == null) {
              mimeType = "application/octet-stream";
          }
          response.setContentType(mimeType);
          response.setContentLength((int) downloadFile.length());
          String headerKey = "Content-Disposition";
          String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
          response.setHeader(headerKey, headerValue);
          ServletOutputStream outStream = response.getOutputStream();
          byte[] buffer = new byte[BUFFER_SIZE];
          int bytesRead = -1;
          while ((bytesRead = inputStream.read(buffer)) != -1) {
              outStream.write(buffer, 0, bytesRead);
          }
          inputStream.close();
          outStream.close();
}
else
{
	
	    String fullPath =  filePath; 
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
      }
}catch(Exception ex)
{
	ServletOutputStream outStream = response.getOutputStream();

	int bytesRead = -1;

	try {
		@SuppressWarnings("deprecation")
		String fullPath = request.getRealPath("/") + "admin/assets/db_img/noimage.jpeg";
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		byte[] buffer = new byte[BUFFER_SIZE];

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
	} catch (Exception e) {

		byte[] buffer = new byte[BUFFER_SIZE];
		outStream.write(buffer, 0, bytesRead);
	}

	outStream.close();

	}		        	
}

public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemForAll(SessionFactory sessionFactory) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	Query q = sessionHQL
			.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' order by id ");
	@SuppressWarnings("unchecked")
	List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}


public @ResponseBody List<EDU_LMS_GENDER_MSTR> getGender() {
		Session sessionHQL = sessionFactory.openSession();

		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_GENDER_MSTR where status='1' order by id");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_GENDER_MSTR> clist = (List<EDU_LMS_GENDER_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	@RequestMapping(value = "/getGender_nametoid", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_GENDER_MSTR> getGender_nametoid(String gender_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_GENDER_MSTR where gender_name=:gender_name and status='1'");
		q.setParameter("gender_name", gender_name);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_GENDER_MSTR> clist = (List<EDU_LMS_GENDER_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	

	//--------------------------------NCISM inst list-----------------------	

		public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteListForNcism(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION where system_id = '44' order by id ");
			@SuppressWarnings("unchecked")
			List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		
//		==========================================NOTIFICATION===============================
		public int Notification(String msg,String to_name , SessionFactory sessionFactory,HttpSession session) {
			Session session1 = sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			String username = session.getAttribute("username").toString();
			String userId = session.getAttribute("userId").toString();
			Date date = new Date();
			TB_NOTIFICATION add = new TB_NOTIFICATION();
			add.setMessage(msg);
			add.setCreated_by(username);
			add.setCreated_date(date);
			add.setFrom_name_send(userId);
			add.setTo_name_sent(to_name);
			add.setStatus("1");
		
			int id = (Integer) session1.save(add);
			session1.save(add);
			session1.flush();
			session1.clear();
			//ra.addAttribute("msg", "Data Saved Successfully.");
			
			
		
			session1.getTransaction().commit();
			session1.close();
			return id;
		}
		//UG-PG FEES Start--------------------
		public @ResponseBody List<EDU_LMS_TERM_MASTER> gettermListFee(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery(
					"select distinct term, prof_name from EDU_LMS_TERM_MASTER where status='1' order by term ");

			@SuppressWarnings("unchecked")
			List<EDU_LMS_TERM_MASTER> clist = (List<EDU_LMS_TERM_MASTER>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}

		//UG-PG FEES End--------------------
		
		//--------------------Masters Start--------------------
	
		@RequestMapping(value = "/gettype_degree_id", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_TYPE_OF_DEGREE_MASTER> gettype_degree_id(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("select id,type_of_degree from EDU_LMS_TYPE_OF_DEGREE_MASTER where status='1'");

			@SuppressWarnings("unchecked")
			List<EDU_LMS_TYPE_OF_DEGREE_MASTER> clist = (List<EDU_LMS_TYPE_OF_DEGREE_MASTER>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		@RequestMapping(value = "/getfaculty_course_id", method = RequestMethod.POST)
		public @ResponseBody List<EDU_FACULTY_COURSE_MSTR> getfaculty_course_id(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("select id,course_name from EDU_FACULTY_COURSE_MSTR where status=1");

			@SuppressWarnings("unchecked")
			List<EDU_FACULTY_COURSE_MSTR> clist = (List<EDU_FACULTY_COURSE_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}

		//--------------------Masters End--------------------
		
//		===============30/12/2022 state_logo
		
		public String fileupload76(byte[] b, String name, int id, String type) {
			String extension = "";
			String fname = "";
			try {
				byte[] bytes = b; // file.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
//					File dir = new File(rootPath + File.separator + "CRPF_Document//" + id);
				File dir = new File("/srv/Document/" );
				if (!dir.exists())
					dir.mkdirs();

				String filename = name; // file.getOriginalFilename();

				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}

				// Create the file on server
				// java.util.Date date= new java.util.Date();
				// fname = dir.getAbsolutePath() + File.separator+""+type+"__" + (new
				// Timestamp(date.getTime())).toString().replace(":",
				// "").toString().replace(".", ".").toString().replace("
				// ","").toString().replace("-","").toString()+ "."+extension;
				fname = dir.getAbsolutePath() + File.separator + "" + type + "__" + currentDateWithTimeStampString() + "."
						+ extension;
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return fname;
		}
		
		
		public @ResponseBody List<TB_FEEDBACK_CATEGORY_MSTR> getFeedBackCat(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from TB_FEEDBACK_CATEGORY_MSTR where status='1' order by id ");

			@SuppressWarnings("unchecked")
			List<TB_FEEDBACK_CATEGORY_MSTR> clist = (List<TB_FEEDBACK_CATEGORY_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		


@RequestMapping(value = "admin/getuniversity_listbyState", method = RequestMethod.POST)
		public @ResponseBody List<UserLogin> getuniversity_listbyState(SessionFactory sessionFactory,String state_id) {
			
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("select distinct u.id,u.university_name from UserLogin l,EDU_LMS_UNIVERSITY_MSTR u\n"
							+ "where u.id=l.university_id and u.status='1' and l.state_id=:state_id");
			
			q.setParameter("state_id", Integer.parseInt(state_id));
			@SuppressWarnings("unchecked")
			List<UserLogin> clist = (List<UserLogin>) q.list();
			
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		@RequestMapping(value = "admin/getinstitute_listbyuniversity_exam", method = RequestMethod.POST)
		public @ResponseBody List<UserLogin> getinstitute_listbyuniversity_exam(SessionFactory sessionFactory,String university_id) {
			
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("select distinct i.id,i.institute_name from UserLogin l,EDU_LMS_INSTITUTE_REGISTRATION i \n"
							+ "where i.id=l.institute_id and i.status='1' and l.university_id=:university_id");
			
			q.setParameter("university_id", Integer.parseInt(university_id));
			@SuppressWarnings("unchecked")
			List<UserLogin> clist = (List<UserLogin>) q.list();
			
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		@RequestMapping(value = "admin/getDegreeListFromInstitute_exam", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_INST> getDegreeListFromInstitute_exam(SessionFactory sessionFactory,String institute_id) {
			
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("select distinct d.id,d.degree_name from EDU_LMS_SYS_DEG_MAP_INST sd,EDU_LMS_DEGREE_MASTER d \n"
							+ "where d.id = sd.degree_id and d.status='1' and sd.institute_id=:institute_id ");
			
			q.setParameter("institute_id", Integer.parseInt(institute_id));
			@SuppressWarnings("unchecked")
			List<EDU_LMS_SYS_DEG_MAP_INST> clist = (List<EDU_LMS_SYS_DEG_MAP_INST>) q.list();
			
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getsystemlist() {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			Query q = sessionHQL
					.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' order by system_name ");
			@SuppressWarnings("unchecked")
			List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		@RequestMapping(value = "/getsector_id", method = RequestMethod.POST)
		public @ResponseBody List<TB_COL_ORG_SECTOR> getsector_id(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("select id,sector_type,status from TB_COL_ORG_SECTOR where status='1'");

			@SuppressWarnings("unchecked")
			List<TB_COL_ORG_SECTOR> clist = (List<TB_COL_ORG_SECTOR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		public @ResponseBody List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> getCounselingAuthoList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from EDU_LMS_COUNSELING_AUTHORITY_MSTR where status=1 order by id ");

			@SuppressWarnings("unchecked")
			List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> list = (List<EDU_LMS_COUNSELING_AUTHORITY_MSTR>) q.list();
			System.err.println("common==============="+list);
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		public @ResponseBody List<EDU_LMS_QUOTA_MSTR> getQuotaList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from EDU_LMS_QUOTA_MSTR where status=1 order by id ");

			@SuppressWarnings("unchecked")
			List<EDU_LMS_QUOTA_MSTR> list = (List<EDU_LMS_QUOTA_MSTR>) q.list();
			System.err.println("common==============="+list);
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		public List<TB_STATE> getMedStateName2(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx1 = sessionHQL.beginTransaction();
			try {
				Query q1 = sessionHQL.createQuery(
						" from TB_STATE where status='1' order  by state_name");
				List<TB_STATE> list = (List<TB_STATE>) q1.list();
				tx1.commit();
				return list;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		
		public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInsCodeListbyInsName(SessionFactory sessionFactory,
				String id) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery(
					"from EDU_LMS_INSTITUTE_REGISTRATION ir where status='1'  and ir.id=:id order by ir.id");

			q.setParameter("id", Integer.parseInt(id));
			@SuppressWarnings("unchecked")

			List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		@RequestMapping(value = "/getdegreeList", method = RequestMethod.POST)
		public @ResponseBody List<String> getdegreeList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("select id, degree_name from EDU_LMS_DEGREE_MASTER where status='1' and type_of_degree='15' order by id");
			System.err.println("check the degree list"+q);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		public List<String> getStateIdfromUserid(SessionFactory sessionFactory, String userid) {

			Session sessionHQL = sessionFactory.openSession();
			Transaction tx1 = sessionHQL.beginTransaction();
			try {
				Query q1 = sessionHQL.createQuery("select cast(state_id as text) from UserLogin where userid=:userid");
				q1.setParameter("userid", Integer.parseInt(userid));
				List<String> list = (List<String>) q1.list();
				tx1.commit();
				return list;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		
		public List<String> getUniIdfromInsid(String institute_id) {

			Session sessionHQL = sessionFactory.openSession();
			Transaction tx1 = sessionHQL.beginTransaction();
			try {
				Query q1 = sessionHQL.createQuery("select cast(university_id as text) from EDU_LMS_INSTITUTE_REGISTRATION where id=:institute_id");
				q1.setParameter("institute_id", Integer.parseInt(institute_id));
				List<String> list = (List<String>) q1.list();
				tx1.commit();
				return list;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		public @ResponseBody List<CC_TB_GUILBERTS_LEVEL_MSTR> getguilberts_levelList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from CC_TB_GUILBERTS_LEVEL_MSTR where status='1' order by id ");

			@SuppressWarnings("unchecked")
			List<CC_TB_GUILBERTS_LEVEL_MSTR> clist = (List<CC_TB_GUILBERTS_LEVEL_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		public @ResponseBody List<CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR> getnch_formative_and_summativeList(SessionFactory sessionFactory) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR where status='1' order by id ");

			@SuppressWarnings("unchecked")
			List<CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR> clist = (List<CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR>) q.list();
			tx.commit();
			sessionHQL.close();
			
			return clist;
		}
		public List<String> getRolebyAccess(SessionFactory sessionFactory2) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx1 = sessionHQL.beginTransaction();
			try {
				Query q1 = sessionHQL.createQuery(
						"select distinct  r.sub_access_lvl from Role r where r.staff_lvl='NCH'");
				List<String> list = (List<String>) q1.list();
				tx1.commit();
				return list;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
	
		public @ResponseBody List<Role> getRoleList(SessionFactory sessionFactory,String roleid1) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from Role where staff_lvl=:roleid1");
			q.setParameter("roleid1", roleid1);
			@SuppressWarnings("unchecked")
			List<Role> clist = (List<Role>) q.list();
			tx.commit();
			sessionHQL.close();
			return clist;
		}

		

		@RequestMapping(value = "/getNCH_TopicListbyCourse", method = RequestMethod.POST)
		public @ResponseBody List<CC_TB_TOPICS_MSTR> getNCH_TopicListbyCourse(
				String course_id) {
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery(
					"select  t.id,t.topic from CC_TB_TOPICS_MSTR t "
					+ "where  t.course_id=:course_id and t.status=1  ");
			q.setParameter("course_id", Integer.parseInt(course_id));
			@SuppressWarnings("unchecked")

			List<CC_TB_TOPICS_MSTR> clist = (List<CC_TB_TOPICS_MSTR>) q.list();
			tx.commit();
			
			sessionHQL.close();
			
			return clist;
		}
		




public @ResponseBody List<CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR> Nchget_Mk_Dk_Nk(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR where status='1' and system_id= 45  order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR> clist = (List<CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	public @ResponseBody List<CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR> Nchgett_l_method(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR where status='1' and system_id = 45 order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR> clist = (List<CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	public @ResponseBody List<CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR> getMillers_level(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR where status='1' and system_id = 45 order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR> clist = (List<CC_TB_E3_REPESENTATION_OF_UNDERSTANDING_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	public @ResponseBody List<CC_TB_C3_DOMAIN_MSTR> NchgetBloom_Domain(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CC_TB_C3_DOMAIN_MSTR where status='1' and system_id= 45  order by id ");

		@SuppressWarnings("unchecked")
		List<CC_TB_C3_DOMAIN_MSTR> clist = (List<CC_TB_C3_DOMAIN_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	//For Link Role with System
	
	@RequestMapping(value = "/getSystembyStafflvl", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystembyStafflvl(@RequestParam(value = "staff_lvl", required = false) String staff_lvl ) {
		
		Session sessionHQL = this.sessionFactory.openSession();
 		Transaction tx1 = sessionHQL.beginTransaction();
 		 		
		Query q = sessionHQL.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' and created_role=:staff_lvl order by id");
		q.setParameter("staff_lvl", staff_lvl);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
		tx1.commit();
		sessionHQL.close();
		return clist;
	}

public @ResponseBody List<EDU_CC_TB_EVALUATION_METHOD_PA_MSTR> getEval_Method_PA_bySystem(SessionFactory sessionFactory,String system_id) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select id,evaluation_method_pa from EDU_CC_TB_EVALUATION_METHOD_PA_MSTR where system_id=:system_id and status='1' order by id ");

				q.setParameter("system_id", Integer.parseInt(system_id));
				@SuppressWarnings("unchecked")
				List<EDU_CC_TB_EVALUATION_METHOD_PA_MSTR> clist = (List<EDU_CC_TB_EVALUATION_METHOD_PA_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
}


//==========================================Broadcasting===============================
public int broadcasting(String msg,String to_name , String rolehidden, SessionFactory sessionFactory,HttpSession session) {
	Session session1 = sessionFactory.openSession();
	Transaction tx = session1.beginTransaction();
	String username = session.getAttribute("username").toString();
	String userId = session.getAttribute("userId").toString();
	Date date = new Date();
	TB_NOTIFICATION add = new TB_NOTIFICATION();
	add.setMessage(msg);
	add.setCreated_by(username);
	add.setCreated_date(date);
	add.setFrom_name_send(userId);
	add.setTo_name_sent(to_name);
	add.setStatus("1");
	add.setRole(rolehidden);

	int id = (Integer) session1.save(add);
	session1.save(add);
	session1.flush();
	session1.clear();
	//ra.addAttribute("msg", "Data Saved Successfully.");
	
	

	session1.getTransaction().commit();
	session1.close();
	return id;
}

@RequestMapping(value = "admin/getCourseToPractical", method = RequestMethod.POST)
public @ResponseBody List<EDU_CC_TB_NCH_PRACTICAL_MSTR> getCourseToPractical(String course_id) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	Query q = sessionHQL
			.createQuery("select id,practical from EDU_CC_TB_NCH_PRACTICAL_MSTR where course_id=:course_id and status='1'");
	
	q.setParameter("course_id", Integer.parseInt(course_id));
	@SuppressWarnings("unchecked")
	List<EDU_CC_TB_NCH_PRACTICAL_MSTR> clist = (List<EDU_CC_TB_NCH_PRACTICAL_MSTR>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}	

@RequestMapping(value = "/getTermBySysProf", method = RequestMethod.POST)
public @ResponseBody List<EDU_LINK_SYS_DEG_PROF_TERM> getTermBySysProf(HttpSession session,String prof) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
//	String role = session.getAttribute("role").toString();
	String userid = session.getAttribute("userId_for_jnlp").toString();
	String institute_id = cmdao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
	String system = cmdao.getinstitute_system(Integer.parseInt(institute_id)).get(0).get(0);
	
//	if(role.toUpperCase().contains("NCISM")) {
//		system = "44";
//	}
//	if(role.toUpperCase().contains("NCH")) {
//		system = "45";
//	}
	
	Query q = sessionHQL.createQuery(
			"select tm.id,tm.term from EDU_LINK_SYS_DEG_PROF_TERM lt,CC_TB_I3_TERM_MSTR tm where "
			+ " lt.term=tm.id and system=:system and prof=:prof ");
	q.setParameter("system", Integer.parseInt(system));
	q.setParameter("prof", Integer.parseInt(prof));
	@SuppressWarnings("unchecked")

	List<EDU_LINK_SYS_DEG_PROF_TERM> clist = (List<EDU_LINK_SYS_DEG_PROF_TERM>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}
	


public @ResponseBody List<EDU_LMS_FACULTY_TYPE_OF_EXP_MSTR> getType_of_exp(SessionFactory sessionFactory) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	Query q = sessionHQL.createQuery("from EDU_LMS_FACULTY_TYPE_OF_EXP_MSTR where status='1' order by id ");

	@SuppressWarnings("unchecked")
	List<EDU_LMS_FACULTY_TYPE_OF_EXP_MSTR> clist = (List<EDU_LMS_FACULTY_TYPE_OF_EXP_MSTR>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}
	
@RequestMapping(value = "admin/getTerm_listByProf_for_Curri", method = RequestMethod.POST)
public @ResponseBody List<EDU_LINK_SYS_DEG_PROF_TERM> getTerm_listByProf_for_Curri(
		String system,String degree,String professional) {
	
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	String qry = " select distinct i.id,i.term \n"
			+ " from EDU_LINK_SYS_DEG_PROF_TERM t,CC_TB_I3_TERM_MSTR i \n"
			+ " where i.id = t.term and i.status=1 and t.status=1 ";
	
	String system_qry = " and t.system=:system ";
	String degree_qry = " and t.degree=:degree ";
	String prof_qry = " and t.prof=:professional ";
	
	if(system != null && system!="") {
		qry += system_qry;
	}
	if(degree != null && degree!="") {
		qry += degree_qry;
	}
	if(professional != null  && professional!="") {
		qry += prof_qry;
	}
	
	Query q = sessionHQL.createQuery(qry);
	
	if(system != null && system!="") {
		q.setParameter("system", Integer.parseInt(system));
	}
	if(degree != null && degree!="") {
		q.setParameter("degree", Integer.parseInt(degree));
	}
	if(professional != null  && professional!="") {
		q.setParameter("professional", Integer.parseInt(professional));
	}
	
	@SuppressWarnings("unchecked")
	List<EDU_LINK_SYS_DEG_PROF_TERM> clist = (List<EDU_LINK_SYS_DEG_PROF_TERM>) q.list();
	
	tx.commit();
	sessionHQL.close();
	return clist;
}


//20-02-2023 for practitionner 
public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemForHomeopathy(SessionFactory sessionFactory) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	Query q = sessionHQL
			.createQuery("from EDU_LMS_SYSTEM_MSTR where system_name='HOMOEOPATHY' order by id ");
	@SuppressWarnings("unchecked")
	List<EDU_LMS_SYSTEM_MSTR> clist = (List<EDU_LMS_SYSTEM_MSTR>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}

//@RequestMapping(value = "/get_NCH_TopicListbyCourse", method = RequestMethod.POST)
//public @ResponseBody List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT> get_NCH_TopicListbyCourse(
//		String course_id) {
//	Session sessionHQL = sessionFactory.openSession();
//	Transaction tx = sessionHQL.beginTransaction();
//	Query q = sessionHQL.createQuery(
//			"select distinct t.id,t.topic from EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT p ,EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD c,CC_TB_TOPICS_MSTR t "
//			+ "where c.p_id = p.id and t.id = p.topic_id and p.course_id=:course_id and t.status=1 and p.status=0 and c.status=0 ");
//	q.setParameter("course_id", Integer.parseInt(course_id));
//	@SuppressWarnings("unchecked")
//
//	List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT> clist = (List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT>) q.list();
//	tx.commit();
//	
//	sessionHQL.close();
//	
//	return clist;
//}

@RequestMapping(value = "/getPracticalListby_Course_NCH", method = RequestMethod.POST)
public @ResponseBody List<EDU_CC_TB_NCH_PRACTICAL_MSTR> getPracticalListby_Course_NCH(String course_id) {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	Query q = sessionHQL.createQuery(
			" select practical from EDU_CC_TB_NCH_PRACTICAL_MSTR where course_id=:course_id and status=1 ");
	q.setParameter("course_id", Integer.parseInt(course_id));
	@SuppressWarnings("unchecked")

	List<EDU_CC_TB_NCH_PRACTICAL_MSTR> clist = (List<EDU_CC_TB_NCH_PRACTICAL_MSTR>) q.list();
	tx.commit();
	sessionHQL.close();
	return clist;
}


@RequestMapping(value = "/getlearning_outcome_listbypractical", method = RequestMethod.POST)
			public @ResponseBody List<EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT> getlearning_outcome_listbypractical(String practical_id) {
				
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						
						"select c.id,c.learni_outcome from EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT p ,EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_CHILD c\n"
						+ "  where c.p_id = p.id  and  p.status=1 and c.status=1 and p.practical_id=:practical_id order by c.id");
				
				
				q.setParameter("practical_id", Integer.parseInt(practical_id));
				@SuppressWarnings("unchecked")

				List<EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT> clist = (List<EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}


			@RequestMapping(value = "/get_NCH_TopicListbyCourse", method = RequestMethod.POST)
			public @ResponseBody List<CC_TB_TOPICS_MSTR> get_NCH_TopicListbyCourse(SessionFactory sessionFactory2,
					String course_id) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select distinct id,topic from CC_TB_TOPICS_MSTR where course_id=:course_id and status='1'");
				q.setParameter("course_id", Integer.parseInt(course_id));
				@SuppressWarnings("unchecked")

				List<CC_TB_TOPICS_MSTR> clist = (List<CC_TB_TOPICS_MSTR>) q.list();
				tx.commit();

				sessionHQL.close();

				return clist;
			}
			
//			--Practitionner Dynamic Doc LIst URMIK
			public List<String> getattachmentNameForOnlyPractitionner(SessionFactory sessionFactory,String screen_url) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx1 = sessionHQL.beginTransaction();
				try {
					Query q1 = sessionHQL
							.createQuery("select d.id,d.doc_name from EDU_DOC_ATTACHMENTS_MSTR d,TB_LDAP_SCREEN_MASTER s  where s.id = d.screen_id and screen_url=:screen_url order  by d.id");
					q1.setParameter("screen_url",(screen_url));
					List<String> list = (List<String>) q1.list();
					
					tx1.commit();
					return list;
				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}
			
			//========Ehnancement OF Research Start=============================//	
			@RequestMapping(value = "/getCategorylist", method = RequestMethod.POST)
			public @ResponseBody List<TB_ENHANCE_RESEARCH_CATEGORY_MSTR> getCategorylist(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select distinct id,category_name from TB_ENHANCE_RESEARCH_CATEGORY_MSTR where status='1'");
//				q.setParameter("course_id", Integer.parseInt(course_id));
				@SuppressWarnings("unchecked")

				List<TB_ENHANCE_RESEARCH_CATEGORY_MSTR> clist = (List<TB_ENHANCE_RESEARCH_CATEGORY_MSTR>) q.list();
				tx.commit();

				sessionHQL.close();

				return clist;
			}
			
			@RequestMapping(value = "/getSearchFieldList", method = RequestMethod.POST)
			public @ResponseBody List<TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR> getSearchFieldList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery(
						"select distinct id,search_field_name from TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR where status='1'");
//				q.setParameter("course_id", Integer.parseInt(course_id));
				@SuppressWarnings("unchecked")

				List<TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR> clist = (List<TB_ENHANCE_RESEARCH_SEARCH_FIELD_MSTR>) q.list();
				tx.commit();

				sessionHQL.close();

				return clist;
			}
			
			//========Ehnancement OF Research End=============================//	
			public @ResponseBody List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> getCounsellingAuthoList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("select id,counseling_authority from EDU_LMS_COUNSELING_AUTHORITY_MSTR where status=1 order by id ");

				@SuppressWarnings("unchecked")
				List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> list = (List<EDU_LMS_COUNSELING_AUTHORITY_MSTR>) q.list();
				System.err.println("common==============="+list);
				tx.commit();
				sessionHQL.close();
				return list;
			}
			public @ResponseBody List<EDU_LMS_QUOTA_MSTR> getQuotaListDropDown(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("select id,quota from EDU_LMS_QUOTA_MSTR where status=1 order by id ");

				@SuppressWarnings("unchecked")
				List<EDU_LMS_QUOTA_MSTR> list = (List<EDU_LMS_QUOTA_MSTR>) q.list();
				System.err.println("common==============="+list);
				tx.commit();
				sessionHQL.close();
				return list;
			}
			public @ResponseBody List<EDU_LMS_INTAKE_TYPE_MSTR> getintake_typelist() {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from EDU_LMS_INTAKE_TYPE_MSTR  where status=1  order by id");
				List<EDU_LMS_INTAKE_TYPE_MSTR> clist = (List<EDU_LMS_INTAKE_TYPE_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}	
			
//			-----help_desk Urmik
			
			public List<HD_INQUIRY_CATEGORY_MSTR> getInq_CatList() {
				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				Query q = session.createQuery("from HD_INQUIRY_CATEGORY_MSTR where status = 1 order by id");
				@SuppressWarnings("unchecked")
				List<HD_INQUIRY_CATEGORY_MSTR> list = (List<HD_INQUIRY_CATEGORY_MSTR>) q.list();
				tx.commit();
				session.close();
				return list;
			}
			
//			====================================HELP DESK==================================
					public @ResponseBody List<Role> getRoleFromStaff_lvl(SessionFactory sessionFactory,String staff_lvl) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from Role where staff_lvl=:staff_lvl");
						q.setParameter("staff_lvl", staff_lvl);
						
						@SuppressWarnings("unchecked")
						List<Role> clist = (List<Role>) q.list();
						tx.commit();
						sessionHQL.close();
						return clist;
					}
					
					public @ResponseBody List<CLG_REG_NATURE_OF_APPOINMENT_MSTR> getNature_of_Appoinment(SessionFactory sessionFactory) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("select id,nature_of_appoinment from CLG_REG_NATURE_OF_APPOINMENT_MSTR where status=1 order by id ");
						@SuppressWarnings("unchecked")
						List<CLG_REG_NATURE_OF_APPOINMENT_MSTR> clist = (List<CLG_REG_NATURE_OF_APPOINMENT_MSTR>) q.list();
						tx.commit();
						sessionHQL.close();
						return clist;
					}
					
					// HELP DESK BY KARAN
					public List<HD_INQUIRY_CATEGORY_MSTR> getinnerInq_CatList() {
						Session session = this.sessionFactory.openSession();
						Transaction tx = session.beginTransaction();
						Query q = session.createQuery("from HD_INQUIRY_CATEGORY_MSTR where status = 1 order by id");
						@SuppressWarnings("unchecked")
						List<HD_INQUIRY_CATEGORY_MSTR> list = (List<HD_INQUIRY_CATEGORY_MSTR>) q.list();
						tx.commit();
						session.close();
						return list;
					}
					
					public List<String> getMedUniversitynchName(SessionFactory sessionFactory) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx1 = sessionHQL.beginTransaction();
						try {
							Query q1 = sessionHQL
									.createQuery("select distinct id,university_name from EDU_LMS_UNIVERSITY_MSTR where status='1' and system_id = 45 order  by university_name");
							List<String> list = (List<String>) q1.list();
							tx1.commit();
							return list;
						} catch (RuntimeException e) {
							return null;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					}
					
			public List<String> getMedInstitutenchName(SessionFactory sessionFactory) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx1 = sessionHQL.beginTransaction();
						try {
							Query q1 = sessionHQL.createQuery(
									"select distinct id,institute_name from EDU_LMS_INSTITUTE_REGISTRATION where status='1' and system_id = 45 order  by institute_name");
							List<String> list = (List<String>) q1.list();
							System.err.println(q1+"-----list-----"+list);
							tx1.commit();
							return list;
						} catch (RuntimeException e) {
							return null;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					}
			
			public @ResponseBody List<EDU_DIVISION_MSTR> getresultDivisionList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("from EDU_DIVISION_MSTR where status='1'  order by division_name ");
				@SuppressWarnings("unchecked")
				List<EDU_DIVISION_MSTR> dlist = (List<EDU_DIVISION_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return dlist;
			}
			
			public @ResponseBody List<EDU_LMS_DOCUMENT_TYPE_MSTR> getDocumenttypeList(SessionFactory sessionFactory) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("from EDU_LMS_DOCUMENT_TYPE_MSTR where status='1'  order by id ");
				@SuppressWarnings("unchecked")
				List<EDU_LMS_DOCUMENT_TYPE_MSTR> dlist = (List<EDU_LMS_DOCUMENT_TYPE_MSTR>) q.list();
				tx.commit();
				sessionHQL.close();
				return dlist;
			}
}
