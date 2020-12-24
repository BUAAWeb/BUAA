package se.buaa.Controller;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Dao.ES_KeywordDao;
import se.buaa.Entity.Collection;
import se.buaa.Entity.CollectionKey;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.ESDocument.ES_Keyword;
import se.buaa.Entity.Enumeration.CodeEnum;
import se.buaa.Entity.Response.Result;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/keyword")
public class KeywordController {
    @Autowired
    ES_DocumentDao es_documentDao;
    @Autowired
    ES_KeywordDao es_keywordDao;
    @RequestMapping("set")
    public void setKeyword(){
//        if(user_id == null)
//            return new Result<>(CodeEnum.noUser.getCode(), CodeEnum.noUser.toString(),null);


        int i=0;
        while(true){
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withPageable(PageRequest.of(i, 100))
                    .build();
            i++;
            Page<ES_Keyword> es_keyword = es_keywordDao.search(searchQuery);
            List<ES_Keyword> ES_keywordList = es_keyword.toList();
            if(ES_keywordList.size()==0)
                break;
            for(ES_Keyword es_keyword1:ES_keywordList){
                String keywords=es_keyword1.keyword;
                if(keywords != null&& !keywords.equals("")){
                    BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
                    QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery("keywords",keywords).slop(0);
                    boolQueryBuilder.must(queryBuilder);
                    NativeSearchQuery searchQuery1 = new NativeSearchQueryBuilder()
                            .withPageable(PageRequest.of(0, 100))
                            .withQuery(queryBuilder)
                            .build();
                    es_keyword1.citedNum = (int) es_documentDao.search(searchQuery1).getTotalElements();
                    System.out.println(es_keyword1.keyword+":"+es_keyword1.citedNum);
                    es_keywordDao.save(es_keyword1);
                }
            }
        }

    }
}
