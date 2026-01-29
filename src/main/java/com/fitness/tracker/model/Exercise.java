package com.fitness.tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Exercise name is required")
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;
    
    @Enumerated(EnumType.STRING)
    private ExerciseType type;
    
    public enum MuscleGroup {
        CHEST, BACK, SHOULDERS, ARMS, LEGS, CORE, CARDIO, FULL_BODY
    }
    
    public enum ExerciseType {
        STRENGTH, CARDIO, FLEXIBILITY, BALANCE
    }
}
