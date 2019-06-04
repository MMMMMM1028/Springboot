package com.ManageServices.service;

import com.ManageServices.dao.ExpertMapper;
import com.ManageServices.dao.UserMapper;
import com.ManageServices.service_interface.ExpertService;
import com.ManageServices.service_interface.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper um;
    @Autowired
    ExpertMapper em;
    @Autowired
    ExpertService es;
    @Transactional(readOnly = true)
    public Map login(String userName,String pwd){
        Map m = um.selectUserByUname(userName,pwd);
        if (m == null){//登陆失败
            return null;
        }
        if (m.get("expertId") != null){//申请认证过专家，检查其是否通过认证
            int expertId = (int) m.get("expertId");
            int isPassed = em.selectExpertIsPassed(expertId);
            if (isPassed != 1){
                m.put("expertId",null);
            }
        }
        return m;
    }

    @Override
    @Transactional(readOnly = true)
    public Map selectUserPersonalInf(int userId){
        Map user = um.selectUserDetial(userId);
        return user;

    }

    @Transactional
    public int insertUser(String userName,String pwd,String nickname,String mail){
        try {
            um.insertUser(userName,pwd,nickname,mail);
        }catch (Exception e){//主键重复
            return -1;
        }
        return 1;

    }
    @Transactional
    public int resetPwd(int userId, String pwd){
        return um.updateUser(userId,null,pwd,null,null,null,0);
    }

//    @Transactional
//    public int changeIcon(int userId, String iconPath){
//        return um.updateUser(userId,null,null,null,null,iconPath,0);
//    }

    @Transactional
    public int changeNickname(int userId, String nickname){
        return um.updateUser(userId,null,null,nickname,null,null,0);
    }

    @Transactional
    public int updateBalance(int userId, int increment){
        return um.updateUser(userId,null,null,null,null,null,increment);
    }

    @Transactional
    @Override
    public int beExpert(int userId, String field, String organization, String name) {
        Map expert = new HashMap();
        expert.put("field",field);
        expert.put("name",name);
        expert.put("organization",organization);
        Map e = em.selectExpertIdByInf(expert);
        if(e == null){// 成为新的专家，即栈内没有该专家的门户
            em.insertExpertByMap(expert);
            int expertId = (int) expert.get("expertId");
            um.bindExpert(userId,expertId);
            //设置isPassed为0代表审核中
            //-1未被认领
            //1通过
            return em.updateExpert(expertId,field,organization,name,-1, 0);
        }else{//已有专家门户
            int expertId = (int) e.get("expertId");
            int isPassed = (int) e.get("isPassed");
            if (isPassed != -1){//该专家正在审核或已经被认领
                return -1;
            }else{
                //完善专家信息
                um.bindExpert(userId,expertId);
                return em.updateExpert(expertId,field,organization,name,-1, 0);
            }
        }
    }

    @Override
    @Transactional
    public int updateMail(int userId, String mail) {
        return um.updateUser(userId, null,null,null,
                mail,null,-1);
    }

}
