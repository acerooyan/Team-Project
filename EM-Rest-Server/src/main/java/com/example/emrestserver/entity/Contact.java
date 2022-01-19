package com.example.emrestserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contact")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PersonID")
    private Person person;

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

//    @Column(name = "WithEmployee")
//    private Integer withEmployee;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "WithEmployee")
    private Employee employee;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contact")
    private House house;



}
