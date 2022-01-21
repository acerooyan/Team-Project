package com.example.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "RoleName")
    private String roleName;
    @Column(name = "description")
    private String description;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "modificationdate")
    private Date modificationDate;
    @Column(name = "lastmodificationuser")
    private int lastModificationUser;


}
