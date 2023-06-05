package com.AyushEdu.dao.Counselling_Institute;

import java.util.Date;
import java.util.List;

import com.AyushEdu.Models.Counselling_Institute.CoRoundgeneration;

public interface RoundGenerationDao {

	void SaveRoundGenerationData(CoRoundgeneration coRoundgeneration, String actiontype);

	List<CoRoundgeneration> LoadRoundGenerationData(String username, String data,String yearval);

	String GetCommissionnameFromId(int commtype);

	List<CoRoundgeneration> LoadRoundGenerationDataForCount(String username, String data,String yearval);

	List<CoRoundgeneration> getRoundGenerationDataFromCompType(String yearval, int userid, int commtype);

	List CheckRoundForCommissionExist(int comtype,String year);

	int getCountForCommission(int userid,String year);

	int getCommissionType(int userid, String year);

	String getCommissionList(int userid, String year);

	CoRoundgeneration GetlastDateAfterAllRound(String year, int createby, int commtype);
	
	public List<CoRoundgeneration> getRoundGenerationData(String yearval);

	Date GetLastDateOFCommission(String yearval);
	
	List<CoRoundgeneration> getRoundGenerationDataFromCompTypeChoiceFilling(String yearval);
	
	List getRoundGenerationDataForSendListToInstitute(String yearval, int userid, int commtype);
	
	public List getRoundGenerationDataForSendListToCounselling(String yearval,int commtype);

	CoRoundgeneration GetlastDateAfterAllRoundForCommission(String year, int i);
	
	List<CoRoundgeneration> LoadRoundGenerationDataFORPDF(String username,String yearval);

	List<CoRoundgeneration>  CheckRegistrationAllow(String datefrm,String yearval);
	
	List<CoRoundgeneration> getRoundGenerationDataFromCompTypeResult(String yearval, int commtype);

	 List CheckRoundForCommissionExistRound(int comtype, String year,int round) ;
}
