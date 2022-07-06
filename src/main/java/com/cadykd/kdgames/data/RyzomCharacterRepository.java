package com.cadykd.kdgames.data;

import com.cadykd.kdgames.models.RyzomCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RyzomCharacterRepository extends JpaRepository<RyzomCharacter, String> {
	@Query(value = "select uc.character_name from users_characters as uc where uc.userid = :userId", nativeQuery = true)
	List<RyzomCharacter> findUserCharacters(Integer userId);
	
	@Query(nativeQuery = true)
	RyzomCharacter findCharacterBycharacterName(String characterName);
}
