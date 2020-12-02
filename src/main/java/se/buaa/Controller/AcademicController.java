package se.buaa.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.Response.Result;
import se.buaa.Service.ES_DocumentService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(allowCredentials="false")
@RestController
@RequestMapping("/academic")
public class AcademicController {
    @Autowired
    ES_DocumentService es_documentService;

    @RequestMapping("getHighCited")
    public Result<Iterable<ES_Document>> getHighCited() {
        Sort.Order order = Sort.Order.desc("cited_quantity");
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order);
        Sort sort = Sort.by(orderList);
        PageRequest page = PageRequest.of(0, 10 ,sort);
        Iterable<ES_Document> highCitedList = es_documentService.findAll(page);
        if(highCitedList != null)
            return new Result<>(200, "success", highCitedList);
        else
            return new Result<>(400,"error",null);
    }

//    @RequestMapping("getById")
//    public Result<Iterable<ES_Document>> getById(String id){
//
//    }
}
