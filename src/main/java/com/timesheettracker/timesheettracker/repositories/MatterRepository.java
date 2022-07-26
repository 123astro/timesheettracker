package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.models.Matter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatterRepository extends JpaRepository<Matter, Long> {
   List<Matter> findAllByClient_id(Long id);

}
