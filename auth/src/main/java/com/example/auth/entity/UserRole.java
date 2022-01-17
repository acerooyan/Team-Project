package com.example.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userrole")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "activeflag")
    private int activeFlag;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "modificationdate")
    private Date modificationDate;
    @Column(name = "lastmodificationuser")
    private int lastModificationUser;




}
