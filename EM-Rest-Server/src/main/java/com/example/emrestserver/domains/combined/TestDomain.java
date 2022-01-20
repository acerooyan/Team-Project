package com.example.emrestserver.domains.combined;

import com.example.emrestserver.domains.standalone.*;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDomain {
    BasicInfoDomain basicInfoDomain;
    ContactInfoDomain contactInfoDomain;
    List<AddressDomain> addressList;
    ContactReferenceDomain contactReferenceDomain;
    ContactEmergencyDomain contactEmergencyDomain;
    ResidentialStatusDomain residentialStatusDomain;
}
