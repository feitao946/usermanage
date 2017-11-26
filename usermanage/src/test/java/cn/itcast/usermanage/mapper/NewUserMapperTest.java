package cn.itcast.usermanage.mapper;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

import cn.itcast.usermanage.pojo.User;

public class NewUserMapperTest {
	
	private NewUserMapper userMapper;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml","spring/applicationContext-mybatis.xml");
		this.userMapper = context.getBean(NewUserMapper.class);
	}

	@Test
	public void testSelectOne() {
		User record = new User();
		record.setUserName("zhangsan");
		System.out.println(this.userMapper.selectOne(record));
	}

	@Test
	public void testSelect() {
		User record = new User();
		record.setAge(30);
		record.setPassword("123456");
		List<User> list = this.userMapper.select(record);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testSelectCount() {
		System.out.println(this.userMapper.selectCount(null));
	}

	@Test
	public void testSelectByPrimaryKey() {
		System.out.println(this.userMapper.selectByPrimaryKey(1l));
	}

	@Test
	public void testInsert() {
		User record = new User();
		record.setName("春姐");
		record.setUserName("springGirl");
		record.setPassword("111111");
		this.userMapper.insert(record);
		System.out.println(record.getId());
	}

	@Test
	public void testInsertSelective() {
		User record = new User();
		record.setName("春哥2");
		record.setUserName("spring2");
		record.setPassword("111111");
		this.userMapper.insertSelective(record);
	}

	@Test
	public void testDelete() {
		User record = new User();
		record.setUserName("spring2");
		this.userMapper.delete(record);
	}

	@Test
	public void testDeleteByPrimaryKey() {
		this.userMapper.deleteByPrimaryKey(15l);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		User record = new User();
		record.setName("春哥2");
		record.setUserName("spring2");
		record.setPassword("111111");
		record.setId(18l);
		this.userMapper.updateByPrimaryKey(record);
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		User record = new User();
		record.setName("春哥2");
		record.setUserName("spring2");
		record.setPassword("111111");
		record.setId(18l);
		this.userMapper.updateByPrimaryKeySelective(record);
	}

	@Test
	public void testSelectCountByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByExample() {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		criteria.andBetween("age", 20, 30);
		criteria.andLike("userName", "%zhang%");
		Criteria criteria2 = example.createCriteria();
		List<Object> ids = new ArrayList<>();
		ids.add(1l);
		ids.add(2l);
		ids.add(3l);
		ids.add(4l);
		ids.add(5l);
		ids.add(6l);
		criteria2.andIn("id", ids);
		example.or(criteria2);
		example.setOrderByClause("age desc, id asc");
		List<User> list = this.userMapper.selectByExample(example);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testUpdateByExampleSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByExample() {
		fail("Not yet implemented");
	}

}
