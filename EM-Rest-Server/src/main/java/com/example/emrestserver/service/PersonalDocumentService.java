package com.example.emrestserver.service;

import com.amazonaws.services.rekognition.model.PersonMatch;
import com.example.emrestserver.dao.PersonalDocumentDao;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Service("personalDocumentService")
public class PersonalDocumentService {

    @Autowired
    PersonalDocumentDao personalDocumentDao;

    @Transactional
    public PersonalDocument buildDocument(String path, Employee employee){

        String[] arrOfStr = path.split("_", 2);
        PersonalDocument personalDocument = PersonalDocument.builder()
                .employee(employee)
                .path(path)
                .title(arrOfStr[1])
                .comment("")
                .createdDate(new Date(System.currentTimeMillis()))
                .createdBy(employee.getPerson().getFirstname())
                .build();

        return addPersonalDocument(personalDocument);
    }


    @Transactional
    public PersonalDocument addPersonalDocument(PersonalDocument personalDocument){
        return personalDocumentDao.addPersonalDocument(personalDocument);
    }

    @Transactional
    public Employee getFirstEmployee(){
        return personalDocumentDao.getEmployee();
    }

}
