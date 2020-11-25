package se.buaa.Entity.ESDocument;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "BUAA_Document",indexStoreType = "book")
public class ES_Book extends ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Date)//出版时间
    private Date time_;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String origin;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//国际标准书号
    private String isbn;


}
