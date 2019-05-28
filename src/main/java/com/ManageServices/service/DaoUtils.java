package com.ManageServices.service;

import com.ManageServices.dao.ExpertExpertMapper;
import com.ManageServices.dao.ExpertMapper;
import com.ManageServices.dao.ExpertPaperMapper;
import com.ManageServices.dao.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoUtils {
    @Autowired
    ExpertMapper em;
    @Autowired
    ExpertPaperMapper epm;
    @Autowired
    ExpertExpertMapper eem;
    @Autowired
    PaperMapper pm;

    public int updateExpertExpertShip(int expertId1, int expertId2){
        String isExisted = eem.isExisted(expertId1,expertId2);
        if(isExisted == null){//专家关系不存在，则插入
            return eem.insertExpertExpert(expertId1,expertId2);
        }else{
            return eem.updateCount(expertId1,expertId2);
        }
    }

}
