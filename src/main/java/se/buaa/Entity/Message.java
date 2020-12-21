package se.buaa.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "content")
    public String content;
    @Column(name = "is_read")
    public boolean is_read;
    @Column(name = "date")
    public Date date;
    @Column(name = "user_id")
    public Integer userid;
}
