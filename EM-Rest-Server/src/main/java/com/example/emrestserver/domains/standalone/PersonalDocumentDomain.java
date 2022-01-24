package com.example.emrestserver.domains.standalone;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocumentDomain {

    private Integer employeeId;
    private String Path;
    private String Title;
    private String comment;
    private String createDate;
    private String createBy;

}
