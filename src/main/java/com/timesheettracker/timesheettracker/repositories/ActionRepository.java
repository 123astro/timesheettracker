package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository <Action, Long> {
}


