package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
	@OneToMany(mappedBy = "ryzomCharacter", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
	List<Skill> skillTree = new ArrayList<>();
	
	public RyzomCharacter(String characterName, CharacterRace race, CharacterGender gender, User user) {
		this.characterName = characterName;
		this.race = race;
		this.gender = gender;
		this.user = user;
		this.skillTree.add(new Skill("Fight", "Fighter Apprentice", 1, 20, 1, "0.0.0", this));
		this.skillTree.add(new Skill("Magic", "Magician Apprentice", 1, 20, 1, "0.0.0", this));
		this.skillTree.add(new Skill("Craft", "Crafter Apprentice", 1, 20, 1, "0.0.0", this));
		this.skillTree.add(new Skill("Harvest", "Forager Apprentice", 1, 20, 1, "0.0.0", this));
	}
	
}
