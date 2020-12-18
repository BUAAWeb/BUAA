package se.buaa.Entity;


import org.springframework.data.relational.core.sql.In;

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
    private Integer cited_Times;
    @Column(name = "achievements")//成果数
    private int achievements;
    @Column(name = "views")
    private Integer views;
    @Column(name = "is_verified")
    public Integer isVerified;
    public Integer is_focus;
    public Integer userID;

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public int getViews() {
        return views;
    }

    public String getOrg() {
        return org;
    }

    public String getName() {
        return name;
    }

    public int getCited_Times() {
        return cited_Times;
    }

    public int getAchievements() {
        return achievements;
    }

    public String getExpertID() {
        return expertID;
    }

    //注入数据后执行该方法，用于处理非数据库字段，若该字段未设置get，set方法，则返回到前端无该字段
    @PostLoad
    public void test(){
        System.out.println("PostLoad success!");
    }
}



