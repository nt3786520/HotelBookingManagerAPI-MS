# hotel-booking-manager-api-ms
hotel-booking-manager-api-ms micro-service for room booking in hotel for client.  

# Task is to implement a simple hotel booking manager in Java, as a microservice API. The number of rooms should be configurable, and it should expose the following methods:
 1. A method to store a booking. A booking consists of a guest name, a room number, and a date.  
 2. A method to find the available rooms on a given date.  
 3. A method to find all the bookings for a given guest. 
# Guidance
 Use only in-memory data structures; do not use a database.  
 Do not use any framework or libraries in your solution.  
 Provide tests with your solution (you may use libraries for the tests).  
 Your solution should build with Maven or Gradle.  
 Do not need to take into account the booking cancellation and guest check out.  
 Test cases as comprehensive as possible.
 Please share github link of your solution with us. 
# Extra credit
Make your solution thread-safe.

# Solution approach:
 # Spring Boot H2 Database example: Building Rest API with Spring Data JPA 

# Swagger docs added for API presentation:
 # http://localhost:8080/swagger-ui/index.html#/ img_3.png(need to check in project folder)

# Spring boot Exception Handling:
# @RestControllerAdvice added in Spring Boot API for handle the exception.

# Added In-memory DB:
 # H2 database for API build and test http://localhost:8080/h2-ui/ img_5.png(need to check in project folder)

# Security and thread safe for API
 # Added basic Auth for API Security for all endPoint. img_4.png(need to check in project folder)
 # username:admin
 # password:password

# Build Endpoints
1.A method to store a booking. A booking consists of a guest name, a room number, and a date.
# http://localhost:8080/api/bookings POST img_2.png(need to check in project folder) 
2.A method to find the available rooms on a given date.
# http://localhost:8080/api/available-rooms?date=2024-05-05 GET img_1.png(need to check in project folder)
3.A method to find all the bookings for a given guest.
# http://localhost:8080/api/bookings-for-guest?guestName=Narendra GET img.png(need to check in project folder)
4.For all record
# http://localhost:8080/api/booking/1 GET img_6.png(need to check in project folder)

# Run Spring Boot application
 mvn spring-boot:run
