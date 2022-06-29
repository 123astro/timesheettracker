package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
