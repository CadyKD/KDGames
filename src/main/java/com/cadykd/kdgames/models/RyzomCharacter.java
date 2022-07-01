package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "characters")
@Entity
// This class is to hold character data for RyzomTools
public class RyzomCharacter {
	// Table Fields
	@Id
	String characterName;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	CharacterRace race;
	public enum CharacterRace {
		MATIS,
		TRYKER,
		FYROS,
		ZORAI
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	CharacterGender gender;
	public enum CharacterGender {
		MALE,
		FEMALE
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_characters",
			joinColumns = @JoinColumn(name = "characterName"),
			inverseJoinColumns = @JoinColumn(name = "userName"))
	User user;
	
	// Each character can have only one skill tree
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	SkillTree characterSkillTree;
	
	public RyzomCharacter(String characterName, CharacterRace race, CharacterGender gender) {
		this.characterName = characterName;
		this.race = race;
		this.gender = gender;
	}
	
	public void addUser(User user) {
		this.user = user;
		user.getRyzomCharacters().add(this);
	}
	
	public void addSkillTree(SkillTree skillTree) {
		this.characterSkillTree = skillTree;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RyzomCharacter that = (RyzomCharacter) o;
		return characterName.equals(that.characterName) && race == that.race && gender == that.gender && user.equals(that.user);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(characterName, race, gender, user);
	}
}
