package com.cadykd.kdgames.security;

import com.cadykd.kdgames.models.AuthGroup;
import com.cadykd.kdgames.models.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserPrincipal implements Serializable, UserDetails {
	
	User user;
	List<AuthGroup> authGroup;
	
	public AppUserPrincipal(User user, List<AuthGroup> authGroup) {
		this.user = user;
		this.authGroup = authGroup;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// check if list is empty
		if(authGroup == null) return Collections.emptyList();
		// disallow duplications
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		// loop through authGroup list and adding each role to spring security Simple Granted Authority object
		authGroup.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth.getAuthGroup())));
		// return the authorities
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
