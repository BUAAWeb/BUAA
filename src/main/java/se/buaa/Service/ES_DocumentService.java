package se.buaa.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se.buaa.Entity.ESDocument.ES_Document;

import java.util.List;


public interface ES_DocumentService {
    Iterable<ES_Document> findAll(PageRequest page);

    void save(List<ES_Document> documentList);

    void save(ES_Document document);

    List<ES_Document> findByTitle(String title);

    List<ES_Document> findByKeywords(String keyword);
    List<ES_Document> findByKeywordsIn(List<String> keywords);
    Page<ES_Document> findByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(Pageable page,
                                                                                  String keywords, String experts, String origin, String startTime, String endTime);
    int countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(
            String keywords,String experts,String origin,String startTime,String endTime);
    int countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            String keywords,String experts,String origin,String startTime,String endTime,String dType);

}
