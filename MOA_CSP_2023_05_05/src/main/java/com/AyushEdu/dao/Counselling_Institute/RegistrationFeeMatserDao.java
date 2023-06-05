package com.AyushEdu.dao.Counselling_Institute;

import java.util.List;

import com.AyushEdu.Models.Counselling_Institute.CoRegistrationfeestructure;

public interface RegistrationFeeMatserDao {

	List<CoRegistrationfeestructure> getRegistrationFeesData(String year, int userid);

	void SaveRegistrationFeesData(CoRegistrationfeestructure coRegistrationfeestructure, String string);

	List CheckRegistrationDetailsExist(int userid, String year,int catid);

	List<CoRegistrationfeestructure> LoadRegistrationFeeData(String yearval, int userid, String data);

	List<CoRegistrationfeestructure> LoadRegistrationFeeDataCount(String yearval, int userid);

	boolean CheckPaymentForCategory(int parseInt,String yearval,int userid);

}
