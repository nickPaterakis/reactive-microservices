
# Reactive Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">

Reactive microservices project using microservices architecture and implements reactive programming, OAuth 2.0 authorization, Container Orchestration, and microservices design patterns like Choreography-based saga and Database per Service. The project follows the  <a href="https://12factor.net/"> twelve-factor app </a> methodology for building software-as-a-service applications.

This project uses technologies like Spring Boot, WebFlux, Spring Cloud Stream, Maven, RabbitMQ, OAuth 2.0, OpenID, Keycloak, PostgreSQL, MySQL, MongoDB, Docker, and Kubernetes.

You can find the front-end of the application [here](https://github.com/nickPaterakis/booking-web-app).

# System Architecture

The architecture of the reactive microservices project is designed to provide a scalable and decoupled solution. The project consists of three main services: the Reservation Service, Property Service, and User Service. The services communicate with each other via RESTful APIs, allowing seamless integration and exchange of information.

The project incorporates Spring WebClient, a non-blocking web client, for efficient and asynchronous communication between services, maintaining the reactive programming paradigm. It also integrates RabbitMQ for asynchronous messaging and event-driven communication. To optimize data storage and retrieval, the services utilize different databases. The Property Service employs MySQL for reliable relational database management, while the Reservation and User Services utilize MongoDB for scalable and flexible NoSQL storage. 

The project integrates Keycloak, an open-source identity and access management solution, to enhance security and authentication. Keycloak supports OAuth 2.0 and OpenID Connect, ensuring secure access delegation and user authentication. By delegating user authentication to a centralized identity provider, the services can focus on their core functionalities.

To deploy the project to production, it utilizes Kubernetes, which provides robust management and scaling capabilities to ensure high availability and fault tolerance.

Overall, the reactive microservices project embraces modern architectural principles and technologies, including Kubernetes, to deliver a scalable, responsive, and highly available solution. By leveraging reactive programming, RESTful APIs, different databases, message queuing, and Keycloak for security and authentication, it enables seamless communication between services, efficient data management, reliable production deployments, and robust security measures. 

![main_diagram](https://github.com/nickPaterakis/reactive-microservices/assets/36018286/501a9e34-6c1e-4922-b56c-7504c970c816)

# OAuth Flow

![security_diagram (1)](https://user-images.githubusercontent.com/36018286/160458106-663d38c9-070f-43f8-94bf-a6be0a327b9d.png)

# Property Service Database Schema

![property_db_shema](https://user-images.githubusercontent.com/36018286/128721034-60c23a0a-9003-44aa-8afd-cfd7f5f94c38.png)
