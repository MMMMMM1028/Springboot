package com.ManageServices.service_interface;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    /**
     * 登陆
     * @param userName
     * @param pwd
     * @return Map{userId，expertId}
     *      若返回null代表登陆失败
     *      若expertId对应的value为null则说明不是专家
     */
    Map login(String userName, String pwd);

    /**
     * 返回用户基本信息，是用户不是专家调用
     * @param uid
     * @return 返回Map
     */
    Map selectUserPersonalInf(int uid);

    /**
     * 创建新用户
     * @param userName
     * @param pwd
     * @param nickname
     * @param mail
     * @return 成功1，失败-1
     */
    int insertUser(String userName,String pwd,String nickname,String mail);

    /**
     * 重置密码
     * @param userId
     * @param pwd
     */
    int resetPwd(int userId, String pwd);
//
//    /**
//     * 更换头像
//     * @param userId
//     * @param iconPath
//     *
//     */
//    int changeIcon(int userId, String iconPath);

    /**
     * 更换昵称
     * @param userId
     * @param nickname
     *
     */
    int changeNickname(int userId, String nickname);


    /**
     * 更改账户余额，
     * @param userId
     * @param increment 整数充值，负数消费
     * @return 1成功，-1失败
     */
    int updateBalance(int userId, int increment);



    /**
     * 更改邮箱
     * @param mail
     * @return
     */
    int updateMail(int userId, String mail);

    /**
     * 申请成为专家，只能通过申请成为专家来认领已经存在的专家主页，且不会有任何提示该专家主页已经存在
     * 显示给用户的只有一种成为专家的方法
     * @param userId
     * @param field
     * @param organization
     * @param name
     * @return
     * -1 代表申请失败，该专家信息已经存在或正在审核
     */
    int beExpert(int userId, String field, String organization, String name);


}
