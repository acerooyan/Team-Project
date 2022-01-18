package com.example.emrestserver.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDomain {
    private String Username;
    private String Password;
}
