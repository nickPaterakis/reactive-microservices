
# Reactive Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">

Reactive microservices project using microservices architecture and implements reactive programming, OAuth 2.0 authorization, Container Orchestration, and microservices design patterns like Choreography-based saga and Database per Service. The project follows the  <a href="https://12factor.net/"> twelve-factor app </a> methodology for building software-as-a-service applications.

This project uses technologies like Spring Boot, WebFlux, Spring Cloud Stream, Maven, RabbitMQ, CSS, Sass, OAuth 2.0, Keycloak, OpenID, MySQL, MongoDB, React, Redux, Docker, and Kubernetes.

You can find the front-end of the application [here](https://github.com/nickPaterakis/booking-web-app).

# System

The architecture of the reactive microservices project is designed to provide a scalable and decoupled solution. The project consists of three main services: the Reservation Service, Property Service, and User Service. The services communicate with each other via RESTful APIs, allowing seamless integration and exchange of information.

To facilitate communication between services, the project employs the use of Spring WebClient, a non-blocking web client provided by the Spring framework. This enables efficient and asynchronous communication for remote calls, ensuring responsive interactions between services while maintaining the reactive programming paradigm. In addition, the project incorporates RabbitMQ, a popular message broker, to enable asynchronous messaging and event-driven communication between services.

The services utilize different databases to optimize data storage and retrieval based on their specific requirements. The Property Service utilizes a MySQL database, which offers a reliable and robust relational database management system. On the other hand, the Reservation and User Services employ MongoDB, a highly scalable and flexible NoSQL database, known for its document-oriented nature and horizontal scalability.

Furthermore, the project incorporates Keycloak, an open-source identity and access management solution, to enhance security and authentication. Keycloak supports industry-standard protocols such as OAuth 2.0 and OpenID Connect, providing secure access delegation and user authentication. By integrating Keycloak, the project delegates user authentication to a centralized identity provider, allowing services to focus on core functionalities.

To deploy the project to production, it leverages Kubernetes, an open-source container orchestration platform. Kubernetes offers robust management and scaling capabilities, ensuring high availability and fault tolerance. By utilizing Kubernetes, the project can easily manage and automate the deployment, scaling, and monitoring of the services, providing a reliable and efficient environment for production usage.

Overall, the reactive microservices project embraces modern architectural principles and technologies to deliver a scalable, responsive, and highly available solution. By leveraging reactive programming, RESTful APIs, different databases, message queuing, Kubernetes, and Keycloak, it enables seamless communication between services, efficient data management, reliable production deployments, and robust security and authentication capabilities.

![main_diagram](https://user-images.githubusercontent.com/36018286/191720154-950952c5-76fe-44d0-a6ea-4a1be88355e1.png)

# OAuth Flow

![security_diagram (1)](https://user-images.githubusercontent.com/36018286/160458106-663d38c9-070f-43f8-94bf-a6be0a327b9d.png)

# Property Service Database Schema

![property_db_shema](https://user-images.githubusercontent.com/36018286/128721034-60c23a0a-9003-44aa-8afd-cfd7f5f94c38.png)
