package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Document.ES_Document;
import se.buaa.Service.ES_DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    ES_DocumentService es_documentService;

    @RequestMapping("findAll")
    public Iterable<ES_Document> findAll() {
        return es_documentService.findAll();
    }

}