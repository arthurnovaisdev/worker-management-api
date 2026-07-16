package com.arthurnovaisdev.controller;

import com.arthurnovaisdev.worker.entities.Department;
import com.arthurnovaisdev.worker.entities.Worker;
import com.arthurnovaisdev.worker.entities.WorkerLevel;
import com.arthurnovaisdev.worker.repositories.DepartmentRepository;
import com.arthurnovaisdev.worker.repositories.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WorkerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Long workerId;

    @BeforeEach
    void setUp() {
        Department design = new Department("Design");
        departmentRepository.save(design);

        Worker worker = new Worker("Alex", WorkerLevel.MID_LEVEL, 1200.0, design);
        workerRepository.save(worker);

        workerId = worker.getId();
    }

    @Test
    void shouldReturnWorkerIncome() throws Exception {
        mockMvc.perform(get("/workers/{id}/income/{year}/{month}", workerId, 2018, 8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Alex")))
                .andExpect(jsonPath("$.department", is("Design")));
    }

    @Test
    void shouldReturn404ForNonExistentWorker() throws Exception {
        mockMvc.perform(get("/workers/{id}/income/{year}/{month}", 999L, 2018, 8))
                .andExpect(status().isNotFound());
    }
}