package se.buaa.entity;

import javax.persistence.*;

@Entity
@Table(name = "expert")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Integer userID;
        @Column(length = 100)
        public String userName;
        public String passwd;
        public String email;
        public int isBanned;
        public int isVerified;

}
