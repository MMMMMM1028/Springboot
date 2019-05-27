package com.ManageServices.dao;

import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

public interface PaperMapper {
    /**
     * 插入时只有以下必要信息，其他信息只能修改
     * @param paperId 自增主键
     * @param title 论文题目
     * @param summary 论文摘要
     * @param keyword 关键字
     * @param author 作者，用逗号分割
     * @param filePath 论文路径（全文链接，或论文下载路径）
     * @param publishedDate 发表日期
     * @param ownerId 论文的所有者ID
     * @return 是否成功
     */
    int insertPaper2(@Param("title")String title,
                    @Param("summary")String summary, @Param("keyword")String keyword,
                    @Param("author")String author, @Param("filePath")String filePath,
                    @Param("publishedDate")String publishedDate, @Param("ownerId")int ownerId);

    int insertPaper(Map paper);
    /**
     * 只允许更改以下信息
     * @param paperId id作为条件
     * @param price 修改价格,-1代表不修改
     * @param downloadCount 修改下载次数,1代表修改，-1代表不修改，自动++
     * @return 是否成功
     */
    int updatePaper(@Param("paperId")int paperId, @Param("price")int price, @Param("downloadCount")int downloadCount);

    /**
     * 删除论文
     * @param paperId 指定论文id
     * @param expertId 删除专家的所有论文
     * @return 是否成功
     */
    int deletePaper(@Param("paperId")int paperId,@Param("expertId")int expertId);

    /**
     * 模糊查询
     * @param fromDate 起始时间
     * @param toDate 终止时间显示该时间段内的论文
     * @param title 根据论文题目进行模糊查询
     * @param summary 根据摘要进行模糊查询
     * @param keyword 根据关键字进行模糊查询
     * @param author 根据作者进行模糊查询
     * @return Map{"paperId":int, "title":string, "author":string, "publishedDate":string, "downloadCount":string}
     *      paperId,用于进一步查看论文的详细内容
     *      显示：论文的题目，作者，发表日期，下载次数
     */
    List<Map> selectPaperLike(@Param("fromDate")String fromDate, @Param("toDate")String toDate,
                              @Param("title")String title, @Param("summary")String summary,
                              @Param("keyword")String keyword, @Param("author")String author);

    /**
     * @param expertId 专家ID
     * @return 返回论文的基本信息（题目，作者，发表日期，下载次数），论文的ID，通过该ID链接到论文主页
     */
    List<Map> selectPaperByEid(@Param("expertId")int expertId);

    /**
     *
     * @param expertId
     * @return 返回论文的基本信息（题目，作者，发表日期，下载次数），论文的ID，通过该ID链接到论文主页
     */
    List<Map> selectOwnPaperByEid(@Param("expertId")int expertId);

    /**
     * 查找论文的详细信息
     * @param paperId 论文ID
     * @return Map key为列名
     *      论文的全部详细信息Map
     *      题目，作者，摘要，关键字，
     *      key：authroIdList，value类型为List<Map> 其内容为 Map{"expertId":int,"expertName":string}
     */
    Map selectPaperDetial(@Param("paperId")int paperId);

}
