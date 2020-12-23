package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Collection;
import se.buaa.Entity.CollectionKey;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, CollectionKey> {
    public List<Collection> findCollectionsByCollectionKey_Userid(int useid);
}
