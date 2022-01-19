package com.example.emrestserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "visastatus")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name = "VisaType")
    private String visaType;

    @Column(name = "Active")
    private Integer active;

    @Column(name = "ModificationDate")
    private Date modificationDate;

    @Column(name = "CreateUser")
    private String createUser;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "visaStatus", cascade = CascadeType.MERGE)
    private List<Employee> employeeList = new ArrayList<>();

}
