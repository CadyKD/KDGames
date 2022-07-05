package com.cadykd.kdgames.data;

import com.cadykd.kdgames.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//	@Query(nativeQuery = true)
	User findByUserName(String username);
	
//	@Query(nativeQuery = true)
	User findByEmail(String email);
}
