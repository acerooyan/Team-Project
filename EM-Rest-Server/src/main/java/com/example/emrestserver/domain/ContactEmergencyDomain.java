package com.example.emrestserver.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactEmergencyDomain {
    private String firstName;
    private String lastName;
    private String middleName;
    private String cellPhone;
    private String email;
    private String relationship;
}
