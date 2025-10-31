# 🌀 Fila-Zero – Backend (Versión BasicAuth)

**Fila-Zero** es una aplicación pensada para gestionar las filas virtuales y la recogida de pedidos en comercios locales.  
Esta versión del backend orientado a completar el desarrollo de entidades, servicios y pruebas con Postman antes de integrar la seguridad final y el frontend.

---

## 🚀 Tecnologías principales

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA / Hibernate**
- **Spring Web**
- **H2 / MySQL** (según entorno)
- **Lombok**
- **Maven**
- **Postman** (para pruebas de endpoints)
- **DTOs con `record`** (para requests y responses)

---

## 🧩 Arquitectura

src/
├── main/java/com/fila_zero/
│ ├── config/ # Configuraciones globales (CORS, excepciones, etc.)
│ ├── controller/ # Controladores REST
│ ├── dto/ # DTOs definidos como record
│ ├── entity/ # Entidades JPA
│ ├── repository/ # Interfaces JPA Repository
│ ├── service/ # Interfaces de servicio
│ ├── service/impl/ # Implementaciones de servicio
│ ├── exception/ # Manejador global y clases personalizadas
│ └── FilaZeroApplication.java
│
└── test/java/com/fila_zero/ # Tests unitarios y de integración


---

## 🧠 Entidades principales

| Entidad | Descripción |
|----------|--------------|
| `CustomerEntity` | Representa al cliente registrado. |
| `ProfileEntity` | Información personal y de contacto del cliente. |
| `RoleEntity` | Define roles simples (por ejemplo, `USER`). |
| `ProductEntity` | Catálogo de productos que pueden incluirse en una entrega (nombre, descripción, precio, stock, etc.). |
| `DeliveryEntity` | Representa el acto de **retirar un pedido en el comercio** (no delivery a domicilio). |
| `DeliveryDetails` | Detalle de los productos incluidos en una entrega (relación N:1 con `DeliveryEntity` y con `ProductEntity`). |
| `QueueEntity` | Representa la cola virtual de espera. |
| `NotificationEntity` | Mensajes enviados al cliente sobre su turno o pedido. |
| `NotificationTypeEntity` | Tipos de notificaciones (ej. “AVISO_TURNO”, “PEDIDO_LISTO”). |

---

---

## 🧠 Entidades principales

| Entidad | Descripción |
|----------|--------------|
| `CustomerEntity` | Representa al cliente registrado. |
| `ProfileEntity` | Información personal y de contacto del cliente. |
| `RoleEntity` | Define roles simples (por ejemplo, `USER`). |
| `ProductEntity` | Catálogo de productos que pueden incluirse en una entrega (nombre, descripción, precio, stock, etc.). |
| `DeliveryEntity` | Representa el acto de **retirar un pedido en el comercio** (no delivery a domicilio). |
| `DeliveryDetails` | Detalle de los productos incluidos en una entrega (relación N:1 con `DeliveryEntity` y con `ProductEntity`). |
| `QueueEntity` | Representa la cola virtual de espera. |
| `NotificationEntity` | Mensajes enviados al cliente sobre su turno o pedido. |
| `NotificationTypeEntity` | Tipos de notificaciones (ej. “AVISO_TURNO”, “PEDIDO_LISTO”). |

---
<img width="753" height="812" alt="diagrama back" src="https://github.com/user-attachments/assets/d5b1ea70-0a8c-461f-9482-52194310cb55" />




<img width="588" height="683" alt="diagrama de clases front" src="https://github.com/user-attachments/assets/a387b4e6-2f0a-4738-805d-61c8ca12eacb" />



👨‍💻 Autor

Fernando Marcos
Proyecto académico / personal — 2025

