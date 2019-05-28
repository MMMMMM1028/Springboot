package com.ManageServices.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertPaperMapper {
    /**
     * 批量添加
     * @param list
     * @return
     */
    int insertByBatch(List<Map> list);

    /**
     * 插入专家论文关系
     *      专家注册后，可认领论文，通过作者名字选出包含该专家的论文
     *      专家上传论文后，根据作者匹配所有专家，可选择该论文的其他作者，
     * @param paperId 索要认领的论文id
     * @param expertId 当前登陆专家id
     * @return
     */
    int insertExpertPaper(@Param("paperId")int paperId, @Param("expertId")int expertId);

//    /** 不允许出现更改，即论文没有转让
//     * 更新以下属性
//     * @param paperId 根据ID进行选择
//     * @param expertId 因出现重名导致作者关系记录错误，而造成的修改
////     * @param isOwner 更改论文所有者
//     * @return 是否成功
//     */
//    int update(@Param("paperId")int paperId, @Param("expertId")int expertId);

    /**
     * 删除专家论文关系
     * @param paperId 论文id，在论文被下架时删除,-1代表不根据论文
     * @param expertId 作者ID，在作者注销是删除所有论文关系，-1代表不根据expertID
     * @return
     */
    int deleteExpertPaper(@Param("paperId")int paperId, @Param("expertId")int expertId);

    /**
     * 选择改论文所有专家的ID
     * @param paperId
     * @return
     */
    int[] selectAuthorIdByPid(@Param("paperId")int paperId);

}
