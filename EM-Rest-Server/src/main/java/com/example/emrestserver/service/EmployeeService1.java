package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeService1 {
    @Autowired
    EmployeeDao employeeDao;

    public Employee getEmpolyeeByEmail(String email){
        return employeeDao.getEmployeeByEmail(email);
    }

    public Address[] getAddressByPersonId(Integer personId){
        return null;
    }
}
