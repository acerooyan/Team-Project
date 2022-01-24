package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.HrHomeDao;
import com.example.emrestserver.domains.combined.HrHomeDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.PersonalDocument;
import com.example.emrestserver.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("hrHomeService")
public class HrHomeService {
    @Autowired
    private HrHomeDao hrHomeDao;

    @Autowired
    private EmployeeDao employeeDao;

//    @Transactional
//    public List<Employee> getAllEmployees(){
//        List<Employee> employeeList = hrHomeDao.getAllEmployees();
//                System.out.println(employeeList);
//
//        return hrHomeDao.getAllEmployees();
//    }
//    @Transactional
//    public List<List<PersonalDocument>> getPersonalDocumentList(){
//        List<Employee> employeeList = getAllEmployees();
//        List<Integer> employeeIdList = employeeList.stream().map(Employee::getId).collect(Collectors.toList());
//        List<List<PersonalDocument>> personalDocumentList = hrHomeDao.getAllPersonalList(employeeIdList);
//        System.out.println(personalDocumentList);
//        return personalDocumentList;
//    }

//    @Transactional
//    public List<HrHomeDomain> mapDocumentWithEmployee(){
//        List<HrHomeDomain> ans = new ArrayList<>();
//        List<List<PersonalDocument>> personalDocumentList =  getPersonalDocumentList();
//        List<Employee> employeeList = getAllEmployees();
//        for(int i = 0; i < employeeList.size(); i++){
//            ans.add(HrHomeDomain.builder()
//                    .employee(employeeList.get(i))
//                            .personalDocumentList(personalDocumentList.get(i))
//                    .build());
//        }
//        return ans;
//    }

    @Transactional
    public HrHomeDomain[] mainService(){
        // get employees whose active is 1
        Employee[] employees = employeeDao.getEmployeeByActive();

        HrHomeDomain[] hrHomeDomains = new HrHomeDomain[employees.length];

        for(int i = 0; i < hrHomeDomains.length; i++){
            Person person = employees[i].getPerson();
            StringBuilder sb = new StringBuilder();
            sb.append(person.getFirstname());
            sb.append(" ");
            if(person.getMiddleName()!= null){
                sb.append(person.getMiddleName());
                sb.append(" ");
            }
            sb.append(person.getLastname());
            HrHomeDomain hrHomeDomain = HrHomeDomain.builder()
                    .fullName(sb.toString())
                    .visa(employees[i].getVisaStatus().getVisaType())
                    .startDate(employees[i].getVisaStartDate().toString())
                    .endDate(employees[i].getVisaEndDate().toString())
                    .dayLeft(daysLeft(employees[i].getVisaEndDate()))
                    .email(person.getEmail())
                    .build();
            hrHomeDomains[i] = hrHomeDomain;
        }
        return hrHomeDomains;

    }

    @Transactional
    public Integer daysLeft(Date endDate){

        LocalDate end = endDate.toLocalDate();
        Integer days = (int) ChronoUnit.DAYS.between(LocalDate.now(),end);
        return days;
    }

}
