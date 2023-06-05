package com.AyushEdu.dao.Curriculum;
import java.util.ArrayList;

public interface View_Que_Paper_Blue_PrintDao {
	
	public ArrayList<ArrayList<String>> getView_QuePaperBluePrint_viewdata(String course_id);
	
	public ArrayList<ArrayList<String>> getpaperformatdata(String course_id,String d3_desirable_know,String qt,String noofpaper);
	
	public ArrayList<ArrayList<String>> getpaperformat_NCH_data(String course_id,String mk_dk,String qt,String noofpaper);
}

