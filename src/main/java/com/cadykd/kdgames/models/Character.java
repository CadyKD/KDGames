package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Character")
@Entity
// This class is to hold character data for RyzomTools
public class Character {
	// Table Fields
	@Id
	@Column(name = "Name")
	String characterName;
	
	@NotNull
	@Enumerated
	@Column(nullable = false)
	CharacterRace race;
	enum CharacterRace {
		MATIS,
		TRYKER,
		FYROS,
		ZORAI
	}
	
	@NotNull
	@Enumerated
	@Column(nullable = false)
	CharacterGender gender;
	enum CharacterGender {
		MALE,
		FEMALE
	}
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "CharacterSkillTree",
			joinColumns = @JoinColumn(name = "Name", referencedColumnName = "Name"),
			inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	SkillTree skillTree;
}
