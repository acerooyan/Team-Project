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
public class Address {
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

    @Column(name = "PersonID")
    private Integer personId;
}
