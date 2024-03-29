package com.esercizio.calendario.controllers;

import com.esercizio.calendario.entities.Calendario;
import com.esercizio.calendario.entities.User;
import com.esercizio.calendario.services.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private CalendarioService calendarioService;

    @PostMapping("/addcalendar")
    public ResponseEntity<Calendario> addCalendar(@RequestBody Calendario calendario){
        calendarioService.addCalendario(calendario);
        return ResponseEntity.ok().body(calendario);
    }

    @GetMapping("/getlist")
    public ResponseEntity<List<Calendario>> getAllCalendar(){
        List<Calendario> allCalendar = calendarioService.getAllCalendar();
        return ResponseEntity.ok().body(allCalendar);
    }

    @GetMapping("/getcalendar/{id}")
    public ResponseEntity<Optional<Calendario>> getCalendar(@PathVariable Long id){
        Optional<Calendario> calendarioOptional = calendarioService.getCalendar(id);
        return ResponseEntity.ok().body(calendarioOptional);
    }

    @PutMapping("/updatecalendar/{id}")
    public ResponseEntity<Calendario> updateCalendarById(@RequestBody Calendario calendario,@PathVariable Long id){
        Optional<Calendario> calendarioOptional = calendarioService.updateCalendar(id,calendario);
        if(calendarioOptional.isPresent()){
            return ResponseEntity.ok().body(calendarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/deleteid")
    public ResponseEntity<Optional<Calendario>> deleteCalendarById(@RequestParam Long id){
        Optional<Calendario> calendarDaCancellare = calendarioService.deleteCalendarById(id);
        if(calendarDaCancellare.isPresent()){
            return ResponseEntity.ok().body(calendarDaCancellare);
        }
        return ResponseEntity.notFound().build();

    }
}
