package com.ManageServices.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderMapper {
    /**
     * 插入订单，购买后自动插入
     * @param userId 购买者ID
     * @param paperId 论文ID
     * @param orderDate 购买时间
     * @param amount 总计
     * @return 是否成功
     */
    int insertOrder(@Param("userId")int userId, @Param("paperId")int paperId, @Param("orderDate")String orderDate,
               @Param("amount")int amount);
//    int update(@Param("userId")int userId, @Param("paperId")int paperId,);订单类不允许修改
//    int delete();

    /**
     * 查询
     *      查询用户的订单用于显示
     *      查询用户是否购买过该论文，指定userId和paperId
     * @param userId 根据用户ID查询，显示该用户所有订单记录
     * @param paperId 根据论文ID查询，根据用户和论文ID查询 判断是否购买过改论文
     * @param fromDate 限定时间段，起始时间
     * @param toDate 终止时间
     * @return
     *      显示：论文题目，论文作者，购买时间，总价,
     *      paperId：用于查看论文
     */
    List<Map> selectOrder(@Param("userId")int userId, @Param("paperId")int paperId,
                     @Param("fromDate")String fromDate, @Param("toDate")String toDate);

}
