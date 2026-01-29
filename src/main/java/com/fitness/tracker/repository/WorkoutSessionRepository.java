package com.fitness.tracker.repository;

import com.fitness.tracker.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    List<WorkoutSession> findByUserId(Long userId);
    List<WorkoutSession> findByUserIdOrderBySessionDateDesc(Long userId);
    List<WorkoutSession> findByUserIdAndSessionDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
