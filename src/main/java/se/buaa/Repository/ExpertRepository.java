package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Expert;

public interface ExpertRepository extends JpaRepository<Expert,String> {
    Expert findByExpertID(String id);
}
