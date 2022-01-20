package com.example.emrestserver.domains.combined;

import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.domains.standalone.BasicInfoDomain;
import com.example.emrestserver.domains.standalone.ContactInfoDomain;
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
}
