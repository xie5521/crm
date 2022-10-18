package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.workbench.domain.FunnelVO;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.ContactsService;
import com.bjpowernode.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChartController {

    @Autowired
    private TranService tranService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/chart/transaction/toTranIndex.do")
    public String toTranIndex(){
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/queryCountOfTranGroupByStage.do")
    @ResponseBody
    public Object queryCountOfTranGroupByStage(){
        List<FunnelVO> funnelVOList = tranService.queryCountOfTranGroupByStage();
        return funnelVOList;
    }

    @RequestMapping("/workbench/chart/activity/toActivityIndex.do")
    public String toActivityIndex(){
        return "workbench/chart/activity/index";
    }

    @RequestMapping("/workbench/chart/activity/queryCountOfActivityGroupByOwner.do")
    @ResponseBody
    public Object queryCountOfActivityGroupByOwner(){
        List<FunnelVO> funnelVOList = activityService.queryCountOfActivityGroupByOwner();
        return funnelVOList;
    }

    @RequestMapping("/workbench/chart/clue/toClueIndex.do")
    public String toClueIndex(){
        return "workbench/chart/clue/index";
    }

    @RequestMapping("/workbench/chart/clue/queryCountOfClueGroupByClueStage.do")
    @ResponseBody
    public Object queryCountOfClueGroupByClueStage(){
        List<String> clueStage = clueService.queryClueStageOfClueGroupByClueStage();
        List<Integer> counts = clueService.queryCountOfClueGroupByClueStage();
        Map<String, Object> map = new HashMap<>();
        map.put("clueStage",clueStage);
        map.put("counts",counts);
        return map;
    }

    @RequestMapping("/workbench/chart/customerAndContacts/toContactsIndex.do")
    public String toContactsIndex(){
        return "workbench/chart/customerAndContacts/index";
    }

    @RequestMapping("/workbench/chart/customerAndContacts/queryCountOfCustomerAndContactsGroupByCustomer.do")
    @ResponseBody
    public Object queryCountOfCustomerAndContactsGroupByCustomer(){
        List<FunnelVO> funnelVOList = contactsService.queryCountOfCustomerAndContactsGroupByCustomer();
        return funnelVOList;
    }
}
