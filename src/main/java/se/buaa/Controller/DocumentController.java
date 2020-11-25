package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Service.ES_DocumentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    ES_DocumentService es_documentService;

    @RequestMapping("findAll")
    public Iterable<ES_Document> findAll() {
        return es_documentService.findAll();
    }

    @RequestMapping("findByKeywordsLike")
    public Iterable<ES_Document> findByKeywordsLike(String keywords) {
        List<String> keyword = new ArrayList<String>();
        keyword.add(keywords);
         return es_documentService.findByKeywordsLike(keyword);
    }

    @RequestMapping("findByKeywords")
    public Iterable<ES_Document> findByKeywords(String keyword) {
        return es_documentService.findByKeywords(keyword);
    }

}
