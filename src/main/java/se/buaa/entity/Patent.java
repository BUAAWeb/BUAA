package se.buaa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patent")
public class Patent extends Document{
    @Column(name = "application_number",length = 100)//申请号
    private String application_Number;
    @Column(name = "public_number",length = 100)//公开号
    private String public_Number;
    @Column(name = "applicant",length = 100)//申请人
    private String applicant;
}
