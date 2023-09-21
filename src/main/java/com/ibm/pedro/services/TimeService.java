package com.ibm.pedro.services;

import com.ibm.pedro.dto.TimeDTO;
import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.model.TimeEntity;
import com.ibm.pedro.repositories.JogadorRepository;
import com.ibm.pedro.repositories.TimeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;


    public List<TimeDTO> buscarTimes() {
        return timeRepository.findAll().stream()
                .map(time -> new TimeDTO("Time " + time.getId(),
                        time.getJogadores().stream()
                                .map(JogadorEntity::getNome)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public void organizarJogadorEmTime(JogadorEntity jogador) {
        String sobrenome = jogador.getNome().split(" ")[1];

        jogador.setTime(encontreTimeExistenteComSobrenome(sobrenome).orElseGet(() -> {
            TimeEntity novoTime = criarNovoTime(sobrenome.substring(0, 1));
            novoTime.getJogadores().add(jogador); // Adicione o jogador ao novo time
            jogadorRepository.save(jogador); // Associe o jogador ao novo time antes de salvar
            return timeRepository.save(novoTime);
        }));
    }


    public void excluirTodosTimes() {
        timeRepository.deleteAll();
    }

    private TimeEntity criarNovoTime(String nomeTime) {
        TimeEntity novoTime = new TimeEntity();
        novoTime.setNome(nomeTime);
        return timeRepository.save(novoTime);
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

}
