package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_SUBJECT_MSTR;

public interface SubjectDAO {

	public List<Map<String, Object>> search_Subject_name(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system,  String course , String subject , String status);
	public long DataTablesubjectDataTotalCount(HttpSession sessionUserId, String Search, String system,  String course ,  String subject);
	public EDU_LMS_SUBJECT_MSTR getSubjectByid(int id);
}
