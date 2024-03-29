package com.esercizio.calendario.controllers;

import com.esercizio.calendario.entities.Evento;
import com.esercizio.calendario.entities.User;
import com.esercizio.calendario.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/addevent")
    public ResponseEntity<Evento> addEvento(@RequestBody Evento evento){
        if (evento.getCalendario().getId() == null) {
        eventoService.addEvent(evento);
        return ResponseEntity.ok().body(evento);
        }
        return ResponseEntity.ok().body(evento);
    }

    @GetMapping("/getlist")
    public ResponseEntity<List<Evento>> getAllEvent(){
        List<Evento> allEvents = eventoService.getAllEvents();
        return ResponseEntity.ok().body(allEvents);
    }

    @GetMapping("/getevent/{id}")
    public ResponseEntity<Optional<Evento>> getEvent(@PathVariable Long id){
        Optional<Evento> eventoOptional = eventoService.getEvent(id);
        return ResponseEntity.ok().body(eventoOptional);
    }

    @PutMapping("/updateevent/{id}")
    public ResponseEntity<Evento> updateEventById(@RequestBody Evento evento,@PathVariable Long id){
        Optional<Evento> eventoOptional = eventoService.updateEvent(id,evento);
        if(eventoOptional.isPresent()){
            return ResponseEntity.ok().body(eventoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/deleteid")
    public ResponseEntity<Optional<Evento>> deleteEventById(@RequestParam Long id){
        Optional<Evento> eventoDaCancellare = eventoService.deleteEventById(id);
        if(eventoDaCancellare.isPresent()){
            return ResponseEntity.ok().body(eventoDaCancellare);
        }
        return ResponseEntity.notFound().build();

    }
}
