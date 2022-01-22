package com.example.emrestserver.domains.profile;

import com.example.emrestserver.domains.standalone.AddressDomain;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDomain {
    PersonalInfoDomain personalInfoDomain;
    AddressDomain[] addressDomains;
    EmploymentDomain employmentDomain;
    EmergencyContactDomain emergencyContactDomain;
    ContactInfoDomain contactInfoDomain;
    DocumentDomain documentDomain;
    AvatarDomain avatarDomain;
}
