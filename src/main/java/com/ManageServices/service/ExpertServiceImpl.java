package com.ManageServices.service;

import com.ManageServices.dao.*;
import com.ManageServices.service_interface.ExpertService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service(interfaceClass = ExpertService.class)
@Component
public class ExpertServiceImpl implements ExpertService{
    @Autowired
    ExpertMapper em;
    @Autowired
    PaperMapper pm;
    @Autowired
    ExpertExpertMapper eem;
    @Autowired
    PatentMapper patentm;
    @Autowired
    ExpertPaperMapper epm;

    @Override
    public int insertExpertByBatch(List<Map> list) {
        return em.insertByBatch(list);
    }

    @Override
    public int insertExpertExpertByBatch(List<Map> list) {
        return eem.insertByBatch(list);
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
    public Map selectExpertHomeByEid(int expertId) {
        Map result = em.selectExpertBasic(expertId);
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
    public Map selectExpertPersonalInf(int expertId) {
        Map expert = em.selectExpertPersonalInf(expertId);
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
    public int authorizeExpert(int expertId) {
        return 0;
    }


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
