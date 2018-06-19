package ftn.isamrs.tim5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.isamrs.tim5.model.System;

import java.util.List;

public interface SystemRepository extends JpaRepository<System, Long> {
    List<System> findAll();
}
