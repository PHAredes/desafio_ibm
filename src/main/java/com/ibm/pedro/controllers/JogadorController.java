package com.ibm.pedro.controllers;

import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    JogadorService jogadorService;

    @PostMapping
    public void inserirJogador(@RequestBody JogadorEntity jogadorEntity) {
        if(jogadorEntity != null) {
            jogadorService.inserirOuAtualizarJogador(jogadorEntity);
        }
    }

    @DeleteMapping("/all")
    public void excluirTodosJogadores() {
        jogadorService.excluirTodosJogadores();
    }


}
