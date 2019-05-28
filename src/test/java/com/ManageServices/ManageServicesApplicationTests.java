package com.ManageServices;

import com.ManageServices.dao.*;
import com.ManageServices.service_interface.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.design.dao.daoservices.dao")
public class ManageServicesApplicationTests {
	@Autowired
    SqlSession s;
    @Autowired
    private UserMapper um;
	@Autowired
    private PaperMapper pm;
	@Autowired
    private ExpertMapper em;
	@Autowired
	private PatentMapper patm;
	@Autowired
	private OrderMapper om;
	@Autowired
	private ExpertExpertMapper eem;
    @Autowired
    private ExpertPaperMapper epm;
    @Autowired
    private ExpertService es;
    @Autowired
	private UserService us;
    @Autowired
    private PaperService ps;
    @Autowired
    private OrderService os;
    @Autowired
    private PatentService patentService;
	@Test
	public void contextLoads() {
	}

    @Test
    public void testUserMapper(){
//		int insert = um.insertUser("testuser1","123","testuser",null);
//		int result = um.updateUser(uid,"testupdate",null,null,null,null,0);
		Map login = um.selectUserByUname("testuser1","123");
//		Map loginerror = um.selectUserByUname("testuser1","456");
//		Map m = um.selectUserDetial((int)login.get("userId"));
//		int dele = um.deleteUser((int)login.get("userId"));
        try {
            int insert = um.insertUser("testuser1","123","testuser",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

	@Test
	public void testExpertMapper(){
//		int i = em.insertExpert(3,"计算机","北航","张三",null,null);
		Map m = em.selectExpertDetial(3);
		List<Map> l = em.selectExpertByName("张");
	}

	@Test
    public void testPaperMapper(){
//		int i =pm.insertPaper("testPaper1","summary","keyword","author1",
//				"filepathtest1","2019-1-1",1);
//		List l = pm.selectPaperByEid(1);
//		List l2 = pm.selectOwnPaperByEid(1);
//		Map M =  pm.selectPaperDetial(1);
    }

    @Test
	public void test(){
		um.insertId(6);
	}
	@Test
	public void testPatentMapper(){
//		int result = patm.insertPatent("patentId1","patentName1",1);
//		List l = patm.selectPatent("patentId1",-1,null);
//		List l = patm.selectPatent(null,1,null);
		List l = patm.selectPatent(null,-1,"pat");
//		int result = patm.deletePatent("1",-1);
//		result = patm.deletePatent(null,1);

	}

	@Test
	public void testOrderMapper(){
		om.insertOrder(1,1,"2019-1-1",25);
		om.selectOrder(1,1,null,null);
		om.selectOrder(1,-1,"2019-1-1","2020-1-1");
	}

	@Test
	public void testExpertExpertMapper(){
//		String resultl =eem.isExisted(1,2);
//		eem.insertExpertExpert(1,3);
		List l = eem.selectExpertExpert(1);
		eem.updateCount(1,2);
		eem.deleteExpertExpert(1);
	}
    @Test
	public void testExpertPaperMapper(){

	}

	@Test
	public void testUserService(){

	}

	@Test
    public void testExpertService(){

    }

    @Test
    public void testPaperService(){
        Map paper = new HashMap();
        paper.put("title","title");
        Map author = new HashMap();
        author.put("name","张三");
        Map author2 = new HashMap();
        author2.put("name","李四");
        author.put("field","");
        author.put("organization","");
        author2.put("field","");
        author2.put("organization","");
        List<Map> authorList = new ArrayList<Map>();
        authorList.add(author);
        authorList.add(author2);
        paper.put("author",authorList);
        List<Map> paperList = new ArrayList<Map>();
        paperList.add(paper);
        ps.insert(paperList);
//        em.selectExpertIdByInf(author);
//        pm.insertPaperByMap(paper);
//        eem.updateCount(121,122);

    }

    @Test
    public void testPatetnService(){
	    patentService.selectPatentByName("patent");
	    patentService.transferPatnet(2,"patentId1");
    }

    @Test
    public void testOrderService(){
        int resutl = os.purchasePaper(15,1);
        os.selectOrderList(15,null,null);
    }

    @Test
	public void testBatch(){
        Map expert = new HashMap();
        expert.put("expertId",109);
        expert.put("field","field");
//		Map expert2 = new HashMap();
//		expert2.put("expertId",107);
//		expert2.put("field","field");
        List<Map> list = new ArrayList<Map>();
        list.add(expert);
//        list.add(expert2);
//        em.insertByBatch(list);
	}
}
