# CoreShield Assessment Processing MapData with MetaData

# CoreShield-Processing-Map-Data-with-Metadata-with-database

---

## **🚀 2️⃣ `README-database.md` (Spring Boot + Database)**
```markdown
# 🛠️ CoreShield Assessment - Spring Boot + Database Version

This is a **Spring Boot REST API** that processes and merges location data **with a database**.  
The API reads `locations.json` and `metadata.json`, stores them in **MySQL/PostgreSQL**, and provides insights.

## 📌 Features
✅ Reads JSON files (`locations.json`, `metadata.json`)  
✅ Stores data in **MySQL/PostgreSQL (via Spring Data JPA)**  
✅ Merges data where `id` matches  
✅ Provides **5 REST API endpoints**  
✅ Uses **Spring Boot, JPA, Hibernate**  

---

## 🚀 Setup & Installation

### **🛠️ Prerequisites**
- Java 17+  
- Apache Maven 3+  
- MySQL / PostgreSQL  
---
### **📌 Steps to Run**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-repo-url.git
   cd coreshield-assessment
---

## **🚀 API Endpoints**
### ** Processing Map Data with Metadata**


| Method | Endpoint               | Description                               |
|--------|------------------------|-------------------------------------------|
| GET    | `/api/mergedData`      | Loads & saves JSON data to DB             |
| GET    | `/api/count`           | Counts locations per type (DB)            |
| GET    | `/api/average-rating`  | Calculates average rating per type (DB)   |
| GET    | `/api/highest-reviews` | Finds location with the most reviews (DB) |
| GET    | `/api/incomplete`      | Identifies incomplete data (DB)           |
