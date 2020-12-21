package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;
import se.buaa.Entity.Data.Achievement;
import se.buaa.Entity.Data.ScholarInfo;
import se.buaa.Entity.Document;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Relation.Document_Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User_Expert;
import se.buaa.Exception.ScholarException;
import se.buaa.Repository.Docu_ExpertRepository;
import se.buaa.Repository.DocumentRepository;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Repository.User_ExpertRepository;

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
    @Autowired
    User_ExpertRepository user_expertRepository;


    @GetMapping("/scholar/getCoAuthors")
    public Result getcoAuthors(@RequestParam("scholar_id") String scholar_id){
        Map<String,Integer> Related_Experts = new LinkedHashMap<String,Integer>();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("expertid", ExampleMatcher.GenericPropertyMatcher::exact)
                .withIgnorePaths("id").withIgnorePaths("documentid");
        // 可视情况使用ES进行优化
        Document_Expert tmpRelation = new Document_Expert();
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

    /** 获取科研人员相关信息 */
    @GetMapping("/scholar/getInfo")
    public Result getScholarInfo(@RequestParam(value = "scholar_id") String scholar_id
                                    , @RequestParam(value = "user_id") String user_id) {
        Expert expert = expertRepository.findByExpertID(scholar_id);

        // 错误处理，类型码在 ErrorConfig.xml 文件中，类型码要和创建的 Exception 类关联
        // Exception 类的创建方法详见 ScholarException
        if (expert == null) return Result.Error(new ScholarException("00"));

        ScholarInfo scholarInfo = new ScholarInfo();

        scholarInfo.name = expert.getName();
        scholarInfo.volume = expert.getViews();
        scholarInfo.scholar_id = expert.getExpertID();
        scholarInfo.affiliate = expert.getOrg();
        scholarInfo.isVerified = expert.getIsVerified() == 1;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("expertid", ExampleMatcher.GenericPropertyMatcher::exact)
                .withIgnorePaths("id").withIgnorePaths("documentid");
        Document_Expert tmpRelation = new Document_Expert();
        tmpRelation.setExpertID(scholar_id);
        Example<Document_Expert> example = Example.of(tmpRelation, matcher);
        List<Document_Expert> exp_docs = docu_expertRepository.findAll(example);

        int size = Math.min(exp_docs.size(), 4);

        for (int i = 0; i < size; i++) {
            Document doc = documentRepository.findDocumentByDocumentID(exp_docs.get(i).getDocumentID());
            scholarInfo.achList.add(new Achievement(doc.getTitle(), doc.getCitedQuantity()));
        }

        int userId = Integer.parseInt(user_id);
        List<User_Expert> list = user_expertRepository.findByUserIdAndExpertId(userId, scholar_id);

        scholarInfo.isFocus = list.size() != 0;

        return Result.Success(scholarInfo);
    }

//    /** 获取科研人员相关文档 */
//    @PostMapping("/getRelateSc")
//    @ResponseBody
//    public Result getScRelatedDoc (@RequestBody Map<String, Object> map) {
//        /* 查询字段提取 */
//        if (map.get("scholar_id") == null) return Result.Error(new ScholarException("00"));
//        String scholar_id = map.get("scholar_id").toString();
//
//        Expert expert = expertRepository.findByExpertID(scholar_id);
//        if (expert == null) return Result.Error(new ScholarException("01"));
//
////        ExampleMatcher matcher = ExampleMatcher.matching()
////                .withMatcher("expertid", ExampleMatcher.GenericPropertyMatcher::exact)
////                .withIgnorePaths("id").withIgnorePaths("documentid");
////        Document_Expert tmpRelation = new Document_Expert();
////        tmpRelation.setExpertID(scholar_id);
////        Example<Document_Expert> example = Example.of(tmpRelation, matcher);
////        List<Document_Expert> exp_docs = docu_expertRepository.findAll(example);
////        List<Document> docs = new ArrayList<>();
////
////        for (int i = 0; i < exp_docs.size(); i++) {
////            docs.add(documentRepository.findByDocumentID(exp_docs.get(i).getDocumentID()));
////        }
//
//        ExampleMatcher matcher = ExampleMatcher.matching();
//        Document tmpDoc = new Document();
//
//        if (map.get("sc_year") != null) {
//            String sc_year = map.get("sc_year").toString();
//            if (!sc_year.equals("")) {
//                matcher.withMatcher("date", ExampleMatcher.GenericPropertyMatcher::startsWith);
//                tmpDoc.setDate(sc_year);
//            }
//        }
//
//        if (map.get("paper_type") != null) {
//            int paper_type = Integer.parseInt(map.get("paper_type").toString());
//            matcher.withMatcher("document_type", ExampleMatcher.GenericPropertyMatcher::exact);
//            tmpDoc.setDocument_type(paper_type);
//        }
//
//        int first_author_flag = 0;
//        if (map.get("first_author") != null) {
//            first_author_flag = Integer.parseInt(map.get("first_author").toString());
//        }
//        String author = expert.getName();
//
//        if (first_author_flag == 0) {
//            matcher.withMatcher("experts", ExampleMatcher.GenericPropertyMatcher::contains);
//            tmpDoc.setExperts(author);
//        }
//        else {
//            matcher.withMatcher("first_author", ExampleMatcher.GenericPropertyMatcher::contains);
//            tmpDoc.setFirst_author(author);
//        }
//
//
//        Example<Document> example = Example.of(tmpDoc, matcher);
//        List<Document> docs = documentRepository.findAll(example);
//
//        String sc_sort = "";
//        if (map.get("sc_sort") != null) {
//            sc_sort = map.get("sc_sort").toString();
//        }
//
//        if (sc_sort.equals("time")) {
//            docs.sort(Comparator.comparing(Document::getDate));
//        }
//        else if (sc_sort.equals("cited")) {
//            docs.sort(Comparator.comparing(Document::getCitedQuantity));
//        }
//
//        int page = 0;
//        if (map.get("page") != null) {
//            page = Integer.parseInt(map.get("page").toString());
//        }
//
//        List<ScRelateDoc> list = new ArrayList<>();
//        /** unfinished */
//        for (int i = 10 * (page-1); i < Math.min(docs.size(), 10 * page); i++) {
//            ScRelateDoc scRelateDoc = new ScRelateDoc();
//            Document doc = docs.get(i);
//            scRelateDoc.setDocId(doc.getDocumentID());
//            scRelateDoc.setTitled(doc.getTitle());
//            scRelateDoc.setAuthors(doc.getExperts());
//            scRelateDoc.setCited(doc.getCitedQuantity());
//            scRelateDoc.setSummary(doc.getSummary());
//            list.add(scRelateDoc);
//        }
//
//        return Result.Success(list);
//    }

    /** 关注、取消关注 */
    @GetMapping("/scholar/focusScholar")
    public Result focusScholar(@RequestParam(value = "scholar_id") String scholar_id
                            , @RequestParam(value = "user_id") String user_id) {
        int userId = Integer.parseInt(user_id);
        List<User_Expert> list = user_expertRepository.findByUserIdAndExpertId(userId, scholar_id);

        if (list.size() == 0) {
            User_Expert ue = new User_Expert(userId, scholar_id);
            user_expertRepository.save(ue);
            return Result.Success(true);
        }
        else {
            user_expertRepository.delete(list.get(0));
            return Result.Success(false);
        }
    }

}
