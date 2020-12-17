package se.buaa.Controller;

//200（OK）- 如果现有资源已被更改
//201（created）- 如果新资源被创建
//202（accepted）- 已接受处理请求但尚未完成（异步处理）
//301（Moved Permanently）- 资源的URI被更新
//303（See Other）- 其他（如，负载均衡）
//400（bad request）- 指代坏请求
//404 （not found）- 资源不存在
//406 （not acceptable）- 服务端不支持所需表示
//409 （conflict）- 通用冲突
//412 （Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）
//415 （unsupported media type）- 接受到的表示不受支持
//500 （internal server error）- 通用错误响应
//503 （Service Unavailable）- 服务当前无法处理请求
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.collect.Map;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregator;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Entity.Data.Data;
import se.buaa.Entity.Data.SearchResultData;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.Enumeration.CodeEnum;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Service.ES_DocumentService;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@CrossOrigin(allowCredentials="false")
@RestController
@RequestMapping("/academic")
public class AcademicController {
    @Autowired
    ES_DocumentDao es_documentDao;

    @Autowired
    ES_DocumentService es_documentService;

    @RequestMapping("test")
    public Iterable<ES_Document> test(ElasticsearchOperations elasticsearchOperations){
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
        // 执行查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);

        TermsAggregationBuilder tb =  AggregationBuilders.terms("citedQuantity").field("cited_quantity");
        nativeSearchQueryBuilder.addAggregation(tb);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();

        Page<ES_Document> items = es_documentDao.search(nativeSearchQuery);

//        Aggregations agg = elasticsearchOperations(nativeSearchQuery, searchResponse -> {
//            Aggregations aggregations = searchResponse.getAggregations();
//            return aggregations;
//        });

        //这里是Storm流的写法，jdk8的新特性
        items.forEach(System.out::println);
        return items;
    }

    @RequestMapping("getHighCited")
    public Result<Data> getHighCited() {
        Sort.Order order = Sort.Order.desc("time");
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order);
        Sort sort = Sort.by(orderList);
        PageRequest page = PageRequest.of(0, 100,sort);
        Iterable<ES_Document> highCitedList = es_documentService.findAll(page);
        List<ES_Document> documentsList = new ArrayList<>();
        highCitedList.forEach(single ->{documentsList.add(single);});
//        for(ES_Document es_document : highCitedList){
//            System.out.println(es_document.getAuthors());
//        }
        Data data = new Data();
        data.setResult_list(documentsList);
        return new Result("200", CodeEnum.success.toString(), data);
//        if(highCitedList != null)
//            return new Result<>(200, "success",highCitedList);
//        else
//            return new Result<>(400,"error",null);
    }
    @RequestMapping("getSearchResult")
    public Result<SearchResultData> findsearchresult(@RequestParam String kw,//keyword
                                                          @RequestParam String au,//author
                                                          @RequestParam("sort") String sortWay, //排序方式
                                                          @RequestParam("page") Integer pageNumber //页数
    ) {

        if(sortWay.compareTo("cited")==0){
            return new Result<SearchResultData>("200","success");
        }
        else if(sortWay.compareTo("time")==0){
            return new Result<SearchResultData>("200","success");
        }
        else{
            Sort.Order order = Sort.Order.desc("cited_quantity");
            List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
            orderList.add(order);
            Sort sort = Sort.by(orderList);
            PageRequest page = PageRequest.of(0, 10 ,sort);
            Iterable<ES_Document> highCitedList = es_documentService.findAll(page);
            List<ES_Document> documentsList = new ArrayList<>();
            highCitedList.forEach(single ->{documentsList.add(single);});
            SearchResultData data=new SearchResultData();
            data.documentList=documentsList;
            return new Result<SearchResultData>("200","success",data);
        }


    }
//
//    @RequestMapping("getById")
//    public Result<Iterable<ES_Document>> getById(String id){
//
//    }
}
