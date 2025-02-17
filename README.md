# Number to Words Converter

This is a web application built using **Spring Boot** that converts decimal number currency value into their word representation.  
It provides a **Web UI** for users to input decimal number between 1 and 999999.99 to get output in words.

---

## Features
- Convert **decimal numbers** (e.g., `123.45` → "ONE HUNDRED AND TWENTY THREE DOLLARS AND FORTY FIVE CENTS").
- **CSRF protection** enabled for form submissions.
- **Bootstrap UI** for a clean and responsive webpage.
- **Thymeleaf templating** for structured HTML pages.
- **Unit tests & security** included.

---

## Prerequisites
Make sure you have the following installed:
- **Java 23+** (Check with `java -version`)
- **Maven 3.9** (Check with `mvn -version`)
- **Spring Boot** (Included in the Maven dependencies)

---

## Installation & Setup
### Clone the Repository
```bash
git clone https://github.com/mandun/word-generator.git
cd word-generator
mvn clean package
java -jar target/word-generator-0.0.1-SNAPSHOT.jar
http://localhost:8080
```

---

## Tests
```bash
mvn test
```



