package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationService {

    //批量保存创建的线索和市场活动的关联关系
    int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list);

    //根据线索id和市场互动id删除市场活动关联关系
    int deleteClueAcitivityRelationByClueIdActivityId(ClueActivityRelation relation);
}
