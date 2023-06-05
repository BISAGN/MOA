package com.AyushEdu.dao.Part_One.Masters;

import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_DEPT_EQUIPMENT_ITEM_MASTER;

import java.util.List;
import java.util.Map;

@Service
public interface ClgDeptEquipmentItemDao {
    
    List<Map<String, Object>> DataTableDeptEquipmentItemDataList(int startPage, int pageLength, String search,
                                                                 String orderColunm, String orderType, String item_name, String status, Integer dept_equip_item_id);

    String updateDeptEquipmentItem(CLG_REG_DEPT_EQUIPMENT_ITEM_MASTER td);

    long DataTableDeptEquipmentItemCount(String search, String item_name, Integer dept_equip_item_id);

}
