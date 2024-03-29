package com.esercizio.calendario.services;

import com.esercizio.calendario.entities.Calendario;
import com.esercizio.calendario.entities.Evento;
import com.esercizio.calendario.repositories.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;

    public Calendario addCalendario(Calendario calendario){
        Calendario calendario1 = calendarioRepository.save(calendario);
        return calendario1;
    }

    public List<Calendario> getAllCalendar() {
        List<Calendario> calendarioList = calendarioRepository.findAll();
        return calendarioList;
    }

    public Optional<Calendario> getCalendar(Long id) {
        Optional<Calendario> calendarioOptional = calendarioRepository.findById(id);
        return calendarioOptional;
    }

    public Optional<Calendario> updateCalendar(Long id,Calendario calendario){
        Optional<Calendario> calendarioDaAggiornare = calendarioRepository.findById(id);
        if (calendarioDaAggiornare.isPresent()){
            calendarioDaAggiornare.get().setNome(calendario.getNome());
            calendarioDaAggiornare.get().setDescrizione(calendario.getDescrizione());
            calendarioDaAggiornare.get().setColore(calendario.getColore());
            calendarioRepository.save(calendarioDaAggiornare.get());
        } else {
            return Optional.empty();
        }
        return calendarioDaAggiornare;
    }


    public Optional<Calendario> deleteCalendarById(Long id){
        Optional<Calendario> calendarioDaCancellareOptional = calendarioRepository.findById(id);
        if(calendarioDaCancellareOptional.isPresent()){
            calendarioRepository.delete(calendarioDaCancellareOptional.get());
            return calendarioDaCancellareOptional;
        }else{
            return Optional.empty();
        }

    }
}
