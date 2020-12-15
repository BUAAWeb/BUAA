package se.buaa.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Service.ES_DocumentService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(allowCredentials="false")
@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    ES_DocumentService es_documentService;
    @Autowired
    ES_DocumentDao es_documentDao;

    @RequestMapping("findAll")
    public Iterable<ES_Document> findAll() {
//        Sort.Order order1 = Sort.Order.desc("_id");
        Sort.Order order2 = Sort.Order.desc("cited_quantity");
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order2);
        Sort sort = Sort.by(orderList);
        PageRequest page = PageRequest.of(0, 200000 );
        return es_documentService.findAll(page);
    }

    @RequestMapping("findByKeywordsLike")
    public Iterable<ES_Document> findByKeywordsLike(String keywords) {
        List<String> keyword = new ArrayList<String>();
        keyword.add(keywords);
         return es_documentService.findByKeywordsLike(keyword);
    }

//    @RequestMapping("findByKeywords")
//    public Iterable<ES_Document> findByKeywords(String keyword) {
//        return es_documentService.findByKeywords(keyword);
//    }

}
