package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "house")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ContactID")
    private Contact contactId;

    @Column(name = "Address")
    private String address;

    @Column(name = "NumberOfPerson")
    private Integer numberOfPerson;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house", cascade = CascadeType.MERGE)
    private List<Employee> employeeList = new ArrayList<>();



}
