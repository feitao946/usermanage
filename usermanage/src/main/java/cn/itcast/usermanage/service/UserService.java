package cn.itcast.usermanage.service;

import java.util.List;

import cn.itcast.usermanage.pojo.EasyUIResult;
import cn.itcast.usermanage.pojo.User;

public interface UserService {

	public EasyUIResult queryUsersByPage(Integer pageNum, Integer pageSize);
	
	public void addUsers(User user1, User user2);

	public Boolean addUser(User user) throws Exception;

	public Boolean deleteUserByIds(List<Object> ids);

	public User queryUsersById(Long id);

	public Boolean updateUser(User user);

	public Boolean deleletUserById(Long id);

}
