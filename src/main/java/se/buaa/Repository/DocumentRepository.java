package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Document;

public interface DocumentRepository extends JpaRepository<Document,String> {
    Document findDocumentByDocumentID(String docID);
}
