package com.esercizio.calendario.services;

import com.esercizio.calendario.entities.Calendario;
import com.esercizio.calendario.entities.Evento;
import com.esercizio.calendario.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento addEvent(Evento evento){
        eventoRepository.save(evento);
        return evento;
    }


    public List<Evento> getAllEvents() {
        List<Evento> eventoList = eventoRepository.findAll();
        return eventoList;
    }

    public Optional<Evento> getEvent(Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        return eventoOptional;
    }

    public Optional<Evento> updateEvent(Long id,Evento evento){
        Optional<Evento> eventoDaAggiornare = eventoRepository.findById(id);
        if (eventoDaAggiornare.isPresent()){
            eventoDaAggiornare.get().setNome(evento.getNome());
            eventoDaAggiornare.get().setDescrizione(evento.getDescrizione());
            eventoDaAggiornare.get().setDataInizio(evento.getDataInizio());
            eventoDaAggiornare.get().setDataFine(evento.getDataFine());
            eventoRepository.save(eventoDaAggiornare.get());
        } else {
            return Optional.empty();
        }
        return eventoDaAggiornare;
    }


    public Optional<Evento> deleteEventById(Long id){
        Optional<Evento> eventoDaCancellareOptional = eventoRepository.findById(id);
        if(eventoDaCancellareOptional.isPresent()){
            eventoRepository.delete(eventoDaCancellareOptional.get());
            return eventoDaCancellareOptional;
        }else{
            return Optional.empty();
        }

    }
}
