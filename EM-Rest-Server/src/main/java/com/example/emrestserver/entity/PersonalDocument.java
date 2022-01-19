package com.example.emrestserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "personaldocument")
@Data
//@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocument implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "Path")
    private String path;

    @Column(name = "Title")
    private String title;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Override
    public String toString(){
        return "personalDocument{ID: "+id+", EmployeeId: "+ employee.getId() + ", path:"+path+"}";
    }

}
