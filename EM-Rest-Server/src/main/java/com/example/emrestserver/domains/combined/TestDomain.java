package com.example.emrestserver.domains.combined;

import com.example.emrestserver.domains.standalone.BasicInfoDomain;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDomain {
    BasicInfoDomain basicInfoDomain;
    ContactInfoDomain contactInfoDomain;
}
