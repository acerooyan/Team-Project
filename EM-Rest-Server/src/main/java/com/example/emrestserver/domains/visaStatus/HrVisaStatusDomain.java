package com.example.emrestserver.domains.visaStatus;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HrVisaStatusDomain {
    public String email;
    public String fullName;
    public String visa;
    public String startDate;
    public String endDate;
    public Integer dayLeft;
    public String[] documents;
    public String currentStep;
    public String nextStep;
    public String comment;
}
