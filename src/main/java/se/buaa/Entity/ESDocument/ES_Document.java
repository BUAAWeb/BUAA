package se.buaa.Entity.ESDocument;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.sonatype.guice.bean.reflect.IgnoreSetters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Controller;
import se.buaa.Controller.AcademicController;
import se.buaa.Dao.ES_ExpertDao;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Relation.Document_Expert;
import se.buaa.Repository.Docu_ExpertRepository;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Util.SpringContextUtil;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Data
@Document(indexName = "document3",indexStoreType = "doc")
public class ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String documentid;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String title;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String dtype;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Field(index = true, type = FieldType.Keyword)
    private String experts;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String keywords;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//摘要
    private String summary;
    @Field(analyzer = "ik_smart", type = FieldType.Integer)//被引次数
    private int cited_quantity;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//link
    private String link;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//link
    private String origin;
    @Field(analyzer = "ik_smart", type = FieldType.Integer)//link
    private int views;
//    @Field(type = FieldType.Date)//link
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String time;
    private boolean is_favor=false;

    public void setAuthors(List<ES_Expert> authors) {
        this.authors = authors;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCited_quantity(int cited_quantity) {
        this.cited_quantity = cited_quantity;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIs_favor(boolean is_favor) {
        this.is_favor = is_favor;
    }

    public void setViews(int views) {
        this.views = views;
    }



    public String getId() {
        return id;
    }

    public String getDocumentid() {
        return documentid;
    }

    public String getTitle() {
        return title;
    }

    public String getExperts() {
        return experts;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getSummary() {
        return summary;
    }

    public int getCited_quantity() {
        return cited_quantity;
    }

    public String getLink() {
        return link;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTime() {
        return time;
    }

    public boolean isIs_favor() {
        return is_favor;
    }

    public List<ES_Expert> getAuthors() {
        return authors;
    }

    public int getViews() {
        return views;
    }
    @Transient
    private List<ES_Expert> authors = new ArrayList<>();
    @Transient
    private List<String> keywordList = new ArrayList<>();
//    @Transient
//    public Docu_ExpertRepository docu_expertRepository;
//    @Transient
//    public ES_ExpertDao es_expertDao;

    @PersistenceConstructor
    public ES_Document(String dtype,String id, String title, String experts, String keywords, String summary,
                       String link, int cited_quantity, String origin,
                       String time){
        this.documentid = id;
        this.title = title;
        this.experts = experts;
        this.keywords = keywords;
        this.summary = summary;
        this.link = link;
        this.cited_quantity = cited_quantity;
        this.time = time;
        this.dtype = dtype;
        String[] authorNames = experts.split("[,，\\s;.。、]+");
        this.authors = new ArrayList<>();
        for(String author : authorNames){

            if(!Pattern.matches("\\s*",author)) {
                ES_Expert es_expert=new ES_Expert();
                es_expert.setName(author);
                this.authors.add(es_expert);
            }
//                ES_Expert es_expert;
//                Document_Expert document_expert = AcademicController.academicController.getDocu_expertRepository().
//                        findByDocumentIDAndExpertName(documentid, author);
//                if(document_expert != null) {
//                    es_expert = AcademicController.academicController.getEs_expertDao().
//                            findByExpertid(document_expert.getExpertID());
//                    if (es_expert == null) {
//                        es_expert = new ES_Expert();
//                        es_expert.setName(author);
//                    }
//                }
//                else {
//                    es_expert = new ES_Expert();
//                    es_expert.setName(author);
//                }
//                this.authors.add(es_expert);
//            }
        }
        String[] keyword = keywords.split("[,，\\s;.。、]+");
        this.keywordList.addAll(Arrays.asList(keyword));
//        if(time == null)
//            this.time = null;
//        else {
//            System.out.println(time);
////            this.time = new SimpleDateFormat("yyyy-MM-dd").parse(time);
//        }
        this.origin = origin;
        this.views = 0;
//        System.out.println(this.authors);
    }

}
