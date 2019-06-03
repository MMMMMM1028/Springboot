package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;


public interface PatentService {
//    /**
//     * 根据关键字，搜索专利
//     * @param patentName
//     * @return
//     */
//    List<Map> selectPatentByName(String patentName);

    /**
     * 插入专利
     * @param patentList
     * 只有一个作者
     * Map{
     *     "patentId"
     *     "patentName"
     *     "summary"
     *     author:Map{
     *         "name"
     *         "field"
     *         "organization"
     *     }
     *     "link"
     * }
     * @return
     */
    void insertPatent(List<Map> patentList);

    /**
     * 专家个人上传专利，须给出国家知识产权局的链接
     * @param patent
     * Map{
     *     "patentId"
     *     "patentName"
     *     "summary"
     *     "expertId"
     *     "link"
     *     }
     * @return
     */
    int uploadPatent(Map patent);


    /**
     * 转让专利
     * @param toExpertId
     * @param patentId
     * @return
     */
    int transferPatnet(int toExpertId, String patentId);

}
