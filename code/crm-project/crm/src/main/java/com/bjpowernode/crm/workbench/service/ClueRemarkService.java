package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkService {

    //根据线索id查询该线索下所有备注
    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);

    //保存添加的线索备注
    int saveCreateClueRemark(ClueRemark clueRemark);

    //根据id删除线索备注
    int deleteClueRemarkById(String id);

    //根据id更新市场活动备注内容
    int saveEditClueRemark(ClueRemark clueRemark);


}
