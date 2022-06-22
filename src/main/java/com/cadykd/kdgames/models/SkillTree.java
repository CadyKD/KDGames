package com.cadykd.kdgames.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "SkillTrees")
@Entity
// This class is to hold skillTree data for RyzomTools
public class SkillTree {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	List<SkillTreeNode> skillList = new ArrayList<>();
	
	@OneToOne(mappedBy = "SkillTree", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	Character character;
}
