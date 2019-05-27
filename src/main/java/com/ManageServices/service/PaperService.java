package com.ManageServices.service;

import java.util.Map;

public interface PaperService {
    /**
     * 添加论文
     * @param title
     * @param summary
     * @param keyword
     * @param author
     * @param filePath 全文链接（ownerId为-1）或下载链接（ownerId!=-1）
     * @param publishDate
     * @param ownerId
     */
    int uploadPaper(String title,String summary, String keyword, String author, String filePath,
                     String publishDate,int ownerId);

    /**
     * 显示论文表主页的所有内容
     * @param paperId
     * @return Map
     * filepath为全文链接或下载链接，根据ownerId判断，是下载链接则需要隐藏，全文链接显示
     */
    Map selectPaperByPid(int paperId);

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

    /**
     * todo 关联作者
     * @param paperId
     * @param expertId
     */
    void relateAuthor(int paperId, int expertId);
}
