package project.SchedulingProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.SchedulingProject.infrastructue.entity.Scheduling;
import project.SchedulingProject.repository.SchedulingRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;

    public Scheduling save(Scheduling scheduling) {
        LocalDateTime SchedulingTime = scheduling.getSchedulingDateTime();
        LocalDateTime EndTime = scheduling.getSchedulingDateTime().plusHours(1);;

     Scheduling schedule = schedulingRepository.findByServiceAndSchedulingDateTimeBetween(scheduling.getService(), SchedulingTime, EndTime);

     if(Objects.nonNull(schedule)) {
         throw new RuntimeException("Scheduling already exists");
     }
     return schedulingRepository.save(scheduling);
    }
}