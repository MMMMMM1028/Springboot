package com.ManageServices.service;

import com.ManageServices.dao.ExpertMapper;
import com.ManageServices.dao.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public int login(String userName,String pwd){
        Map m = um.selectUserByUname(userName,pwd);
        if(m == null){
            return -1;
        }else{
            return (int)m.get("userId");
        }
    }
    @Transactional(readOnly = true)
    public Map selectUserByUid(int userId){
        Map expert = es.selectExpertByUid(userId);
        if(expert != null){//是一名专家
            return expert;
        }
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
        return um.updateUser(userId,null,pwd,null,null,null,-1);
    }

    @Transactional
    public int changeIcon(int userId, String iconPath){
        return um.updateUser(userId,null,null,null,null,iconPath,-1);
    }

    @Transactional
    public int changeNickname(int userId, String nickname){
        return um.updateUser(userId,null,null,nickname,null,null,-1);
    }

    @Transactional
    public int updateBalance(int userId, int increment){
        return um.updateUser(userId,null,null,null,null,null,increment);
    }

    @Transactional
    @Override
    public int beExpert(int userId, String field, String organization, String name, String tel, String mail) {
//        em.insertExpert(field, organization, name, tel, mail);
        return 0;
    }

    @Transactional
    @Override
    public int authorizeExpert(int userId, String field, String organization, String name, String tel, String mail) {
        return 0;
    }
}
