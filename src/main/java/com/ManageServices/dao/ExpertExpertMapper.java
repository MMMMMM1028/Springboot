package com.ManageServices.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertExpertMapper {
    /**
     * 插入专家关系，专家在注册后，可以认领自己的论文，认领后添加专家关系和专家论文关系
     * @param expertId1 当前专家1ID
     * @param expertId2 专家2ID
     * @return
     */
    int insertExpertExpert(@Param("expertId1")int expertId1, @Param("expertId2")int expertId2);

    /**
     * 在插入钱调用，检查该关系是否存在，如果存在则增加合作次数，否则插入
     * @param expertId1
     * @param expertId2
     * @return null代表不存在，“1”string代表存在
     */
    String isExisted(@Param("expertId1")int expertId1, @Param("expertId2")int expertId2);
    /**
     * 只能修改合作次数,合作次数++
     * @param expertId1 专家1
     * @param expertId2 专家2 作为联合主键
     * @return 是否成功
     */
    int updateCount(@Param("expertId1")int expertId1, @Param("expertId2")int expertId2);

    /**
     * 专家注销时，删除指定专家的所有合作关系
     * @param expertId 专家ID
     * @return
     */
    int deleteExpertExpert(@Param("expertId")int expertId);

    /**
     * 查询专家关系，需要联合专家表返回姓名
     * @param expertId 指定专家的id
     * @return 返回与其有合作关系的所有专家姓名
     */
    List<Map> selectExpertExpert(@Param("expertId")int expertId);


}
