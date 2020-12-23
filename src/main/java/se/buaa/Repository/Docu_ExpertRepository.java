package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Relation.Document_Expert;

import java.util.List;

public interface Docu_ExpertRepository extends JpaRepository<Document_Expert,Integer>{
    List<Document_Expert> findDocument_ExpertsByDocumentID(String doc_id);
    List<Document_Expert> findDocument_ExpertsByExpertID(String expert_id);
    Document_Expert findByDocumentIDAndExpertName(String documentId,String expertName);
}
