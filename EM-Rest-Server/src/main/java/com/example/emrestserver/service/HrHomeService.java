package com.example.emrestserver.service;

import com.example.emrestserver.dao.HrHomeDao;
import com.example.emrestserver.domain.HrHomeDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("hrHomeService")
public class HrHomeService {
    @Autowired
    private HrHomeDao hrHomeDao;

    @Transactional
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = hrHomeDao.getAllEmployees();
                System.out.println(employeeList);

        return hrHomeDao.getAllEmployees();
    }
    @Transactional
    public List<List<PersonalDocument>> getPersonalDocumentList(){
        List<Employee> employeeList = getAllEmployees();
        List<Integer> employeeIdList = employeeList.stream().map(Employee::getId).collect(Collectors.toList());
        List<List<PersonalDocument>> personalDocumentList = hrHomeDao.getAllPersonalList(employeeIdList);
        System.out.println(personalDocumentList);
        return personalDocumentList;
    }

    @Transactional
    public List<HrHomeDomain> mapDocumentWithEmployee(){
        List<HrHomeDomain> ans = new ArrayList<>();
        List<List<PersonalDocument>> personalDocumentList =  getPersonalDocumentList();
        List<Employee> employeeList = getAllEmployees();
        for(int i = 0; i < employeeList.size(); i++){
            ans.add(HrHomeDomain.builder()
                    .employee(employeeList.get(i))
                            .personalDocumentList(personalDocumentList.get(i))
                    .build());
        }
        return ans;
    }
}
