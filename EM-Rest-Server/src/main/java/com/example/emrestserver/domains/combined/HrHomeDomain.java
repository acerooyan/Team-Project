package com.example.emrestserver.domains.combined;

import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HrHomeDomain {
    Employee employee;
    List<PersonalDocument> personalDocumentList;


}
