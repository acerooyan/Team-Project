package com.example.auth.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationTokenDomain {
    private String email;
    private Integer duration;
}
