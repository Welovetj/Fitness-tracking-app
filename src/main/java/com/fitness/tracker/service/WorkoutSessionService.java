package com.fitness.tracker.service;

import com.fitness.tracker.dto.CreateWorkoutSessionRequest;
import com.fitness.tracker.dto.WorkoutSessionDTO;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.model.*;
import com.fitness.tracker.repository.ExerciseRepository;
import com.fitness.tracker.repository.UserRepository;
import com.fitness.tracker.repository.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public WorkoutSessionDTO createWorkoutSession(CreateWorkoutSessionRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        WorkoutSession session = new WorkoutSession();
        session.setUser(user);
        session.setSessionDate(request.getSessionDate() != null ? request.getSessionDate() : LocalDateTime.now());
        session.setNotes(request.getNotes());
        session.setDurationMinutes(request.getDurationMinutes());

        if (request.getExercises() != null) {
            for (CreateWorkoutSessionRequest.ExerciseEntry entry : request.getExercises()) {
                Exercise exercise = exerciseRepository.findById(entry.getExerciseId())
                        .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + entry.getExerciseId()));

                WorkoutExercise workoutExercise = new WorkoutExercise();
                workoutExercise.setWorkoutSession(session);
                workoutExercise.setExercise(exercise);
                workoutExercise.setSets(entry.getSets());
                workoutExercise.setReps(entry.getReps());
                workoutExercise.setWeight(entry.getWeight());
                workoutExercise.setDurationSeconds(entry.getDurationSeconds());
                workoutExercise.setDistance(entry.getDistance());
                workoutExercise.setNotes(entry.getNotes());

                session.getExercises().add(workoutExercise);
            }
        }

        WorkoutSession savedSession = workoutSessionRepository.save(session);
        return convertToDTO(savedSession);
    }

    @Transactional(readOnly = true)
    public WorkoutSessionDTO getWorkoutSessionById(Long id) {
        WorkoutSession session = workoutSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout session not found with id: " + id));
        return convertToDTO(session);
    }

    @Transactional(readOnly = true)
    public List<WorkoutSessionDTO> getAllWorkoutSessions() {
        return workoutSessionRepository.findAllWithExercises().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<WorkoutSessionDTO> getWorkoutSessionsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return workoutSessionRepository.findByUserIdOrderBySessionDateDesc(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteWorkoutSession(Long id) {
        if (!workoutSessionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Workout session not found with id: " + id);
        }
        workoutSessionRepository.deleteById(id);
    }

    private WorkoutSessionDTO convertToDTO(WorkoutSession session) {
        WorkoutSessionDTO dto = new WorkoutSessionDTO();
        dto.setId(session.getId());
        dto.setUserId(session.getUser().getId());
        dto.setSessionDate(session.getSessionDate());
        dto.setNotes(session.getNotes());
        dto.setDurationMinutes(session.getDurationMinutes());

        List<WorkoutSessionDTO.WorkoutExerciseDTO> exerciseDTOs = session.getExercises().stream()
                .map(we -> {
                    WorkoutSessionDTO.WorkoutExerciseDTO exerciseDTO = new WorkoutSessionDTO.WorkoutExerciseDTO();
                    exerciseDTO.setId(we.getId());
                    exerciseDTO.setExerciseId(we.getExercise().getId());
                    exerciseDTO.setExerciseName(we.getExercise().getName());
                    exerciseDTO.setMuscleGroup(we.getExercise().getMuscleGroup());
                    exerciseDTO.setSets(we.getSets());
                    exerciseDTO.setReps(we.getReps());
                    exerciseDTO.setWeight(we.getWeight());
                    exerciseDTO.setDurationSeconds(we.getDurationSeconds());
                    exerciseDTO.setDistance(we.getDistance());
                    exerciseDTO.setNotes(we.getNotes());
                    return exerciseDTO;
                })
                .collect(Collectors.toList());

        dto.setExercises(exerciseDTOs);
        return dto;
    }
}
