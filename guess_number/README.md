# 🚀 Spring Boot Project

A [Spring Boot](https://spring.io/projects/spring-boot) application built with Java, following best practices for clean architecture, RESTful APIs, and efficient database management.

---

## 📋 Features

- ✅ REST API with Spring Boot
- ✅ JPA/Hibernate for database operations
- ✅ Exception handling & validation
- ✅ Lombok for boilerplate reduction

## 🛠️ Tech Stack

- **Java**: 24 (or your version)
- **Spring Boot**: 3.x
- **Build Tool**: Maven
- **Database**: MySQL 

## 📦 Getting Started

### 1️⃣ Prerequisites
Make sure you have installed:

- [Java JDK 24+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/) 
- [MySQL/](https://www.mysql.com/) 

### 2️⃣ Clone the Repository

```bash
git clone https://github.com/hahuythong332/guess_number.git
cd guess_number
```

### 3️⃣ Configure Application

Edit the file application.yml:

    server: 

        port: 8080  

        servlet:

        context-path: /guess_number/api

    spring:

        datasource:

            url: "jdbc:mysql://localhost:3306/guess_number"

            username: root

            password: root

        jpa:

            hibernate:

                ddl-auto: update

                show-sql: true

        jwt:

            signerKey: "e86wukYciV6FX6e9dEykkTlBjh+59USH6IpaenK1lJkvz9fF8Sk5B8SG7pX8/Wa/"

### 4️⃣ Build and run

-- mvn clean install

-- mvn spring-boot:run

### 🌐 API DOCUMENTATION

Once the application is running, open:

http://localhost:8080/guess_number/api/login

Get token response to post man environment file to authenticate.
