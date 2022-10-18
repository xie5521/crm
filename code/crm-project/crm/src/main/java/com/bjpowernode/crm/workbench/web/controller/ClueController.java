package com.bjpowernode.crm.workbench.web.controller;


import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.DicValueService;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.*;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.ClueActivityRelationService;
import com.bjpowernode.crm.workbench.service.ClueRemarkService;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    //跳转到线索主页面
    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<User> userList=userService.queryAllUsers();
        List<DicValue> appellationList=dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList=dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList",userList);
        request.setAttribute("appellationList",appellationList);
        request.setAttribute("clueStateList",clueStateList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/clue/index";
    }

    //保存创建的线索
    @RequestMapping("/workbench/clue/saveCreateClue.do")
    public @ResponseBody Object saveCreateClue(Clue clue, HttpSession session){
        User user=(User)session.getAttribute(Constants.SESSION_USER);

        //封装参数
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.formatDateTime(new Date()));
        clue.setCreateBy(user.getId());

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的线索
            int ret = clueService.saveCreateClue(clue);

            if(ret>0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试..");
        }

        return returnObject;
    }

    //分页查询
    @RequestMapping("/workbench/clue/queryClueByConditionForPage.do")
    @ResponseBody
    public Object queryClueByConditionForPage(String fullname, String company, String phone, String mphone,
                                                  String source,String owner,String state,
                                                  Integer pageNo, Integer pageSize){
        Map<String, Object> map = new HashMap<>();
        map.put("fullname",fullname);
        map.put("company",company);
        map.put("phone",phone);
        map.put("mphone",mphone);
        map.put("source",source);
        map.put("owner",owner);
        map.put("state",state);
        map.put("beginNo", (pageNo-1) * pageSize);
        map.put("pageSize", pageSize);

        //调用Service层方法,查询数据
        List<Clue> clueList = clueService.queryClueByConditionForPage(map);
        int totalRows = clueService.queryCountofClueByCondition(map);
        //根据查询结果生成响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("clueList", clueList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }

    //根据id删除线索
    @RequestMapping("/workbench/clue/deleteClueByIds.do")
    @ResponseBody
    public Object deleteClueByIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，查询数据
            int ret = clueService.deleteClueByIds(id);
            if(ret >0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。");
        }
        return returnObject;
    }

    //根据id查询线索的信息,修改线索
    @RequestMapping("/workbench/clue/selectClueById.do")
    @ResponseBody
    public Object selectClueById(String id){
        //调用Service层方法，查询市场活动
        Clue clue = clueService.queryClueById(id);
        //根据查询结果，返回响应信息
        return clue;
    }

    //保存更新的线索，保存修改的线索
    @RequestMapping("/workbench/clue/saveEditClue.do")
    @ResponseBody
    public Object  saveEditClue(Clue clue, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        clue.setEditTime(DateUtils.formatDateTime(new Date()));
        clue.setEditBy(user.getId());
        //调用Service层方法，查询市场活动
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueService.saveEditClue(clue);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。");
        }

        return returnObject;
    }

    //根据市场活动activityId查询所有市场活动明细信息
    @RequestMapping("/workbench/clue/detailClue.do")
    public String detailClue(String id,HttpServletRequest request){
        //调用service层方法，查询数据
        Clue clue = clueService.queryClueForDetailById(id);
        //备注层service
        List<ClueRemark> remarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        //调ActivityService方法
        List<Activity> activityList = activityService.queryActivityForDetailByClueId(id);
        //把数据保存到request中
        request.setAttribute("clue",clue);
        request.setAttribute("remarkList",remarkList);
        request.setAttribute("activityList",activityList);
        //请求转发
        return "workbench/clue/detail";
    }

    //根据name模糊查询市场活动，并且把已经跟clueId关联过得市场活动排除
    @RequestMapping("/workbench/clue/queryActivityFoeDetailByNameClueId.do")
    @ResponseBody
    public Object queryActivityFoeDetailByNameClueId(String activityName,String clueId){
        Map<String, Object> map = new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service，查询市场活动
        List<Activity> activityList = activityService.queryActivityForDetailByNameClueId(map);
        //根据查询结果，返回响应信息
        return activityList;
    }

    //保存关联的市场活动
    @RequestMapping("/workbench/clue/saveBund.do")
    @ResponseBody
    public Object saveBund(String[] activityId, String clueId){
        //封装参数
        List<ClueActivityRelation> relationList = new ArrayList<>();
        ClueActivityRelation car = null;
        for(String ai : activityId){
            car = new ClueActivityRelation();
            car.setActivityId(ai);
            car.setClueId(clueId);
            car.setId(UUIDUtils.getUUID());
            relationList.add(car);
        }
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service方法，批量保存线索和市场活动关联关系的明细信息
            int ret = clueActivityRelationService.saveCreateClueActivityRelationByList(relationList);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);

                List<Activity> activityList = activityService.queryActivityForDetailByIds(activityId);
                returnObject.setRetData(activityList);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。");
        }
        return returnObject;
    }

    //根据线索id和市场互动id删除市场活动关联关系
    @RequestMapping("/workbench/clue/saveUnbund.do")
    @ResponseBody
    public Object saveUnbund(ClueActivityRelation relation){
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service方法，删除数据
            int ret = clueActivityRelationService.deleteClueAcitivityRelationByClueIdActivityId(relation);
            if(ret > 0 ){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。");
        }
        return  returnObject;
    }

    //查询线索的关键信息
    @RequestMapping("/workbench/clue/toConvert.do")
    public String toConvert(String id, HttpServletRequest request){
        //调用service方法，查询线索明细信息
        Clue clue = clueService.queryClueForDetailById(id);
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        //把数据保存到作用域中
        request.setAttribute("clue",clue);
        request.setAttribute("stageList",stageList);
        return "workbench/clue/convert";
    }

    //根据名字模糊查询市场活动，并且查询跟clueId关联过得市场活动
    @RequestMapping("/workbench/clue/queryActivityForConverByNameClueId.do")
    @ResponseBody
    public Object queryActivityForConverByNameClueId(String activityName,String clueId){
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service方法，查询市场活动
        List<Activity> activityList = activityService.queryActivityForConvertByNameClueId(map);
        //根据查询结果，生成响应信息
        return activityList;
    }

    //保存线索转换
    @RequestMapping("/workbench/clue/convertClue.do")
    @ResponseBody
    public Object convertClue(String clueId, String money, String name, String expectedDate,
                              String stage,String activityId, String isCreateTran,HttpSession session){
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedDate",expectedDate);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isCreateTran",isCreateTran);
        map.put(Constants.SESSION_USER,session.getAttribute(Constants.SESSION_USER));
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存线索转换
            clueService.saveConvertClue(map);

            returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。");
        }

        return returnObject;
    }
}
