package se.buaa.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "degree")
public class Degree extends Document{
    @Column(name = "time_")//学位年度
    private Date time_;
    @Column(name = "doi")//数字对象唯一标识符
    private String doi;
    @Column(name = "level",length = 10)//学位级别
    private String level;
    @Column(name = "origin")
    private String origin;
}

