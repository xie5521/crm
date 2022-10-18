package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {

    //根据市场活动activityId查询所有市场活动明细信息
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    //保存添加的市场活动备注
    int saveCreateActivityRemark(ActivityRemark remark);

    //根据id删除市场活动备注
    int deleteActivityRemarkById(String id);

    //根据id更新市场活动备注内容
    int saveEditActivityRemark(ActivityRemark remark);


}
