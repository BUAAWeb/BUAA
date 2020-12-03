package se.buaa.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import se.buaa.Entity.ESDocument.ES_Document;

import java.util.List;


//@NoRepositoryBean
public interface ES_DocumentDao extends PagingAndSortingRepository<ES_Document, Long> {
    Page<ES_Document> findByKeywordsLike(String keyword,);
    Page<ES_Document> findByKeywords(String keyword);
    Page<ES_Document> findByTitleLike(String title);
//    Page<ES_Document> findAll(Pageable page);
}
