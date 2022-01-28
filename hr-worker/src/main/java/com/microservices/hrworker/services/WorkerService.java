package com.microservices.hrworker.services;

import com.microservices.hrworker.dto.response.WorkerResponseDTO;
import com.microservices.hrworker.entities.Worker;
import com.microservices.hrworker.exception.WorkerException;
import com.microservices.hrworker.repositories.WorkerRepository;
import com.microservices.hrworker.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public WorkerResponseDTO findWorkById(Long id) {
        Worker worker = findById(id);
        return entityToWorkerResponseDTO(worker);
    }

    public Page<WorkerResponseDTO> findAll(Pageable pageable) {
        Page<Worker> workers = workerRepository.findAll(pageable);
        return workers.map(this::entityToWorkerResponseDTO);
    }

    private Worker findById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new WorkerException(ErrorMessages.WORKER_NOT_FOUND, id));
    }

    private WorkerResponseDTO entityToWorkerResponseDTO(Worker worker) {
        return WorkerResponseDTO.builder()
                .id(worker.getId())
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .build();
    }
}
