package com.ibm.pedro.services;

import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.repositories.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    @Autowired
    JogadorRepository jogadorRepository;

    public void inserirOuAtualizarJogador(JogadorEntity jogadorEntity) {
        jogadorRepository.save(jogadorEntity);
    }

    public void excluirTodosJogadores() {
        jogadorRepository.deleteAll();
    }
}
