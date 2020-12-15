package se.buaa.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applicationForm")
public class ApplicationForm{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer formID;
    public int userID;
    public int flag;            //为0时表示认领文献，1时认领门户
    public String objectID;        //分别对应文献或门户的ID
    public String time;
    public int result;
    public String email;
    public String userName;
    public String objectName;
    public String msg;
}
