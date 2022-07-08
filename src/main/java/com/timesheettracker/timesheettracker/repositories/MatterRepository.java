package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatterRepository extends JpaRepository<Matter, Long> {
}
