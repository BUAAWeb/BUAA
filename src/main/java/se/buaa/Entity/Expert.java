package se.buaa.Entity;


import javax.persistence.*;

@Entity
@Table(name = "expert")
public class Expert {
    @Id
    private String expertID;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "org", length = 100)
    private String org;
    @Column(name = "cited_times")
    private int cited_Times;
    @Column(name = "achievements")//成果数
    private int achievements;

    public String getOrg() {
        return org;
    }

    public String getExpertID() {
        return expertID;
    }
}



