package com.arthurnovaisdev.worker.repositories;

import com.arthurnovaisdev.worker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
