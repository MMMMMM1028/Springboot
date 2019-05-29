package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 购买论文
     * @param userId
     * @param paperId
     * @return
     */
    int purchasePaper(int userId, int paperId);

    /**
     * 查询订单记录
     * @param userId
     * @param fromDate
     * @param toDate
     * @return
     */
    List<Map> selectOrderList(int userId, String fromDate, String toDate);

}
