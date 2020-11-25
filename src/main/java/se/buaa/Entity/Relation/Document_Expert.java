package se.buaa.Entity.Relation;

import javax.persistence.*;

@Entity
@Table(name = "document_expert")
public class Document_Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "expertID")
    private String expertID;
    @Column(name = "documentID")
    private String documentID;
}
