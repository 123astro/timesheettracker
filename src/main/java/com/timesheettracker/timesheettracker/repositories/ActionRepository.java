package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository <Action, Long> {


}


