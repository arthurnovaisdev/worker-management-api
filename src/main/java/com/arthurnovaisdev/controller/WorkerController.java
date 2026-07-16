package com.arthurnovaisdev.controller;

import com.arthurnovaisdev.worker.dto.WorkerIncomeDTO;
import com.arthurnovaisdev.worker.entities.Worker;
import com.arthurnovaisdev.worker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value =  "/workers")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping(value = "/{id}/income/{year}/{month}")
    public WorkerIncomeDTO getIncome(@PathVariable Long id, @PathVariable Integer year, @PathVariable Integer month) {

        Worker worker = workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker not found"));

        return new WorkerIncomeDTO(worker.getName(), worker.getDepartament().getName(), worker.income(year, month));
    }
}
