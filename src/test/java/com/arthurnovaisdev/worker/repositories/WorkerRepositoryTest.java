package com.arthurnovaisdev.worker.repositories;

import com.arthurnovaisdev.worker.entities.Department;
import com.arthurnovaisdev.worker.entities.Worker;
import com.arthurnovaisdev.worker.entities.WorkerLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldSaveAndFindWorkerById() {
        Department design = new Department("Design");
        departmentRepository.save(design);

        Worker worker = new Worker("Alex", WorkerLevel.MID_LEVEL, 1200.0, design);
        workerRepository.save(worker);

        Optional<Worker> found = workerRepository.findById(worker.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Alex");
        assertThat(found.get().getDepartament().getName()).isEqualTo("Design");
    }

    @Test
    void shouldReturnEmptyWhenWorkerDoesNotExist() {
        Optional<Worker> found = workerRepository.findById(999L);

        assertThat(found).isEmpty();
    }
}