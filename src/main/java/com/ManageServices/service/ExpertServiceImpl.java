package com.ManageServices.service;

import com.ManageServices.dao.ExpertExpertMapper;
import com.ManageServices.dao.ExpertMapper;
import com.ManageServices.dao.PaperMapper;
import com.ManageServices.dao.PatentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ExpertServiceImpl implements ExpertService{
    @Autowired
    ExpertMapper em;
    @Autowired
    PaperMapper pm;
    @Autowired
    ExpertExpertMapper eem;
    @Autowired
    PatentMapper patentm;


    @Override
    public int insertExpert(String field, String organization, String name, String mail, String tel) {
        return em.insertExpert(field,organization,name,tel,mail);
    }

    @Override
    public List selectPartnership(int expertId) {
        List<Map> result = eem.selectExpertExpert(expertId);
        return result;
    }

    @Override
    public List<Map> selectApplyingExpert() {
        List<Map> result = em.selectApplyingExpert();
        return result;
    }

    @Override
    public List<Map> selectOwnPaperByEid(int expertId) {
        List<Map> result = pm.selectOwnPaperByEid(expertId);
        return result;
    }

    @Override
    public List<Map> selectPaperByEid(int expertId) {
        List<Map> result = pm.selectPaperByEid(expertId);
        return result;
    }

    @Override
    public Map selectExpertByEid(int expertId) {
        Map result = em.selectExpertDetial(expertId);
        return result;
    }

    @Override
    public Map selectExpertByUid(int userId) {
        Map expert = em.selectByUid(userId);
        int expertId = (int)expert.get("expertId");
        List<Map> ownPaperList = selectOwnPaperByEid(expertId);
        List<Map> paperList = selectPaperByEid(expertId);
        List<Map> patentList = selectPatentByEid(expertId);
        expert.put("ownPaperList",ownPaperList);
        expert.put("paperList",paperList);
        expert.put("patentList",patentList);
        return expert;
    }

    @Override
    public int applyAuthorize(int userId, int expertId) {
        Map expert = em.selectExpertDetial(expertId);
        int isPassed = (int)expert.get("isPassed");
        if(isPassed != -1){
            return -1;
        }
        return em.updateExpert(expertId,null,null,null,null,null,-1,0,userId);
    }

    @Override
    public int authorizeExpert(int expertId) {
        return em.updateExpert(expertId,null,null,null,null,null,-1,1,-1);
    }

    @Override
    public int updatePartnership(int expertId1, int expertId2) {
        String isExisted = eem.isExisted(expertId1,expertId2);
        if(isExisted == null){//专家关系不存在，则插入
            return eem.insertExpertExpert(expertId1,expertId2);
        }else{
            return eem.updateCount(expertId1,expertId2);
        }
    }

    @Override
    public List<Map> selectPatentByEid(int expertId) {
        List<Map> patentList = patentm.selectPatent(null,expertId,null);
        return patentList;
    }

    @Override
    public List<Map> selectExpertByName(String name) {
        List<Map> expertList = em.selectExpertByName(name);
        return expertList;
    }
}
