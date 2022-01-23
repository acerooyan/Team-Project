package com.example.emrestserver.domain;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeProfileDomain {
    private List<EmployeesDomain> employeesDomains;
    private String email;
    private Integer curPage;
    private Integer maxResult;
    private Integer totalNum;
}
