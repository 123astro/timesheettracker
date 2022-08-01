package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatterRepository extends JpaRepository<Matter, Long> {
   List<Matter> findAllByClient_id(Long id);
}
