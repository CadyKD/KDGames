package com.cadykd.kdgames.dto;

import com.cadykd.kdgames.validation.PasswordMatcher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@PasswordMatcher
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	@NotNull
	@NotEmpty(message="{NotEmpty.User.Missing}")
	private String userName;
	
	@Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$", message = "{Pattern.User.Email}")
	@NotNull
	@NotEmpty(message = "{NotEmpty.User.Missing}")
	private String email;
	
	@NotNull
	@NotEmpty(message="{NotEmpty.User.Missing}")
	private String password;
	private String matchingPassword;
}
