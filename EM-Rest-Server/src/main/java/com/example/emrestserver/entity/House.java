package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "ContactID")
    private Integer contactId;

    @Column(name = "Address")
    private String address;

    @Column(name = "NumberOfPerson")
    private Integer numberOfPerson;

}
