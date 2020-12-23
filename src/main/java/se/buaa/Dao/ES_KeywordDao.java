package se.buaa.Dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import se.buaa.Entity.ESDocument.ES_Expert;
import se.buaa.Entity.ESDocument.ES_Keyword;

public interface ES_KeywordDao extends ElasticsearchRepository<ES_Keyword, Long> {
    ES_Keyword findByKeyword(String keyword);
}
