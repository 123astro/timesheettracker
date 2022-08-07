package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository <Action, Long> {
    List<Action> findAllByMatter_id(Long id);

}

