package com.cadykd.kdgames.security;

import com.cadykd.kdgames.data.AuthGroupRepository;
import com.cadykd.kdgames.models.AuthGroup;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AppUserDetailsService implements UserDetailsService {
	AuthGroupRepository authGroupRepository;
	UserService userService;
	@Autowired
	public AppUserDetailsService(AuthGroupRepository authGroupRepository, UserService userService) {
		this.authGroupRepository = authGroupRepository;
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userService.findUserByEmail(email);
		} catch (NoSuchElementException e) {
			log.warn("Could not find user with email: " + email);
			e.printStackTrace();
		} catch (UsernameNotFoundException e) {
			log.warn("Could not find user with email: " + email);
			e.printStackTrace();
		}
		if (user == null) {
			throw new UsernameNotFoundException("No user with email:" + email);
		}
		user = userService.findUserByEmail(email);
		List<AuthGroup> authGroupList = authGroupRepository.findByaEmail(email);
		return new AppUserPrincipal(user, authGroupList);
	}
}
