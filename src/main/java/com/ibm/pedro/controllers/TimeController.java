package com.ibm.pedro.controllers;

import com.ibm.pedro.model.TimeEntity;
import com.ibm.pedro.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    TimeService timeService;

    @GetMapping
    public List<TimeEntity> buscarTime() {
        System.out.println("iniciando busca");
        List<TimeEntity> times = timeService.buscarTimes();
        System.out.printf("Times encontrados: {}", times.size());
        return times;
    }
    
}
