package com.example.emrestserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /*
    @ManyToOne
    @JoinColumn(name="PersonID")
    private Person personId;
     */

    //either one above or below
//    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "PersonID")
    private Person person;

    @Column(name = "Title")
    private String title;

    // no need managerId, just trade as normal column
    @Column(name = "ManagerID")
    private Integer managerId;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Car")
    private String car;

    @ManyToOne
    @JoinColumn(name = "VisaStatusID")
    private VisaStatus visaStatus;

    @Column(name = "VisaStartDate")
    private Date visaStartDate;

    @Column(name = "VisaEndDate")
    private Date visaEndDate;

    @Column(name = "DriverLicence")
    private String driverLicence;


    @Column(name = "DriverLicence_ExpirationDate")
    private Date driverLicence_ExpirationDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HouseID")
    private House house;
    //    @Column(name = "HouseID")
//    private Integer houseId;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.MERGE)
    private List<PersonalDocument> personalDocumentList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<ApplicationWorkFlow> applicationWorkFlowList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.MERGE)
    private List<Contact> contactList;


    @Override
    public String toString() {
        return "Employee{ID:" + id + ", Person: " + person.getId() + ", visaStatusID: " + visaStatus.getId() + "}";
    }
}


