# Fitness Monolith Application

A comprehensive fitness tracking and recommendation system built with Spring Boot. This monolith application allows users to register, track their fitness activities, and receive personalized recommendations based on their workout data.

## 🚀 Features

- **User Authentication**: User registration and management
- **Activity Tracking**: Track various fitness activities with metrics
- **Personalized Recommendations**: AI-powered fitness recommendations based on user activities
- **Multiple Activity Types**: Support for running, walking, cycling, swimming, weight training, yoga, HIIT, cardio, stretching, and more
- **RESTful API**: Clean and well-structured REST endpoints
- **MySQL Database**: Persistent data storage with JPA/Hibernate

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Data JPA** - Database abstraction layer
- **MySQL** - Relational database
- **Lombok** - Reduces boilerplate code
- **Jackson** - JSON processing
- **Maven** - Dependency management and build tool

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

## 🗄️ Database Setup

1. Create a MySQL database:
   ```sql
   CREATE DATABASE fitness_demo;
   ```

2. Update the database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/fitness_demo
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## ⚙️ Configuration

The application runs on port **8082** by default. You can modify this in `application.properties`:

```properties
server.port=8082
spring.application.name=fitness-monolith
```

## 🏃 Running the Application

### Using Maven

```bash
# Navigate to the project directory
cd fitness-monolith

# Run the application
./mvnw spring-boot:run
```

Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

### Using IDE

1. Import the project into your IDE
2. Run the `FitnessMonolithApplication.java` class

The application will start on `http://localhost:8082`

## 📡 API Endpoints

### Authentication

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}
```

**Response:**
```json
{
  "id": "user-uuid",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2024-01-01T10:00:00"
}
```

### Activities

#### Track Activity
```http
POST /api/activities
Content-Type: application/json

{
  "userId": "user-uuid",
  "activityType": "RUNNING",
  "duration": 30,
  "caloriesBurned": 300,
  "startTime": "2024-01-01T08:00:00",
  "additionalMetrics": {
    "distance": 5.0,
    "pace": "6:00",
    "heartRate": 150
  }
}
```

#### Get User Activities
```http
GET /api/activities
X-User-ID: user-uuid
```

**Response:**
```json
[
  {
    "id": "activity-uuid",
    "activityType": "RUNNING",
    "duration": 30,
    "caloriesBurned": 300,
    "startTime": "2024-01-01T08:00:00",
    "additionalMetrics": {
      "distance": 5.0,
      "pace": "6:00"
    }
  }
]
```

### Recommendations

#### Generate Recommendation
```http
POST /api/recommendation/generate
Content-Type: application/json

{
  "userId": "user-uuid"
}
```

**Response:**
```json
{
  "id": "recommendation-uuid",
  "type": "IMPROVEMENT",
  "recommendation": "Based on your recent activities...",
  "improvements": ["Increase duration", "Add variety"],
  "suggestions": ["Try interval training", "Focus on recovery"],
  "safety": ["Stay hydrated", "Warm up properly"]
}
```

## 📁 Project Structure

```
fitness-monolith/
├── src/
│   ├── main/
│   │   ├── java/com/project/fitness/
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── ActivityController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   └── RecommendationController.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── ActivityRequest.java
│   │   │   │   ├── ActivityResponse.java
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   └── UserResponse.java
│   │   │   ├── model/               # Entity Models
│   │   │   │   ├── User.java
│   │   │   │   ├── Activity.java
│   │   │   │   ├── ActivityType.java
│   │   │   │   └── Recommendation.java
│   │   │   ├── repository/          # Data Access Layer
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ActivityRepository.java
│   │   │   │   └── RecommendationRepository.java
│   │   │   ├── service/             # Business Logic
│   │   │   │   ├── UserService.java
│   │   │   │   ├── ActivityService.java
│   │   │   │   └── RecommendationService.java
│   │   │   └── FitnessMonolithApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Test files
└── pom.xml                          # Maven configuration
```

## 🎯 Activity Types

The application supports the following activity types:

- `RUNNING`
- `WALKING`
- `CYCLING`
- `SWIMMING`
- `WEIGHT_TRAINING`
- `YOGA`
- `HIIT`
- `CARDIO`
- `STRETCHING`
- `OTHER`

## 🔐 Database Schema

### Users Table
- `id` (UUID, Primary Key)
- `email` (String)
- `password` (String)
- `firstName` (String)
- `lastName` (String)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

### Activities Table
- `id` (UUID, Primary Key)
- `user_id` (UUID, Foreign Key)
- `activityType` (Enum)
- `duration` (Integer)
- `caloriesBurned` (Integer)
- `startTime` (Timestamp)
- `additionalMetrics` (JSON)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

### Recommendations Table
- `id` (UUID, Primary Key)
- `user_id` (UUID, Foreign Key)
- `activity_id` (UUID, Foreign Key)
- `type` (String)
- `recommendation` (String, 2000 chars)
- `improvements` (JSON Array)
- `suggestions` (JSON Array)
- `safety` (JSON Array)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

## 🧪 Testing

Run tests using Maven:

```bash
./mvnw test
```

## 📝 Development Notes

- The application uses JPA with Hibernate for ORM
- Database schema is automatically updated on startup (`spring.jpa.hibernate.ddl-auto=update`)
- SQL queries are logged for debugging (`spring.jpa.show-sql=true`)
- UUIDs are used as primary keys for all entities
- JSON columns are used for flexible metric storage

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**mishraharsh6244**

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MySQL community for the robust database system

---

For more information or support, please open an issue in the repository.
