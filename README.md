[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/AMjY7Wdy)
# SafetyNet Alerts System

The SafetyNet Alerts System is a Spring Boot application that is designed to help emergency repsonders access critical information efficiently.
The system provides first responders with access to the important information that is required during emergency sitautions.

## Project Overview

The system has several REST endpoints that provide different views of resident information, in order to help first responders make informed decisions during emergencies.

## Key Features

The SafetyNet Alerts System contains 4 main endpoints:
1. Fire Station Coverage Information
    - Retrieves a list of residents served by a fire station.
    - Includes resident information and emergency contact details.
    - Provides a count of adults and children in the service area.
      
2. Child Alert System
    - Indentifies children living at an address.
    - Lists other members from the household for context.
    - Helps responders quickly locate and assist children.

3. Emergency Phone Alert System
    - Retrieves phone numbers for all the residents served by a fire station.
    - Facilitates emergency notification text messages.
    - Enables mass communication during emergencies.

4. Resident Information Lookup
    - Provides detailed info about residents
    - Lists medications and allergies.
    - Supports emergency medical response.


  ## Technical Information

  ### Built With:
   - Spring Boot
   - Java
   - H2 Database
   - JPA

### Project Structure

This project follows the MVC pattern:
- Models: Define data structures for Person and FireStation
- Controllers: Handles HTTP requests and route them to appropriate services.
- Services: Contains all the business logic and data processing.
- Repos: Manage all the data and storage of data.

## Endpoint Testing

1. Fire Station Information:
   - http://localhost:8080/firestation?stationNumber=1
   - ![image](https://github.com/user-attachments/assets/a0257446-968c-4bdb-8b21-53df78caff38)

2. Child Alert:
   - http://localhost:8080/childAlert?address=123 Main St
   - ![image](https://github.com/user-attachments/assets/aee7a31e-d2fa-483e-b2a4-2b4e031c972a)
  
3. Phone Alert:
   - http://localhost:8080/phoneAlert?firestation=1
   - ![image](https://github.com/user-attachments/assets/474fdd7a-01df-4524-a826-f00222722e69)

4. Person Information:
   - http://localhost:8080/personInfo?firstName=John&lastName=Smith
   - ![image](https://github.com/user-attachments/assets/3df49429-53a6-4daa-a98c-2013f62e88f6)


