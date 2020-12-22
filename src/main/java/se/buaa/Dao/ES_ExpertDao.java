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
import se.buaa.Entity.ESDocument.ES_Expert;

import java.util.List;


//@NoRepositoryBean
public interface  ES_ExpertDao extends ElasticsearchRepository<ES_Expert, Long> {
    ES_Expert findByExpertid(String id);
    List<ES_Expert> findByName(String name);
    Page<ES_Expert> findByNameContaining(Pageable page,String name);
    Page<ES_Expert> findByNameIn(Pageable page,String name);
}
