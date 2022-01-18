package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
    private String dob;

    @Column(name = "UserId")
    private String userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.MERGE)
    private List<Employee> employeeList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.MERGE)
    private List<Contact> contactList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.MERGE)
    private List<Address> addressList = new ArrayList<>();



}
