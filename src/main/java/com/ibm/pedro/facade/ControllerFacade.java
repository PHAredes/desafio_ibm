package com.ibm.pedro.facade;


import com.ibm.pedro.controllers.JogadorController;
import com.ibm.pedro.controllers.TimeController;
import com.ibm.pedro.model.JogadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ControllerFacade {

    @Autowired
    JogadorController jogadorController;
    @Autowired
    TimeController timeController;

    @GetMapping("/times")
    public Map<String, List<String>> buscarTimes() {
        return timeController.buscarTimes();
    }

    @PostMapping("/jogador")
    @ResponseStatus(HttpStatus.CREATED)
    public void inserirJogador(@RequestBody JogadorEntity jogadorEntity) {
        timeController.organizarJogadorEmTime(jogadorEntity);
        jogadorController.inserirJogador(jogadorEntity);
    }

    @DeleteMapping("/jogador/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTodosJogadores() {
        jogadorController.excluirTodosJogadores();
        timeController.excluirTodosTimes();
    }
}
