# Research Project Java Monolith
> The program was prepared for the subject Research Project.
## Table of contents
* [About The Project](#about-the-project)
* [Technologies](#technologies)
* [Getting Started](#getting-started)
* [Contact](#contact)

## About The Project

The aim of the project was to implement monolith application for graph's operations using Java and Spring Boot technologies.

## Technologies
* Java 17
* Spring Boot 2.6.6
* Docker

## Getting Started
Clone the repository  
`git clone https://github.com/ProjektBadawczy/ResearchProjectMonolithJava.git`

Run application: \
`mvn clean package`\
`docker compose up --build`

## REST API
Get basic information about graph based on ID: http://localhost:8081/Graph/GetGraph?id=0

Get maximum flow in graph based on ID beewten two vertices: http://localhost:8081/Graph/GetMaxGraphFlow?id=0&source=1&destination=2

## Contact
Piotr Szymański - piotrszymanski133@gmail.com