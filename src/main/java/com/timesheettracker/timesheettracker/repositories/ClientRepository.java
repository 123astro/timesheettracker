package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByAttorney_id(Long id);
}
