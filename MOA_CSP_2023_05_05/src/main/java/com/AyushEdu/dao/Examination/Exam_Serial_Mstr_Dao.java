package com.AyushEdu.dao.Examination;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Examination.EXAM_TB_EXAM_SERIAL_MSTR;

public interface Exam_Serial_Mstr_Dao {
	public List<Map<String, Object>> DataTableExamSerialist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String exam_serial, String status);

	public long DataTableExamSerialTotalCount(String search, String exam_serial,String status);
	
	public EXAM_TB_EXAM_SERIAL_MSTR getExamSerialByid(int id);

	public String updateExamSerial(EXAM_TB_EXAM_SERIAL_MSTR td);

}
