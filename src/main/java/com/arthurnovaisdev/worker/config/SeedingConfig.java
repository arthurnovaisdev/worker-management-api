package com.arthurnovaisdev.worker.config;

import com.arthurnovaisdev.worker.entities.Department;
import com.arthurnovaisdev.worker.entities.HourContract;
import com.arthurnovaisdev.worker.entities.Worker;
import com.arthurnovaisdev.worker.entities.WorkerLevel;
import com.arthurnovaisdev.worker.repositories.DepartmentRepository;
import com.arthurnovaisdev.worker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Configuration
public class SeedingConfig implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        Department dept = new Department("Design");

        dept = departmentRepository.save(dept);

        Worker worker = new Worker("Alex", WorkerLevel.MID_LEVEL, 1200.0, dept);

        worker = workerRepository.save(worker);

        HourContract contract1 = new HourContract(LocalDate.of(2026, 7, 15), 60.0, 25);
        HourContract contract2 = new HourContract(LocalDate.of(2026, 2, 10), 50.0, 10);
        HourContract contract3 = new HourContract(LocalDate.of(2026, 5, 7), 45.0, 17);

        worker.addContract(contract1);
        worker.addContract(contract2);
        worker.addContract(contract3);

    }
}
