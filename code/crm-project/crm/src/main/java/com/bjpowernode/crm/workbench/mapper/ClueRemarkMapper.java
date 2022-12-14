package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Tue Jun 07 12:53:06 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Tue Jun 07 12:53:06 CST 2022
     */
    int insert(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Tue Jun 07 12:53:06 CST 2022
     */
    int insertSelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Tue Jun 07 12:53:06 CST 2022
     */
    ClueRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Tue Jun 07 12:53:06 CST 2022
     */
    int updateByPrimaryKeySelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Tue Jun 07 12:53:06 CST 2022
     */
    int updateByPrimaryKey(ClueRemark record);

    //根据线索id查询该线索下所有备注
    List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId);

    //保存添加的线索备注
    int insertClueRemark(ClueRemark clueRemark);

    //根据id删除线索备注
    int deleteClueRemarkById(String id);

    //根据id更新市场活动备注内容
    int updateClueRemark(ClueRemark clueRemark);

    //根据clueId查询该线索下所有的备注信息
    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    //根据clueId删除该线索下所有的备注
    int deleteClueRemarkByClueId(String clueId);
}
