package com.example.emrestserver.domains.standalone;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDomain {
    private String username;
    private String password;
}
