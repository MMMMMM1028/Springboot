package com.ManageServices.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertMapper {
    /**
     * 批量添加
     * @param list
     * @return
     */
    int insertByBatch(List<Map> list);

    /**
     * 插入通过map，因为需要返回自增主键
     * @param expert
     * @return 自增主键
     */
    int insertExpertByMap(Map expert);


    /**
     * 插入
     * @param field 领域
     * @param organization 组织
     * @param name 真实姓名
     * @param tel 电话
     * @param mail 邮箱
     * @return 是否成功
     */
    int insertExpert(@Param("field")String field, @Param("organization")String organization,
                     @Param("name")String name, @Param("tel")String tel,
                     @Param("mail")String mail);
    /**
     * 修改以下属性
     * @param expertId
     * @param field 领域
     * @param organization 组织
     * @param income 收入增量,-1代表不修改
     * @param name 姓名
     * @param tel 电话
     * @param isPassed -1为认证，0正在审核，1已认证
     * @return 是否成功
     */
    int updateExpert(@Param("expertId")int expertId,@Param("field")String field, @Param("organization")String organization,
                     @Param("name")String name, @Param("tel")String tel, @Param("mail")String mail,
                     @Param("income")int income, @Param("isPassed")int isPassed);

//    /**
//     * 绑定用户与专家
//     * @param userId
//     * @param expertId
//     * @return
//     */
//    int bindUser(@Param("userId")int userId,@Param("expertId")int expertId);

    /**
     * 根据userId返回，专家全部个人信息
     * @param expertId
     * @return
     */
    Map selectExpertPersonalInf(@Param("expertId")int expertId);

    /**
     * 查看该专家审核状态
     * @param expertId
     * @return
     */
    int selectExpertIsPassed(@Param("expertId")int expertId);

    /**
     * 返回专家基本信息用于显示在专家门户
     * @param expertId
     * @return
     */
    Map selectExpertBasic(@Param("expertId")int expertId);
    /**
     * todo
     * 查看所有申请中的专家 isPassed=0
     * @return
     */
    List<Map> selectApplyingExpert();
    /**
     * 删除专家
     * @param expertId 只能根据id进行删除
     * @return 是否成功
     */
    int deleteExpert(@Param("expertId")int expertId);

    /**
     * 搜索专家，根据专家姓名进行模糊查询
     * @param expertName 专家姓名
     * @return
     */
    List<Map> selectExpertByName(@Param("expertName")String expertName);

    /**
     * 通过ID，查询专家的详细信息，
     * @param expertId 专家ID
     * @return Map key为列名
     */
    Map selectExpertDetial(@Param("expertId")int expertId);

    Map selectExpertIdByInf(Map expertInfMap);

//    /**
//     *  放在论文表中
//     * 查询该专家的所有参与论文，专家论文关系表链接论文表
//     * @param expertId 专家ID
//     * @return Map{"title":stirng,"author":string,"puhlishedDate":string,"downloadCount":int, paperId":int}
//     *      返回论文的基本信息（题目，作者，发表日期，下载次数）
//     *      paperId通过该ID链接到论文主页
//     */
//    List<Map> selectPaperByEId(@Param("expertId")int expertId);


//    /**
//     *  放在论文表中
//     * 返回可以管理的论文的基本信息，专家表链接论文表
//     * @param expertId 专家Id
//     * @return Map{"title":stirng,"author":string,"puhlishedDate":string,"downloadCount":int, paperId":int}
//     *      返回论文的基本信息（题目，作者，发表日期，下载次数）
//     *      paperId通过该ID链接到论文主页
//     */
//    List<Map> selectOwnPaperByEId(@Param("expertId")int expertId);

//    /**
//     * todo 放在专利表中
//     * 查询该专家的所有专利
//     * @param expertId 专家ID
//     * @return Map{"patentId","patentName"}
//     *      返回专利的基本信息（专利号，专利名，作者，），
//     *      作者ID，通过ID链接到作者主页
//     */
//    List<Map> selectPatentByEId(@Param("expertId")int expertId);
//
//    /**
//     * todo 放在专家关系表中
//     * 查询与该专家具有合作关系的专家姓名，专家关系表链接专家表，需要返回合作者的姓名
//     * @param expertId 专家ID
//     * @return Map{"expertId":int,"name":string,"count":int}
//     *      expertId，合作专家的ID用于查看该专家的详细信息，
//     *      name，合作专家的姓名，用于显示
//     *      count，合作次数，用于显示
//     */
//    List<Map> selectRealtionByEId(@Param("expertId")int expertId);
}
