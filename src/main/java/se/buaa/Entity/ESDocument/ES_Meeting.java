package se.buaa.Entity.ESDocument;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "BUAA_Document",indexStoreType = "meeting")
public class ES_Meeting extends ES_Document {
    @Id
    private String id;
    @Field(analyzer = "ik_smart", type = FieldType.Date)//会议时间
    private Date time_;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String origin;

    public Date getTime_() {
        return time_;
    }

    public void setTime_(Date time_) {
        this.time_ = time_;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
