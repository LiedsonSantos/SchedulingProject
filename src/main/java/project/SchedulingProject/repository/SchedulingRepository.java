package project.SchedulingProject.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import project.SchedulingProject.entity.Scheduling;

import java.time.LocalDateTime;
import java.util.List;


public interface SchedulingRepository extends JpaRepository<Scheduling,Integer> {

    Scheduling findByServiceAndSchedulingDateTimeBetween(String service,
                                                         LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Transactional
    void deleteBySchedulingDateTimeAndClient(LocalDateTime schedulingDateTime, String client);

    List<Scheduling> findBySchedulingDateTimeBetween(LocalDateTime initialDateTime, LocalDateTime finalDateTime);

    Scheduling findBySchedulingDateTimeAndClient(LocalDateTime schedulingDateTime, String client);






}



