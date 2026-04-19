Carpooling Platform Backend
Welcome to the Carpooling Platform! This is a robust backend system designed to connect drivers with empty seats to passengers looking for a ride.

At its core, this application ensures that rides are safely managed, users are tracked, and vehicles are never overbooked.

What Does It Do? (Core Features)
This platform handles three main things:

User Management: People can register on the platform. Every user can act as either a Driver or a Passenger.

Ride Management: Drivers can post a new ride, specifying where they are leaving from, where they are going, what time they leave, and how many empty seats they have. Passengers can search for available rides between specific cities.

Smart Booking System: Passengers can book seats on a ride. The system is smart enough to check how many seats are already taken and will automatically reject a booking if it exceeds the car's capacity.

Tech Stack
This project is built using modern, industry-standard tools:

Language: Java 17
Framework: Spring Boot 3.2.x (Spring Web, Spring Data JPA, Spring Validation)
Database: MySQL
Tools: Maven, Lombok (for cleaner code)

How It Works Under the Hood
The application is built using a clean, layered architecture:
Controllers: The "front desk" of the app. They receive web requests (like "book a ride") and return responses.
Services: The "brain" of the app. This is where the business rules live (e.g., checking if a user exists or calculating if a car has enough empty seats before allowing a booking).
Repositories: The "filing cabinets." These handle all the heavy lifting of saving and retrieving data from the MySQL database.
Global Exception Handler: A safety net that catches errors (like searching for a ride that doesn't exist) and translates them into friendly, readable error messages.

Getting Started (For Developers)
Want to run this on your own machine? Follow these steps:

Prerequisites
Java 17 installed

MySQL Server installed and running

Maven installed

1. Database Setup
Create a new database in your local MySQL server named carpooling_db.

2. Configure Credentials
Open the src/main/resources/application.properties file and update the database credentials to match your local MySQL setup:

Properties
spring.datasource.username=root
spring.datasource.password=your_password
(Note: The app is configured to automatically create and update the database tables for you upon startup!)

3. Run the Application
Open your terminal, navigate to the project folder, and run:

Bash
mvn spring-boot:run
The server will start locally on http://localhost:8080.

Quick API Map
Here are the main endpoints you can interact with:

Users (/api/users)
POST /api/users - Register a new user.
GET /api/users/{id} - Get a user's details.

Rides (/api/rides)
POST /api/rides - Create a new ride (requires a valid Driver ID).
GET /api/rides/search?source={city}&destination={city} - Find available rides between two cities.

Bookings (/api/bookings)
POST /api/bookings - Book a seat on a ride.
GET /api/bookings/passenger/{passengerId} - See all bookings made by a specific passenger.
