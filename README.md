# Hubrick Challenge

This repository contains the source code for a solution to the Hubrick Challenge.

The problem description can be found here - https://github.com/hubrick/hubrick-backend-challenge/blob/master/README.md

The solution is a User Management System build as a REST API, exposing the following operations:

* Create User
* Update User
* Delete User
* Retrieve User (By Id)
* Retrieve All Users

## Design

The design of the solution is as follows:

* Controller Layer - Exposes the system's operations endpoints. Communicated with Service Layer (Spring Rest Controller)
* Service Layer - Contains any the User Management business logic. Communicates with Repository Layer (Spring Component)
* Repository Layer - ElasticserchRepository, connects to an embedded elasticsearch instance in order to retrieve and persist   entities per service requests.
* IdGenerator - generates unique ids for newly created users
* Validators - Each responsible for validating an InputField.  The UserValidator is a composition of all User fields'          validators
* Exceptions - Each providing an appropriate HttpStatus
 
## Testing

The project contains integration tests which can be run using 

```
$ mvn clean verify
```

## Running

The project can be run by using

```
$ mvn spring-boot:run
```

## Experimenting/Swagger

The project is integrated with SwaggerUI which allows for exploring, learning, and testing the API.  Once the application is running, simply navigate to - http://localhost:8080/swagger-ui.html

### Apologies

At this stage, the project does not write any logs :(


