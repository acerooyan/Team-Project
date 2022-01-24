package com.example.emrestserver.domains.hire;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HireDomain {
    public String fullName;
    public String workAuthorization;
    public String type;
    public String status;
    public String email;



}
