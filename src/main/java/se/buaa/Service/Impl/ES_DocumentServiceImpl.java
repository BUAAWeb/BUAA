package se.buaa.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Service.ES_DocumentService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class ES_DocumentServiceImpl implements ES_DocumentService {
    @Autowired
    ES_DocumentDao es_documentDao;

    @Override
    public Page<ES_Document> findAll(PageRequest page) {
        return es_documentDao.findAll(page);
    }

    @Override
    public void save(List<ES_Document> documentList) {
        es_documentDao.saveAll(documentList);
    }

    @Override
    public void save(ES_Document document) {
        es_documentDao.save(document);
    }

    @Override
    public List<ES_Document> findByTitle(String title) {
        return es_documentDao.findByTitleLike(title);
    }

//    @Override
//    public Iterable<ES_Document> findByKeywords(String keyword, PageRequest page) {
//        return null;
//    }

    @Override
    public List<ES_Document> findByKeywords(String keyword) {
        return es_documentDao.findByKeywords(keyword);
    }

    @Override
    public List<ES_Document> findByKeywordsIn(List<String> keywords) {
//        List<ES_Document> es_documentList = new ArrayList<>();
//        for(String keyword : keywords){
//            List<ES_Document> es_documentList1 = es_documentDao.findByKeywordsIn(keyword);
//            if(es_documentList1 != null)
//                es_documentList.addAll(es_documentList1);
//        }
//        removeDuplicate(es_documentList);
//        return es_documentList;
        return null;
    }

    @Override
    public Page<ES_Document> findByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(Pageable page,String keywords, String experts, String origin, String startTime, String endTime) {
        if(startTime == null || startTime.equals(""))
            startTime = "0";
        if(endTime == null || endTime.equals(""))
            endTime = "2020";
        keywords = keywords == null ? "":keywords;
        experts = experts == null ? "":experts;
        origin = origin == null ? "":origin;
        return es_documentDao.findByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(page,keywords, experts, origin, startTime, endTime);
    }

    @Override
    public int countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(String keywords, String experts, String origin, String startTime, String endTime) {
        if(startTime == null || startTime.equals(""))
            startTime = "0";
        if(endTime == null || endTime.equals(""))
            endTime = "2020";
        keywords = keywords == null ? "":keywords;
        experts = experts == null ? "":experts;
        origin = origin == null ? "":origin;
        return es_documentDao.countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(keywords, experts, origin, startTime, endTime);
    }
    @Override
    public int countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(String keywords, String experts, String origin, String startTime, String endTime,String dtype) {
        if(startTime == null || startTime.equals(""))
            startTime = "0";
        if(endTime == null || endTime.equals(""))
            endTime = "2020";
        keywords = keywords == null ? "":keywords;
        experts = experts == null ? "":experts;
        origin = origin == null ? "":origin;
        if(dtype!=null)
            return es_documentDao.countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtype(keywords, experts, origin, startTime, endTime,dtype);
        else
            return es_documentDao.countByKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetween(keywords, experts, origin, startTime, endTime);
    }

    public static void handleParams(String keywords, String experts, String origin, String startTime, String endTime){
        if(startTime == null)
            startTime = "0";
        if(endTime == null)
            endTime = "2020";
        keywords = keywords == null ? "":keywords;
        experts = experts == null ? "":experts;
        origin = origin == null ? "":origin;
    }

    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
