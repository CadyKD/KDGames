package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "auth_group")
@Entity
public class AuthGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NonNull
	String userName;
	@NonNull
	String email;
	@NonNull
	String authGroup;
	
	public AuthGroup(User user, String authGroup) {
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.authGroup = authGroup;
	}
}