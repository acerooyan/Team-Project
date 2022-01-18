package com.example.emrestserver.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeDomain {

    BasicInfoDomain basicInfoDomain;
    CarInfoDomain carInfoDomain;
    ContactEmergencyDomain contactEmergencyDomain;
    ContactReferenceDomain contactReferenceDomain;
    ResidentialStatusDomain residentialStatusDomain;

}
