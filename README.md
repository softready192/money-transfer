# money-transfer

Problem Statement:

Tech stack: 

Programming Language: Either Java or Kotlin
Web Framework: Spring Boot

Application Requirements:

Service should expose a REST API to accept money transfers to other accounts. Money transfers should persist new balance of accounts
Service should expose a REST API for getting the account details. You can disregard currencies at this time

Points to consider:

Keep the design simple and to the point. The architecture should be scalable for adding new features
Proper handling of concurrent transactions for the accounts (with unit tests)
The datastore should run in-memory for the tests
Proper unit testing and decent coverage is a must
Upload the code to a repository
Disregard Currency or Rate Conversion
Improvise where details are not provided

Plus Points:

Assignment implemented in Kotlin
Documentation of API (e.g. Swagger)
Dockerized app


Pre-Requisite: Java 11 Maven 3.8.1

Steps to Run Application:

Git clone: git clone https://github.com/softready192/money-transfer.git
Navigate to the checkout directory and run : mvn clean install
A jar will be created in target folder. Run the jar file: java -jar transfer-0.0.1-SNAPSHOT.jar
Liquibase will insert the master data in in-memory database.
Open swagger link in browser: http://localhost:8080/swagger-ui.html
Use the account controller api for saving account, get Account details, get all accounts operations.
Use the payment transfer controller for payment transfer.

Please pull the docker base image from the given location:

docker pull softready192/money-transfer:0.0.1
docker run -p 8080:8080 softready192/money-transfer:0.0.1
