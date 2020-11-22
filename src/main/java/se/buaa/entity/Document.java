package se.buaa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "experts", length = 100)
    private String experts;
    @Column(name = "keywords", length = 100)
    private String keywords;
    @Column(name = "summary",length = 1000)//摘要
    private String summary;
    @Column(name = "citedQuantity")//被引次数
    private int citedQuantity;
    @Column(name = "link")//link
    private String link;

    @Transient  //<-非数据库字段
    private List<Expert> expertList ;
}
