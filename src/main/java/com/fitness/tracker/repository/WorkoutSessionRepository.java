package com.fitness.tracker.repository;

import com.fitness.tracker.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    List<WorkoutSession> findByUserId(Long userId);
    
    @Query("SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercises we LEFT JOIN FETCH we.exercise WHERE ws.user.id = :userId ORDER BY ws.sessionDate DESC")
    List<WorkoutSession> findByUserIdOrderBySessionDateDesc(Long userId);
    
    List<WorkoutSession> findByUserIdAndSessionDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercises we LEFT JOIN FETCH we.exercise")
    List<WorkoutSession> findAllWithExercises();
}
