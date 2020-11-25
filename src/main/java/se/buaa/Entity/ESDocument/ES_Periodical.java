package se.buaa.Entity.ESDocument;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "BUAA_Document",indexStoreType = "periodical")
public class ES_Periodical extends ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Date)//年份
    private Date time_;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//数字对象唯一标识符
    private String doi;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String origin;

}
