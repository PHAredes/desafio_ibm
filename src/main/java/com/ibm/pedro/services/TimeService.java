package com.ibm.pedro.services;

import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.model.TimeEntity;
import com.ibm.pedro.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TimeService {

    @Autowired
    TimeRepository timeRepository;

    public List<TimeEntity> buscarTimes(){
        System.out.println("tentou buscar");
        List<TimeEntity> listaTeste = timeRepository.findAll();
        System.out.printf("Times encontrados: {}", listaTeste.size());
        return listaTeste;

    }

    public void organizarJogadorEmTime(JogadorEntity jogador) {
        TimeEntity novoTime = new TimeEntity();
        novoTime.setNome(jogador.getNome());
        timeRepository.save(novoTime);
        jogador.setTime(novoTime);
    }

    public Map<String, List<String>> organizarTimes(List<String> jogadores) {
        Map<String, List<String>> times = new HashMap<>();

        jogadores.stream()
                .filter(jogador -> jogador.split(" ").length == 2)
                .forEach(jogador -> {
                    String sobrenome = jogador.split(" ")[1];
                    String chaveTime = sobrenomeJaExiste(times, sobrenome) ? "Time " + (times.size() + 1) : sobrenome.substring(0, 1);
                    times.computeIfAbsent(chaveTime, key -> new ArrayList<>()).add(jogador);
                });

        return times;
    }

    private boolean sobrenomeJaExiste(Map<String, List<String>> times, String sobrenome) {
        return times.values().stream()
                .flatMap(List::stream)
                .map(j -> j.split(" ")[1])
                .anyMatch(s -> s.equals(sobrenome));
    }
}
