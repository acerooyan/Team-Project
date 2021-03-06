package com.example.emrestserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "person")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;


    @Column(name = "FirstName")
    private String firstname;

    @Column(name = "LastName")
    private String lastname;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "Email")
    private String email;

    @Column(name = "CellPhone")
    private String cellPhone;

    @Column(name = "AlternatePhone")
    private String alternatePhone;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "UserId")
    private Integer userId;




    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private Employee employee;


//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.MERGE)
//
//    private List<Contact> contactList = new ArrayList<>();
//
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.MERGE)
//    private List<Address> addressList = new ArrayList<>();



}
