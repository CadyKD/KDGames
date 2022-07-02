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
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		List<AuthGroup> authGroupList = authGroupRepository.findByUserName(userName);
		User u = userService.findUserByName(userName);
		
		return new AppUserPrincipal(u,authGroupList);
	}
}
