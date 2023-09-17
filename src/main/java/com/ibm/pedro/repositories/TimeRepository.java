package com.ibm.pedro.repositories;

import com.ibm.pedro.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>
{
}
