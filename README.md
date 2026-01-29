# Gym Workout Tracker 💪

A comprehensive gym workout tracking application built with Spring Boot. This application allows users to track their fitness journey by logging workouts, exercises, and monitoring progress over time.

## Features

- **User Management**: Create and manage user profiles with personal metrics (weight, height)
- **Exercise Library**: Pre-loaded database of common gym exercises categorized by muscle groups
- **Workout Sessions**: Log complete workout sessions with multiple exercises
- **Detailed Tracking**: Track sets, reps, weight, duration, and distance for each exercise
- **REST API**: Full-featured RESTful API for all operations
- **Data Persistence**: H2 in-memory database (easily switchable to MySQL/PostgreSQL)
- **Exception Handling**: Global exception handling with meaningful error messages
- **Validation**: Input validation for all API endpoints

## Tech Stack

- **Backend Framework**: Spring Boot 3.2.1
- **Language**: Java 17
- **Database**: H2 (in-memory)
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Additional Libraries**: 
  - Lombok (reduces boilerplate code)
  - Spring Boot Validation
  - Spring Boot Web

## Project Structure

```
src/main/java/com/fitness/tracker/
├── config/           # Configuration classes and data initialization
├── controller/       # REST API controllers
├── dto/             # Data Transfer Objects
├── exception/       # Custom exceptions and global exception handler
├── model/           # JPA entity classes
├── repository/      # Spring Data JPA repositories
└── service/         # Business logic layer
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation & Running

1. Clone the repository:
```bash
git clone https://github.com/Welovetj/Fitness-tracking-app.git
cd Fitness-tracking-app
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Accessing H2 Console

The H2 database console is available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:workoutdb`
- Username: `sa`
- Password: (leave blank)

## API Documentation

### Users API

#### Create User
```http
POST /api/users
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "weight": 75.5,
  "height": 180.0
}
```

#### Get All Users
```http
GET /api/users
```

#### Get User by ID
```http
GET /api/users/{id}
```

#### Update User
```http
PUT /api/users/{id}
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "weight": 76.0,
  "height": 180.0
}
```

#### Delete User
```http
DELETE /api/users/{id}
```

### Exercises API

#### Create Exercise
```http
POST /api/exercises
Content-Type: application/json

{
  "name": "Barbell Row",
  "description": "Back exercise for thickness",
  "muscleGroup": "BACK",
  "type": "STRENGTH"
}
```

#### Get All Exercises
```http
GET /api/exercises
```

#### Get Exercises by Muscle Group
```http
GET /api/exercises?muscleGroup=CHEST
```

#### Search Exercises
```http
GET /api/exercises?search=press
```

#### Get Exercise by ID
```http
GET /api/exercises/{id}
```

#### Update Exercise
```http
PUT /api/exercises/{id}
```

#### Delete Exercise
```http
DELETE /api/exercises/{id}
```

**Available Muscle Groups**: CHEST, BACK, SHOULDERS, ARMS, LEGS, CORE, CARDIO, FULL_BODY

**Available Exercise Types**: STRENGTH, CARDIO, FLEXIBILITY, BALANCE

### Workout Sessions API

#### Create Workout Session
```http
POST /api/workout-sessions
Content-Type: application/json

{
  "userId": 1,
  "sessionDate": "2024-01-15T10:30:00",
  "notes": "Great chest day!",
  "durationMinutes": 60,
  "exercises": [
    {
      "exerciseId": 1,
      "sets": 4,
      "reps": 10,
      "weight": 80.0,
      "notes": "Felt strong today"
    },
    {
      "exerciseId": 2,
      "sets": 3,
      "reps": 12,
      "weight": 60.0
    }
  ]
}
```

#### Get All Workout Sessions
```http
GET /api/workout-sessions
```

#### Get Workout Sessions by User
```http
GET /api/workout-sessions?userId=1
```

#### Get Workout Session by ID
```http
GET /api/workout-sessions/{id}
```

#### Delete Workout Session
```http
DELETE /api/workout-sessions/{id}
```

## Data Models

### User
- id (Long)
- username (String, unique)
- email (String, unique)
- fullName (String)
- weight (Double, in kg)
- height (Double, in cm)
- createdAt (LocalDateTime)

### Exercise
- id (Long)
- name (String)
- description (String)
- muscleGroup (Enum)
- type (Enum)

### WorkoutSession
- id (Long)
- user (User)
- sessionDate (LocalDateTime)
- notes (String)
- durationMinutes (Integer)
- exercises (List of WorkoutExercise)

### WorkoutExercise
- id (Long)
- workoutSession (WorkoutSession)
- exercise (Exercise)
- sets (Integer)
- reps (Integer)
- weight (Double, in kg)
- durationSeconds (Integer)
- distance (Double, in km)
- notes (String)

## Testing the API

You can test the API using tools like:
- Postman
- cURL
- HTTPie
- Any REST client

### Example cURL Commands

Create a user:
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"athlete1","email":"athlete@gym.com","fullName":"Test Athlete","weight":80,"height":175}'
```

Get all exercises:
```bash
curl http://localhost:8080/api/exercises
```

Create a workout session:
```bash
curl -X POST http://localhost:8080/api/workout-sessions \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"notes":"Leg day","durationMinutes":45,"exercises":[{"exerciseId":2,"sets":5,"reps":5,"weight":100}]}'
```

## Pre-loaded Exercises

The application comes with 15 pre-loaded exercises covering all major muscle groups:
- Bench Press, Squat, Deadlift
- Shoulder Press, Pull-ups
- Bicep Curls, Tricep Dips
- Lunges, Leg Press
- Plank, Crunches
- Running, Cycling, Jump Rope
- Lat Pulldown

## Error Handling

The API returns appropriate HTTP status codes and error messages:
- `200 OK` - Successful GET/PUT requests
- `201 Created` - Successful POST requests
- `204 No Content` - Successful DELETE requests
- `400 Bad Request` - Validation errors
- `404 Not Found` - Resource not found
- `409 Conflict` - Duplicate resource (e.g., username/email already exists)
- `500 Internal Server Error` - Server errors

## Future Enhancements

- User authentication and authorization with Spring Security
- Progress tracking and analytics
- Workout templates and plans
- Personal records tracking
- Social features (sharing workouts, following friends)
- Mobile app integration
- Export workout data
- Integration with fitness wearables

## License

This project is open source and available for educational and personal use.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Contact

For questions or feedback, please open an issue in the GitHub repository.