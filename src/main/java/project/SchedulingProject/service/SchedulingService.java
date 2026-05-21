package project.SchedulingProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.SchedulingProject.infrastructue.entity.Scheduling;
import project.SchedulingProject.repository.SchedulingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;

    public Scheduling save(Scheduling scheduling) {
        LocalDateTime SchedulingTime = scheduling.getSchedulingDateTime();
        LocalDateTime EndTime = scheduling.getSchedulingDateTime().plusHours(1);

        Scheduling scheduled = schedulingRepository.findByServiceAndSchedulingDateTimeBetween(scheduling.getService(), SchedulingTime, EndTime);

        if (Objects.nonNull(scheduled)) {
            throw new RuntimeException("Scheduling already exists");
        }
        return schedulingRepository.save(scheduling);
    }

    public void deleteScheduling(LocalDateTime schedulingDateTime, String client) {
        schedulingRepository.deleteBySchedulingDateTimeAndClient(schedulingDateTime, client);

    }

    public List<Scheduling> findSchedulingToday(LocalDate Date) {
        LocalDateTime initialDateTime = Date.atStartOfDay();
        LocalDateTime finalDateTime = Date.atTime(23, 59, 59);

        return schedulingRepository.findBySchedulingDateTimeBetween(initialDateTime, finalDateTime);
    }

    public Scheduling changeSchedule(Scheduling scheduling, String client, LocalDateTime schedulingdateTime) {
        Scheduling schedule = schedulingRepository.findBySchedulingDateTimeAndClient(schedulingdateTime, client);

        if (Objects.isNull(schedule)) {
            throw new RuntimeException("Scheduling not found");
        }
        scheduling.setId(schedule.getId());
        return schedulingRepository.save(scheduling);
    }
}