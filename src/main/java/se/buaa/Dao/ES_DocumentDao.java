//package se.buaa.Dao;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//import se.buaa.Entity.ESDocument.ES_Document;
//
//import java.util.List;
//
//
////@NoRepositoryBean
//public interface  ES_DocumentDao extends ElasticsearchRepository<ES_Document, Long> {
//    ES_Document findByDocumentid(String id);
//    List<ES_Document> findByKeywordsIn(String keyword);
//    List<ES_Document> findByKeywords(String keyword);
//    List<ES_Document> findByTitleIn(String title);
//    List<ES_Document> findByExpertsContains(String experts);
//    Page<ES_Document> findByExpertsInAndExperts(Pageable page,List<String> expertsName,String expertName);
//    Page<ES_Document> findByExpertsIsContaining(Pageable page,String experts);
//    Page<ES_Document> findByExpertsEquals(Pageable page,String experts);
//    Page<ES_Document> findByExpertsIn(Pageable page,List<String> expertsName);
//    Page<ES_Document> findByExpertsContaining(Pageable page,String experts);
//    Page<ES_Document> findByAuthorsIs(Pageable page,String authors);
//    Page<ES_Document> findByExperts(Pageable page,String experts);
//    Page<ES_Document> findAll(Pageable page);
//    Page<ES_Document> findByKeywordsInAndExpertsInAndOriginIn(Pageable page,String keywords,String experts,String origin);
//    Page<ES_Document> findByKeywordsInAndExpertsInAndOriginInAndTimeBetween(Pageable page,
//            String keywords,String experts,String origin,String startTime,String endTime);
//
//    int countByKeywordsInAndExpertsInAndOriginInAndTimeBetween(
//            String keywords,String experts,String origin,String startTime,String endTime);
//    int countByKeywordsInAndExpertsInAndOriginInAndTimeBetweenAndDtype(
//            String keywords,String experts,String origin,String startTime,String endTime,String dType);
//}
package se.buaa.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import se.buaa.Entity.ESDocument.ES_Document;

import java.util.List;


//@NoRepositoryBean
public interface  ES_DocumentDao extends ElasticsearchRepository<ES_Document, Long> {
    ES_Document findByDocumentid(String id);
    List<ES_Document> findByKeywordsLike(String keyword);
    List<ES_Document> findByKeywords(String keyword);
    List<ES_Document> findByTitleLike(String title);
    List<ES_Document> findTop10ByViews();
    Page<ES_Document> findAll(Pageable page);
    Page<ES_Document> findByExpertsIn(Pageable page,List<String> experts);
    Page<ES_Document> findByKeywordsIn(Pageable page,String keywords);
    List<ES_Document> findByExpertsIn(List<String> experts);
    Page<ES_Document> findByKeywordsLikeAndExpertsLikeAndOriginLike(Pageable page,String keywords,String experts,String origin);

    Page<ES_Document> findByExpertsLike(Pageable page,String keywords);
    Page<ES_Document> findByExpertsIn(Pageable page,String keywords);
    Page<ES_Document> findByExpertsContaining(Pageable page,String keywords);
    Page<ES_Document> findByExpertsContains(Pageable page,String keywords);
//    Page<ES_Document> (Pageable page,String keywords);
    Page<ES_Document> findByKeywordsIsIn(Pageable page,String keywords);
    Page<ES_Document> findByExpertsIsIn(Pageable page,String experts);

    Page<ES_Document> findByKeywordsLikeAndExpertsLikeAndOriginLikeAndTimeBetween(Pageable page,
                                                                                  String keywords,String experts,String origin,String startTime,String endTime);
    Page<ES_Document> findByKeywordsInAndExpertsLikeAndOriginLike(Pageable page,String keywords,String experts,String origin);
    Page<ES_Document> findByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(Pageable page, String keywords,String experts,String origin,String startTime,String endTime);
    Page<ES_Document> findByTitleInOrSummaryInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            Pageable page,String title,String summary,String keywords,String experts,
            String origin,String startTime,String endTime,String dType);
    Page<ES_Document> findByTitleInAndSummaryInAndKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            Pageable page,String title,String summary,String keywords,String experts,
            String origin,String startTime,String endTime,String dType);
    Page<ES_Document> findByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            Pageable page,String title,String experts, String origin,String startTime,String endTime,String dType,
            String summary,String experts1, String origin1,String startTime1,String endTime1,String dType1,
            String keywords, String experts2, String origin2,String startTime2,String endTime2,String dType2);
    int countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            String title,String experts, String origin,String startTime,String endTime,String dType,
            String summary,String experts1, String origin1,String startTime1,String endTime1,String dType1,
            String keywords, String experts2, String origin2,String startTime2,String endTime2,String dType2);
    Page<ES_Document> findByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
            Pageable page,String title,String experts, String origin,String startTime,String endTime,List<String> dType,
            String summary,String experts1, String origin1,String startTime1,String endTime1,List<String> dType1,
            String keywords, String experts2, String origin2,String startTime2,String endTime2,List<String> dType2);
    int countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
            String title,String experts, String origin,String startTime,String endTime,List<String> dType,
            String summary,String experts1, String origin1,String startTime1,String endTime1,List<String> dType1,
            String keywords, String experts2, String origin2,String startTime2,String endTime2,List<String> dType2);
    int countByExpertsIn(String experts);
    int countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(
            String keywords,String experts,String origin,String startTime,String endTime);
    int countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            String keywords,String experts,String origin,String startTime,String endTime,String dType);
    int countByTitleInOrSummaryInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(
            String title,String summary,String keywords,String experts,String origin,String startTime,String endTime);
    int countByTitleInOrSummaryInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            String title,String summary,String keywords,String experts,String origin,String startTime,String endTime,String dType);
    int countByTitleInAndSummaryInAndKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(
            String title,String summary,String keywords,String experts,String origin,String startTime,String endTime,String dType);

}