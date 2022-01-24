package com.example.emrestserver.service;

import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.domains.EmployeeProfileDomain;
import com.example.emrestserver.domains.EmployeesDomain;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public EmployeeProfileDomain getEmployees(Integer curPage, Integer totalNum, Integer maxResult, String email) {
        List<Person> personList = personDao.getAllPerson(curPage, totalNum, maxResult, email);
        EmployeeProfileDomain employeeProfileDomain = new EmployeeProfileDomain();
        List<EmployeesDomain> employeesDomains = new ArrayList<>();
        for (Person person : personList) {
            if (person.getCurPage() != null) employeeProfileDomain.setCurPage(person.getCurPage());
            if (person.getTotalNum() != null) employeeProfileDomain.setTotalNum(person.getTotalNum());
            if (person.getMaxResult() != null) employeeProfileDomain.setMaxResult(person.getMaxResult());
            EmployeesDomain employeesDomain = EmployeesDomain.builder().name(person.getFirstname() + " " + person.getLastname()).SSN(person.getSsn()).startDate(DateUtil.DateToString(person.getEmployee().getStartDate())).visaStatus(person.getEmployee().getVisaStatus().getVisaType()).build();
            employeesDomains.add(employeesDomain);
        }
        employeeProfileDomain.setEmployeesDomains(employeesDomains);
        return employeeProfileDomain;
    }
}
