package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "digitaldocument")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalDocument implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name = "Type")
    private String type;

    @Column(name = "Required")
    private Byte required;

    @Column(name = "TemplateLocation")
    private String templateLocation;

    @Column(name = "Description")
    private String description;



}
