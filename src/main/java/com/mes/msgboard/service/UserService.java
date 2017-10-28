package com.mes.msgboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mes.msgboard.entity.User;
import com.mes.msgboard.repository.IUserRepository;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	public User getUserFromToken(String token) {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findOneByUsername(username);
		return user;
	}

	public void saveUser(User user) {
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}
}
