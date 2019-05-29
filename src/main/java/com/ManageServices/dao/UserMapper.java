package com.ManageServices.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
@Mapper
public interface UserMapper {
    /**
     * 插入时只会插入以下属性，其他的属性为默认值只能修改
     * @param userName 登陆账号不允许为空
     * @param password 密码不允许为空，默认密码123456
     * @param nickName 昵称，不允许为空
     * @param mail 邮箱，找回时使用
     * @return 插入的行数
     * 主键重复，抛出异常
     */
    int insertUser(@Param("userName")String userName, @Param("password")String password,
                   @Param("nickName")String nickName,@Param("mail")String mail);

//    /**
//     * 检查用户名是否存在
//     * @param userName
//     * @return Map{"userName":string}，不存在返回null
//     */
//    boolean isExisted(@Param("userName")String userName);


    /**
     * 更改
     * @param userId id作为条件
     * @param userName 修改登陆账户
     * @param password 修改密码
     * @param nickName 修改昵称
     * @param mail 修改邮箱
     * @param iconPath 修改头像
     * @param balance 修改积分,-1代表不修改积分
     * @return
     */
    int updateUser(@Param("userId")int userId, @Param("userName")String userName, @Param("password")String password,
               @Param("nickName")String nickName,@Param("mail")String mail,@Param("iconPath")String iconPath,
               @Param("balance")int balance);

    /**
     * 绑定专家，未通过审核，记录申请记录
     * @param expertId
     * @param userId
     * @return
     */
    int bindExpert(@Param("userId")int userId, @Param("expertId")int expertId);

    int insertId(@Param("userId")int userId);
    /**
     * 删除
     * @param userId 只能根据id进行用户删除
     * @return
     */
    int deleteUser(@Param("userId")int userId);

    /**
     * 查询用户详细信息，
     * @param userId 根据id查询
     * @return Map key为列名
     */
    Map selectUserDetial(@Param("userId")int userId);

    /**
     * 登陆验证密码
     * @param userName 账号
     * @param password 密码
     * @return Map 如果账号密码比匹配则返回null，否则返回Map{"userId":int}
     */
    Map selectUserByUname(@Param("userName")String userName, @Param("password")String password);
}
