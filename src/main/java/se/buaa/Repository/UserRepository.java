package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserID(Integer id);
    User findByUserName(String name);
}
