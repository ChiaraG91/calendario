package com.esercizio.calendario.repositories;

import com.esercizio.calendario.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT user FROM User user WHERE user.nome = :nome AND user.cognome = :cognome")
    User findByNomeAndCognome(@Param("nome") String nome, @Param("cognome") String cognome);
}
