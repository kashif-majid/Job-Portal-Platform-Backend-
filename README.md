# Job Platform Portal – Microservice Architecture Project

## Table of Contents
- [Overview](#overview)
- [System Architecture](#system-architecture)
- [Microservices Breakdown](#microservices-breakdown)
- [Infrastructure & Supporting Services](#infrastructure--supporting-services)
- [Technology Stack](#technology-stack)
- [Deployment & Orchestration](#deployment--orchestration)
- [Development Workflow](#development-workflow)
- [API Gateway & Security](#api-gateway--security)
- [Configuration Management](#configuration-management)
- [Service Discovery](#service-discovery)
- [Monitoring & Tracing](#monitoring--tracing)
- [Database & Messaging](#database--messaging)
- [Kubernetes Manifests](#kubernetes-manifests)
- [How to Run](#how-to-run)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This repository contains a comprehensive Job Platform Portal built using a microservices architecture. The system is designed to facilitate job postings, company management, reviews, and more, with a focus on scalability, maintainability, and resilience. Each domain is encapsulated in its own Spring Boot microservice, allowing independent development, deployment, and scaling.

---

## System Architecture

The platform is composed of several microservices, each responsible for a specific business domain. These services communicate over REST and asynchronous messaging, and are orchestrated using Docker and Kubernetes. The architecture follows best practices for cloud-native applications, including centralized configuration, service discovery, and distributed tracing.

### High-Level Diagram

```
+-------------------+      +-------------------+      +-------------------+
| Company Service   |<---->| Gateway           |<---->| Client            |
+-------------------+      +-------------------+      +-------------------+
| Job Service       |<---->|
+-------------------+      |
| Review Service    |<---->|
+-------------------+      |
| Service Registry  |<-----+
+-------------------+
| Config Server     |
+-------------------+
| PostgreSQL        |
+-------------------+
| RabbitMQ          |
+-------------------+
| Zipkin            |
+-------------------+
```

---

## Microservices Breakdown

### 1. Company MicroService
- **Purpose:** Manages company profiles, details, and related operations.
- **Endpoints:** CRUD for companies, search, and integration with job/review services.
- **Persistence:** Stores company data in PostgreSQL.
- **Inter-Service Communication:** REST and RabbitMQ events.

### 2. Job MicroService
- **Purpose:** Handles job postings, applications, and job-related logic.
- **Endpoints:** CRUD for jobs, job search, application submission.
- **Persistence:** Stores job data in PostgreSQL.
- **Inter-Service Communication:** REST and RabbitMQ events.

### 3. Review MicroService
- **Purpose:** Manages reviews for companies and jobs.
- **Endpoints:** CRUD for reviews, review aggregation, reporting.
- **Persistence:** Stores review data in PostgreSQL.
- **Inter-Service Communication:** REST and RabbitMQ events.

### 4. Gateway
- **Purpose:** Serves as the single entry point for all client requests.
- **Responsibilities:** Routing, authentication, rate limiting, and request aggregation.
- **Technology:** Spring Cloud Gateway.

### 5. Config Server
- **Purpose:** Centralized management of configuration properties for all microservices.
- **Technology:** Spring Cloud Config Server.

### 6. Service Registry
- **Purpose:** Enables service discovery and dynamic routing.
- **Technology:** Eureka or similar.

---

## Infrastructure & Supporting Services

- **PostgreSQL:** Relational database for persistent storage of all domain data.
- **RabbitMQ:** Message broker for asynchronous communication and event-driven architecture.
- **Zipkin:** Distributed tracing for monitoring and debugging microservice interactions.

---

## Technology Stack

- **Java 17+**
- **Spring Boot**
- **Spring Cloud (Config, Gateway, Eureka)**
- **Docker**
- **Kubernetes**
- **PostgreSQL**
- **RabbitMQ**
- **Zipkin**

---

## Deployment & Orchestration

### Docker
All microservices and supporting infrastructure are containerized. The `docker-compose.yml` file orchestrates local development and testing.

### Kubernetes
Production-grade deployment is managed via Kubernetes manifests located in the `k8s/` directory. These include deployments, services, replica sets, and bootstrap scripts for each microservice and infrastructure component.

---

## Development Workflow

1. **Clone the repository.**
2. **Configure environment variables and application properties.**
3. **Start infrastructure services (PostgreSQL, RabbitMQ, Zipkin).**
4. **Build and run each microservice using Maven:**
   ```sh
   ./mvnw spring-boot:run
   ```
5. **Use the API Gateway for accessing endpoints.**

---

## API Gateway & Security

- All client requests are routed through the Gateway.
- Security, authentication, and rate limiting are handled at the gateway level.
- The gateway aggregates responses and provides unified error handling.

---

## Configuration Management

- Centralized configuration via the Config Server.
- Each microservice fetches its configuration at startup, enabling dynamic updates and environment-specific settings.

---

## Service Discovery

- Service Registry enables dynamic discovery and load balancing.
- Microservices register themselves at startup and can locate other services without hardcoded URLs.

---

## Monitoring & Tracing

- Zipkin is integrated for distributed tracing.
- All requests are traced end-to-end, enabling performance monitoring and debugging.

---

## Database & Messaging

- PostgreSQL is used for persistent storage across all services.
- RabbitMQ enables asynchronous event-driven communication, decoupling services and improving scalability.

---

## Kubernetes Manifests

- Located in the `k8s/` directory.
- Includes deployments, services, replica sets, and bootstrap scripts for each microservice and infrastructure component.
- Example files:
  - `first-deployment.yaml`
  - `first-pod.yaml`
  - `first-service.yaml`
  - `replica-set.yaml`

---

## How to Run

### Local Development

1. Start infrastructure services (PostgreSQL, RabbitMQ, Zipkin).
2. Build and run each microservice:
   ```sh
   ./mvnw spring-boot:run
   ```
3. Access endpoints via the API Gateway.

### Docker Compose

1. Use the provided `docker-compose.yml` to start all services:
   ```sh
   docker-compose up --build
   ```

### Kubernetes

1. Apply manifests from the `k8s/` directory:
   ```sh
   kubectl apply -f k8s/
   ```

---

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request. For major changes, open an issue first to discuss what you would like to change.

---


