


# EmployWise Spring Project

This project is a Spring Boot application for managing employee information.

## Getting Started

These instructions will help you set up and run the project locally.

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Maven
- PostgreSQL database

### Database Setup

1. Create a PostgreSQL database named `employwise`.
2. Update the database connection properties in `src/main/resources/application.properties` with your database details.

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/employwise-spring.git
   ```

2. Navigate to the project directory:

   ```bash
   cd employwise-spring
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The application will be accessible at `http://localhost:8080`.

## API Documentation

### Add Employee

- **Endpoint:** POST /api/employees
- **Request Body:**

  ```json
  {
    "employeeName": "John Doe",
    "phoneNumber": "123456789",
    "email": "john.doe@example.com",
    "reportsTo": "managerId", // Optional
    "profileImage": "base64EncodedImage" // Optional
  }
  ```

### Get All Employees

- **Endpoint:** GET /api/employees
- **Response:**

  ```json
  [
    {
      "id": "employeeId",
      "employeeName": "John Doe",
      "phoneNumber": "123456789",
      "email": "john.doe@example.com",
      "reportsTo": "managerId",
      "profileImage": "base64EncodedImage"
    },
    // ... other employees
  ]
  ```

### Delete Employee

- **Endpoint:** DELETE /api/employees/{id}
- **Path Variable:** id (Employee ID)

### Update Employee

- **Endpoint:** PUT /api/employees/{id}
- **Path Variable:** id (Employee ID)
- **Request Body:**

  ```json
  {
    "employeeName": "Updated Name",
    "phoneNumber": "987654321",
    "email": "updated.email@example.com",
    "reportsTo": "newManagerId", // Optional
    "profileImage": "newBase64EncodedImage" // Optional
  }
  ```

### Get Nth Level Manager

- **Endpoint:** GET /api/employees/nthLevelManager/{employeeId}/{level}
- **Path Variables:**
  - employeeId (Employee ID)
  - level (Integer)

- **Example:**

  ```plaintext
  GET /api/employees/nthLevelManager/employeeId/1
  ```

  Returns the manager at the first level above the specified employee.

### Get Employees with Pagination and Sorting

- **Endpoint:** GET /api/employees/paginatedAndSorted
- **Query Parameters:**
  - page (Integer)
  - size (Integer)
  - sortBy (String)

- **Example:**

  ```plaintext
  GET /api/employees/paginatedAndSorted?page=0&size=10&sortBy=employeeName
  ```

  Returns a paginated and sorted list of employees.


