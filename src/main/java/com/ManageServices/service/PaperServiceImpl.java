package com.ManageServices.service;

import com.ManageServices.dao.ExpertPaperMapper;
import com.ManageServices.dao.OrderMapper;
import com.ManageServices.dao.PaperMapper;
import com.ManageServices.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper pm;
    @Autowired
    ExpertPaperMapper epm;
    @Autowired
    UserMapper um;
    @Autowired
    OrderMapper om;

    @Override
    public int insertPaperByBatch(List<Map> list) {
        return pm.insertByBatch(list);
    }


    @Override
    public int insertExpertPaperByBatch(List<Map> list) {
        return epm.insertByBatch(list);
    }

    @Transactional
    @Override
    public int uploadPaper(String title, String summary, String keyword, String author, String filePath, String publishDate, int ownerId) {
        Map paper = new HashMap();
        paper.put("title",title);
        paper.put("summary",summary);
        paper.put("keyword",keyword);
        paper.put("author",author);
        paper.put("filePath",filePath);
        paper.put("publishDate",publishDate);
        paper.put("ownerId",ownerId);
//        pm.insertPaper(paperId,title,summary,keyword,author,filePath,publishDate,ownerId);
        pm.insertPaper(paper);
        //插入专家论文关系表
        return epm.insertExpertPaper((int)paper.get("paperId"),ownerId);
    }

    @Transactional
    @Override
    public String download(int paperId, int userId) {
        Map paper = pm.selectPaperDetial(paperId);
        int price = (int)paper.get("price");
        if(price != 0){
            List<Map> order = om.selectOrder(userId,paperId,null,null);
            if(order == null){
                return null;
            }
        }
        pm.updatePaper(paperId,-1,1);
        return (String)paper.get("filepath");
    }

    @Transactional(readOnly = true)
    @Override
    public Map selectPaperByPid(int paperId){
        Map paper = pm.selectPaperDetial(paperId);
        return  paper;
    }

    @Transactional
    @Override
    public int changePrice(int paperId, int price) {
        return pm.updatePaper(paperId,price,-1);
    }

    @Transactional
    @Override
    public void relateAuthor(int paperId, int expertId){

    }

}
