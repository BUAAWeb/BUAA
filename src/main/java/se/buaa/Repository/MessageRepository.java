package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository <Message,Integer> {
    List<Message> findByUser_id(Integer id);
    Message findByMsg_id(Integer id);
    void deleteByMsg_id(Integer id);
}
