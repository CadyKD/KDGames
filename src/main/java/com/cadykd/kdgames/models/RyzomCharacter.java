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
	@Column(name = "charName")
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
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_characters",
			joinColumns = @JoinColumn(name = "characterName"),
			inverseJoinColumns = @JoinColumn(name = "userName"))
	User user;
	
	// Each character can have only one skill tree
	@ToString.Exclude
	@OneToOne(mappedBy = "ryzomCharacter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	SkillTree characterSkillTree = new SkillTree(this.characterName, this);
	
	public RyzomCharacter(String characterName, CharacterRace race, CharacterGender gender, User user) {
		this.characterName = characterName;
		this.race = race;
		this.gender = gender;
		this.user = user;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RyzomCharacter that = (RyzomCharacter) o;
		return characterName.equals(that.characterName) && race == that.race && gender == that.gender;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(characterName, race, gender);
	}
}
