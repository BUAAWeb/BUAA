package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Relation.Document_Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.Docu_ExpertRepository;
import se.buaa.Repository.DocumentRepository;
import se.buaa.Repository.ExpertRepository;

import java.util.*;

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
    public Result getcoAuthors(@RequestParam("scholar_id") String scholar_id){
        Map<String,Integer> Related_Experts = new LinkedHashMap<String,Integer>();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("expertid", ExampleMatcher.GenericPropertyMatcher::exact)
                .withIgnorePaths("id").withIgnorePaths("documentid");
        //可视情况使用ES进行优化
        Document_Expert tmpRelation=new Document_Expert();
        tmpRelation.setExpertID(scholar_id);
        Example<Document_Expert> example = Example.of(tmpRelation,matcher);
        List<Document_Expert> all_docs = docu_expertRepository.findAll(example);
        matcher = ExampleMatcher.matching()
                .withMatcher("documentid", ExampleMatcher.GenericPropertyMatcher::exact)
                .withIgnorePaths("id").withIgnorePaths("expertid");
        List<Document_Expert> this_experts;
        for(Document_Expert doc:all_docs){
            tmpRelation.setDocumentID(doc.getDocumentID());
            example = Example.of(tmpRelation,matcher);
            this_experts = docu_expertRepository.findAll(example);
            for(Document_Expert One_expert:this_experts){
                String expert_id = One_expert.getExpertID();
                if(expert_id.equals(scholar_id))
                    continue;
                if(Related_Experts.containsKey(expert_id)){
                    Related_Experts.put(expert_id,Related_Experts.get(expert_id)+1);
                }
                else{
                    Related_Experts.put(expert_id,1);
                }
            }
        }
        //为空直接返回
        if(Related_Experts.isEmpty())
            return Result.Success();
        //将map排序
        ArrayList<Map.Entry<String,Integer>> l = new ArrayList<Map.Entry<String,Integer>>(Related_Experts.entrySet());

        l.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        Iterator<Map.Entry<String, Integer>> iter = l.iterator();
        List<String> expert_num_list = new ArrayList<>();
        Map.Entry<String, Integer> tmpEntry = null;
        for(int i=0;i<10;i++){
            if(!iter.hasNext())
                break;
            tmpEntry = iter.next();
            expert_num_list.add(tmpEntry.getKey());
        }
        List<Expert> experts =new ArrayList<>();
        for(String item:expert_num_list){
            experts.add(expertRepository.findByExpertID(item));
        }
        return Result.Success(experts);
    }


    @GetMapping("/scholar/getCoAffiliate")
    public Result getcoAffiliate(@RequestParam("scholar_id") String scholar_id) {
        Map<String,Integer> Related_Experts = new LinkedHashMap<String,Integer>();
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("expertid", ExampleMatcher.GenericPropertyMatcher::exact)
//                .withIgnorePaths("id").withIgnorePaths("documentid");
//        //可视情况使用ES进行优化
//        Document_Expert tmpRelation=new Document_Expert();
//        tmpRelation.setExpertID(scholar_id);
//        Example<Document_Expert> example = Example.of(tmpRelation,matcher);
        List<Document_Expert> all_docs = docu_expertRepository.findDocument_ExpertsByExpertID(scholar_id);
//        matcher = ExampleMatcher.matching()
//                .withMatcher("documentid", ExampleMatcher.GenericPropertyMatcher::exact)
//                .withIgnorePaths("id").withIgnorePaths("expertid");
        List<Document_Expert> this_experts;
        for(Document_Expert doc:all_docs){
//            tmpRelation.setDocumentID(doc.getDocumentID());
//            example = Example.of(tmpRelation,matcher);
            this_experts = docu_expertRepository.findDocument_ExpertsByDocumentID(doc.getDocumentID());
            for(Document_Expert One_expert:this_experts){
                String expert_id = One_expert.getExpertID();
                if(expert_id.equals(scholar_id))
                    continue;
                if(Related_Experts.containsKey(expert_id)){
                    Related_Experts.put(expert_id,Related_Experts.get(expert_id)+1);
                }
                else{
                    Related_Experts.put(expert_id,1);
                }
            }
        }
        //为空直接返回
        if(Related_Experts.isEmpty())
            return Result.Success();
        Map<String,Integer> Affiliate_count = new LinkedHashMap<String,Integer>();
        Expert tmpExpert;
        String org;
        for (Map.Entry<String, Integer> entry : Related_Experts.entrySet()) {
            tmpExpert = expertRepository.findByExpertID(entry.getKey());
            org = tmpExpert.getOrg();
            if(Affiliate_count.containsKey(org)){
                Affiliate_count.put(org,Affiliate_count.get(org)+entry.getValue());
            }
            else{
                Related_Experts.put(org,entry.getValue());
            }
        }

        //将map排序
        ArrayList<Map.Entry<String,Integer>> l = new ArrayList<Map.Entry<String,Integer>>(Affiliate_count.entrySet());

        l.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        Iterator<Map.Entry<String, Integer>> iter = l.iterator();
        List<Pair<String,Integer>> Affiliate_count_list = new ArrayList<>();
        Map.Entry<String, Integer> tmpEntry = null;
        for(int i=0;i<10;i++){
            if(!iter.hasNext())
                break;
            tmpEntry = iter.next();
            Affiliate_count_list.add(Pair.of(tmpEntry.getKey(),tmpEntry.getValue()));
        }
        return Result.Success(Affiliate_count_list);

    }



}
