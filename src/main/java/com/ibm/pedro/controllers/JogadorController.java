package com.ibm.pedro.controllers;

import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.services.JogadorService;
import com.ibm.pedro.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    JogadorService jogadorService;
    @Autowired
    TimeService timeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserirJogador(@RequestBody JogadorEntity jogadorEntity) {
        if(jogadorService.nomeJogadorInvalido(jogadorEntity))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do jogador fora dos padrões esperados");

        timeService.organizarJogadorEmTime(jogadorEntity);
        jogadorService.inserirOuAtualizarJogador(jogadorEntity);
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTodosJogadores() {
        jogadorService.excluirTodosJogadores();
    }


}
