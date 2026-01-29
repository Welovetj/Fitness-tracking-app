package com.fitness.tracker.config;

import com.fitness.tracker.model.Exercise;
import com.fitness.tracker.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    @Override
    public void run(String... args) {
        // Initialize some default exercises
        createExercise("Bench Press", "Classic chest exercise", Exercise.MuscleGroup.CHEST, Exercise.ExerciseType.STRENGTH);
        createExercise("Squat", "Compound leg exercise", Exercise.MuscleGroup.LEGS, Exercise.ExerciseType.STRENGTH);
        createExercise("Deadlift", "Full body compound movement", Exercise.MuscleGroup.BACK, Exercise.ExerciseType.STRENGTH);
        createExercise("Shoulder Press", "Overhead pressing movement", Exercise.MuscleGroup.SHOULDERS, Exercise.ExerciseType.STRENGTH);
        createExercise("Pull-ups", "Bodyweight back exercise", Exercise.MuscleGroup.BACK, Exercise.ExerciseType.STRENGTH);
        createExercise("Bicep Curls", "Isolation exercise for biceps", Exercise.MuscleGroup.ARMS, Exercise.ExerciseType.STRENGTH);
        createExercise("Tricep Dips", "Compound arm exercise", Exercise.MuscleGroup.ARMS, Exercise.ExerciseType.STRENGTH);
        createExercise("Lunges", "Single-leg exercise", Exercise.MuscleGroup.LEGS, Exercise.ExerciseType.STRENGTH);
        createExercise("Plank", "Core stability exercise", Exercise.MuscleGroup.CORE, Exercise.ExerciseType.STRENGTH);
        createExercise("Running", "Cardiovascular endurance", Exercise.MuscleGroup.CARDIO, Exercise.ExerciseType.CARDIO);
        createExercise("Cycling", "Low-impact cardio", Exercise.MuscleGroup.CARDIO, Exercise.ExerciseType.CARDIO);
        createExercise("Jump Rope", "High-intensity cardio", Exercise.MuscleGroup.CARDIO, Exercise.ExerciseType.CARDIO);
        createExercise("Lat Pulldown", "Back width exercise", Exercise.MuscleGroup.BACK, Exercise.ExerciseType.STRENGTH);
        createExercise("Leg Press", "Quad-focused exercise", Exercise.MuscleGroup.LEGS, Exercise.ExerciseType.STRENGTH);
        createExercise("Crunches", "Abdominal exercise", Exercise.MuscleGroup.CORE, Exercise.ExerciseType.STRENGTH);
    }

    private void createExercise(String name, String description, Exercise.MuscleGroup muscleGroup, Exercise.ExerciseType type) {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMuscleGroup(muscleGroup);
        exercise.setType(type);
        exerciseRepository.save(exercise);
    }
}
