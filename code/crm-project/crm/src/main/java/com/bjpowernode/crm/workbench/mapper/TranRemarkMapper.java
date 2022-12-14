package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.TranRemark;

import java.util.List;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat Jun 11 14:14:39 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat Jun 11 14:14:39 CST 2022
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat Jun 11 14:14:39 CST 2022
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat Jun 11 14:14:39 CST 2022
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat Jun 11 14:14:39 CST 2022
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat Jun 11 14:14:39 CST 2022
     */
    int updateByPrimaryKey(TranRemark record);

    //批量保存创建的交易备注
    int insertTranRemarkByList(List<TranRemark> list);

    List<TranRemark> selectTranRemarkForDetailByTranId(String id);

    int insertTranRemark(TranRemark tranRemark);

    int deleteTranRemarkById(String id);

    int updateTranRemark(TranRemark tranRemark);

    int deleteTranRemarkByTranIds(String[] tranId);
}
