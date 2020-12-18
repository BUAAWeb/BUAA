package se.buaa.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user_expert")
public class User_Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer relationId;

    public Integer userId;
    @Column(length = 100)
    public String expertId;

    public User_Expert(Integer userId, String expertId) {
        this.userId = userId;
        this.expertId = expertId;
    }

    public User_Expert() {

    }
}
