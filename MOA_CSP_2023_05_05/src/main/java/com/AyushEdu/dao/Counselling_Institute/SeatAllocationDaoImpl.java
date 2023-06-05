package com.AyushEdu.dao.Counselling_Institute;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Counselling_Institute.CoSeatallocationmatrix;

@Service
@Repository
public class SeatAllocationDaoImpl implements SeatAllocationDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void SaveSeatAllocationData(CoSeatallocationmatrix coSeatallocationmatrix, String actiontpe) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			if (actiontpe.equalsIgnoreCase("add")) {
				sessionHQL.save(coSeatallocationmatrix);
			} else {
				sessionHQL.update(coSeatallocationmatrix);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

	}

	@Override
	public List<CoSeatallocationmatrix> LoadSeatAllocationData(String yearval, int userid, String data) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoSeatallocationmatrix> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);

			String comtypeva = "";
			String SearchValue = "";
			if (!jsonObject.get("Search").equals("")) { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += " upper(insid.instituteName) like : instituteName"
						+ " OR  upper(cast(totalseat as text)) like  :totalseat "
						+ " OR upper(cast(centralCounPercentage as text)) like :centralCounPercentage"
						+ " OR upper(cast(centralSeat as text)) like :centralSeat"
						+ " OR upper(cast(stateCounPercentage as text)) like :stateCounPercentage"
						+ " OR upper(cast(stateSeat as text)) like :stateSeat"
//						
						+ " OR upper(catid.category) like :category"
						+ " OR upper(cast(catPercentage as text)) like :catPercentage"
						+ " OR upper(cast(catSeat as text)) like :catSeat" + ")";

			}
			String orderColunm = jsonObject.get("orderColunm").toString();
			if (orderColunm.equalsIgnoreCase("2")) {
				orderColunm = "year";
			}
			if (orderColunm.equalsIgnoreCase("3")) {
				orderColunm = "insid.instituteName";
			}
			if (orderColunm.equalsIgnoreCase("4")) {
				orderColunm = "totalseat";
			}
			if (orderColunm.equalsIgnoreCase("5")) {
				orderColunm = "centralCounPercentage";
			}
			if (orderColunm.equalsIgnoreCase("6")) {
				orderColunm = "centralSeat";
			}
			if (orderColunm.equalsIgnoreCase("7")) {
				orderColunm = "stateCounPercentage";
			}
			if (orderColunm.equalsIgnoreCase("8")) {
				orderColunm = "stateSeat";
			}
			if (orderColunm.equalsIgnoreCase("9")) {
				orderColunm = "catid.category";
			}
			if (orderColunm.equalsIgnoreCase("10")) {
				orderColunm = "catPercentage";
			}
			if (orderColunm.equalsIgnoreCase("11")) {
				orderColunm = "catSeat";
			}

			String orderType = jsonObject.get("orderType").toString();
			String pageLength = jsonObject.get("pageLength").toString();

			String startPage = jsonObject.get("startPage").toString();
			System.out.println("pageLength" + pageLength);
			Query q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where year = :year and createby = :createby and status = '1' "
							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

//			Query q1 = sessionHQL.createQuery(
//					"select new com.AyushEdu.seat_allocation_entity.CoStudentenrollment(commtype,neetrollnumber,applicationnumber,neetmarks,neetrank,tenthpercentage,twelvthpercentage,year,firstname,middlename,lastname,dob,mobilenumber,emaildi,aadhaarnumber,seid) From CoStudentenrollment where year = :year and createby = :createby and status = '1' "
//							+ SearchValue + " ORDER BY " + orderColunm + " " + orderType);

			q1.setMaxResults(Integer.parseInt(pageLength));
			q1.setFirstResult(Integer.parseInt(startPage));
			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
			if (!jsonObject.get("Search").equals("")) {
				String search = jsonObject.get("Search").toString().toUpperCase();

				q1.setParameter("instituteName", '%' + search + '%');
				q1.setParameter("totalseat", '%' + search + '%');
				q1.setParameter("centralCounPercentage", '%' + search + '%');
				q1.setParameter("centralSeat", '%' + search + '%');
				q1.setParameter("stateCounPercentage", '%' + search + '%');
				q1.setParameter("stateSeat", '%' + search + '%');
//				q1.setParameter("yearval", '%' + search + '%');
				q1.setParameter("category", '%' + search + '%');
				q1.setParameter("catPercentage", '%' + search + '%');
				q1.setParameter("catSeat", '%' + search + '%');

			}
			// q1.setResultTransformer(Transformers.aliasToBean(StudentEnrollment.class));
			System.out.println("Q1" + q1.getQueryString());
			list = q1.list();
			// List<StudentEnrollment> list1 = q1.list();

//			for (CoStudentenrollment coStudentenrollment : list) {
//				System.out.println(coStudentenrollment.getAadhaarnumber());
//			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<CoSeatallocationmatrix> LoadSeatAllocationDataCount(String yearval, int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoSeatallocationmatrix> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where year = :year and createby = :createby and status = '1'");

			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoSeatallocationmatrix>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List<CoSeatallocationmatrix> getSeatAllocationDataByInsID(String yearval, int userid, int cid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoSeatallocationmatrix> list = null;
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try {

			Query q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where year = :year and createby = :createby and status = '1' and insid.id=:insid");

			q1.setParameter("createby", userid);
			q1.setParameter("year", yearval);
			q1.setParameter("insid", cid);

			System.out.println("Q1" + q1.getQueryString());
			list = (List<CoSeatallocationmatrix>) q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List ChecKInstituteExist(int insid, int categoryid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where insid.id=:insid and catid.id=:catid and status = '1'");
			q1.setParameter("insid", insid);
			q1.setParameter("catid", categoryid);

			list = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public String GetStatePercentageFromInsID(int inid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		String jsonstring = "";
		JSONObject jsonObject = new JSONObject();
		try {
			Query q1 = sessionHQL.createQuery("From CoSeatallocationmatrix where insid.id=:insid  and status = '1'");
			q1.setParameter("insid", inid);

			list = q1.list();
			if (!list.isEmpty()) {
				CoSeatallocationmatrix coSeatallocationmatrix = (CoSeatallocationmatrix) list.get(0);
				jsonObject.put("status", "1");
				jsonObject.put("message", "success");
				jsonObject.put("statepercentage", coSeatallocationmatrix.getStateCounPercentage());
				jsonObject.put("stateseat", coSeatallocationmatrix.getStateSeat());
				jsonObject.put("centralcouncper", coSeatallocationmatrix.getCentralCounPercentage());
				jsonObject.put("centralcouncseat", coSeatallocationmatrix.getCentralSeat());
				jsonObject.put("totalseat", coSeatallocationmatrix.getTotalseat());
			} else {
				jsonObject.put("status", "0");
				jsonObject.put("message", "No Data Found for Selected Institute");
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return jsonObject.toJSONString();
	}

	@Override
	public List ChecKInstituteExistForState(int insid, int categoryid, int stateid, int userid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where insid.id=:insid and catid.id=:catid and insid.stateId.stateId=:stateId and createby = :createby and status = '1'");
			q1.setParameter("insid", insid);
			q1.setParameter("catid", categoryid);
			q1.setParameter("stateId", stateid);
			q1.setParameter("createby", userid);

			list = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return list;
	}

	@Override
	public List GetInstituteDataAccordingToSystem(int systemid, String systemName, int createby, long seid, int round,
			String commission, int stateid) {

		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List obj = null;
		String comtype = "";
		try {

			Query q2 = sessionHQL.createQuery(
					"select distinct(inid.id)  From CoStudentchoicefilling where round =:round and seid.seid =:seid and status = :status");
			q2.setParameter("round", round + "");
			q2.setParameter("seid", seid);
			q2.setParameter("status", "0");
			Query q1 = null;

			if (commission.equalsIgnoreCase("Commission")) {

				if (!q2.list().isEmpty()) {

					q1 = sessionHQL.createQuery(
							"select distinct(insid.id),insid.instituteName From CoSeatallocationmatrix where  createby = :createby and status = '1' and insid.systemId.id = :systemid and insid.systemId.systemName = :systemName and insid.id   not in (:ids) and insid.status='1'");
					q1.setParameter("ids", q2.list());
				} else {
					q1 = sessionHQL.createQuery(
							"select distinct(insid.id),insid.instituteName From CoSeatallocationmatrix where  createby = :createby and status = '1' and insid.systemId.id = :systemid and insid.systemId.systemName = :systemName  and insid.status='1'");
				}
//		"select distinct(c.insid.id),c.insid.instituteName From CoSeatallocationmatrix c,CoStudentchoicefilling cs where  cs.seid.seid=6 and c.insid.id not in(cs.inid.id) and  c.createby = :createby and c.status = '1' and c.insid.systemId.id = :systemid and c.insid.systemId.systemName = :systemName");
				q1.setParameter("systemid", systemid);
				q1.setParameter("systemName", systemName);
				q1.setParameter("createby", createby);

				System.out.println("Q!" + q1.getQueryString());
			} else {
				if (!q2.list().isEmpty()) {

					q1 = sessionHQL.createQuery(
							"select distinct(insid.id),insid.instituteName From CoSeatallocationmatrix where insid.stateId.stateId = :stateId and createby = :createby and status = '1' and insid.systemId.id = :systemid and insid.systemId.systemName = :systemName and insid.id   not in (:ids)");
					q1.setParameter("ids", q2.list());
				} else {
					q1 = sessionHQL.createQuery(
							"select distinct(insid.id),insid.instituteName From CoSeatallocationmatrix where insid.stateId.stateId = :stateId and createby = :createby and status = '1' and insid.systemId.id = :systemid and insid.systemId.systemName = :systemName");
				}
//		"select distinct(c.insid.id),c.insid.instituteName From CoSeatallocationmatrix c,CoStudentchoicefilling cs where  cs.seid.seid=6 and c.insid.id not in(cs.inid.id) and  c.createby = :createby and c.status = '1' and c.insid.systemId.id = :systemid and c.insid.systemId.systemName = :systemName");
				q1.setParameter("systemid", systemid);
				q1.setParameter("systemName", systemName);
				q1.setParameter("createby", createby);
				q1.setParameter("stateId", stateid);

				System.out.println("Q!" + q1.getQueryString());
			}
			obj = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return obj;
	}

	@Override
	public CoSeatallocationmatrix GetTotalSeatsAccordingToCategory(int insid, int categoryid, int userid, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		CoSeatallocationmatrix coSeatallocationmatrix = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where insid.id=:insid and catid.id=:catid and year=:year and createby = :createby and status = '1' order by cid desc");
			q1.setParameter("insid", insid);
			q1.setParameter("catid", categoryid);

			q1.setParameter("createby", userid);
			q1.setParameter("year", year);

			list = q1.list();
			if (!list.isEmpty()) {
				coSeatallocationmatrix = (CoSeatallocationmatrix) list.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coSeatallocationmatrix;
	}

	@Override
	public int GetAvailableSeatsFromChocieFillingTable(int insid, String assignedsatus, int catid, int createby,
			String year, String rolename) {
		int count = 0;
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		// CoSeatallocationmatrix coSeatallocationmatrix = null;
		try {
			Query q1 = null;
			if (rolename.equalsIgnoreCase("State Council")) {
				q1 = sessionHQL.createQuery(
						"From CoStudentchoicefilling where inid.id=:insid and assignedcategory.id=:catid and seid.year=:year  and commtype = :commtype and status =:status");
				q1.setParameter("commtype", createby);
			} else {
				q1 = sessionHQL.createQuery(
						"From CoStudentchoicefilling where inid.id=:insid and assignedcategory.id=:catid and seid.year=:year  and seid.createby = :createby and status =:status");
				q1.setParameter("createby", createby);
			}

			q1.setParameter("insid", insid);
			q1.setParameter("catid", catid);
			q1.setParameter("status", assignedsatus);
			q1.setParameter("year", year);

			list = q1.list();
			count = list.size();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	@Override
	public int GetAvailableSeatsFromChocieFillingTableRound(int insid, String assignedsatus, int catid, int createby,
			String year, String rolename, String round) {
		int count = 0;
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		// CoSeatallocationmatrix coSeatallocationmatrix = null;
		try {
			Query q1 = null;
//			if (rolename.equalsIgnoreCase("State Council")) {
			q1 = sessionHQL.createQuery(
					"From CoStudentchoicefilling where inid.id=:insid and assignedcategory.id=:catid and seid.year=:year  and commtype = :commtype and status =:status");
			q1.setParameter("commtype", createby);
//			} else {
//				q1 = sessionHQL.createQuery(
//						"From CoStudentchoicefilling where inid.id=:insid and assignedcategory.id=:catid and seid.year=:year  and seid.createby = :createby and status =:status and round=:round");
//				q1.setParameter("createby", createby);
//			}

			q1.setParameter("insid", insid);
			q1.setParameter("catid", catid);
			q1.setParameter("status", assignedsatus);
			q1.setParameter("year", year);
			// q1.setParameter("round", round);

			list = q1.list();
			count = list.size();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	@Override
	public int GetAvailableSeatsFromChocieFillingTableSelf(int insid, String assignedsatus, int catid, Long seid,
			String year) {
		int count = 0;
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List list = null;
		String comtype = "";
		// CoSeatallocationmatrix coSeatallocationmatrix = null;
		try {
			Query q1 = sessionHQL.createQuery(
					"From CoStudentchoicefilling where inid.id=:insid and seid.catid.id=:catid and seid.year=:year  and seid.seid = :seid and status =:status");
			q1.setParameter("insid", insid);
			q1.setParameter("catid", catid);
			q1.setParameter("status", assignedsatus);
			q1.setParameter("seid", seid);
			q1.setParameter("year", year);

			list = q1.list();
			count = list.size();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	@Override
	public List GetInstituteDataAccordingToSystemGeneral(int systemid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List obj = null;
		String comtype = "";
		try {

			Query q1 = null;
			if (systemid == 0) {
//				q1 = sessionHQL.createQuery(
//						"select distinct(insid.id),insid.instituteName,insid.systemId.systemName From CoSeatallocationmatrix where  status = '1' and insid.status = '1' group by insid.systemId.systemName,insid.id,insid.instituteName order by insid.systemId.systemName");
			
			q1=sessionHQL.createQuery("select distinct(id),instituteName From EduLmsInstituteReg where status='1'");
			} else {
//				q1 = sessionHQL.createQuery(
//						"select distinct(insid.id),insid.instituteName From CoSeatallocationmatrix where  status = '1' and insid.systemId.id = :systemid and insid.status = '1'");
//				q1.setParameter("systemid", systemid);
				q1=sessionHQL.createQuery("select distinct(id),instituteName From EduLmsInstituteReg where status='1' and systemId.id=:systemid order by instituteName");
				q1.setParameter("systemid", systemid);
			}

//			System.out.println("sys id---"+systemid+"Q!--->  " + q1.getQueryString());

			obj = q1.list();
			
			
//			System.err.println("obj---"+obj.size());

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return obj;
	}

	@Override
	public List<CoSeatallocationmatrix> getSeatAllocationData(int userid, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoSeatallocationmatrix> coSeatallocationmatrixs = null;
		String comtype = "";
		try {

			Query q1 = null;
			q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where year=:year and createby =:createby and status = '1' and insid.status = '1'");
			q1.setParameter("createby", userid);
			q1.setParameter("year", year);

			System.out.println("Q!" + q1.getQueryString());

			coSeatallocationmatrixs = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coSeatallocationmatrixs;
	}

	@Override
	public long CheckAlreadySeatsTransferredStatus(int userid, String year) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<CoSeatallocationmatrix> coSeatallocationmatrixs = null;
		long count = 0;
		try {

			Query q1 = null;
			q1 = sessionHQL.createQuery(
					"select count(cid) From CoSeatallocationmatrix where year=:year  and status = '1' and insid.status = '1' and transfer_status = 'Y'");
			// q1.setParameter("createby", userid);
			q1.setParameter("year", year);

			System.out.println("Q!" + q1.getQueryString());

			count = (long) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return count;
	}

	@Override
	public CoSeatallocationmatrix GetSeatForUpdate(Integer insid, Integer catid2, String year, int stateid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoSeatallocationmatrix coSeatallocationmatrixs = null;
		String comtype = "";
		try {

			Query q1 = null;
			q1 = sessionHQL.createQuery(
					"From CoSeatallocationmatrix where year=:year and  status = '1' and insid.status = '1' and insid.id=:insid and catid.id=:catid and insid.stateId.stateId =:stateid order by cid desc");
			q1.setParameter("insid", insid);
			q1.setParameter("catid", catid2);
			q1.setParameter("year", year);
			q1.setParameter("stateid", stateid);

			q1.setMaxResults(1);
			System.out.println("Q!" + q1.getQueryString());

			coSeatallocationmatrixs = (CoSeatallocationmatrix) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return coSeatallocationmatrixs;
	}

	@Override
	public List GetDistinctInstitute(String year, int userId) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoSeatallocationmatrix coSeatallocationmatrixs = null;
		String comtype = "";
		List response = null;
		try {

			Query q1 = null;
			q1 = sessionHQL.createQuery(
					"select distinct(insid.id),insid.instituteName From CoSeatallocationmatrix where year=:year and  status = '1' and insid.status = '1' and createby =:userId order by insid.instituteName");
			q1.setParameter("userId", userId);
			q1.setParameter("year", year);

			System.out.println("Q!" + q1.getQueryString());

			response = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return response;
	}

	@Override
	public List<CoSeatallocationmatrix> GetDistinctCategoryFromSeatAllocation(String year, int userId) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoSeatallocationmatrix coSeatallocationmatrixs = null;
		String comtype = "";
		List<CoSeatallocationmatrix> response = null;
		try {

			Query q1 = null;
			q1 = sessionHQL.createQuery(
					"select distinct(catid.id),catid.category From CoSeatallocationmatrix where year=:year and  status = '1' and catid.status = '1' and createby =:userId order by catid.id");
			q1.setParameter("userId", userId);
			q1.setParameter("year", year);

			System.out.println("Q!" + q1.getQueryString());

			response = q1.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return response;
	}

	@Override
	public String GetSeatFromInstitute(int userid, String year, int insid) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CoSeatallocationmatrix coSeatallocationmatrixs = null;
		String comtype = "";
		String response = null;
		try {

			Query q1 = null;
			q1 = sessionHQL.createSQLQuery("SELECT cast(json_agg(json_build_object('cat_id',t.catid,\n"
					+ "                                  'cat_seat',t.cat_seat,\n"
					+ "                                  'transfered_seats',t.transfered_seats\n"
					+ ")) as text) from co_seatallocationmatrix t where t.insid =:insid and t.createby=:userId and t.year = :year ");
			q1.setParameter("userId", userid);
			q1.setParameter("year", year);
			q1.setParameter("insid", insid);
			System.out.println("Q!" + q1.getQueryString());

			response = (String) q1.uniqueResult();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return response;
	}

}
