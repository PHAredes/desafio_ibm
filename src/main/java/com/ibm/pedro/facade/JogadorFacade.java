package com.ibm.pedro.facade;

import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
public class JogadorFacade {

    @Autowired
    JogadorService jogadorService;

    public void inserirJogador(@RequestBody JogadorEntity jogadorEntity) {
        jogadorService.inserirOuAtualizarJogador(jogadorEntity);
    }

    public void excluirTodosJogadores() {
        jogadorService.excluirTodosJogadores();
    }

    public boolean validarNome(JogadorEntity jogadorEntity) {
        return jogadorService.nomeJogadorInvalido(jogadorEntity);
    }
}
