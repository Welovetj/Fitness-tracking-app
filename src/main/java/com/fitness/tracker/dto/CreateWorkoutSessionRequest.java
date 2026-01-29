package com.fitness.tracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutSessionRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private LocalDateTime sessionDate;
    private String notes;
    private Integer durationMinutes;
    private List<ExerciseEntry> exercises;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseEntry {
        @NotNull(message = "Exercise ID is required")
        private Long exerciseId;
        private Integer sets;
        private Integer reps;
        private Double weight;
        private Integer durationSeconds;
        private Double distance;
        private String notes;
    }
}
