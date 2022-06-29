package com.cadykd.kdgames.data;

import com.cadykd.kdgames.models.SkillTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillTreeRepository extends JpaRepository<SkillTree, Integer> {
}
