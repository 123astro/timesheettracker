package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Attorney;
import com.timesheettracker.timesheettracker.models.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttorneyRepository extends JpaRepository<Attorney, Long> {
    List<Attorney> findAllById(Long id);

}
