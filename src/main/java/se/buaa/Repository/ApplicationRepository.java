package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.ApplicationForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<ApplicationForm,Integer> {
    @Override
    ArrayList<ApplicationForm> findAll();
    ArrayList<ApplicationForm> findApplicationFormsByResultIs(int result);
    ApplicationForm findApplicationFormByUserIDAndObjectID(int userID,String objectID);
    ApplicationForm findApplicationFormByFormID(int id);
}
