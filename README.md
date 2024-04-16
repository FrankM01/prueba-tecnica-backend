
# Proyecto de Backend - API de Futbolistas

Este proyecto consiste en una API para gestionar información de futbolistas, incluyendo sus nombres, apellidos, fecha de nacimiento, características y posición en el campo.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3
- MySQL + Driver
- Spring Web
- Maven
- Lombok
- Spring Data JPA
- Postman

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- `src/main/java`: Contiene el código fuente de la aplicación Java.
  - Asi como dentro esta carpetizado por `controller`, `model` y 'service'.
- `src/main/resources`: Contiene archivos de configuración y recursos estáticos.
- `pom.xml`: Archivo de configuración de Maven.

## Endpoints Disponibles

La API ofrece los siguientes endpoints:

- `GET /api/v1/futbolistas`: Obtiene la lista de todos los futbolistas registrados.
- `GET /api/v1/futbolista/{id}`: Obtiene la información de un futbolista por su ID.
- Asi como tambien los demas como `POST`,`PUT` y `DELETE`
- Ejemplo: `localhost:8092/api/v1/futbolistas`

## Configuración de la Base de Datos

La aplicación utiliza una base de datos MySQL para almacenar la información de los futbolistas. Asegúrate de configurar correctamente las credenciales de la base de datos en el archivo `application.properties` ubicado en `src/main/resources`.
Ahi podrá colocar el `puerto`, `url`, `username` y `password`

## Iniciar la Aplicación

Para ejecutar la aplicación, sigue estos pasos:

1. Asegúrate de tener instalado Java 17 y Maven en tu sistema.
2. Configura una base de datos MySQL y actualiza las credenciales en `application.properties`.
3. Asi como tener la dependecia de `spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect`
4. Y ahora si ejecutar el proyecto
   `

## Consideraciones

- La API solo esta de manera localhost, pero cumple con todo lo necesario.
## Autor

[Frank Mijhael]

---