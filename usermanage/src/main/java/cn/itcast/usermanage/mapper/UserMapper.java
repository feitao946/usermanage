package cn.itcast.usermanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.usermanage.pojo.User;

public interface UserMapper {
	
	public User queryUserById(Long id);

	public List<User> queryUsersByPage(@Param("start")Integer start, @Param("size")Integer size);

	public List<User> queryUsersAll();

	public int addUser(User user) throws Exception;

	public int deleteUserByIds(@Param("ids")Long[] ids);
}
