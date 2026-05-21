package project.SchedulingProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.SchedulingProject.infrastructue.entity.Scheduling;
import project.SchedulingProject.service.SchedulingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/scheduling")
@RequiredArgsConstructor
public class SchedulingController {

    private final SchedulingService schedulingService;

    @PostMapping
    public ResponseEntity<Scheduling> saveScheduling(@RequestBody Scheduling scheduling) {
        return ResponseEntity.ok().body(schedulingService.save(scheduling));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteScheduling(@RequestParam String client,
                                                 @RequestParam LocalDateTime schedulingDateTime) {
        schedulingService.deleteScheduling(schedulingDateTime, client);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Scheduling>> getAllSchedulings(@RequestParam LocalDate schedulingDate) {
        return ResponseEntity.ok().body(schedulingService.findSchedulingToday(schedulingDate));
    }

    @PutMapping
    public ResponseEntity<Scheduling> updateScheduling(@RequestParam String client,
                                                       @RequestBody Scheduling scheduling,
                                                       @RequestParam LocalDateTime schedulingDateTime) {
        return ResponseEntity.accepted().body(schedulingService.changeSchedule(scheduling, client, schedulingDateTime));
    }

}
