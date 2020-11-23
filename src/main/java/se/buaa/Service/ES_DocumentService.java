package se.buaa.Service;

import se.buaa.Document.ES_Document;

import java.util.List;


public interface ES_DocumentService {
    Iterable<ES_Document> findAll();

    void save(List<ES_Document> documentList);

    void save(ES_Document document);

    List<ES_Document> findByTitle(String title);

    List<ES_Document> findByKeywords(List<String> keywords);
}
