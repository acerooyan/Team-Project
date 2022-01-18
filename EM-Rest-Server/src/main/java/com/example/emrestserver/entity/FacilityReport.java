package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "facilityreport")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Title")
    private String title;

    @Column(name = "EmployeeID")
    private Integer emplyeeId;

    @Column(name = "ReportDate")
    private Date reportDate;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private String status;

}
