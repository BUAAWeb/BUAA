package se.buaa.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CollectionKey implements Serializable{
    @Column(name="userid")
    private Integer userid;
    @Column(name="documentid")
    private String documentid;

}
