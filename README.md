# Grocery Store Inventory Management System

## Project Description

Developed a grocery store inventory management system that allows the user to add items to the inventory as well as purchase items listed in the inventory. Provides net profit, pure sales and business expense metrics that are accessible through HTTP requests as well as logs.

## Technologies Used

* Spring Boot
* Spring Data
* JDBC
* Amazon RDS
* Kubernetes
* Docker
* Maven
* FluentD
* Grafana
* Loki
* Docker
* Log4J
* PostgreSQL
* Git

## Features

* All features are accessible via HTTP requests through the use of a RESTful API
* Add items to inventory
* Create users to make purchases
* Purchase items from inventory
* Obtain net profit
* Obtain pure sales
* Obtain business expenses
* All data is stored in an Amazon RDS
* Project image was built using Docker and deployed via Kubernetes

To-do list:

* Add more robust exception handling and data validation

## Getting started

Begin by cloning the repository into a desired folder

```git clone https://github.com/210201sre/husker-p1.git```

To run application locally, Loki, Grafana and FluentD agents must be started. This can be accomplished using Docker Desktop and the provided docker compose file inside the logging folder.

```
cd logging
docker-compose up -d
```

Once the agents are running, the application can be compiled as a Spring Boot application and ran using Spring Tool Suite 4. 

All features of the application are accessible via HTTP requests to a RESTful API.

### User features
