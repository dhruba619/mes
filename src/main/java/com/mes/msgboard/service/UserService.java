package com.mes.msgboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Role;
import com.mes.msgboard.entity.User;
import com.mes.msgboard.json.UserData;
import com.mes.msgboard.repository.IUserRepository;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	public User getUserFromToken() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findOneByUsername(username);
		return user;
	}

	public List<User> saveUser(UserData userdata) throws MESException {
		List<User> users = new ArrayList<>();
		Role role = roleService.getRoleById(userdata.getRole());
		if (role == null) {
			throw new MESException("BAD_REQUEST", "Invalid Role", HttpStatus.BAD_REQUEST, null);
		}
		User user;
		if (userdata.getId() == null || userdata.getId() == 0) {
			user = new User(userdata.getId(), userdata.getName(), userdata.getEmail(), userdata.getPassword(),
					userdata.getUsername(), userdata.isActive(), userdata.getImageUrl(), role);
		} else {
			user = new User(userdata.getName(), userdata.getEmail(), userdata.getPassword(), userdata.getUsername(),
					userdata.isActive(), userdata.getImageUrl(), role);
		}

		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user = userRepository.save(user);

		users.add(user);
		return users;
	}

	public List<User> getAllUser() {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<User> getUser(Integer id) {
		List<User> users = new ArrayList<>();
		users.add(userRepository.findOne(id));
		return users;
	}
}
