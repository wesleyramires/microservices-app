package com.microservices.hrworker.controller;

import com.microservices.hrworker.dto.response.WorkerResponseDTO;
import com.microservices.hrworker.dto.response.utils.ResponseDTO;
import com.microservices.hrworker.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${test.config}")
    private String testConfig;

    @Autowired
    private WorkerService workerService;

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getProfilleConfig() {
        System.out.println("CONFIG: ".concat(testConfig));
        return ResponseEntity.noContent().build();
    }

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
