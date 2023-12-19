# TV Program Management UI Application

## Overview

The TV Program Management UI Application is a Java-based graphical user interface for managing TV shows, episodes, episode date and time, guests, editors, and hosts. It interacts with a separate Java Spring Boot API to perform CRUD (Create, Read, Update, Delete) operations on the TV program data.

## Features

- **Create**: Create new TV shows, episodes, guests, editors, and hosts.
- **Read**: Display detailed information about TV shows, episodes, guests, editors, and hosts.
- **Update**: Modify existing data for TV shows, episodes, guests, editors, and hosts.
- **Delete**: Remove unwanted records from the database.

## Prerequisites

Before running the TV Program Management UI Application, ensure you have the following dependencies installed:

- **Java**: Java SE Development Kit (JDK) 8 or higher.
- **Gradle**: Build and dependency management tool.
- **Spring Boot API**: Ensure that the separate Spring Boot API for the TV program is up and running. The API is accessible [here.](https://github.com/emina-mahmutbegovic/process-information-systems-api)


## Getting Started

1. Clone the repository to your local machine:

 ```shell
https://github.com/emina-mahmutbegovic/process-information-systems-ui
```

2. Navigate to the Project Directory

Before you can build and run the Java UI application, make sure you navigate to the project directory in your terminal or command prompt. Use the `cd` command to change the directory to where your project files are located.

```bash
cd /path/to/your/project-directory
```

3. Build project using Gradle:

MAC/Linux users:
```bash
./gradlew build
```

Windows users
```bash
gradlew build
```
4. Run UI Application:

MAC/Linux users:
```bash
./gradlew run
```

Windows users:
```bash
gradlew run
```

## Usage
Once the UI application is running, you can use it to perform various management tasks for the TV program:

#### Create: 
Click on the "Create" button to add new TV shows, episodes, guests, editors, and hosts.

#### Read: 
Use the navigation menu to view detailed information about existing records.

#### Update: 
Select a record and click the "Edit" button to modify its details.

#### Delete: 
To remove a record, select it and click the "Delete" button. Confirm the deletion when prompted.

## Acknowledgments
We would like to thank the contributors and users who have helped improve this application.

If you have any questions or need assistance, please feel free to contact us at *eminamahmutbey@gmail.com*.

Happy TV program management!