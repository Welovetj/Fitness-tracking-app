package com.fitness.tracker.repository;

import com.fitness.tracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(Exercise.MuscleGroup muscleGroup);
    List<Exercise> findByType(Exercise.ExerciseType type);
    List<Exercise> findByNameContainingIgnoreCase(String name);
}
