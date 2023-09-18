package com.ibm.pedro.services;

import com.ibm.pedro.dto.TimeDTO;
import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.model.TimeEntity;
import com.ibm.pedro.repositories.JogadorRepository;
import com.ibm.pedro.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    JogadorRepository jogadorRepository;

    public List<TimeDTO> buscarTimes() {
        List<TimeEntity> times = timeRepository.findAll();
        List<TimeDTO> timesDTO = new ArrayList<>();

        for (TimeEntity time : times) {
            List<String> nomesJogadores = time.getJogadores().stream()
                    .map(JogadorEntity::getNome)
                    .collect(Collectors.toList());

            TimeDTO timeDTO = new TimeDTO("Time " + time.getId(), nomesJogadores);
            timesDTO.add(timeDTO);
        }

        return timesDTO;
    }

    public void organizarJogadorEmTime(JogadorEntity jogador) {
        String sobrenome = jogador.getNome().split(" ")[1];
        TimeEntity timeExistente = encontreTimeExistenteComSobrenome(sobrenome);

        if (timeExistente != null) {
            jogador.setTime(timeExistente); // Associe o jogador ao time existente
        } else {
            TimeEntity novoTime = criarNovoTime(sobrenome.substring(0, 1));
            timeRepository.save(novoTime);
            jogador.setTime(novoTime); // Associe o jogador ao novo time
        }

        jogadorRepository.save(jogador);
    }

    private TimeEntity encontreTimeExistenteComSobrenome(String sobrenome) {
        List<JogadorEntity> jogadores = jogadorRepository.findAll();

        for (JogadorEntity jogador : jogadores) {
            String jogadorSobrenome = jogador.getNome().split(" ")[1];
            if (jogadorSobrenome.equals(sobrenome)) {
                return criarNovoTime(sobrenome); // Cria um novo time se o sobrenome for igual
            } else if (jogadorSobrenome.startsWith(sobrenome.substring(0, 1))) {
                return jogador.getTime(); // Retorna o time se a primeira letra do sobrenome for igual
            }
        }

        return null;
    }

    private TimeEntity criarNovoTime(String nomeTime) {
        TimeEntity novoTime = new TimeEntity();
        novoTime.setNome(nomeTime);
        return timeRepository.save(novoTime);
    }
}
