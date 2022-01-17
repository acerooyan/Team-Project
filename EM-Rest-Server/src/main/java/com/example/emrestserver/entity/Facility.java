package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "facility")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Type")
    private String type;
    @Column(name = "Description")
    private String description;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "HouseID")
    private Integer houseID;

}
