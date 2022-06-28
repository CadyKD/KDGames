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
@Table(name = "character")
@Entity
// This class is to hold character data for RyzomTools
public class Character {
	// Table Fields
	@Id
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	User user;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinTable(name = "character_skill_tree",
			joinColumns = @JoinColumn(name = "character_name"),
			inverseJoinColumns = @JoinColumn(name = "tree_id"))
	SkillTree skillTree;
}
