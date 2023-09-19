package com.ibm.pedro.controllers;


import com.ibm.pedro.facade.JogadorFacade;
import com.ibm.pedro.facade.TimeFacade;
import com.ibm.pedro.model.JogadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    JogadorFacade jogadorFacade;
    @Autowired
    TimeFacade timeFacade;

    @GetMapping("/times")
    public Map<String, List<String>> buscarTimes() {
        return timeFacade.buscarTimes();
    }

    @PostMapping("/jogador")
    @ResponseStatus(HttpStatus.CREATED)
    public void inserirJogador(@RequestBody JogadorEntity jogadorEntity) {
        if (jogadorFacade.validarNome(jogadorEntity))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do jogador fora dos padrões esperados");
        timeFacade.organizarJogadorEmTime(jogadorEntity);
        jogadorFacade.inserirJogador(jogadorEntity);
    }

    @DeleteMapping("/jogador/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTodosJogadores() {
        jogadorFacade.excluirTodosJogadores();
        timeFacade.excluirTodosTimes();
    }
}
