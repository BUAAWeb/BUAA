package se.buaa.Service;

import org.springframework.data.domain.PageRequest;
import se.buaa.Entity.ESDocument.ES_Document;

import java.awt.print.Pageable;
import java.util.List;


public interface ES_DocumentService {
    Iterable<ES_Document> findAll(PageRequest page);

    void save(List<ES_Document> documentList);

    void save(ES_Document document);

    List<ES_Document> findByTitle(String title);

    Iterable<ES_Document> findByKeywords(String keyword,PageRequest page);
    Iterable<ES_Document> findByKeywordsLike(List<String> keywords);
}
