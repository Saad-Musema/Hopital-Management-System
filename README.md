# Hospital Management System

This repository contains a hospital management system implemented in Java. The system provides functionalities to manage patients, doctors, nurses, and receptionists within a hospital setting.

## Features

- **Patient Management**: Add, update, and delete patient records. Track patient admissions, discharges, and medical records.
- **Doctor Management**: Manage doctor information including specialization, contact details, and schedule.
- **Nurse Management**: Maintain nurse details such as shift schedule and contact information.
- **Receptionist Interface**: Interface for receptionists to perform tasks such as admitting new patients, scheduling appointments, and managing inventory.
- **Appointment Scheduling**: Schedule appointments between patients and doctors, and manage appointment details.
- **Inventory Tracking**: Track hospital inventory and supplies, manage stock levels, and reorder supplies as needed.

## Usage

To use the hospital management system:

1. **Clone the Repository**: Clone this repository to your local machine using Git.

2. **Compile and Run**: Compile the Java source files and run the `Main` class to start the hospital management system.

3. **Interact with the System**: Use the provided command-line interface to interact with the system. Follow the prompts to perform various tasks such as adding patients, scheduling appointments, and managing inventory.

## Dependencies

- **Java Development Kit (JDK)**: Ensure that you have Java Development Kit (JDK) installed on your system to compile and run the Java code.
- **MySQL Database**: The system relies on a MySQL database to store and manage data. Make sure you have MySQL installed and configured on your system, and update the database connection settings in the code as needed.


File Structure Explanation

    src/: Source directory containing all Java source files.
        com/hospital/controller/: Controller classes responsible for handling different roles (e.g., ReceptionistController, DoctorController).
        com/hospital/db/: Database-related classes including database connection and repository classes.
        com/hospital/model/: Model classes representing entities such as Patient, Doctor, Nurse.
        Main.java: Main class to run the application.
    lib/: Directory for external libraries (if any). You can place JDBC driver JAR files or any other external dependencies here.
    resources/: Resource directory containing configuration files, SQL scripts, etc.
        resources/db/: Directory for database-related files such as SQL scripts for database creation and configuration.
        config.properties: Configuration properties file containing settings like database connection details.
    .gitignore: Git ignore file specifying files and directories to be ignored by Git version control.
    README.md: README file with project documentation, usage instructions, and other information for users and contributors.
    LICENSE: License file specifying the terms under which the project is licensed (e.g., MIT License, Apache License).

Adding Components

    Controllers: Add controller classes under src/com/hospital/controller/. Each controller should handle specific roles or functionalities (e.g., ReceptionistController, DoctorController).
    Database Related: Database connection and repository classes go under src/com/hospital/db/. This includes classes for establishing database connections, executing queries, and interacting with the database.
    Model Classes: Model classes representing entities (e.g., Patient, Doctor) are placed under src/com/hospital/model/.
    Main Class: The main class to run the application should be placed directly under src/ or src/com/hospital/.
    External Libraries: Place any external libraries (e.g., JDBC driver JAR files) under the lib/ directory.
    Resources: Configuration files, SQL scripts, and other resources go under the resources/ directory.


    
## Contributing

Contributions to the hospital management system are welcome! If you have suggestions for improvements, bug fixes, or new features, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---
