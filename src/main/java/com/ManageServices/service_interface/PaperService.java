package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;

public interface PaperService {

//    /**
//     * 批量添加论文
//     * Map {"paperId":int
//     *      "title":string
//     *      "summary":string
//     *      "keyword":String
//     *      "author":string
//     *      }
//     * @param list
//     * @return
//     */
//    int insertPaperByBatch(List<Map> list);

    /**
     * 爬虫插入 论文，论文作者，作者之间关系，作者论文关系
     * @param List
     * @return 插入的论文Id
     */
    List insert(List<Map> List);
//
//    /**
//     * 批量添加专家论文
//     * Map {"expertId":int
//     *      "paperId":string
//     *      }
//     * @param list
//     * @return
//     */
//    int insertExpertPaperByBatch(List<Map> list);


    /**
     * Map{
     *      "title":string
     *      "summary":string
     *      "keyword":string
     *      "author":[
     *                  {"name","organization","field"}
     *                  {"name","organization","field"}
     *                  ...
     *              ]
     *      "ownerId":int
     *      "filepath":string 爬取的数据该字段为链接，上传的论文该字段为文件路径，通过ownerID是否非空判断类型
     *      }
     * @param paper
     * @return
     */
    int uploadPaper(Map paper);

    /**
     * 显示论文表主页的所有内容
     * @param paperId
     * @return Map
     * filepath为全文链接或下载链接，根据ownerId判断，是下载链接则需要隐藏，全文链接显示
     * 每条paperItem(参与论文列表，拥有论文列表），返回的格式为
     * Map{
     *     "title"
     *     "summary"
     *     "keyword"
     *     "downloadCount"
     *     "authorList":[
     *          {"name","expertId"}
     *          {"name","expertId"}
     *          ...
     *      ]
     * }
     */
    Map selectPaperHomeByPid(int paperId);

    /**
     * 下载论文，
     * @param paperId
     * @param userId
     * @return null未购买不允许下载，filepath允许下载
     */
    String download(int paperId,int userId);

    /**
     * 论文定价
     * @param paperId
     * @param price
     */
    int changePrice(int paperId, int price);


}
