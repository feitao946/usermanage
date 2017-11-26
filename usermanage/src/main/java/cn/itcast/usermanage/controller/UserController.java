package cn.itcast.usermanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.usermanage.pojo.EasyUIResult;
import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="page/{pageName}")
	public String toPage(@PathVariable("pageName")String pageName){
		
		return pageName;
	}

	/*@RequestMapping(value="/users")
	public String toUsers(){
		
		return "users";
	}
	
	@RequestMapping(value="page/add")
	public String toUserAdd(){
		
		return "user-add";
	}*/
	
	@RequestMapping(value="list")
	@ResponseBody
	public EasyUIResult queryUsersByPage(@RequestParam("page")Integer pageNum, @RequestParam("rows") Integer pageSize){
		return this.userService.queryUsersByPage(pageNum, pageSize);
	}
	
	@RequestMapping(value="save")
	@ResponseBody
	public Map<String, Object> addUser(User user){
		Map<String, Object> map = new HashMap<>();
		try {
			Boolean b = this.userService.addUser(user);
			if(b){
				map.put("status", 200);
			} else {
				map.put("status", 500);
			}
		} catch (Exception e) {
			map.put("status", 500);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public Map<String, Object> deleteUser(@RequestParam("ids") List<Object> ids){
		Map<String, Object> map = new HashMap<>();
		try {
			Boolean b = this.userService.deleteUserByIds(ids);
			if(b){
				map.put("status", 200);
			} else {
				map.put("status", 500);
			}
		} catch (Exception e) {
			map.put("status", 500);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value="export/excel")
	public String exportExcel(@RequestParam("page")Integer pageNum, @RequestParam("rows") Integer pageSize, Model model){
		EasyUIResult easyUIResult = this.userService.queryUsersByPage(pageNum, pageSize);
		model.addAttribute("userList", easyUIResult.getRows());
		return "userExcelView";
	}
}
