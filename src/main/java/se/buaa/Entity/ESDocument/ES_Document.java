package se.buaa.Entity.ESDocument;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import se.buaa.Entity.Expert;
import se.buaa.Repository.ExpertRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Document(indexName = "document",indexStoreType = "document")
public class ES_Document {
    @Id
    private String id;
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

    @Transient
    private List<String> authors = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Autowired
    ExpertRepository expertRepository;

    @PersistenceConstructor
    public ES_Document(String id,String title,String experts,String keywords,String summary,String link,int cited_quantity){
        this.id = id;
        this.title = title;
        this.experts = experts;
        this.keywords = keywords;
        this.summary = summary;
        this.link = link;
        this.cited_quantity = cited_quantity;

        String[] authorNames = experts.split(",");
//        System.out.print("1");
        Collections.addAll(authors, authorNames);
//        for(String name : authorNames){
//            System.out.println(name);
//        }
//        System.out.println(this.authors);
    }

}
