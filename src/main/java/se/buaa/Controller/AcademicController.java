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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;
import se.buaa.Config.JwtUtils;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Dao.ES_ExpertDao;
import se.buaa.Entity.Collection;
import se.buaa.Entity.CollectionKey;
import se.buaa.Entity.Data.Data;
import se.buaa.Entity.Data.SearchResultData;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.ESDocument.ES_Expert;
import se.buaa.Entity.Enumeration.CodeEnum;
import se.buaa.Entity.Response.Result;
import se.buaa.FontEntity.*;
import se.buaa.Repository.CollectionRepository;
import se.buaa.Service.ES_DocumentService;

//import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.*;


//@CrossOrigin
//@CrossOrigin(allowCredentials="false")
@RestController
@RequestMapping("/academic")
public class AcademicController {
    @Autowired
    ES_DocumentDao es_documentDao;

    @Autowired
    ES_DocumentService es_documentService;

    @Autowired
    ES_ExpertDao es_expertDao;

    @Autowired
    CollectionRepository collectionRepository;

    public static List<String> typeListDefault = new ArrayList<>();

    static int pageSize = 10;

    @RequestMapping("test")
    public void test(String keywords,String experts,String type,String sort,int page){
        Map<String,String> searchMap = new HashMap<>();
        searchMap.put("keywords",keywords);
        searchMap.put("experts",experts);
        searchMap.put("startYear","0");
        searchMap.put("endYear","2020");
        searchMap.put("sort",sort);
        List<String> typeList = getTypeList(type);

        Data data = getSearchResult(searchMap, typeList,page);

        System.out.println(data.total);
        for(ES_Document es_document : data.getResult_list()){
            System.out.println(es_document.getDtype());
        }
//        String field = "experts";
//        String keyword = "*张伟*";
//        String time = "time";
////        BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder().should(QueryBuilders);
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        boolQueryBuilder.must(QueryBuilders.wildcardQuery(field, keyword));
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("*计算机*",
//                "title","summary");
//        boolQueryBuilder.must(queryBuilder);
//        QueryBuilder queryBuilder1 = QueryBuilders.rangeQuery("time").from("2000").to("2020")
//                .includeUpper(true).includeLower(true);
//        boolQueryBuilder.must(queryBuilder);
//        boolQueryBuilder.must(queryBuilder1);
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(boolQueryBuilder)
//                .withPageable(PageRequest.of(0, 100))
//                .build();
////        long total  = es_documentDao.count();
//        Page<ES_Document> es_documents = es_documentDao.search(searchQuery);
//        System.out.println("total:" + es_documents.getTotalElements());
//        List<ES_Document> es_documentList = es_documents.toList();
//        for(ES_Document es_document : es_documentList)
//            System.out.println(es_document.getExperts() + "  title:" + es_document.getTitle() + "  summary:" + es_document.getSummary());
////        PageRequest page = PageRequest.of(0, 200);
////        String experts1 = "张伟";
////        String experts2 = ",张伟,";
////        String experts3 = "*张伟*";
////
////        for (int i = 0 ;i<5;i++){
////            System.out.println(i + " :   experts:" + experts1);
////            test1(i+1,experts1);
////            System.out.println(i + " :   experts:" + experts2);
////            test1(i+1,experts2);
////            System.out.println(i + " :   experts:" + experts3);
////            test1(i+1,experts3);
////        }
//
//
//
//
////        WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder()
////        long total = es_documentDao.count();
////        System.out.println("total: " + total);
////        for(int i = 0;i <= 1000 ;i++){
////            PageRequest page = PageRequest.of(i, 100);
////            Iterable<ES_Document> highCitedList = es_documentDao.findAll(page);
////            System.out.println("page:" + i + 1);
////            for(ES_Document es_document : highCitedList) {
////                System.out.println(es_document.toString());
//////                es_document.setViews(0);
////                es_documentDao.save(es_document);
////            }
////        }
////        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
////        // 执行查询
////        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
////        nativeSearchQueryBuilder.withQuery(queryBuilder);
////        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
////
////        TermsAggregationBuilder tb =  AggregationBuilders.terms("citedQuantity").field("cited_quantity");
////        nativeSearchQueryBuilder.addAggregation(tb);
////        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
////
////        Page<ES_Document> items = es_documentDao.search(nativeSearchQuery);
////
//////        Aggregations agg = elasticsearchOperations(nativeSearchQuery, searchResponse -> {
//////            Aggregations aggregations = searchResponse.getAggregations();
//////            return aggregations;
//////        });
////
////        //这里是Storm流的写法，jdk8的新特性
////        items.forEach(System.out::println);
////        return items;
    }


//    @RequestMapping("test")
//    public void test(){
//        long total = es_documentDao.count();
//        for(int i = 0;i <= (total+1)/2000 ;i++){
//            PageRequest page = PageRequest.of(i, 2000);
//            Iterable<ES_Document> highCitedList = es_documentDao.findAll(page);
//            for(ES_Document es_document : highCitedList) {
//                es_document.setViews(0);
//                es_documentDao.save(es_document);
//            }
//        }
////        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
////        // 执行查询
////        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
////        nativeSearchQueryBuilder.withQuery(queryBuilder);
////        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
////
////        TermsAggregationBuilder tb =  AggregationBuilders.terms("citedQuantity").field("cited_quantity");
////        nativeSearchQueryBuilder.addAggregation(tb);
////        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
////
////        Page<ES_Document> items = es_documentDao.search(nativeSearchQuery);
////
//////        Aggregations agg = elasticsearchOperations(nativeSearchQuery, searchResponse -> {
//////            Aggregations aggregations = searchResponse.getAggregations();
//////            return aggregations;
//////        });
////
////        //这里是Storm流的写法，jdk8的新特性
////        items.forEach(System.out::println);
////        return items;
//    }

    @RequestMapping("getHighCited")
    public Result<Data> getHighCited() {
        Sort.Order order = Sort.Order.desc("time");
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order);
        Sort sort = Sort.by(orderList);
        PageRequest page = PageRequest.of(0, 20,sort);
        Iterable<ES_Document> highCitedList = es_documentService.findAll(page);
        List<ES_Document> documentsList = new ArrayList<>();
        highCitedList.forEach(single ->{documentsList.add(single);});
//        for(ES_Document es_document : highCitedList){
//            System.out.println(es_document.getAuthors());
//        }
//        for(ES_Document es_document : documentsList){
//            String experts = es_document.getExperts();
//            String[] authorNames = experts.split(",");
//            List<ES_Expert> es_experts = new ArrayList<>();
//            for(String name : authorNames){
//                ES_Expert expert = new ES_Expert();
//                List<ES_Expert> temp =  es_expertDao.findByName(name);
//                if(temp == null || temp.size() == 0) {
//                    expert.setName(name);
//                }
//                else
//                    expert = temp.get(0);
//                es_experts.add(expert);
//
//            }
////        System.out.print("1");
//            es_document.setAuthors(es_experts);
//        }
        Data data = new Data();
        data.setResult_list(documentsList);
        return new Result("200", CodeEnum.success.toString(), data);

    }

    @RequestMapping("getExpert")
    public Result<Data> getExpert(String expertName,int pageNumber) {
        Date date1=new Date();
        Sort.Order order = Sort.Order.desc("time");
        System.out.println(expertName);
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order);
        Sort sort = Sort.by(orderList);

        if(expertName==null)
            return new Result("308", CodeEnum.error.toString());
        List<String> expertsName=new ArrayList<>();
//        expertsName.add("，"+expertName+"，");
//        expertsName.add(","+expertName+",");
//        expertsName.add(expertName+"，");
//        expertsName.add(expertName+",");
//        expertsName.add("，"+expertName);
//        expertsName.add(","+expertName);
        expertsName.add(expertName);

//        Iterable<ES_Document> highCitedList = es_documentDao.findByExpertsLikeAndExperts(page,expertsName,expertName);
//        List<ES_Document> documentsList = es_documentDao.findByAuthors(page,expertName);
        List<ES_Document> documentsList = new ArrayList<>();
        List<ES_Document> es_documentList = new ArrayList<>();
        es_documentList=es_documentDao.findByExpertsIn(expertsName);

        Data data = new Data();
        data.total=es_documentList.size();
        if(data.total<10*pageNumber-10)
            return new Result("300", CodeEnum.pageOutOfRange.toString(), data);
        if(data.total<10*pageNumber-1)
            data.setResult_list(documentsList.subList(10*pageNumber-10,data.total));
        else
            data.setResult_list(documentsList.subList(10*pageNumber-10,10*pageNumber-1));


        Date date2=new Date();
        data.time= (int) (date2.getTime( )-date1.getTime());
        return new Result("200", CodeEnum.success.toString(), data);

    }

    Data getSearchResult(SearchWords searchWords,String sort,List<String> typeList, int pageNum){
        Data data = new Data();
        String searchWords1 = searchWords.getSearchWords();
        String title = searchWords.getTitle();
        String keywords = searchWords.getKeywords();
        String startYear = searchWords.getStartTime();
        String endYear = searchWords.getEndTime();
        String experts = searchWords.getExperts();
        String origin = searchWords.getOrigin();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if(searchWords1 != null&& !searchWords1.equals("")){
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keywords,//keywords,//"*" + keywords + "*",
                    "title.keyword","summary.keyword");
            boolQueryBuilder.must(queryBuilder);
        }
        if(title != null&& !title.equals("")){
            QueryBuilder queryBuilder = QueryBuilders.wildcardQuery( "title","*" + origin + "*");
            boolQueryBuilder.must(queryBuilder);
        }
        if(keywords != null&& !keywords.equals("")){
            QueryBuilder queryBuilder = QueryBuilders.wildcardQuery( "title","*" + origin + "*");//es still has some problems,so we can't search keywords now!
            boolQueryBuilder.must(queryBuilder);
        }
        if(startYear != null && endYear != null){
            QueryBuilder queryBuilder = QueryBuilders.rangeQuery("time").from(startYear).to(endYear)
                    .includeUpper(true).includeLower(true);
            boolQueryBuilder.must(queryBuilder);
        }
        if(experts != null&& !experts.equals("")){
            QueryBuilder queryBuilder;
            experts  = experts.replaceAll("[,，\\s;.。]+","*");
            System.out.println(experts);
            queryBuilder = QueryBuilders.wildcardQuery( "experts","*" + experts + "*");
            boolQueryBuilder.must(queryBuilder);
        }
        if(origin != null&& !origin.equals("")){
            QueryBuilder queryBuilder = QueryBuilders.wildcardQuery( "origin","*" + origin + "*");
            boolQueryBuilder.must(queryBuilder);
        }
        if(typeList.size() == 1){
            QueryBuilder queryBuilder = QueryBuilders.matchQuery("dtype", typeList.get(0));
            boolQueryBuilder.must(queryBuilder);
        }

        Sort sort1 = getSort(sort);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(pageNum - 1, pageSize,sort1))
                .build();

        Page<ES_Document> es_documents = es_documentDao.search(searchQuery);
        List<ES_Document> es_documentList = es_documents.toList();
        //如果只搜作者，添加返回作者列表
        if((keywords==null||keywords=="")&&experts!=null&&experts!=""){// TODO: 2020-12-22 org
            List<String> expertList=new ArrayList<>();
            expertList.add(experts);
            PageRequest page = PageRequest.of(1, 6);
            Page<ES_Expert> es_experts = es_expertDao.findByNameIn(page,experts);
            List<ES_Expert> es_expertList = es_experts.toList();
            data.setExpert_list(es_expertList);
        }
        data.setTotal((int) es_documents.getTotalElements());
        data.setResult_list(es_documentList);
        return data;
    }

    Data getSearchResult(Map<String,String> searchMap,List<String> typeList, int pageNum){
        Data data = new Data();
        String keywords = searchMap.get("keywords");
        String startYear = searchMap.get("startYear");
        String endYear = searchMap.get("endYear");
        String experts = searchMap.get("experts");
        String origin = searchMap.get("origin");
        String sort = searchMap.get("sort");

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if(keywords != null){
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("*" + keywords + "*",//keywords,//"*" + keywords + "*",
                    "title","summary");
            boolQueryBuilder.must(queryBuilder);
        }
        if(startYear != null && endYear != null){
            QueryBuilder queryBuilder = QueryBuilders.rangeQuery("time").from(startYear).to(endYear)
                    .includeUpper(true).includeLower(true);
            boolQueryBuilder.must(queryBuilder);
        }
        if(experts != null){
            QueryBuilder queryBuilder;
            experts  = experts.replaceAll("[,，\\s;.。]+","*");
//            System.out.println(experts);
            queryBuilder = QueryBuilders.wildcardQuery( "experts","*" + experts + "*");
            boolQueryBuilder.must(queryBuilder);
        }
        if(origin != null){
            QueryBuilder queryBuilder = QueryBuilders.wildcardQuery( "origin","*" + origin + "*");
            boolQueryBuilder.must(queryBuilder);
        }
        if(typeList.size() == 1){
            QueryBuilder queryBuilder = QueryBuilders.matchQuery("dtype", typeList.get(0));
            boolQueryBuilder.must(queryBuilder);
        }

        Sort sort1 = getSort(sort);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(pageNum - 1, pageSize,sort1))
                .build();

        Page<ES_Document> es_documents = es_documentDao.search(searchQuery);
        List<ES_Document> es_documentList = es_documents.toList();

        data.setTotal((int) es_documents.getTotalElements());
        data.setResult_list(es_documentList);
        return data;
    }

    @RequestMapping("getKeyword")
    public Result<Data> getKeyword(String keyword,int pageNumber,String type) {
        Date date1=new Date();
        Sort.Order order = Sort.Order.desc("cited");
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order);
        Sort sort = Sort.by(orderList);

        if(keyword == null)
            return new Result("308", CodeEnum.error.toString());
        List<String> typeList=new ArrayList<>();
        if(type == null){
            typeList = typeListDefault;
        }
        else
            typeList.add(type);

        PageRequest page= PageRequest.of(pageNumber-1,10);
        List<ES_Document> documentsList = new ArrayList<>();
        Iterable<ES_Document> highCitedList = es_documentDao.findByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                page,keyword,"","","1970","2020",typeList,
                keyword,"","","1970","2020",typeList,
                keyword,"","","1970","2020",typeList
        );
        highCitedList.forEach(single ->{documentsList.add(single);});

        Data data = new Data();
        data.total=es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                keyword,"","","1970","2020",typeList,
                keyword,"","","1970","2020",typeList,
                keyword,"","","1970","2020",typeList
        );
        data.result_list=documentsList;
        Date date2=new Date();
        data.time= (int) (date2.getTime( )-date1.getTime());
        return new Result("200", CodeEnum.success.toString(), data);

    }
    private List<ES_Document> clean(List<ES_Document> es_documents,String experts) {
        boolean k=false;
        int l=es_documents.size();
        for (int i=0;i<l;i++){
            k=false;
            int l2=es_documents.get(i).getAuthors().size();
            for(int j=0;j<l2;j++){
                if(es_documents.get(i).getAuthors().get(j).getName().compareTo(experts)==0) {
                    k = true;
                    break;
                }
            }
            if(!k){
                es_documents.remove(i);
                i--;
                l--;
            }

        }
        return es_documents;
    }

    //{a=1, b=2, c=3} 格式转换成map
    private static Map<String, String> mapStringToMap(String str) {
        str = str.replaceAll("\"","");
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split(":")[0].trim();
            String value = string.split(":").length == 1 ? null:string.split(":")[1];
            map.put(key, value);
        }
        return map;
    }

    public CodeEnum checkSearchInput(Post post){
        if(post.getPage() == null)
            post.setPage("1");

        if(post.getUserID() == null )
            return CodeEnum.noUser;

        if(post.getSort() == null)
            return CodeEnum.noSort;

        return  CodeEnum.success;
    }

    public CodeEnum handleSearchInput(Post post){
        try {
            Integer.parseInt(post.getPage());
        }
        catch (Exception e) {
            return CodeEnum.pageNotInteger;
        }

        SearchWords searchWords = post.getSearch_words();

        if(searchWords.getExperts() == null)
            searchWords.setExperts("");

        if(searchWords.getEndTime() == null || searchWords.getEndTime().equals("") || searchWords.getEndTime().equals("0"))
            searchWords.setEndTime("2020");

        FilterWords filterWords = post.getFilter_words();

        String year = filterWords.getYear();

        if(year != null ){
            if(!"".equals(year)) {
                try {
                    Integer.parseInt(year);
                }
                catch (Exception e) {
                    return CodeEnum.yearNotInteger;
                }
                searchWords.setStartTime(filterWords.getYear());
            }
        }
        else if(searchWords.getStartTime() == null || searchWords.getStartTime().equals(""))
            searchWords.setStartTime("0");

        try {
            Integer.parseInt(searchWords.getStartTime());
            Integer.parseInt(searchWords.getEndTime());
        }
        catch (Exception e) {
            return CodeEnum.yearNotInteger;
        }

        return  CodeEnum.success;
    }

    public CodeEnum checkAndHandleSearchInput(Post post){
        CodeEnum result = checkSearchInput(post);

        if(result != CodeEnum.success)
            return result;

        return handleSearchInput(post);
    }

    public Sort getSort(String sort){
        Sort.Order order;
        switch (sort) {
            case "time":
                order = Sort.Order.desc("time"); break;
            case "cited":
                order = Sort.Order.desc("cited_quantity"); break;
            default:
                order = Sort.Order.desc("views");
        }
        List<Sort.Order> orderList = new ArrayList<>();
        orderList.add(order);
        Sort sort1 = Sort.by(orderList);
        return sort1;
    }

    public List<String> getTypeList(String type){
        List<String> typeList = new ArrayList<>();
        if(type == null || type.equals("")) typeList = typeListDefault;
        else typeList.add(type);
        return typeList;

    }

    @PostMapping("getSearchResult")
    public Result<Data> getSearchResult(@RequestBody Post post//, String sort, String page, String userID, HttpServletRequest request
//            @RequestParam String kw,//keyword
//                                                         @RequestParam String experts,//author
//                                                         @RequestParam String origin,
//                                                         @RequestParam String startTime,
//                                                         @RequestParam String endTime,
//                                                         @RequestParam("sort") String sortWay, //排序方式
//                                                         @RequestParam("page") Integer pageNumber //页数
    ) {
        //判断输入是否合法，并处理相关数据
        CodeEnum result = checkAndHandleSearchInput(post);
        if(result != CodeEnum.success) return new Result<>(result.getCode(),result.toString(),new Data());

//        System.out.println(post.toString());
        //
        Date date1=new Date();
        //变量声明
        int pageNum;
        String page = post.getPage();
        String sort = post.getSort();
        String userID = post.getUserID();
        SearchWords search_word = post.getSearch_words();
        FilterWords filterWords=post.getFilter_words();
        List<String> typeList = getTypeList(filterWords.getType());

        pageNum = Integer.parseInt(page);
        if(pageNum < 1) return new Result<>(CodeEnum.pageLessThanOne.getCode(),CodeEnum.pageLessThanOne.toString(),new Data());

        Data data = getSearchResult(search_word,sort,typeList,pageNum);
        int total = data.total;
//        List<String> typeList = getTypeList(filterWords.getType());
//
//        Sort sort1 = getSort(sort);
//        PageRequest page1 = PageRequest.of(pageNum - 1, pageSize, sort1);

//        int total = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
//                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
//                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList
//        );
//        System.out.println(total);
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        if(totalPage == 0&&data.getExpert_list().size()==0)
            return new Result<Data>(CodeEnum.noResult.getCode(),CodeEnum.noResult.toString(),new Data());
        if( totalPage < pageNum &&data.getExpert_list().size()==0)
            return new Result<Data>(CodeEnum.pageOutOfRange.getCode(),CodeEnum.pageOutOfRange.toString(),new Data());

        for (ES_Document es_document : data.getResult_list()) {
            String doc_id = es_document.getId();
            CollectionKey ck = new CollectionKey(Integer.parseInt(userID), doc_id);
            Optional<Collection> res = collectionRepository.findById(ck);
            es_document.setIs_favor(res.isPresent());
        }
//        Iterable<ES_Document> searchResult = es_documentDao.findByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                page1,search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
//                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
//                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList
//        );

//        List<ES_Document> documentsList = new ArrayList<>();
//        searchResult.forEach(single ->{documentsList.add(single);});
//
//        for (ES_Document es_document : documentsList) {
//            String doc_id = es_document.getId();
//            CollectionKey ck = new CollectionKey(Integer.parseInt(userID), doc_id);
//            Optional<Collection> res = collectionRepository.findById(ck);
//            es_document.setIs_favor(res.isPresent());
//        }
//
//        data.setResult_list(documentsList);
//        data.setTotal(total);
        data.filter_list.add(getTimeFilter(search_word,typeList));
        data.filter_list.add(getTypeFilter(search_word,typeList));
        Date date2=new Date();


        data.time= (int) (date2.getTime()-date1.getTime());

        return new Result<>(CodeEnum.success.getCode(),CodeEnum.success.toString(),data);
    }
    private Filter getTimeFilter(SearchWords searchWords,List<String> typeList){
        Filter filter=new Filter();
        filter.filter_name = "year";
        filter.title = "时间";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String year = searchWords.getEndTime();
        if(year == null || year.length() < 4)
            year = simpleDateFormat.format(new Date()).substring(0,4);
        year = year.substring(0,4);
        String startYear = searchWords.getStartTime();
        int start = Integer.parseInt(startYear);

        int[] years = new int[5];
        years[0] = Integer.parseInt(year);
        years[1] = years[0] - 1;
        years[2] = years[0] - 2;
        years[3]  = years[0] - 5;
        years[4]  = years[0] - 10;
        SearchWords searchWords1  = new SearchWords();
        BeanUtils.copyProperties(searchWords, searchWords1);
        for(int i = 0; i < 5 ;i++){
            searchWords1.setStartTime(String.valueOf(years[i]));
            Filter_Item filter_item = new Filter_Item();
            filter_item.content = String.valueOf(years[i]);
            filter_item.count = getSearchResult(searchWords1,"cited",typeList,1).getTotal();
            if(start <= years[i])
                filter.filter_itemList.add(filter_item);
        }
        return filter;
//        int count1 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year1.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year1.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year1.toString(),searchWords.getEndTime(),typeList
//        );
//        int count2 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year2.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year2.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year2.toString(),searchWords.getEndTime(),typeList
//        );
//        int count3 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year3.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year3.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year3.toString(),searchWords.getEndTime(),typeList
//        );
//        int count4 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year4.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year4.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year4.toString(),searchWords.getEndTime(),typeList
//        );
//        int count5 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year5.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year5.toString(),searchWords.getEndTime(),typeList,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year5.toString(),searchWords.getEndTime(),typeList
//        );
//        Filter_Item filter_item1=new Filter_Item();
//        Filter_Item filter_item2=new Filter_Item();
//        Filter_Item filter_item3=new Filter_Item();
//        Filter_Item filter_item4=new Filter_Item();
//        Filter_Item filter_item5=new Filter_Item();
//        filter_item1.content=year1.toString();
//        filter_item1.count=count1;
//        filter_item2.content=year2.toString();
//        filter_item2.count=count2;
//        filter_item3.content=year3.toString();
//        filter_item3.count=count3;
//        filter_item4.content=year4.toString();
//        filter_item4.count=count4;
//        filter_item5.content=year5.toString();
//        filter_item5.count=count5;
//        if(start<=year1)
//            filter.filter_itemList.add(filter_item1);
//        if(start<=year2)
//            filter.filter_itemList.add(filter_item2);
//        if(start<=year3)
//            filter.filter_itemList.add(filter_item3);
//        if(start<=year4)
//            filter.filter_itemList.add(filter_item4);
//        if(start<=year5)
//            filter.filter_itemList.add(filter_item5);
    }
    private Filter getTypeFilter(SearchWords searchWords,List<String> typeList){// TODO: 2020-12-18 filter还没有传并接受
        String[] type = {
                "期刊",
                "会议",
                "专利",
                "图书",
                "学位"
        };

        Filter filter=new Filter();
        filter.filter_name="type";
        filter.title="种类";

        if(typeList.size() == 5) {
            for (int i = 0; i < 5; i++) {
                Filter_Item filter_item = new Filter_Item();
                filter_item.count = getSearchResult(searchWords,"cited",getTypeList(typeList.get(i)),1).getTotal();
                filter_item.content = typeList.get(i);
                filter.filter_itemList.add(filter_item);
            }
        }
        else{
            Filter_Item filter_item = new Filter_Item();
            filter_item.count = getSearchResult(searchWords,"cited",getTypeList(typeList.get(0)),1).getTotal();
            filter_item.content = typeList.get(0);
            filter.filter_itemList.add(filter_item);
        }
        return filter;

//        List<String> typeList1=new ArrayList<>();
//        typeList1.add("期刊");
//        List<String> typeList2=new ArrayList<>();
//        typeList2.add("会议");
//        List<String> typeList3=new ArrayList<>();
//        typeList3.add("专利");
//        List<String> typeList4=new ArrayList<>();
//        typeList4.add("图书");
//        List<String> typeList5=new ArrayList<>();
//        typeList5.add("学位");
//
//        int count1 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList1,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList1,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList1
//        );
//        int count2 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList2,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList2,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList2
//        );
//        int count3 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList3,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList3,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList3
//        );
//        int count4 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList4,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList4,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList4
//        );
//        int count5 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList5,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList5,
//                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList5
//        );
//        Filter_Item filter_item1=new Filter_Item();
//        Filter_Item filter_item2=new Filter_Item();
//        Filter_Item filter_item3=new Filter_Item();
//        Filter_Item filter_item4=new Filter_Item();
//        Filter_Item filter_item5=new Filter_Item();
//        filter_item1.content="期刊";
//        filter_item1.count=count1;
//        filter_item2.content="会议";
//        filter_item2.count=count2;
//        filter_item3.content="专利";
//        filter_item3.count=count3;
//        filter_item4.content="图书";
//        filter_item4.count=count4;
//        filter_item5.content="学位";
//        filter_item5.count=count5;
//        if(typeList.size()==5){
//            filter.filter_itemList.add(filter_item1);
//            filter.filter_itemList.add(filter_item2);
//            filter.filter_itemList.add(filter_item3);
//            filter.filter_itemList.add(filter_item4);
//            filter.filter_itemList.add(filter_item5);
//        }
//        else if(typeList.get(0).compareTo("期刊")==0){
//            filter.filter_itemList.add(filter_item1);
//        }
//        else if(typeList.get(0).compareTo("会议")==0){
//            filter.filter_itemList.add(filter_item2);
//        }
//        else if(typeList.get(0).compareTo("专利")==0){
//            filter.filter_itemList.add(filter_item3);
//        }
//        else if(typeList.get(0).compareTo("图书")==0){
//            filter.filter_itemList.add(filter_item4);
//        }
//        else if(typeList.get(0).compareTo("学位")==0){
//            filter.filter_itemList.add(filter_item5);
//        }
    }
    @RequestMapping("getById")
    public Result<ES_Document> getById(String document_id,String user_id){
        if(user_id == null || user_id.equals("-1"))
            return new Result<>(CodeEnum.noUser.getCode(), CodeEnum.noUser.toString(),null);
        if(document_id == null)
            return new Result<>(CodeEnum.docIdNotExist.getCode(), CodeEnum.docIdNotExist.toString(),null);
        ES_Document es_document = es_documentDao.findByDocumentid(document_id);
        if(es_document == null)
            return new Result<>(CodeEnum.documentNotExist.getCode(), CodeEnum.documentNotExist.toString(),null);
        else{
            CollectionKey collectionKey;
            try {
                collectionKey = new CollectionKey(Integer.parseInt(user_id), document_id);
            }
            catch (Exception e){
                return new Result<>(CodeEnum.noUser.getCode(), CodeEnum.noUser.toString(),null);
            }

            Optional<Collection> res = collectionRepository.findById(collectionKey);
            es_document.setIs_favor(res.isPresent());
            return new Result<>(CodeEnum.success.getCode(), CodeEnum.success.toString(),es_document);
        }

    }

    @GetMapping("favorSc")
    public Result<Boolean> favorSc(@RequestParam("document_id") String doc_id,@RequestParam("user_id") int user_id,@RequestParam("token") String token){
        if (JwtUtils.verifyToken(token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        CollectionKey ck =new CollectionKey(user_id,doc_id);
        Optional<Collection> res = collectionRepository.findById(ck);
        if(res.isPresent())
        {
            collectionRepository.deleteById(ck);
            return Result.Success(false);
        }
        else
        {
            Collection collection = new Collection(ck);
            collectionRepository.save(collection);
            return Result.Success(true);
        }

    }
}
