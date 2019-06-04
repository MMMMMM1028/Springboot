package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;

public interface ExpertService {

//    /**
//     * 批量添加专家
//     * Map {"expertId":int
//     *      "field":string
//     *      "organization":string
//     *      "name":String
//     *      }
//     * @param list
//     * @return
//     */
//    int insertExpertByBatch(List<Map> list);
//
//    /**
//     * 批量添加专家专家
//     * Map {"expertId1":int
//     *      "expertId2":int
//     *      }
//     * @param list
//     * @return
//     */
//    int insertExpertExpertByBatch(List<Map> list);


    /**
     * todo 显示的内容
     * 用于管理员审核，显示所有提交的审核信息
     * @return
     * List<Map>
     *     Map{
     *          "expertId"
     *          "name"
     *          "userId"
     *          "nickname"
     *     }
     */
    List<Map> selectApplyingExpert();

    /**
     * 管理员认证专家，todo 没有界面，通知专家方法
     * @param expertId
     */
    int authorizeExpert(int expertId);


    /**
     * 专家门户显示的全部内容
     * 根据专家ID，返回专家门户的所有内容
     * @param expertId
     * @return Map{
     *      "expertId"
     *      "field"
     *      "organization"
     *      "name"
     *      "paperList":[paperBasic...]
     *      "patentList":[patentBasic...]
     *      "relationship":[Map{"expertId1","expertId2","count"}...]
     * }
     */
    Map selectExpertHomeByEid(int expertId);

    /**
     * 显示专家个人管理页面的内容，登陆用户为专家时调用
     * @param expertId
     * @return 基本信息，参与的论文列表，拥有的论文列表，拥有的专利列表
     *Map{
     *      "expertId"
     *      "field"
     *      "organization"
     *      "name"
     *      "paperList":[paperBasic...]
     *      "ownPaperList":[paperBasic...]
     *      "patentList":[patentBasic...]
     *      "relationship":[Map{"expertId1","expertId2","count"}...]
     * }
     */
    Map selectExpertPersonalInf(int expertId);

}
