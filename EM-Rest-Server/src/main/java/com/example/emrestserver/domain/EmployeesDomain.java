package com.example.emrestserver.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeesDomain {
    private String name;
    private String SSN;
    private String startDate;
    private String visaStatus;
}
