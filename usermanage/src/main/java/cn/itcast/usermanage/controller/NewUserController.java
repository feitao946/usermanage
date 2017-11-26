package cn.itcast.usermanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.UserService;

@RequestMapping("rest/user")
@Controller
public class NewUserController {
	
	@Autowired
	private UserService userService;
	
	/*@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody
	public User queryUserById(@PathVariable("id") Long id){
		return this.userService.queryUsersById(id);
	}*/
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<User> queryUserById(@PathVariable("id") Long id){
		try {
			if(id == null || id.intValue()<0 ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			// int i=1/0;
			User user = this.userService.queryUsersById(id);
			if(user == null){
				// 响应404
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			// 响应200
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 响应500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(User user){
		try {
			if(user == null | user.getId() == null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			Boolean b = this.userService.updateUser(user);
			if(b){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> addUser(User user){
		try {
			if(user == null | StringUtils.isEmpty(user.getUserName())){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			Boolean b = this.userService.addUser(user);
			if(b){
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUserById(@RequestParam("ids") List<Object> ids){
		try {
			if(ids == null || ids.size() == 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			Boolean b = this.userService.deleteUserByIds(ids);
			if(b){
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
