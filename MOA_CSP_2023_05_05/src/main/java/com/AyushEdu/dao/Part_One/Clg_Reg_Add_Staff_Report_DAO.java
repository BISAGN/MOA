package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Add_Staff_Report_DAO {

	public ArrayList<ArrayList<String>> DataTableSearch_College_Staff_ListDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String college_staff_list, String first_name, String date_of_appoinment,
			String nature_of_appoinment, String emp_id, String emp_department, String emp_qualification, String aadhar_no,
			String guest_first_name, String guest_date_of_appoinment,
			String guest_nature_of_appoinment, String guest_emp_id, String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no,
			String non_first_name, String non_date_of_appoinment,
			String non_nature_of_appoinment, String non_emp_id, String non_emp_department, String non_emp_qualification, String non_aadhar_no,
			String role, String userid,String institute_id);
	public long DataTableSearch_College_Staff_ListDataTotalCount(String Search, String college_staff_list, String first_name, String date_of_appoinment,
			String nature_of_appoinment, String emp_id, String emp_department, String emp_qualification, String aadhar_no, 
			String guest_first_name, String guest_date_of_appoinment,
			String guest_nature_of_appoinment, String guest_emp_id, String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no,
			String non_first_name, String non_date_of_appoinment,
			String non_nature_of_appoinment, String non_emp_id, String non_emp_department, String non_emp_qualification, String non_aadhar_no,
			String role,
			String userid, String institute_id);

}
