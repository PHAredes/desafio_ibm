package com.ibm.pedro.repositories;

import com.ibm.pedro.model.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {

}
