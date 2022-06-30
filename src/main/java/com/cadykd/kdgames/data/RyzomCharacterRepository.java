package com.cadykd.kdgames.data;

import com.cadykd.kdgames.models.RyzomCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RyzomCharacterRepository extends JpaRepository<RyzomCharacter, String> {
	@Query(value = "select * from users_characters as uc where uc.user_name = :userName", nativeQuery = true)
	List<RyzomCharacter> findUserCharacters(String userName);
}
