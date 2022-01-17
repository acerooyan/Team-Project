package com.example.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registration_token")
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

    @Column(name = "valid_duration")
    private int validDuration;

    @Column(name = "email")
    private String email;

    @Column(name = "created_by")
    private String createdBy;

}
