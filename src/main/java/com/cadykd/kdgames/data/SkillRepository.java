package com.cadykd.kdgames.data;

import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
	
	@Query(value = "select sl.skill_name from skills_list as sl where sl.char_name = :characterName", nativeQuery = true)
	List<Skill> findCharacterSkills(String characterName);
}
