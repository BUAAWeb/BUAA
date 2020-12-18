package se.buaa.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Integer userID;
        @Column(length = 100)
        public String userName;
        public String passwd;
        public String email;
        public Integer isBanned;
        public Integer isVerified;
        public Integer isAdmin;
        public String realName;
        public String phoneNum;
        public String url;
        public String expertID;
}
