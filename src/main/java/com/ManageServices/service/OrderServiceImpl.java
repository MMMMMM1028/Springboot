package com.ManageServices.service;

import com.ManageServices.dao.OrderMapper;
import com.ManageServices.dao.PaperMapper;
import com.ManageServices.dao.UserMapper;
import com.ManageServices.service_interface.OrderService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Service(interfaceClass = OrderService.class)
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper om;
    @Autowired
    PaperMapper pm;
    @Autowired
    UserMapper um;

    @Override
    @Transactional
    public int purchasePaper(int userId, int paperId) {
        Map paper = pm.selectPaperDetial(paperId);
        Map user = um.selectUserDetial(userId);
        int price = (int)paper.get("price");
        int balance = (int)user.get("balance");
        if(price > balance){
            return -1;
        }else{
            um.updateUser(userId,null,null,null,null,
                    null,-price);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(new Date());
            om.insertOrder(userId,paperId,date,price);
        }
        return 1;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Map> selectOrderList(int userId, String fromDate, String toDate) {
        List<Map> orderList = om.selectOrder(userId,-1,fromDate,toDate);
        return null;
    }
}
