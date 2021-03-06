package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name = "AddressLine1")
    private String addressLine1;

    @Column(name = "AddressLine2")
    private String addressLine2;

    @Column(name = "City")
    private String city;

    @Column(name = "Zipcode")
    private Integer zipcode;

    @Column(name = "StateName")
    private String stateName;

    @Column(name = "StateAbbr")
    private String stateAbbr;

    @ManyToOne
    @JoinColumn(name = "PersonID")
    private Person person;
}
