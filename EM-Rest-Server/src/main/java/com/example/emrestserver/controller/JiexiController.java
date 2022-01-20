package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.TestDomain;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/em")
public class JiexiController {

    @Autowired
    RegisterService registerService;


}