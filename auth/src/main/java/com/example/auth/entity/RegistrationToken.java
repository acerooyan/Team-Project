package com.example.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registrationtoken")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "token")
    private String token;

    @Column(name = "validDuration")
    private Integer validDuration;

    @Column(name = "Email")
    private String email;

    @Column(name = "CreatedBy")
    private String createdBy;

}
