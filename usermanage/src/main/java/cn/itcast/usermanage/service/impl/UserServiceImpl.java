package cn.itcast.usermanage.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.usermanage.mapper.NewUserMapper;
import cn.itcast.usermanage.pojo.EasyUIResult;
import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private NewUserMapper userMapper;

	@Override
	public EasyUIResult queryUsersByPage(Integer pageNum, Integer pageSize) {
		EasyUIResult easyUIResult = new EasyUIResult();
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = this.userMapper.select(null);
		PageInfo<User> page = new PageInfo<>(userList);
		easyUIResult.setTotal(page.getTotal());
		easyUIResult.setRows(page.getList());
		return easyUIResult;
	}

	@Override
	public void addUsers(User user1, User user2) {
//		this.userMapper.addUser(user1);
//		int i=1/0;
//		this.userMapper.addUser(user2);
	}

	@Override
	public Boolean addUser(User user) throws Exception {
		int count = this.userMapper.insertSelective(user);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteUserByIds(List<Object> ids) {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", ids);
		int count = this.userMapper.deleteByExample(example);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public User queryUsersById(Long id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean updateUser(User user) {
		return this.userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public Boolean deleletUserById(Long id) {
		return this.userMapper.deleteByPrimaryKey(id)>0;
	}

}
