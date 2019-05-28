package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;

public interface ExpertService {

    /**
     * 批量添加专家
     * Map {"expertId":int
     *      "field":string
     *      "organization":string
     *      "name":String
     *      }
     * @param list
     * @return
     */
    int insertExpertByBatch(List<Map> list);

    /**
     * 批量添加专家专家
     * Map {"expertId1":int
     *      "expertId2":int
     *      }
     * @param list
     * @return
     */
    int insertExpertExpertByBatch(List<Map> list);
    /**
     * 添加专家
     * @param field
     * @param organization
     * @param name
     * @param mail
     * @param tel
     */
    int insertExpert(String field,String organization,String name, String mail, String tel);

    /**
     * todo 提交的信息
     * 申请认证专家,如果该专家主页已经被认领或正在审核，则需要联系管理员
     * @param userId 提交申请的用户ID
     * @param expertId 被认证的专家ID
     * @return -1申请失败，1申请成功
     */
    int applyAuthorize(int userId, int expertId);

    /**
     * todo 显示的内容
     * 用于管理员审核，显示所有提交的审核信息
     * @return
     */
    List<Map> selectApplyingExpert();

    /**
     * 管理员认证专家，todo 通知专家方法
     * @param expertId
     */
    int authorizeExpert(int expertId);

//    /**
//     * 显示专家所有参与的论文
//     * @param expertId
//     * @return 返回论文的基本信息（题目，作者，发表日期，下载次数），论文的ID，通过该ID链接到论文主页
//     */
//    List<Map> selectPaperByEid(int expertId);

//    /**
//     * 显示专家拥有的论文
//     * @param expertId
//     * @return
//     */
//    List<Map> selectOwnPaperByEid(int expertId);

//    /**
//     * 根据专家ID，显示其所有的专利
//     * @param expertId
//     * @return
//     */
//    List<Map> selectPatentByEid(int expertId);

    /**
     * 专家门户显示的全部内容
     * 根据专家ID，返回专家门户的所有内容
     * @param expertId
     * @return 返回专家门户的所有内容
     */
    Map selectExpertByEid(int expertId);

    /**
     * 显示专家个人管理页面的内容，登陆用户为专家时调用
     * @param expertId
     * @return 基本信息，参与的论文列表，拥有的论文列表，拥有的专利列表
     */
    Map selectExpertInfByEid(int expertId);

//    /**
//     * 返回合作关系
//     * @param expertId
//     * @return
//     */
//    List selectPartnership(int expertId);

//    /**
        //私有
//     * 更新合作关系，如果存在则合作次数++，否则添加合作次数
//     * @param expertId1
//     * @param expertId2
//     */
//    int updatePartnership(int expertId1,int expertId2);

    /**
     * 根据专家的姓名进行查询
     * @param name
     * @return
     */
    List<Map> selectExpertByName(String name);

    /**
     * 专家认领论文 或 论文管理者，为论文关联作者
     * @param expertId 认领：登陆专家的ID， 关联：选择要关联的专家ID
     * @param paperId 认领：选择要认领的论文ID， 关联：选择的要管理的论文ID
     * @return
     */
    int claimAndRelate(int expertId, int paperId);

//    /**
//     * 论文管理者，为论文关联作者
//     * @param paperId
//     * @param expertId
//     * @return
//     */
//    int relateAuthor(int expertId, int paperId);
}
