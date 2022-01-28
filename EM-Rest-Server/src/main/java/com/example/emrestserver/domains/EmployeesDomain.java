package com.example.emrestserver.domains;

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
    private String email;
}
