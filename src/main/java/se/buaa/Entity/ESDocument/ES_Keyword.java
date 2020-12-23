package se.buaa.Entity.ESDocument;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@Document(indexName = "keyword",indexStoreType = "keyword")
public class ES_Keyword {
    @Id
    private String id;
    @Field( type = FieldType.Text)
    public String keyword;
    @Field(type = FieldType.Integer)
    public int view;
    @Field(type = FieldType.Integer)
    public int citedNum;

}
