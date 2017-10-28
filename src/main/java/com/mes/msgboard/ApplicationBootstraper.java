package com.mes.msgboard;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mes.msgboard.entity.User;
import com.mes.msgboard.service.UserService;

@Component
public class ApplicationBootstraper implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationBootstraper.class);

	@Autowired
	UserService userService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = new User(1, "John Doe", "john@doe.com", "1234", "johnd", true,
				Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()), "some_url", "ADMIN");
		userService.saveUser(user);
		
		User user2 = new User(2, "Jackson Doe", "jacksond@doe.com", "1234", "jacksond", true,
				Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()), "some_url", "USER");
		userService.saveUser(user2);
		
	}

}
