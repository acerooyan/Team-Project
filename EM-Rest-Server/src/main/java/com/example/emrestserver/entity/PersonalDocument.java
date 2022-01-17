package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personaldocument")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name = "EmployeeID")
    private Integer employeeId;

    @Column(name = "Path")
    private String path;

    @Column(name = "Title")
    private String title;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "CreatedBy")
    private String createdBy;


}
