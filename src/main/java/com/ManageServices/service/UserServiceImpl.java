package com.ManageServices.service;

import com.ManageServices.dao.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper um;

    public int login(String userName,String pwd){
        Map m = um.selectUserByUname(userName,pwd);
        if(m == null){
            return -1;
        }else{
            return (int)m.get("userId");
        }
    }
    public Map selectUserByUid(int userId){
        Map m = um.selectUserDetial(userId);
        return m;
//        ObjectMapper mapper = new ObjectMapper();
//        String result = mapper.writeValueAsString(m);
    }

    public int insertUser(String userName,String pwd,String nickname,String mail){
        try {
            um.insertUser(userName,pwd,nickname,mail);
        }catch (Exception e){//主键重复
            return -1;
        }
        return 1;

    }
    public int resetPwd(int userId, String pwd){
        return um.updateUser(userId,null,pwd,null,null,null,-1);
    }

    public int changeIcon(int userId, String iconPath){
        return um.updateUser(userId,null,null,null,null,iconPath,-1);
    }

    public int changeNickname(int userId, String nickname){
        return um.updateUser(userId,null,null,nickname,null,null,-1);
    }

    public int updateBalance(int userId, int increment){
        return um.updateUser(userId,null,null,null,null,null,increment);
    }
}
