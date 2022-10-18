package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.FunnelVO;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    //保存创建的市场活动
    int saveCreateActivity(Activity activity);

    //根据条件分页查询市场活动的列表
    List<Activity> queryActivityByConditionForPage(Map<String, Object> map);

    //查询符合条件的分页查询的总条数
    int queryCountofActivityByCondition(Map<String, Object> map);

    //根据id删除
    int deleteActivityByIds(String[] ids);


    //根据id查询市场活动的信息,修改市场活动
    Activity queryActivityById(String id);

    //保存更新的市场活动，保存修改的市场活动
    int saveEditActivity(Activity activity);

    //查询所有的市场活动，批量导出
    List<Activity> queryAllActivitys();

    //查询所有的市场活动，选择导出
    List<Activity> queryXzActivity(String[] ids);

    //批量保存创建的市场活动，导入文件
    int saveCreateActivityByList(List<Activity> activityList);

    //根据activityId查询市场活动的明细信息
    Activity queryActivityForDetailById(String id);

    //根据clueId查询该线索相关联的市场活动的明细
    List<Activity> queryActivityForDetailByClueId(String clueId);

    //根据name模糊查询市场活动，并且把已经跟clueId关联过得市场活动排除
    List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map);

    //根据id数组ids查询市场活动的明细信息
    List<Activity> queryActivityForDetailByIds(String[] ids);

    //根据名字模糊查询市场活动，并且查询跟clueId关联过得市场活动
    List<Activity> queryActivityForConvertByNameClueId(Map<String, Object> map);

    List<Activity> queryActivityForDetailByContactsId(String contactsId);

    List<Activity> queryActivityForDetailByNameAndContactsId(Map<String, Object> map);

    List<Activity> queryActivityByFuzzyName(String activityName);

    List<FunnelVO> queryCountOfActivityGroupByOwner();
}
