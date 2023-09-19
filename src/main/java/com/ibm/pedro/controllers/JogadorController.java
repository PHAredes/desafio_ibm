package com.ibm.pedro.controllers;

import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.services.JogadorService;
import com.ibm.pedro.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class JogadorController {

    @Autowired
    JogadorService jogadorService;
    @Autowired
    TimeService timeService;

    public void inserirJogador(@RequestBody JogadorEntity jogadorEntity) {
        if (jogadorService.nomeJogadorInvalido(jogadorEntity))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do jogador fora dos padrões esperados");

        jogadorService.inserirOuAtualizarJogador(jogadorEntity);
    }

    public void excluirTodosJogadores() {
        jogadorService.excluirTodosJogadores();
    }
}
