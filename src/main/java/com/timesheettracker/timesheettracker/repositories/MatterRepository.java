package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.Action;
import com.timesheettracker.timesheettracker.models.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatterRepository extends JpaRepository<Matter, Long> {
   List<Matter> findAllByClient_id(Long id);
   //List<Matter> findAllByAttorney_id(Long id);
   List<Matter> findAllByClient_Attorney_id(Long id);
   //add to list matters

}
