package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.dao.employeeDao1;
import com.example.emrestserver.domains.EmployeeProfileDomain;
import com.example.emrestserver.domains.EmployeesDomain;
import com.example.emrestserver.entity.Employee;
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

    private employeeDao1 employeeDao;

    @Autowired
    private PersonDao personDao;
    @Autowired
    public PersonService(employeeDao1 employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeProfileDomain getEmployees(Integer curPage, Integer totalNum, Integer maxResult, String email) {
        List<Employee> employeeList = employeeDao.getAllEmployees(curPage, totalNum, maxResult, email);
        EmployeeProfileDomain employeeProfileDomain = new EmployeeProfileDomain();
        List<EmployeesDomain> employeesDomains = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getCurPage() != null) employeeProfileDomain.setCurPage(employee.getCurPage());
            if (employee.getTotalNum() != null) employeeProfileDomain.setTotalNum(employee.getTotalNum());
            if (employee.getMaxResult() != null) employeeProfileDomain.setMaxResult(employee.getMaxResult());
            EmployeesDomain employeesDomain = EmployeesDomain.builder().name(employee.getPerson().getFirstname() + " " + employee.getPerson().getLastname())
                    .SSN(employee.getPerson().getSsn()).startDate(DateUtil.DateToString(employee.getStartDate()))
                    .visaStatus(employee.getVisaStatus().getVisaType()).email(employee.getPerson().getEmail()).build();
            employeesDomains.add(employeesDomain);
        }
        employeeProfileDomain.setEmployeesDomains(employeesDomains);
        return employeeProfileDomain;
    }

    @Transactional
    public Person getPersonByEmail(String email){
        return personDao.getPersonByEmail(email);
    }
    @Transactional
    public Person updatePersonWithPerson(Person person){
        return personDao.updatePersonWithPerson(person);
    }

}