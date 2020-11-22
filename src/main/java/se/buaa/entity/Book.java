package se.buaa.entity;

import se.buaa.Enumeration.Enumeration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends Document{
    @Column(name = "time_")//出版时间
    private Date time_;
    @Column(name = "origin")
    private String origin;
    @Column(name = "isbn")//国际标准书号
    private String isbn;

}
