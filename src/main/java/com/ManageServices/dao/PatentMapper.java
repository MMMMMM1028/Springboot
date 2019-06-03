package com.ManageServices.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PatentMapper {
    /**
     * 插入
     * Map
         *  patentId 传入不是自动生成
         *  patentName 专利名称
         *  expertId 所有者ID
         *  link  栈外连接
         *  summary 专利内容简介
     * @return 是否成功
     */
    int insertPatent(@Param("patent")Map patent);

    /**
     * 将指定专利转让给指定专家
     * @param patentId 专利号
     * @param newExpertId 目的专家ID
     * @return
     */
    int updatePatent(@Param("patentId")String patentId, @Param("newExpertId")int newExpertId);

    /**
     * 删除专利，二选一
     * @param patentId 通过id删除指定单个专利，下架专利
     * @param expertId 删除该专家的所有专利，专家注销
     * @return
     */
    int deletePatent(@Param("patentId")String patentId, @Param("expertId")int expertId);

    /**
     * 搜索专利，显示指定专家专利
     * @param patentId 通过专利号
     * @param expertId 显示指定专家的所有专利
     * @return Map{"patentId":string, "patentName":string, "expertId":int, "expertName":string}
     *      patentId，用于专家删除专利
     *      patentName，用于显示
     *      expertId，查看作者详细信息的条件
     *      expertName，用于显示
     */
    List<Map> selectPatent(@Param("patentId")String patentId, @Param("expertId")int expertId);

//    /**
//     * 专利没有详细内容
//     * 查看专利的详细内容
//     * @param patentId 专利号
//     * @return Map key为列名
//     */
//    List<Map> selectPatentDetial(@Param("patentID")String patentId);


}
