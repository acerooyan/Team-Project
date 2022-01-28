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
    public String fullName;
    public String visa;
    public String startDate;
    public String endDate;
    public Integer dayLeft;
    public String email;
}
