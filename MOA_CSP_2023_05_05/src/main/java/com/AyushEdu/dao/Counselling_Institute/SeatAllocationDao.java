package com.AyushEdu.dao.Counselling_Institute;

import java.util.List;

import com.AyushEdu.Models.Counselling_Institute.CoSeatallocationmatrix;

public interface SeatAllocationDao {
	public void SaveSeatAllocationData(CoSeatallocationmatrix coSeatallocationmatrix, String actiontpe);

	public List<CoSeatallocationmatrix> LoadSeatAllocationData(String yearval, int userid, String data);
	
	public List<CoSeatallocationmatrix> LoadSeatAllocationDataCount(String yearval, int userid);

	public List<CoSeatallocationmatrix> getSeatAllocationDataByInsID(String yearval, int userid,int cid);

	public List ChecKInstituteExist(int insid, int categoryid);

	public String GetStatePercentageFromInsID(int inid);

	public List ChecKInstituteExistForState(int insid, int categoryid, int stateid,int userid);

	public List GetInstituteDataAccordingToSystem(int systemid, String systemnname,
			int createby,long seid,int round,String commission,int stateid);

	public CoSeatallocationmatrix GetTotalSeatsAccordingToCategory(int insid, int categoryid,int userid,String year);

	public int GetAvailableSeatsFromChocieFillingTable(int insid, String assignedsatus, int catid, int createby,String year,String rolename);

	public int GetAvailableSeatsFromChocieFillingTableSelf(int insid, String assignedsatus, int catid, Long seid,
			String year);

	public List GetInstituteDataAccordingToSystemGeneral(int systemid);

	public List<CoSeatallocationmatrix> getSeatAllocationData(int userid, String year);

	public long CheckAlreadySeatsTransferredStatus(int userid, String year);

	public CoSeatallocationmatrix GetSeatForUpdate(Integer id, Integer id2, String year,int satteid);

	public  List<CoSeatallocationmatrix> GetDistinctInstitute(String year, int userId);

	public List<CoSeatallocationmatrix> GetDistinctCategoryFromSeatAllocation(String year, int userId);

	public String GetSeatFromInstitute(int userid, String year, int insid);

	public int GetAvailableSeatsFromChocieFillingTableRound(int insid, String assignedsatus, int catid, int createby,
			String year, String rolename,String round);
	
}
