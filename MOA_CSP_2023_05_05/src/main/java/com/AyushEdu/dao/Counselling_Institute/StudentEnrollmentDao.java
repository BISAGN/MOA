package com.AyushEdu.dao.Counselling_Institute;

import java.util.List;

import com.AyushEdu.Models.Counselling_Institute.CoCommissiontype;
import com.AyushEdu.Models.Counselling_Institute.CoStudentenrollment;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;

public interface StudentEnrollmentDao {

	
	public List<CoCommissiontype> GetCommissionType();
	
	public List<TbLmsCategoryMstr> GetCategoryType();
	
	public void SaveStudentEnrommentData(CoStudentenrollment coStudentenrollment,String actiontype);

	public List<CoStudentenrollment> LoadStudentEnrolledData(String yearval,int createby,String data);

	public String GetCommissionTypeFromID(Integer commtype);

	public String getStateName(Integer commtype);

	public CoStudentenrollment GetStudentEnrolledDataByID(long seid);

	public List CheckNEETRollNumberexist(String lowerCase);

	public List CheckApplciationNumberexist(String lowerCase);

	public List<CoStudentenrollment> LoadStudentEnrolledDataForCount(String yearval, int userid);

	public Object GetCategoryFromSEID(Long seid);
	
	public CoStudentenrollment GetStudentEnrolledDataByLoginId(int userid) ;

	public List<CoStudentenrollment> GetStudentDataAccordingToNEETRank(String meritround, String year,int userid,String rolename);

	public int getCategoryIDFromCategoryName(String string);

	public List CheckAadaarNumberexist(String lowerCase);

	public boolean ValdiateNEEtDetails(String aadhaatnumber, String appnumber, String neetrollno, String neetmarks,
			String neetrank,String yearval,String dob,int category);

	public boolean CheckUplaodedInformation(String aadhaatnumber, String yearval);
	
	public List CheckNEETRollNumberexistForCouncilORState(String lowerCase,int craeeby);

	public List CheckApplciationNumberexistForCouncilORState(String lowerCase,int craeeby);
	
	public List CheckAadaarNumberexistForCouncilORState(String lowerCase,int craeeby);

	public CoStudentenrollment CheckDetailsExist(String appnumber, String aadhaatnumber, String yearval);
	
}
