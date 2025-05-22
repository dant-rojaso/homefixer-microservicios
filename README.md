# HomeFixer - Microservicios

Sistema de microservicios para la plataforma HomeFixer que conecta clientes con técnicos especializados.

## Arquitectura

Este proyecto implementa una arquitectura de microservicios con los siguientes servicios:

### Microservicios Implementados

1. **Servicio de Solicitudes** (Puerto: 8081)
   - Gestión del ciclo de vida de las solicitudes de servicio
   - Endpoints: `/api/solicitudes`

2. **Servicio de Asignación** (Puerto: 8082)
   - Asignación automática de técnicos a solicitudes
   - Endpoints: `/api/asignacion`

3. **Servicio de Notificaciones** (Puerto: 8083)
   - Notificaciones de estado en tiempo real
   - Endpoints: `/api/notificaciones`

4. **Servicio de Insignias/Reputación** (Puerto: 8090)
   - Sistema de reputación y niveles de técnicos
   - Endpoints: `/api/reputacion`

5. **Servicio de Procesamiento de Pagos** (Puerto: 8085)
   - Gestión de pagos y transacciones
   - Endpoints: `/api/pagos`

6. **Servicio de Comisiones** (Puerto: 8086)
   - Cálculo y gestión de comisiones
   - Endpoints: `/api/comisiones`

7. **Servicio de Autenticación** (Puerto: 8087)
   - Autenticación y autorización de usuarios
   - Endpoints: `/api/auth`

8. **Servicio de Roles** (Puerto: 8088)
   - Gestión de roles y permisos
   - Endpoints: `/api/roles`

9. **Servicio de Valoración** (Puerto: 8089)
   - Sistema de valoraciones de técnicos
   - Endpoints: `/api/valoraciones`

10. **Servicio de Usuarios** (Puerto: 8091)
    - Gestión de datos de usuarios
    - Endpoints: `/api/usuarios`

## Tecnologías Utilizadas

- **Spring Boot 3.3.3**
- **Java 17**
- **Spring Data JPA**
- **H2 Database** (en memoria para desarrollo)
- **Maven**
- **Lombok**

## Cómo ejecutar

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior

### Ejecutar un microservicio
```bash
cd servicio-solicitudes
mvn spring-boot:run