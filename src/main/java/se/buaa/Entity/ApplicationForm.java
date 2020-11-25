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
    public int objectID;        //分别对应文献或门户的ID
    public Date time;
    public String result;

}
