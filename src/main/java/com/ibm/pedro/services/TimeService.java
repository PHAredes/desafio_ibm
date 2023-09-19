package com.ibm.pedro.services;

import com.ibm.pedro.dto.TimeDTO;
import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.model.TimeEntity;
import com.ibm.pedro.repositories.JogadorRepository;
import com.ibm.pedro.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

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
        Optional<TimeEntity> timeExistente = encontreTimeExistenteComSobrenome(sobrenome);

        if (timeExistente.isPresent()) {
            jogador.setTime(timeExistente.get());
        } else {
            TimeEntity novoTime = criarNovoTime(sobrenome.substring(0, 1));
            jogador.setTime(novoTime); // Associe o jogador ao novo time antes de salvar
            jogadorRepository.save(jogador);
            novoTime.getJogadores().add(jogador); // Adicione o jogador ao novo time
            timeRepository.save(novoTime);
        }
    }

    public void excluirTodosTimes() {
        timeRepository.deleteAll();
    }

    private Optional<TimeEntity> encontreTimeExistenteComSobrenome(String sobrenome) {
        List<JogadorEntity> jogadores = jogadorRepository.findAll();

        for (JogadorEntity jogador : jogadores) {
            String jogadorSobrenome = jogador.getNome().split(" ")[1];
            if (jogadorSobrenome.equals(sobrenome)) {
                return Optional.of(criarNovoTime(sobrenome));
            } else if (jogadorSobrenome.startsWith(sobrenome.substring(0, 1))) {
                return Optional.of(jogador.getTime());
            }
        }

        return Optional.empty();
    }

    private TimeEntity criarNovoTime(String nomeTime) {
        TimeEntity novoTime = new TimeEntity();
        novoTime.setNome(nomeTime);
        return timeRepository.save(novoTime);
    }
}
