
# Space Reservation System

## Overview
The **Space Reservation System** is an application designed to manage the reservation of spaces such as meeting rooms, auditoriums, or any other reservable area. The application allows users to register, log in, and manage their reservations. Administrators can manage available spaces, assign roles to users, and keep the information up to date.

## Key Features
- **User Management:** User registration, login, and profile management.
- **Space Management:** Viewing spaces, registering, updating, and deleting spaces.
- **Reservation Management:** Creating and viewing reservations.

## Prerequisites
Before you begin, make sure you have the following installed:
- Docker
- Docker Compose
- Git

## Getting Started

### 1. Clone the Repository
Start by cloning the repository to your local machine:
```bash
git clone https://github.com/your-username/space-reservation-system.git
cd space-reservation-system
```

### 2. Build and Run the Application with Docker
To build and run the application using Docker, follow these steps:

```bash
# Pull the Docker image
docker pull atuhome/appbooking:0.1.2

# Start the containers
docker-compose up
```

This will start all the necessary containers, including the application and the MySQL database.

### 3. Accessing the Application
Once the containers are up and running, you can access the application in your browser at:

Aquí tienes los nombres de las API junto con sus URL y paths:

### API Names and Paths

1. **User Registration**
   - URL: `http://localhost:8081`
   - Path: `/api/users/register`

2. **User Login**
   - URL: `http://localhost:8081`
   - Path: `/api/users/login`

3. **Register Type of Space**
   - URL: `http://localhost:8081`
   - Path: `/api/typespaces/register`

4. **Register Space**
   - URL: `http://localhost:8081`
   - Path: `/api/spaces/register`

5. **Massive Register Space**
   - URL: `http://localhost:8081`
   - Path: `/api/spaces/massive-register`

6. **Filter Space**
   - URL: `http://localhost:8081`
   - Path: `/api/spaces/filter`

7. **Create Reservation**
   - URL: `http://localhost:8081`
   - Path: `/api/reservations`

8. **Get Available Space**
   - URL: `http://localhost:8081`
   - Path: `/api/reservations/floor-date`

9. **Get All Spaces**
   - URL: `http://localhost:8081`
   - Path: `/api/spaces`

10. **Get All Small Spaces**
    - URL: `http://localhost:8081`
    - Path: `/api/spaces/small-spaces`

11. **Get Spaces by Floor**
    - URL: `http://localhost:8081`
    - Path: `/api/spaces/floor/{floor}`

12. **Check Space Availability**
    - URL: `http://localhost:8081`
    - Path: `/api/spaces/available/{id}`

13. **Get Reservation by ID**
    - URL: `http://localhost:8081`
    - Path: `/api/reservations/{id}`

14. **Get Reservations by User**
    - URL: `http://localhost:8081`
    - Path: `/api/reservations/user/`

15. **Get Reservations by Space**
    - URL: `http://localhost:8081`
    - Path: `/api/reservations/space/{spaceId}`


### 4. Running Tests
To run the tests for the application, you can use the following command:
```bash
docker-compose exec app mvn test
```

### 5. Stopping the Application
To stop the application and remove the containers, use the following command:
```bash
docker-compose down
```

## Project Structure
- **/src**: Contains the source code of the application.
- **/docker-compose.yml**: Docker Compose file for building and running the application.
- **/README.md**: Project documentation.
