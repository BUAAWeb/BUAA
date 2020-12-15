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
    List<ES_Document> findByKeywordsLike(String keyword);
    List<ES_Document> findByKeywords(String keyword);
    List<ES_Document> findByTitleLike(String title);
    Page<ES_Document> findAll(Pageable page);
}
