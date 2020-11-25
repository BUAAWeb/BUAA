package se.buaa.Entity.ESDocument;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "BUAA_Document",indexStoreType = "degree")
public class ES_Degree extends ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Date)//学位年度
    private Date time_;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//数字对象唯一标识符
    private String doi;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//学位级别
    private String level;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String origin;



}

