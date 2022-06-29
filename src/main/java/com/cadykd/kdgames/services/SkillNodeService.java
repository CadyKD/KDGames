package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.SkillNodeRepository;
import com.cadykd.kdgames.data.SkillTreeRepository;
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
public class SkillNodeService {
	// Initialize Fields
	SkillTreeRepository skillTreeRepository;
	SkillNodeRepository skillNodeRepository;
	
	// Initialize Service
	@Autowired
	public SkillNodeService(SkillTreeRepository skillTreeRepository, SkillNodeRepository skillNodeRepository) {
		this.skillTreeRepository = skillTreeRepository;
		this.skillNodeRepository = skillNodeRepository;
	}
	
	// Locate skill by name
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public SkillTreeNode findBySkillName(String skillName) throws NoSuchElementException {
		return skillNodeRepository.findById(skillName).orElseThrow();
	}
}
