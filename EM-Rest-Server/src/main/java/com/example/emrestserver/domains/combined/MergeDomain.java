package com.example.emrestserver.domains.combined;

import com.example.emrestserver.domains.standalone.*;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeDomain {

    BasicInfoDomain basicInfoDomain;
    ContactInfoDomain contactInfoDomain;
    CarInfoDomain carInfoDomain;
    ContactEmergencyDomain contactEmergencyDomain;
    ContactReferenceDomain contactReferenceDomain;
    ResidentialStatusDomain residentialStatusDomain;

}
