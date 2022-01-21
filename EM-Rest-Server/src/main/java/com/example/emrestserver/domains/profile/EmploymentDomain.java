package com.example.emrestserver.domains.profile;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDomain {
    String workAuthorization;
    String workAuthorizationStartDate;
    String workAuthorizationEndDate;
    String title;
}
