package se.buaa.Entity;

import javax.persistence.*;

@Entity
public class Blob2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer imgID;
    @Column(length = 1024 * 1024 * 20)// 20M
    public byte[] data;

}