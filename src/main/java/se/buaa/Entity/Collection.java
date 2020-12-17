package se.buaa.Entity;


import javax.persistence.*;

@Entity
@Table(name = "collection")
public class Collection {
    @Id
    private Integer userID;
    @Column(name = "documentID", length = 100)
    private String documentID;

    public Integer getUserID() {
        return userID;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    //注入数据后执行该方法，用于处理非数据库字段，若该字段未设置get，set方法，则返回到前端无该字段
    @PostLoad
    public void test(){
        System.out.println("PostLoad success!");
    }
}



