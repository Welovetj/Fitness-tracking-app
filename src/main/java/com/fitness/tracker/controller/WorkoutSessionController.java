package com.fitness.tracker.controller;

import com.fitness.tracker.dto.CreateWorkoutSessionRequest;
import com.fitness.tracker.dto.WorkoutSessionDTO;
import com.fitness.tracker.service.WorkoutSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-sessions")
@RequiredArgsConstructor
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @PostMapping
    public ResponseEntity<WorkoutSessionDTO> createWorkoutSession(
            @Valid @RequestBody CreateWorkoutSessionRequest request) {
        WorkoutSessionDTO createdSession = workoutSessionService.createWorkoutSession(request);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSessionDTO> getWorkoutSessionById(@PathVariable Long id) {
        WorkoutSessionDTO session = workoutSessionService.getWorkoutSessionById(id);
        return ResponseEntity.ok(session);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSessionDTO>> getAllWorkoutSessions(
            @RequestParam(required = false) Long userId) {
        
        if (userId != null) {
            return ResponseEntity.ok(workoutSessionService.getWorkoutSessionsByUserId(userId));
        } else {
            return ResponseEntity.ok(workoutSessionService.getAllWorkoutSessions());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSession(@PathVariable Long id) {
        workoutSessionService.deleteWorkoutSession(id);
        return ResponseEntity.noContent().build();
    }
}
