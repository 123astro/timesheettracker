package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Attorney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttorneyRepository extends JpaRepository<Attorney, Long> {
}
