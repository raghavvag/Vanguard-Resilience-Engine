# 🧠 NEXUS-0 — Cognitive Supply Chain Control Tower

> Event-driven, graph-based system for supply chain impact analysis and future AI reasoning.

---

## 🚀 What is NEXUS-0?

NEXUS-0 is a **Cognitive Supply Chain Control Tower** designed to answer questions like:

> *“If a supplier is disrupted, which products are affected?”*

Traditional ERP systems store data in tables.  
NEXUS-0 models **dependencies** using an **event-driven architecture + graph database**, enabling real-time impact analysis and explainable intelligence.

---

## 🧩 Core Idea

- **PostgreSQL** → Source of truth (what exists)
- **Kafka** → Event backbone (what changed)
- **Neo4j** → Dependency graph (how things are connected)
- **AI (future)** → Reasoning layer (what will happen)

---

## 🏗️ High-Level Architecture

```text
Client
  ↓
Spring Boot REST APIs
  ↓
PostgreSQL (Supabase)
  ↓
Domain Events
  ↓
Kafka
  ↓
Kafka Consumers
  ↓
Neo4j Graph
🛠 Tech Stack
Java 21

Spring Boot 3.x

Spring Security + JWT

PostgreSQL (Supabase)

Apache Kafka

Neo4j

Docker / Docker Compose

Gradle

🔐 Security Model
Stateless JWT authentication

Role-based authorization (RBAC)

Method-level security using @PreAuthorize

Roles
Role	Permissions
ADMIN	Full access
OPS_MANAGER	Operational access
SME_USER	Read-only access

📦 Domain Model
Supplier
Represents an external dependency

Country is critical for risk modeling

Soft-deactivation supported

Product
Depends on a Supplier

Aggregates supplier risk

Identified by SKU

🔗 Dependency Graph (Neo4j)
text
Copy code
(:Supplier)-[:SUPPLIES]->(:Product)
This relationship enables impact analysis and graph traversal.

🔄 Event-Driven Flow (End-to-End)
text
Copy code
API Call
  ↓
Service Layer
  ↓
PostgreSQL Commit
  ↓
Domain Event Emitted
  ↓
Kafka Topic
  ↓
Kafka Consumer
  ↓
Neo4j Graph Projection
If a node exists in Neo4j, Kafka is working correctly.

📡 Events Implemented
SUPPLIER_CREATED

PRODUCT_CREATED

Events represent facts, not commands.

🧠 Why Kafka?
Decouples services

Enables async processing

Multiple consumers can react independently

Supports future AI, alerts, analytics

Kafka does not delete messages on consumption.
Consumers move offsets; messages expire via retention policy.

🗂 Backend Package Structure
text
Copy code
backend/
 ├── auth/
 ├── security/
 ├── domain/
 │    ├── supplier/
 │    └── product/
 ├── ingestion/
 │    ├── event/
 │    ├── producer/
 │    └── consumer/
 ├── graph/
 │    └── service/
 ├── common/
 └── config/
🌐 API Overview
Auth
POST /auth/signup

POST /auth/login

Supplier
POST /suppliers

GET /suppliers/{id}

DELETE /suppliers/{id}

Product
POST /products

GET /products/{id}

GET /products/by-supplier/{supplierId}

🧪 Testing Strategy
API Testing
Postman

JWT stored in environment

RBAC verified via 403 responses

Kafka + Graph Verification
Create a supplier/product → check Neo4j:

cypher
Copy code
MATCH (s:Supplier)-[:SUPPLIES]->(p:Product)
RETURN s, p;
If visible, the entire event pipeline works.

🧭 Current Progress (Completed)
Secure JWT auth & RBAC

Supplier & Product domains

Kafka producers & consumers

Neo4j graph projection

Supplier → Product dependency graph

🛣️ Roadmap (Upcoming)
Graph traversal APIs (impact analysis)

Region & geography modeling

External disruption ingestion (news, weather)

Graph + Vector RAG

AI reasoning layer

Natural language queries

Frontend control tower

Production hardening

▶️ Run Locally
bash
Copy code
docker compose up -d
./gradlew bootRun
Backend: http://localhost:8081

Neo4j Browser: http://localhost:7474

🧠 Design Principles
Source of truth in PostgreSQL

Events over direct coupling

Graph for dependencies

Security before features

Idempotent consumers

Explainability over magic