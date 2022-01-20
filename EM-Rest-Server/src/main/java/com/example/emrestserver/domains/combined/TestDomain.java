package com.example.emrestserver.domains.combined;

import com.example.emrestserver.domains.standalone.BasicInfoDomain;
import com.example.emrestserver.entity.Address;
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
    Address[] addressList;
}
