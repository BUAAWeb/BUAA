package se.buaa.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Entity.Collection;
import se.buaa.Entity.CollectionKey;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.CollectionRepository;
import se.buaa.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class PerDoc {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    ES_DocumentDao es_documentDao;

    @RequestMapping("/getCollectionList")
    public Result getCollectionList(@RequestParam("userID")int id)
    {
        List<Collection> collections=collectionRepository.findCollectionsByCollectionKey_Userid(id);
        List<CoFile> CoFileList= new ArrayList<>();
        ES_Document tmp;
        for(Collection collection:collections)
        {
            tmp = es_documentDao.findByDocumentid(collection.getCollectionKey().getDocumentid());
            if(tmp == null)
                continue;
            CoFileList.add(new CoFile(tmp.getDocumentid(),tmp.getTitle()));
        }
        return Result.Success(CoFileList);
    }

    public class CoFile{
        public String id;
        public String title;
        public CoFile(String tid,String ttitle)
        {
            this.id=tid;
            this.title=ttitle;
        }
    }

}
