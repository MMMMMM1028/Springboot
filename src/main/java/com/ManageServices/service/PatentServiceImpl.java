package com.ManageServices.service;


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

    @Override
    @Transactional
    public int transferPatnet(int toExpertId, String patentId) {
        int result = pm.updatePatent(patentId,toExpertId);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map> selectPatentByName(String patentName) {
        List<Map> patentList = pm.selectPatent(null,-1,patentName);
        return patentList;
    }
}
