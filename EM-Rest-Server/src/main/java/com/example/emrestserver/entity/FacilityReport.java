package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "facilityreport")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Title")
    private String title;
    @Column(name = "EmployeeID")
    private Integer emplyeeID;
    @Column(name = "Description")
    private String description;
    @Column(name = "ReportDate")
    private Date reportDate;
    @Column(name = "Status")
    private String status;

}
