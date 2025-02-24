# Microservicio de Asistente Virtual

## Descripción General
Este microservicio es parte de un sistema de asistente virtual, diseñado para procesar consultas de los usuarios y devolver respuestas simuladas. Sigue una arquitectura de microservicios y utiliza Spring para su desarrollo.

## API Documentacion

### Base URL: `/api/chat`

### Endpoints

#### Process User Question

- **Endpoint:** `POST /chat`
- **Descripción:** Recibe la pregunta de un usuario y devuelve una respuesta simulada.
- **Request Body:**

  ```json
  {
    "message": "What is the weather like?"
  }
  ```

- **Response Body:**

  ```json
  {
    "response": "The weather is sunny.",
    "context": {
      "conversationId": "12345",
      "timestamp": "2025-02-23 12:00:00"
    }
  }
  ```

- **Error Responses:**

- **Invalid Input:**

  ```json
  {
    "error": "Invalid input. Please provide a valid question.",
    "context": {
        "conversationId": "12345",
        "timestamp": "2025-02-23 12:00:00"
    }
  }
  ```

  - **Pregunta no reconocida:**

    ```json
    {
      "error": "I don't understand the question.",
      "context": {
        "conversationId": "12345",
        "timestamp": "2025-02-23 12:00:00"
      }
    }
    ```

## Justificación de la Arquitectura

### Stack Tecnológico

- **Spring Boot**: Permite un desarrollo rápido y simplifica la gestión de dependencias.
- **Spring Web**: Proporciona un marco robusto para el desarrollo de API RESTful.
- **Swagger (Springdoc OpenAPI)**: Utilizado para la documentación y prueba de la API.
- **JUnit, Mockito & MockServer**: Garantizan la cobertura de pruebas y la fiabilidad.
- **Spring Cloud Gateway**: Implementa el patrón Gateway para gestionar el tráfico, aplicar políticas de seguridad y enrutar solicitudes.
- **MariaDB (Integración futura)**: Posible base de datos para almacenar el historial de conversaciones.

## Arquitectura del Sisatema

### Diagrama de Componentes e Integración

```
+----------------+      +----------------------+       +----------------------+
|  API Gateway   | ---> |  Chat                | ---> |  Servicios externos   |
|  (Spring Cloud)|      |  Microservice        |      |  (Weather API)        |
+----------------+      +----------------------+       +----------------------+
```

- **API Gateway** sirve como un único punto de entrada y enruta las solicitudes de manera segura.
- **Microservicio Chat ** procesa las consultas de los usuarios y genera respuestas.
- **Microservicio Clima** proporcionan datos en tiempo real, como el clima.

### Diagrama de secuencia: "El usuario pregunta sobre el clima"

1. El usuario envía una solicitud a /api/chat con una pregunta.
2. La Puerta de Enlace API reenvía la solicitud al microservicio.
3. El microservicio analiza la pregunta.
4. Si se encuentra una coincidencia, devuelve una respuesta predefinida.
5. Si no se encuentra una coincidencia, devuelve un mensaje de error.
6. Opcionalmente, puede llamar a una API externa para obtener datos en tiempo real sobre el clima.

```
Usuario --> Puerta de Enlace API --> Microservicio de Chat --> API Externa (opcional)
Usuario <-- Puerta de Enlace API <-- Microservicio de Chat <-- Respuesta
```

## Consideraciones de Seguridad
(Posible futrura implementación)

- Implementar autenticación basada en JWT para un acceso seguro a la API.
- Aplicar limitación de tasa para prevenir el uso excesivo de la API.
- Forzar HTTPS para asegurar la transmisión de datos.

## Mejoras Futuras

- **Integraciones de API en tiempo real** para mejorar la precisión de las respuestas.
- **Integración de base de datos** para rastrear y almacenar las interacciones de los usuarios.
- **Procesamiento de Lenguaje Natural (NLP)** para la generación inteligente de respuestas.

## Getting Started

### Pre-requisitos
- Java 17 o superior
- Maven

### Ejecución

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/your-repo/virtual-assistant.git
   ```
2. Build:
   ```sh
   mvn clean install
   ```
3. Run:
   ```sh
   mvn spring-boot:run
   ```

### Running Tests

Para ejecutar los test:
```sh
mvn test
```

## Conclusion
This microservice is a foundational component of the virtual assistant ecosystem. It enables simple conversational interactions and can be extended with additional services for a richer user experience.

