package com.ManageServices.service;

import com.ManageServices.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int insertExpertByBatch(List<Map> list) {
        return em.insertByBatch(list);
    }

    @Override
    public int insertExpertExpertByBatch(List<Map> list) {
        return eem.insertByBatch(list);
    }

    @Override
    @Transactional
    public int insertExpert(String field, String organization, String name, String mail, String tel) {
        return em.insertExpert(field,organization,name,tel,mail);
    }


    private List selectPartnership(int expertId) {
        List<Map> result = eem.selectExpertExpert(expertId);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map> selectApplyingExpert() {
        List<Map> result = em.selectApplyingExpert();
        return result;
    }

    private List<Map> selectOwnPaperByEid(int expertId) {
        List<Map> result = pm.selectOwnPaperByEid(expertId);
        return result;
    }

    private List<Map> selectPaperByEid(int expertId) {
        List<Map> result = pm.selectPaperByEid(expertId);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Map selectExpertByEid(int expertId) {
        Map result = em.selectExpertDetial(expertId);
        List<Map> paperList = selectPaperByEid(expertId);
        List<Map> patentList = selectPatentByEid(expertId);
        List<Map> relationList = selectPartnership(expertId);
        result.put("paperList",paperList);
        result.put("patentList",patentList);
        result.put("relationship",relationList);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Map selectExpertByUid(int userId) {
        Map expert = em.selectByUid(userId);
        if (expert == null){
            return null;
        }else{
            int isPassed = (int) expert.get("isPassed");
            if (isPassed != 1){
                return null;
            }
        }
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
    @Transactional
    public int applyAuthorize(int userId, int expertId) {
        Map expert = em.selectExpertDetial(expertId);
        int isPassed = (int)expert.get("isPassed");
        if(isPassed != -1){
            return -1;
        }
        return em.updateExpert(expertId,null,null,null,null,null,-1,0,userId);
    }

    @Override
    @Transactional
    public int authorizeExpert(int expertId) {
        return em.updateExpert(expertId,null,null,null,null,null,-1,1,-1);
    }

    @Override
    @Transactional
    public int updatePartnership(int expertId1, int expertId2) {
        String isExisted = eem.isExisted(expertId1,expertId2);
        if(isExisted == null){//专家关系不存在，则插入
            return eem.insertExpertExpert(expertId1,expertId2);
        }else{
            return eem.updateCount(expertId1,expertId2);
        }
    }

    @Transactional(readOnly = true)
    private List<Map> selectPatentByEid(int expertId) {
        List<Map> patentList = patentm.selectPatent(null,expertId,null);
        return patentList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map> selectExpertByName(String name) {
        List<Map> expertList = em.selectExpertByName(name);
        return expertList;
    }

}
