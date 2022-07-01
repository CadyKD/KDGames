package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "skills")
@Entity
// This class is to hold skill data for RyzomTools SkillTrees
public class Skill {
	// Table Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String skillName;
	String title;
	int minLevel;
	int maxLevel;
	int currentLevel;
	String sortOrder;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "skills_list",
			joinColumns = @JoinColumn(name = "skillName"),
			inverseJoinColumns = @JoinColumn(name = "charName"))
	RyzomCharacter ryzomCharacter;
	
	public Skill(String skillName, String title, int minLevel, int maxLevel, int currentLevel, String sortOrder, RyzomCharacter ryzomCharacter) {
		this.skillName = skillName;
		this.title = title;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.currentLevel = currentLevel;
		this.sortOrder = sortOrder;
		this.ryzomCharacter = ryzomCharacter;
	}
}
