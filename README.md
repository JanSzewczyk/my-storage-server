# my-storage

## Table of contents
* [General info](#general-info)
* [Dependencies](#dependencies)
* [Setup](#setup)

## General info
My Storage is an application for warehouse management. 
Allows you to create a certain number of warehouses assigned to owners 
who can employ and create employees assigned to the warehouse. 



## Dependencies
#### Dependencies used to create this application:
* spring-boot-starter-data-jpa          2.2.6.RELEASE        
* spring-boot-starter-actuator          2.2.6.RELEASE
* spring-boot-starter-security          2.2.6.RELEASE
* spring-boot-starter-web               2.2.6.RELEASE
* spring-boot-starter-hateoas           2.2.6.RELEASE
* spring-security-jwt                   1.0.7.RELEASE
* spring-security-oauth2                2.1.0.RELEASE
* modelmapper                           2.3.6
* postgresql
* lombok

	
## Setup
### Build Spring Boot Project with Maven
```
$ maven package
$ mvn install
```

### Run Spring Boot app with java -jar command
```
$ java -jar target\my-storage-0.1.1.jar
```

### Run Spring Boot app using Maven
```
$ mvn spring-boot:run
```