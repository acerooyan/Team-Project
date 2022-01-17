package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name = "PersonID")
    private Integer personId;

    @Column(name = "Relationship")
    private String relationShip;

    @Column(name = "Title")
    private String title;

    @Column(name = "isReference")
    private Byte isReference;

    @Column(name = "isEmergency")
    private Byte isEmergency;

    @Column(name = "isLandlord")
    private Byte isLandlord;

}
