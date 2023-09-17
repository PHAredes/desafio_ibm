package com.ibm.pedro.controllers;

import com.ibm.pedro.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    TimeRepository timeRepository;
}
