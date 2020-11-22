package se.buaa.Relation;

import javax.persistence.*;

@Entity
@Table(name = "document_expert")
public class Document_Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "expertID")
    private int expertID;
    @Column(name = "documentID")
    private int documentID;
}
