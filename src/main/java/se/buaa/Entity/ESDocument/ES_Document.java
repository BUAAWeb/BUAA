package se.buaa.Entity.ESDocument;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.sonatype.guice.bean.reflect.IgnoreSetters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import se.buaa.Entity.Expert;
import se.buaa.Repository.ExpertRepository;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Document(indexName = "document",indexStoreType = "doc")
public class ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String documentid;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String title;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Field(analyzer = "ik_smart", type = FieldType.Text)
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
//    @Field(type = FieldType.Date)//link
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String time;

    @Transient
    private List<String> authors = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Autowired
    ExpertRepository expertRepository;

    @PersistenceConstructor
    public ES_Document(String id, String title, String experts, String keywords, String summary,
                       String link, int cited_quantity, String origin,
                       String time) throws ParseException {
        this.documentid = id;
        this.title = title;
        this.experts = experts;
        this.keywords = keywords;
        this.summary = summary;
        this.link = link;
        this.cited_quantity = cited_quantity;
        this.time = time;
//        if(time == null)
//            this.time = null;
//        else {
//            System.out.println(time);
////            this.time = new SimpleDateFormat("yyyy-MM-dd").parse(time);
//        }
        this.origin = origin;


        String[] authorNames = experts.split(",");
//        System.out.print("1");
        Collections.addAll(authors, authorNames);
//        for(String name : authorNames){
//            System.out.println(name);
//        }
//        System.out.println(this.authors);
    }

}
