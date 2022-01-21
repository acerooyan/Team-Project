package com.example.emrestserver.domains.profile;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDomain {
    private String cellPhone;
    private String alternatePhone;
    private String email;
}
