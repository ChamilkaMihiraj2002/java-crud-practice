# Java + MySQL CRUD Application

## Repository Description

This repository contains a Java-based CRUD (Create, Read, Update, Delete) application integrated with a MySQL database. The project demonstrates fundamental database operations and provides a robust example of Java and MySQL integration for beginners and intermediate learners.

## Features

- **Database Connection**: Establishes a secure connection to a MySQL database using JDBC.
- **Create Operation**: Allows users to insert new records into the database.
- **Read Operation**: Retrieves and displays data from the database.
- **Update Operation**: Updates existing records in the database.
- **Delete Operation**: Removes records from the database.
- **Error Handling**: Implements basic error handling for database operations.
- **Modular Code**: Structured using separate classes for better readability and maintainability.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or later installed.
- **MySQL**: A MySQL server should be running, with credentials for the database setup.
- **JDBC Driver**: Add the MySQL JDBC driver to your project's classpath.

## Setup Instructions

1. Clone this repository:
   ```bash
   git clone https://github.com/ChamilkaMihiraj2002/java-mysql-crud.git
   ```

2. Import the project into your favorite IDE (e.g., IntelliJ IDEA, Eclipse).

3. Create a MySQL database and table using the provided SQL script:
   ```sql
   CREATE DATABASE crud_example;

   USE crud_example;

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL,
       age INT NOT NULL
   );
   ```

4. Update the database configuration in the `DatabaseConnection` class:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/crud_example";
   private static final String USER = "yourusername";
   private static final String PASSWORD = "yourpassword";
   ```

5. Run the application and perform CRUD operations!

## Usage

- **Insert**: Add a new user.
- **View**: Display all users.
- **Update**: Modify user details.
- **Delete**: Remove a user by ID.

## Contributing

Feel free to fork this repository, submit issues, or create pull requests if you have ideas for improvements or new features.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

Happy coding! 🚀
