package com.AyushEdu.dao.Part_One.Masters;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_NATURE_OF_APPOINTMENT;
public interface ClgAppointDAO {

    public CLG_REG_NATURE_OF_APPOINTMENT getNatureAppointmentByid(int id);

    public List<Map<String, Object>> DataTableClgAppointDataList(int startPage, int pageLength, String search,
                                                                 String orderColunm, String orderType, String clg_appoint_name,String status);

    public String updateNatureOfAppointment(CLG_REG_NATURE_OF_APPOINTMENT td);

    public long DataTableClgAppointDataTotalCount(String search, String clg_appoint_name);



}
