package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "skill_tree")
@Entity
// This class is to hold skillTree data for RyzomTools
public class SkillTree {
	// Table Fields
	@Id
	@Column(name = "character_name")
	String characterName;
	
	// Each skill tree belongs to one character
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@MapsId
	@JoinColumn(name = "character_name")
	RyzomCharacter ryzomCharacter;
	
	// Each skill tree has many skills
	@ToString.Exclude
	@OneToMany(mappedBy = "skillTree", cascade = CascadeType.ALL)
	List<SkillTreeNode> skillList = new ArrayList<>();
	
	public SkillTree(String characterName, RyzomCharacter ryzomCharacter) {
		this.characterName = characterName;
		this.ryzomCharacter = ryzomCharacter;
	}
	
	public void addSkillNode(SkillTreeNode skillNode) {
		this.skillList.add(skillNode);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SkillTree skillTree = (SkillTree) o;
		return characterName.equals(skillTree.characterName) && ryzomCharacter.equals(skillTree.ryzomCharacter);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(characterName, ryzomCharacter);
	}
}
