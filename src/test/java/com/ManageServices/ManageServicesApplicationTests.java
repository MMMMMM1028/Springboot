package com.ManageServices;

import com.ManageServices.dao.*;
import com.ManageServices.service_interface.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
		Map reuslt = pm.selectPaperDetial(18);
		Map result2 = ps.selectPaperHomeByPid(18);

	}

	@Test
	public void testUserService(){
	    String uname = "testuser1";
	    String pwd = "123456";
	    int insert = us.insertUser("testuser1","123456","user1",null);
	    Map login = us.login(uname,pwd);
	    int userId = (int) login.get("userId");
	    Map user = us.selectUserPersonalInf(userId);
	    String field = "系统工程 地图制图学与地理信息工程";
	    String name = "仰燕兰";
	    String org = "东南大学自动化学院";
	    int result = us.beExpert(userId,field,org,name,null,null);
	    System.out.println(insert);
//        SqlSessionFactoryBuilder
	}

	@Test
    public void testExpertService(){
        String uname = "testuser1";
        String pwd = "123456";
        Map login = us.login(uname,pwd);
        int userId = (int) login.get("userId");
        int expertId = (int) login.get("expertId");
        Map expertInf = es.selectExpertPersonalInf(expertId);
        Map expertHome = es.selectExpertHomeByEid(expertId);
//        pm.selectPaperByEid(1);
    }


    @Test
    public void testPaperService(){
	    us.updateBalance(1,10);
        ps.changePrice(18,5);
        os.purchasePaper(1,18);
        ps.download(18,1);
    }

    @Test
    public void testPatetnService(){
	    patentService.selectPatentByName("patent");
	    patentService.transferPatnet(2,"patentId1");
    }

    @Test
    public void testOrderService(){
//        int resutl = os.purchasePaper(15,1);
        List orderlist = os.selectOrderList(1,null,null);
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
	@Test
	public void testTransaction(){
//		os.testTransaction();
	}

}
