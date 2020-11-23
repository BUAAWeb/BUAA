package se.buaa.Document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.List;

@Data
@Document(indexName = "document",indexStoreType = "document")
public class ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String title;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String experts;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String keywords;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//摘要
    private String summary;
    @Field(analyzer = "ik_smart", type = FieldType.Integer)//被引次数
    private int citedQuantity;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//link
    private String link;

}
