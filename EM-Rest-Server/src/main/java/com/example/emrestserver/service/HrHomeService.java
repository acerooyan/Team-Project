package com.example.emrestserver.service;

import com.example.emrestserver.dao.HrHomeDao;
import com.example.emrestserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrHomeService {
    @Autowired
    HrHomeDao hrHomeDao;

    public List<Employee> getFetchedAllEmployee(){
        System.out.println(hrHomeDao.getEmployeeListWithVisaStatusActive());
    }
}
