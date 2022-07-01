package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

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
public class SkillTreeNode {
	// Table Fields
	@Id
	String skillName;
	String title;
	int minLevel;
	int maxLevel;
	int currentLevel;
	String sortOrder;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "tree_skills",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "skills_skillName"))
	SkillTree skillTree;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SkillTreeNode that = (SkillTreeNode) o;
		return minLevel == that.minLevel && maxLevel == that.maxLevel && currentLevel == that.currentLevel && skillName.equals(that.skillName) && title.equals(that.title) && sortOrder.equals(that.sortOrder) && skillTree.equals(that.skillTree);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(skillName, title, minLevel, maxLevel, currentLevel, sortOrder, skillTree);
	}
}
