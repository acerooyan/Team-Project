package com.example.emrestserver.domains.combined;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStatusDomain {
    private String currentStep;
    private String nextStep;
    private String comment;
    String[] documents;

}
