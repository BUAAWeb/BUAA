package se.buaa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.buaa.Entity.Blob2;

public interface BlobRepository extends JpaRepository<Blob2,Integer> {
    Blob2 findBlob2ByImgID(int imgID);
}
