package com.microservices.hrworker.repositories;

import com.microservices.hrworker.entities.Worker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Optional<Worker> findById(Long id);

    Page<Worker> findAll(Pageable pageable);
}
