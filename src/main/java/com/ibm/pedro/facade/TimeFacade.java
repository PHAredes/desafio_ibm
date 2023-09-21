package com.ibm.pedro.facade;

import com.ibm.pedro.dto.TimeDTO;
import com.ibm.pedro.model.JogadorEntity;
import com.ibm.pedro.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeFacade {

    @Autowired
    TimeService timeService;

    public List<TimeDTO> buscarTimes() {
        return timeService.buscarTimes();
    }
    public void organizarJogadorEmTime(JogadorEntity jogadorEntity) {
        timeService.organizarJogadorEmTime(jogadorEntity);
    }

    public void excluirTodosTimes() {
        timeService.excluirTodosTimes();
    }
}
