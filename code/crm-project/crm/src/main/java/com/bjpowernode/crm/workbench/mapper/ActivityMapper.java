package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.FunnelVO;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Wed Jun 01 14:04:48 CST 2022
     */
    int deleteByPrimaryKey(String id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Wed Jun 01 14:04:48 CST 2022
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Wed Jun 01 14:04:48 CST 2022
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Wed Jun 01 14:04:48 CST 2022
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Wed Jun 01 14:04:48 CST 2022
     */
    int updateByPrimaryKey(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *保存创建的市场活动
     * @mbggenerated Wed Jun 01 14:04:48 CST 2022
     */
    int insertActivity(Activity activity);

    //根据条件分页查询市场活动的列表
    List<Activity> selectActivityByConditionForPage(Map<String , Object> map);

    //查询符合条件的分页查询的总条数
    int selectCountofActivityByCondition(Map<String, Object> map);

    //根据ids批量删除市场活动
    int deleteActivityByIds(String[] ids);

    //根据id查询市场活动的信息,修改市场活动
    Activity selectActivityById(String id);

    //保存更新的市场活动，保存修改的市场活动
    int updateActivity(Activity activity);

    //查询所有的市场活动，批量导出
    List<Activity> selectAllActivitys();

    //查询所有的市场活动，选择导出
    List<Activity>  selectXzActivity(String[] ids);

    //批量保存创建的市场活动，导入文件
    int insertActivityByList(List<Activity> activityList);

    //根据activityId查询市场活动的明细信息
    Activity selectActivityForDetailById(String id);

    //根据clueId查询该线索相关联的市场活动的明细
    List<Activity> selectActivityForDetailByClueId(String clueId);

    //根据name模糊查询市场活动，并且把已经跟clueId关联过得市场活动排除
    List<Activity> selectActivityForDetailByNameClueId(Map<String, Object> map);

    //根据id数组ids查询市场活动的明细信息
    List<Activity> selectActivityForDetailByIds(String[] ids);

    //根据名字模糊查询市场活动，并且查询跟clueId关联过得市场活动
    List<Activity> selectActivityForConvertByNameClueId(Map<String, Object> map);

    /**
     * 通过联系人id查询该联系人绑定的所有市场活动
     * @param contactsId 联系人id
     * @return 市场活动集合
     */
    List<Activity> selectActivityForDetailByContactsId(String contactsId);

    /**
     * 通过市场活动名和联系人id模糊查询市场活动
     * @param map 封装的联系人id和市场活动名参数
     * @return 市场活动集合
     */
    List<Activity> selectActivityForDetailByNameAndContactsId(Map<String, Object> map);

    /**
     * 通过市场活动名称进行模糊查询
     * @param activityName 市场活动模糊名称
     * @return 查询到的对应的市场活动
     */
    List<Activity> selectActivityByFuzzyName(String activityName);

    /**
     * 查询市场活动表中各个所有者的数据量
     * @return 数据集合
     */
    List<FunnelVO> selectCountOfActivityGroupByOwner();
}