package se.buaa.Document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@Document(indexName = "BUAA_Document",indexStoreType = "patent")
public class ES_Patent extends ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//申请号
    private String application_Number;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//公开号
    private String public_Number;
    @Field(analyzer = "ik_smart", type = FieldType.Text)//申请人
    private String applicant;

}
