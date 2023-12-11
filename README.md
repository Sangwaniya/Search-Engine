# Java-MultiThreading-ErrorLogger
A Java project that demonstrates multi-threading, unit testing, and error logging features.

## Description
This project consists of three main components:

- **Search**: A class that allows the user to search for a file in the system by specifying the file name and the directory path. The search is performed in a separate thread and returns a list of file locations that match the query.
- **View**: A class that displays the file locations searched by the user in a table format. The table shows the file name, size, and last modified date for each file location.
- **Recommend**: A class that uses a simple algorithm to recommend files to search based on the user's previous queries. The algorithm considers the frequency and recency of the queries and suggests the most relevant files.

## Testing
The project uses JUnit 5 as the testing framework. The test cases cover the main functionalities of the search, view, and recommend classes, as well as the error handling and logging mechanisms.

## Error Logging
The project uses Log4j 2 as the logging framework and SLF4J as the logging facade. The log messages are written to a file named `error.log` in the project root directory. The log level can be configured in the `log.xml` file.

## Usage
To run the project, you need to have Java 11 or higher installed on your system. You can use the following commands to compile and execute the project.
