package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@Table(name = "users")
@Entity
// User class, for people who want to access RyzomTools
public class User {
	// Table Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NonNull
	String userName;
	@NonNull
	String email;
	@Setter(AccessLevel.NONE)
	@NonNull
	@Column(length=70)
	String password;
	
	// Constructor
	public User(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = new BCryptPasswordEncoder(4).encode(password);
	}
	
	public User(Integer id, String userName, String email, String password) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = new BCryptPasswordEncoder(4).encode(password);
	}
	
	// One user may have many characters
	@ToString.Exclude
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	Set<RyzomCharacter> ryzomCharacters = new LinkedHashSet<>();
	
	// Encryption for user passwords
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder(4).encode(password);
	}
	
	// Add a character to the set of characters a user has
	public void addToCharacters(RyzomCharacter ryzomCharacter) {
		ryzomCharacters.add(ryzomCharacter);
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
