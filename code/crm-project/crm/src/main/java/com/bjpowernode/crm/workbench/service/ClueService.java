package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {

    //保存创建的线索
    int saveCreateClue(Clue clue);

    //根据条件分页查询市场活动的列表
    List<Clue> queryClueByConditionForPage(Map<String, Object> map);

    //查询符合条件的分页查询的总条数
    int queryCountofClueByCondition(Map<String, Object> map);

    //根据id删除线索
    int deleteClueByIds(String[] ids);

    //根据id查询线索的信息,修改线索
    Clue queryClueById(String id);

    //保存更新的线索，保存修改的线索
    int saveEditClue(Clue clue);

    //根据id查询线索的明细信息
    Clue queryClueForDetailById(String id);

    //保存创建的线索
    void saveConvertClue(Map<String,Object> map);

    List<String> queryClueStageOfClueGroupByClueStage();

    List<Integer> queryCountOfClueGroupByClueStage();

}
