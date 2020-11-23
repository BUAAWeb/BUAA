package se.buaa.entity;

import se.buaa.Enumeration.Enumeration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "periodical")
public class Periodical extends Document{
    @Column(name = "time_")//年份
    private Date time_;
    @Column(name = "doi")//数字对象唯一标识符
    private String doi;
    @Column(name = "origin")
    private String origin;
}