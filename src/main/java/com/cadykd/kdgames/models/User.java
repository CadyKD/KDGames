package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
// uncomment when security is added
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Users")
@Entity
// User class, for people who want to access RyzomTools and play minigames
public class User {
	// Table Fields
	@Id
	String userName;
	@NonNull
	String email;
	@Setter(AccessLevel.NONE)
	@NonNull
	String password;
	
	// Constructor
	public User(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		// Delete first line under this and uncomment second line when security is added
		this.password = password;
		//this.password = new BCryptPasswordEncoder(4).encode(password);
	}
	
	// One user may have many characters
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_characters",
			joinColumns = @JoinColumn(name = "user_name"),
			inverseJoinColumns = @JoinColumn(name = "character_name"))
	Set<RyzomCharacter> ryzomCharacters = new LinkedHashSet<>();
	
	// Encryption for user passwords
	public void setPassword(String password) {
		// Delete first line under this and uncomment second line when security is added
		this.password = password;
		//this.password = new BCryptPasswordEncoder(4).encode(password);
	}
	
	// Add a character to the set of characters a user has
	public void addCharacter(RyzomCharacter ryzomCharacter) {
		ryzomCharacters.add(ryzomCharacter);
		ryzomCharacter.addUser(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return userName.equals(user.userName) && email.equals(user.email) && password.equals(user.password) && Objects.equals(ryzomCharacters, user.ryzomCharacters);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userName, email, password, ryzomCharacters);
	}
}
