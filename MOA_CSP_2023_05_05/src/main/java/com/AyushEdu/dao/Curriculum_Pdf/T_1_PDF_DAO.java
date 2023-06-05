package com.AyushEdu.dao.Curriculum_Pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;

public interface T_1_PDF_DAO {
	

	public ArrayList<ArrayList<String>> non_lec_activities(String course_id);
	
	public ArrayList<ArrayList<String>> table1_co_po_link(String course_id);

}
