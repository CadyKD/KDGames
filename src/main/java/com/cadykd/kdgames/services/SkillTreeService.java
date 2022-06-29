package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.SkillNodeRepository;
import com.cadykd.kdgames.data.SkillTreeRepository;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.SkillTree;
import com.cadykd.kdgames.models.SkillTreeNode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class SkillTreeService {
	// Initialize Fields
	SkillTreeRepository skillTreeRepository;
	SkillNodeRepository skillNodeRepository;
	
	// Initialize Service
	@Autowired
	public SkillTreeService(SkillTreeRepository skillTreeRepository, SkillNodeRepository skillNodeRepository) {
		this.skillTreeRepository = skillTreeRepository;
		this.skillNodeRepository = skillNodeRepository;
	}
	
	// Save new or make changes to a skillTree
	public void saveOrUpdate(SkillTree skillTree){
		skillTreeRepository.save(skillTree);
		log.info(String.format("Ryzom Skill Tree Generated: %d Character Name: %s", skillTree.getId(), skillTree.getRyzomCharacter().getCharacterName()));
	}
	
	public void delete(SkillTree skillTree) {
		skillTreeRepository.delete(skillTree);
	}
	
	// Join a skill node to its skill tree
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public void addSkillNodeToSkillTree(Integer treeId, SkillTreeNode skillNode) throws NoSuchElementException{
		SkillTree skillTree = skillTreeRepository.findById(treeId).orElseThrow();
		skillNode = skillNodeRepository.save(skillNode);
		skillTree.addSkillNode(skillNode);
		skillTreeRepository.save(skillTree);
	}
}
