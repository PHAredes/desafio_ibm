package com.ibm.pedro.controllers;

import com.ibm.pedro.repositories.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository;

}
