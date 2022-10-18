package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.domain.ClueRemark;
import com.bjpowernode.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ClueRemarkController {

    @Autowired
    private ClueRemarkService clueRemarkService;

    //保存添加的线索备注
    @RequestMapping("/workbench/clue/saveCreateClueRemark.do")
    @ResponseBody
    public Object saveCreateClueRemark(ClueRemark clueRemark, HttpSession session){
        User user= (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setCreateTime(DateUtils.formatDateTime(new Date()));
        clueRemark.setCreateBy(user.getId());
        clueRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service方法，查询数据
            int ret = clueRemarkService.saveCreateClueRemark(clueRemark);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
                returnObject.setRetData(clueRemark);
            }else {
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

    //根据id删除市场活动备注
    @RequestMapping("/workbench/clue/deleteActivityRemarkById.do")
    @ResponseBody
    public Object deleteActivityRemarkById(String id){
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service方法
            int ret = clueRemarkService.deleteClueRemarkById(id);
            if(ret > 0){
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

    //根据id更新市场活动备注内容
    @RequestMapping("/workbench/clue/saveEditClueRemark.do")
    @ResponseBody
    public Object saveEditClueRemark(ClueRemark clueRemark, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        clueRemark.setEditTime(DateUtils.formatDateTime(new Date()));
        clueRemark.setEditBy(user.getId());
        clueRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueRemarkService.saveEditClueRemark(clueRemark);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
                returnObject.setRetData(clueRemark);
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
}
