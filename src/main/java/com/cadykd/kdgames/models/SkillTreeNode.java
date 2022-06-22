package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Skills")
@Entity
// This class is to hold skill data for RyzomTools SkillTrees
public class SkillTreeNode {
	// Table Fields
	@Id
	String skillName;
	String title;
	@ManyToOne
	@JoinColumn(name = "ParentSkillName")
	SkillTreeNode parentSkill = null;
	List<SkillTreeNode> childSkills = null;
	int minLevel;
	int maxLevel;
	int sortOrder;
	
	// Check if the current node is a root skill or child skill
	public boolean isRootNode() {
		return this.getParentSkill() == null;
	}
	
	// check if the current node has child skills
	public boolean hasChildSkills() {
		return this.childSkills.isEmpty();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SkillTreeNode that = (SkillTreeNode) o;
		return minLevel == that.minLevel && maxLevel == that.maxLevel && sortOrder == that.sortOrder && skillName.equals(that.skillName) && title.equals(that.title) && Objects.equals(parentSkill, that.parentSkill) && Objects.equals(childSkills, that.childSkills);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(skillName, title, parentSkill, childSkills, minLevel, maxLevel, sortOrder);
	}
}
