package com.example.emrestserver.service;


import com.example.emrestserver.dao.VisaStatusDao;
import com.example.emrestserver.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("visaStatusService")
public class VisaStatusService {

    @Autowired
    VisaStatusDao visaStatusDao;

    public VisaStatus updateVisaStatusActiveById(Integer id, String active){
        return visaStatusDao.updateVisaStatusActiveById(id,active);
    }

}
