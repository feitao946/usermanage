package cn.itcast.usermanage.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.usermanage.pojo.User;

public class UserServiceTest {
	
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml","spring/applicationContext-mybatis.xml","spring/applicationContext-transaction.xml");
		this.userService = context.getBean(UserService.class);
	}

	@Test
	public void testAddUsers() {
		User user1 = new User();
		user1.setUserName("xiaohei");
		user1.setName("小黑");
		user1.setPassword("123456");
		User user2 = new User();
		user2.setUserName("xiaohei2");
		user2.setName("小黑2");
		user2.setPassword("123456");
		this.userService.addUsers(user1 , user2);
	}

}
