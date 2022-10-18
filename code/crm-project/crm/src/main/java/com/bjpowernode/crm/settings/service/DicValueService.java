package com.bjpowernode.crm.settings.service;


import com.bjpowernode.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueService {

    //根据typeCode查询数据字典值
    List<DicValue> queryDicValueByTypeCode(String typeCode);

    //根据id查询数据字典值
    DicValue queryDicValueById(String id);

}
