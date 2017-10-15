package com.mes.msgboard.service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.mes.msgboard.entity.User;

@Service
public class UserService {
	
	public User getUserFromToken(String token) {
		//TODO Auth and user feature is yet to be implmented
		return new User(1, "John Doe", "john@doe.com", "1234", "johnd", true,
				Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()), "some_url", 1);
	}
}
