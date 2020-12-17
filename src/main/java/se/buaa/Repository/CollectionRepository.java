package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Collection;
import se.buaa.Entity.CollectionKey;

public interface CollectionRepository extends JpaRepository<Collection, CollectionKey> {
}
