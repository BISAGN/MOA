package com.AyushEdu.dao.Counselling_Institute;

import java.util.List;

import com.AyushEdu.Models.Counselling_Institute.CoFeescategorytype;
import com.AyushEdu.Models.Counselling_Institute.CoInstituteotherdetail;
import com.AyushEdu.Models.Counselling_Institute.EduLmsCountryMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDegreeCateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsDistrictMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsInstituteReg;
import com.AyushEdu.Models.Counselling_Institute.EduLmsStateMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsSystemMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityMstr;
import com.AyushEdu.Models.Counselling_Institute.EduLmsUniversityTypeMstr;
import com.AyushEdu.Models.Counselling_Institute.TbLmsCategoryMstr;

public interface InstituteMasterDao_old {

	List<EduLmsCountryMstr> GetCountry();

	List<EduLmsStateMstr> getStateData(int countryid);

	List<EduLmsDistrictMstr> getDistrictData(int countryid, int stateid);

	List<EduLmsSystemMstr> GetSystemType();

	List<EduLmsUniversityMstr> GetUniversity();

	List CheckInstituteCodeExist(String lowerCase, int systemtypeint, int universityint);

	List CheckInstiuteNameExist(String lowerCase, int systemtypeint, int universityint);

	EduLmsInstituteReg GetInstituteDataByID(int id);

	void SaveInstituteData(EduLmsInstituteReg eduLmsInstituteReg, String actiontype);

	String GETCollgegeUniqueIDwithIncrement(int systemtypeint);

	String GetSystemABR(int systemtypeint);

	List<EduLmsInstituteReg> LoadInstituteData(String  username, String data,String rolename,int userid);

	List<EduLmsInstituteReg> LoadInstituteDataForCount(String username,String rolename,int userid);

	List<EduLmsInstituteReg> LoadInstituteDataForState(int stateid);

	List<EduLmsUniversityMstr> getUniversityList();

	List CheckInstiuteMobileExist(String lowerCase, int systemtypeint, int universityint);

	List CheckInstiuteEmailExist(String lowerCase, int systemtypeint, int universityint);

	void SaveInstituteSignUpData(EduLmsInstituteReg eduLmsInstituteReg, String string);

	EduLmsInstituteReg getInstitituteDataFromUserName(String username);


	EduLmsInstituteReg GetInstituteDataByUsername(String username);
	List<CoFeescategorytype> GetFees();

	void saveInstituteOtherDetail(CoInstituteotherdetail coInstituteotherdetails);
	
	void DeleteInstituteOtherDetail(CoInstituteotherdetail coInstituteotherdetails);

	List<CoInstituteotherdetail> GetInstituteOTHERDataByID(int insid);

	boolean CheckInstituteOtherDetailAlreadyExist(int insid,int feesid,int categoryid);
	public EduLmsInstituteReg GetInstituteDataByIDForActivateORDeactivate(int id);

	List<EduLmsInstituteReg> LoadInstituteDataGeneral(String data);

	List<EduLmsInstituteReg> LoadInstituteDataForCountGeneral(String data);

	int GetTotalSeatsOFInstitute(Integer id);

	EduLmsDegreeCateMstr GetIdDegreeType(String string);
	
	public List<TbLmsCategoryMstr> GetCategoryType();
	
	
}
