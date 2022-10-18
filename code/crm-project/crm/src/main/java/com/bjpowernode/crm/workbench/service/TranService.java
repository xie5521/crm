package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.FunnelVO;
import com.bjpowernode.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranService {
    List<Tran> queryTransactionByConditionForPage(Map<String, Object> map);

    int queryCountOfTransactionByCondition(Map<String, Object> map);

    void saveCreateTransaction(Tran tran);

    Tran queryTransactionById(String id);

    void saveEditTransaction(Tran tran);

    void deleteTranByIds(String[] ids);

    Tran queryTranForDetailById(String id);

    List<FunnelVO> queryCountOfTranGroupByStage();
}