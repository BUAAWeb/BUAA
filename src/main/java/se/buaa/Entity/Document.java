package se.buaa.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {
    @Id
    private String documentID;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "experts", length = 100)
    private String experts;
    @Column(name = "keywords", length = 100)
    private String keywords;
    @Column(name = "summary",length = 1000)//摘要
    private String summary;
    @Column(name = "citedQuantity")//被引次数
    private Integer citedQuantity;
    @Column(name = "link")//link
    private String link;
    @Column(name = "document_type")
    private Integer document_type;
    @Column(name = "first_author")
    private String first_author;
    @Column(name = "date")
    private String date;

    @Transient  //<-非数据库字段
    private List<Expert> expertList ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getTitle() {
        return title;
    }

    public String getExperts() {
        return experts;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getSummary() {
        return summary;
    }

    public int getCitedQuantity() {
        return citedQuantity;
    }

    public String getLink() {
        return link;
    }

    public List<Expert> getExpertList() {
        return expertList;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCitedQuantity(int citedQuantity) {
        this.citedQuantity = citedQuantity;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDocument_type() {
        return document_type;
    }

    public void setDocument_type(int document_type) {
        this.document_type = document_type;
    }

    public String getFirst_author() {
        return first_author;
    }

    public void setFirst_author(String first_author) {
        this.first_author = first_author;
    }

    public void setExpertList(List<Expert> expertList) {
        this.expertList = expertList;
    }
}
