# NEXUS-0 – System Architecture Document

## 1. Overview

NEXUS-0 is an AI-augmented, event-driven Cognitive Supply Chain Control Tower.
Unlike traditional ERP systems that describe past events, NEXUS-0 reasons about
future impact by combining:

- Transactional data (orders, shipments, suppliers)
- Real-time events (CDC + Kafka)
- Structural dependencies (Neo4j Knowledge Graph)
- External signals (news, disruptions)
- AI reasoning (Graph-RAG)

---

## 2. Architectural Principles

- Event-driven by default
- Strong separation of concerns
- Graph as the source of structural truth
- AI as a reasoning layer, not a data source
- Language-isolated services (Java + Python)
- Cloud-ready but cloud-agnostic

---

## 3. High-Level Architecture

Frontend (React / Next.js)
|
v
API Gateway (Spring Boot)
|
v
| Domain | Ingestion | Graph | AI | Auth Services |
markdown
Copy code
|
v
PostgreSQL <--> Kafka <--> Neo4j
|
v
Python AI Services

yaml
Copy code

---

## 4. Backend Modules (Java – Spring Boot)

### 4.1 API Layer
- Exposes REST endpoints
- Handles validation and request mapping
- No business logic

### 4.2 Domain Layer
- Core business entities
- Supply chain rules and policies
- Framework-independent

### 4.3 Ingestion Service
- Consumes Kafka topics
- Handles Debezium CDC events
- Transforms DB changes into graph updates

### 4.4 Graph Service
- Owns Neo4j access
- Executes Cypher queries
- Performs dependency traversal

### 4.5 AI Orchestration Service
- Builds prompts
- Retrieves graph + vector context
- Invokes LLMs via Spring AI
- Parses structured responses

### 4.6 Auth Service
- JWT-based authentication
- Role-based authorization
- Secures all APIs

---

## 5. Python AI Services

Python services are isolated and stateless.

Responsibilities:
- News ingestion
- Entity extraction (location, event type)
- Embedding generation
- Periodic intelligence ingestion

Communication:
- Kafka (primary)
- REST (secondary, control)

---

## 6. Data Architecture

### PostgreSQL (System of Record)
- Stores transactional data
- Emits CDC events
- Ensures ACID compliance

### Neo4j (Knowledge Graph)
- Models supply chain structure
- Nodes: Supplier, Product, Order, Region, Port
- Relationships: SUPPLIES, DEPENDS_ON, ROUTES_THROUGH

### Vector Store
- Stores embeddings of unstructured data
- Linked to graph entities
- Enables semantic retrieval

---

## 7. AI Reasoning Flow (Graph-RAG)

1. User query received
2. Intent + entity extraction
3. Graph traversal for dependencies
4. Vector search for relevant context
5. LLM reasoning using grounded context
6. Structured response returned to UI

---

## 8. Security Architecture

- Stateless JWT authentication
- Role-based access control
- Secured AI and graph endpoints
- Rate limiting on expensive operations

---

## 9. Non-Functional Requirements

- High availability
- Horizontal scalability
- Observability-first design
- Low latency graph queries
- Explainable AI outputs

---

## 10. Architectural Philosophy

AI does not decide facts.

Facts come from:
- Databases
- Events
- Graph structure

AI only:
- Explains
- Reasons
- Predicts impact

---

## 11. Future Extensions

- What-if simulation engine
- Alerting & notifications
- Multi-tenant isolation
- Advanced risk scoring
- UI-based graph exploration