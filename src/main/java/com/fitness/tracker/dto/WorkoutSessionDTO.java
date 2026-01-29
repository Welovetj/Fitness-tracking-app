package com.fitness.tracker.dto;

import com.fitness.tracker.model.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSessionDTO {
    private Long id;
    private Long userId;
    private LocalDateTime sessionDate;
    private String notes;
    private Integer durationMinutes;
    private List<WorkoutExerciseDTO> exercises = new ArrayList<>();
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkoutExerciseDTO {
        private Long id;
        private Long exerciseId;
        private String exerciseName;
        private Exercise.MuscleGroup muscleGroup;
        private Integer sets;
        private Integer reps;
        private Double weight;
        private Integer durationSeconds;
        private Double distance;
        private String notes;
    }
}
