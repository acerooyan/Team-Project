package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applicationworkflow")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationWorkFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name ="CreatedDate")
    private Date createdDate;

    @Column(name = "ModificationDate")
    private Date modificationDate;


    @Column(name = "Status")
    private String status;

    @Column(name = "Comments")
    private String comments;

    @Column(name = "Type")
    private String type;
}
