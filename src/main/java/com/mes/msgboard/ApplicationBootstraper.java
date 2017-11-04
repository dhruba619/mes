package com.mes.msgboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mes.msgboard.entity.Role;
import com.mes.msgboard.json.UserData;
import com.mes.msgboard.service.RoleService;
import com.mes.msgboard.service.UserService;

@Component
public class ApplicationBootstraper implements ApplicationRunner {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Value("${mes.admin.username:admin}")
	private String username;
	
	@Value("${mes.admin.password:admin}")
	private String password;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role role = new Role(1, "ADMIN");
		roleService.createOrUpdateRole(role);

		UserData userData = new UserData(0, "John Doe", "john@gmail.com", password, username, true,
				"http://some.url.com/image.jpg", 1);
		
		userService.saveUser(userData);
	}

}
