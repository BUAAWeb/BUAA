package se.buaa.Dao;

import org.springframework.data.repository.CrudRepository;
import se.buaa.Entity.ESDocument.ES_Document;

import java.util.List;

public interface ES_DocumentDao extends CrudRepository<ES_Document, Long> {
    List<ES_Document> findByKeywordsLike(String keyword);
    List<ES_Document> findByKeywords(String keyword);
    List<ES_Document> findByTitleLike(String title);
}
