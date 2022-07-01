package com.cadykd.kdgames.data;

import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.SkillTreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillNodeRepository extends JpaRepository<SkillTreeNode, String> {
}
