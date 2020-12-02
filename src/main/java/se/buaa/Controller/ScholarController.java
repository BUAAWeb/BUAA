package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.Docu_ExpertRepository;
import se.buaa.Repository.DocumentRepository;
import se.buaa.Repository.ExpertRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ScholarController {
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    Docu_ExpertRepository docu_expertRepository;
    @Autowired
    DocumentRepository documentRepository;

    @GetMapping("/scholar/getCoAuthors")
    public Result getcoAuthors(@RequestParam("scholar_id") int scholar_id){
        

    }

}
