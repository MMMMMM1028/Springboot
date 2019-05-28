package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;


public interface PatentService {
    /**
     * 根据关键字，搜索专利
     * @param patentName
     * @return
     */
    List<Map> selectPatentByName(String patentName);

    /**
     * 转让专利
     * @param toExpertId
     * @param patentId
     * @return
     */
    int transferPatnet(int toExpertId, String patentId);

}
