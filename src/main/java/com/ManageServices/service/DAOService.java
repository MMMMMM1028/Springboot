package com.ManageServices.service;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DAOService {
    /**
     * 添加用户
     * @param username 登陆用户名
     * @param password 密码
     * @param nickname 用户昵称
     * @param mail 用户邮箱
     * @return boolean 是否成功
     */
    boolean addUser(String username,String password,String nickname,String mail);

    /**
     * 登陆验证
     * @param username 用户名
     * @param password 密码
     * @return uid 用户ID
     */
    int verifyUser(String username,String password);

    /**
     * 上传论文
     * ——添加论文记录，
     * ——生成专家论文关系记录
     * @param userId 论文拥有者专家Id
     * @param title 论文题目
     * @param author 作者列表，逗号分割
     * @param keyword 论文关键词
     * @param summary 论文摘要
     * @param price 论文价格，可为0
     * @param file 论文的文件路径
     * @param time 发表时间
     * @return pid 论文的ID
     */
    int uploadPaper(int userId, String title, String author, String keyword, String summary, String price, String file, Date time);



    /**
     * 改变论文作者关系
     * @param paperId 出错的论文id
     * @param oldUserId 出错的作者的用户id
     * @param newUserId 正确的作者的用户id
     * @return 是否成功
     */
    boolean updateExpertPaper(int paperId, int oldUserId, int newUserId);

    /**
     * 添加专利
     * @param patentId 专利号
     * @param patentName 专利名称
     * @param expertId 专利所有者Id
     * @return 是否成功
     */
    boolean addPatent(int patentId, String patentName, int expertId);

    /**
     * 修改专利所有者
     * @param patentId 专利号
     * @param newExpertId 新的专利所有者Id
     * @return 是否成功
     */
    boolean updatePatent(int patentId, int newExpertId);

    /**
     * 购买论文，
     * ——添加订单记录
     * ——修改用户余额
     * ——增加专家积分
     * @param userId 购买者ID
     * @param paperId 所购买的论文的ID
     * @return Map{"stat":0|1,"balance":int} 0代表购买失败余额不足，balance代表当前余额
     */
    Map purchasePaper(int userId, int paperId);

    /**
     * 注册专家
     * @param userId 用户id
     * @param field 领域
     * @param name 专家姓名
     * @param organization 专家所在组织
     * @param mail 专家邮箱，可为null
     * @param tel 专家电话，可为null
     * @return 是否成功
     */
    boolean expertRegister(int userId, String field, String name, String organization, String mail, String tel);

    /**
     * 根据作者姓名进行模糊查询，返回站内已有论文
     * @param userName 作者姓名
     * @return List<Map> map保存论文属性，key为列名
     */
    List<Map> getPaperByUname(String userName);

    /**
     * 站内搜索，分为作者，论文题目，关键字和专利.分别调用不同的查询语句
     * @param type "author","paper","patent","keyword"
     * @param keyword 需要匹配的字符串
     * @result List<Map>
     */
    List<Map> search(String type, String keyword);

    /**
     * 根据专家ID获取专家信息
     * @param expertId 专家ID
     * @return Map返回指定专家信息
     */
    Map getExpertById(int expertId);

    /**
     *获得专家关系表
     * @param expertId 专家Id
     * @return List<Map>返回合作关系信息
     */
    List<Map> getExpertRelationship(int expertId);

    /**
     * 根据论文获取该论文和专家的关系（查询paper-expert表）
     * @param paperId 论文id
     * @return 返回完整的专家论文关系表查询结果
     */
    public List<Map> getPaperAuthors(int paperId);

    /**
     * 获取所有订单
     * @param userId 用户id
     * @param from 开始日期
     * @param to 结束日期
     * @return 返回完整的订单表查询结果
     */
    public List<Map> getOrders(int userId, String from, String to);

    /**
     * 获取专家的所有专利信息
     * @param userId 专家用户id
     * @return 返回完整的专利表查询信息
     */
    public List<Map> getPatentListByExpertId(int userId);

    /**
     * 获取论文信息
     *  需要查表是否具有论文的下载权限
     * @param paperId 论文id
     * @return 返回完整的论文表查询结果
     */
    public Map getPaperById(int paperId);
}
