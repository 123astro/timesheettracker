package com.timesheettracker.timesheettracker.repositories;

import com.timesheettracker.timesheettracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
