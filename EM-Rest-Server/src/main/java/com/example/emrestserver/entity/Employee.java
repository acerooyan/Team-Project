package com.example.emrestserver.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    /*
    @ManyToOne
    @JoinColumn(name="PersonID")
    private Person personId;
     */

    //either one above or

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

    @Column(name = "VisaStatusID")
    private Integer visaStatusId;

    @Column(name = "VisaStartDate")
    private Date visaStartDate;

    @Column(name="VisaEndDate")
    private Date visaEndDate;

    @Column(name="DriverLicence")
    private String driverLicence;

    @Column(name="DriverLicence_ExpirationDate")
    private Date driverLicence_ExpirationDate;

    @Column(name="HouseID")
    private Integer houseId;

}
