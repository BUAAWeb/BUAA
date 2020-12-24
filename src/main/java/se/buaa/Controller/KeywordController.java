package se.buaa.Controller;

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
        int i=164;
        while(true){
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withPageable(PageRequest.of(i, 100))
                    .build();
            i++;
            Page<ES_Document> es_documents = es_documentDao.search(searchQuery);
            List<ES_Document> es_documentList = es_documents.toList();
            if(es_documentList.size()==0)
                break;
            for(ES_Document es_document:es_documentList){
                for(String keyword:es_document.getKeywordList()){
                    if(Pattern.matches("\\s*",keyword)||keyword==null){
                        continue;
                    }
                    ES_Keyword es_keyword = es_keywordDao.findByKeyword(keyword);
                    if(es_keyword!=null){
                        System.out.println(es_keyword.keyword+"已存在:"+i);
                        es_keyword.citedNum++;
                        es_keywordDao.save(es_keyword);
                        continue;
                    }
                    else {
                        es_keyword=new ES_Keyword();
                        es_keyword.keyword=keyword;
                        System.out.println(keyword);
                        es_keywordDao.save(es_keyword);
                    }
                }
            }
        }

    }
}
