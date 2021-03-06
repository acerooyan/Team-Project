package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "facility")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facility implements Serializable {
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
    private Integer houseId;

}
