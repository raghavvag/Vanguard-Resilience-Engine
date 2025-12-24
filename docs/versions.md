# NEXUS-0 – Version Matrix & Technology Baseline

This document defines the official technology versions used in the NEXUS-0 backend system.
All services must remain compatible with these versions to ensure stability and reproducibility.

---

## 🔧 Language & Runtime

- Java: **21 (LTS)**
- JVM Compatibility Target: Java 21+
- Build Tool: **Gradle (multi-module ready)**

---

## 🌱 Backend Framework

- Spring Boot: **4.0.1**
- Spring Framework: 6.x (managed by Spring Boot)
- Spring Security: Latest compatible with Boot 4
- Spring Data JPA / Neo4j: Latest compatible with Boot 4

---

## 🗄️ Databases

### Relational Database
- PostgreSQL: **15.x**
- Driver: org.postgresql:postgresql

### Graph Database
- Neo4j: **5.x**
- Neo4j Java Driver / Spring Data Neo4j

---

## 🔄 Streaming & Messaging

- Apache Kafka: **3.x**
- Zookeeper: **3.8+**
- Spring Kafka: Managed via Spring Boot

---

## 🧠 AI & Intelligence Layer

- Spring AI: Compatible starter for Spring Boot 4
- LLM Providers:
  - OpenAI (cloud)
  - Ollama (local / self-hosted)
- Embedding Models: OpenAI / HuggingFace compatible

---

## 📈 Observability & Resilience

- Spring Boot Actuator
- Micrometer
- Prometheus Registry
- Resilience4j

---

## 🧪 Testing

- JUnit 5
- Spring Boot Test
- Testcontainers:
  - PostgreSQL
  - Neo4j
  - Kafka

---

## 🐳 Infrastructure

- Docker: 24+
- Docker Compose: v2+

---

## 📌 Versioning Policy

- Java LTS versions preferred
- Spring Boot minor upgrades allowed
- Database major upgrades require validation
- AI model versions are abstracted via Spring AI

---

## ✅ Goal

Ensure:
- Reproducible builds
- Long-term maintainability
- Production-grade stability
- Easy onboarding for new contributors
