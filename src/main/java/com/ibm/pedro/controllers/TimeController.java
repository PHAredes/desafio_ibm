package com.ibm.pedro.controllers;

import com.ibm.pedro.dto.TimeDTO;
import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TimeController {

    @Autowired
    TimeService timeService;

    public Map<String, List<String>> buscarTimes() {
        List<TimeDTO> times = timeService.buscarTimes();
        Map<String, List<String>> timeMap = new HashMap<>();

        for (TimeDTO time : times) {
            timeMap.put(time.getNome(), time.getJogadores());
        }

        return timeMap;
    }
    public void organizarJogadorEmTime(JogadorEntity jogadorEntity) {
        timeService.organizarJogadorEmTime(jogadorEntity);
    }

    public void excluirTodosTimes() {
        timeService.excluirTodosTimes();
    }
}
