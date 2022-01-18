package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "facilityreportdetail")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReportDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ReportID")
    private Integer reportId;

    @Column(name = "EmployeeID")
    private Integer emplyeeId;

    @Column(name = "Comments")
    private String comments;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "LastModificationDate")
    private Date lastModificationDate;

}
