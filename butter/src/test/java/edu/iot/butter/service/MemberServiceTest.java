package edu.iot.butter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.iot.butter.model.Avata;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"classpath:config/database-context.xml"
})
@WebAppConfiguration
public class MemberServiceTest {
	@Autowired
	MemberService service;
	
	@Test
	public void testDaoDI() {
		assert service != null : "service is null";
	}
	
	@Test 
	public void testAvata() throws Exception {
		byte[] image = service.getAvata("admins");
		
		assert image != null : "service is null";
	}
}
