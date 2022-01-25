package com.microservices.hrpayroll.services;

import com.microservices.hrpayroll.dto.response.WorkerResponseDTO;
import com.microservices.hrpayroll.entities.Worker;
import com.microservices.hrpayroll.exception.WorkerException;
import com.microservices.hrpayroll.repositories.WorkerRepository;
import com.microservices.hrpayroll.utils.ErrorMessages;
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
