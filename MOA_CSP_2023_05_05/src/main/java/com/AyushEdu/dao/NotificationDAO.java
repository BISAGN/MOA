package com.AyushEdu.dao;

import java.util.List;
import java.util.Map;

public interface NotificationDAO {

	public List<Map<String, Object>> notiboxlist(String ticket, String ticket_status, String from_date,
			String to_date, String help_topic, String userId, String role, String module, String label1,String screen_url);
	
	public List<Map<String, Object>> notiseeall_list(String ticket, String ticket_status, String from_date,
			String to_date, String help_topic, String userId, String role, String module, String label1,String screen_url);
}
