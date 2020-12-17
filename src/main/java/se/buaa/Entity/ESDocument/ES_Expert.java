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
@Document(indexName = "expert",indexStoreType = "expert")
public class ES_Expert {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String expertid;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String org;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String domain;
}
