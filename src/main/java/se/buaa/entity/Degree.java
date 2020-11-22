package se.buaa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "degree")
public class Degree extends Document{
    @Column(name = "date")//学位年度
    private Date date;
    @Column(name = "doi")//数字对象唯一标识符
    private String doi;
    @Column(name = "level",length = 10)//学位级别
    private String level;
}

