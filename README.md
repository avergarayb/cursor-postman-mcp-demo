# API REST de pedidos

Backend con **Spring Boot**, **Spring Data JPA** y **H2** en memoria. Expone CRUD sobre usuarios, productos, órdenes y líneas de pedido.

## Características

- CRUD completo sobre las entidades del dominio.
- Arquitectura en capas: controller, service, repository.
- **DTOs** y mapeo con **MapStruct** (evita problemas de serialización).
- Validación con **Jakarta Validation** y respuestas de error centralizadas con **`@ControllerAdvice`**.
- Documentación **OpenAPI 3** generada automáticamente y **Swagger UI** integrado (SpringDoc).

## Stack

| Tecnología | Uso |
|------------|-----|
| Spring Boot 3.5 | Web, configuración |
| Spring Data JPA | Persistencia |
| H2 | Base en memoria y consola web |
| Lombok | Reducción de boilerplate |
| MapStruct | Entity ↔ DTO |
| SpringDoc OpenAPI | Especificación OpenAPI y explorador Swagger UI |

## Requisitos

- JDK 21
- Maven 3.9+

## Ejecución

```bash
cd cursor-postman-mcp-demo
mvn spring-boot:run
```

Comprobar que responde:

```text
http://localhost:8080/api/v1/users
```

Compilar y ejecutar tests:

```bash
mvn clean verify
```

## Datos iniciales

Se cargan desde `src/main/resources/data.sql` al arrancar la aplicación.

## API

Base: `http://localhost:8080/api/v1`

| Recurso | Métodos |
|---------|---------|
| `/users` | GET, POST, PUT, DELETE |
| `/users/{id}/orders` | GET |
| `/products` | GET, POST, PUT, DELETE |
| `/orders` | GET, POST, PUT, DELETE |
| `/orders/{orderId}/items` | GET |
| `/order-items` | GET, POST, PUT, DELETE |

Al crear un pedido, el campo `date` es opcional; si se omite, se usa la fecha y hora actuales.

## Documentación OpenAPI

Con la aplicación en marcha:

| Recurso | URL |
|---------|-----|
| **Swagger UI** (probar endpoints) | `http://localhost:8080/swagger-ui.html` |
| **OpenAPI JSON** | `http://localhost:8080/v3/api-docs` |
| **OpenAPI YAML** | `http://localhost:8080/v3/api-docs.yaml` |

La especificación se genera a partir de los controladores y los DTOs (incluye restricciones de validación cuando aplica). Metadatos globales en `OpenApiConfig`.

## Consola H2

URL: `http://localhost:8080/h2-console`

| Campo | Valor |
|-------|--------|
| JDBC URL | `jdbc:h2:mem:orderdb` |
| Usuario | `sa` |
| Contraseña | *(vacía)* |

## Estructura del código

```text
src/main/java/com/example/cursorpostmanmcpdemo/
├── config/         # OpenAPI (SpringDoc)
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── mapper/
└── exception/
```

## Modelo de dominio

- Un **User** tiene muchas **Order**.
- Una **Order** tiene muchas **OrderItem**.
- Un **Product** puede aparecer en muchas **OrderItem**.

## Licencia

Código de ejemplo con fines educativos. Úsalo y adáptalo según necesites.

**Punto de entrada:** `com.example.cursorpostmanmcpdemo.CursorPostmanMcpDemoApplication`
