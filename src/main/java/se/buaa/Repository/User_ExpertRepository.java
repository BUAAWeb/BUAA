package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Expert;
import se.buaa.Entity.User_Expert;

import java.util.List;

public interface User_ExpertRepository extends JpaRepository<User_Expert, Integer> {
    List<User_Expert> findByUserIdAndExpertId(Integer user_id, String expert_id);
    List<User_Expert> findByUserId(Integer user_id);
}
