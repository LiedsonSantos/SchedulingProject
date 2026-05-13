package project.SchedulingProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.SchedulingProject.infrastructue.entity.Scheduling;

import java.time.LocalDateTime;


public interface SchedulingRepository extends JpaRepository<Scheduling,Integer> {

    Scheduling findByServiceAndSchedulingDateTimeBetween(String service,
                                                         LocalDateTime startDateTime, LocalDateTime endDateTime);
}
