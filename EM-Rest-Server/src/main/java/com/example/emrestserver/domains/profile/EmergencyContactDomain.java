package com.example.emrestserver.domains.profile;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactDomain {
    private String fullName;
    private String relationship;
    private String cellPhone;
    private String email;
}
