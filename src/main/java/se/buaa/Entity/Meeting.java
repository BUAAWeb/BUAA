package se.buaa.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meeting")
public class Meeting extends Document{

    @Column(name = "time_")//会议时间
    private Date time_;
    @Column(name = "origin")
    private String origin;

}
