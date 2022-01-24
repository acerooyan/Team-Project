package com.example.emrestserver.domains;

import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.VisaStatus;
import lombok.*;

@Data
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDomain {
    Person person;
    VisaStatus visaStatus;
}
