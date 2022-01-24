package com.example.emrestserver.domains.visaStatus;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStatusDomain {
    public String currentStep;
    private String nextStep;
    private String comment;
    private String status;
    private String i983;
    private String i983Sample;
    String[] documents;

}
