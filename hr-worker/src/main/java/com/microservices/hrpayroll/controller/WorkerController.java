package com.microservices.hrpayroll.controller;

import com.microservices.hrpayroll.dto.response.WorkerResponseDTO;
import com.microservices.hrpayroll.dto.response.utils.ResponseDTO;
import com.microservices.hrpayroll.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<ResponseDTO<Page<WorkerResponseDTO>>> findAll(Pageable pageable) {
        Page<WorkerResponseDTO> workers = workerService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(workers));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<WorkerResponseDTO>> findById(@PathVariable Long id) {
        WorkerResponseDTO responseDTO = workerService.findWorkById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(responseDTO));
    }

}
