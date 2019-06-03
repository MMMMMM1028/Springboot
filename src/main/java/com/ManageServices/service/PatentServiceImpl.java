package com.ManageServices.service;


import com.ManageServices.dao.ExpertMapper;
import com.ManageServices.dao.PatentMapper;
import com.ManageServices.service_interface.PatentService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = PatentService.class)
@Component
public class PatentServiceImpl implements PatentService{
    @Autowired
    PatentMapper pm;
    @Autowired
    ExpertMapper em;

    @Override
    @Transactional
    public int transferPatnet(int toExpertId, String patentId) {
        int result = pm.updatePatent(patentId,toExpertId);
        return result;
    }

    @Override
    public void insertPatent(List<Map> patentList) {
        for (Map patent : patentList){
            Map expert = (Map)patent.get("author");
            patent.remove("author");
            em.insertExpertByMap(expert);
            int expertId = (int)expert.get("expertId");
            patent.put("expertId",expertId);
            pm.insertPatent(patent);
        }
    }

    @Override
    public int uploadPatent(Map patent) {
        return pm.insertPatent(patent);
    }

    //    @Override
//    @Transactional(readOnly = true)
//    public List<Map> selectPatentByName(String patentName) {
//        List<Map> patentList = pm.selectPatent(null,-1,patentName);
//        return patentList;
//    }
}
