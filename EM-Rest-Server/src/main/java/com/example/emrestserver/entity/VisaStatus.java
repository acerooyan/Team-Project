package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visastatus")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name = "VisaType")
    private String visaType;

    @Column(name = "Active")
    private Byte active;

    @Column(name = "ModificationDate")
    private Date modificationDate;

    @Column(name = "CreateUser")
    private Integer createUser;

}
