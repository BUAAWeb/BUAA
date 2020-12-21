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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
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
import se.buaa.Entity.Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.FontEntity.*;
import se.buaa.Repository.CollectionRepository;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Service.ES_DocumentService;

//import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


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

    @RequestMapping("test")
    public void test(){
        long total = es_documentDao.count();
        for(int i = 0;i <= (total+1)/2000 ;i++){
            PageRequest page = PageRequest.of(i, 2000);
            Iterable<ES_Document> highCitedList = es_documentDao.findAll(page);
            for(ES_Document es_document : highCitedList) {
                es_document.setViews(0);
                es_documentDao.save(es_document);
            }
        }
//        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
//        // 执行查询
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(queryBuilder);
//        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
//
//        TermsAggregationBuilder tb =  AggregationBuilders.terms("citedQuantity").field("cited_quantity");
//        nativeSearchQueryBuilder.addAggregation(tb);
//        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
//
//        Page<ES_Document> items = es_documentDao.search(nativeSearchQuery);
//
////        Aggregations agg = elasticsearchOperations(nativeSearchQuery, searchResponse -> {
////            Aggregations aggregations = searchResponse.getAggregations();
////            return aggregations;
////        });
//
//        //这里是Storm流的写法，jdk8的新特性
//        items.forEach(System.out::println);
//        return items;
    }

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
    @RequestMapping("getKeyword")
    public Result<Data> getKeyword(String keyword,int pageNumber,String type) {
        Date date1=new Date();
        Sort.Order order = Sort.Order.desc("cited");
        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order1);
        orderList.add(order);
        Sort sort = Sort.by(orderList);

        if(keyword==null)
            return new Result("308", CodeEnum.error.toString());
        List<String> typeList=new ArrayList<>();
        if(type==null){
            typeList.add("期刊");
            typeList.add("图书");
            typeList.add("专利");
            typeList.add("会议");
            typeList.add("期刊");
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
            if(k==false){
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
//        System.out.println(search_word.getStartTime().equals(""));
        int pageNum;
//        System.out.println("kw:" + kw);
//        System.out.println(post.toString());
        SearchWords search_word = post.getSearch_words();
        String page = post.getPage();
        String sort = post.getSort();
        String userID = post.getUserID();
        String type=null;
        System.out.println(
                post.toString()
        );


        if(search_word.getExperts()==null)
            search_word.setExperts("");

        if(search_word.getEndTime() == null || search_word.getEndTime().equals("") || search_word.getEndTime().equals("0"))
            search_word.setEndTime("2020");
        FilterWords filterWords=post.getFilter_words();
        if(filterWords.getYear()!=null&&filterWords.getYear()!="")
            search_word.setStartTime(filterWords.getYear());
        else if(search_word.getStartTime() == null || search_word.getStartTime().equals(""))
            search_word.setStartTime("0");
        if(filterWords.getType()!=null)
            type = filterWords.getType();
        if(search_word.getStartTime() != null && !Pattern.matches("\\d*",search_word.getStartTime()))
            return new Result<Data>(CodeEnum.error.getCode(),CodeEnum.error.toString(),new Data());

        if(search_word.getEndTime() != null && !Pattern.matches("\\d*",search_word.getEndTime()))
            return new Result<Data>(CodeEnum.error.getCode(),CodeEnum.error.toString(),new Data());

        if(page == null)
            return new Result<Data>(CodeEnum.noPage.getCode(),CodeEnum.noPage.toString(),new Data());
        try {
            pageNum = Integer.parseInt(page);
        }
        catch (Exception e) {
            return new Result<Data>(CodeEnum.pageNotInteger.getCode(), CodeEnum.pageNotInteger.toString(), new Data());
        }

        if(pageNum < 1){
            return new Result<Data>(CodeEnum.pageLessThanOne.getCode(),CodeEnum.pageLessThanOne.toString(),new Data());
        }
        List<String> typeList=new ArrayList<>();
        if(type==null||type==""){
            typeList.add("期刊");
            typeList.add("图书");
            typeList.add("专利");
            typeList.add("会议");
            typeList.add("期刊");
        }
        else
            typeList.add(type);
        PageRequest page1;

        if(sort == null)
            page1 = PageRequest.of(pageNum - 1, 10);
        else {
            Sort.Order order;
            switch (sort) {
                case "time":
                    order = Sort.Order.desc("time");
                    break;
                default:
                    order = Sort.Order.desc("cited_quantity");
                    /*
                    *                 case "cited":
                    order = Sort.Order.desc("cited_quantity");
                    break;
                default:
                    order = Sort.Order.desc("cited_quantity");
                    * */
            }
            List<Sort.Order> orderList = new ArrayList<>();
            orderList.add(order);
            Sort sort1 = Sort.by(orderList);
            page1 = PageRequest.of(pageNum - 1, 10, sort1);
            //page1 = PageRequest.of(pageNum - 1, 10);
        }



        int total = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList
        );
        System.out.println(total);
        if( ( total +1 ) / 10 + 1 < pageNum )
            return new Result<Data>(CodeEnum.pageOutOfRange.getCode(),CodeEnum.pageOutOfRange.toString(),new Data());

        Iterable<ES_Document> searchResult = es_documentDao.findByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                page1,search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList,
                search_word.getKw(),search_word.getExperts(),search_word.getOrigin(),search_word.getStartTime(),search_word.getEndTime(),typeList
        );

        Data data = new Data();
        List<ES_Document> documentsList = new ArrayList<>();
        searchResult.forEach(single ->{documentsList.add(single);});
        if(userID!=null){
            int l=documentsList.size();
            for(int i=0;i<l;i++){
                String doc_id=documentsList.get(i).getId();
                CollectionKey ck =new CollectionKey(Integer.parseInt(userID),doc_id);
                Optional<Collection> res = collectionRepository.findById(ck);
                if(res.isPresent())
                {
                    documentsList.get(i).setIs_favor(true);
                }
                else
                {
                    documentsList.get(i).setIs_favor(false);
                }
            }
        }

        data.setResult_list(documentsList);
        data.setTotal(total);

        String year=search_word.getEndTime();
        Filter filter=getTimeFilter(search_word,typeList);
        data.filter_list.add(filter);
        filter=getTypeFilter(search_word,typeList);
        data.filter_list.add(filter);
        return new Result<Data>(CodeEnum.success.getCode(),CodeEnum.success.toString(),data);
//        data.setTotal();
//        if(sortWay.compareTo("cited")==0){
//            return new Result<SearchResultData>("200","success");
//        }
//        else if(sortWay.compareTo("time")==0){
//            return new Result<SearchResultData>("200","success");
//        }
//        else{
//            Sort.Order order = Sort.Order.desc("cited_quantity");
//            List<Sort.Order> orderList = new ArrayList<>();
////        orderList.add(order1);
//            orderList.add(order);
//            Sort sort = Sort.by(orderList);
//            PageRequest page = PageRequest.of(0, 10 ,sort);
//            Iterable<ES_Document> highCitedList = es_documentService.findAll(page);
//            List<ES_Document> documentsList = new ArrayList<>();
//            highCitedList.forEach(single ->{documentsList.add(single);});
//            SearchResultData data=new SearchResultData();
//            data.documentList=documentsList;
//            return new Result<SearchResultData>("200","success",data);
//        }
    }
    private Filter getTimeFilter(SearchWords searchWords,List<String> typeList){
        Filter filter=new Filter();
        filter.filter_name="year";
        filter.title="时间";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String year=searchWords.getEndTime();
        if(year==null)
            year = simpleDateFormat.format(new Date()).substring(0,4);
        year=year.substring(0,4);
        String startYear=searchWords.getStartTime();
        Integer start=Integer.parseInt(startYear);

        if(startYear!=null)
            start=Integer.parseInt(startYear);
        else
            start=1970;
        Integer  year1=Integer.parseInt(year);
        Integer  year2=year1-1;
        Integer  year3=year1-2;
        Integer  year4=year1-5;
        Integer  year5=year1-10;
        int count1 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year1.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year1.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year1.toString(),searchWords.getEndTime(),typeList
        );
        int count2 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year2.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year2.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year2.toString(),searchWords.getEndTime(),typeList
        );
        int count3 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year3.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year3.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year3.toString(),searchWords.getEndTime(),typeList
        );
        int count4 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year4.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year4.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year4.toString(),searchWords.getEndTime(),typeList
        );
        int count5 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year5.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year5.toString(),searchWords.getEndTime(),typeList,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),year5.toString(),searchWords.getEndTime(),typeList
        );
        Filter_Item filter_item1=new Filter_Item();
        Filter_Item filter_item2=new Filter_Item();
        Filter_Item filter_item3=new Filter_Item();
        Filter_Item filter_item4=new Filter_Item();
        Filter_Item filter_item5=new Filter_Item();
        filter_item1.content=year1.toString();
        filter_item1.count=count1;
        filter_item2.content=year2.toString();
        filter_item2.count=count2;
        filter_item3.content=year3.toString();
        filter_item3.count=count3;
        filter_item4.content=year4.toString();
        filter_item4.count=count4;
        filter_item5.content=year5.toString();
        filter_item5.count=count5;
        if(start<=year1)
            filter.filter_itemList.add(filter_item1);
        if(start<=year2)
            filter.filter_itemList.add(filter_item2);
        if(start<=year3)
            filter.filter_itemList.add(filter_item3);
        if(start<=year4)
            filter.filter_itemList.add(filter_item4);
        if(start<=year5)
            filter.filter_itemList.add(filter_item5);
        return filter;
    }
    private Filter getTypeFilter(SearchWords searchWords,List<String> typeList){// TODO: 2020-12-18 filter还没有传并接受
        Filter filter=new Filter();
        filter.filter_name="type";
        filter.title="种类";
        List<String> typeList1=new ArrayList<>();
        typeList1.add("期刊");
        List<String> typeList2=new ArrayList<>();
        typeList2.add("会议");
        List<String> typeList3=new ArrayList<>();
        typeList3.add("专利");
        List<String> typeList4=new ArrayList<>();
        typeList4.add("图书");
        List<String> typeList5=new ArrayList<>();
        typeList5.add("学位");

        int count1 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList1,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList1,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList1
        );
        int count2 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList2,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList2,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList2
        );
        int count3 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList3,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList3,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList3
        );
        int count4 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList4,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList4,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList4
        );
        int count5 = es_documentDao.countByTitleInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrSummaryInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeInOrKeywordsInAndExpertsLikeAndOriginLikeAndTimeBetweenAndDtypeIn(
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList5,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList5,
                searchWords.getKw(),searchWords.getExperts(),searchWords.getOrigin(),searchWords.getStartTime(),searchWords.getEndTime(),typeList5
        );
        Filter_Item filter_item1=new Filter_Item();
        Filter_Item filter_item2=new Filter_Item();
        Filter_Item filter_item3=new Filter_Item();
        Filter_Item filter_item4=new Filter_Item();
        Filter_Item filter_item5=new Filter_Item();
        filter_item1.content="期刊";
        filter_item1.count=count1;
        filter_item2.content="会议";
        filter_item2.count=count2;
        filter_item3.content="专利";
        filter_item3.count=count3;
        filter_item4.content="图书";
        filter_item4.count=count4;
        filter_item5.content="学位";
        filter_item5.count=count5;
        if(typeList.size()==5){
            filter.filter_itemList.add(filter_item1);
            filter.filter_itemList.add(filter_item2);
            filter.filter_itemList.add(filter_item3);
            filter.filter_itemList.add(filter_item4);
            filter.filter_itemList.add(filter_item5);
        }
        else if(typeList.get(0).compareTo("期刊")==0){
            filter.filter_itemList.add(filter_item1);
        }
        else if(typeList.get(0).compareTo("会议")==0){
            filter.filter_itemList.add(filter_item2);
        }
        else if(typeList.get(0).compareTo("专利")==0){
            filter.filter_itemList.add(filter_item3);
        }
        else if(typeList.get(0).compareTo("图书")==0){
            filter.filter_itemList.add(filter_item4);
        }
        else if(typeList.get(0).compareTo("学位")==0){
            filter.filter_itemList.add(filter_item5);
        }
        return filter;
    }
    @RequestMapping("getById")
    public Result<ES_Document> getById(String document_id,int user_id){
        if(document_id == null)
            return new Result<>(CodeEnum.docIdNotExist.getCode(), CodeEnum.docIdNotExist.toString(),null);
        ES_Document es_document = es_documentDao.findByDocumentid(document_id);
        if(es_document == null)
            return new Result<>(CodeEnum.documentNotExist.getCode(), CodeEnum.documentNotExist.toString(),null);
        else{
            CollectionKey collectionKey=new CollectionKey(user_id,document_id);
            Optional<Collection> res = collectionRepository.findById(collectionKey);
            if(res.isPresent()){
                es_document.setIs_favor(true);
            }
            else {
                es_document.setIs_favor(false);
            }
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
