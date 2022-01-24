package com.example.emrestserver.domains.profile;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDomain {
    String fullName;
    String dob;
    Integer age;
    String gender;
    String ssn;
}
