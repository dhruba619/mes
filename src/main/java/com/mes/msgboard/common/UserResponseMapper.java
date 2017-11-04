package com.mes.msgboard.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mes.msgboard.entity.User;
import com.mes.msgboard.json.UserData;
import com.mes.msgboard.json.UserResponse;

public class UserResponseMapper implements Function<List<User>, UserResponse> {

	@Override
	public UserResponse apply(List<User> t) {
		UserResponse response = new UserResponse();
		response.setUsers(t.stream().map(this::convert).collect(Collectors.toList()));
		return response;
	}

	private UserData convert(User u) {
		return new UserData(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getEmail(), u.isActive(),
				u.getImageUrl(), u.getRole().getId());
	}
}
