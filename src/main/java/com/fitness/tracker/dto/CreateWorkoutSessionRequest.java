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
    
    @jakarta.validation.constraints.Positive(message = "Duration must be positive")
    private Integer durationMinutes;
    
    @jakarta.validation.Valid
    private List<ExerciseEntry> exercises;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseEntry {
        @NotNull(message = "Exercise ID is required")
        private Long exerciseId;
        
        @jakarta.validation.constraints.Positive(message = "Sets must be positive")
        private Integer sets;
        
        @jakarta.validation.constraints.Positive(message = "Reps must be positive")
        private Integer reps;
        
        @jakarta.validation.constraints.Positive(message = "Weight must be positive")
        private Double weight;
        
        @jakarta.validation.constraints.Positive(message = "Duration must be positive")
        private Integer durationSeconds;
        
        @jakarta.validation.constraints.Positive(message = "Distance must be positive")
        private Double distance;
        
        private String notes;
    }
}
