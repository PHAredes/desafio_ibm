package com.ibm.pedro.repositories;

import com.ibm.pedro.model.JogadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<JogadorEntity, Long> {
    
}
