package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.HSSFUtiles;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    //备注层service
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        //调用service方法，保存创建的市场活动
        try {
            int ret = activityService.saveCreateActivity(activity);
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

    //分页查询
    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name, String owner, String startDate, String endDate,
                                                  Integer pageNo, Integer pageSize){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo-1) * pageSize);
        map.put("pageSize", pageSize);

        //调用Service层方法,查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountofActivityByCondition(map);
        //根据查询结果生成响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("activityList", activityList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }

    //根据id删除数据
    @RequestMapping("/workbench/activity/deleteActivityIds.do")
    @ResponseBody
    public Object deleteActivityIds(String[] id){
        ReturnObject    returnObject = new ReturnObject();
        try {
            int ret = activityService.deleteActivityByIds(id);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。。");
        }
        return returnObject;
    }

    //根据id查询市场活动的信息,修改市场活动
    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id) {
        //调用Service层方法，查询市场活动
        Activity activity = activityService.queryActivityById(id);
        //根据查询结果，返回响应信息
        return activity;
    }

    //保存更新的市场活动，保存修改的市场活动
    @RequestMapping("/workbench/activity/saveEditActivity.do")
    @ResponseBody
    public Object  saveEditActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        activity.setEditTime(DateUtils.formatDateTime(new Date()));
        activity.setEditBy(user.getId());
        //调用Service层方法，查询市场活动
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityService.saveEditActivity(activity);
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

    //文件下载的功能
    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws IOException {
        //1，设置响应类型、
        response.setContentType("application/octet-stream;charset=UTF-8");
        //2，获取输出流
        OutputStream out = response.getOutputStream();

        //浏览器接收到响应信息之后，默认在浏览器窗口直接打开，即使打不开，也会调应用程序打开，只有实在打不开，才会激活文件下载窗口
        //可以设置响应头信息，使浏览器接收到响应信息后直接激活文件下载窗口，即使能打开也不打开
        response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");

        //3，读取excel文件（InputStram），输出到浏览器（OutputStream）
        InputStream is = new FileInputStream("E:\\CRM-ssm\\code\\serverDir\\studentList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while((len = is.read(buff)) != -1){
            out.write(buff, 0, len);
        }
        is.close();
        out.flush();
    }

    //查询所有的市场活动,导出下载
    @RequestMapping("/workbench/activity/exportAllActivitys.do")
    public void exportAllActivitys(HttpServletResponse response) throws Exception{
        //调用service层方法，查询所有的市场活动
        List<Activity> activityList = activityService.queryAllActivitys();
        //创建excel文件，并且把activityList写入到excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        //遍历activityList，创建HSSFRow对象，生成所有的数据行
        if(activityList != null && activityList.size() > 0){
            Activity activity = null;
            for(int i = 0; i <activityList.size(); i++) {
                activity = activityList.get(i);
                //每遍历出一个activity，生成一行
                row = sheet.createRow(i + 1);
                //每一行创建11列，每一列的数据从activity中获取
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }

        //根据wb对象生成excel文件
        /*OutputStream os = new FileOutputStream("E:\\CRM-ssm\\code\\serverDir\\studentList.xls");
        wb.write(os);
        os.close();
        wb.close();*/
        //把生成的excel文件下载到客户端
        //1，设置响应类型、
        //response.setContentType("application/octet-stream;charset=UTF-8");
        //2，获取输出流
        //OutputStream out = response.getOutputStream();
        //浏览器接收到响应信息之后，默认在浏览器窗口直接打开，即使打不开，也会调应用程序打开，只有实在打不开，才会激活文件下载窗口
        //可以设置响应头信息，使浏览器接收到响应信息后直接激活文件下载窗口，即使能打开也不打开
        //response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");
        //3，读取excel文件（InputStram），输出到浏览器（OutputStream）
        /*InputStream is = new FileInputStream("E:\\CRM-ssm\\code\\serverDir\\studentList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while((len = is.read(buff)) != -1){
            out.write(buff, 0, len);
        }
        is.close();*/
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");
        wb.write(out);
        wb.close();
        out.flush();
    }

    //查询所有的市场活动,选择导出下载
    @RequestMapping("/workbench/activity/exportXzActivitys.do")
    private void exportXzActivitys(String[] ids,HttpServletResponse response) throws Exception{
        //调用service层方法，查询所有的市场活动
        List<Activity> activityList = activityService.queryXzActivity(ids);
        //创建excel文件，并且把activityList写入到excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        //遍历activityList，创建HSSFRow对象，生成所有的数据行
        if(activityList != null && activityList.size() > 0){
            Activity activity = null;
            for(int i = 0; i <activityList.size(); i++) {
                activity = activityList.get(i);
                //每遍历出一个activity，生成一行
                row = sheet.createRow(i + 1);
                //每一行创建11列，每一列的数据从activity中获取
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");
        wb.write(out);
        wb.close();
        out.flush();
    }

    //文件上传
    //必须配置SpringMVC的文件上传解析器 multipartResolver
    @RequestMapping("/workbench/activity/fileUpLoad.do")
    @ResponseBody
    public Object fileUpLoad(String userName, MultipartFile myFile) throws IOException {
        //把文本数据打印到控制台
        System.out.println("userName="+ userName);
        //把文件在服务器指定目录中生成一个同样的文件
        String originalFilename = myFile.getOriginalFilename();
        File file = new File("E:\\CRM-ssm\\code\\新建文件夹\\",originalFilename); //路径必须手动创建好，文件会自动创建
        myFile.transferTo(file);

        //返回响应信息
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
        returnObject.setMessage("上传成功");
        return returnObject;
    }

    //批量保存创建的市场活动，导入文件
    @RequestMapping("/workbench/activity/importActivity.do")
    @ResponseBody
    public Object  importActivity(MultipartFile activityFile, HttpSession session){
        User user  = (User) session.getAttribute(Constants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        try {
            //把excel文件写到磁盘目录中
            /*String originalFilename = activityFile.getOriginalFilename();
            File file = new File("E:\\CRM-ssm\\code\\新建文件夹\\",originalFilename); //路径必须手动创建好，文件会自动创建
            activityFile.transferTo(file);*/

            //解析excel文件，获取文件中的数据，并且封装成activityList
            //根据excel文件生成HSSFWorkbook对象，封装了excel文件的全部信息
            //InputStream is = new FileInputStream("E:\\CRM-ssm\\code\\新建文件夹\\"+originalFilename);
            InputStream is = activityFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(is);
            //根据wb获取HSSFSheet对象，封装了一页的所有信息
            HSSFSheet sheet = wb.getSheetAt(0);       //页的下标
            //根据sheet获取HSSFRow对象，封装了一行的所有信息
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            List<Activity> activityList = new ArrayList<>();
            for(int i = 1; i <= sheet.getLastRowNum(); i++){     //getLastRowNum最后一行的下表
                row = sheet.getRow(i);        //行的下表，下表从0开始，
                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtils.formatDateTime(new Date()));
                activity.setCreateBy(user.getId());

                for(int j = 0; j < row.getLastCellNum(); j++){     //getLastCellNum最后一列下表+1
                    //根据row获取HSSFCell对象，封装了一列的所有信息
                    cell = row.getCell(j);
                    //获取数据
                    String cellValue = HSSFUtiles.getCellValueForStr(cell);
                    if(j == 0){
                        activity.setName(cellValue);
                    }else if(j == 1){
                        activity.setStartDate(cellValue);
                    }else if(j == 2){
                        activity.setEndDate(cellValue);
                    }else if(j == 3){
                        activity.setCost(cellValue);
                    }else if(j == 4){
                        activity.setDescription(cellValue);
                    }
                }
                //每一行中的所有列都封装完成之后，保存到List当中
                activityList.add(activity);
            }
            //调用service层方法，保存所有的市场活动
            int ret = activityService.saveCreateActivityByList(activityList);

            returnObject.setCode(Constants.RETURN_OBJECT_SUCCESS);
            returnObject.setRetData(ret);
        } catch (IOException e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。");
        }
        return returnObject;
    }

    //根据市场活动activityId查询所有市场活动明细信息
    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        //调用service层方法，查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        //备注层service
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        //把数据保存到request中
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        //请求转发
        return "workbench/activity/detail";
    }

}
