package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Relation.Document_Expert;

public interface Docu_ExpertRepository extends JpaRepository<Document_Expert,Integer>{
}
