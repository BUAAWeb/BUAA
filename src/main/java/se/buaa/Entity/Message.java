package se.buaa.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer msgid;
    @Column(name = "is_read")
    public boolean is_read;
    @Column(name = "date")
    public Date date;
    @Column(name = "userid")
    public Integer userid;
    @Column(name = "objectName")
    public String objectName;
    @Column(name = "objectID")
    public String objectID;
    @Column(name = "type")
    public String type;
    @Column(name = "success")
    public boolean success;
    @Column(name = "reason")
    public String reason;
}
