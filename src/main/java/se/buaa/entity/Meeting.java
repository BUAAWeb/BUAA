package se.buaa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meeting")
public class Meeting extends Document{

    @Column(name = "date")//会议时间
    private Date date;
    @Column(name = "meeting_name",length = 100)//会议名称
    private String meeting_Name;

}
